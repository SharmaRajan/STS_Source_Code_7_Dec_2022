package com.nrlm.mclfmis.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.usermanagement.entity.LoginHistoryEntity;

@Repository
public interface LoginHistoryRepository  extends JpaRepository<LoginHistoryEntity, Long>{

}
