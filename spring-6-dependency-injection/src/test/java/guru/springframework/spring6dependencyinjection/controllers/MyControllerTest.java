package guru.springframework.spring6dependencyinjection.controllers;

import org.junit.jupiter.api.Test;

class MyControllerTest {

    @Test
    void sayHello() {
        MyController controller = new MyController();
        System.out.println(controller.sayHello());
    }
}
