package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;

public interface IIngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteById(Long recipeId, Long idToDelete);
}
