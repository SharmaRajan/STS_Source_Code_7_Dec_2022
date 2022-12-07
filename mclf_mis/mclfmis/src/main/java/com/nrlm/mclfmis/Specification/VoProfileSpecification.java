package com.nrlm.mclfmis.Specification;

import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.Grampanchayat;
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
public class VoProfileSpecification {
	public Specification<VoProfile> getVoProfiles(HttpServletRequest request,List<String> locations, Integer levelid) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			Join<Grampanchayat, Block> bl = null;
			if (levelid.equals(ProjectConstants.STATE)) {
				Join<VoProfile,Grampanchayat> gps = root.join("gp");
				bl = gps.join("block");
				Join<Block, District> dis = bl.join("district");
				Join<District, State> state = dis.join("state");
				predicates.add(state.get("stateCode").in(locations));
//                criteriaBuilder.in(dis.get("stateCode")).value(locations);
			}
			if (levelid.equals(ProjectConstants.DISTRICT)) {
				Join<VoProfile,Grampanchayat> gps = root.join("gp");
				bl = gps.join("block");
				Join<Block, District> dis = bl.join("district", JoinType.INNER);
				predicates.add(dis.get("districtCode").in(locations));
			}
			if (levelid.equals(ProjectConstants.BLOCK)) {
				Join<VoProfile,Grampanchayat> gps = root.join("gp");
				bl = gps.join("block");
				predicates.add(bl.get("blockCode").in(locations));
			}
			if (levelid.equals(ProjectConstants.CLF)) {
				Join<VoProfile,ClfVoMapping> vos = root.join("voprofile");
				Join<ClfVoMapping, ClfMaster> clfs = vos.join("clfmaster");
				predicates.add(clfs.get("clfCode").in(locations));
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
			if (request.getParameter("gpcode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("gpCode"), request.getParameter("gpcode")));
			}
			if (request.getParameter("vocode") != null) {
				predicates.add(criteriaBuilder.equal(root.get("voCode"), request.getParameter("vocode")));
			}
			query.orderBy(criteriaBuilder.asc(root.get("voName")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
