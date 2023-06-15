package com.home.funny.model.po.listener;

import com.home.funny.model.po.HomeFunnyUserDetail;
import com.home.funny.repository.HomeFunnyUserDetailRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HomeFunnyAuditorAware implements AuditorAware<HomeFunnyUserDetail> {

    @Autowired
    private HomeFunnyUserDetailRepository homeFunnyUserDetailRepository;

    @NotNull
    @Override
    public Optional<HomeFunnyUserDetail> getCurrentAuditor() {
        return homeFunnyUserDetailRepository.findById(1L);
    }
}
