package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

	IRecipeServiceImpl recipeService;

	@Mock
	IRecipeRepository recipeRepository;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks ( this );
		recipeService = new IRecipeServiceImpl ( recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand );
	}

	@Test
	public void getRecipeByIdTest() throws Exception {
		Recipe recipe = new Recipe ( );
		recipe.setId ( 1L );
		Optional<Recipe> recipeOptional = Optional.of ( recipe );

		when ( recipeRepository.findById ( anyLong ( ) ) ).thenReturn ( recipeOptional );

		Recipe recipeReturned = recipeService.findById ( 1L );

		assertNotNull ( "Null recipe returned", recipeReturned );
		verify ( recipeRepository, times ( 1 ) ).findById ( anyLong ( ) );
		verify ( recipeRepository, never ( ) ).findAll ( );
	}

	@Test
	public void getRecipesTest() throws Exception {
		Recipe recipe = new Recipe ( );
		HashSet<Recipe> receipesData = new HashSet<> ( );
		receipesData.add ( recipe );

		when ( recipeService.getRecipes ( ) ).thenReturn ( receipesData );

		Set<Recipe> recipes = recipeService.getRecipes ( );

		assertEquals ( recipes.size ( ), 1 );
		verify ( recipeRepository, times ( 1 ) ).findAll ( );
		verify ( recipeRepository, never ( ) ).findById ( anyLong ( ) );
	}

	@Test
	public void testDeleteById() throws Exception {
		/// given
		Long idToDelete = 2L;

		/// when
		recipeService.deleteById ( idToDelete );

		/// no 'when', since method has coid return type

		/// then
		verify ( recipeRepository, times ( 1 ) ).deleteById ( anyLong ( ) );
	}
}
