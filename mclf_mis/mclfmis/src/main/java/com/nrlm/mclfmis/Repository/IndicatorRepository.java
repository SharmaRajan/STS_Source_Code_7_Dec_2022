package com.nrlm.mclfmis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.Indicator;

@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Integer>, JpaSpecificationExecutor<Indicator> {

	@Query("Select ind FROM Indicator ind INNER JOIN SectionEntity se on se.id = ind.sectionId where se.tabType = :tabid")
	public List<Indicator> findTabWisedata(Integer tabid);

	@Query("Select ind FROM Indicator ind where sectionId = :secid")
	public List<Indicator> findAllBySection(Integer secid);

	@Query(value = "Select aoe from AnswerOptionEntity aoe where aoe.tableName = ?1 and aoe.shortKey = ?2 and aoe.status = 1")
	List<AnswerOptionEntity> getAnswerOptionMasterList(String tableName, String shortKey);

	public Page<Indicator> findAll(Specification<Indicator> spec, Pageable pageable);
	public Indicator findByIndctrName(String indctrName);
	public Indicator findFirstBySectionId(Integer sectionId);
	public Indicator findByIndctrNameAndSectionId(String indctrName,Integer sectionId);
	
	@Query(value="Select ind from Indicator ind where ind.sectionId = ?1 and LOWER(ind.indctrName) = ?2 and ind.status = 1")
	public Optional<Indicator> findBySectionIdAndIndctrName(Long sectionId,String indctrName);
	
	public Optional<Indicator> findById(Integer indctrId);

	@Query(value="Select max(ind.sequence) as ind from Indicator ind where  ind.sectionId = ?1")
	public Integer findMaxSeqBySection(Long sectionId);
}
