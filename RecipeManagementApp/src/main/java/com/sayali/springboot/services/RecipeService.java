package com.sayali.springboot.services;

import java.util.List;
import java.util.Optional;

import com.sayali.springboot.models.Recipe;
import com.sayali.springboot.models.RecipeRequest;
import com.sayali.springboot.models.RecipeSearch;
import com.sayali.springboot.models.RecipeSearchResult;

public interface RecipeService {
	
	public List<Recipe> getRecipes();
	
	public Optional<Recipe> getRecipe(int id);
	
	public Recipe createRecipe(Recipe recipe);
	
	public Optional<Recipe> updateRecipe(Recipe recipe);
	
	public String deleteRecipe(int id);
	
	public List<Recipe> searchRecipe(RecipeSearch recipe);

}
