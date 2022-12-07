package com.nrlm.mclfmis.Specification;

import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.Grampanchayat;
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
public class GpSpecification {
	public Specification<Grampanchayat> getGps(HttpServletRequest request,List<String> locations, Integer levelid) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (levelid.equals(ProjectConstants.STATE)) {
				Join<Grampanchayat, Block> bl = root.join("block");
				Join<Block, District> dis = bl.join("district");
				Join<District, State> state = dis.join("state");
				predicates.add(state.get("stateCode").in(locations));
//                criteriaBuilder.in(dis.get("stateCode")).value(locations);
			}
			if (levelid.equals(ProjectConstants.DISTRICT)) {
				Join<Grampanchayat, Block> bl = root.join("block");
				Join<Block, District> dis = bl.join("district", JoinType.INNER);
				predicates.add(dis.get("districtCode").in(locations));
			}
			if (levelid.equals(ProjectConstants.BLOCK)) {
				predicates.add(root.get("blockCode").in(locations));
			}
			if (request.getParameter("statecode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("stateCode"), request.getParameter("statecode")));
			}
			if (request.getParameter("discode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("districtCode"), request.getParameter("discode")));
			}
			if (request.getParameter("blockcode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("blockCode"), request.getParameter("blockcode")));
			}
			if (request.getParameter("gpcode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("grampanchayatCode"), request.getParameter("gpcode")));
			}
			query.orderBy(criteriaBuilder.asc(root.get("grampanchayatName")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
