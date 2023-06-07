package com.home.funny.gateway.security.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class HomeFunnySecurityContextRepository implements ServerSecurityContextRepository {

    private final String contextKey = "hf.session.security.context.key";

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        log.debug("9.1-保存上下文");
        return exchange.getSession().doOnNext(session -> {
            if (context == null) {
                session.getAttributes().remove(contextKey);
            } else {
                session.getAttributes().put(contextKey, context);
            }
        }).flatMap(WebSession::changeSessionId);

    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        log.debug("9.2-加载上下文");
        return exchange.getSession().flatMap(session -> {
            Object ctx = session.getAttributes().get(contextKey);
            return Mono.justOrEmpty((SecurityContext) ctx);
        });
    }
}
