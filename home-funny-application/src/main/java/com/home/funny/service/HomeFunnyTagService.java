package com.home.funny.service;

import com.home.funny.model.converter.HomeFunnyMediaTagMapper;
import com.home.funny.model.dto.HomeFunnyMediaTagDto;
import com.home.funny.model.po.HomeFunnyMediaTag;
import com.home.funny.repository.HomeFunnyMediaTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HomeFunnyTagService {
    @Autowired
    private HomeFunnyMediaTagRepository homeFunnyMediaTagRepository;
    @Autowired
    private HomeFunnyMediaTagMapper homeFunnyMediaTagMapper;

    public List<HomeFunnyMediaTag> mediaTags() {
        return homeFunnyMediaTagRepository.findAll();
    }

    @Transactional
    public HomeFunnyMediaTagDto putMediaTags(HomeFunnyMediaTagDto tag) {
        Optional<HomeFunnyMediaTag> byName = homeFunnyMediaTagRepository.findByName(tag.name());

        if (byName.isPresent()) {
            return homeFunnyMediaTagMapper.toDto(byName.get());
        }

        return homeFunnyMediaTagMapper.toDto(homeFunnyMediaTagRepository.save(homeFunnyMediaTagMapper.toEntity(tag)));
    }

    public void delete(Long id) {
        homeFunnyMediaTagRepository.deleteById(id);
    }
}
