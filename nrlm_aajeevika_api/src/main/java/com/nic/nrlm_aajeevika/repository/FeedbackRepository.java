package com.nic.nrlm_aajeevika.repository;

import com.nic.nrlm_aajeevika.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {


}
