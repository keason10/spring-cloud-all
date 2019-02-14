package com.example.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "CLOUD-CUSTOMER-SERVICE")
public interface CustomerFeignClient {
    @RequestMapping(value = "customer", method = RequestMethod.GET)
    String index();
}
