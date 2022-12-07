package com.nic.nrlm_aajeevika.specification;

import com.nic.nrlm_aajeevika.entity.Module;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleSpecification {
    public Specification<Module> getModules(HttpServletRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            	predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
           }
            if (request.getParameter("moduleName") != null ) {
                predicates.add(criteriaBuilder.equal(root.get("moduleName"), request.getParameter("moduleName")));
            }

            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    
        };
    }
    
    

}
