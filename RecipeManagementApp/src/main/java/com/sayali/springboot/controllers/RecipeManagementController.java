package com.sayali.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sayali.springboot.models.RecipeSearch;
import com.sayali.springboot.models.RecipeSearchResult;
import com.sayali.springboot.common.Messages;
import com.sayali.springboot.common.ValidateInput;
import com.sayali.springboot.models.AthenticationResponse;
import com.sayali.springboot.models.AuthenticationRequest;
import com.sayali.springboot.models.Recipe;
import com.sayali.springboot.models.RecipeResult;
import com.sayali.springboot.models.RecipeActionResult;
import com.sayali.springboot.repos.RecipeRepository;
import com.sayali.springboot.services.MyUserDetailsService;
import com.sayali.springboot.services.RecipeService;
import com.sayali.springboot.util.RecipeManagementUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
//@RequestMapping("/api")
@Validated
public class RecipeManagementController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private RecipeManagementUtil recipeManagementUtil;

	@Autowired
	RecipeRepository repo;

	@Autowired
	private RecipeService recipeService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeManagementController.class);

	public RecipeManagementController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
	
	@RequestMapping(value = "/recipes/", method = RequestMethod.GET)
	public RecipeResult getRecipes() {

		LOGGER.info("In controller");

		List<Recipe> recipes = null;

		RecipeResult recipeResult = new RecipeResult(1, null);

		try {

			recipes = recipeService.getRecipes();

			recipeResult = new RecipeResult(200, Messages.SUCCESSFUL.getMessage(), recipes);

		} catch (Exception e) {

			System.out.println("Here in catch " + e.getMessage());
			recipeResult = new RecipeResult(1, Messages.UNSUCCESSFUL.getMessage(), null);

		}

		return recipeResult;
	}

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World!";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getUserpassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);

		}

		ValidateInput validateInput = new ValidateInput();

		boolean isValid = validateInput.validateAuthenticationRequest(authenticationRequest);

		if (isValid) {

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String jwtToken = recipeManagementUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AthenticationResponse(jwtToken));
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
	@SecurityRequirement(name = "bearerAuth")
	public RecipeActionResult getRecipe(@Valid @PathVariable("id") int id) {
		LOGGER.info("Finding recipe by id : " + id);

		Recipe recipe = null;

		
		RecipeActionResult recipeResult = new RecipeActionResult(1, null);

		try {

			System.out.println("In try");

			recipe = recipeService.getRecipe(id).orElse(null);

			if (recipe != null) {

				LOGGER.info("Here id is " + recipe.getId());

				recipeResult = new RecipeActionResult(HttpStatus.OK.value(), Messages.SUCCESSFUL.getMessage(), recipe);

			} else {
				LOGGER.info("ID not found");
			}

		} catch (Exception e) {

			LOGGER.error("In catch" + e.getMessage());
			recipeResult = new RecipeActionResult(1, Messages.UNSUCCESSFUL.getMessage(), recipe);
		}

		return recipeResult;
	}

	@RequestMapping(value = "/recipes/", method = RequestMethod.POST)
	public RecipeActionResult createRecipe(@Valid @RequestBody Recipe recipe) {

		Recipe createdRecipe = new Recipe();

		RecipeActionResult recipeResult = new RecipeActionResult(1, null);

		try {

			createdRecipe = recipeService.createRecipe(recipe);
			recipeResult = new RecipeActionResult(HttpStatus.OK.value(), Messages.SUCCESSFUL.getMessage(),
					createdRecipe);

		} catch (Exception e) {

			LOGGER.error("In Catch" + e.getMessage());
			recipeResult = new RecipeActionResult(1, Messages.UNSUCCESSFUL.getMessage(), createdRecipe);

		}

		return recipeResult;
	}

	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
	public RecipeActionResult updateRecipe(@Valid @RequestBody Recipe recipe) {

		Recipe updateRecipe = null;

		RecipeActionResult recipeResult = new RecipeActionResult(1, null);

		try {

			updateRecipe = recipeService.updateRecipe(recipe).orElse(null);

			recipeResult = new RecipeActionResult(HttpStatus.OK.value(), Messages.SUCCESSFUL.getMessage(),
					updateRecipe);

		} catch (Exception e) {
			LOGGER.error("In catch");
			recipeResult = new RecipeActionResult(1, Messages.UNSUCCESSFUL.getMessage(), updateRecipe);

		}

		return recipeResult;
	}

	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
	public RecipeActionResult deleteRecipe(@Valid @PathVariable("id") String id) {
		// repo.deleteById(Integer.valueOf(id));

		String deleteMessage = null;
		RecipeActionResult recipeResult = new RecipeActionResult(1, null);
		try {
			
			deleteMessage =	recipeService.deleteRecipe(Integer.valueOf(id));
			
			recipeResult = new RecipeActionResult(HttpStatus.OK.value(), Messages.SUCCESSFUL.getMessage());
			
		} catch (Exception e) {
			LOGGER.error("In catch " + e.getMessage());
			recipeResult = new RecipeActionResult(HttpStatus.OK.value(), Messages.UNSUCCESSFUL.getMessage());
		}

		return recipeResult;
	}

	@RequestMapping(value = "/recipes/search", method = RequestMethod.GET)
	public ResponseEntity<RecipeSearchResult> searchRecipe(@Valid @RequestBody RecipeSearch recipe) {

		LOGGER.info("Here in search");
		ResponseEntity<RecipeSearchResult> searchResult = new ResponseEntity<RecipeSearchResult>(
				new RecipeSearchResult(1, null, null), HttpStatus.OK);

		try {
			List<Recipe> recipes = recipeService.searchRecipe(recipe);
			searchResult = new ResponseEntity<RecipeSearchResult>(
					new RecipeSearchResult(HttpStatus.OK.value(), Messages.SUCCESSFUL.getMessage(), recipes),
					HttpStatus.OK);

		} catch (Exception ex) {
			searchResult = new ResponseEntity<RecipeSearchResult>(
					new RecipeSearchResult(1, Messages.UNSUCCESSFUL.getMessage(), null), HttpStatus.OK);

		}

		return searchResult;

	}

}
