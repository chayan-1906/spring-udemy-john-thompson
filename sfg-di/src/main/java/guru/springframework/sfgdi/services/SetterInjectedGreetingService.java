package guru.springframework.sfgdi.services;

import guru.springframework.sfgdi.controllers.SetterInjectedController;
import org.springframework.stereotype.Service;

//@Service
public class SetterInjectedGreetingService implements GreetingService {
	/**
	 * @return
	 */
	@Override
	public String sayGreeting() {
		return "Hello World - Setter";
	}
}
