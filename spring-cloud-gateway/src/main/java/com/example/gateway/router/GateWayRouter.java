package com.example.gateway.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GateWayRouter {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_response_upper", r -> r.host("*.rewriteresponseupper.org")
                        .filters(f -> f.prefixPath("/gateway")
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, s) -> Mono.just(s.toUpperCase()))).uri("http://localhost:1121/gateway/hello/123"))
                .build();
    }
}
