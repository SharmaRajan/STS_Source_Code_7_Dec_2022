package com.nrlm.mclfmis.Repository;

import com.nrlm.mclfmis.Entity.State;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, String> {
    
	public Page<State> findAll(Pageable pageable);
	public List<State> findByStateCode(String state_code);
}
