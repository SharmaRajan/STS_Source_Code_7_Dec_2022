package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, String>, JpaSpecificationExecutor<District> {

    @Query("Select dt FROM District dt where stateCode IN (:locationcode)")
    public Page<District> findAllByStatecode(List<String> locationcode, Pageable pageable);

    @Query("Select dt FROM District dt where districtCode IN (:discode)")
    public Page<District> findAllBydiscode(List<String> discode, Pageable pageable);

    public Page<District> findAll(Specification<District> spec, Pageable pageable);
}
