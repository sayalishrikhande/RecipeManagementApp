package com.sayali.springboot.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sayali.springboot.models.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
