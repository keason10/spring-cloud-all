package com.example.gateway.controller;

import com.example.gateway.client.CustomerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//注意@RestController（value = "gateway"） 并不能使路径带上 /gateway
//value ="/gateway" 和 value ="gateway" 一样的 ，即 带不带/ 效果都一样
@RestController
@RequestMapping(value = "/gateway")
public class GateWayFirstController {

    @Autowired
    private CustomerFeignClient customerFeignClient;

    @GetMapping(value = "hello")
    public String gateway() {
        return "gateway";
    }

    @GetMapping(value = "/client")
    public String feignClient() {
        return customerFeignClient.index();
    }
}
