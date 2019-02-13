package com.example.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    @Autowired
    private Environment environment;
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "customer", method = RequestMethod.GET)
    public String index() {
        System.out.println("spring.application.name=" + environment.getProperty("spring.application.name") + "    server.port=" + environment.getProperty("server.port"));
        return "hello world";
    }
}
