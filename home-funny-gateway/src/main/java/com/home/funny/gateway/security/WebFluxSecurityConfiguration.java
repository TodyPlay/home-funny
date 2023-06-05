package com.home.funny.gateway.security;

import com.home.funny.gateway.security.handlers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
public class WebFluxSecurityConfiguration {

    public static final String LOGIN_URL = "/api/v1/login";
    public static final String LOGOUT_URL = "/api/v1/logout";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http,
                                              AuthenticationWebFilter authenticationWebFilter,
                                              HomeFunnySecurityContextRepository homeFunnySecurityContextRepository,
                                              HomeFunnyAccessDeniedHandler homeFunnyAccessDeniedHandler,
                                              HomeFunnyAuthenticationEntryPoint homeFunnyAuthenticationEntryPoint,
                                              HomeFunnyLogoutSuccessHandler homeFunnyLogoutSuccessHandler,
                                              HomeFunnyAuthorizationManager homeFunnyAuthorizationManager) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(exc -> {
                    exc
                            .pathMatchers(LOGIN_URL, LOGOUT_URL).permitAll()
                            .pathMatchers("/api/v1/**", "/minio/**").access(homeFunnyAuthorizationManager);
                })
                .securityContextRepository(homeFunnySecurityContextRepository)
                .exceptionHandling(v -> v
                        .accessDeniedHandler(homeFunnyAccessDeniedHandler)
                        .authenticationEntryPoint(homeFunnyAuthenticationEntryPoint))
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .logout(out -> {
                    out
                            .logoutUrl(LOGOUT_URL)
                            .logoutSuccessHandler(homeFunnyLogoutSuccessHandler);
                })
                .build();

    }

    @Bean
    public AuthenticationWebFilter authenticationWebFilter(HomeFunnyAuthenticationManager reactiveAuthenticationManager,
                                                           HomeFunnySecurityContextRepository homeFunnySecurityContextRepository,
                                                           HomeFunnyAuthenticationConverter homeFunnyAuthenticationConverter,
                                                           HomeFunnyAuthenticationSuccessHandler homeFunnyAuthenticationSuccessHandler,
                                                           HomeFunnyAuthenticationFailureHandler homeFunnyAuthenticationFailureHandler) {

        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager);

        authenticationWebFilter.setSecurityContextRepository(homeFunnySecurityContextRepository);
        authenticationWebFilter.setServerAuthenticationConverter(homeFunnyAuthenticationConverter);
        authenticationWebFilter.setAuthenticationSuccessHandler(homeFunnyAuthenticationSuccessHandler);
        authenticationWebFilter.setAuthenticationFailureHandler(homeFunnyAuthenticationFailureHandler);
        authenticationWebFilter.setRequiresAuthenticationMatcher(
                ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, LOGIN_URL)
        );

        return authenticationWebFilter;

    }
}
