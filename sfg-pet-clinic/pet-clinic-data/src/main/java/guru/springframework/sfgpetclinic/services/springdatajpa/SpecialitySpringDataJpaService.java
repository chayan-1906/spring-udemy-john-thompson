package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.ISpecialityRepository;
import guru.springframework.sfgpetclinic.services.ISpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySpringDataJpaService implements ISpecialityService {

	private final ISpecialityRepository specialityRepository;

	public SpecialitySpringDataJpaService(ISpecialityRepository specialityRepository) {
		this.specialityRepository = specialityRepository;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Speciality> findAll() {
		Set<Speciality> specialities = new HashSet<> ( );
		specialityRepository.findAll ( ).forEach ( specialities::add );
		return specialities;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Speciality findById(Long id) {
		return specialityRepository.findById ( id ).orElse ( null );
	}

	/**
	 * @param speciality
	 * @return
	 */
	@Override
	public Speciality save(Speciality speciality) {
		return specialityRepository.save ( speciality );
	}

	/**
	 * @param speciality
	 */
	@Override
	public void delete(Speciality speciality) {
		specialityRepository.delete ( speciality );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		specialityRepository.deleteById ( id );
	}
}
