package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {

    Page<Content> findAll(Specification<Content> spec, Pageable pageable);

    Optional<Content> findOneByTitle(String title);

    @Query(value = "Select max(se.ranking) as rank from Content se where  se.moduleId = :moduleId AND se.subModuleId=:subModuleId")
    Integer findMaxSeqByTabId(Integer moduleId, Integer subModuleId);

    @Query(nativeQuery = true, value = "Select distinct(author) as author from content where module_id = :moduleId")
    List<Object[]> findByAuhtor(Integer moduleId);

    @Query(nativeQuery = true, value = "Select distinct(source) as source from content where module_id = :moduleId")
    List<Object[]> findBySource(Integer moduleId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE content  SET ranking = ranking+1 WHERE module_id = :moduleId AND sub_module_id =:subModuleId AND ranking >= :rank AND id != :contentId")
    int updateAllRanking(Integer moduleId, Integer subModuleId, Integer rank, Long contentId);


}
