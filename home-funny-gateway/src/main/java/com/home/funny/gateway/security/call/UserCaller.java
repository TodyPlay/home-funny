package com.home.funny.gateway.security.call;

import com.home.funny.gateway.security.dto.HFUserDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/api/v1/user")
public interface UserCaller {

    @GetExchange("{username}")
    Mono<HFUserDetail> findUserByName(@PathVariable String username);

}
