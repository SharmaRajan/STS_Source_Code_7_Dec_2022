package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.entity.Faq;
import com.nic.nrlm_aajeevika.entity.TickerPopupSlider;
import com.nic.nrlm_aajeevika.usermanagement.entity.User;
import com.nic.nrlm_aajeevika.usermanagement.entity.UserLevel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<TickerPopupSlider, Long>, JpaSpecificationExecutor<TickerPopupSlider> {

    Page<TickerPopupSlider> findAll(Specification<TickerPopupSlider> spec, Pageable pageable);
    
    
    @Query(nativeQuery = true, value = "Select count(id) as ranking from ticker_popup_slider where ranking = :ranking")
    Integer findCountRanking(Integer ranking);

//    
//    @Query(nativeQuery = true, value = "Select count(id) as ranking from ticker_popup_slider where content_type = :contenttype")
//    Integer findCountRanking(Integer contenttype);

  

}
