package guru.springframework.spring6dependencyinjection.services;

import org.springframework.stereotype.Service;

@Service("setterGreetingBean")
public class GreetingServiceSetterInjection implements IGreetingService {

    /**
     * @return
     */
    @Override
    public String sayGreeting() {
        return "Hey I'm setting a Greeting";
    }
}
