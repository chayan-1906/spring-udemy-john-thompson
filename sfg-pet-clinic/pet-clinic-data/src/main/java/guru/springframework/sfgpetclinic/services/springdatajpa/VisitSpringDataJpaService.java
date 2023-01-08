package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.IVisitRepository;
import guru.springframework.sfgpetclinic.services.IVisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSpringDataJpaService implements IVisitService {

	private final IVisitRepository visitRepository;

	public VisitSpringDataJpaService(IVisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	/**
	 * @return
	 */
	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<> ( );
		visitRepository.findAll ( ).forEach ( visits::add );
		return visits;
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Visit findById(Long id) {
		return visitRepository.findById ( id ).orElse ( null );
	}

	/**
	 * @param visit
	 * @return
	 */
	@Override
	public Visit save(Visit visit) {
		return visitRepository.save ( visit );
	}

	/**
	 * @param visit
	 */
	@Override
	public void delete(Visit visit) {
		visitRepository.delete ( visit );
	}

	/**
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById ( id );
	}
}
