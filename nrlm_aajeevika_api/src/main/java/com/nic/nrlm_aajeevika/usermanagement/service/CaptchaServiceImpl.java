package com.nic.nrlm_aajeevika.usermanagement.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.usermanagement.entity.CaptchaEntity;
import com.nic.nrlm_aajeevika.usermanagement.repository.CaptchaRepository;

@Service
public class CaptchaServiceImpl implements CaptchaService{
	


	@Autowired
	CaptchaRepository captchaRepository;
	
	@Override
	public boolean saveCaptcha(String sessionId, String captchaString) {
		try {
			CaptchaEntity ceObj = captchaRepository.findBySessionId(sessionId);
			System.out.println("----------------"+sessionId);
			CaptchaEntity ce = null;
			if(ceObj != null && ceObj.getId() != 0) {
				ce = ceObj;
				
			}
			else {
				ce = new CaptchaEntity();
				ce.setSessionId(sessionId);
			}
			ce.setCaptcha(captchaString);
			ce.setCreatedAt(new Date());
			
			if(captchaRepository.save(ce).getId() != 0) {
				return true;
			}
			else {
				throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Captcha not created");
			}
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

}
}
