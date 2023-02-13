package com.home.funny.repository;

import com.home.funny.model.HomeFunnyMediaTagMapping;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyMediaTagMappingR2Repository extends R2dbcRepository<HomeFunnyMediaTagMapping, Long> {
}
