package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final IRecipeRepository recipeRepository;

	public RecipeServiceImpl(IRecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
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
}
