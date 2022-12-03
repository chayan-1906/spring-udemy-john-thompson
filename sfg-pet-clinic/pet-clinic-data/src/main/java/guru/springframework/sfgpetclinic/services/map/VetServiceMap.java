package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
	/**
	 * @return
	 */
	@Override
	public Set<Vet> findAll() {
		return super.findAll ( );
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Vet findById(Long id) {
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
	 * @param vet
	 */
	@Override
	public void delete(Vet vet) {
		super.delete ( vet );
	}

	/**
	 * @param vet
	 * @return
	 */
	@Override
	public Vet save(Vet vet) {
		return super.save ( vet.getId ( ), vet );
	}
}
