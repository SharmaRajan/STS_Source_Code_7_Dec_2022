package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.Month;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRepository extends JpaRepository<Month, Integer> {
	
	public Page<Month> findAll(Pageable pageable);
	
	//public List<Month> findAllByOrderByIdAsc();

	public List<Month> findAllByOrderBySequenceAsc();

	public List<Month> findByIdLessThanEqualOrderBySequenceAsc(Integer curMonth);
	
	public Optional<Month> findById(Integer curMonth);

	public List<Month> findByIdGreaterThanEqualOrderBySequenceAsc(Integer curMonth);
	
	public List<Month> findByIdBetweenOrderBySequenceAsc(Integer startMonth,Integer endMonth);

}
