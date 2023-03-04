package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImageServiceImplTest {

	@Mock
	IRecipeRepository recipeRepository;

	IImageService imageService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks ( this );

		imageService = new ImageServiceImpl ( recipeRepository );
	}

	@Test
	public void saveImageFile() throws Exception {
		/// given
		Long id = 1L;
		MultipartFile multipartFile = new MockMultipartFile ( "imagefile", "testing.txt", "text/plain", "Spring Framework Guru".getBytes ( ) );

		Recipe recipe = new Recipe ( );
		recipe.setId ( id );
		Optional<Recipe> recipeOptional = Optional.of ( recipe );

		Mockito.when ( recipeRepository.findById ( ArgumentMatchers.anyLong ( ) ) ).thenReturn ( recipeOptional );

		ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass ( Recipe.class );

		/// when
		imageService.saveImageFile ( id, multipartFile );

		/// then
		verify ( recipeRepository, times ( 1 ) ).save ( argumentCaptor.capture ( ) );
		Recipe savedRecipe = argumentCaptor.getValue ( );
		assertEquals ( multipartFile.getBytes ( ).length, savedRecipe.getImage ( ).length );
	}
}
