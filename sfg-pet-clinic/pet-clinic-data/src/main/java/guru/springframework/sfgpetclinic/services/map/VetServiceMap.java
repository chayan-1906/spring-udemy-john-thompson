package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
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
		return super.save ( vet );
	}
}
