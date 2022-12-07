package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.CmtcEntity;

@Repository
public interface CmtcRepository extends JpaRepository<CmtcEntity, Long> {

}
