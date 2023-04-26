package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class GreetingServicePrimary implements IGreetingService {

    /**
     * @return
     */
    @Override
    public String sayGreeting() {
        return "Hello from the Primary Bean";
    }
}
