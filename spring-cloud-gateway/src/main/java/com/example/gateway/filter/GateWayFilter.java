package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GateWayFilter {

    /*这种方式可以进行调试  如果用下边的 lambda 则调试不了*/
    @Bean
    public  GlobalFilter getGateWayFilterImplInt() {
        return new GateWayFilterImplInt();
    }

    //代码手动在请求头中添加信息 #######################################################################################
    /*GlobalFilter 只用定义@Bean注解就可以了*/
    @Bean
    public GlobalFilter customGlobalFilter() {
        return new GlobalFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //If you want to build a "pre" filter you need to manipulate the
                //request before calling chain.filter
                ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
                //use builder to manipulate the request
                builder.header("X-Request-Foo-Global-Filter", "hello");
                return chain.filter(exchange.mutate().request(builder.build()).build());
            }
        };
    }

    //代码手动在相应头里边添加信息
    @Bean
    public GlobalFilter customGlobalPostFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.just(exchange))
                .map(serverWebExchange -> {
                    //adds header to response
                    serverWebExchange.getResponse().getHeaders().set("X-Response-Foo-Global-Filter",
                            HttpStatus.OK.equals(serverWebExchange.getResponse().getStatusCode()) ? "It worked": "It did not work");
                    return serverWebExchange;
                }).then();
    }

    //定义顺序顺序越小，pre越先执行，post 越后执行 ######################################################
    @Bean
    @Order(-1)
    public GlobalFilter a() {
        return (exchange, chain) -> {
            System.out.println("first pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("third post filter");
            }));
        };
    }

    @Bean
    @Order(0)
    public GlobalFilter b() {
        return (exchange, chain) -> {
            System.out.println("second pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("second post filter");
            }));
        };
    }

    //访问频次限制 #################################################################################
    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
