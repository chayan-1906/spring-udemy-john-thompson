package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

	@Mock
	PetMapService petMapService;

	@Mock
	OwnerMapService ownerMapService;

	@Mock
	PetTypeMapService petTypeMapService;

	@InjectMocks
	PetController petController;

	MockMvc mockMvc;

	Owner owner;

	Set<PetType> petTypes;

	@BeforeEach
	void setUp() {
		owner = Owner.builder ( ).id ( 1L ).build ( );

		petTypes = new HashSet<> ( );
		petTypes.add ( PetType.builder ( ).id ( 1L ).name ( "Dog" ).build ( ) );
		petTypes.add ( PetType.builder ( ).id ( 2L ).name ( "Cat" ).build ( ) );

		mockMvc = MockMvcBuilders.standaloneSetup ( petController ).build ( );
	}

	@Test
	void initCreationForm() throws Exception {
		when ( ownerMapService.findById ( anyLong ( ) ) ).thenReturn ( owner );
		when ( petTypeMapService.findAll ( ) ).thenReturn ( petTypes );

		mockMvc.perform ( get ( "/owners/1/pets/new" ) ).andExpect ( status ( ).isOk ( ) ).andExpect ( model ( ).attributeExists ( "owner" ) ).andExpect ( model ( ).attributeExists ( "pet" ) ).andExpect ( view ( ).name ( "pets/createOrUpdatePetForm" ) );
	}

	@Test
	void processCreationForm() throws Exception {
		when ( ownerMapService.findById ( anyLong ( ) ) ).thenReturn ( owner );
		when ( petTypeMapService.findAll ( ) ).thenReturn ( petTypes );

		mockMvc.perform ( post ( "/owners/1/pets/new" ) ).andExpect ( status ( ).is3xxRedirection ( ) ).andExpect ( view ( ).name ( "redirect:/owners/1" ) );

		verify ( petMapService ).save ( any ( ) );
	}

	@Test
	void initUpdateForm() throws Exception {
		when ( ownerMapService.findById ( anyLong ( ) ) ).thenReturn ( owner );
		when ( petTypeMapService.findAll ( ) ).thenReturn ( petTypes );
		when ( petMapService.findById ( anyLong ( ) ) ).thenReturn ( Pet.builder ( ).id ( 2L ).build ( ) );

		mockMvc.perform ( get ( "/owners/1/pets/2/edit" ) ).andExpect ( status ( ).isOk ( ) ).andExpect ( model ( ).attributeExists ( "owner" ) ).andExpect ( model ( ).attributeExists ( "pet" ) ).andExpect ( view ( ).name ( "pets/createOrUpdatePetForm" ) );
	}

	@Test
	void processUpdateForm() throws Exception {
		when ( ownerMapService.findById ( anyLong ( ) ) ).thenReturn ( owner );
		when ( petTypeMapService.findAll ( ) ).thenReturn ( petTypes );

		mockMvc.perform ( post ( "/owners/1/pets/2/edit" ) ).andExpect ( status ( ).is3xxRedirection ( ) ).andExpect ( view ( ).name ( "redirect:/owners/1" ) );

		verify ( petMapService ).save ( any ( ) );
	}

	@Test
	void populatePetTypes() {
		// TODO: Implement
	}

	@Test
	void findOwner() {
		// TODO: Implement
	}

	@Test
	void initOwnerBinder() {
		// TODO: Implement
	}
}
