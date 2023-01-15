package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.IOwnerRepository;
import guru.springframework.sfgpetclinic.repositories.IPetRepository;
import guru.springframework.sfgpetclinic.repositories.IPetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

	@Mock
	IOwnerRepository ownerRepository;

	@Mock
	IPetRepository petRepository;

	@Mock
	IPetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSpringDataJpaService ownerSpringDataJpaService;

	public static String LAST_NAME = "Smith";

	Owner owner;

	@BeforeEach
	void setUp() {
		owner = Owner.builder ( ).id ( 1L ).lastName ( LAST_NAME ).build ( );
	}

	@Test
	void findAll() {
		Set<Owner> ownerSet = new HashSet<> ( );
		ownerSet.add ( Owner.builder ( ).id ( 1L ).build ( ) );
		ownerSet.add ( Owner.builder ( ).id ( 2L ).build ( ) );
		when ( ownerRepository.findAll ( ) ).thenReturn ( ownerSet );
		Set<Owner> owners = ownerSpringDataJpaService.findAll ( );
		assertNotNull ( owners );
		assertEquals ( 2, owners.size ( ) );
	}

	@Test
	void findById() {
		when ( ownerRepository.findById ( anyLong ( ) ) ).thenReturn ( Optional.of ( owner ) );
		Owner owner1 = ownerSpringDataJpaService.findById ( 1L );
		assertNotNull ( owner1 );
	}

	@Test
	void findByIdNotFound() {
		when ( ownerRepository.findById ( anyLong ( ) ) ).thenReturn ( Optional.empty ( ) );
		Owner owner1 = ownerSpringDataJpaService.findById ( 1L );
		assertNull ( owner1 );
	}

	@Test
	void save() {
		Owner ownerToSave = Owner.builder ( ).id ( 1L ).build ( );
		when ( ownerRepository.save ( any ( ) ) ).thenReturn ( owner );
		Owner savedOwner = ownerSpringDataJpaService.save ( ownerToSave );
		assertNotNull ( savedOwner );
		// default is 1 times
		verify ( ownerRepository, times ( 1 ) ).save ( any ( ) );
	}

	@Test
	void delete() {
		ownerSpringDataJpaService.delete ( owner );
		verify ( ownerRepository ).delete ( any ( ) );
	}

	@Test
	void deleteById() {
		ownerSpringDataJpaService.deleteById ( 1L );
		verify ( ownerRepository ).deleteById ( anyLong ( ) );
	}

	@Test
	void findByLastName() {
		Owner returnOwner = Owner.builder ( ).id ( 1L ).lastName ( LAST_NAME ).build ( );
		when ( ownerSpringDataJpaService.findByLastName ( any ( ) ) ).thenReturn ( returnOwner );
		Owner smith = ownerSpringDataJpaService.findByLastName ( LAST_NAME );
		assertEquals ( LAST_NAME, smith.getLastName ( ) );
		verify ( ownerRepository ).findByLastName ( any ( ) );
	}
}
