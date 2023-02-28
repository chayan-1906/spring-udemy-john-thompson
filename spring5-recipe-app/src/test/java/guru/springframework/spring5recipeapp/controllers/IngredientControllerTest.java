package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.IIngredientService;
import guru.springframework.spring5recipeapp.services.IRecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

	@Mock
	IRecipeService IRecipeService;

	@Mock
	IIngredientService ingredientService;

	IngredientController ingredientController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks ( this );
		ingredientController = new IngredientController ( IRecipeService, ingredientService );
		mockMvc = MockMvcBuilders.standaloneSetup ( ingredientController ).build ( );
	}

	@Test
	public void testListIngredients() throws Exception {
		/// given
		RecipeCommand command = new RecipeCommand ( );
		when ( IRecipeService.findCommandById ( anyLong ( ) ) ).thenReturn ( command );

		/// when
		mockMvc.perform ( get ( "/recipe/1/ingredients" ) )
				.andExpect ( status ( ).isOk ( ) )
				.andExpect ( view ( ).name ( "recipe/ingredient/list" ) )
				.andExpect ( model ( ).attributeExists ( "recipe" ) );

		/// then
		verify ( IRecipeService, times ( 1 ) ).findCommandById ( anyLong ( ) );
	}
}
