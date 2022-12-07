package com.nrlm.mclfmis.customRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import com.nrlm.mclfmis.Entity.TrainingEntity;
import com.nrlm.mclfmis.Entity.TrainingMapperEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.TrainingFilterRequest;
import com.nrlm.mclfmis.Request.TrainingRequest;
import com.nrlm.mclfmis.customEntity.TrainingCustomEntity;

@Repository
public class CustomTrainingRepositoryImpl implements CustomTrainingRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingCustomEntity> getTraining(TrainingFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel) {
		
		List<TrainingCustomEntity> trainingList = null;
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<TrainingCustomEntity> query = criteriaBuilder.createQuery(TrainingCustomEntity.class);
		final Root<TrainingEntity> root = query.from(TrainingEntity.class);
		
		 
		Join<TrainingEntity,TrainingMapperEntity> mapper = root.join("trainMappers", JoinType.INNER);
		//final Root<ClfMaster> root = query.from(ClfMaster.class);
		Join<ClfMaster, TrainingEntity> clf = root.join("clfmaster", JoinType.INNER);
		//final Root<StaffCustomEntity> temp = query.from(StaffCustomEntity.class); //new
		
		Join<Block, ClfMaster> bl = clf.join("block", JoinType.INNER);
		Join<District, Block> dis = bl.join("district", JoinType.INNER);
		Join<District, State> state = dis.join("state", JoinType.INNER);
		
		String whereClause = "where td.id is not null ";
		
//		List<Predicate> predicates = new ArrayList<>();
        
//		if (filterdto.getStateCode() != null) {
//			predicates.add(criteriaBuilder.equal(state.get("stateCode"), filterdto.getStateCode()));
//		}
//		if (filterdto.getDistrictCode() != null) {
//			predicates.add(criteriaBuilder.equal(dis.get("districtCode"), filterdto.getDistrictCode()));
//		}
//		if (filterdto.getBlockCode() != null) {
//			predicates.add(criteriaBuilder.equal(bl.get("blockCode"), filterdto.getBlockCode()));
//		}
//		if (filterdto.getClfCode() != null) {
//			predicates.add(criteriaBuilder.equal(clf.get("clfCode"), filterdto.getClfCode()));
//		}
//		if (filterdto.getClfName() != null) {
//			 predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
//		}
//
//		if (filterdto.getId() != null && filterdto.getId() != 0) {
//			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
//		}
//		if (filterdto.getStatus() != null && filterdto.getStatus() != 0) {
//			
//				predicates.add(criteriaBuilder.equal(root.get("trainingStatus"), filterdto.getStatus()));
//		}
//		
//		
//		if (levelName.equals(ProjectConstants.STATE)) {
//			predicates.add(state.get("stateCode").in(locations));
//		} else if (levelName.equals(ProjectConstants.DISTRICT)) {
//			predicates.add(dis.get("districtCode").in(locations));
//		} else if (levelName.equals(ProjectConstants.BLOCK)) {
//			predicates.add(bl.get("blockCode").in(locations));
//		} else if (levelName.equals(ProjectConstants.CLF)) {
//			predicates.add(clf.get("clfCode").in(locations));
//		}
//		
//		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//		
//		query.multiselect(root.get("id"),state.get("stateCode"),state.get("stateName"),dis.get("districtCode"),dis.get("districtName"),
//				bl.get("blockCode"),bl.get("blockName"), clf.get("clfCode"),clf.get("clfName"),
//				root.get("trainingConduct"),criteriaBuilder.function("ARRAY_AGG", Integer.class, mapper.get("clfStaffType"))
//				,root.get("startDate"),
//				root.get("endDate"),root.get("createdBy"),root.get("updatedBy"),root.get("createdAt"),root.get("updatedAt"),root.get("status"),
//				criteriaBuilder.selectCase(root.get("trainingStatus"))
//				.when(ProjectConstants.PENDING, ProjectConstants.PENDING_TEXT)
//				.when(ProjectConstants.COMPLETED, ProjectConstants.COMPLETED_TEXT)
//				.when(ProjectConstants.IN_PROGRESS, ProjectConstants.IN_PROGRESS_TEXT)
//				.otherwise(ProjectConstants.IN_PROGRESS_TEXT));
//		
//		query.groupBy(root.get("id")); tdJoin ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id,
		
