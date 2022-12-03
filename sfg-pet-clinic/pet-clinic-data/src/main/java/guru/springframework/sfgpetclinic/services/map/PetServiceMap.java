package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
	/**
	 * @return
	 */
	@Override
	public Set<Pet> findAll() {
		return super.findAll ( );
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Pet findById(Long id) {
		return super.findById ( id );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		super.deleteById ( id );
	}

	/**
	 * @param pet
	 */
	@Override
	public void delete(Pet pet) {
		super.delete ( pet );
	}

	/**
	 * @param pet
	 * @return
	 */
	@Override
	public Pet save(Pet pet) {
		return super.save ( pet.getId ( ), pet );
	}
}
