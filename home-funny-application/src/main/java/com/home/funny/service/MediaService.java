package com.home.funny.service;

import com.home.funny.model.HomeFunnyMediaTag;
import com.home.funny.model.HomeFunnyMediaTagMapping;
import com.home.funny.model.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMediaTagMappingR2Repository;
import com.home.funny.repository.HomeFunnyMediaTagR2Repository;
import com.home.funny.repository.HomeFunnyMultiMediaR2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MediaService {
    @Autowired
    private HomeFunnyMediaTagR2Repository tagR2Repository;
    @Autowired
    private HomeFunnyMediaTagMappingR2Repository mappingR2Repository;
    @Autowired
    private HomeFunnyMultiMediaR2Repository mediaR2Repository;

    public Flux<HomeFunnyMediaTag> mediaTags() {
        return tagR2Repository.findAll();
    }

    public Flux<HomeFunnyMultiMedia> all() {
        // TODO: 2023/2/13  
    }
}
