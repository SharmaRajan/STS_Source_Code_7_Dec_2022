package com.nrlm.mclfmis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Request.SectionRequest;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long>,JpaSpecificationExecutor<SectionEntity> {

	@Query(value="Select aoe from AnswerOptionEntity aoe where aoe.tableName = ?1 and aoe.shortKey = ?2 and aoe.status = 1")
	List<AnswerOptionEntity> getSectionTabList(String tableName, String shortKey);

	@Query(value="Select se from SectionEntity se where se.tabType = ?1 and se.status = 1 order by se.sequence")
	List<SectionEntity> getSectionsByTabId(Integer tabId);
	
	@Query(value="Select se from SectionEntity se where se.tabType = ?1 and se.sectionStatus = 1 and se.status = 1 order by se.sequence")
	List<SectionEntity> getSectionsSequenceByTabId(Integer tabId);
	
	@Query(value="Select se from SectionEntity se where se.id = ?1 and se.status = 1  order by se.sequence")
	List<SectionEntity> getSectionsBySectionId(Long l);

	@Query(value="Select se from SectionEntity se where LOWER(se.sectionName) = ?1 and se.tabType = ?2 and se.status = 1")
	Optional<SectionEntity> findBySectionNameAndTabId(String sectionName, Integer tabId);

	@Query(value="Select max(se.sequence) as seq from SectionEntity se where  se.tabType = ?1")
	Integer findMaxSeqByTabId(Integer tabId);
	
	Page<SectionEntity> findAll(Specification<SectionEntity> spec,Pageable pageable);

}
