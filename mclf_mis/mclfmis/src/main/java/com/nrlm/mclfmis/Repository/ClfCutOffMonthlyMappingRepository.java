package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyMapping;

@Repository
public interface ClfCutOffMonthlyMappingRepository extends JpaRepository<ClfCutOffMonthlyMapping, Long> {
	//public Optional<ClfCutOffMonthlyMapping> findByCutOffIdAndIndctrId(Integer cutOffId, Integer indctrId);

	public Optional<ClfCutOffMonthlyMapping> findByCutOffIdAndIndctrId(Long id, Long indctrId);

	public void deleteAllByCutOffIdAndSectionId(Long cutOffId, Long skip);

	public void deleteAllByCutOffIdAndIndctrId(Long cutOffId, Long indctrId);

}
