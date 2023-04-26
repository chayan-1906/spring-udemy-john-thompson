package guru.springframework.spring6dependencyinjection.controllers;

import guru.springframework.spring6dependencyinjection.services.GreetingServiceImpl;
import guru.springframework.spring6dependencyinjection.services.IGreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final IGreetingService greetingService;

    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }

    public String sayHello() {
        System.out.println("I'm in the controller");
        return greetingService.sayGreeting();
    }

    public void beforeInit() {
        System.out.println("## Before Init - Called by Bean Post Processor");
    }

    public void afterInit() {
        System.out.println("## After Init - Called by Bean Post Processor");
    }
}
