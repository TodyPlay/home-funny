package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.gateway.security.dto.HFResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HomeFunnyAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        log.debug("4-认证失败处理器");
        return Mono.just(webFilterExchange.getExchange().getResponse())
                .flatMap(resp -> {
                    resp.setStatusCode(HttpStatus.UNAUTHORIZED);
                    resp.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    DataBufferFactory dataBufferFactory = resp.bufferFactory();

                    HFResponse<?> response = HFResponse.unAuthorization(exception.getMessage());

                    try {
                        return resp.writeWith(Mono.just(dataBufferFactory.wrap(objectMapper.writeValueAsBytes(response))));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }
}
