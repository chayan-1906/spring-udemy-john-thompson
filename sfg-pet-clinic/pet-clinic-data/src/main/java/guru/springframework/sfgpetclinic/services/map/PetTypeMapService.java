package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
	/**
	 * @return
	 */
	@Override
	public Set<PetType> findAll() {
		return super.findAll ( );
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public PetType findById(Long id) {
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
	 * @param object
	 */
	@Override
	public void delete(PetType object) {
		super.delete ( object );
	}

	/**
	 * @param object
	 * @return
	 */
	@Override
	public PetType save(PetType object) {
		return super.save ( object );
	}
}
