package com.sayali.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sayali.springboot.controllers.RecipeManagementController;
import com.sayali.springboot.models.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import javax.net.ssl.SSLEngineResult.Status;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeManagementAppApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeManagementAppApplicationTests.class);

	RestTemplate restTemplate = new RestTemplate();

	@Value("${recipe.services.url}")
	private String baseURL;

	public RecipeManagementAppApplicationTests() {
		// TODO Auto-generated constructor stub
	}

	String myToken = null;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {

		AuthenticationRequest newUser = new AuthenticationRequest();
		newUser.setUsername("myUser");
		newUser.setUserpassword("mypass");

		Gson gson = new Gson();
		String requestJson = gson.toJson(newUser);

		ResultActions response = mockMvc
				.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(requestJson));

		response.andExpect(status().isOk());

		MvcResult result = response.andReturn();

		LOGGER.info(result.getResponse().getContentAsString());

		String myToken1 = result.getResponse().getContentAsString();

		myToken = myToken1.substring(13, myToken1.length() - 2);

		LOGGER.info("My Token is " + myToken);

	}

	@Test
	public void testGetRecipes() throws Exception {

		LOGGER.info("Token in get test is --- " + myToken);
		ResultActions response = mockMvc.perform(get("/recipes/").header("Authorization", "Bearer " + myToken));
		response.andExpect(status().isOk());
	}

	@Test
	public void testGetRecipeByID() throws Exception {
		LOGGER.info("In get by ID test");
		ResultActions response = mockMvc.perform(get("/recipes/100").header("Authorization", "Bearer " + myToken));

		response.andExpectAll(status().isOk());

	}
	
	@Test
	public void testCreateRecipeByID() throws Exception {
		LOGGER.info("In createt test");
		
		RecipeRequest recipeRequest = new RecipeRequest();
		recipeRequest.setIngredients("Test Ingredients");
		recipeRequest.setInstructions("Test Instructions");
		recipeRequest.setName("Test");
		recipeRequest.setServings(10);
		recipeRequest.setVeg(2);
		recipeRequest.setUserID(1);

		Gson gson = new Gson();
		String requestJson = gson.toJson(recipeRequest);
		
		ResultActions response = mockMvc.perform(post("/recipes/").header("Authorization", "Bearer " + myToken)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson));

		response.andExpectAll(status().isOk());

	}

	@Test
	public void testUpdateRecipeByID() throws Exception {
		LOGGER.info("In update test ");
		
		RecipeRequest recipeRequest = new RecipeRequest();
		recipeRequest.setId(100);
		recipeRequest.setIngredients("Test Ingredients");
		recipeRequest.setInstructions("Test Instructions");
		recipeRequest.setName("Test");
		recipeRequest.setServings(10);
		recipeRequest.setVeg(2);
		recipeRequest.setUserID(1);

		Gson gson = new Gson();
		String requestJson = gson.toJson(recipeRequest);
		
		ResultActions response = mockMvc.perform(put("/recipes/100").header("Authorization", "Bearer " + myToken)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson));

		response.andExpectAll(status().isOk());

	}
	

	/*
	 * @Test public void testGetRecipe() { LOGGER.info(baseURL); RecipeActionResult
	 * recipe = restTemplate.getForObject (baseURL+"1", RecipeActionResult.class);
	 * assertNotNull(recipe); LOGGER.info("Name "+recipe.getRecipe().getName());
	 * assertEquals("Tea", recipe.getRecipe().getName()); }
	 * 
	 * @Test public void testCreateRecipe() { Recipe recipe = new Recipe();
	 * recipe.setName("Rice"); recipe.setIngredients("Rice and water");
	 * recipe.setInstructions("Boil water and rice"); recipe.setServings(5);
	 * recipe.setUserID(1); recipe.setVeg(1); RecipeActionResult newRecipe =
	 * restTemplate.postForObject(baseURL, recipe, RecipeActionResult.class);
	 * assertNotNull(newRecipe); assertNotNull(newRecipe.getRecipe().getId());
	 * assertEquals("Rice", newRecipe.getRecipe().getName()); }
	 * 
	 * @Test public void testUpdateRecipe() {
	 * 
	 * RestTemplate restTemplate1 = new RestTemplate();
	 * 
	 * RecipeActionResult recipe1 = restTemplate1.getForObject(baseURL+"2",
	 * RecipeActionResult.class);
	 * 
	 * recipe1.getRecipe().setServings(4);;
	 * 
	 * //restTemplate1.put("http://localhost:8080/recipes/", recipe1);
	 * restTemplate1.put(baseURL+"{id}", recipe1, recipe1.getRecipe().getId());
	 * 
	 * }
	 * 
	 */

}
