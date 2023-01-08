package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.IPetTypeRepository;
import guru.springframework.sfgpetclinic.services.IPetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSpringDataJpaService implements IPetTypeService {

	private final IPetTypeRepository petTypeRepository;

	public PetTypeSpringDataJpaService(IPetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	/**
	 * @return
	 */
	@Override
	public Set<PetType> findAll() {
		Set<PetType> petTypes = new HashSet<> ( );
		petTypeRepository.findAll ( ).forEach ( petTypes::add );
		return petTypes;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public PetType findById(Long id) {
		return petTypeRepository.findById ( id ).orElse ( null );
	}

	/**
	 * @param petType
	 * @return
	 */
	@Override
	public PetType save(PetType petType) {
		return petTypeRepository.save ( petType );
	}

	/**
	 * @param petType
	 */
	@Override
	public void delete(PetType petType) {
		petTypeRepository.delete ( petType );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById ( id );
	}
}
