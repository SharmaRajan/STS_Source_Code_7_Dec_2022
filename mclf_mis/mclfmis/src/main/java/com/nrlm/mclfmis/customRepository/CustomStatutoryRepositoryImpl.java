package com.nrlm.mclfmis.customRepository;

import java.util.ArrayList;
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
import com.nrlm.mclfmis.Entity.Financial;
import com.nrlm.mclfmis.Entity.InternalControlEntity;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Entity.StatutoryEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.customEntity.StatutoryCustomEntity;

@Repository
public class CustomStatutoryRepositoryImpl implements CustomStatutoryRepository{


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public StatutoryEntity getStatutoryByIdAndClfCodeAndFinYearId(Long id,String clfCode, Integer finYearId) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<StatutoryEntity> query = criteriaBuilder.createQuery(StatutoryEntity.class);
		final Root<StatutoryEntity> root = query.from(StatutoryEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(id != null && id != 0) {
			
			predicates.add(criteriaBuilder.equal(root.get("id"), id));
		}

		if(clfCode != null && !clfCode.isEmpty()) {
			
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
		
		if(finYearId != null && finYearId != 0) {
			
			predicates.add(criteriaBuilder.equal(root.get("finYearId"), finYearId));
		}
		
		
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<StatutoryEntity> typedQuery = entityManager.createQuery(query);

		List<StatutoryEntity> statList = typedQuery.getResultList();
		if(statList == null || statList.size() == 0) {
			return null;
		}
		return statList.get(0);

	}

	@Override
	public List<StatutoryCustomEntity> getStatutoryList(ComplianceFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel) {
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<StatutoryCustomEntity> query = criteriaBuilder.createQuery(StatutoryCustomEntity.class);
		final Root<StatutoryEntity> root = query.from(StatutoryEntity.class);
		
		Join<ClfMaster, StatutoryEntity> clf = root.join("clfmaster", JoinType.INNER);
		Join<Block, ClfMaster> bl = clf.join("block", JoinType.INNER);
		Join<District, Block> dis = bl.join("district", JoinType.INNER);
		Join<District, State> state = dis.join("state", JoinType.INNER);
		Join<Financial, StatutoryEntity> fin = root.join("financial", JoinType.INNER);
		
		List<Predicate> predicates = new ArrayList<>();
        
		if (filterdto.getStateCode() != null && !filterdto.getStateCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(state.get("stateCode"), filterdto.getStateCode()));
		}
		if (filterdto.getDistrictCode() != null && !filterdto.getDistrictCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(dis.get("districtCode"), filterdto.getDistrictCode()));
		}
		if (filterdto.getBlockCode() != null && !filterdto.getBlockCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(bl.get("blockCode"), filterdto.getBlockCode()));
		}
		if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(clf.get("clfCode"), filterdto.getClfCode()));
		}
		if (filterdto.getClfName() != null && !filterdto.getClfName().isEmpty()) {
			 predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
		}

		if (filterdto.getId() != null && filterdto.getId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
		}
		if (filterdto.getStatus() != null && filterdto.getStatus() != 0) {
				predicates.add(criteriaBuilder.equal(root.get("complianceStatus"), filterdto.getStatus()));
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
		
		query.multiselect(root.get("id"),state.get("stateCode"),state.get("stateName"),dis.get("districtCode"),dis.get("districtName"),
				
				bl.get("blockCode"),bl.get("blockName"), clf.get("clfCode"),clf.get("clfName"),root.get("finYearId"),root.get("isExtAudit"),

				root.get("lastExtAuditDate"),root.get("isAgbmConduct"),root.get("lastAgbmConductDate"),root.get("lastAgbmParticipant"),
				
				root.get("isLdrRotationDue"),root.get("lastLdrRotationDate"),root.get("isItReturns"),root.get("itReturnsFinYearId"),
				
				root.get("lastItReturnsDate"),root.get("isAnnualReturns"),root.get("annualReturnsFinYearId"),root.get("annualReturnsDate"),
				
				root.get("isInputLicense"),root.get("isRating"),root.get("ratingAgency"),root.get("lastRatingDate"),root.get("govRating"),
				
				root.get("finPerformRating"),root.get("mgmtRating"),root.get("overallRating"),
				
				root.get("createdBy"),root.get("updatedBy"),root.get("createdAt"),root.get("updatedAt"),root.get("status"),
				
				criteriaBuilder.selectCase(root.get("complianceStatus"))
				.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
				.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
				.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
				.otherwise(ProjectConstants.IN_PROGRESS_TEXT), fin.get("fnclYear"));
		
		query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")),
				criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(clf.get("clfName")));

		TypedQuery<StatutoryCustomEntity> typedQuery = entityManager.createQuery(query);

		typedQuery.setFirstResult(pageModel.getOffset());
		typedQuery.setMaxResults(pageModel.getLimit());
		
		return typedQuery.getResultList();

	}

	@Override
	public Long getStatutoryCount(ComplianceFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		final Root<StatutoryEntity> root = query.from(StatutoryEntity.class);

		Join<ClfMaster, StatutoryEntity> clf = root.join("clfmaster", JoinType.INNER);
		Join<Block, ClfMaster> bl = clf.join("block", JoinType.INNER);
		Join<District, Block> dis = bl.join("district", JoinType.INNER);
		Join<District, State> state = dis.join("state", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getStateCode() != null && !filterdto.getStateCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(state.get("stateCode"), filterdto.getStateCode()));
		}
		if (filterdto.getDistrictCode() != null && !filterdto.getDistrictCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(dis.get("districtCode"), filterdto.getDistrictCode()));
		}
		if (filterdto.getBlockCode() != null && !filterdto.getBlockCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(bl.get("blockCode"), filterdto.getBlockCode()));
		}
		if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(clf.get("clfCode"), filterdto.getClfCode()));
		}
		if (filterdto.getClfName() != null && !filterdto.getClfName().isEmpty()) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
		}
		if (filterdto.getId() != null && filterdto.getId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
		}
		if (filterdto.getStatus() != null && filterdto.getStatus() != 0) {
			
				predicates.add(criteriaBuilder.equal(root.get("complianceStatus"), filterdto.getStatus()));
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
	public StatutoryEntity getLatestStatutoryByClfCode(String clfCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<StatutoryEntity> query = criteriaBuilder.createQuery(StatutoryEntity.class);
		final Root<StatutoryEntity> root = query.from(StatutoryEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(clfCode != null && !clfCode.isEmpty()) {
			
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.desc(root.get("id")));
		
		TypedQuery<StatutoryEntity> typedQuery = entityManager.createQuery(query);
		
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(1);

		List<StatutoryEntity> statList = typedQuery.getResultList();
		if(statList == null || statList.size() == 0) {
			return null;
		}
		return statList.get(0);

	}

	@Override
	public StatutoryEntity getLastSubmitStatutoryByClfCode(String clfCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<StatutoryEntity> query = criteriaBuilder.createQuery(StatutoryEntity.class);
		final Root<StatutoryEntity> root = query.from(StatutoryEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(criteriaBuilder.equal(root.get("complianceStatus"), ProjectConstants.COMPLETED));
		
		if(clfCode != null && !clfCode.isEmpty()) {
			
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.desc(root.get("id")));
		
		TypedQuery<StatutoryEntity> typedQuery = entityManager.createQuery(query);
		
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(1);

		List<StatutoryEntity> statList = typedQuery.getResultList();
		if(statList == null || statList.size() == 0) {
			return null;
		}
		return statList.get(0);

	}

	
}
