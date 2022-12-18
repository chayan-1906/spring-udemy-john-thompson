package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialityService specialityService;

	public VetServiceMap(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}

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
		if (vet.getSpecialities ( ).size ( ) > 0) {
			vet.getSpecialities ( ).forEach ( speciality -> {
				if (speciality.getId ( ) == null) {
					Speciality savedSpeciality = specialityService.save ( speciality );
					speciality.setId ( savedSpeciality.getId ( ) );
				}
			} );
		}
		return super.save ( vet );
	}
}
