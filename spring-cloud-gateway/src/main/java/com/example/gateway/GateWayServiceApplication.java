package com.example.gateway;

import com.example.gateway.filter.GateWayFilter;
import com.example.gateway.router.GateWayRouter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ImportAutoConfiguration(classes = { GateWayFilter.class})
public class GateWayServiceApplication {

    public static void main(String[] args) {
        System.out.println("gateway");
        SpringApplication.run(GateWayServiceApplication.class, args);
    }
}
