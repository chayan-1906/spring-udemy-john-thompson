package guru.springframework.spring6dependencyinjection.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements IGreetingService {

    /**
     * @return
     */
    @Override
    public String sayGreeting() {
        return "Hello Everyone from Base Service!!!";
    }
}
