package com.home.funny.gateway.security.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HomeFunnyUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.just(User.builder().passwordEncoder(passwordEncoder::encode).username(username).password("123456").roles("ADMIN", "USER").build());
    }
}
