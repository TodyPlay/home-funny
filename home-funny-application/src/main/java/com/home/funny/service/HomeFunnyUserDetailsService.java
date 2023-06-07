package com.home.funny.service;

import com.home.funny.model.converter.HomeFunnyUserDetailMapper;
import com.home.funny.model.dto.HomeFunnyUserDetailDto;
import com.home.funny.repository.HomeFunnyUserDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeFunnyUserDetailsService {

    private final HomeFunnyUserDetailRepository homeFunnyUserDetailRepository;
    private final HomeFunnyUserDetailMapper homeFunnyUserDetailMapper;

    public HomeFunnyUserDetailsService(HomeFunnyUserDetailRepository homeFunnyUserDetailRepository,
                                       HomeFunnyUserDetailMapper homeFunnyUserDetailMapper) {
        this.homeFunnyUserDetailRepository = homeFunnyUserDetailRepository;
        this.homeFunnyUserDetailMapper = homeFunnyUserDetailMapper;
    }

    public HomeFunnyUserDetailDto findByUsername(String username) {
        return homeFunnyUserDetailRepository.findByUsername(username).map(homeFunnyUserDetailMapper::toDto).orElse(null);
    }
}
