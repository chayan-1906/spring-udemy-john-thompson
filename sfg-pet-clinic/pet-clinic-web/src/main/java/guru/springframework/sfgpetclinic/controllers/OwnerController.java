package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.IOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("owners")
@Controller
public class OwnerController {

	private final IOwnerService ownerService;

	public OwnerController(IOwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields ( "id" );
	}

	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute ( "owner", Owner.builder ( ).build ( ) );
		return "owners/findOwners";
	}

	@GetMapping
	public String processFindForm(Owner owner, BindingResult result, Model model) {
		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName ( ) == null) {
			owner.setLastName ( "" ); // empty string signifies broadest possible search
		}

		// find owners by last name
		List<Owner> ownersResults = ownerService.findAllByLastNameLike ( owner.getLastName ( ) );
		System.out.println ( "ownersResults = " + ownersResults );
		for (Owner ownersResult : ownersResults) {
			System.out.println ( ownersResult.getFirstName ( ) );
		}
		if (ownersResults.isEmpty ( )) {
			// no owners found
			result.rejectValue ( "lastName", "notFound", "not found" );
			return "owners/findOwners";
		} else if (ownersResults.size ( ) == 1) {
			// only one owner found
			owner = ownersResults.get ( 0 );
			return "redirect:/owners/" + owner.getId ( );
		} else {
			// multiple owners found
			model.addAttribute ( "selections", ownersResults );
			return "owners/ownersList";
		}
	}

	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView modelAndView = new ModelAndView ( "owners/ownerDetails" );
		modelAndView.addObject ( ownerService.findById ( ownerId ) );
		return modelAndView;
	}
}
