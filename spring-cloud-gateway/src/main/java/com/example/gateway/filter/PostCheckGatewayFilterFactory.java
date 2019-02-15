package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/*AbstractGatewayFilterFactory 方式，需要
1.@Service 注解
2.实现 AbstractGatewayFilterFactory
3.类名一定要为${filterName} + GatewayFilterFactory，如这里定义为JwtCheckGatewayFilterFactory的话，它的filterName就是JwtCheck
3.在 application.yml 文件添加
		filters:
		 -${filterName}
 */
@Service
public class PostCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<PostCheckGatewayFilterFactory.Config> {

	public PostCheckGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		// grab configuration from Config object
		return (exchange, chain) -> chain.filter(exchange).then(Mono.just(exchange))
				.map(serverWebExchange -> {
					//adds header to response
					serverWebExchange.getResponse().getHeaders().set("X-Response-Foo-Abstract-Filter",
							HttpStatus.OK.equals(serverWebExchange.getResponse().getStatusCode()) ? "It worked": "It did not work");
					return serverWebExchange;
				}).then();
	}

	public static class Config {
        //Put the configuration properties for your filter here
	}

}