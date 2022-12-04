package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.CrudService;
import guru.springframework.sfgpetclinic.services.OwnerService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
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
		return super.save ( owner.getId ( ), owner );
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
