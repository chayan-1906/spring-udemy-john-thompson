package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	IRecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks ( this );
		recipeService = new RecipeServiceImpl ( recipeRepository );
	}

	@Test
	public void getRecipes() throws Exception {
		Recipe recipe = new Recipe ( );
		HashSet recipesData = new HashSet ( );
		recipesData.add ( recipe );
		when ( recipeRepository.findAll ( ) ).thenReturn ( recipesData );

		Set<Recipe> recipes = recipeService.getRecipes ( );
		assertEquals ( recipes.size ( ), 1 );

		verify ( recipeRepository, times ( 1 ) ).findAll ( );
	}
}
