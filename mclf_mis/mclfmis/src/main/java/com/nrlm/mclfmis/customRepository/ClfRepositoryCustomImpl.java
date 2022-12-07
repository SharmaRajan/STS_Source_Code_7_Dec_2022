package com.nrlm.mclfmis.customRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.customEntity.ClfMasterEntity;

@Repository
public class ClfRepositoryCustomImpl implements ClfRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public List<ClfMasterEntity> findByClfcode(String clfcode) {
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<ClfMasterEntity> query = criteriaBuilder.createQuery(ClfMasterEntity.class);
		final Root<ClfMaster> root = query.from(ClfMaster.class);
		Join<Block, ClfMaster> bl = root.join("block", JoinType.INNER);
		Join<District, Block> dis = bl.join("district", JoinType.INNER);
		Join<District, State> state = dis.join("state", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfcode));

		query.multiselect(state.get("stateCode"),state.get("stateName"),dis.get("districtCode"),dis.get("districtName"),
				bl.get("blockCode"),bl.get("blockName"),root.get("clfCode"),root.get("clfName"),root.get("formationDate"));
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		TypedQuery<ClfMasterEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
	
	
}
