package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 1.获取认证信息
 */
@Component
@Slf4j
public class HomeFunnyAuthenticationConverter implements ServerAuthenticationConverter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {

        log.debug("2-登录时验证信息获取");

        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(body -> {
            try (InputStream inputStream = body.asInputStream()) {
                Map<String, Object> map = objectMapper.readValue(inputStream, new TypeReference<>() {
                });

                return Mono.just(new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password")));

            } catch (IOException e) {
                return Mono.error(e);
            }
        });

    }
}
