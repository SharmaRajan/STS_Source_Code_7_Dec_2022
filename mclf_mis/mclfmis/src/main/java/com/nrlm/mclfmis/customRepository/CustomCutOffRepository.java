package com.nrlm.mclfmis.customRepository;

import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMapping;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.ClfVoMapping;
import com.nrlm.mclfmis.Entity.Clfgpmapping;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.Financial;
import com.nrlm.mclfmis.Entity.GpCutOffEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMapping;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Entity.Month;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Entity.VoCutOffEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMapping;
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
public class CustomCutOffRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<ClfCustomCutOffEntity> getClfCutOff(CutOffFilterRequest filterdto, List<String> locations,
                                                    String levelName, PageModel pageModel) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ClfCustomCutOffEntity> query = criteriaBuilder.createQuery(ClfCustomCutOffEntity.class);
        final Root<ClfMaster> root = query.from(ClfMaster.class);

        Join<ClfMaster, ClfCutOffEntity> clfcutoff = root.join("clfcutoff", JoinType.LEFT);
        Join<ClfCutOffEntity, Financial> clfyear = clfcutoff.join("financeYear", JoinType.LEFT);
        Join<ClfCutOffEntity, Month> clfmonth = clfcutoff.join("month", JoinType.LEFT);
        Join<Block, ClfMaster> bl = root.join("block", JoinType.INNER);
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
                //predicates.add(criteriaBuilder.isNull(clfcutoff.get("cutOffStatus")));
                predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(clfcutoff.get("overallStatus")), criteriaBuilder.equal(clfcutoff.get("overallStatus"), ProjectConstants.PENDING)));

            } else {
                predicates.add(criteriaBuilder.equal(clfcutoff.get("overallStatus"), filterdto.getStatus()));
            }

        }
        if (filterdto.getReportYear() != 0 && filterdto.getReportYear() != null) {
            predicates.add(criteriaBuilder.equal(clfyear.get("id"), filterdto.getReportYear()));
        }
        if (filterdto.getReportMonth() != 0 && filterdto.getReportMonth() != null) {
            predicates.add(criteriaBuilder.equal(clfmonth.get("id"), filterdto.getReportMonth()));
        }

        if (levelName.equals(ProjectConstants.STATE)) {
            predicates.add(state.get("stateCode").in(locations));
        } else if (levelName.equals(ProjectConstants.DISTRICT)) {
            predicates.add(dis.get("districtCode").in(locations));
        } else if (levelName.equals(ProjectConstants.BLOCK)) {
            predicates.add(bl.get("blockCode").in(locations));
        } else if (levelName.equals(ProjectConstants.CLF)) {
            predicates.add(root.get("clfCode").in(locations));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        query.multiselect(clfcutoff.get("id"), state.get("stateCode"), state.get("stateName"), dis.get("districtCode"),
                dis.get("districtName"), bl.get("blockCode"), bl.get("blockName"), root.get("clfCode"),
                root.get("clfName"),
                criteriaBuilder.concat(criteriaBuilder.concat(clfmonth.get("monthName"), " "), clfyear.get("fnclYear")),
                criteriaBuilder.selectCase(clfcutoff.get("overallStatus"))
                        .when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
                        .when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
                        .when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
                        .otherwise(ProjectConstants.PENDING_TEXT),
                criteriaBuilder.selectCase(clfcutoff.get("cutOffStatus"))
                        .when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
                        .when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
                        .when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
                        .otherwise(ProjectConstants.PENDING_TEXT),
                root.get("formationDate"), clfcutoff.get("cutOffYear"), clfcutoff.get("cutOffMonth"));
 
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
    				
    				query.orderBy(criteriaBuilder.desc(root.get("clfName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(root.get("clfName")));
    			}
        		
        	}
       
		}
		else {
			query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")),
	                criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(root.get("clfName")));
		}
        
        
        TypedQuery<ClfCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageModel.getOffset());
        typedQuery.setMaxResults(pageModel.getLimit());
        return typedQuery.getResultList();
    }

    public Long getClfCutOffCount(CutOffFilterRequest filterdto, List<String> locations, String levelName,
                                  PageModel pageModel) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        final Root<ClfMaster> root = query.from(ClfMaster.class);

        Join<ClfMaster, ClfCutOffEntity> clfcutoff = root.join("clfcutoff", JoinType.LEFT);
        Join<ClfCutOffEntity, Financial> clfyear = clfcutoff.join("financeYear", JoinType.LEFT);
        Join<ClfCutOffEntity, Month> clfmonth = clfcutoff.join("month", JoinType.LEFT);
        Join<Block, ClfMaster> bl = root.join("block", JoinType.INNER);
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
                //predicates.add(criteriaBuilder.isNull(clfcutoff.get("cutOffStatus")));
                predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(clfcutoff.get("overallStatus")), criteriaBuilder.equal(clfcutoff.get("overallStatus"), ProjectConstants.PENDING)));

            } else {
                predicates.add(criteriaBuilder.equal(clfcutoff.get("overallStatus"), filterdto.getStatus()));
            }

        }
        if (filterdto.getReportYear() != 0 && filterdto.getReportYear() != null) {
            predicates.add(criteriaBuilder.equal(clfyear.get("id"), filterdto.getReportYear()));
        }
        if (filterdto.getReportMonth() != 0 && filterdto.getReportMonth() != null) {
            predicates.add(criteriaBuilder.equal(clfmonth.get("id"), filterdto.getReportMonth()));
        }

        if (levelName.equals(ProjectConstants.NATIONAL)) {

        } else if (levelName.equals(ProjectConstants.STATE)) {
            predicates.add(state.get("stateCode").in(locations));
        } else if (levelName.equals(ProjectConstants.DISTRICT)) {
            predicates.add(dis.get("districtCode").in(locations));
        } else if (levelName.equals(ProjectConstants.BLOCK)) {
            predicates.add(bl.get("blockCode").in(locations));
        } else if (levelName.equals(ProjectConstants.CLF)) {
            predicates.add(root.get("clfCode").in(locations));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        query.multiselect(criteriaBuilder.count(root.get("clfCode")));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    public List<VoCustomCutOffEntity> getVoCutOff(CutOffFilterRequest filterdto, List<String> locations,
                                                  String levelName, PageModel pageModel) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VoCustomCutOffEntity> query = criteriaBuilder.createQuery(VoCustomCutOffEntity.class);
        final Root<ClfVoMapping> root = query.from(ClfVoMapping.class);

        Join<ClfVoMapping, VoProfile> voProfile = root.join("voprofile", JoinType.INNER);
        Join<VoCutOffEntity, VoProfile> voCutOff = voProfile.join("vocutoff", JoinType.LEFT);
        Join<VoCutOffEntity, Financial> voyear = voCutOff.join("financeYear", JoinType.LEFT);
        Join<VoCutOffEntity, Month> vomonth = voCutOff.join("month", JoinType.LEFT);
        Join<Grampanchayat, VoProfile> gp = voProfile.join("gp", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
        }
		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
			//	predicates.add(criteriaBuilder.isNull(voCutOff.get("cutOffStatus")));
				predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(voCutOff.get("cutOffStatus")),criteriaBuilder.equal(voCutOff.get("cutOffStatus"),ProjectConstants.PENDING)));


			} else {
				predicates.add(criteriaBuilder.equal(voCutOff.get("cutOffStatus"), filterdto.getStatus()));
			}

		}
        if (filterdto.getGrampanchayatCode() != null && !filterdto.getGrampanchayatCode().isEmpty()) {
            predicates.add(criteriaBuilder.equal(gp.get("grampanchayatCode"), filterdto.getGrampanchayatCode()));
        }
        if (filterdto.getVoCode() != null && !filterdto.getVoCode().isEmpty()) {
            predicates.add(criteriaBuilder.equal(voProfile.get("voCode"), filterdto.getVoCode()));
        }
        if (filterdto.getVoName() != null && !filterdto.getVoName().isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(criteriaBuilder.lower(voProfile.get("voName")), "%" + filterdto.getVoName().toLowerCase() + "%"));
        }
        if (filterdto.getReportYear() != 0) {
            predicates.add(criteriaBuilder.equal(voyear.get("id"), filterdto.getReportYear()));
        }
        if (filterdto.getReportMonth() != 0) {
            predicates.add(criteriaBuilder.equal(vomonth.get("id"), filterdto.getReportMonth()));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        if (levelName.equals(ProjectConstants.NATIONAL)) {

        }

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
        typedQuery.setFirstResult(pageModel.getOffset());
        typedQuery.setMaxResults(pageModel.getLimit());
        return typedQuery.getResultList();
    }

    public Long getVoCutOffCount(CutOffFilterRequest filterdto, List<String> locations, String levelName,
                                 PageModel pageModel) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        final Root<ClfVoMapping> root = query.from(ClfVoMapping.class);

        Join<ClfVoMapping, VoProfile> voProfile = root.join("voprofile", JoinType.INNER);
        Join<VoCutOffEntity, VoProfile> voCutOff = voProfile.join("vocutoff", JoinType.LEFT);
        Join<VoCutOffEntity, Financial> voyear = voCutOff.join("financeYear", JoinType.LEFT);
        Join<VoCutOffEntity, Month> vomonth = voCutOff.join("month", JoinType.LEFT);
        Join<Grampanchayat, VoProfile> gp = voProfile.join("gp", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
        }
		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
                predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(voCutOff.get("cutOffStatus")),criteriaBuilder.equal(voCutOff.get("cutOffStatus"),ProjectConstants.PENDING)));

            } else {
				predicates.add(criteriaBuilder.equal(voCutOff.get("cutOffStatus"), filterdto.getStatus()));
			}
		}
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
        if (filterdto.getReportYear() != 0) {
            predicates.add(criteriaBuilder.equal(voyear.get("id"), filterdto.getReportYear()));
        }
        if (filterdto.getReportMonth() != 0) {
            predicates.add(criteriaBuilder.equal(vomonth.get("id"), filterdto.getReportMonth()));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        if (levelName.equals(ProjectConstants.NATIONAL)) {

        }

        query.multiselect(criteriaBuilder.count(voProfile.get("voCode")));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    public List<GpCustomCutOffEntity> getGpCutOff(CutOffFilterRequest filterdto, List<String> locations,
                                                  String levelName, PageModel pageModel) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<GpCustomCutOffEntity> query = criteriaBuilder.createQuery(GpCustomCutOffEntity.class);
        final Root<Clfgpmapping> root = query.from(Clfgpmapping.class);

        Join<Clfgpmapping, Grampanchayat> gp = root.join("gp", JoinType.INNER);
        Join<GpCutOffEntity, Grampanchayat> gpCutOff = gp.join("gpcutoff", JoinType.LEFT);
        Join<GpCutOffEntity, Financial> gpyear = gpCutOff.join("financeYear", JoinType.LEFT);
        Join<GpCutOffEntity, Month> gpmonth = gpCutOff.join("month", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
        }
		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
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

        if (levelName.equals(ProjectConstants.NATIONAL)) {

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
        typedQuery.setFirstResult(pageModel.getOffset());
        typedQuery.setMaxResults(pageModel.getLimit());
        return typedQuery.getResultList();
    }

    public Long getGpCutOffCount(CutOffFilterRequest filterdto, List<String> locations, String levelName,
                                 PageModel pageModel) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        final Root<Clfgpmapping> root = query.from(Clfgpmapping.class);

        Join<Clfgpmapping, Grampanchayat> gp = root.join("gp", JoinType.INNER);
        Join<GpCutOffEntity, Grampanchayat> gpCutOff = gp.join("gpcutoff", JoinType.LEFT);
        Join<GpCutOffEntity, Financial> gpyear = gpCutOff.join("financeYear", JoinType.LEFT);
        Join<GpCutOffEntity, Month> gpmonth = gpCutOff.join("month", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (filterdto.getClfCode() != null && !filterdto.getClfCode().isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
        }
		if (filterdto.getStatus() != 0 && filterdto.getStatus() != null) {
			if (filterdto.getStatus() == 1) {
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

        if (levelName.equals(ProjectConstants.NATIONAL)) {

        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        query.multiselect(criteriaBuilder.count(gp.get("grampanchayatCode")));
        TypedQuery<Long> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
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

    public List<Indicator> getIndicatorsBySectionId(Long sectionId) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Indicator> query = criteriaBuilder.createQuery(Indicator.class);
        final Root<Indicator> root = query.from(Indicator.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("status"), 1));

        if (sectionId != null) {
            predicates.add(criteriaBuilder.equal(root.get("sectionId"), sectionId));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        query.orderBy(criteriaBuilder.asc(root.get("sequence")));
        TypedQuery<Indicator> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();

    }

    public ClfCutOffEntity checkCutOffExistByClfCode(String clfCode) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ClfCutOffEntity> query = criteriaBuilder.createQuery(ClfCutOffEntity.class);
        final Root<ClfCutOffEntity> root = query.from(ClfCutOffEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // predicates.add(criteriaBuilder.equal(root.get("cutOffStatus"),2));

        if (clfCode != null && !clfCode.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), clfCode));
        }
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        List<ClfCutOffEntity> clfList = entityManager.createQuery(query).getResultList();
        if (clfList != null && clfList.size() != 0) {
            return clfList.get(0);
        } else {
            return null;
        }
    }

    public VoCutOffEntity checkCutOffExistByVoCode(String voCode) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VoCutOffEntity> query = criteriaBuilder.createQuery(VoCutOffEntity.class);
        final Root<VoCutOffEntity> root = query.from(VoCutOffEntity.class);
        query.where(criteriaBuilder.equal(root.get("voCode"), voCode));
        List<VoCutOffEntity> voList = entityManager.createQuery(query).getResultList();
        if (voList != null && voList.size() != 0) {
            return voList.get(0);
        } else {
            return null;
        }
    }

    public GpCutOffEntity checkCutOffExistByGpCode(String grampanchayatCode) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<GpCutOffEntity> query = criteriaBuilder.createQuery(GpCutOffEntity.class);
        final Root<GpCutOffEntity> root = query.from(GpCutOffEntity.class);
        query.where(criteriaBuilder.equal(root.get("grampanchayatCode"), grampanchayatCode));
        List<GpCutOffEntity> gpList = entityManager.createQuery(query).getResultList();
        if (gpList != null && gpList.size() != 0) {
            return gpList.get(0);
        } else {
            return null;
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

    public Page<ClfBasicProfileCustomEntity> getClfbasicProfileList(HttpServletRequest request, List<String> locations,
                                                                    String levelId, PageModel pageModel, Pageable pag) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ClfBasicProfileCustomEntity> query = criteriaBuilder
                .createQuery(ClfBasicProfileCustomEntity.class);
        final Root<ClfMaster> root = query.from(ClfMaster.class);
        Join<ClfBasicProfile, ClfMaster> clfmas = root.join("clfbasicprofile", JoinType.LEFT);
        Join<Block, ClfMaster> bl = root.join("block", JoinType.INNER);
        Join<District, Block> dis = bl.join("district", JoinType.INNER);
        Join<District, State> state = dis.join("state", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

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
        if (request.getParameter("stateCode") != null && !request.getParameter("stateCode").isEmpty()) {
            predicates.add(criteriaBuilder.equal(bl.get("stateCode"), request.getParameter("stateCode")));
        }
        if (request.getParameter("districtCode") != null && !request.getParameter("districtCode").isEmpty()) {
            predicates.add(criteriaBuilder.equal(bl.get("districtCode"), request.getParameter("districtCode")));
        }
        if (request.getParameter("blockCode") != null && !request.getParameter("blockCode").isEmpty()) {
            predicates.add(criteriaBuilder.equal(bl.get("blockCode"), request.getParameter("blockCode")));

        }
        if (request.getParameter("clfCode") != null && !request.getParameter("clfCode").isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("clfCode"), "%" + request.getParameter("clfCode") + "%"));

        }
        if (request.getParameter("monthId") != null && !request.getParameter("monthId").isEmpty()) {
            predicates.add(criteriaBuilder.equal(clfmas.get("monthId"), request.getParameter("monthId")));

        }
        if (request.getParameter("finYearId") != null && !request.getParameter("finYearId").isEmpty()) {
            predicates.add(criteriaBuilder.equal(clfmas.get("finYearId"), request.getParameter("finYearId")));

        }
        if (request.getParameter("profileStatus") != null && !request.getParameter("profileStatus").isEmpty()) {
            if (Integer.parseInt(request.getParameter("profileStatus")) == 1) {
                predicates.add(criteriaBuilder.isNull(clfmas.get("profileStatus")));
            } else {
                predicates
                        .add(criteriaBuilder.equal(clfmas.get("profileStatus"), request.getParameter("profileStatus")));
            }
        }
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        query.multiselect(state.get("stateName"), dis.get("districtName"), bl.get("blockName"), root.get("clfCode"),
                root.get("clfName"),
                criteriaBuilder.selectCase(clfmas.get("profileStatus")).when(3, "Completed").when(2, "In Progress")
                        .otherwise("Pending"),
                root.get("formationDate"), clfmas.get("clfRecgn"), clfmas.get("monthId"), clfmas.get("finYearId"),
                clfmas.get("npName"), clfmas.get("npDesgn"), clfmas.get("otherDesgn"), clfmas.get("officeStatus"),
                clfmas.get("pan"), clfmas.get("tan"), clfmas.get("gstin"));
        
        if(request.getParameter("sortCol") != null) {
        	
        	if(request.getParameter("sortCol").equals("stateName")) {
        		
        		if(request.getParameter("sortOrder")!= null && request.getParameter("sortOrder").equals("desc")) {
    				
    				query.orderBy(criteriaBuilder.desc(state.get("stateName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(state.get("stateName")));
    			}
        		
        	}
        	else if(request.getParameter("sortCol").equals("districtName")) {
        		
        		if(request.getParameter("sortOrder")!= null && request.getParameter("sortOrder").equals("desc")) {
    				
    				query.orderBy(criteriaBuilder.desc(dis.get("districtName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(dis.get("districtName")));
    			}
        	}
        	else if(request.getParameter("sortCol").equals("blockName")) {
        		
        		if(request.getParameter("sortOrder")!= null && request.getParameter("sortOrder").equals("desc")) {
    				
    				query.orderBy(criteriaBuilder.desc(bl.get("blockName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(bl.get("blockName")));
    			}
        		
        	}
        	else if(request.getParameter("sortCol").equals("clfName")) {

        		if(request.getParameter("sortOrder")!= null && request.getParameter("sortOrder").equals("desc")) {
    				
    				query.orderBy(criteriaBuilder.desc(root.get("clfName")));
    			}
    			else {
    				query.orderBy(criteriaBuilder.asc(root.get("clfName")));
    			}
        		
        	}
       
		}
		else {
			query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")), criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(root.get("clfName")));
		}
        
        
        TypedQuery<ClfBasicProfileCustomEntity> typedQuery = entityManager.createQuery(query);
        Integer count = typedQuery.getResultList().size();
        typedQuery.setFirstResult(pageModel.getPAGE());
        typedQuery.setMaxResults(pageModel.getSIZE());
        return new PageImpl<ClfBasicProfileCustomEntity>(typedQuery.getResultList(), pag, count);
    }
    
  
    public Integer getCutOffSectionStatusByCutOffIdAndSectionId(Integer tabId,Long cutOffId, Long sectionId){
    	
    	String subquery = "";
    	
    	if(tabId == ProjectConstants.TAB_CLF) {
    		subquery = " Select cm.cut_off_id,cm.indctr_id,cm.indctr_value,cm.option_id"
    					+ " from mclf_clf_cut_off_mapping cm"
    				+ " where cm.cut_off_id= :cutOffId ";
    	}
    	else if(tabId == ProjectConstants.TAB_VO) {
    		subquery = " Select cm.cut_off_id,cm.indctr_id,cm.indctr_value,cm.option_id"
					+ " from mclf_vo_cut_off_mapping cm"
					+ " where cm.cut_off_id= :cutOffId ";
    	}
    	else if(tabId == ProjectConstants.TAB_GP) {
    		subquery = " Select cm.cut_off_id,cm.indctr_id,cm.indctr_value,cm.option_id"
					+ " from mclf_gp_cut_off_mapping cm"
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

    /*
     * To fetch Indicator Wise Value
     */
    public List<IndicatorCustomEntity> getIndicatorsValueBySectionId(Long sectionId, Integer tabId,
                                                                     HttpServletRequest request) {

        List<IndicatorCustomEntity> authors = null;
        if (tabId.equals(ProjectConstants.TAB_CLF)) {
//			Join<Indicator, ClfCutOffMapping> clfcutoffmapping = root.join("clfMapping", JoinType.LEFT);
//			Join<ClfCutOffMapping, ClfCutOffEntity> clfcutoff = clfcutoffmapping.join("clfcutOffEntity", JoinType.INNER);
//			clfcutoff.on(criteriaBuilder.and(criteriaBuilder.equal(clfcutoff.get("clfCode"), request.getParameter("clfCode"))));
            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value as indctr_value,subquery.option_id"
                    + " FROM mclf_mst_indicator indi"
                    + " left join "
                    + " (Select clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,"
                    + " ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id "
                    + " from mclf_clf_cut_off_mapping clfmapping "
                    + " inner join mclf_clf_cut_off_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.clf_code= :clfCode)"
                    + " Group By clfmapping.indctr_id,clfmapping.cut_off_id"
                    + " ) as subquery on indi.id=subquery.indctr_id "
                    + " where indi.section_id=:secId AND indi.status = 1  "
                    + " order by sequence ASC";
            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
                    .setParameter("clfCode", request.getParameter("clfCode")).setParameter("secId", sectionId);
            authors = (List<IndicatorCustomEntity>) q.getResultList();
        } else if (tabId.equals(ProjectConstants.TAB_VO)) {
            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id as option_id "
                    + " FROM mclf_mst_indicator indi"
                    + " left join "
                    + " (Select MAX(vomapping.cut_off_id) as cut_off_id,vomapping.indctr_id,MAX(vomapping.indctr_value) as indctr_value,"
                    + " ARRAY_TO_STRING(ARRAY_AGG(vomapping.option_id),',') as option_id,MAX(clfcutoff.vo_code) as vo_code"
                    + " from mclf_vo_cut_off_mapping vomapping "
                    + " inner join mclf_vo_cut_off_details clfcutoff on (vomapping.cut_off_id=clfcutoff.id and clfcutoff.vo_code= :voCode)"
                    + " GROUP BY vomapping.cut_off_id,vomapping.indctr_id"
                    + " ) as subquery on indi.id=subquery.indctr_id "
                    + " where indi.section_id=:secId AND indi.status = 1"
                    + " order by sequence ASC";
            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
                    .setParameter("voCode", request.getParameter("voCode")).setParameter("secId", sectionId);
            authors = (List<IndicatorCustomEntity>) q.getResultList();
        } else if (tabId.equals(ProjectConstants.TAB_GP)) {
            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id as option_id"
                    + " FROM mclf_mst_indicator indi"
                    + " left join "
                    + " (Select MAX(gpmapping.cut_off_id) as cut_off_id,gpmapping.indctr_id,MAX(gpmapping.indctr_value) as indctr_value,"
                    + " ARRAY_TO_STRING(ARRAY_AGG(gpmapping.option_id), ',') as option_id,MAX(clfcutoff.grampanchayat_code) as grampanchayat_code"
                    + " from mclf_gp_cut_off_mapping gpmapping"
                    + " inner join mclf_gp_cut_off_details clfcutoff on (gpmapping.cut_off_id=clfcutoff.id and clfcutoff.grampanchayat_code= :gpCode)"
                    + " GROUP BY gpmapping.cut_off_id,gpmapping.indctr_id"
                    + " ) as subquery on indi.id=subquery.indctr_id "
                    + " where indi.section_id=:secId AND indi.status = 1 "
                    + " order by sequence ASC";
            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
                    .setParameter("gpCode", request.getParameter("gpCode")).setParameter("secId", sectionId);
            authors = (List<IndicatorCustomEntity>) q.getResultList();
        }
        return authors;
    }
    
    
    
    /*
     * To fetch Indicator Wise Value
     */
    @SuppressWarnings("unchecked")
	public List<IndicatorCustomEntity> getIndicatorsValueByTabIdAndSectionIdIn(CutOffFormRequest req) {

        List<IndicatorCustomEntity> authors = null;
        if (req.getTabId() == ProjectConstants.TAB_CLF) {
            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value as indctr_value,subquery.option_id"
                    + " FROM mclf_mst_indicator indi"
                    + " left join "
                    + " (Select clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,"
                    + " ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id "
                    + " from mclf_clf_cut_off_mapping clfmapping "
                    + " inner join mclf_clf_cut_off_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.clf_code= :clfCode)"
                    + " Group By clfmapping.indctr_id,clfmapping.cut_off_id"
                    + " ) as subquery on indi.id=subquery.indctr_id "
                    + " where indi.section_id in (:secId) AND indi.status = 1  "
                    + " order by sequence ASC";
            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
                    .setParameter("clfCode", req.getClfCode()).setParameter("secId", req.getFilledSection());
            authors = (List<IndicatorCustomEntity>) q.getResultList();
        } 
        else if (req.getTabId() == ProjectConstants.TAB_VO) {
            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id as option_id "
                    + " FROM mclf_mst_indicator indi"
                    + " left join "
                    + " (Select MAX(vomapping.cut_off_id) as cut_off_id,vomapping.indctr_id,MAX(vomapping.indctr_value) as indctr_value,"
                    + " ARRAY_TO_STRING(ARRAY_AGG(vomapping.option_id),',') as option_id,MAX(clfcutoff.vo_code) as vo_code"
                    + " from mclf_vo_cut_off_mapping vomapping "
                    + " inner join mclf_vo_cut_off_details clfcutoff on (vomapping.cut_off_id=clfcutoff.id and clfcutoff.vo_code= :voCode)"
                    + " GROUP BY vomapping.cut_off_id,vomapping.indctr_id"
                    + " ) as subquery on indi.id=subquery.indctr_id "
                    + " where indi.section_id in (:secId) AND indi.status = 1"
                    + " order by sequence ASC";
            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
            		.setParameter("voCode", req.getVoCode()).setParameter("secId", req.getFilledSection());
            authors = (List<IndicatorCustomEntity>) q.getResultList();
        }
        else if (req.getTabId() == ProjectConstants.TAB_GP) {
            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value,subquery.option_id as option_id"
                    + " FROM mclf_mst_indicator indi"
                    + " left join "
                    + " (Select MAX(gpmapping.cut_off_id) as cut_off_id,gpmapping.indctr_id,MAX(gpmapping.indctr_value) as indctr_value,"
                    + " ARRAY_TO_STRING(ARRAY_AGG(gpmapping.option_id), ',') as option_id,MAX(clfcutoff.grampanchayat_code) as grampanchayat_code"
                    + " from mclf_gp_cut_off_mapping gpmapping"
                    + " inner join mclf_gp_cut_off_details clfcutoff on (gpmapping.cut_off_id=clfcutoff.id and clfcutoff.grampanchayat_code= :gpCode)"
                    + " GROUP BY gpmapping.cut_off_id,gpmapping.indctr_id"
                    + " ) as subquery on indi.id=subquery.indctr_id "
                    + " where indi.section_id in (:secId) AND indi.status = 1 "
                    + " order by sequence ASC";
            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
            		.setParameter("gpCode", req.getClfCode()).setParameter("secId", req.getFilledSection());
            authors = (List<IndicatorCustomEntity>) q.getResultList();
        }
        return authors;
    }
    
    
    

    /*
     * Get VO Over All Status BY CLF
     */

    public List<VoCustomCutOffEntity> getVoCutOffStatus(CutOffFilterRequest filterdto) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VoCustomCutOffEntity> query = criteriaBuilder.createQuery(VoCustomCutOffEntity.class);
        final Root<ClfVoMapping> root = query.from(ClfVoMapping.class);

        Join<ClfVoMapping, VoProfile> voProfile = root.join("voprofile", JoinType.INNER);
        Join<VoCutOffEntity, VoProfile> voCutOff = voProfile.join("vocutoff", JoinType.LEFT);
        Join<VoCutOffEntity, Financial> voyear = voCutOff.join("financeYear", JoinType.LEFT);
        Join<VoCutOffEntity, Month> vomonth = voCutOff.join("month", JoinType.LEFT);
        Join<Grampanchayat, VoProfile> gp = voProfile.join("gp", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (filterdto.getClfCode() != null) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        query.multiselect(voCutOff.get("voCutOffId"), gp.get("grampanchayatCode"), gp.get("grampanchayatName"),
                voProfile.get("voCode"), voProfile.get("voName"),
                criteriaBuilder.concat(criteriaBuilder.concat(vomonth.get("monthName"), " "), voyear.get("fnclYear")),
                criteriaBuilder.selectCase(voCutOff.get("cutOffStatus"))
                        .when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
                        .when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
                        .when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT).otherwise(ProjectConstants.PENDING_TEXT),
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
        Join<GpCutOffEntity, Grampanchayat> gpCutOff = gp.join("gpcutoff", JoinType.LEFT);
        Join<GpCutOffEntity, Financial> gpyear = gpCutOff.join("financeYear", JoinType.LEFT);
        Join<GpCutOffEntity, Month> gpmonth = gpCutOff.join("month", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (filterdto.getClfCode() != null) {
            predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        query.multiselect(gpCutOff.get("id"), gp.get("grampanchayatCode"), gp.get("grampanchayatName"),
                criteriaBuilder.concat(criteriaBuilder.concat(gpmonth.get("monthName"), " "), gpyear.get("fnclYear")),
                criteriaBuilder.selectCase(gpCutOff.get("cutOffStatus"))
                        .when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
                        .when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
                        .when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT).otherwise(ProjectConstants.IN_PROGRESS_TEXT),
                gpCutOff.get("cutOffYear"), gpCutOff.get("cutOffMonth"));
        query.orderBy(criteriaBuilder.asc(gp.get("grampanchayatName")));
        TypedQuery<GpCustomCutOffEntity> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

}
