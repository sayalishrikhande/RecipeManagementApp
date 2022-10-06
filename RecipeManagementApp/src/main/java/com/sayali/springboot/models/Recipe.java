package com.sayali.springboot.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.sayali.springboot.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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
	private Boolean veg;

	@NotNull
	@CreatedDate
	private Date created_Date;

	@NotNull
	@LastModifiedDate
	private Date updated_Date;
	
	@CreatedBy
	@NotNull
	private int created_By;
	
	@LastModifiedBy
	@NotNull
	private int updated_By;

	public int getUpdated_By() {
		return updated_By;
	}

	public void setUpdated_By(int updated_By) {
		this.updated_By = updated_By;
	}

	public Recipe(Recipe recipe) { // TODO Auto-generated constructor stub
		if(0<recipe.getId()) {
			this.id = recipe.getId();
		}
		if(null!=recipe.getIngredients()) {
		this.ingredients = recipe.getIngredients();
		}
		if(null!=recipe.getInstructions()) {
		this.instructions = recipe.getInstructions();
		}
		if(null!=recipe.getName()) {
		this.name = recipe.getName();
		}
		if(0<recipe.getServings()) {
		this.servings = recipe.getServings();
		}
		if(0<recipe.getUserID()) {
		this.userID = recipe.getUserID();
		}
		if(null!=recipe.isVeg()) {
		this.veg = recipe.isVeg();
		}
		if(null!=recipe.getCreated_Date()) {
		this.created_Date = DateUtil.getCurrentDateTime();
		}
		if(null!=recipe.getUpdated_Date()) {
		this.updated_Date = DateUtil.getCurrentDateTime();
		}
	}

	public Recipe() {
		// TODO Auto-generated constructor stub
	}

	public Boolean isVeg() {
		return veg;
	}

	public void setVeg(Boolean veg) {
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

	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date createdDate) {
		this.created_Date = createdDate;
	}

	public Date getUpdated_Date() {
		return updated_Date;
	}

	public void setUpdated_Date(Date updatedDate) {
		this.updated_Date = updatedDate;
	}

	public int getCreatedBy() {
		return created_By;
	}

	public void setCreatedBy(int createdBy) {
		this.created_By = createdBy;
	}

}
