package com.home.funny.gateway.security.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HomeFunnyAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        log.debug("7-权限验证校验");
        return authentication.filter(Authentication::isAuthenticated)
                .map(Authentication::isAuthenticated)
                .map(AuthorizationDecision::new)
                .switchIfEmpty(Mono.defer(() -> Mono.just(new AuthorizationDecision(false))));
    }
}
