package com.nic.nrlm_aajeevika.repository;


import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.entity.SubModule;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubModuleRepository extends JpaRepository<SubModule, Long>, JpaSpecificationExecutor<SubModule> {

    Optional<SubModule> findBySubModuleName(String subModuleName);
    Optional<SubModule> findByModuleId(Integer moduleId);
    Optional<SubModule> findByModuleIdAndSubModuleName(Integer moduleId,String subModuleName);
    Page<SubModule> findAll(Specification<SubModule> spec, Pageable pageable);
}
