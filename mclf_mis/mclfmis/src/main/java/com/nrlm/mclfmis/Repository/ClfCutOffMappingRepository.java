package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfCutOffMapping;

@Repository
public interface ClfCutOffMappingRepository extends JpaRepository<ClfCutOffMapping, Long> {

	Optional<ClfCutOffMapping> findByCutOffIdAndIndctrId(Long cutOffId, Long indctrId);

	void deleteAllByCutOffIdAndIndctrId(Long cutOffId, Long indctrId);
	
	void deleteAllByCutOffIdAndSectionId(Long cutOffId, Long sectionId);
}
