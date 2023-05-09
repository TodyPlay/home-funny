package com.home.funny.service;

import com.home.funny.model.converter.HomeFunnyMediaDetailMapper;
import com.home.funny.model.converter.HomeFunnyMultiMediaMapper;
import com.home.funny.model.dto.HomeFunnyMediaDetailDto;
import com.home.funny.model.dto.HomeFunnyMultiMediaDto;
import com.home.funny.model.dto.paging.PageableDTO;
import com.home.funny.model.dto.query.MediaQueryDTO;
import com.home.funny.model.po.*;
import com.home.funny.repository.HomeFunnyMediaDetailRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class MediaService {
    @Autowired
    private HomeFunnyMediaTagRepository tagRepository;
    @Autowired
    private HomeFunnyMultiMediaRepository homeFunnyMultiMediaRepository;
    @Autowired
    private HomeFunnyMediaDetailRepository homeFunnyMediaDetailRepository;
    @Autowired
    private HomeFunnyMultiMediaMapper homeFunnyMultiMediaMapper;
    @Autowired
    private HomeFunnyMediaDetailMapper homeFunnyMediaDetailMapper;

    public List<HomeFunnyMediaTag> mediaTags() {
        return tagRepository.findAll();
    }

    public Page<HomeFunnyMultiMediaDto> findMedias(MediaQueryDTO parameter, PageableDTO page) {
        Specification<HomeFunnyMultiMedia> sf = (root, query, cb) -> {
            Join<HomeFunnyMultiMedia, HomeFunnyMediaTagMapping> mapingJoin = root.join(HomeFunnyMultiMedia_.tagMappings, JoinType.LEFT);
            mapingJoin.on(cb.equal(root.get(HomeFunnyMultiMedia_.id), mapingJoin.get(HomeFunnyMediaTagMapping_.multiMedia)));

            Join<HomeFunnyMediaTagMapping, HomeFunnyMediaTag> tagJoin = mapingJoin.join(HomeFunnyMediaTagMapping_.mediaTag, JoinType.LEFT);
            tagJoin.on(cb.equal(mapingJoin.get(HomeFunnyMediaTagMapping_.mediaTag), tagJoin.get(HomeFunnyMediaTag_.id)));

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(parameter.getName())) {
                predicates.add(cb.like(root.get(HomeFunnyMultiMedia_.name), "%" + parameter.getName() + "%"));
            }

            if (parameter.getTypes() != null && parameter.getTypes().length > 0) {
                predicates.add(root.get(HomeFunnyMultiMedia_.mediaType).in((Object[]) parameter.getTypes()));
            }

            if (parameter.getTags() != null && parameter.getTags().length > 0) {
                predicates.add(tagJoin.get(HomeFunnyMediaTag_.name).in((Object[]) parameter.getTags()));
            }

            if (parameter.getDateStart() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get(HomeFunnyMultiMedia_.createDate), parameter.getDateStart()));
            }

            if (parameter.getDateEnd() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get(HomeFunnyMultiMedia_.createDate), parameter.getDateEnd()));
            }


            return query.distinct(true).where(predicates.toArray(new Predicate[0])).getRestriction();
        };

        return homeFunnyMultiMediaRepository.findAll(sf, page.getPageable()).map(homeFunnyMultiMediaMapper::toDto);
    }

    public Page<HomeFunnyMultiMediaDto> findMedias2(MediaQueryDTO parameter, PageableDTO page) {
        return null;
    }

    public HomeFunnyMultiMediaDto findById(Long id) {
        return homeFunnyMultiMediaRepository.findById(id).map(homeFunnyMultiMediaMapper::toDto).orElseThrow();
    }

    public List<HomeFunnyMediaDetailDto> mediaDetailByMediaId(Long id) {
        List<HomeFunnyMediaDetail> mediaDetails = homeFunnyMediaDetailRepository.findByMultiMediaId(id);
        return mediaDetails.stream().map(homeFunnyMediaDetailMapper::toDto).toList();
    }
}
