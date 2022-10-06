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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
				.perform(post(baseURL + "authenticate").contentType(MediaType.APPLICATION_JSON).content(requestJson));

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
		ResultActions response = mockMvc.perform(get(baseURL).header("Authorization", "Bearer " + myToken));
		response.andExpect(status().isOk());
	}

	@Test
	public void testGetRecipeByID() throws Exception {
		LOGGER.info("In get by ID test");
		ResultActions response = mockMvc.perform(get(baseURL + "100").header("Authorization", "Bearer " + myToken));

		response.andExpectAll(status().isOk());

	}
	
	@Test
	public void testDeleteRecipeByID() throws Exception {
		LOGGER.info("In get by ID test");
		ResultActions response = mockMvc.perform(delete(baseURL + "100").header("Authorization", "Bearer " + myToken));

		response.andExpectAll(status().isOk());

	}

	@Test
	public void testCreateRecipe() throws Exception {
		LOGGER.info("In createt test");

		RecipeRequest recipeRequest = new RecipeRequest();
		recipeRequest.setIngredients("Test Ingredients");
		recipeRequest.setInstructions("Test Instructions");
		recipeRequest.setName("Test");
		recipeRequest.setServings(10);
		recipeRequest.setVeg(false);
		recipeRequest.setUserID(1);

		Gson gson = new Gson();
		String requestJson = gson.toJson(recipeRequest);

		ResultActions response = mockMvc.perform(post(baseURL).header("Authorization", "Bearer " + myToken)
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
		recipeRequest.setVeg(false);
		recipeRequest.setUserID(1);

		Gson gson = new Gson();
		String requestJson = gson.toJson(recipeRequest);

		ResultActions response = mockMvc.perform(put(baseURL + "100").header("Authorization", "Bearer " + myToken)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson));

		response.andExpectAll(status().isOk());

	}

	@Test
	public void testSearchRecipe() throws Exception {
		LOGGER.info("In search test");
		
		RecipeSearch searchRequest = new RecipeSearch();
		searchRequest.setIncludeIngredients("Milk");
		searchRequest.setExcludeIngredients("Coffee");
		searchRequest.setInstructions("Boil");
		searchRequest.setName("Tea");
		searchRequest.setServings(10);
		searchRequest.setVeg(true);
		searchRequest.setUserID(1);

		Gson gson = new Gson();
		String requestJson = gson.toJson(searchRequest);

		
		ResultActions response = mockMvc.perform(get(baseURL + "search").header("Authorization", "Bearer " + myToken)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson));

		response.andExpectAll(status().isOk());

	}

}
