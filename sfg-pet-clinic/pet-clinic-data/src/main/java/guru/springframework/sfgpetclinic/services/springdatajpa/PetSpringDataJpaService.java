package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.IPetRepository;
import guru.springframework.sfgpetclinic.services.IPetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSpringDataJpaService implements IPetService {

	private final IPetRepository petRepository;

	public PetSpringDataJpaService(IPetRepository petRepository) {
		this.petRepository = petRepository;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<> ( );
		petRepository.findAll ( ).forEach ( pets::add );
		return pets;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Pet findById(Long id) {
		return petRepository.findById ( id ).orElse ( null );
	}

	/**
	 * @param pet
	 * @return
	 */
	@Override
	public Pet save(Pet pet) {
		return petRepository.save ( pet );
	}

	/**
	 * @param pet
	 */
	@Override
	public void delete(Pet pet) {
		petRepository.delete ( pet );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		petRepository.deleteById ( id );
	}
}
