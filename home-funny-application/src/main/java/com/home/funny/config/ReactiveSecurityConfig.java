package com.home.funny.config;

import com.home.funny.repository.HomeUserDetailsR2Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class ReactiveSecurityConfig {

    @Bean
    public UserDetailsRepositoryReactiveAuthenticationManager manager(HomeUserDetailsR2Repository service) {
        return new UserDetailsRepositoryReactiveAuthenticationManager(service);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) {
        return security.build();
    }

}
