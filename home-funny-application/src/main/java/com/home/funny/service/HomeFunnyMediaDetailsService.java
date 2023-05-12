package com.home.funny.service;

import com.home.funny.model.po.HomeFunnyMediaDetail;
import com.home.funny.model.po.HomeFunnyStorage;
import com.home.funny.repository.HomeFunnyMediaDetailRepository;
import com.home.funny.repository.HomeFunnyStorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class HomeFunnyMediaDetailsService {

    private final HomeFunnyStorageRepository homeFunnyStorageRepository;
    private final HomeFunnyMediaDetailRepository homeFunnyMediaDetailRepository;

    public HomeFunnyMediaDetailsService(HomeFunnyStorageRepository homeFunnyStorageRepository, HomeFunnyMediaDetailRepository homeFunnyMediaDetailRepository) {
        this.homeFunnyStorageRepository = homeFunnyStorageRepository;
        this.homeFunnyMediaDetailRepository = homeFunnyMediaDetailRepository;
    }

    @Transactional
    public HomeFunnyMediaDetail saveOrUpdate(HomeFunnyMediaDetail detail) {
        if (detail.getStorage() != null) {
            homeFunnyStorageRepository.save(detail.getStorage());
        }
        return homeFunnyMediaDetailRepository.save(detail);
    }

    @Transactional
    public List<HomeFunnyMediaDetail> saveOrUpdate(List<HomeFunnyMediaDetail> detail) {

        List<HomeFunnyStorage> filteredStorage = detail.stream().map(HomeFunnyMediaDetail::getStorage).filter(Objects::nonNull).toList();
        homeFunnyStorageRepository.saveAll(filteredStorage);

        return homeFunnyMediaDetailRepository.saveAll(detail);
    }
}
