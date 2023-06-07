package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeFunnyUserDetailRepository extends JpaRepository<HomeFunnyUserDetail, Long> {
    Optional<HomeFunnyUserDetail> findByUsername(String username);
}