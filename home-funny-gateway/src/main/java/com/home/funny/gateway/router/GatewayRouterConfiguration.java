package com.home.funny.gateway.router;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hf.routes")
public class GatewayRouterConfiguration {

    private String minioUrl;
    private String appUrl;
    private String indexUrl;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("minio", r -> r.path("/minio/**")
                        .filters(f -> f.rewritePath("/minio/(?<segment>)", "/${segment}"))
                        .uri(minioUrl))
                .route("app", r -> r.path("/api/**").uri(appUrl))
                .route("index", r -> r.path("/**").uri(indexUrl))
                .build();
    }

    public String getMinioUrl() {
        return minioUrl;
    }

    public void setMinioUrl(String minioUrl) {
        this.minioUrl = minioUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }
}
