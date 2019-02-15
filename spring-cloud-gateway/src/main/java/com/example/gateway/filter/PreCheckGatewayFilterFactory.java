package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

//代码手动在请求头中添加信息
@Service
public class PreCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<PreCheckGatewayFilterFactory.Config> {

	public PreCheckGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		// grab configuration from Config object
		System.out.println("GatewayFilter");
		return (exchange, chain) -> {
            //If you want to build a "pre" filter you need to manipulate the
            //request before calling chain.filter
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            //use builder to manipulate the request
			builder.header("X-Request-Foo-Abstract-Filter", "hello");
            return chain.filter(exchange.mutate().request(builder.build()).build());
		};
	}

	public static class Config {
        //Put the configuration properties for your filter here
	}

}