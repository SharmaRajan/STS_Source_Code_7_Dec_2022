package com.nrlm.mclfmis.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.nrlm.mclfmis.Entity.ClfPoliciesEntity;
import com.nrlm.mclfmis.Request.ClfPoliciesRequest;

@Component
public class ClfpoliciesSpecification {

	public Specification<ClfPoliciesEntity> getSectionList(ClfPoliciesRequest filterdto) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (filterdto.getClfCode() != null) {
				predicates.add(criteriaBuilder.equal(root.get("clfCode"), filterdto.getClfCode()));
			}
			if (filterdto.getCboHrPolicies() != 0 ) {
			predicates.add(criteriaBuilder.equal(root.get("cboHrPolicies"), filterdto.getCboHrPolicies()));
			}
			if (filterdto.getGovPolicies() != 0 ) {
			predicates.add(criteriaBuilder.equal(root.get("govPolicies"), filterdto.getGovPolicies()));
			}
			if (filterdto.getFinPolicies() != 0 ) {
			predicates.add(criteriaBuilder.equal(root.get("finPolicies"), filterdto.getFinPolicies()));
			}
			
//			if (filterdto.getStatus() != 0 ) {
//				predicates.add(criteriaBuilder.equal(root.get("status"), filterdto.getStatus()));
//			}
			
			query.orderBy(criteriaBuilder.asc(root.get("clfCode")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
