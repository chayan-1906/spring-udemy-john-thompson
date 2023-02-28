package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.services.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

	private final IRecipeService IRecipeService;

	public IndexController(IRecipeService IRecipeService) {
		this.IRecipeService = IRecipeService;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		log.debug ( "Getting Index Page" );
		model.addAttribute ( "recipes", IRecipeService.getRecipes ( ) );
		return "index";
	}
}
