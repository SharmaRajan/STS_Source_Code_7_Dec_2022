package com.nic.nrlm_aajeevika.usermanagement.service;


public interface CaptchaService {
	
	boolean saveCaptcha(String sessionId, String sImageCode);

}
