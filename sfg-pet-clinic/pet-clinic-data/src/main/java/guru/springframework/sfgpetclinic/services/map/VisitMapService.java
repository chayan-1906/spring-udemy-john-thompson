package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.IVisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements IVisitService {

	/**
	 * @return
	 */
	@Override
	public Set<Visit> findAll() {
		return super.findAll ( );
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Visit findById(Long id) {
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
	 * @param visit
	 */
	@Override
	public void delete(Visit visit) {
		super.delete ( visit );
	}

	/**
	 * @param visit
	 * @return
	 */
	@Override
	public Visit save(Visit visit) {
		if (visit.getPet ( ) == null || visit.getPet ( ).getOwner ( ) == null || visit.getPet ( ).getId ( ) == null || visit.getPet ( ).getOwner ( ) == null) {
			throw new RuntimeException ( "Invalid visit" );
		}
		return super.save ( visit );
	}
}
