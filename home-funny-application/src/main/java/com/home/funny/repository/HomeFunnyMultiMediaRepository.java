package com.home.funny.repository;

import com.home.funny.dto.media.MediaQueryDTO;
import com.home.funny.model.HomeFunnyMultiMedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyMultiMediaRepository extends JpaRepository<HomeFunnyMultiMedia, Long> {

    @Query("""
            select distinct media from HomeFunnyMultiMedia media
            left join HomeFunnyMediaTagMapping mapping on mapping.multiMedia.id = media.id
            left join HomeFunnyMediaTag tag on tag.id = mapping.mediaTag.id
            where media.name like %:#{#query.name}%
            and media.mediaType in :#{#query.type}
            and tag.name in :#{#query.tags}
            and media.createDate between :#{#query.date[0]} and :#{#query.date[1]}
            """)
    Page<HomeFunnyMultiMedia> findAll(MediaQueryDTO query, Pageable pageable);
}
