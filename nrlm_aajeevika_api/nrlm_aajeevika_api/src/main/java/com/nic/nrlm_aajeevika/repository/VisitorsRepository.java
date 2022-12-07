package com.nic.nrlm_aajeevika.repository;


import com.nic.nrlm_aajeevika.entity.Visitors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitorsRepository extends JpaRepository<Visitors, Long>, JpaSpecificationExecutor<Visitors> {

    Page<Visitors> findAll(Specification<Visitors> spec, Pageable pageable);

}


