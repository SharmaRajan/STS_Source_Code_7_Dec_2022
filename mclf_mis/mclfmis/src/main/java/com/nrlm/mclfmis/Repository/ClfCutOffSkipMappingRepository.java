package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.CutOffSkipMapping;

@Repository
public interface ClfCutOffSkipMappingRepository extends JpaRepository<ClfCutOffSkipMapping, Long> {

	Optional<CutOffSkipMapping> findByCutOffIdAndSectionId(Long cutOffId, Long sectionId);


}
