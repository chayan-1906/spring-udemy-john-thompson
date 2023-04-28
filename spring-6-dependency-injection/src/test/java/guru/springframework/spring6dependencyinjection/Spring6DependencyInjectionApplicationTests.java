package guru.springframework.spring6dependencyinjection;

import guru.springframework.spring6dependencyinjection.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6DependencyInjectionApplicationTests {

    @Autowired
    MyController controller;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void testAutowireOfController() {
        System.out.println(controller.sayHello());
    }

    @Test
    void testGetControllerFromCtx() {
        controller = applicationContext.getBean(MyController.class);
        System.out.println(controller.sayHello());
    }

    @Test
    void contextLoads() {
    }

}
