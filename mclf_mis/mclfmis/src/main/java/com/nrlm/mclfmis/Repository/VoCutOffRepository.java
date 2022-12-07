package com.nrlm.mclfmis.Repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.VoCutOffEntity;

@Repository
public interface VoCutOffRepository extends JpaRepository<VoCutOffEntity, Long> {


	List<VoCutOffEntity> findAll(Specification<VoCutOffEntity> spec);

}
