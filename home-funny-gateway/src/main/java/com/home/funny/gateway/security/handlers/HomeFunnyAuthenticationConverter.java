package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.gateway.security.dto.LoginData;
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
                LoginData loginData = objectMapper.readValue(inputStream, LoginData.class);

                return Mono.just(new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));

            } catch (IOException e) {
                return Mono.error(e);
            }
        });

    }
}
