package com.example.order;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @auther: yant09
 * @date: 2018/9/19 21:32
 */
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    //启动负载均衡 轮询调用customer服务
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String helloConsummer() {
        return restTemplate.getForEntity("http://CLOUD-CUSTOMER-SERVICE/customer", String.class).getBody();
    }

    //FeignClient 调用
    @RequestMapping(value = "/order/feign", method = RequestMethod.GET)
    public String helloConsummerFeign() {
        return customerFeignClient.index();
    }

    //DiscoveryClient 调用
    @RequestMapping(value = "/order/discovery/client", method = RequestMethod.GET)
    public String helloDiscoveryClient() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-CUSTOMER-SERVICE");
        if (!CollectionUtils.isEmpty(serviceInstances)) {
            for (ServiceInstance instance : serviceInstances) {
                System.out.println(instance.getUri().toString());
            }
        }
        return "DiscoveryClient";
    }
}
