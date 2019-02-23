package com.example.gateway.controller;

import com.example.gateway.client.CustomerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
@RestController 的value 属性的意思：The value may indicate a suggestion for a logical component name,
注意@RestController（value = "gateway"） 并不能使路径带上 /gateway， 所以要使用 @RequestMapping(value = "/gateway")
RequestMapping value ="/gateway" 和 value ="gateway" 一样的 ，即 带不带/ 效果都一样
*/
@RestController
@RequestMapping(value = "/gateway")
public class GateWayFirstController {

    @Autowired
    private CustomerFeignClient customerFeignClient;

    /*
    PathVariable : hello/{name}
    RequestParam : hell0?foo=bar
    */
    @GetMapping(value = "hello/{name}")
    public String gateway(@PathVariable(name = "name") String str, @RequestParam(value = "foo") String foo,
                          @RequestHeader("X-Request-Foo-Property-Filter") String restStr,
                          @RequestHeader("X-Request-Foo-Abstract-Filter") String REQUEST_HEADER_ABSTRACT_FILTER,
                          @RequestHeader("X-Request-Foo-Global-Filter") String REQUEST_HEADER_Filter) {//,@RequestHeader("X-Request-Foo") String restStr
        return str + "  " + foo;
    }

    @GetMapping(value = "/client")
    public String feignClient() {
        return customerFeignClient.index();
    }

    @PostMapping(value = "post")
    public String post() {
        return "post";
    }

    @GetMapping(value = "/rate/{value}")
    public String getRate(@PathVariable(name = "value") String value) {
        return "rate: "+value;
    }
}
