package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.GpCutOffMapping;

@Repository
public interface GpCutOffMappingRepository extends JpaRepository<GpCutOffMapping, Long> {

    Optional<GpCutOffMapping> findByCutOffIdAndIndctrId(Long id, Long indctrId);

    void deleteAllByCutOffIdAndIndctrId(Long cutOffId, Long indctrId);

	void deleteAllByCutOffIdAndSectionId(Long id, Long sectionId);

}
