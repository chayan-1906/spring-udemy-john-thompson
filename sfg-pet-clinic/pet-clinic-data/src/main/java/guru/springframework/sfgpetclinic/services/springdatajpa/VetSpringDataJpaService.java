package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.IVetRepository;
import guru.springframework.sfgpetclinic.services.IVetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSpringDataJpaService implements IVetService {

	private final IVetRepository vetRepository;

	public VetSpringDataJpaService(IVetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<> ( );
		vetRepository.findAll ( ).forEach ( vets::add );
		return vets;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Vet findById(Long id) {
		;
		return vetRepository.findById ( id ).orElse ( null );
	}

	/**
	 * @param vet
	 * @return
	 */
	@Override
	public Vet save(Vet vet) {
		return vetRepository.save ( vet );
	}

	/**
	 * @param vet
	 */
	@Override
	public void delete(Vet vet) {
		vetRepository.delete ( vet );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		vetRepository.deleteById ( id );
	}
}