		if (filterdto.getStateCode() != null) {
			whereClause += " AND st.state_code = :stateCode ";
		}
		if (filterdto.getDistrictCode() != null) {
			whereClause += " AND ds.district_code = :districtCode ";
		}
		if (filterdto.getBlockCode() != null) {
			whereClause += " AND bl.block_code = :blockCode ";

		}
		if (filterdto.getClfCode() != null) {
			whereClause += " AND cm.clf_code = :clfCode ";

		}
		if (filterdto.getClfName() != null) {
			whereClause += " AND cm.clf_name = :clfName ";
			// predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
		}

		if (filterdto.getId() != null && filterdto.getId() != 0) {
			whereClause += " AND td.id = :id ";
//			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
		}
		if (filterdto.getStatus() != null && filterdto.getStatus() != 0) {
			whereClause += " AND td.training_status = :trainingStatus ";

		}
		
		if (levelName.equals(ProjectConstants.STATE)) {
			whereClause += " AND st.state_code in(:locations) ";

			//predicates.add(state.get("stateCode").in(locations));
		} else if (levelName.equals(ProjectConstants.DISTRICT)) {
			whereClause += " AND ds.district_code in(:locations) ";

			//predicates.add(dis.get("districtCode").in(locations));
		} else if (levelName.equals(ProjectConstants.BLOCK)) {
			whereClause += " AND bl.block_code in(:locations) ";

//			predicates.add(bl.get("blockCode").in(locations));
		} else if (levelName.equals(ProjectConstants.CLF)) {
			whereClause += " AND cm.clf_code in(:locations) ";

//			predicates.add(clf.get("clfCode").in(locations));
		}
		
		
		String sql = "SELECT td.id, MAX(st.state_code) as state_code , MAX(st.state_name) as state_name,"
					+ " MAX(bl.block_code) as block_code, MAX(bl.block_name) as block_name,"
					+ " MAX(ds.district_code) as district_code, MAX(ds.district_name) as district_name, "
					+ " MAX(cm.clf_code) as clf_code, MAX(cm.clf_name) as clf_name,"
					+ " td.training_conduct,ARRAY_TO_STRING(ARRAY_AGG(mp.clf_staff_type),',') as clf_staff_type,"
					+" ARRAY_TO_STRING(ARRAY_AGG((case when mp.clf_staff_type is null then (case when mp.training_module is null then 0 else mp.training_module end) end)),',') as training_module,"
					+" ARRAY_TO_STRING(ARRAY_AGG((case when mp.clf_staff_type is null then (case when mp.participant_no is null then 0 else mp.participant_no end) end)),',') as participant_no,"

					//+ " ARRAY_TO_STRING(ARRAY_AGG(mp.training_module),',') as training_module,"
					//+ " ARRAY_TO_STRING(ARRAY_AGG(mp.participant_no),',') as participant_no,
					+" TO_CHAR(td.start_date, 'YYYY-MM-DD') as start_date, "
					+" TO_CHAR(td.end_date, 'YYYY-MM-DD') as end_date , td.created_by, td.updated_by, td.created_at, td.updated_at, td.status,"
					+ " (case when td.training_status = 1 then 'Pending' "
					+ " when td.training_status = 2 then 'In Progress' "
					+ " when td.training_status = 3 then 'Completed' else 'Pending' end) as training_status "
					+" FROM mclf_clf_training_details td "
					+" Inner Join mclf_clf_training_details_mapper mp on (td.id=mp.training_id) "
					+" Inner Join clf_profile cm on (td.clf_code=cm.clf_code) "
					+" Inner Join mst_block bl on (bl.block_code=cm.block_code) "
					+" Inner Join mst_district ds on (bl.district_code=ds.district_code) "
					+" Inner Join mst_state st on (ds.state_code=st.state_code) "
					+whereClause
					+"Group By td.id";
					
		
				Query q = entityManager.createNativeQuery(sql, "JediResult");
				
