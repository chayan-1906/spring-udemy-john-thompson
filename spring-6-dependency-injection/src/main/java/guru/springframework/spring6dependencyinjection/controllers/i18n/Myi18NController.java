package guru.springframework.spring6dependencyinjection.controllers.i18n;

import guru.springframework.spring6dependencyinjection.services.IGreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class Myi18NController {

    private final IGreetingService greetingService;

    public Myi18NController(@Qualifier("i18nService") IGreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
