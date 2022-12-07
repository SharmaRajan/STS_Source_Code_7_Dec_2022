package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOptionEntity, String> {
	
	@Query(value = "Select aoe from AnswerOptionEntity aoe where aoe.tableName = ?1 and aoe.shortKey = ?2 and aoe.status = 1 order by aoe.sequence")
	List<AnswerOptionEntity> findAllBykeyandtable(String tableName, String shortKey);

	@Query(value = "Select aoe from AnswerOptionEntity aoe where aoe.tableName = ?1 and aoe.status = 1 order by aoe.shortKey, aoe.sequence")
	List<AnswerOptionEntity> findAllBytable(String tableName);
	
	@Query(value = "Select aoe from AnswerOptionEntity aoe where aoe.tableName = ?1 and aoe.shortKey = ?2 and aoe.optionId = ?3 and aoe.status = 1")
	Optional<AnswerOptionEntity> findFirstBykeyandtableanoptionid(String tableName, String shortKey,Integer optionid);

}
