package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyMediaTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyMediaTagRepository extends JpaRepository<HomeFunnyMediaTag, Long> {
}
