package com.home.funny.config;

import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "s3")
public class MinioClientConfiguration {

    private String endpoint;

    private String access;

    private String secret;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(access, secret).build();
    }

    @Bean
    public MinioAsyncClient minioAsyncClient() {
        return MinioAsyncClient.builder().endpoint(endpoint).credentials(access, secret).build();

    }
}
