package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyMediaDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeFunnyMediaDetailRepository extends JpaRepository<HomeFunnyMediaDetail, Long> {

    List<HomeFunnyMediaDetail> findByMultiMediaId(Long id);
}