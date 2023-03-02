package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.IOwnerService;
import guru.springframework.sfgpetclinic.services.IPetService;
import guru.springframework.sfgpetclinic.services.IPetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final IPetService iPetService;

	private final IOwnerService iOwnerService;

	private final IPetTypeService iPetTypeService;

	public PetController(IPetService iPetService, IOwnerService iOwnerService, IPetTypeService iPetTypeService) {
		this.iPetService = iPetService;
		this.iOwnerService = iOwnerService;
		this.iPetTypeService = iPetTypeService;
	}

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return iPetTypeService.findAll ( );
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return iOwnerService.findById ( ownerId );
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields ( "id" );
	}

	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner, Model model) {
		System.out.println ( "initCreationForm" );
		Pet pet = new Pet ( );
		owner.getPets ( ).add ( pet );
		pet.setOwner ( owner );
		model.addAttribute ( "pet", pet );
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/new")
	public String processCreationForm(Owner owner, Pet pet, BindingResult bindingResult, ModelMap modelMap) {
		System.out.println ( "processCreationForm" + "owner : " + owner.getId ( ) + " pet : " + pet.getName ( ) );
		if (StringUtils.hasLength ( pet.getName ( ) ) && pet.isNew ( ) && owner.getPet ( pet.getName ( ), true ) != null) {
			bindingResult.rejectValue ( "name", "duplicate", "already exists" );
		}
		pet.setOwner ( iOwnerService.findById ( owner.getId ( ) ) );
		owner.getPets ( ).add ( pet );
		if (bindingResult.hasErrors ( )) {
			modelMap.put ( "pet", pet );
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		} else {
			iPetService.save ( pet );
			return "redirect:/owners/" + owner.getId ( );
		}
	}

	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
		model.addAttribute ( "pet", iPetService.findById ( petId ) );
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(Pet pet, BindingResult bindingResult, Owner owner, Model model) {
		if (bindingResult.hasErrors ( )) {
			pet.setOwner ( owner );
			model.addAttribute ( "pet", pet );
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		} else {
			owner.getPets ( ).add ( pet );
			iPetService.save ( pet );
			return "redirect:/owners/" + owner.getId ( );
		}
	}
}
