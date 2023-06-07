package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.gateway.security.dto.HFResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HomeFunnyAccessDeniedHandler implements ServerAccessDeniedHandler {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        log.debug("1-访问拒绝处理器");
        return Mono
                .defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(resp -> {
                    resp.setStatusCode(HttpStatus.UNAUTHORIZED);
                    resp.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    HFResponse<?> response = HFResponse.unAuthorization(denied.getMessage());

                    try {
                        DataBuffer buffer = resp.bufferFactory().wrap(objectMapper.writeValueAsBytes(response));
                        return resp.writeWith(Mono.just(buffer));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }
}
