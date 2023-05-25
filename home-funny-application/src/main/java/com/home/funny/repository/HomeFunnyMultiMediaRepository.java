package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyMultiMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeFunnyMultiMediaRepository extends JpaRepository<HomeFunnyMultiMedia, Long>, JpaSpecificationExecutor<HomeFunnyMultiMedia> {
    Page<HomeFunnyMultiMedia> findByNameLike(@Nullable String name, Pageable pageable);

    @NonNull
    Optional<HomeFunnyMultiMedia> findByMediaTags_NameLike(@Nullable String name);

}
