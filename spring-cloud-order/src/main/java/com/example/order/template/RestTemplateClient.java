package com.example.order.template;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;

    //熔断
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloConsummer() {
        return restTemplate.getForEntity("http://CLOUD-CUSTOMER-SERVICE/customer", String.class).getBody();
    }

    public String helloFallback(){
        return "error restTemplate";
    }
}
