package com.home.funny.repository;

import com.home.funny.model.HomeFunnyStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyFileRepository extends JpaRepository<HomeFunnyStorage, Long> {
}