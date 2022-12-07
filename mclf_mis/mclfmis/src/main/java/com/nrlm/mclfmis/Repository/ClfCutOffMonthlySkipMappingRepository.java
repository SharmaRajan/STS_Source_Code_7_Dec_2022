package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfCutOffMonthlySkipMapping;
import com.nrlm.mclfmis.Entity.CutOffMonthlySkipMapping;

@Repository
public interface ClfCutOffMonthlySkipMappingRepository extends JpaRepository<ClfCutOffMonthlySkipMapping, Long> {

	Optional<CutOffMonthlySkipMapping> findByCutOffIdAndSectionId(Long cutOffId, Long sectionId);

}
