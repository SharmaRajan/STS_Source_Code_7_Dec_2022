package com.nrlm.mclfmis.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrlm.mclfmis.usermanagement.entity.CaptchaEntity;

@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaEntity, Long> {

	CaptchaEntity  findBySessionId(String sessionId);


}
