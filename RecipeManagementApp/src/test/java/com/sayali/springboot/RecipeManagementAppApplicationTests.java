package com.sayali.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.sayali.springboot.controllers.RecipeManagementController;
import com.sayali.springboot.models.Recipe;
import com.sayali.springboot.models.*;

@SpringBootTest
class RecipeManagementAppApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeManagementAppApplicationTests.class);

	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${recipe.services.url}")
	private String baseURL;
	
	
	public RecipeManagementAppApplicationTests() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testGetRecipe() {
		LOGGER.info(baseURL);
		RecipeActionResult recipe =  restTemplate.getForObject (baseURL+"1", RecipeActionResult.class);
		assertNotNull(recipe);
		LOGGER.info("Name "+recipe.getRecipe().getName());
		assertEquals("Tea", recipe.getRecipe().getName());
	}
	
	@Test
	public void testCreateRecipe() {
		Recipe recipe = new Recipe();
		recipe.setName("Rice");
		recipe.setIngredients("Rice and water");
		recipe.setInstructions("Boil water and rice");
		recipe.setServings(5);
		recipe.setUserID(1);
		recipe.setVeg(1);;
		RecipeActionResult newRecipe = restTemplate.postForObject(baseURL, recipe, RecipeActionResult.class);
		assertNotNull(newRecipe);
		assertNotNull(newRecipe.getRecipe().getId());
		assertEquals("Rice", newRecipe.getRecipe().getName());
	}
	
	@Test
	public void testUpdateRecipe() {
		
		RestTemplate restTemplate1 = new RestTemplate();

		RecipeActionResult recipe1 =  restTemplate1.getForObject(baseURL+"2", RecipeActionResult.class);
		
		recipe1.getRecipe().setServings(4);;

		//restTemplate1.put("http://localhost:8080/recipes/", recipe1);
		restTemplate1.put(baseURL+"{id}", recipe1, recipe1.getRecipe().getId());
		
	}
	
}
