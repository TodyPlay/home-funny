package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Map;

@Component
@Slf4j
public class HomeFunnyLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        log.debug("8-登出成功处理");
        ServerHttpResponse response = exchange.getExchange().getResponse();
        DataBufferFactory dataBufferFactory = response.bufferFactory();

        Map<String, ? extends Serializable> code = Map.of("code", 200, "message", "登出成功");

        try {
            return response.writeWith(Mono.just(dataBufferFactory.wrap(objectMapper.writeValueAsBytes(code))));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }

    }
}
