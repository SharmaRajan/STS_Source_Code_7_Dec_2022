package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.ClfBasicProfile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClfBasicProfileRepository extends JpaRepository<ClfBasicProfile, Integer> {

		public Optional<ClfBasicProfile> findByClfCode(String clfCode);
	
		public Page<ClfBasicProfile> findAll(Specification<ClfBasicProfile> spec, Pageable pageable);

		public List<ClfBasicProfile> findByPan(String pan);
	
		public List<ClfBasicProfile> findByTan(String tan);
	
		public List<ClfBasicProfile> findByGstin(String gstin);
}
