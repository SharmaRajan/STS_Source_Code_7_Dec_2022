package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfCutOffEntity;

@Repository
public interface ClfCutOffRepository extends JpaRepository<ClfCutOffEntity, String> {

	public Page<ClfCutOffEntity> findAll(Specification<ClfCutOffEntity> spec, Pageable page);

	public Optional<ClfCutOffEntity> findByClfCode(String clfCode);

}
