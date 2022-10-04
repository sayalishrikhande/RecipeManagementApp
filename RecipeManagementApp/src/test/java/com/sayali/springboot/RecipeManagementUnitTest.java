package com.sayali.springboot;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/*import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;*/

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;

import com.sayali.springboot.controllers.RecipeManagementController;
import com.sayali.springboot.models.Recipe;
import com.sayali.springboot.models.RecipeActionResult;
import com.sayali.springboot.models.RecipeResult;
import com.sayali.springboot.models.RecipeSearch;
import com.sayali.springboot.models.RecipeSearchResult;
//import com.sayali.springboot.repos.RecipeRepository;
import com.sayali.springboot.services.RecipeService;
import com.sayali.springboot.services.RecipeServiceImpl;

@RunWith(SpringRunner.class)
public class RecipeManagementUnitTest {
	
	/*
	 * @Autowired private MockMvc mockMVC;
	 * 
	 * @MockBean private RecipeRepository repo;
	 */
	
	RecipeManagementController recipeMngController;
	Recipe recipe;
	
	@BeforeEach
	public void setUp () {
		RecipeService recipeService = Mockito.mock(RecipeServiceImpl.class);
		this.recipeMngController = new RecipeManagementController(recipeService);
		
		recipe = new Recipe();
		recipe.setName("Coffee");
		recipe.setIngredients("Water, Milk, Coffee, Sugar");
		recipe.setInstructions("Boil the ingredients together");
		recipe.setServings(2);
		recipe.setUserID(1);
		recipe.setVeg(1);
	}	
	
	@Test
	public void testGetRecipes() {
		RecipeResult recipeResult = recipeMngController.getRecipes();
		
		assertNotNull(recipeResult);
		assertNotEquals(-1, recipeResult.getRecipes().size());	
	}
	
	@Test
	public void testGetRecipe() {
		RecipeActionResult recipeResult = recipeMngController.getRecipe(1);
		
		assertNotNull(recipeResult.getStatus());
	}
	
	@Test
	public void testCreateRecipe() {
		RecipeActionResult recipeResult = recipeMngController.createRecipe(recipe);
		
		assertNotNull(recipeResult.getMessage());
		assertNotNull(recipeResult.getStatus());		
	}
	
	@Test
	public void testUpdateRecipe() {
		RecipeActionResult recipeResult = recipeMngController.updateRecipe(recipe);
		
		assertNotNull(recipeResult.getMessage());
		assertNotNull(recipeResult.getStatus());	
	}
	
	@Test
	public void testDeleteRecipe() {
		RecipeActionResult recipeResult = recipeMngController.updateRecipe(recipe);
		
		assertNotNull(recipeResult.getMessage());
		assertNotNull(recipeResult.getStatus());	
	}
	
	@Test
	public void testSearchRecipe() {
		ResponseEntity<RecipeSearchResult> recipeResult = recipeMngController.searchRecipe(new RecipeSearch(1, 1, 1, "Tea", "Boil", "Milk", "Coffee", 1));
		
		assertNotNull(recipeResult.getBody().getStatus());
		assertNotNull(recipeResult.getBody().getMessage());	
	}

	/*@Test
	public void testFindAll() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(3);
		recipe.setIngredients("Rice");
		recipe.setIngredients("Rice and water");
		recipe.setInstructions("Boil water and rice");
		recipe.setServings(5);
		recipe.setUserID(1);
		
		List<Recipe> recipes = new ArrayList<>();
		
		recipes.add(recipe);
		
		when(repo.findAll()).thenReturn(recipes);
		
		try {
			
			mockMVC.perform(get("/recipes/")).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
*/

}
