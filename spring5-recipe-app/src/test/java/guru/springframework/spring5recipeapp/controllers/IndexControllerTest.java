package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.IRecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

	@Mock
	IRecipeService IRecipeService;

	@Mock
	Model model;

	IndexController indexController;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks ( this );
		indexController = new IndexController ( IRecipeService );
	}

	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup ( indexController ).build ( );
		mockMvc.perform ( MockMvcRequestBuilders.get ( "/" ) )
				.andExpect ( MockMvcResultMatchers.status ( ).isOk ( ) )
				.andExpect ( MockMvcResultMatchers.view ( )
						.name ( "index" ) );
	}

	@Test
	public void getIndexPage() throws Exception {

		// given
		Set<Recipe> recipes = new HashSet<> ( );
		recipes.add ( new Recipe ( ) );

		Recipe recipe = new Recipe ( );
		recipe.setId ( 1L );
		recipes.add ( recipe );

		when ( IRecipeService.getRecipes ( ) ).thenReturn ( recipes );

		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass ( Set.class );

		// when
		String viewName = indexController.getIndexPage ( model );

		// then
		assertEquals ( "index", viewName );
		verify ( IRecipeService, times ( 1 ) ).getRecipes ( );
		verify ( model, times ( 1 ) ).addAttribute ( eq ( "recipes" ), argumentCaptor.capture ( ) );
		Set<Recipe> setInController = argumentCaptor.getValue ( );
		assertEquals ( 2, setInController.size ( ) );
	}
}
