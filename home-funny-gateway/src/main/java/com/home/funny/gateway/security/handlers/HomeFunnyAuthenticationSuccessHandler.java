package com.home.funny.gateway.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@Slf4j
public class HomeFunnyAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.debug("6-验证成功处理器");
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()))
                .flatMap(resp -> {
                    resp.getHeaders().setContentType(MediaType.APPLICATION_JSON);

                    HashMap<String, Object> map = new HashMap<>();

                    map.put("code", 200);
                    map.put("message", "登录成功");
                    map.put("token", authentication.getPrincipal());

                    try {
                        return resp.writeWith(Mono.just(resp.bufferFactory().wrap(objectMapper.writeValueAsBytes(map))));
                    } catch (JsonProcessingException e) {
                        return Mono.error(e);
                    }
                });
    }
}
