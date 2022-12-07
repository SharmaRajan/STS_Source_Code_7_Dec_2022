package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.Financial;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialYearRepository extends JpaRepository<Financial, Integer> {
	public Page<Financial> findAll(Pageable pageable);

	public List<Financial> findAll();
	
	public List<Financial> findByIdGreaterThan(Integer yearId);
	
	public List<Financial> findByFnclStartDateGreaterThan(Date startDate);
	
	public Optional<Financial> findById(Integer yearId);

	public List<Financial> findByFnclStartDateGreaterThanEqual(Date startDate);

	//public List<Financial> findByFormName(String formName);
	
	//public List<Financial> findByCutOff(Integer status);
	
	//public List<Financial> findByMonthlyCutOff(Integer status);
}
