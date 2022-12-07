package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.SubCommCSTEntity;

@Repository
public interface SubCommCSTRepository extends JpaRepository<SubCommCSTEntity, Long>{

}
