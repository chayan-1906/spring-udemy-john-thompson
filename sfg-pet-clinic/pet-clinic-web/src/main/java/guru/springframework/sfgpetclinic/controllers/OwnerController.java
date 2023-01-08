package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.IOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("owners")
@Controller
public class OwnerController {

	private final IOwnerService ownerService;

	public OwnerController(IOwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOfOwners(Model model) {
		model.addAttribute ( "owners", ownerService.findAll ( ) );
		return "owners/index";
	}

	@RequestMapping("/find")
	public String findOwners() {
		return "notimplemented";
	}
}
