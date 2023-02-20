package guru.springframework.sfgpetclinic.formatters;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.IPetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

	private final IPetTypeService iPetTypeService;

	@Autowired
	public PetTypeFormatter(IPetTypeService iPetTypeService) {
		this.iPetTypeService = iPetTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName ( );
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
		Collection<PetType> findPetTypes = iPetTypeService.findAll ( );
		for (PetType petType : findPetTypes) {
			if (petType.getName ( ).equals ( text )) return petType;
		}
		throw new ParseException ( "type not found: " + text, 0 );
	}
}
