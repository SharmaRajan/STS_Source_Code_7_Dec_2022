package com.nic.nrlm_aajeevika.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.nrlm_aajeevika.usermanagement.entity.CaptchaEntity;
import com.nic.nrlm_aajeevika.usermanagement.entity.LoginHistory;



@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {


}
