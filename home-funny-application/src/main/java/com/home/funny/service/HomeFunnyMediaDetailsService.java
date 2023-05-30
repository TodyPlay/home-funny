package com.home.funny.service;

import com.home.funny.model.converter.HomeFunnyMediaDetailMapperImpl;
import com.home.funny.model.dto.HomeFunnyMediaDetailDto;
import com.home.funny.model.po.HomeFunnyMediaDetail;
import com.home.funny.model.po.HomeFunnyStorage;
import com.home.funny.repository.HomeFunnyMediaDetailRepository;
import com.home.funny.repository.HomeFunnyStorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HomeFunnyMediaDetailsService {

    private final HomeFunnyStorageRepository homeFunnyStorageRepository;
    private final HomeFunnyMediaDetailRepository homeFunnyMediaDetailRepository;
    private final HomeFunnyMediaDetailMapperImpl homeFunnyMediaDetailMapper;

    public HomeFunnyMediaDetailsService(HomeFunnyStorageRepository homeFunnyStorageRepository, HomeFunnyMediaDetailRepository homeFunnyMediaDetailRepository, HomeFunnyMediaDetailMapperImpl homeFunnyMediaDetailMapper) {
        this.homeFunnyStorageRepository = homeFunnyStorageRepository;
        this.homeFunnyMediaDetailRepository = homeFunnyMediaDetailRepository;
        this.homeFunnyMediaDetailMapper = homeFunnyMediaDetailMapper;
    }

    @Transactional
    public HomeFunnyMediaDetail saveOrUpdate(HomeFunnyMediaDetail detail) {
        if (detail.getStorage() != null) {
            homeFunnyStorageRepository.save(detail.getStorage());
        }
        return homeFunnyMediaDetailRepository.save(detail);
    }

    @Transactional
    public List<HomeFunnyMediaDetail> saveOrUpdate(List<HomeFunnyMediaDetail> details) {

        List<HomeFunnyStorage> filteredStorage = details.stream().map(HomeFunnyMediaDetail::getStorage).filter(Objects::nonNull).toList();
        homeFunnyStorageRepository.saveAll(filteredStorage);

        return homeFunnyMediaDetailRepository.saveAll(details);
    }

    @Transactional
    public void delete(List<HomeFunnyMediaDetail> details) {
        homeFunnyMediaDetailRepository.deleteAll(details);
    }

    public HomeFunnyMediaDetailDto detailById(Long id) {
        Optional<HomeFunnyMediaDetail> detail = homeFunnyMediaDetailRepository.findById(id);
        return detail.map(homeFunnyMediaDetailMapper::toDto).orElseThrow();
    }

}
