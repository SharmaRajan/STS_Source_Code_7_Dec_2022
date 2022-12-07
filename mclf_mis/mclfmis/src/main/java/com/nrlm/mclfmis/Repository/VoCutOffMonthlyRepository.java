package com.nrlm.mclfmis.Repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.VoCutOffEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyEntity;
import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustom;

@Repository
public interface VoCutOffMonthlyRepository extends JpaRepository<VoCutOffMonthlyEntity, Long>,ClfCutOffMonthlyRepositoryCustom {

	List<VoCutOffMonthlyEntity> findAll(Specification<VoCutOffMonthlyEntity> spec);

}
