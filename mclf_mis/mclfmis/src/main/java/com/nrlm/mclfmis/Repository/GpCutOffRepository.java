package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.GpCutOffEntity;

@Repository
public interface GpCutOffRepository extends JpaRepository<GpCutOffEntity, Long>{

}
