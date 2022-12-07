package com.nrlm.mclfmis.customRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Entity.SubCommCSTEntity;
import com.nrlm.mclfmis.Entity.SubCommDetailsEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.SubCommCSTFilterRequest;
import com.nrlm.mclfmis.customEntity.SubCommCSTCustomEntity;
import com.nrlm.mclfmis.customEntity.SubCommDetCustomEntity;

@Repository
public class SubCommCSTCustomRepositoryImpl implements SubCommCSTCustomRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<SubCommCSTCustomEntity> getCSTList(SubCommCSTFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<SubCommCSTCustomEntity> query = criteriaBuilder.createQuery(SubCommCSTCustomEntity.class);
		final Root<SubCommCSTEntity> root = query.from(SubCommCSTEntity.class);
		
		//final Root<ClfMaster> root = query.from(ClfMaster.class);
		Join<ClfMaster, SubCommCSTEntity> clf = root.join("clfmaster", JoinType.INNER);
		//final Root<StaffCustomEntity> temp = query.from(StaffCustomEntity.class); //new
		
		Join<Block, ClfMaster> bl = clf.join("block", JoinType.INNER);
		Join<District, Block> dis = bl.join("district", JoinType.INNER);
		Join<District, State> state = dis.join("state", JoinType.INNER);
		
		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getStateCode() != null) {
			predicates.add(criteriaBuilder.equal(state.get("stateCode"), filterdto.getStateCode()));
		}
		if (filterdto.getDistrictCode() != null) {
			predicates.add(criteriaBuilder.equal(dis.get("districtCode"), filterdto.getDistrictCode()));
		}
		if (filterdto.getBlockCode() != null) {
			predicates.add(criteriaBuilder.equal(bl.get("blockCode"), filterdto.getBlockCode()));
		}
		if (filterdto.getClfCode() != null) {
			predicates.add(criteriaBuilder.equal(clf.get("clfCode"), filterdto.getClfCode()));
		}
		if (filterdto.getClfName() != null) {
			 predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
		}
		if (filterdto.getStatus() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("commStatus"), filterdto.getStatus()));
		}
		if (filterdto.getId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
		}
		

		
		if (levelName.equals(ProjectConstants.STATE)) {
			predicates.add(state.get("stateCode").in(locations));
		} else if (levelName.equals(ProjectConstants.DISTRICT)) {
			predicates.add(dis.get("districtCode").in(locations));
		} else if (levelName.equals(ProjectConstants.BLOCK)) {
			predicates.add(bl.get("blockCode").in(locations));
		} else if (levelName.equals(ProjectConstants.CLF)) {
			predicates.add(clf.get("clfCode").in(locations));
		}
		
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		query.multiselect(root.get("id"), clf.get("clfCode"), clf.get("clfName"),state.get("stateCode"), state.get("stateName"),
				dis.get("districtCode"),dis.get("districtName"),  bl.get("blockCode"),  bl.get("blockName"), root.get("cstFormed"),
				root.get("totCstMemb"), root.get("createdBy"), root.get("updatedBy"),
				root.get("createdAt"), root.get("updatedAt"),root.get("status"),
				criteriaBuilder.selectCase(root.get("commStatus"))
				.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
				.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
				.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
				.otherwise(ProjectConstants.PENDING_TEXT));
		
		query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")),
				criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(clf.get("clfName")));

		TypedQuery<SubCommCSTCustomEntity> typedQuery = entityManager.createQuery(query);

		typedQuery.setFirstResult(pageModel.getOffset());
		typedQuery.setMaxResults(pageModel.getLimit());
		
		return typedQuery.getResultList();
	}

	@Override
	public Long getCSTCount(SubCommCSTFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel) {
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		final Root<SubCommCSTEntity> root = query.from(SubCommCSTEntity.class);

		Join<ClfMaster, SubCommCSTEntity> clf = root.join("clfmaster", JoinType.INNER);
		Join<Block, ClfMaster> bl = clf.join("block", JoinType.INNER);
		Join<District, Block> dis = bl.join("district", JoinType.INNER);
		Join<District, State> state = dis.join("state", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getStateCode() != null) {
			predicates.add(criteriaBuilder.equal(state.get("stateCode"), filterdto.getStateCode()));
		}
		if (filterdto.getDistrictCode() != null) {
			predicates.add(criteriaBuilder.equal(dis.get("districtCode"), filterdto.getDistrictCode()));
		}
		if (filterdto.getBlockCode() != null) {
			predicates.add(criteriaBuilder.equal(bl.get("blockCode"), filterdto.getBlockCode()));
		}
		if (filterdto.getClfCode() != null) {
			predicates.add(criteriaBuilder.equal(clf.get("clfCode"), filterdto.getClfCode()));
		}
		if (filterdto.getClfName() != null) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
		}
		if (filterdto.getStatus() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("commStatus"), filterdto.getStatus()));
		}
		if (filterdto.getId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
		}

		

		if (levelName.equals(ProjectConstants.NATIONAL)) {

		} else if (levelName.equals(ProjectConstants.STATE)) {
			predicates.add(state.get("stateCode").in(locations));
		} else if (levelName.equals(ProjectConstants.DISTRICT)) {
			predicates.add(dis.get("districtCode").in(locations));
		} else if (levelName.equals(ProjectConstants.BLOCK)) {
			predicates.add(bl.get("blockCode").in(locations));
		} else if (levelName.equals(ProjectConstants.CLF)) {
			predicates.add(clf.get("clfCode").in(locations));
		}

		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		query.multiselect(criteriaBuilder.count(root.get("id")));
		TypedQuery<Long> typedQuery = entityManager.createQuery(query);
		return typedQuery.getSingleResult();
	}

	@Override
	public SubCommCSTEntity getCSTByClfCode(Long id, String clfCode) {
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<SubCommCSTEntity> query = criteriaBuilder.createQuery(SubCommCSTEntity.class);
		final Root<SubCommCSTEntity> root = query.from(SubCommCSTEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();

		if(id != null && id != 0) {
			predicates.add(criteriaBuilder.notEqual(root.get("id"), id));
		}
	
		if(clfCode != null && !clfCode.isEmpty()) {
			
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
		
		
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<SubCommCSTEntity> typedQuery = entityManager.createQuery(query);

		List<SubCommCSTEntity> SubCSTList = typedQuery.getResultList();
		if(SubCSTList == null || SubCSTList.size() == 0) {
			return null;
		}
		return SubCSTList.get(0);
	}

}
