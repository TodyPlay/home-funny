package com.home.funny.service;

import com.home.funny.constant.MediaType;
import com.home.funny.dto.HomeFunnyMultiMediaDto;
import com.home.funny.dto.PageableDTO;
import com.home.funny.dto.media.MediaQueryDTO;
import com.home.funny.model.HomeFunnyMediaTag;
import com.home.funny.model.HomeFunnyMediaTagMapping;
import com.home.funny.model.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMediaTagRepository;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {
    @Autowired
    private HomeFunnyMediaTagRepository tagRepository;
    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;

    public List<HomeFunnyMediaTag> mediaTags() {
        return tagRepository.findAll();
    }

    public Page<HomeFunnyMultiMediaDto> findMedias(MediaQueryDTO parameter, PageableDTO page) {
        Specification<HomeFunnyMultiMedia> sf = (root, query, cb) -> {

            Join<HomeFunnyMultiMedia, HomeFunnyMediaTagMapping> mapingJoin = root.join("tagMappings", JoinType.LEFT);
            mapingJoin.on(cb.equal(root.get("id").as(Long.class), mapingJoin.get("multiMedia").as(HomeFunnyMultiMedia.class)));

            Join<HomeFunnyMediaTagMapping, HomeFunnyMediaTag> tagJoin = mapingJoin.join("mediaTag", JoinType.LEFT);
            tagJoin.on(cb.equal(mapingJoin.get("mediaTag").as(HomeFunnyMediaTag.class), tagJoin.get("id").as(Long.class)));

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(parameter.getName())) {
                predicates.add(cb.like(root.get("name").as(String.class), "%" + parameter.getName() + "%"));
            }

            if (parameter.getTypes() != null && parameter.getTypes().length > 0) {
                predicates.add(root.get("mediaType").as(MediaType.class).in((Object[]) parameter.getTypes()));
            }

            if (parameter.getTags() != null && parameter.getTags().length > 0) {
                predicates.add(tagJoin.get("name").as(String.class).in((Object[]) parameter.getTags()));
            }

            if (parameter.getDateStart() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createDate").as(LocalDate.class), parameter.getDateStart()));
            }

            if (parameter.getDateEnd() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createDate").as(LocalDate.class), parameter.getDateEnd()));
            }


            return query.distinct(true).where(predicates.toArray(new Predicate[0])).getRestriction();
        };

        return homeFunnyMultiMediaRepository.findAll(sf, page.getPageable())
                .map(v -> new HomeFunnyMultiMediaDto(v.getId(), v.getName(), v.getCoverName(), v.getMediaType(), v.getCreateDate(), v.getDescription()));
    }

    public HomeFunnyMultiMediaDto findById(Long id) {
        return homeFunnyMultiMediaRepository.findById(id)
                .map(v -> new HomeFunnyMultiMediaDto(v.getId(), v.getName(), v.getCoverName(), v.getMediaType(), v.getCreateDate(), v.getDescription()))
                .orElseThrow();
    }
}
