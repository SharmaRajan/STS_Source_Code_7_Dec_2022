package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.VoCutOffMonthlyMapping;

@Repository
public interface VoCutOffMonthlyMappingRepository extends JpaRepository<VoCutOffMonthlyMapping, Long>{
	//public Optional<VoCutOffMonthlyMapping> findByCutOffIdAndIndctrId(Integer cutOffId, Integer indctrId);

	public Optional<VoCutOffMonthlyMapping> findByCutOffIdAndIndctrId(Long voCutOffId, Long indctrId);

	public void deleteAllByCutOffIdAndSectionId(Long cutOffId, Long skip);

	public void deleteAllByCutOffIdAndIndctrId(Long cutOffId, Long indctrId);

}
