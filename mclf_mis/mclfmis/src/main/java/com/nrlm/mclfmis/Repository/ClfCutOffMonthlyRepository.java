package com.nrlm.mclfmis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyEntity;
import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustom;

@Repository
public interface ClfCutOffMonthlyRepository
		extends JpaRepository<ClfCutOffMonthlyEntity, Long>, ClfCutOffMonthlyRepositoryCustom {

	public Page<ClfCutOffMonthlyEntity> findAll(Specification<ClfCutOffMonthlyEntity> spec, Pageable page);

	public Optional<ClfCutOffMonthlyEntity> findByClfCode(String clfCode);
	
	public Optional<ClfCutOffMonthlyEntity> findByClfCodeAndCutOffYearAndCutOffMonth(String clfCode,Integer yearId,Integer monthId);



}
