package com.home.funny.service;

import com.home.funny.dto.PageableDTO;
import com.home.funny.dto.media.MediaQueryDTO;
import com.home.funny.model.HomeFunnyMediaTag;
import com.home.funny.model.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMediaTagRepository;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    private HomeFunnyMediaTagRepository tagRepository;
    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;

    public List<HomeFunnyMediaTag> mediaTags() {
        return tagRepository.findAll();
    }

    public Page<HomeFunnyMultiMedia> findMedias(MediaQueryDTO query, PageableDTO page) {
        return homeFunnyMultiMediaRepository.findAll(page.getPageRequest());
    }

}
