package com.sayali.springboot.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sayali.springboot.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
