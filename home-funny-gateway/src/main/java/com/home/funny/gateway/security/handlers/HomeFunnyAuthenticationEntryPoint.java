package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.gateway.security.dto.HFResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

@Component
@Slf4j
public class HomeFunnyAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        log.debug("3-未登录时处理");
        return Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(resp -> {
                    resp.setStatusCode(HttpStatus.UNAUTHORIZED);
                    resp.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    HFResponse<?> response = HFResponse.unAuthorization("尚未登录");

                    try {
                        DataBuffer buffer = resp.bufferFactory().wrap(objectMapper.writeValueAsBytes(response));
                        return resp.writeWith(Mono.just(buffer));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });

    }
}
