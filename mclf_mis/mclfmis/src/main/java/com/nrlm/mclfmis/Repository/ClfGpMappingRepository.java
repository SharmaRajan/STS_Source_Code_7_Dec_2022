package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.Clfgpmapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClfGpMappingRepository extends JpaRepository<Clfgpmapping, Integer> {

//    @Query(value = "select cg from Clfgpmapping cg where cg.clf_code = ?1")
//    public List<Clfgpmapping> findByClfgpmappings(String clf_code);
}
