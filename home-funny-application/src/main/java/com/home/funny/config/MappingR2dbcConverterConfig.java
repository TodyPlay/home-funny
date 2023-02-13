package com.home.funny.config;

import com.home.funny.constant.Authority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;

import java.util.List;

@Configuration
public class MappingR2dbcConverterConfig {


    @Bean("com.home.funny.config.MappingR2dbcConverterConfig.customConversions")
    public R2dbcCustomConversions customConversions() {
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, List.of(new Authority.AuthorityListConverter()));
    }
}
