package com.home.funny.service;

import com.home.funny.model.converter.HomeFunnyMultiMediaMapper;
import com.home.funny.model.dto.HomeFunnyMultiMediaDto;
import com.home.funny.model.dto.query.MediaQueryDTO;
import com.home.funny.model.po.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class HomeFunnyMediaService {
    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;
    @Autowired
    private HomeFunnyMultiMediaMapper homeFunnyMultiMediaMapper;


    public Page<HomeFunnyMultiMediaDto> findMedias(MediaQueryDTO parameter, Pageable page) {
        return homeFunnyMultiMediaRepository.findByNameLike(
                "%" + parameter.getName() + "%",
                page
        ).map(homeFunnyMultiMediaMapper::toDto);
    }

    public HomeFunnyMultiMediaDto findById(Long id) {
        return homeFunnyMultiMediaRepository.findById(id).map(homeFunnyMultiMediaMapper::toDto).orElseThrow();
    }

    @Transactional
    public HomeFunnyMultiMediaDto saveOrUpdate(HomeFunnyMultiMediaDto homeFunnyMultiMedia) {

        if (homeFunnyMultiMedia.id() != null) {
            return update(homeFunnyMultiMedia);
        } else {
            return save(homeFunnyMultiMedia);
        }
    }

    private HomeFunnyMultiMediaDto update(HomeFunnyMultiMediaDto homeFunnyMultiMedia) {

        HomeFunnyMultiMedia media = homeFunnyMultiMediaRepository.findById(homeFunnyMultiMedia.id()).orElseThrow();

        homeFunnyMultiMediaMapper.partialUpdate(homeFunnyMultiMedia, media);

        HomeFunnyMultiMedia result = homeFunnyMultiMediaRepository.save(media);

        return homeFunnyMultiMediaMapper.toDto(result);
    }

    private HomeFunnyMultiMediaDto save(HomeFunnyMultiMediaDto homeFunnyMultiMediaDto) {
        HomeFunnyMultiMedia homeFunnyMultiMedia = homeFunnyMultiMediaMapper.toEntity(homeFunnyMultiMediaDto);

        HomeFunnyMultiMedia result = homeFunnyMultiMediaRepository.save(homeFunnyMultiMedia);

        return homeFunnyMultiMediaMapper.toDto(result);
    }

    @Transactional
    public void delete(Long id) {
        Optional<HomeFunnyMultiMedia> byId = homeFunnyMultiMediaRepository.findById(id);

        byId.ifPresent(media -> homeFunnyMultiMediaRepository.delete(media));
    }


}
