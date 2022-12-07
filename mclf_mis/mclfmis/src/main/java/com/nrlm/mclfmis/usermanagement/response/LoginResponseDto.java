package com.nrlm.mclfmis.usermanagement.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class LoginResponseDto {

	private String token;
	private Map<String,Object> userInfo;
	private Map<String,Object> userGeo;
	@JsonIgnore
	private List<String> authorities;
	private Integer responseCode;
	private String responseDesc;
	
	private Map<String,Object> permissions;
	
	
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public Map<String,Object> getPermissions() {
		return permissions;
	}
	public void setPermissions(Map<String,Object> permissions) {
		this.permissions = permissions;
	}
	public Map<String,Object> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(Map<String,Object> userInfo) {
		this.userInfo = userInfo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Map<String,Object> getUserGeo() {
		return userGeo;
	}
	public void setUserGeo(Map<String,Object> userGeo) {
		this.userGeo = userGeo;
	}
	
	
	
}
