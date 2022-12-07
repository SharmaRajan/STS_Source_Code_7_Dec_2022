package com.nrlm.mclfmis.Repository;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import com.nrlm.mclfmis.Entity.State;
//
//import javax.persistence.criteria.CriteriaBuilder;
//
//@Repository
//public class StateRepositoryImpl {
//
//    @Override
//    List<State> findAllBylocationids(List<Integer> locations, Integer levelid, Pageable pageable) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<State> cq = cb.createQuery(State.class);
//
//        Root<State> state = cq.from(State.class);
//        List<Predicate> predicates = new ArrayList<>();
//        if (authorName != null) {
//            predicates.add(cb.equal(state.get(""), authorName));
//        }
//        if (authorName != null) {
//            predicates.add(cb.equal(state.get(""), authorName));
//        }
//        if (title != null) {
//            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
//        }
//        cq.where(predicates.toArray(new Predicate[0]));
//
//        return em.createQuery(cq).getResultList();
//    }
//}
