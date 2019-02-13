package com.example.register.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegisterTwoServiceApplication {

    public static void main(String[] args) {
        System.out.println("four");
        SpringApplication.run(RegisterTwoServiceApplication.class, args);

    }
}
