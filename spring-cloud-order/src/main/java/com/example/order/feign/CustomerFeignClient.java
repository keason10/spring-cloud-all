package com.example.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//注意服务调用服务名称要大写
//熔断
@FeignClient(value = "CLOUD-CUSTOMER-SERVICE", fallback = CustomerFeignClientImpl.class)
public interface CustomerFeignClient {
    @RequestMapping(value = "customer", method = RequestMethod.GET)
    String index();
}
