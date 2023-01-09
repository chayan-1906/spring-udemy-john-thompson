package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.IPetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements IPetTypeService {
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
