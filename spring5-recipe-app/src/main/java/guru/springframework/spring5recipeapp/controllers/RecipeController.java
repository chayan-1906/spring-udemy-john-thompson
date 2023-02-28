package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.IRecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

	private final IRecipeService IRecipeService;

	public RecipeController(IRecipeService IRecipeService) {
		this.IRecipeService = IRecipeService;
	}

	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute ( "recipe", IRecipeService.findById ( Long.valueOf ( id ) ) );
		return "recipe/show";
	}

	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute ( "recipe", new RecipeCommand ( ) );
		return "recipe/recipeform";
	}

	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute ( "recipe", IRecipeService.findCommandById ( Long.valueOf ( id ) ) );
		return "recipe/recipeform";
	}

	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = IRecipeService.saveRecipeCommand ( command );
		return "redirect:/recipe/" + savedCommand.getId ( ) + "/show";
	}


	@GetMapping
	@RequestMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		System.out.println ( "Deleting id: " + id );
		IRecipeService.deleteById ( Long.valueOf ( id ) );
		return "redirect:/";
	}
}
