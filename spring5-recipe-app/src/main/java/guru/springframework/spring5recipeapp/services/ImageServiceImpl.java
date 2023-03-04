package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.IRecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements IImageService {

	private final IRecipeRepository recipeRepository;

	public ImageServiceImpl(IRecipeRepository recipeService) {
		this.recipeRepository = recipeService;
	}

	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		try {
			Recipe recipe = recipeRepository.findById ( recipeId ).get ( );
			Byte[] byteObjects = new Byte[ file.getBytes ( ).length ];
			int i = 0;
			for (byte b : file.getBytes ( )) byteObjects[ i++ ] = b;
			recipe.setImage ( byteObjects );
			recipeRepository.save ( recipe );
		} catch (IOException e) {
			/// TODO: Handle Better
			log.error ( "Error occurred", e );
			e.printStackTrace ( );
		}
	}
}
