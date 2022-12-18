package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;

	private final VetService vetService;

	private final PetTypeService petTypeService;

	@Autowired
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {

		PetType dog = new PetType ( );
		dog.setName ( "Dog" );
		PetType savedDogPetType = petTypeService.save ( dog );

		PetType cat = new PetType ( );
		cat.setName ( "Cat" );
		PetType savedCatPetType = petTypeService.save ( cat );

		Owner owner1 = new Owner ( );
		owner1.setId ( 1L );
		owner1.setFirstName ( "Padmanabha" );
		owner1.setLastName ( "Das" );
		owner1.setAddress ( "Omrahagunj" );
		owner1.setCity ( "Lalbagh" );
		owner1.setTelephone ( "+91-9647100133" );

		Pet mikesPet = new Pet ( );
		mikesPet.setPetType ( savedDogPetType );
		mikesPet.setOwner ( owner1 );
		mikesPet.setBirthDate ( LocalDate.now ( ) );
		mikesPet.setName ( "Rosco" );
		owner1.getPets ( ).add ( mikesPet );

		ownerService.save ( owner1 );

		Owner owner2 = new Owner ( );
		owner2.setId ( 2L );
		owner2.setFirstName ( "Prosenjit" );
		owner2.setLastName ( "Das" );
		owner1.setAddress ( "Omrahaganj" );
		owner1.setCity ( "Lalbagh, MSD, WB" );
		owner1.setTelephone ( "+91-9832740782" );

		Pet fionasCat = new Pet ( );
		fionasCat.setPetType ( savedCatPetType );
		fionasCat.setOwner ( owner2 );
		fionasCat.setBirthDate ( LocalDate.now ( ) );
		fionasCat.setName ( "Just Cat" );
		owner2.getPets ( ).add ( fionasCat );

		ownerService.save ( owner2 );

		System.out.println ( "Loaded owners..." );

		Vet vet1 = new Vet ( );
		vet1.setId ( 1L );
		vet1.setFirstName ( "Sam" );
		vet1.setLastName ( "Axe" );
		vetService.save ( vet1 );

		Vet vet2 = new Vet ( );
		vet2.setId ( 2L );
		vet2.setFirstName ( "Jessie" );
		vet2.setLastName ( "Porter" );
		vetService.save ( vet2 );

		System.out.println ( "Loaded vets..." );
	}
}
