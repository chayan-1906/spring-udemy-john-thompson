package guru.springframework.pets;

import org.springframework.stereotype.Service;

@Service
public class PetServiceFactory {

	public PetService getPetService(String petType) {
		switch (petType) {
			case "dog":
				return new DogPetService ( );
			case "cat":
				return new CatPetService ( );
			default:
				return new DogPetService ( );
		}
	}
}
