package com.home.funny.repository;

import com.home.funny.model.HomeFunnyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeFunnyFileRepository extends JpaRepository<HomeFunnyFile, Long> {
}