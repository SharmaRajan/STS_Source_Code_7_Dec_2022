package com.nrlm.mclfmis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.TrainingEntity;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity,Long> {

}