				if (filterdto.getStateCode() != null) {
					q.setParameter("stateCode", filterdto.getStateCode());
				}
				if (filterdto.getDistrictCode() != null) {
					q.setParameter("districtCode", filterdto.getDistrictCode());
				}
				if (filterdto.getBlockCode() != null) {
					q.setParameter("blockCode", filterdto.getBlockCode());

				}
				if (filterdto.getClfCode() != null) {
					q.setParameter("clfCode", filterdto.getClfCode());

				}
				if (filterdto.getClfName() != null) {
					q.setParameter("clfName", filterdto.getClfName());
					// predicates.add(criteriaBuilder.like(criteriaBuilder.lower(clf.get("clfName")), "%" + filterdto.getClfName().toLowerCase() + "%"));
				}

				if (filterdto.getId() != null && filterdto.getId() != 0) {
					q.setParameter("id", filterdto.getId());
//					predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
				}
				if (filterdto.getStatus() != null && filterdto.getStatus() != 0) {
					q.setParameter("trainingStatus", filterdto.getStatus());

				}
				
				
				if (levelName.equals(ProjectConstants.STATE)) {
					q.setParameter("locations", locations);
					//predicates.add(state.get("stateCode").in(locations));
				} else if (levelName.equals(ProjectConstants.DISTRICT)) {
					q.setParameter("locations", locations);
					//predicates.add(dis.get("districtCode").in(locations));
				} else if (levelName.equals(ProjectConstants.BLOCK)) {
					q.setParameter("locations", locations);
//					predicates.add(bl.get("blockCode").in(locations));
				} else if (levelName.equals(ProjectConstants.CLF)) {
					q.setParameter("locations", locations);
//					predicates.add(clf.get("clfCode").in(locations));
				}
//				authors = (List<TrainingCustomEntity>)q.getResultList();
				trainingList = q.getResultList();
	
//		if (tabId.equals(ProjectConstants.TAB_CLF)) {
////			Join<Indicator, ClfCutOffMapping> clfcutoffmapping = root.join("clfMapping", JoinType.LEFT);
////			Join<ClfCutOffMapping, ClfCutOffEntity> clfcutoff = clfcutoffmapping.join("clfcutOffEntity", JoinType.INNER);
////			clfcutoff.on(criteriaBuilder.and(criteriaBuilder.equal(clfcutoff.get("clfCode"), request.getParameter("clfCode"))));
//            String sql = "SELECT indi.id, indi.indctr_name, indi.description, indi.mandatory,"
//                    + "indi.indctr_type,indi.min_value,indi.max_value,subquery.indctr_value as indctr_value,subquery.option_id"
//                    + " FROM mclf_mst_indicator indi "
//                    + " left join "
//                    + " (Select clfmapping.indctr_id,MAX(clfmapping.indctr_value) as indctr_value,"
//                    + " ARRAY_TO_STRING(ARRAY_AGG(clfmapping.option_id),',') as option_id "
//                    + " from mclf_clf_cut_off_mapping clfmapping "
//                    + " inner join mclf_clf_cut_off_details clfcutoff on (clfmapping.cut_off_id=clfcutoff.id and clfcutoff.clf_code= :clfCode)"
//                    + " Group By clfmapping.indctr_id,clfmapping.cut_off_id"
//                    + " ) as subquery on indi.id=subquery.indctr_id "
//                    + " where indi.section_id=:secId AND indi.status = 1  "
//                    + " order by sequence ASC";
//            Query q = entityManager.createNativeQuery(sql, IndicatorCustomEntity.class)
//                    .setParameter("clfCode", request.getParameter("clfCode")).setParameter("secId", sectionId);
//            authors = (List<IndicatorCustomEntity>) q.getResultList();
//        }
//		
//		
//		query.orderBy(criteriaBuilder.asc(state.get("stateName")), criteriaBuilder.asc(dis.get("districtName")),
//				criteriaBuilder.asc(bl.get("blockName")), criteriaBuilder.asc(clf.get("clfName")));

//		TypedQuery<TrainingCustomEntity> typedQuery = entityManager.createQuery(query);
//
//		typedQuery.setFirstResult(pageModel.getOffset());
//		typedQuery.setMaxResults(pageModel.getLimit());
		q.setFirstResult(pageModel.getOffset());
		q.setMaxResults(pageModel.getLimit());
		//authors = (List<TrainingCustomEntity>)q.getResultList();
		//List<TrainingCustomEntity> samples = q.getResultList();
		
