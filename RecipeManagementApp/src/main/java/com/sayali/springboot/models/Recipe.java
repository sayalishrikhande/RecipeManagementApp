package com.sayali.springboot.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Min(1)
	private int id;
	@NotNull
	@Min(1)
	private int servings;
	@NotNull
	@Min(1)
	private int userID;
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	private String instructions;
	@NotNull
	private String ingredients;
	@NotNull
	@Min(1)
	private int veg;

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

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	

	/*
	 * private String createdDate;
	 * 
	 * public String getCreatedDate() { return createdDate; }
	 * 
	 * public void setCreatedDate(String createdDate) { this.createdDate =
	 * createdDate; }
	 * 
	 * public String getUpdatedDate() { return updatedDate; }
	 * 
	 * public void setUpdatedDate(String updatedDate) { this.updatedDate =
	 * updatedDate; }
	 * 
	 * private String updatedDate;
	 */

}
