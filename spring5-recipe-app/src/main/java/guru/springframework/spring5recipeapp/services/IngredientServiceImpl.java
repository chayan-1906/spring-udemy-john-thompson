package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import guru.springframework.spring5recipeapp.repositories.IUnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IIngredientService {
	private final IUnitOfMeasureRepository unitOfMeasureRepository;

	private final IngredientToIngredientCommand ingredientToIngredientCommand;

	private final IRecipeRepository recipeRepository;

	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IRecipeRepository recipeRepository, IngredientCommandToIngredient ingredientCommandToIngredient,
	                             IUnitOfMeasureRepository unitOfMeasureRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
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

	/**
	 * @param command
	 * @return
	 */
	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById ( command.getRecipeId ( ) );
		if (!recipeOptional.isPresent ( )) {
			// TODO: toss error if not found!
			log.error ( "Recipe not found for id: " + command.getRecipeId ( ) );
			return new IngredientCommand ( );
		} else {
			Recipe recipe = recipeOptional.get ( );
			Optional<Ingredient> ingredientOptional = recipe
					.getIngredients ( )
					.stream ( )
					.filter ( ingredient -> ingredient.getId ( ).equals ( command.getId ( ) ) )
					.findFirst ( );
			if (ingredientOptional.isPresent ( )) {
				Ingredient ingredientFound = ingredientOptional.get ( );
				ingredientFound.setDescription ( command.getDescription ( ) );
				ingredientFound.setAmount ( command.getAmount ( ) );
				ingredientFound.setUnitOfMeasure ( unitOfMeasureRepository
						.findById ( command.getUnitOfMeasure ( ).getId ( ) )
						.orElseThrow ( () -> new RuntimeException ( "UOM NOT FOUND" ) ) ); // TODO: address this
			} else {
				// add new Ingredient
				Ingredient ingredient = ingredientCommandToIngredient.convert ( command );
				ingredient.setRecipe ( recipe );
				recipe.addIngredient ( ingredient );
			}

			Recipe savedRecipe = recipeRepository.save ( recipe );

			Optional<Ingredient> savedIngredientOptional =
					savedRecipe.getIngredients ( ).stream ( )
							.filter ( recipeIngredients -> recipeIngredients.getId ( ).equals ( command.getId ( ) ) )
							.findFirst ( );

			// check by description
			if (!savedIngredientOptional.isPresent ( )) {
				savedIngredientOptional = savedRecipe.getIngredients ( ).stream ( )
						.filter ( recipeIngredients -> recipeIngredients.getDescription ( ).equals ( command.getDescription ( ) ) )
						.filter ( recipeIngredients -> recipeIngredients.getAmount ( ).equals ( command.getAmount ( ) ) )
						.filter ( recipeIngredients -> recipeIngredients.getUnitOfMeasure ( ).getId ( ).equals ( command.getUnitOfMeasure ( ).getId ( ) ) )
						.findFirst ( );
			}

			// TODO: Check for fail
			return ingredientToIngredientCommand.convert ( savedIngredientOptional.get ( ) );
		}
	}

	/**
	 * @param recipeId
	 * @param idToDelete
	 */
	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		log.debug ( "Deleting ingredient: " + recipeId + ":" + idToDelete );
		Optional<Recipe> recipeOptional = recipeRepository.findById ( recipeId );
		if (recipeOptional.isPresent ( )) {
			Recipe recipe = recipeOptional.get ( );
			log.debug ( "found recipe" );
			Optional<Ingredient> ingredientOptional = recipe
					.getIngredients ( )
					.stream ( )
					.filter ( ingredient -> ingredient.getId ( ).equals ( idToDelete ) )
					.findFirst ( );
			if (ingredientOptional.isPresent ( )) {
				log.debug ( "found Ingredient" );
				Ingredient ingredientToDelete = ingredientOptional.get ( );
				ingredientToDelete.setRecipe ( null );
				recipe.getIngredients ( ).remove ( ingredientOptional.get ( ) );
				recipeRepository.save ( recipe );
			}
		} else {
			log.debug ( "Recipe Id Not found. Id:" + recipeId );
		}
	}
}
