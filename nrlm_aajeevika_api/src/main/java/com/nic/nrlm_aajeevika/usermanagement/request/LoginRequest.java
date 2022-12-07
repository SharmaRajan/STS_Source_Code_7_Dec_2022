package com.nic.nrlm_aajeevika.usermanagement.request;

public class LoginRequest {


	private String loginId;
	private String password;
	private String sessionId;
	private String captcha;
	private String noCaptcha;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getNoCaptcha() {
		return noCaptcha;
	}
	public void setNoCaptcha(String noCaptcha) {
		this.noCaptcha = noCaptcha;
	}
	
	
}
