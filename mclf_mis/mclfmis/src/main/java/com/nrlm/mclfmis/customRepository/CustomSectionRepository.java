package com.nrlm.mclfmis.customRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CustomSectionRepository {

	@PersistenceContext
	EntityManager entityManager;
}
