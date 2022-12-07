package com.nic.nrlm_aajeevika.specification;

import com.nic.nrlm_aajeevika.entity.SubModule;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class SubModuleSpecification {
    public Specification<SubModule> getSubModules(HttpServletRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getParameter("id") != null ) {
            	predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
           } 
            if (request.getParameter("moduleId") != null ) {
            	predicates.add(criteriaBuilder.equal(root.get("moduleId"), request.getParameter("moduleId")));
           } 
     
            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    
        };
    }
    


}
