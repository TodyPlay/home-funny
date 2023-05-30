package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyMediaTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface HomeFunnyMediaTagRepository extends JpaRepository<HomeFunnyMediaTag, Long> {
    boolean existsByName(String name);

    Optional<HomeFunnyMediaTag> findByName(String name);

    List<HomeFunnyMediaTag> findByNameIn(Collection<String> names);
}
