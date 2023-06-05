package com.home.funny.gateway.security.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class HomeFunnyAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        log.debug("1-访问拒绝处理器");
        return Mono
                .defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(resp -> {
                    resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    resp.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    DataBuffer buffer = resp.bufferFactory().wrap("""
                            {"code":"403"},"message":"权限拒绝"}""".getBytes(StandardCharsets.UTF_8));
                    return resp.writeWith(Mono.just(buffer));
                });
    }
}
