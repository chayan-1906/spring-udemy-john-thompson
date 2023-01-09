
package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

	private final IOwnerService ownerService;

	private final IVetService vetService;

	private final IPetTypeService petTypeService;

	private final ISpecialityService specialityService;

	private final IVisitService visitService;

	@Autowired
	public DataLoader(IOwnerService ownerService, IVetService vetService, IPetTypeService petTypeService, ISpecialityService specialityService, IVisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 */
	@Override
	public void run(String... args) {

		int count = petTypeService.findAll ( ).size ( );
		if (count == 0) {
			loadData ( );
		}
	}

	private void loadData() {
		PetType dog = new PetType ( );
		dog.setName ( "Dog" );
		PetType savedDogPetType = petTypeService.save ( dog );

		PetType cat = new PetType ( );
		cat.setName ( "Cat" );
		PetType savedCatPetType = petTypeService.save ( cat );

		Speciality radiology = new Speciality ( );
		radiology.setDescription ( "Radiology" );
		Speciality savedRadiology = specialityService.save ( radiology );

		Speciality surgery = new Speciality ( );
		surgery.setDescription ( "Surgery" );
		Speciality savedSurgery = specialityService.save ( surgery );

		Speciality dentistry = new Speciality ( );
		dentistry.setDescription ( "Dentistry" );
		Speciality savedDentistry = specialityService.save ( dentistry );

		Owner owner1 = new Owner ( );
//		owner1.setId ( 1L );
		owner1.setFirstName ( "Padmanabha" );
		owner1.setLastName ( "Das" );
		owner1.setAddress ( "Omrahagunj" );
		owner1.setCity ( "Lalbagh" );
		owner1.setTelephone ( "+91-9647100133" );

		Pet padmanabhasPet = new Pet ( );
		padmanabhasPet.setPetType ( savedDogPetType );
		padmanabhasPet.setOwner ( owner1 );
		padmanabhasPet.setBirthDate ( LocalDate.now ( ) );
		padmanabhasPet.setName ( "Rosco" );
		owner1.getPets ( ).add ( padmanabhasPet );
		ownerService.save ( owner1 );

		Owner owner2 = new Owner ( );
//		owner2.setId ( 2L );
		owner2.setFirstName ( "Prosenjit" );
		owner2.setLastName ( "Das" );
		owner2.setAddress ( "Omrahaganj" );
		owner2.setCity ( "Lalbagh, MSD, WB" );
		owner2.setTelephone ( "+91-9832740782" );

		Pet prosenjitsPet = new Pet ( );
		prosenjitsPet.setName ( "Just Cat" );
		prosenjitsPet.setOwner ( owner2 );
		prosenjitsPet.setBirthDate ( LocalDate.now ( ) );
		prosenjitsPet.setPetType ( savedCatPetType );
		owner2.getPets ( ).add ( prosenjitsPet );
		ownerService.save ( owner2 );

		Visit catVisit = new Visit ( );
		catVisit.setPet ( prosenjitsPet );
		catVisit.setDate ( LocalDate.now ( ) );
		catVisit.setDescription ( "Sneezy Kitty" );
		visitService.save ( catVisit );

		System.out.println ( "Loaded owners..." );

		Vet vet1 = new Vet ( );
		vet1.setId ( 1L );
		vet1.setFirstName ( "Sam" );
		vet1.setLastName ( "Axe" );
		vet1.getSpecialities ( ).add ( savedRadiology );
		vetService.save ( vet1 );

		Vet vet2 = new Vet ( );
		vet2.setId ( 2L );
		vet2.setFirstName ( "Jessie" );
		vet2.setLastName ( "Porter" );
		vet2.getSpecialities ( ).add ( savedSurgery );
		vetService.save ( vet2 );

		System.out.println ( "Loaded vets..." );
	}
}
