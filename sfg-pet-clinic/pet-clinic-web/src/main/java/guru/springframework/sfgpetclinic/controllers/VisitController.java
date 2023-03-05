package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.IPetService;
import guru.springframework.sfgpetclinic.services.IVisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class VisitController {

	private final IVisitService visitService;

	private final IPetService petService;

	public VisitController(IVisitService visitService, IPetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields ( "id" );
	}

	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {
		Pet pet = this.petService.findById ( petId );
		model.put ( "pet", pet );
		Visit visit = new Visit ( );
		pet.getVisits ( ).add ( visit );
		visit.setPet ( pet );
		return visit;
	}

	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(Visit visit, BindingResult result) {
		if (result.hasErrors ( )) {
			return "pets/createOrUpdateVisitForm";
		} else {
			visitService.save ( visit );
			return "redirect:/owners/{ownerId}";
		}
	}
}
