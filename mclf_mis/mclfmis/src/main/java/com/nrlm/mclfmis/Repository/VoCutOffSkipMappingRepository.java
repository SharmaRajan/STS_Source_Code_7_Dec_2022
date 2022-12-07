package com.nrlm.mclfmis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.CutOffSkipMapping;
import com.nrlm.mclfmis.Entity.VoCutOffSkipMapping;

@Repository
public interface VoCutOffSkipMappingRepository extends JpaRepository<VoCutOffSkipMapping, Long> {

	Optional<CutOffSkipMapping> findByCutOffIdAndSectionId(Long cutOffId, Long sectionId);


}
