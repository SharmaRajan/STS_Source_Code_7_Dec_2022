package com.nrlm.mclfmis.usermanagement.service;

public interface CaptchaService {

	boolean saveCaptcha(String sessionId, String sImageCode);

}
