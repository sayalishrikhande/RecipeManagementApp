package com.sayali.springboot.models;

public class RecipeActionResult extends RecipeStatusResponse {

	public RecipeActionResult(Integer status, String message) {
		super(status, message);
		// TODO Auto-generated constructor stub
	}

	private Recipe recipe = null;

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public RecipeActionResult(Integer status, String message, Recipe recipe) {
		super(status, message);
		// TODO Auto-generated constructor stub

		this.setRecipe(recipe);
	}

}
