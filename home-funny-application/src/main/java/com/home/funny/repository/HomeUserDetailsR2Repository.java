package com.home.funny.repository;

import com.home.funny.model.HomeFunnyUserDetails;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeUserDetailsR2Repository extends R2dbcRepository<HomeFunnyUserDetails, Long>, ReactiveUserDetailsService {

}
