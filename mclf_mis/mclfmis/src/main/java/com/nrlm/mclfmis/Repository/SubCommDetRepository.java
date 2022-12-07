package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.SubCommDetailsEntity;

@Repository
public interface SubCommDetRepository extends JpaRepository<SubCommDetailsEntity, Long>{

}
