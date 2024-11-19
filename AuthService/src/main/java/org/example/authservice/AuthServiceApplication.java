package org.example.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AuthServiceApplication.class, args);
        System.out.println("Service name: " + context.getEnvironment().getProperty("spring.application.name"));
        System.out.println("Eureka app name: " + context.getEnvironment().getProperty("eureka.instance.appname"));

    }

}
