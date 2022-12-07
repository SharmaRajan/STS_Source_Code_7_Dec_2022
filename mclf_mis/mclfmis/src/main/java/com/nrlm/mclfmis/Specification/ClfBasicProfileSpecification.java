package com.nrlm.mclfmis.Specification;

import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.Entity.Clfgpmapping;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Entity.VoProfile;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.ClfVoMapping;
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
public class ClfBasicProfileSpecification {
	public Specification<ClfBasicProfile> getClfProfiles(HttpServletRequest request,List<String> locations, String levelid) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			Join<ClfMaster, Block> bl =null;
			if (levelid.equals(ProjectConstants.STATE)) {
				Join<ClfBasicProfile, ClfMaster> pro = root.join("clfmaster");
				bl = pro.join("block");
				Join<Block, District> dis = bl.join("district");
				Join<District, State> state = dis.join("state");
				predicates.add(state.get("stateCode").in(locations));
			}
			if (levelid.equals(ProjectConstants.DISTRICT)) {
				Join<ClfBasicProfile, ClfMaster> pro = root.join("clfmaster");
				bl = pro.join("block");
				Join<Block, District> dis = bl.join("district", JoinType.INNER);
				predicates.add(dis.get("districtCode").in(locations));
			}
			if (levelid.equals(ProjectConstants.BLOCK)) {
				Join<ClfBasicProfile, ClfMaster> pro = root.join("clfmaster");
				bl = pro.join("block");
				predicates.add(bl.get("blockCode").in(locations));
			}
			if (levelid.equals(ProjectConstants.CLF)) {
				predicates.add(root.get("clfCode").in(locations));
			}			
			if (request.getParameter("statecode") != null) {
				predicates.add(criteriaBuilder.equal(bl.get("stateCode"), request.getParameter("statecode")));
			}
			if (request.getParameter("discode") != null) {
				predicates.add(criteriaBuilder.equal(bl.get("districtCode"), request.getParameter("discode")));
			}
			if (request.getParameter("blockcode") != null) {
				predicates.add(criteriaBuilder.equal(bl.get("blockCode"), request.getParameter("blockcode")));
			}
			if (request.getParameter("clfcode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("clfCode"), request.getParameter("clfcode")));
			}
			query.orderBy(criteriaBuilder.asc(root.get("clfCode")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
