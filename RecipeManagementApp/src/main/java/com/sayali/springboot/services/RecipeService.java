package com.sayali.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sayali.springboot.models.Recipe;
import com.sayali.springboot.models.RecipeSearch;

@Service
public interface RecipeService {
	
	public List<Recipe> getRecipes();
	
	public Optional<Recipe> getRecipe(int id);
	
	public Recipe createRecipe(Recipe recipe);
	
	public Optional<Recipe> updateRecipe(Recipe recipe);
	
	public String deleteRecipe(int id);
	
	public List<Recipe> searchRecipe(RecipeSearch recipe);

}
