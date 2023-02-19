package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.IOwnerRepository;
import guru.springframework.sfgpetclinic.repositories.IPetRepository;
import guru.springframework.sfgpetclinic.repositories.IPetTypeRepository;
import guru.springframework.sfgpetclinic.services.IOwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements IOwnerService {

	private final IOwnerRepository ownerRepository;

	private final IPetRepository petRepository;

	private final IPetTypeRepository petTypeRepository;

	public OwnerSpringDataJpaService(IOwnerRepository ownerRepository, IPetRepository petRepository, IPetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<> ( );
		ownerRepository.findAll ( ).forEach ( owners::add );
		return owners;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Owner findById(Long id) {
		return ownerRepository.findById ( id ).orElse ( null );
	}

	/**
	 * @param object
	 * @return
	 */
	@Override
	public Owner save(Owner object) {
		return ownerRepository.save ( object );
	}

	/**
	 * @param owner
	 */
	@Override
	public void delete(Owner owner) {
		ownerRepository.delete ( owner );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById ( id );
	}

	/**
	 * @param lastName
	 * @return
	 */
	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName ( lastName );
	}

	/**
	 * @param lastName
	 * @return
	 */
	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return ownerRepository.findAllByLastNameLike ( lastName );
	}
}
