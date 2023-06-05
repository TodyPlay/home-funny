package com.home.funny.gateway.security.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@Slf4j
public class HomeFunnyAuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private HomeFunnyUserDetailsService homeFunnyUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        log.debug("5-认证管理器");
        if (authentication.isAuthenticated()) {
            return Mono.just(authentication);
        }

        return homeFunnyUserDetailsService.findByUsername(authentication.getPrincipal().toString()).flatMap(userDetails -> {
            String rawPassword = authentication.getCredentials().toString();
            String encodedPassword = userDetails.getPassword();
            boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
            if (matches) {
                return Mono.just(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.emptyList()));
            }

            return Mono.error(() -> new UsernameNotFoundException("密码错误或用户未找到"));
        });

    }
}
