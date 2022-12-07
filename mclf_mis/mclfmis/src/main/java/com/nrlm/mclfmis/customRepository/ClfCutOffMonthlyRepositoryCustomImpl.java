package com.nrlm.mclfmis.customRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.ClfVoMapping;
import com.nrlm.mclfmis.Entity.Clfgpmapping;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.Financial;
import com.nrlm.mclfmis.Entity.GpCutOffEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Entity.Month;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Entity.VoCutOffEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.VoProfile;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Response.ClfBasicProfileResponse;
import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.customEntity.ClfBasicProfileCustomEntity;
import com.nrlm.mclfmis.customEntity.ClfCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.GpCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.IndicatorCustomEntity;
import com.nrlm.mclfmis.customEntity.VoCustomCutOffEntity;

@Repository
public class ClfCutOffMonthlyRepositoryCustomImpl implements ClfCutOffMonthlyRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	// For Check Monthly and year wise unique for CLf
	public ClfCutOffMonthlyEntity checkCutOffExistByClfCodeWithMonthYear(String clfCode, Integer monthId,
			Integer yearId) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<ClfCutOffMonthlyEntity> query = criteriaBuilder.createQuery(ClfCutOffMonthlyEntity.class);
		final Root<ClfCutOffMonthlyEntity> root = query.from(ClfCutOffMonthlyEntity.class);
		query.where(criteriaBuilder.equal(root.get("clfCode"), clfCode),
				criteriaBuilder.equal(root.get("cutOffMonth"), monthId),
				criteriaBuilder.equal(root.get("cutOffYear"), yearId));
		List<ClfCutOffMonthlyEntity> clfList = entityManager.createQuery(query).getResultList();
		if (clfList != null && clfList.size() != 0) {
			return clfList.get(0);
		} else {
			return null;
		}
	}

	// For Check Monthly and year wise unique for Vo

	public VoCutOffMonthlyEntity checkCutOffExistByVoCodeWithMonthYear(String voCode, Integer monthId, Integer yearId) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<VoCutOffMonthlyEntity> query = criteriaBuilder.createQuery(VoCutOffMonthlyEntity.class);
		final Root<VoCutOffMonthlyEntity> root = query.from(VoCutOffMonthlyEntity.class);
		query.where(criteriaBuilder.equal(root.get("voCode"), voCode),
				criteriaBuilder.equal(root.get("cutOffMonth"), monthId),
				criteriaBuilder.equal(root.get("cutOffYear"), yearId));
		List<VoCutOffMonthlyEntity> voList = entityManager.createQuery(query).getResultList();
		if (voList != null && voList.size() != 0) {
			return voList.get(0);
		} else {
			return null;
		}
	}
	// For Check Monthly and year wise unique for GP

	public GpCutOffMonthlyEntity checkCutOffExistByGpCodeWithMonthYear(String grampanchayatCode, Integer monthId,
			Integer yearId) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<GpCutOffMonthlyEntity> query = criteriaBuilder.createQuery(GpCutOffMonthlyEntity.class);
		final Root<GpCutOffMonthlyEntity> root = query.from(GpCutOffMonthlyEntity.class);
		query.where(criteriaBuilder.equal(root.get("grampanchayatCode"), grampanchayatCode),
				criteriaBuilder.equal(root.get("cutOffMonth"), monthId),
				criteriaBuilder.equal(root.get("cutOffYear"), yearId));
		List<GpCutOffMonthlyEntity> voList = entityManager.createQuery(query).getResultList();
		if (voList != null && voList.size() != 0) {
			return voList.get(0);
		} else {
			return null;
		}
	}
	
	public ClfCutOffMonthlyEntity getLatestMonthlyClfCutOff(String clfCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<ClfCutOffMonthlyEntity> query = criteriaBuilder.createQuery(ClfCutOffMonthlyEntity.class);
		final Root<ClfCutOffMonthlyEntity> root = query.from(ClfCutOffMonthlyEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (clfCode != null) {
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		}
		predicates.add(criteriaBuilder.equal(root.get("cutOffStatus"),ProjectConstants.COMPLETED));
		
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		//query.multiselect(root.get("cutOffYear"), root.get("cutOffMonth"));
		query.orderBy(criteriaBuilder.desc(root.get("cutOffYear")), criteriaBuilder.desc(root.get("cutOffMonth")));
		TypedQuery<ClfCutOffMonthlyEntity> typedQuery = entityManager.createQuery(query);
		//typedQuery.setFirstResult(0);
		//typedQuery.setMaxResults(1);
		List<ClfCutOffMonthlyEntity> cutOffMonthlyList = typedQuery.getResultList();
		if(cutOffMonthlyList != null && cutOffMonthlyList.size() != 0) {
			return cutOffMonthlyList.get(0);
		}
		else {
			return null;
		}
		
		
	}
	public VoCutOffMonthlyEntity getLatestVoCutOff(String voCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<VoCutOffMonthlyEntity> query = criteriaBuilder.createQuery(VoCutOffMonthlyEntity.class);
		final Root<VoCutOffMonthlyEntity> root = query.from(VoCutOffMonthlyEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (voCode != null) {
			predicates.add(criteriaBuilder.equal(root.get("voCode"), voCode));
		}
		predicates.add(criteriaBuilder.equal(root.get("cutOffStatus"),ProjectConstants.COMPLETED));
		
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		//query.multiselect(root.get("cutOffYear"), root.get("cutOffMonth"));
		query.orderBy(criteriaBuilder.desc(root.get("cutOffYear")), criteriaBuilder.desc(root.get("cutOffMonth")));
		TypedQuery<VoCutOffMonthlyEntity> typedQuery = entityManager.createQuery(query);
		//typedQuery.setFirstResult(0);
		//typedQuery.setMaxResults(1);
		return typedQuery.getSingleResult();
		
	}
	
	public GpCutOffMonthlyEntity getLatestGpCutOff(String gpCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<GpCutOffMonthlyEntity> query = criteriaBuilder.createQuery(GpCutOffMonthlyEntity.class);
		final Root<GpCutOffMonthlyEntity> root = query.from(GpCutOffMonthlyEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (gpCode != null) {
			predicates.add(criteriaBuilder.equal(root.get("grampanchayatCode"), gpCode));
		}
		predicates.add(criteriaBuilder.equal(root.get("cutOffStatus"),ProjectConstants.COMPLETED));
		
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		//query.multiselect(root.get("cutOffYear"), root.get("cutOffMonth"));
		query.orderBy(criteriaBuilder.desc(root.get("cutOffYear")), criteriaBuilder.desc(root.get("cutOffMonth")));
		TypedQuery<GpCutOffMonthlyEntity> typedQuery = entityManager.createQuery(query);
		//typedQuery.setFirstResult(0);
		//typedQuery.setMaxResults(1);
		return typedQuery.getSingleResult();
		
	}

	// For get Clf cut off monthly list
	public Page<ClfCustomCutOffEntity> getClfCutOff(CutOffFilterRequest filterdto, List<String> locations,
			String levelId, PageModel pageModel, Pageable pag) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<ClfCustomCutOffEntity> query = criteriaBuilder.createQuery(ClfCustomCutOffEntity.class);
		final Root<ClfCutOffMonthlyEntity> root = query.from(ClfCutOffMonthlyEntity.class);

		Join<ClfCutOffMonthlyEntity, ClfMaster> clfcutoff = root.join("clfprofile", JoinType.INNER);
		Join<ClfCutOffMonthlyEntity, Financial> clfyear = root.join("financeYear", JoinType.INNER);
		Join<ClfCutOffMonthlyEntity, Month> clfmonth = root.join("month", JoinType.INNER);
		Join<Block, ClfMaster> bl = clfcutoff.join("block", JoinType.INNER);
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
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
		}

		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
				//predicates.add(criteriaBuilder.isNull(root.get("overallStatus")));
				predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(root.get("overallStatus")),criteriaBuilder.equal(root.get("overallStatus"),ProjectConstants.PENDING)));

			} else {
				predicates.add(criteriaBuilder.equal(root.get("overallStatus"), filterdto.getStatus()));
			}
		}
		if (filterdto.getReportYear() != 0 && filterdto.getReportYear() != null) {
			predicates.add(criteriaBuilder.equal(clfyear.get("id"), filterdto.getReportYear()));
		}
		if (filterdto.getReportMonth() != 0 && filterdto.getReportMonth() != null) {
			predicates.add(criteriaBuilder.equal(clfmonth.get("id"), filterdto.getReportMonth()));
		}
		if (levelId.equals(ProjectConstants.NATIONAL)) {

		} else if (levelId.equals(ProjectConstants.STATE)) {
			predicates.add(state.get("stateCode").in(locations));
		} else if (levelId.equals(ProjectConstants.DISTRICT)) {
			predicates.add(dis.get("districtCode").in(locations));
		} else if (levelId.equals(ProjectConstants.BLOCK)) {
			predicates.add(bl.get("blockCode").in(locations));
		} else if (levelId.equals(ProjectConstants.CLF)) {
			predicates.add(root.get("clfCode").in(locations));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		query.multiselect(root.get("id"), state.get("stateCode"), state.get("stateName"), dis.get("districtCode"),
				dis.get("districtName"), bl.get("blockCode"), bl.get("blockName"), root.get("clfCode"),
				clfcutoff.get("clfName"),
				criteriaBuilder.concat(criteriaBuilder.concat(clfmonth.get("monthName"), " "), clfyear.get("fnclYear")),
				criteriaBuilder.selectCase(root.get("overallStatus"))
					.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
					.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
					.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
					.otherwise(ProjectConstants.PENDING_TEXT),
				criteriaBuilder.selectCase(root.get("cutOffStatus"))
					.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
					.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
					.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
					.otherwise(ProjectConstants.PENDING_TEXT),
				clfcutoff.get("formationDate"), root.get("cutOffYear"), root.get("cutOffMonth"));
		
		 if(filterdto.getSortCol() != null) {
	        	
	        	if(filterdto.getSortCol().equals("stateName")) {
	        		
	        		if(filterdto.getSortOrder() != null && filterdto.getSortOrder().equals("desc")) {
	    				
	    				query.orderBy(criteriaBuilder.desc(state.get("stateName")));
	    			}
	    			else {
	    				query.orderBy(criteriaBuilder.asc(state.get("stateName")));
	    			}
	        		
	        	}
	        	else if(filterdto.getSortCol().equals("districtName")) {
	        		
	        		if(filterdto.getSortOrder() != null && filterdto.getSortOrder().equals("desc")) {
	    				
	    				query.orderBy(criteriaBuilder.desc(dis.get("districtName")));
	    			}
	    			else {
	    				query.orderBy(criteriaBuilder.asc(dis.get("districtName")));
	    			}
	        	}
	        	else if(filterdto.getSortCol().equals("blockName")) {
	        		
	        		if(filterdto.getSortOrder() != null && filterdto.getSortOrder().equals("desc")) {
	    				
	    				query.orderBy(criteriaBuilder.desc(bl.get("blockName")));
	    			}
	    			else {
	    				query.orderBy(criteriaBuilder.asc(bl.get("blockName")));
	    			}
	        		
	        	}
	        	else if(filterdto.getSortCol().equals("clfName")) {

	        		if(filterdto.getSortOrder() != null && filterdto.getSortOrder().equals("desc")) {
	    				
	    				query.orderBy(criteriaBuilder.desc(clfcutoff.get("clfName")));
	    			}
	    			else {
	    				query.orderBy(criteriaBuilder.asc(clfcutoff.get("clfName")));
	    			}
	        		
	        	}
	       
		 }
		 else {
			 query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")),
						criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(clfcutoff.get("clfName")));
		 }
		
		
		
		
		TypedQuery<ClfCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
		Integer count = typedQuery.getResultList().size();
		typedQuery.setFirstResult(pageModel.getOffset());
		typedQuery.setMaxResults(pageModel.getLimit());
		return new PageImpl<ClfCustomCutOffEntity>(typedQuery.getResultList(), pag, count);
	}

	public Page<VoCustomCutOffEntity> getVoCutOff(CutOffFilterRequest filterdto, List<String> locations, String levelId,
			PageModel pageModel, Pageable pag) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<VoCustomCutOffEntity> query = criteriaBuilder.createQuery(VoCustomCutOffEntity.class);
		final Root<ClfVoMapping> root = query.from(ClfVoMapping.class);

		//Join<ClfMaster, ClfVoMapping> clf = root.join("clfmaster", JoinType.INNER);
		//Join<ClfCutOffMonthlyEntity, ClfMaster> clfCutOff = clf.join("clfcutoffmonthly", JoinType.INNER);
		
		Join<ClfVoMapping, VoProfile> voProfile = root.join("voprofile", JoinType.INNER);
		Join<VoCutOffMonthlyEntity, VoProfile> voCutOff = voProfile.join("vocutoffmonthly", JoinType.INNER);
		Join<VoCutOffMonthlyEntity, Financial> voyear = voCutOff.join("financeYear", JoinType.INNER);
		Join<VoCutOffMonthlyEntity, Month> vomonth = voCutOff.join("month", JoinType.INNER);
		Join<Grampanchayat, VoProfile> gp = voProfile.join("gp", JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getClfCode() != null) {
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
		}
		/*if (filterdto.getStatus() != 0) {
			if (filterdto.getStatus() == 1) {
				predicates.add(criteriaBuilder.isNull(voCutOff.get("cutOffStatus")));

			} else {
				predicates.add(criteriaBuilder.equal(voCutOff.get("cutOffStatus"), filterdto.getStatus()));
			}
		}*/
		if (filterdto.getGrampanchayatCode() != null && !filterdto.getGrampanchayatCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(gp.get("grampanchayatCode"), filterdto.getGrampanchayatCode()));
		}
		if (filterdto.getVoCode() != null && !filterdto.getVoCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(voProfile.get("voCode"), filterdto.getVoCode()));
		}
		if (filterdto.getVoName() != null && !filterdto.getVoName().isEmpty()) {
			predicates.add(
					criteriaBuilder.like(voProfile.get("voName"), "%" + filterdto.getVoName().toUpperCase() + "%"));
		}
		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
				//predicates.add(criteriaBuilder.isNull(root.get("overallStatus")));
				predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(voCutOff.get("cutOffStatus")),criteriaBuilder.equal(voCutOff.get("cutOffStatus"),ProjectConstants.PENDING)));

			} else {
				predicates.add(criteriaBuilder.equal(voCutOff.get("cutOffStatus"), filterdto.getStatus()));
			}
		}
		if (filterdto.getReportYear() != 0) {
			predicates.add(criteriaBuilder.equal(voyear.get("id"), filterdto.getReportYear()));
		}
		if (filterdto.getReportMonth() != 0) {
			predicates.add(criteriaBuilder.equal(vomonth.get("id"), filterdto.getReportMonth()));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		query.multiselect(voCutOff.get("voCutOffId"), gp.get("grampanchayatCode"), gp.get("grampanchayatName"),
				voProfile.get("voCode"), voProfile.get("voName"),
				criteriaBuilder.concat(criteriaBuilder.concat(vomonth.get("monthName"), " "), voyear.get("fnclYear")),
				criteriaBuilder.selectCase(voCutOff.get("cutOffStatus"))
					.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
					.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
					.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
					.otherwise(ProjectConstants.PENDING_TEXT),
				voCutOff.get("cutOffYear"), voCutOff.get("cutOffMonth"));
		
		
		if(filterdto.getSortCol() != null) {
        	
        	if(filterdto.getSortCol().equals("voName")) {
        		
        		if(filterdto.getSortOrder() != null && filterdto.getSortOrder().equals("desc")) {
    				
    				query.orderBy(criteriaBuilder.desc(voProfile.get("voName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(voProfile.get("voName")));
    			}
        		
        	}
       
		}
        else {
        	 query.orderBy(criteriaBuilder.asc(voProfile.get("voName")));
        }

		
		TypedQuery<VoCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
		Integer count = typedQuery.getResultList().size();
		typedQuery.setFirstResult(pageModel.getOffset());
		typedQuery.setMaxResults(pageModel.getLimit());
		return new PageImpl<VoCustomCutOffEntity>(typedQuery.getResultList(), pag, count);
	}

	public Page<GpCustomCutOffEntity> getGpCutOff(CutOffFilterRequest filterdto, List<String> locations, String levelId,
			PageModel pageModel, Pageable pag) {

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<GpCustomCutOffEntity> query = criteriaBuilder.createQuery(GpCustomCutOffEntity.class);
		final Root<Clfgpmapping> root = query.from(Clfgpmapping.class);

		Join<Clfgpmapping, Grampanchayat> gp = root.join("gp", JoinType.INNER);
		Join<GpCutOffMonthlyEntity, Grampanchayat> gpCutOff = gp.join("gpcutoffmonthly", JoinType.INNER);
		Join<GpCutOffMonthlyEntity, Financial> gpyear = gpCutOff.join("financeYear", JoinType.INNER);
		Join<GpCutOffMonthlyEntity, Month> gpmonth = gpCutOff.join("month", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
		}
	
		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
				//predicates.add(criteriaBuilder.isNull(root.get("overallStatus")));
				predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(gpCutOff.get("cutOffStatus")),criteriaBuilder.equal(gpCutOff.get("cutOffStatus"),ProjectConstants.PENDING)));

			} else {
				predicates.add(criteriaBuilder.equal(gpCutOff.get("cutOffStatus"), filterdto.getStatus()));
			}
		}
		if (filterdto.getGrampanchayatCode() != null && !filterdto.getGrampanchayatCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(gp.get("grampanchayatCode"), filterdto.getGrampanchayatCode()));
		}
		if (filterdto.getGrampanchayatName() != null && !filterdto.getGrampanchayatName().isEmpty()) {
			predicates.add(criteriaBuilder.like(gp.get("grampanchayatName"),
					"%" + filterdto.getGrampanchayatName().toUpperCase() + "%"));
		}
		if (filterdto.getReportYear() != 0) {
			predicates.add(criteriaBuilder.equal(gpyear.get("id"), filterdto.getReportYear()));
		}
		if (filterdto.getReportMonth() != 0) {
			predicates.add(criteriaBuilder.equal(gpmonth.get("id"), filterdto.getReportMonth()));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		query.multiselect(gpCutOff.get("id"), gp.get("grampanchayatCode"), gp.get("grampanchayatName"),
				criteriaBuilder.concat(criteriaBuilder.concat(gpmonth.get("monthName"), " "), gpyear.get("fnclYear")),
				criteriaBuilder.selectCase(gpCutOff.get("cutOffStatus"))
					.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
					.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
					.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
					.otherwise(ProjectConstants.PENDING_TEXT),
				gpCutOff.get("cutOffYear"), gpCutOff.get("cutOffMonth"));
		
		if(filterdto.getSortCol() != null) {
        	
        	if(filterdto.getSortOrder() != null && filterdto.getSortCol().equals("grampanchayatName")) {
        		
        		if(filterdto.getSortOrder().equals("desc")) {
    				
    				query.orderBy(criteriaBuilder.desc(gp.get("grampanchayatName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(gp.get("grampanchayatName")));
    			}
        		
        	}
       
		}
        else {
        	 query.orderBy(criteriaBuilder.asc(gp.get("grampanchayatName")));
        }
		
		
		TypedQuery<GpCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
		Integer count = typedQuery.getResultList().size();
		typedQuery.setFirstResult(pageModel.getOffset());
		typedQuery.setMaxResults(pageModel.getLimit());
		return new PageImpl<GpCustomCutOffEntity>(typedQuery.getResultList(), pag, count);
	}

	public List<SectionEntity> getSectionByTabId(Integer tabId) {

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<SectionEntity> query = criteriaBuilder.createQuery(SectionEntity.class);
		final Root<SectionEntity> root = query.from(SectionEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(criteriaBuilder.equal(root.get("status"), 1));
		predicates.add(criteriaBuilder.equal(root.get("sectionStatus"), 1));

		if (tabId != null) {
			predicates.add(criteriaBuilder.equal(root.get("tabType"), tabId));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		query.orderBy(criteriaBuilder.asc(root.get("sequence")));
		TypedQuery<SectionEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

	public List<IndicatorCustomEntity> getIndicatorsBySectionId(Long sectionId, Integer tabId, Integer cutOffId) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<IndicatorCustomEntity> query = criteriaBuilder.createQuery(IndicatorCustomEntity.class);
		final Root<Indicator> root = query.from(Indicator.class);

		if (tabId.equals(ProjectConstants.TAB_CLF)) {
			Join<Indicator, ClfCutOffMonthlyMapping> clfcutoff = root.join("clfMonthMapping", JoinType.LEFT);
			clfcutoff.on(criteriaBuilder.and(criteriaBuilder.equal(clfcutoff.get("cutOffId"), cutOffId)));
			query.multiselect(root.get("id"), root.get("indctrName"), root.get("description"), root.get("mandatory"),
					root.get("indctrType"), root.get("minValue"), root.get("maxValue"), clfcutoff.get("indctrValue"));

		} else if (tabId.equals(ProjectConstants.TAB_VO)) {
			Join<Indicator, VoCutOffMonthlyMapping> vocutoff = root.join("voMonthMapping", JoinType.LEFT);
			vocutoff.on(criteriaBuilder.and(criteriaBuilder.equal(vocutoff.get("cutOffId"), cutOffId)));
			query.multiselect(root.get("id"), root.get("indctrName"), root.get("description"), root.get("mandatory"),
					root.get("indctrType"), root.get("minValue"), root.get("maxValue"), vocutoff.get("indctrValue"));
		} else if (tabId.equals(ProjectConstants.TAB_GP)) {
			Join<Indicator, GpCutOffMonthlyMapping> gpcutoff = root.join("gpMonthMapping", JoinType.LEFT);
			gpcutoff.on(criteriaBuilder.and(criteriaBuilder.equal(gpcutoff.get("cutOffId"), cutOffId)));
			query.multiselect(root.get("id"), root.get("indctrName"), root.get("description"), root.get("mandatory"),
					root.get("indctrType"), root.get("minValue"), root.get("maxValue"), gpcutoff.get("indctrValue"));
		}
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(root.get("status"), 1));
		if (sectionId != null) {
			predicates.add(criteriaBuilder.equal(root.get("sectionId"), Long.parseLong(String.valueOf(sectionId))));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		query.orderBy(criteriaBuilder.asc(root.get("sequence")));
		TypedQuery<IndicatorCustomEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

	public List<IndicatorCustomEntity> getIndicatorsValueBySectionId(Long sectionId, Integer tabId,
			HttpServletRequest request) {

		List<IndicatorCustomEntity> authors = null;
		if (tabId.equals(ProjectConstants.TAB_CLF)) {
//			Join<Indicator, ClfCutOffMapping> clfcutoffmapping = root.join("clfMapping", JoinType.LEFT);
//			Join<ClfCutOffMapping, ClfCutOffEntity> clfcutoff = clfcutoffmapping.join("clfcutOffEntity", JoinType.INNER);
//			clfcutoff.on(criteriaBuilder.and(criteriaBuilder.equal(clfcutoff.get("clfCode"), request.getParameter("clfCode"))));
			String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
					+ "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id"
					+ " FROM mclf_mst_indicator indi"
					+ " left join (Select MAX(clfmapping.cut_off_id) as cut_off_id,"
					+ " clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,"
					+ " ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,"
					+ " MAX(clfcutoff.clf_code) as clf_code "
					+ " from mclf_clf_cut_off_monthly_mapping clfmapping"
					+ " inner join mclf_clf_cut_off_monthly_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.clf_code= :clfCode)"
					+ " where clfcutoff.cut_off_month= :month and clfcutoff.cut_off_year= :year"
					+ " GROUP BY clfmapping.indctr_id) as subquery on indi.id=subquery.indctr_id "
					+ " where indi.section_id=:secId order by sequence ASC";

			Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class).setParameter("clfCode",
					request.getParameter("clfCode"));
			if (request.getParameter("reportMonth") != null && !request.getParameter("reportMonth").isEmpty()) {
				q.setParameter("month", Integer.parseInt(request.getParameter("reportMonth")));
			} else {
				q.setParameter("month", 0);
			}
			if (request.getParameter("reportYear") != null && !request.getParameter("reportYear").isEmpty()) {
				q.setParameter("year", Integer.parseInt(request.getParameter("reportYear")));
			} else {
				q.setParameter("year", 0);
			}
			q.setParameter("secId", sectionId);
			authors = (List<IndicatorCustomEntity>) q.getResultList();
		} else if (tabId.equals(ProjectConstants.TAB_VO)) {
			String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
					+ "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id FROM mclf_mst_indicator indi"
					+ " left join (Select MAX(clfmapping.cut_off_id) as cut_off_id,clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,MAX(clfcutoff.vo_code) as vo_code from mclf_vo_cut_off_monthly_mapping clfmapping"
					+ " inner join mclf_vo_cut_off_monthly_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.vo_code= :voCode)"
					+ " where clfcutoff.cut_off_month= :month and clfcutoff.cut_off_year= :year GROUP BY clfmapping.cut_off_id,clfmapping.indctr_id) as subquery on indi.id=subquery.indctr_id "
					+ " where indi.section_id=:secId order by sequence ASC";

			Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
					.setParameter("voCode", request.getParameter("voCode")).setParameter("secId", sectionId);
			if (request.getParameter("reportMonth") != null && !request.getParameter("reportMonth").isEmpty()) {
				q.setParameter("month", Integer.parseInt(request.getParameter("reportMonth")));
			} else {
				q.setParameter("month", 0);
			}
			if (request.getParameter("reportYear") != null && !request.getParameter("reportYear").isEmpty()) {
				q.setParameter("year", Integer.parseInt(request.getParameter("reportYear")));
			} else {
				q.setParameter("year", 0);
			}
			authors = (List<IndicatorCustomEntity>) q.getResultList();
		} else if (tabId.equals(ProjectConstants.TAB_GP)) {
			String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
					+ "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id FROM mclf_mst_indicator indi"
					+ " left join (Select MAX(clfmapping.cut_off_id) as cut_off_id,clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,MAX(clfcutoff.grampanchayat_code) as grampanchayat_code from mclf_gp_cut_off_monthly_mapping clfmapping"
					+ " inner join mclf_gp_cut_off_monthly_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.grampanchayat_code= :gpCode)"
					+ " where clfcutoff.cut_off_month= :month and clfcutoff.cut_off_year= :year GROUP BY clfmapping.cut_off_id,clfmapping.indctr_id) as subquery on indi.id=subquery.indctr_id "
					+ " where indi.section_id=:secId order by sequence ASC";
			Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
					.setParameter("gpCode", request.getParameter("gpCode")).setParameter("secId", sectionId);
			if (request.getParameter("reportMonth") != null && !request.getParameter("reportMonth").isEmpty()) {
				q.setParameter("month", Integer.parseInt(request.getParameter("reportMonth")));
			} else {
				q.setParameter("month", 0);
			}
			if (request.getParameter("reportYear") != null && !request.getParameter("reportYear").isEmpty()) {
				q.setParameter("year", Integer.parseInt(request.getParameter("reportYear")));
			} else {
				q.setParameter("year", 0);
			}
			authors = (List<IndicatorCustomEntity>) q.getResultList();
		}
		return authors;
	}
	
	public List<IndicatorCustomEntity> getIndicatorsValueByTabIdAndSectionIdIn(CutOffFormRequest request) {

		List<IndicatorCustomEntity> authors = null;
		if (request.getTabId() == ProjectConstants.TAB_CLF) {
			String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
					+ "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id FROM mclf_mst_indicator indi"
					+ " left join (Select MAX(clfmapping.cut_off_id) as cut_off_id,clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,MAX(clfcutoff.clf_code) as clf_code from mclf_clf_cut_off_monthly_mapping clfmapping"
					+ " inner join mclf_clf_cut_off_monthly_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.clf_code= :clfCode)"
					+ " where clfcutoff.cut_off_month= :month and clfcutoff.cut_off_year= :year GROUP BY clfmapping.indctr_id) as subquery on indi.id=subquery.indctr_id "
					+ " where indi.section_id in (:secId) order by sequence ASC";

			Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class).setParameter("clfCode",
					request.getClfCode());
			if (request.getReportMonth() != null && request.getReportMonth() != 0) {
				q.setParameter("month", request.getReportMonth());
			} else {
				q.setParameter("month", 0);
			}
			if (request.getReportYear() != null && request.getReportYear() != 0) {
				q.setParameter("year", request.getReportYear());
			} else {
				q.setParameter("year", 0);
			}
			q.setParameter("secId", request.getFilledSection());
			authors = (List<IndicatorCustomEntity>) q.getResultList();
		} else if (request.getTabId() == ProjectConstants.TAB_VO) {
			String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
					+ "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id FROM mclf_mst_indicator indi"
					+ " left join (Select MAX(clfmapping.cut_off_id) as cut_off_id,clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,MAX(clfcutoff.vo_code) as vo_code from mclf_vo_cut_off_monthly_mapping clfmapping"
					+ " inner join mclf_vo_cut_off_monthly_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.vo_code= :voCode)"
					+ " where clfcutoff.cut_off_month= :month and clfcutoff.cut_off_year= :year GROUP BY clfmapping.cut_off_id,clfmapping.indctr_id) as subquery on indi.id=subquery.indctr_id "
					+ " where indi.section_id in (:secId) order by sequence ASC";

			Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
					.setParameter("voCode", request.getVoCode());
			if (request.getReportMonth() != null && request.getReportMonth() != 0) {
				q.setParameter("month", request.getReportMonth());
			} else {
				q.setParameter("month", 0);
			}
			if (request.getReportYear() != null && request.getReportYear() != 0) {
				q.setParameter("year", request.getReportYear());
			} else {
				q.setParameter("year", 0);
			}
			q.setParameter("secId", request.getFilledSection());
			authors = (List<IndicatorCustomEntity>) q.getResultList();
		} else if (request.getTabId() == ProjectConstants.TAB_GP) {
			String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
					+ "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id FROM mclf_mst_indicator indi"
					+ " left join (Select MAX(clfmapping.cut_off_id) as cut_off_id,clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,MAX(clfcutoff.grampanchayat_code) as grampanchayat_code from mclf_gp_cut_off_monthly_mapping clfmapping"
					+ " inner join mclf_gp_cut_off_monthly_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.grampanchayat_code= :gpCode)"
					+ " where clfcutoff.cut_off_month= :month and clfcutoff.cut_off_year= :year GROUP BY clfmapping.cut_off_id,clfmapping.indctr_id) as subquery on indi.id=subquery.indctr_id "
					+ " where indi.section_id=:secId order by sequence ASC";
			Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
					.setParameter("gpCode", request.getGrampanchayatCode());
			if (request.getReportMonth() != null && request.getReportMonth() != 0) {
				q.setParameter("month", request.getReportMonth());
			} else {
				q.setParameter("month", 0);
			}
			if (request.getReportYear() != null && request.getReportYear() != 0) {
				q.setParameter("year", request.getReportYear());
			} else {
				q.setParameter("year", 0);
			}
			q.setParameter("secId", request.getFilledSection());
			authors = (List<IndicatorCustomEntity>) q.getResultList();
		}
		return authors;
	}

	public boolean checkCutOffExistByClfCode(String clfCode) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<ClfCutOffEntity> query = criteriaBuilder.createQuery(ClfCutOffEntity.class);
		final Root<ClfCutOffEntity> root = query.from(ClfCutOffEntity.class);
		query.where(criteriaBuilder.equal(root.get("clfCode"), clfCode));
		List<ClfCutOffEntity> clfList = entityManager.createQuery(query).getResultList();
		if (clfList != null && clfList.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<AnswerOptionEntity> getIndicatorOptions(String tableName, String shortKey) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<AnswerOptionEntity> query = criteriaBuilder.createQuery(AnswerOptionEntity.class);
		final Root<AnswerOptionEntity> root = query.from(AnswerOptionEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(criteriaBuilder.equal(root.get("tableName"), tableName));

		predicates.add(criteriaBuilder.equal(root.get("shortKey"), shortKey));

		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		query.orderBy(criteriaBuilder.asc(root.get("sequence")));
		TypedQuery<AnswerOptionEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

	/*
	 * Get VO Over All Status BY CLF
	 */

	public List<VoCustomCutOffEntity> getVoCutOffStatus(CutOffFilterRequest filterdto) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<VoCustomCutOffEntity> query = criteriaBuilder.createQuery(VoCustomCutOffEntity.class);
		final Root<ClfVoMapping> root = query.from(ClfVoMapping.class);

		Join<ClfVoMapping, VoProfile> voProfile = root.join("voprofile", JoinType.INNER);
		Join<VoCutOffMonthlyEntity, VoProfile> voCutOff = voProfile.join("vocutoffmonthly", JoinType.INNER);
		Join<VoCutOffMonthlyEntity, Financial> voyear = voCutOff.join("financeYear", JoinType.INNER);
		Join<VoCutOffMonthlyEntity, Month> vomonth = voCutOff.join("month", JoinType.INNER);
		Join<Grampanchayat, VoProfile> gp = voProfile.join("gp", JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getClfCode() != null) {
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
		}
		if(filterdto.getReportYear() != null) {
			predicates.add(criteriaBuilder.equal(voCutOff.get("cutOffYear"), filterdto.getReportYear()));
		}
		if(filterdto.getReportMonth() != null) {
			predicates.add(criteriaBuilder.equal(voCutOff.get("cutOffMonth"), filterdto.getReportMonth()));
		}

		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		query.multiselect(voCutOff.get("voCutOffId"), gp.get("grampanchayatCode"), gp.get("grampanchayatName"),
				voProfile.get("voCode"), voProfile.get("voName"),
				criteriaBuilder.concat(criteriaBuilder.concat(vomonth.get("monthName"), " "), voyear.get("fnclYear")),
				criteriaBuilder.selectCase(voCutOff.get("cutOffStatus"))
					.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
					.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
					.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
					.otherwise(ProjectConstants.PENDING_TEXT),
				voCutOff.get("cutOffYear"), voCutOff.get("cutOffMonth"));
		query.orderBy(criteriaBuilder.asc(voProfile.get("voName")));
		TypedQuery<VoCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

	/*
	 * Get GP Over All Status BY CLF
	 */

	public List<GpCustomCutOffEntity> getGpCutOffStatus(CutOffFilterRequest filterdto) {

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<GpCustomCutOffEntity> query = criteriaBuilder.createQuery(GpCustomCutOffEntity.class);
		final Root<Clfgpmapping> root = query.from(Clfgpmapping.class);

		Join<Clfgpmapping, Grampanchayat> gp = root.join("gp", JoinType.INNER);
		Join<GpCutOffMonthlyEntity, Grampanchayat> gpCutOff = gp.join("gpcutoffmonthly", JoinType.INNER);
		Join<GpCutOffMonthlyEntity, Financial> gpyear = gpCutOff.join("financeYear", JoinType.INNER);
		Join<GpCutOffMonthlyEntity, Month> gpmonth = gpCutOff.join("month", JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();

		if (filterdto.getClfCode() != null) {
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
		}
		if(filterdto.getReportYear() != null) {
			predicates.add(criteriaBuilder.equal(gpCutOff.get("cutOffYear"), filterdto.getReportYear()));
		}
		if(filterdto.getReportMonth() != null) {
			predicates.add(criteriaBuilder.equal(gpCutOff.get("cutOffMonth"), filterdto.getReportMonth()));
		}

		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

		query.multiselect(gpCutOff.get("id"), gp.get("grampanchayatCode"), gp.get("grampanchayatName"),
				criteriaBuilder.concat(criteriaBuilder.concat(gpmonth.get("monthName"), " "), gpyear.get("fnclYear")),
				criteriaBuilder.selectCase(gpCutOff.get("cutOffStatus"))
					.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
					.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
					.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
					.otherwise(ProjectConstants.PENDING_TEXT),
				gpCutOff.get("cutOffYear"), gpCutOff.get("cutOffMonth"));
		query.orderBy(criteriaBuilder.asc(gp.get("grampanchayatName")));
		TypedQuery<GpCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}

	public ClfCutOffMonthlyEntity checkCutOffExistByClfCode(String clfCode, Integer reportYear, Integer reportMonth) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ClfCutOffMonthlyEntity> query = criteriaBuilder.createQuery(ClfCutOffMonthlyEntity.class);
        final Root<ClfCutOffMonthlyEntity> root = query.from(ClfCutOffMonthlyEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // predicates.add(criteriaBuilder.equal(root.get("cutOffStatus"),2));

        if (clfCode != null && !clfCode.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
        }
        if (reportYear != null && reportYear != 0) {
            predicates.add(criteriaBuilder.equal(root.get("cutOffYear"), reportYear));
        }
        if (reportMonth != null && reportMonth !=0) {
            predicates.add(criteriaBuilder.equal(root.get("cutOffMonth"), reportMonth));
        }
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        List<ClfCutOffMonthlyEntity> clfList = entityManager.createQuery(query).getResultList();
        if (clfList != null && clfList.size() != 0) {
            return clfList.get(0);
        } else {
            return null;
        }
    }
	
	public List<String> getClfVoMapping(String clfCode) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        final Root<ClfVoMapping> root = query.from(ClfVoMapping.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
        
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        
		query.multiselect(root.get("voCode"));
		TypedQuery<String> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
    }
	
	public List<String> getClfGpMapping(String clfCode) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        final Root<Clfgpmapping> root = query.from(Clfgpmapping.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
        
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        
		query.multiselect(root.get("grampanchayatCode"));
		TypedQuery<String> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
    }

	public Integer getCutOffSectionStatusByCutOffIdAndSectionId(Integer tabId, Long cutOffId, Long sectionId) {
    	
    	String subquery = "";
    	
    	if(tabId == ProjectConstants.TAB_CLF) {
    		subquery = " Select cm.cut_off_id,cm.indctr_id,cm.indctr_value,cm.option_id"
    					+ " from mclf_clf_cut_off_monthly_mapping cm"
    				+ " where cm.cut_off_id= :cutOffId ";
    	}
    	else if(tabId == ProjectConstants.TAB_VO) {
    		subquery = " Select cm.cut_off_id,cm.indctr_id,cm.indctr_value,cm.option_id"
					+ " from mclf_vo_cut_off_monthly_mapping cm"
					+ " where cm.cut_off_id= :cutOffId ";
    	}
    	else if(tabId == ProjectConstants.TAB_GP) {
    		subquery = " Select cm.cut_off_id,cm.indctr_id,cm.indctr_value,cm.option_id"
					+ " from mclf_gp_cut_off_monthly_mapping cm"
					+ " where cm.cut_off_id= :cutOffId ";
    	}
    	String sql = "Select count(r.indctr_id) as cnt from"
				+ " (Select cutoff.cut_off_id,"
				+ " 	sec.id as section_id,"
				+ " 	ind.id as indctr_id,"
				+ " 	(case when ind.indctr_type in (3,4,7) then cast(cutoff.option_id as varchar) "
				+ " 	else cutoff.indctr_value end) as answer"
				+ " 	from mclf_mst_section sec"
				+ " 	 join mclf_mst_indicator ind on (sec.id = ind.section_id and sec.id = :secId)"
				+ " 	left join "
				+ " 	( " + subquery + " ) cutoff on (ind.id = cutoff.indctr_id)"
				+ " 	where ind.mandatory = 1 and sec.section_status =1 and ind.status=1"
				+ " 	 ) r"
				+ " 	 where r.answer is null";
	
		Query q = entityManager.createNativeQuery(sql)
	            .setParameter("cutOffId", cutOffId).setParameter("secId", sectionId);
	    int cnt =  ((Number)q.getSingleResult()).intValue();
	    if(cnt == 0) {
	    	return ProjectConstants.COMPLETED;
	    }
	    else {
	    	return ProjectConstants.IN_PROGRESS;
	    }

    }

}
