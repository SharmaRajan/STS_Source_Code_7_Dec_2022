package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.ClfVoMapping;
import com.nrlm.mclfmis.Entity.Clfgpmapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClfVoMappingRepository extends JpaRepository<ClfVoMapping, Integer> {

//    @Query(value = "select cv from ClfVoMapping cv where cv.clf_code = ?1")
//    public List<ClfVoMapping> findByClfVoMappings(String clf_code);
}
