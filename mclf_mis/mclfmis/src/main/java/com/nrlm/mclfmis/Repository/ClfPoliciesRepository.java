package com.nrlm.mclfmis.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfPoliciesEntity;



@Repository
public interface ClfPoliciesRepository extends JpaRepository<ClfPoliciesEntity, Long>,JpaSpecificationExecutor<ClfPoliciesEntity>{

	Page<ClfPoliciesEntity> findAll(Specification<ClfPoliciesEntity> spec,Pageable pageable);
}