		return trainingList;

	}
     
	@Override
	public Long getTrainingCount(TrainingFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		final Root<TrainingEntity> root = query.from(TrainingEntity.class);

		Join<ClfMaster, TrainingEntity> clf = root.join("clfmaster", JoinType.INNER);
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
		if (filterdto.getId() != null && filterdto.getId() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getId()));
		}
		if (filterdto.getStatus() != null && filterdto.getStatus() != 0) {
			
				predicates.add(criteriaBuilder.equal(root.get("trainingStatus"), filterdto.getStatus()));
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
	public TrainingEntity getTrainingByClfCodeAndDetails(TrainingRequest req){
		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<TrainingEntity> query = criteriaBuilder.createQuery(TrainingEntity.class);
		final Root<TrainingEntity> root = query.from(TrainingEntity.class);
		
		Join<TrainingEntity, TrainingMapperEntity> mapent = root.join("trainMappers", JoinType.INNER);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Integer> listint = new ArrayList<Integer>();
		
//		if(req.getId() != null && req.getId() != 0) {
//		predicates.add(criteriaBuilder.equal(root.get("id"), req.getId()));
//		}
		if(req.getClfCode() != null && !req.getClfCode().isEmpty()) {
			predicates.add(criteriaBuilder.equal(root.get("clfCode"), req.getClfCode()));
		}
		if(req.getTrainingConduct() != null && req.getTrainingConduct() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("trainingConduct"), req.getTrainingConduct()));
		}
		if(req.getModulePart() != null && req.getModulePart().size()!= 0 )  {
			
			for(HashMap<String,Integer> hashmod : req.getModulePart()	) {
				listint.add(hashmod.get("trainingModule"));
			}
			
			 predicates.add(mapent.get("trainingModule").in(listint));
			//predicates.add(criteriaBuilder.in(mapent.get("trainingModule"),req.getTrainingModule()));
		}
		if(req.getStartDate() != null) {
			predicates.add(criteriaBuilder.equal(root.get("startDate"), req.getStartDate()));
		}
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		TypedQuery<TrainingEntity> typedQuery = entityManager.createQuery(query);
		List<TrainingEntity> trainingList = typedQuery.getResultList();
		if(trainingList == null || trainingList.size() == 0) {
		return null;
		}
		return trainingList.get(0); }

	@Override
	public TrainingEntity getTrainingById(Long id) {
	
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<TrainingEntity> query = criteriaBuilder.createQuery(TrainingEntity.class);
		final Root<TrainingEntity> root = query.from(TrainingEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(criteriaBuilder.equal(root.get("id"), id));
			
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<TrainingEntity> typedQuery = entityManager.createQuery(query);

		List<TrainingEntity> trainingList = typedQuery.getResultList();
		if(trainingList == null || trainingList.size() == 0) {
			return null;
		}
		return trainingList.get(0);

	}

}


