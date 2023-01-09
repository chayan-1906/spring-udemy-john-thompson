package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.ISpecialityService;
import guru.springframework.sfgpetclinic.services.IVetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements IVetService {

	private final ISpecialityService specialityService;

	public VetMapService(ISpecialityService specialityService) {
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
