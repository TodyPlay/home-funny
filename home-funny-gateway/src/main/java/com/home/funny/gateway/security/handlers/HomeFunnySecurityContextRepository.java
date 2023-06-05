package com.home.funny.gateway.security.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;


@Component
@Slf4j
public class HomeFunnySecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private HomeFunnyUserDetailsService homeFunnyUserDetailsService;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        log.debug("9-加载上下文");

        ServerHttpRequest request = exchange.getRequest();
        HttpCookie sessionId = request.getCookies().getFirst("Token");

        if (sessionId == null) {
            return Mono.empty();
        }

        Mono<UserDetails> byUsername = homeFunnyUserDetailsService.findByUsername(sessionId.getValue());

        return byUsername.map(admin -> new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword(), Collections.emptyList()))
                .map(SecurityContextImpl::new);
    }
}
