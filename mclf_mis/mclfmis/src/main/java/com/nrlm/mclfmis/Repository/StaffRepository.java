package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long>{

}
