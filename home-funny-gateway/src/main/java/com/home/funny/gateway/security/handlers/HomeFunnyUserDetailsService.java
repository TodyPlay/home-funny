package com.home.funny.gateway.security.handlers;

import com.home.funny.gateway.security.call.UserCaller;
import com.home.funny.gateway.security.dto.HFUserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class HomeFunnyUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCaller userCaller;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        Mono<HFUserDetail> user = userCaller.findUserByName(username);

        return user.doOnNext(u -> {
            log.debug("{}", u);
        }).map(u -> {
            return User.builder().passwordEncoder(passwordEncoder::encode).username(u.username()).password(u.password()).roles("ADMIN", "USER").build();
        });
    }
}
