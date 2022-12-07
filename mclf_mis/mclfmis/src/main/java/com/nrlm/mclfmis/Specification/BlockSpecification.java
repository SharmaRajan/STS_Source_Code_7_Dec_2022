package com.nrlm.mclfmis.Specification;

import com.nrlm.mclfmis.Entity.Block;
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
public class BlockSpecification {
    public Specification<Block> getBlocks(HttpServletRequest request,List<String> locations, String levelid) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (levelid.equals(ProjectConstants.STATE)) { 
            	 Join<Block, District> dis = root.join("district");
            	 Join<District, State> state = dis.join("state");
            	 predicates.add(state.get("stateCode").in(locations));
//                criteriaBuilder.in(dis.get("stateCode")).value(locations);
            }	
            if (levelid.equals(ProjectConstants.DISTRICT)) { 
            	Join<Block, District> dis = root.join("district", JoinType.INNER);
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
            query.orderBy(criteriaBuilder.asc(root.get("blockName")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
