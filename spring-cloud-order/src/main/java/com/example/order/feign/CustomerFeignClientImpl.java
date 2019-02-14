package com.example.order.feign;

import org.springframework.stereotype.Service;

//让spring 管理Bean
@Service
public class CustomerFeignClientImpl implements CustomerFeignClient {
    @Override
    public String index() {
        return "error CustomerFeignClient";

    }
}
