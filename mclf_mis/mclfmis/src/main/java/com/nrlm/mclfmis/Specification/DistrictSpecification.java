package com.nrlm.mclfmis.Specification;

import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class DistrictSpecification {

	public Specification<District> getDistricts(HttpServletRequest request, List<String> locations, Integer levelid) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (levelid.equals(ProjectConstants.STATE)) {
				Join<District, State> state = root.join("state", JoinType.INNER);
				predicates.add(state.get("stateCode").in(locations));
//                criteriaBuilder.in(root.get("stateCode")).value(locations);
			}
			if (levelid.equals(ProjectConstants.DISTRICT)) {
				predicates.add(root.get("districtCode").in(locations));
			}
//    alternate method to get parameter  from request instead of method parameter       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			if (request.getParameter("statecode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("stateCode"), request.getParameter("statecode")));
			}
			if (request.getParameter("discode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("districtCode"), request.getParameter("discode")));
			}

			query.orderBy(criteriaBuilder.asc(root.get("districtName")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
