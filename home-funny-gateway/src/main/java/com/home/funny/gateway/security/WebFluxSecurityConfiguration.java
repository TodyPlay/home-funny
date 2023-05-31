package com.home.funny.gateway.security;

import com.home.funny.gateway.security.handler.WebFluxExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebFluxSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http, WebFluxExceptionHandler webFluxExceptionHandler) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(webFluxExceptionHandler);
                })
                .authorizeExchange(v -> {
                    v.pathMatchers("/api/v1/**", "/minio/**").authenticated();
                })
                .authorizeExchange(v -> {
                    v.pathMatchers("/**").permitAll();
                })
                .build();
    }
}
