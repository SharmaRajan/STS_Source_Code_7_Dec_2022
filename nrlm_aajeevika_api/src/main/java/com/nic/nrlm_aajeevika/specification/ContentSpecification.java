package com.nic.nrlm_aajeevika.specification;

import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.exception.HandledException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ContentSpecification {
    public Specification<Content> getContents(HttpServletRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
//	    alternate method to get parameter  from request instead of method parameter       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
            predicates.add(criteriaBuilder.equal(root.get("moduleId"), request.getParameter("moduleId")));
            predicates.add(criteriaBuilder.equal(root.get("approvedRejectedStatus"), 1));
         
            if (request.getParameter("id") != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
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


            if (request.getParameter("issuedNo") != null) {
                predicates.add(criteriaBuilder.equal(root.get("issuedNo"), request.getParameter("issuedNo")));
            }
            if (request.getParameter("publishedBy") != null && !request.getParameter("publishedBy").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("publishedBy"), request.getParameter("publishedBy")));
            }
            if (request.getParameter("author") != null && !request.getParameter("author").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("author"), request.getParameter("author")));
            }
            if (request.getParameter("source") != null && !request.getParameter("source").isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("source"), request.getParameter("source")));
            }
            if (request.getParameter("publishedDateFrom") != null && !request.getParameter("publishedDateFrom").isEmpty()) {
                try {
                    Date processingDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("publishedDateFrom"));
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("publishedDate"), processingDate));

                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, "From date is in wrong format.");
                }
            }
            if (request.getParameter("publishedDateTo") != null && !request.getParameter("publishedDateTo").isEmpty()) {
                try {
                    Date processingDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("publishedDateTo"));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("publishedDate"), processingDate));

                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, "to date is in wrong format.");
                }
            }
            
           
            
            
            if (request.getParameter("issuedDate") != null && !request.getParameter("issuedDate").isEmpty()) {
                try {
                    Date processingDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("issuedDate"));
                    predicates.add(criteriaBuilder.equal(root.get("issuedDate"), processingDate));
                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, "Issued date is in wrong format.");
                }
            }
            if (request.getParameter("publishedDate") != null && !request.getParameter("publishedDate").isEmpty()) {
                try {
                    Date processingDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("publishedDate"));
                    predicates.add(criteriaBuilder.equal(root.get("publishedDate"),processingDate));
                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, "Published date is in wrong format.");
                }
            }
            if (request.getParameter("uploadedOn") != null && !request.getParameter("uploadedOn").isEmpty()) {
                try {
                    Date processingDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("uploadedOn"));
                    predicates.add(criteriaBuilder.equal(root.get("uploadedOn"), processingDate));
                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, "Published date is in wrong format.");
                }
            }
            if (request.getParameter("month") != null && !request.getParameter("month").isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("MONTH", Integer.class, root.get("publishedDate")), Integer.parseInt(request.getParameter("month"))));
            }
            if (request.getParameter("year") != null && !request.getParameter("year").isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class, root.get("publishedDate")), Integer.parseInt(request.getParameter("year"))));
            }

            if (request.getParameter("globalSearch") != null && !request.getParameter("globalSearch").isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + request.getParameter("globalSearch") + "%")
                ));
            }
            Date date = new Date();
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.isNull(root.get("autoExpiry")),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("autoExpiry"), date)
            ));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), "1"));
            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Content> getSearchContent(HttpServletRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getParameter("moduleId") != null) {
                predicates.add(criteriaBuilder.equal(root.get("moduleId"), request.getParameter("moduleId")));
            }
            if (request.getParameter("id") != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getParameter("id")));
            }
            if (request.getParameter("globalSearch") != null && !request.getParameter("globalSearch").isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("author"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("documentSource"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("location"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("source"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("rtiQuery"), "%" + request.getParameter("globalSearch") + "%"),
                        criteriaBuilder.like(root.get("publishedBy"), "%" + request.getParameter("globalSearch") + "%")
                 ));
            }
            Date date = new Date();
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.isNull(root.get("autoExpiry")),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("autoExpiry"), date)
            ));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), "1"));
            query.orderBy(criteriaBuilder.desc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
