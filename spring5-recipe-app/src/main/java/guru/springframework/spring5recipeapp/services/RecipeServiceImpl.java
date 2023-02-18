package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final IRecipeRepository recipeRepository;

	private final RecipeCommandToRecipe recipeCommandToRecipe;

	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(IRecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
	                         RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Recipe> getRecipes() {
		log.debug ( "I'm in the service" );
		Set<Recipe> recipeSet = new HashSet<> ( );
		recipeRepository.findAll ( ).iterator ( ).forEachRemaining ( recipeSet::add );
		return recipeSet;
	}

	@Override
	public Recipe findById(Long l) {
		Optional<Recipe> recipeOptional = recipeRepository.findById ( l );
		if (!recipeOptional.isPresent ( )) {
			throw new RuntimeException ( "Recipe Not Found!" );
		}
		return recipeOptional.get ( );
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert ( command );
		Recipe savedRecipe = recipeRepository.save ( detachedRecipe );
		log.debug ( "Saved RecipeId:" + savedRecipe.getId ( ) );
		return recipeToRecipeCommand.convert ( savedRecipe );
	}
}
