package com.home.funny.gateway.config.router;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "hf.routes")
public class GatewayRouterConfiguration {

    private String minioUrl;
    private String appUrl;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("minio", r -> r.path("/minio/**")
                        .filters(f -> f.rewritePath("/minio/(?<segment>)", "/${segment}"))
                        .uri(minioUrl))
                .route("app", r -> r.path("/api/**").uri(appUrl))
                .build();
    }
}
