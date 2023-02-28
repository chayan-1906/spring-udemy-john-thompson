package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.services.IIngredientService;
import guru.springframework.spring5recipeapp.services.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

	private final IRecipeService IRecipeService;

	private final IIngredientService ingredientService;

	public IngredientController(IRecipeService recipeService, IIngredientService ingredientService) {
		this.IRecipeService = recipeService;
		this.ingredientService = ingredientService;
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		System.out.println ( "Getting ingredient list for recipe id: " + recipeId );

		// use command object to avoid lazy load errors in Thymeleaf.
		model.addAttribute ( "recipe", IRecipeService.findCommandById ( Long.valueOf ( recipeId ) ) );

		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		model.addAttribute ( "ingredient",
				ingredientService.findByRecipeIdAndIngredientId ( Long.valueOf ( recipeId ), Long.valueOf ( id ) ) );
		return "recipe/ingredient/show";
	}
}
