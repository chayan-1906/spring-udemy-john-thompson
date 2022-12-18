package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {
	/**
	 * @return
	 */
	@Override
	public Set<Speciality> findAll() {
		return super.findAll ( );
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public Speciality findById(Long id) {
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
	 * @param speciality
	 */
	@Override
	public void delete(Speciality speciality) {
		super.delete ( speciality );
	}

	/**
	 * @param speciality
	 * @return
	 */
	@Override
	public Speciality save(Speciality speciality) {
		return super.save ( speciality );
	}
}
