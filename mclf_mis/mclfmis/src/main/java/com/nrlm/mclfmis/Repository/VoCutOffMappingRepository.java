package com.nrlm.mclfmis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.VoCutOffMapping;

@Repository
public interface VoCutOffMappingRepository extends JpaRepository<VoCutOffMapping, Long>{

	Optional<VoCutOffMapping> findByCutOffIdAndIndctrId(Long voCutOffId, Long indctrId);
	
	void deleteAllByCutOffIdAndIndctrId(Long voCutOffId, Long indctrId);

	void deleteAllByCutOffIdAndSectionId(Long voCutOffId, Long sectionId);

}
