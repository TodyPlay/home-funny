package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.funny.gateway.security.dto.HFResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HomeFunnyAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.debug("6-验证成功处理器");

        return webFilterExchange.getExchange().getSession().map(WebSession::getId)
                .flatMap(sessionId -> {
                    ServerHttpResponse resp = webFilterExchange.getExchange().getResponse();
                    resp.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    HFResponse<?> response = HFResponse.ok(sessionId, "登录成功");

                    try {
                        return resp.writeWith(Mono.just(resp.bufferFactory().wrap(objectMapper.writeValueAsBytes(response))));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }
}