package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.StatutoryEntity;

@Repository
public interface StatutoryRepository  extends JpaRepository<StatutoryEntity, Long>{

}
