package com.nrlm.mclfmis.Specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.nrlm.mclfmis.Entity.Block;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.District;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Entity.State;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.SectionRequest;

@Component
public class SectionSpecification {

	public Specification<SectionEntity> getSectionList(SectionRequest filterdto) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(criteriaBuilder.equal(root.get("tabType"), filterdto.getTabId()));
			
			if (filterdto.getSectionId() != null) {
				predicates.add(criteriaBuilder.equal(root.get("id"), filterdto.getSectionId()));
			}
			if (filterdto.getSectionName() != null) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("sectionName")),"%"+filterdto.getSectionName().toLowerCase()+"%"));

			}
			if (filterdto.getStatus() != null) {
				predicates.add(criteriaBuilder.equal(root.get("sectionStatus"), filterdto.getStatus()));
			}
			if(filterdto.getSortCol() != null) {
				if(filterdto.getSortOrder() != null && filterdto.getSortOrder().equals("desc")) {
					query.orderBy(criteriaBuilder.desc(root.get(filterdto.getSortCol())));
				}
				else {
					query.orderBy(criteriaBuilder.asc(root.get(filterdto.getSortCol())));
				}
			}
			else {
				query.orderBy(criteriaBuilder.asc(root.get("sequence")));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
