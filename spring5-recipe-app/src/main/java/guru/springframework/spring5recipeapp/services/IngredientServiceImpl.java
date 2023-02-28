package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IIngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;

	private final IRecipeRepository recipeRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IRecipeRepository recipeRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
	}

	/**
	 * @param recipeId
	 * @param ingredientId
	 * @return
	 */
	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById ( recipeId );
		if (!recipeOptional.isPresent ( )) {
			// TODO: Implement error handling
			System.out.println ( "Recipe with id " + recipeId + " not found: " );
		}
		Recipe recipe = recipeOptional.get ( );
		Optional<IngredientCommand> ingredientCommandOptional =
				recipe.getIngredients ( ).stream ( )
						.filter ( ingredient -> ingredient.getId ( ).equals ( ingredientId ) )
						.map ( ingredientToIngredientCommand::convert )
						.findFirst ( );
		if (!ingredientCommandOptional.isPresent ( )) {
			// TODO: Implement error handling
			System.out.println ( "Ingredient with id " + ingredientId + " not found" );
		}
		return ingredientCommandOptional.get ( );
	}
}
