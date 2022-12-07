package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.entity.Module;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {

    Optional<Module> findByModuleName(String moduleName);
    Page<Module> findAll(Specification<Module> spec, Pageable pageable);

}
