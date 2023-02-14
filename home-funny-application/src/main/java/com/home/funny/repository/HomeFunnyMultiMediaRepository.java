package com.home.funny.repository;

import com.home.funny.model.HomeFunnyMultiMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyMultiMediaRepository extends JpaRepository<HomeFunnyMultiMedia, Long> {

}
