package com.nic.nrlm_aajeevika.specification;

import com.nic.nrlm_aajeevika.entity.Content;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminContentSpecification {
    public Specification<Content> getAdminContents(HttpServletRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getParameter("moduleId") != null) {
                predicates.add(criteriaBuilder.equal(root.get("moduleId"), request.getParameter("moduleId")));
            }
            if (request.getParameter("id") != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
            }
            if (request.getParameter("subModuleId") != null && !request.getParameter("subModuleId").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("subModuleId"), request.getParameter("subModuleId")));
            }
            if (request.getParameter("isUpdate") != null && !request.getParameter("isUpdate").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("isUpdate"), 1));
            }
            if (request.getParameter("isHighlight") != null && !request.getParameter("isHighlight").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("isHighlight"), 1));
            }
            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
