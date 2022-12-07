package com.nic.nrlm_aajeevika.specification;

import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.entity.Faq;
import com.nic.nrlm_aajeevika.entity.TickerPopupSlider;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class TickerSpecification {
    public Specification<TickerPopupSlider> getticker(HttpServletRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), "1"));
            if (request.getParameter("id") != null ) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
            }
          
            if (request.getParameter("contentType") != null && !request.getParameter("contentType").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("contentType"), request.getParameter("contentType")));
            }
            if (request.getParameter("ranking") != null && !request.getParameter("ranking").isEmpty()) {
            	predicates.add(criteriaBuilder.equal(root.get("ranking"), request.getParameter("ranking")));
            }
        
      
            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
