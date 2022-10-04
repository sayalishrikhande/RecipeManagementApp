package com.sayali.springboot.models;

import java.util.List;

public class RecipeSearchResult extends RecipeStatusResponse{
	
	private List<Recipe> recipes = null;

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	

	public RecipeSearchResult(Integer status, String message, List<Recipe> recipes) {
		super(status, message);
		// TODO Auto-generated constructor stub
		
		this.setRecipes(recipes);
	}

}
