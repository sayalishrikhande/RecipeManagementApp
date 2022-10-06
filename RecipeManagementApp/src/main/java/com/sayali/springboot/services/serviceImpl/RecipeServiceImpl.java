package com.sayali.springboot.services.serviceImpl;

import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayali.springboot.models.RecipeSearch;
import com.sayali.springboot.repos.RecipeRepository;
import com.sayali.springboot.services.RecipeService;
import com.sayali.springboot.util.DateUtil;
import com.sayali.springboot.models.Recipe;

@Service
public class RecipeServiceImpl implements RecipeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);

	@Autowired
	RecipeRepository repo;

	@Override
	public List<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		// return null;
		List<Recipe> myList = repo.findAll();

		LOGGER.info("Here   " + myList.size());

		return myList;
	}

	@Override
	public Optional<Recipe> getRecipe(int id) {
		// TODO Auto-generated method stub
		Assert.notNull(id, "ID is null");
		Optional<Recipe> recipe = repo.findById(id);

		return recipe;
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		Assert.notNull(recipe, "Input is null");
		
		recipe.setCreatedDate(DateUtil.getCurrentDateTime());
		recipe.setUpdatedDate(DateUtil.getCurrentDateTime());
		Recipe savedRecipe = repo.save(recipe);
		return savedRecipe;
	}

	@Override
	public Optional<Recipe> updateRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		Assert.notNull(recipe, "Request is null");
		Assert.notNull(recipe.getId(), "ID is null");
		recipe.setUpdatedDate(DateUtil.getCurrentDateTime());
		LOGGER.info("Here in update");

		Optional<Recipe> updateRecipe = repo.findById(recipe.getId());
		
		if(updateRecipe.get()!=null) {
			LOGGER.info("Found by id "+ updateRecipe.get().getId());
			updateRecipe = Optional.of(repo.save(recipe));
		}
				
		return updateRecipe;
	}

	@Override
	public String deleteRecipe(int id) {
		// TODO Auto-generated method stub
		Assert.notNull(id, "ID is null");
		System.out.println("Here");
		
		Recipe deleteRecipe = repo.findById(id).orElse(null);
		
		if(null!=deleteRecipe) {
		repo.deleteById(id);
		return "Deleted ID is " + id;
		} else {
			return "Not found ID " + id;
		}
		
	}

	@Override
	public List<Recipe> searchRecipe(RecipeSearch recipe) {
		// TODO Auto-generated method stub

		List<Recipe> recipes = repo.findAll();

		LOGGER.info("Total Recipes " + recipes.size());

		List<Recipe> recipes1 = null;

		try {

			if (recipe.getName() != null) {
				LOGGER.info("Name is " + recipe.getName());
				recipes1 = recipes.stream()
						.filter(x -> x.getName().toLowerCase().contains(recipe.getName().toLowerCase()))
						.collect(Collectors.toList());
				recipes = recipes1;
			}

			if (recipe.getIncludeIngredients() != null) {
				recipes1 = recipes.stream()
						.filter(x -> x.getIngredients().toLowerCase().contains(recipe.getIncludeIngredients()))
						.collect(Collectors.toList());
				recipes = recipes1;
			}

			if (recipe.getServings() > 0) {
				recipes1 = recipes.stream().filter(x -> x.getServings() == recipe.getServings())
						.collect(Collectors.toList());
				recipes = recipes1;
			}

			if (recipe.getExcludeIngredients() != null) {
				LOGGER.info("Here excludeIngredients");
				recipes1 = recipes.stream().filter(
						x -> !(x.getIngredients().toLowerCase().contains(recipe.getExcludeIngredients().toLowerCase())))
						.collect(Collectors.toList());
				recipes = recipes1;
			}

			if (recipe.getInstructions() != null) {
				LOGGER.info("Here instructions");
				recipes1 = recipes.stream()
						.filter(x -> x.getInstructions().toLowerCase().contains(recipe.getInstructions().toLowerCase()))
						.collect(Collectors.toList());
				recipes = recipes1;
			}

			if (recipe.getVeg() > 0) {
				LOGGER.info("Here  in Vegggg");
				recipes1 = recipes.stream().filter(x -> x.getVeg() == recipe.getVeg()).collect(Collectors.toList());
				LOGGER.info("After filter");
			}

			if (recipes1 != null) {
				LOGGER.info("Recipes found for criteria" + recipes1.size());
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return recipes1;
	}

}
