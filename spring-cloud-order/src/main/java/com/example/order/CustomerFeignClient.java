package com.example.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//注意服务调用服务名称要大写
@FeignClient(value = "CLOUD-CUSTOMER-SERVICE")
public interface CustomerFeignClient {
    @RequestMapping(value = "customer", method = RequestMethod.GET)
    String index();
}
