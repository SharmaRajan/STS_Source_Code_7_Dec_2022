package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.GpCutOffMonthlyMapping;

@Repository
public interface GpCutOffMonthlyMappingRepository extends JpaRepository<GpCutOffMonthlyMapping, Long> {
	
	public Optional<GpCutOffMonthlyMapping> findByCutOffIdAndIndctrId(Long long1, Long long2);

	public void deleteAllByCutOffIdAndSectionId(Long cutOffId, Long skip);

	public void deleteAllByCutOffIdAndIndctrId(Long cutOffId, Long indctrId);

}
