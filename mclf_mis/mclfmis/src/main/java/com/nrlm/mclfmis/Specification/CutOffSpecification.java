package com.nrlm.mclfmis.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.ClfVoMapping;
import com.nrlm.mclfmis.Entity.Clfgpmapping;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.GpCutOffEntity;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Entity.VoCutOffEntity;
import com.nrlm.mclfmis.Entity.VoProfile;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;

@Component
public class CutOffSpecification {
	//@PersistenceContext
	//EntityManager entityManager;
	
	//@SuppressWarnings("unchecked")
	public Specification<ClfMaster> getClfCutOff(CutOffFilterRequest filterdto,List<String> locations, String levelName) {
		
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			//final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        //final CriteriaQuery<ClfMaster> query = criteriaBuilder.createQuery(ClfMaster.class);
	        //final Root<ClfMaster> clf = query.from(ClfMaster.class);
	        
			Join<ClfMaster,ClfCutOffEntity> clfcutoff = root.join("clfcutoff",JoinType.LEFT);
			Join<Block,ClfMaster> bl = root.join("block", JoinType.INNER);
        	Join<District,Block> dis = bl.join("district", JoinType.INNER);
            Join<District,State> state = dis.join("state", JoinType.INNER);
			
            if (filterdto.getStateCode() != null) {
				predicates.add(criteriaBuilder.equal(state.get("stateCode"), filterdto.getStateCode()));
			}
			if (filterdto.getDistrictCode() != null) {
				predicates.add(criteriaBuilder.equal(dis.get("districtCode"),filterdto.getDistrictCode()));
			}
			if (filterdto.getBlockCode() != null) {
				predicates.add(criteriaBuilder.equal(bl.get("blockCode"), filterdto.getBlockCode()));
			}
			if (filterdto.getClfCode() != null) {
				predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
			}
			
			if(levelName.equals(ProjectConstants.NATIONAL)) {
				
			}
			else if (levelName.equals(ProjectConstants.STATE)) {
				predicates.add(state.get("stateCode").in(locations));
			}
			else if (levelName.equals(ProjectConstants.DISTRICT)) {
				predicates.add(dis.get("districtCode").in(locations));
			}
			else if (levelName.equals(ProjectConstants.BLOCK)) {
				predicates.add(bl.get("blockCode").in(locations));
			}
			else if (levelName.equals(ProjectConstants.CLF)) {
				predicates.add(root.get("clfCode").in(locations));
			}
			
			//query.multiselect(criteriaBuilder.construct(ClfCutOffEntity.class,clf));
			query.orderBy(criteriaBuilder.asc(clfcutoff.get("cutOffYear")),criteriaBuilder.asc(clfcutoff.get("cutOffMonth")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	/*
	 public Specification<VoCutOffEntity> getVoCutOff(String clfCode) {
	 
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			Join<VoProfile,VoCutOffEntity> vo = root.join("voprofile");
			Join<ClfVoMapping,VoProfile> clfvo = vo.join("voprofile", JoinType.INNER);
        	Join<ClfMaster,ClfVoMapping> clf = clfvo.join("clfmaster", JoinType.INNER);
           
            if (clfCode != null) {
				predicates.add(criteriaBuilder.equal(clf.get("clfCode"), clfCode));
			}
						
			query.orderBy(criteriaBuilder.asc(root.get("cutOffYear")),criteriaBuilder.asc(root.get("cutOffMonth")));
			//query.add(
			//        criteriaBuilder.selectCase().when(root.get("cutOffStatus").equals(1)), "Pending").otherwise("Draft"));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	public Specification<GpCutOffEntity> getGpCutOff(String clfCode) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			Join<Grampanchayat,GpCutOffEntity> gp = root.join("gp");
			Join<Clfgpmapping,Grampanchayat> clfgp = gp.join("gp", JoinType.INNER);
        	Join<ClfMaster,Clfgpmapping> clf = clfgp.join("clfgpmaster", JoinType.INNER);
           
            if (clfCode != null) {
				predicates.add(criteriaBuilder.equal(clf.get("clfCode"), clfCode));
			}
						
			query.orderBy(criteriaBuilder.asc(root.get("cutOffYear")),criteriaBuilder.asc(root.get("cutOffMonth")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
	*/
}
