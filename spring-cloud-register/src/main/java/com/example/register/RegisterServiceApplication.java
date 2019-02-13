package com.example.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegisterServiceApplication {

    public static void main(String[] args) {
        System.out.println("four");
        SpringApplication.run(RegisterServiceApplication.class, args);

    }
}
