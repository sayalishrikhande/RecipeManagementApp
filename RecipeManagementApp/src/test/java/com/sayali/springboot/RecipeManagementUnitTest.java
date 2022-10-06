package com.sayali.springboot;

/*import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;*/

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import com.sayali.springboot.controllers.RecipeManagementController;
import com.sayali.springboot.filters.RecipeManagementFilter;
import com.sayali.springboot.models.Recipe;
import com.sayali.springboot.models.RecipeActionResult;
import com.sayali.springboot.models.RecipeResult;
import com.sayali.springboot.models.RecipeSearch;
import com.sayali.springboot.models.RecipeSearchResult;
import com.sayali.springboot.services.RecipeService;
import com.sayali.springboot.services.serviceImpl.MyUserDetailsServiceImpl;
import com.sayali.springboot.services.serviceImpl.RecipeServiceImpl;
import com.sayali.springboot.util.DateUtil;

@RunWith(SpringRunner.class)
public class RecipeManagementUnitTest {

	RecipeManagementController recipeMngController;
	Recipe recipe;

	@BeforeEach
	public void setUp() {
		RecipeService recipeService = Mockito.mock(RecipeServiceImpl.class);
		this.recipeMngController = new RecipeManagementController(recipeService);

		recipe = new Recipe();
		recipe.setName("Coffee");
		recipe.setIngredients("Water, Milk, Coffee, Sugar");
		recipe.setInstructions("Boil the ingredients together");
		recipe.setServings(2);
		recipe.setUserID(1);
		recipe.setVeg(true);
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
		ResponseEntity<RecipeSearchResult> recipeResult = recipeMngController
				.searchRecipe(new RecipeSearch(1, 1, 1, "Tea", "Boil", "Milk", "Coffee", true));

		assertNotNull(recipeResult.getBody().getStatus());
		assertNotNull(recipeResult.getBody().getMessage());
	}

	@Test
	public void testDateUtil() {
		assertNotNull(DateUtil.getCurrentDateTime());
	}
	
	@Test
	public void testFiltes() throws Exception{
		RecipeManagementFilter testFilter = new RecipeManagementFilter();
		MockFilterChain mockChain = new MockFilterChain();
	    MockHttpServletRequest req = new MockHttpServletRequest();
	    MockHttpServletResponse rsp = new MockHttpServletResponse();
		testFilter.doFilter(req, rsp, mockChain);
		assertEquals("", rsp.getContentAsString());
	}
	
	@Test
	public void testUserDetailsService() throws Exception{
		MyUserDetailsServiceImpl myUser = new MyUserDetailsServiceImpl();
		assertNotNull(myUser.loadUserByUsername("myUser"));
	}

}
