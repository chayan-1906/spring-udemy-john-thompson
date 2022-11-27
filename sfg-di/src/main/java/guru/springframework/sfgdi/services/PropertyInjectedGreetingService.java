package guru.springframework.sfgdi.services;

import org.springframework.stereotype.Service;

@Service
public class PropertyInjectedGreetingService implements GreetingService {
	/**
	 * @return
	 */
	@Override
	public String sayGreeting() {
		return "Hello World - Property";
	}
}
