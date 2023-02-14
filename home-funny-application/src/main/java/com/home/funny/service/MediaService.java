package com.home.funny.service;

import com.home.funny.dto.PageableDTO;
import com.home.funny.dto.media.MediaQueryDTO;
import com.home.funny.model.HomeFunnyMediaTag;
import com.home.funny.model.HomeFunnyMultiMedia;
import com.home.funny.repository.HomeFunnyMediaTagRepository;
import com.home.funny.repository.HomeFunnyMultiMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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

    public Page<HomeFunnyMultiMedia> findMedias(MediaQueryDTO query, PageableDTO page) {
        Specification<HomeFunnyMultiMedia> sf = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(query.getName())) {
                Predicate nameP = cb.like(root.get("name").as(String.class), query.getName());
                predicates.add(nameP);
            }

            Predicate and = cb.and(predicates.toArray(new Predicate[0]));

            cq.where(and);

            return cq.getRestriction();
        };

        return homeFunnyMultiMediaRepository.findAll(sf, page.getPageRequest());
    }

}
