package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.IPetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements IPetService {

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
		return super.save ( pet );
	}
}
