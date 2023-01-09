package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.IOwnerService;
import guru.springframework.sfgpetclinic.services.IPetService;
import guru.springframework.sfgpetclinic.services.IPetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements IOwnerService {

	private final IPetTypeService petTypeService;

	private final IPetService petService;

	public OwnerMapService(IPetTypeService petTypeService, IPetService petService) {
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Owner> findAll() {
		return super.findAll ( );
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Owner findById(Long id) {
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
	 * @param owner
	 */
	@Override
	public void delete(Owner owner) {
		super.delete ( owner );
	}

	/**
	 * @param owner
	 * @return
	 */
	@Override
	public Owner save(Owner owner) {
		if (owner != null) {
			if (owner.getPets ( ) != null) {
				owner.getPets ( ).forEach ( pet -> {
					if (pet.getPetType ( ) != null) {
						if (pet.getPetType ( ).getId ( ) == null) {
							pet.setPetType ( petTypeService.save ( pet.getPetType ( ) ) );
						}
					} else {
						throw new RuntimeException ( "Pet Type is required" );
					}

					if (pet.getId ( ) == null) {
						Pet savedPet = petService.save ( pet );
						pet.setId ( savedPet.getId ( ) );
					}
				} );
			}
			return super.save ( owner );
		} else {
			return null;
		}
	}

	/**
	 * @param lastName
	 * @return
	 */
	@Override
	public Owner findByLastName(String lastName) {
		return null;
	}
}
