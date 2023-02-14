package com.home.funny.constant;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限级别
 */
public enum Authority {

    API,

    ADMIN;


    public static class AuthorityListConverter implements Converter<String, List<Authority>> {

        @Override
        public List<Authority> convert(String source) {
            if (source.isBlank()) {
                return new ArrayList<>();
            }

            return Arrays.stream(source.split(",")).map(Authority::valueOf).collect(Collectors.toList());
        }
    }
}
