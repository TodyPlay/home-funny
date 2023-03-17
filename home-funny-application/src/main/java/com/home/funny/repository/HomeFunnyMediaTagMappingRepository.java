package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyMediaTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyMediaTagMappingRepository extends JpaRepository<HomeFunnyMediaTagMapping, Long> {

}
