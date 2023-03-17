package com.home.funny.repository;

import com.home.funny.model.po.HomeFunnyUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeUserDetailsRepository extends JpaRepository<HomeFunnyUserDetail, Long> {

}
