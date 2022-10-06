package com.sayali.springboot.models;

import java.util.List;

import com.sayali.springboot.models.Recipe;

public class RecipeResult extends RecipeStatusResponse {

	public RecipeResult(Integer status, String message) {
		super(status, message);
		// TODO Auto-generated constructor stub
	}

	private List<Recipe> recipes = null;

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public RecipeResult(Integer status, String message, List<Recipe> recipes) {
		super(status, message);
		// TODO Auto-generated constructor stub

		this.setRecipes(recipes);
	}

}
