package com.sayali.springboot.models;

import java.util.Date;

public class RecipeSearch {
	
	private int id;
	private int servings;
	private int userID;
	private String name;
	private String instructions;
	private String includeIngredients;
	private String excludeIngredients;
	private int veg;
	
	/*
	 * public String getCreatedDate() { return createdDate; } public void
	 * setCreatedDate(String createdDate) { this.createdDate = createdDate; } public
	 * String getUpdatedDate() { return updatedDate; } public void
	 * setUpdatedDate(String updatedDate) { this.updatedDate = updatedDate; }
	 * private String createdDate; private String updatedDate;
	 */
	public int getVeg() {
		return veg;
	}
	public void setVeg(int veg) {
		this.veg = veg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getServings() {
		return servings;
	}
	
	public RecipeSearch(int id, int servings, int userID, String name, String instructions, String includeIngredients,
			String excludeIngredients, int veg) {
		super();
		this.id = id;
		this.servings = servings;
		this.userID = userID;
		this.name = name;
		this.instructions = instructions;
		this.includeIngredients = includeIngredients;
		this.excludeIngredients = excludeIngredients;
		this.veg = veg;
	}
	public void setServings(int servings) {
		this.servings = servings;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getIncludeIngredients() {
		return includeIngredients;
	}
	public void setIncludeIngredients(String includeIngredients) {
		this.includeIngredients = includeIngredients;
	}
	public String getExcludeIngredients() {
		return excludeIngredients;
	}
	public void setExcludeIngredients(String excludeIngredients) {
		this.excludeIngredients = excludeIngredients;
	}
	/*
	 * public boolean isVeg() { return isVeg; } public void setVeg(boolean isVeg) {
	 * this.isVeg = isVeg; }
	 */

}
