package com.home.funny.repository;

import com.home.funny.model.HomeFunnyMultiMedia;
import org.springframework.data.r2dbc.core.ReactiveSelectOperation;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface HomeFunnyMultiMediaR2Repository extends R2dbcRepository<HomeFunnyMultiMedia, Long> {

}
