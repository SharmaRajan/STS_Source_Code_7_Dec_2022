package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.GpCutOffEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyEntity;
import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustom;

@Repository
public interface GpCutOffMonthlyRepository extends JpaRepository<GpCutOffMonthlyEntity, Long>,ClfCutOffMonthlyRepositoryCustom{

}
