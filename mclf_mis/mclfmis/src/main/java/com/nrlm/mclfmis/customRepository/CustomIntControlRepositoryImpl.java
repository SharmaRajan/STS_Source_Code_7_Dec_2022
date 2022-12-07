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
import com.nrlm.mclfmis.Entity.Financial;
import com.nrlm.mclfmis.Entity.InternalControlEntity;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;

import com.nrlm.mclfmis.customEntity.IntControlCustomEntity;

@Repository
public class CustomIntControlRepositoryImpl implements CustomIntControlRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<IntControlCustomEntity> getIntControl(ComplianceFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel) {
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<IntControlCustomEntity> query = criteriaBuilder.createQuery(IntControlCustomEntity.class);
		final Root<InternalControlEntity> root = query.from(InternalControlEntity.class);
		Join<Financial, InternalControlEntity> fin = root.join("financial", JoinType.INNER);
		//final Root<ClfMaster> root = query.from(ClfMaster.class);
		Join<ClfMaster, InternalControlEntity> clf = root.join("clfmaster", JoinType.INNER);
		
		//final Root<StaffCustomEntity> temp = query.from(StaffCustomEntity.class); //new
		
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
		if (filterdto.getFinYearId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("finYearId"), filterdto.getFinYearId()));
		}
		if (filterdto.getQrtrId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("qrtrId"), filterdto.getQrtrId()));
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
				bl.get("blockCode"),bl.get("blockName"),clf.get("clfCode"),clf.get("clfName"), root.get("finYearId"),
				root.get("qrtrId"), root.get("intAudit"),  root.get("grb"),  root.get("gbMeeting"),
				root.get("status"),root.get("createdAt"), root.get("updatedAt"),
				root.get("createdBy"), root.get("updatedBy"),
				criteriaBuilder.selectCase(root.get("complianceStatus"))
				.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
				.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
				.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
				.otherwise(ProjectConstants.IN_PROGRESS_TEXT), 
				fin.get("fnclYear"),
				criteriaBuilder.selectCase(root.get("qrtrId"))
				.when(ProjectConstants.Q1, ProjectConstants.Q1_TEXT)
				.when(ProjectConstants.Q2, ProjectConstants.Q2_TEXT)
				.when(ProjectConstants.Q3, ProjectConstants.Q3_TEXT)
				.when(ProjectConstants.Q4, ProjectConstants.Q4_TEXT)
				.otherwise(ProjectConstants.Q1_TEXT)
				);
		
		
		query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")),
				criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(clf.get("clfName")));

		TypedQuery<IntControlCustomEntity> typedQuery = entityManager.createQuery(query);

		typedQuery.setFirstResult(pageModel.getOffset());
		typedQuery.setMaxResults(pageModel.getLimit());
		
		return typedQuery.getResultList();
	}

	@Override
	public Long getIntControlCount(ComplianceFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		final Root<InternalControlEntity> root = query.from(InternalControlEntity.class);

		Join<ClfMaster, InternalControlEntity> clf = root.join("clfmaster", JoinType.INNER);
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
		if (filterdto.getFinYearId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("finYearId"), filterdto.getFinYearId()));
		}
		if (filterdto.getQrtrId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("qrtrId"), filterdto.getQrtrId()));
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
	public InternalControlEntity getIntControlByClfCodeAndFinYearIdAndQrtrId(Long id, String clfCode,Integer yearId,Integer qrtrId) {
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<InternalControlEntity> query = criteriaBuilder.createQuery(InternalControlEntity.class);
		final Root<InternalControlEntity> root = query.from(InternalControlEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();

		/*
		 * if(id != null && id != 0) {
		 * predicates.add(criteriaBuilder.notEqual(root.get("id"), id)); }
		 */
	
		if(clfCode != null && !clfCode.isEmpty()) {
			
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
		if(yearId != null && yearId != 0) {
					
					predicates.add(criteriaBuilder.equal(root.get("finYearId"), yearId));
				}
		if(qrtrId != null && qrtrId != 0) {
			
			predicates.add(criteriaBuilder.equal(root.get("qrtrId"), qrtrId));
		}
		
		
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<InternalControlEntity> typedQuery = entityManager.createQuery(query);

		List<InternalControlEntity> intCtrlList = typedQuery.getResultList();
		
		if(intCtrlList == null || intCtrlList.size() == 0) {
			return null;
		}
		return intCtrlList.get(0);

	}

	@Override
	public InternalControlEntity getLatestIntControlByClfCode(String clfCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<InternalControlEntity> query = criteriaBuilder.createQuery(InternalControlEntity.class);
		final Root<InternalControlEntity> root = query.from(InternalControlEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(clfCode != null && !clfCode.isEmpty()) {
			
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.desc(root.get("finYearId")),criteriaBuilder.desc(root.get("qrtrId")));
		
		TypedQuery<InternalControlEntity> typedQuery = entityManager.createQuery(query);
		
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(1);

		List<InternalControlEntity> intCtrlList = typedQuery.getResultList();
		if(intCtrlList == null || intCtrlList.size() == 0) {
			return null;
		}
		return intCtrlList.get(0);

	}
	

}
