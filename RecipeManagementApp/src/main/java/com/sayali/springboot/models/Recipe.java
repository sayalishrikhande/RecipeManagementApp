package com.sayali.springboot.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.sayali.springboot.models.RecipeRequest;
import com.sayali.springboot.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe")
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

	@NotNull
	private Date created_Date;

	@NotNull
	private Date updated_Date;

	public Recipe(RecipeRequest recipe) { // TODO Auto-generated constructor stub
		this.ingredients = recipe.getIngredients();
		this.instructions = recipe.getInstructions();
		this.name = recipe.getName();
		this.servings = recipe.getServings();
		this.userID = recipe.getUserID();
		this.veg = recipe.getVeg();
		this.created_Date = DateUtil.getCurrentDateTime();
		this.updated_Date = DateUtil.getCurrentDateTime();
	}

	public Recipe() {
		// TODO Auto-generated constructor stub
	}

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

	public Date getCreatedDate() {
		return created_Date;
	}

	public void setCreatedDate(Date createdDate) {
		this.created_Date = createdDate;
	}

	public Date getUpdatedDate() {
		return updated_Date;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updated_Date = updatedDate;
	}

}
