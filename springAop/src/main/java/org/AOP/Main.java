package org.AOP;

import org.example.BeanConfig;
import org.example.Doctor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);


}
