package guru.springframework.spring6dependencyinjection.services;

import org.springframework.stereotype.Service;

@Service("propertyGreetingService")
public class GreetingServicePropertyInjected implements IGreetingService {

    /**
     * @return
     */
    @Override
    public String sayGreeting() {
        return "Friends don't let friends to property injection!!!";
    }
}
