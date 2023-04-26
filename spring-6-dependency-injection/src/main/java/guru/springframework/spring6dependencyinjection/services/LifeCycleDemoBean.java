package guru.springframework.spring6dependencyinjection.services;

import guru.springframework.spring6dependencyinjection.controllers.MyController;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, BeanPostProcessor {

    private String javaVer;

    public LifeCycleDemoBean() {
        System.out.println("## I'm in the LifeCycleBean Constructor ##");
    }

    @Value("${java.specification.version}")
    public void setJavaVer(String javaVer) {
        this.javaVer = javaVer;
        System.out.println("## 1 Properties Set. Java Ver: " + this.javaVer);
    }

    /**
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("## 2 BeanNameAware My Bean Name is: " + name);
    }

    /**
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## 3 BeanFactoryAware - Bean Factory has been set");
    }

    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("## 4 ApplicationContextAware - Application Context has been set");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("## 5 PostConstruct - The Post Construct annotated method has been called");
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("## 6 afterPropertiesSet - Populate Properties The LifeCycleBean has its properties");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("## 7 The @PreDestroy annotated method has been called");
    }

    /**
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("## 8 DisposableBean.destroy The LifeCycleBean has been terminated");
    }

    /**
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("## postProcessBeforeInitialization: " + beanName);
        if (bean instanceof MyController) {
            MyController myController = (MyController) bean;
            System.out.println("Calling before init");
            myController.beforeInit();
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    /**
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization: " + beanName);
        if (bean instanceof MyController) {
            MyController myController = (MyController) bean;
            System.out.println("Calling after init");
            myController.afterInit();
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    public void beforeInit() {
        System.out.println("## Before Init - Called by Bean Post Processor");
    }

    public void afterInit() {
        System.out.println("## After Init - Called by Bean Post Processor");
    }
}
