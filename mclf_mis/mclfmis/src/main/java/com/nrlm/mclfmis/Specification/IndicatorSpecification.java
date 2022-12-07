package com.nrlm.mclfmis.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Entity.SectionEntity;

@Component
public class IndicatorSpecification {
	public Specification<Indicator> getIndicators(Integer tabid,HttpServletRequest request) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
//	    alternate method to get parameter  from request instead of method parameter       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			Join<Indicator, SectionEntity> sec = root.join("sectionEntity");
			predicates.add(criteriaBuilder.equal(sec.get("tabType"), tabid));
			if (request.getParameter("id") != null) {
				predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
			}
			if (request.getParameter("sectionId") != null && !request.getParameter("sectionId").isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("sectionId"), request.getParameter("sectionId")));
			}
			if (request.getParameter("indctrName") != null) {
				predicates.add(criteriaBuilder.like(root.get("indctrName"), "%" +request.getParameter("indctrName")+"%" ));
			}
			if (request.getParameter("indctrType") != null && !request.getParameter("indctrType").isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("indctrType"), request.getParameter("indctrType")));
			}
			if (request.getParameter("startMonth") != null && !request.getParameter("startMonth").isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("startMonth"), request.getParameter("startMonth")));
//				predicates.add(criteriaBuilder.equal(root.get("startFnclYear"), request.getParameter("startFnclYear")));
			}
			if (request.getParameter("status") != null && !request.getParameter("status").isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("status"), request.getParameter("status")));
			}
			if(request.getParameter("sortCol") != null) {
				if(request.getParameter("sortOrder")  != null && request.getParameter("sortOrder").equals("desc")) {
					query.orderBy(criteriaBuilder.desc(root.get(request.getParameter("sortCol"))));
				}
				else {
					query.orderBy(criteriaBuilder.asc(root.get(request.getParameter("sortCol"))));
				}
			}
			else {
				query.orderBy(criteriaBuilder.asc(root.get("sequence")));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
	
	public Specification<Indicator> getIndicatorSequenceList(Long sectionId) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

				predicates.add(criteriaBuilder.equal(root.get("sectionId"),sectionId));
			
				query.orderBy(criteriaBuilder.asc(root.get("sequence")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
