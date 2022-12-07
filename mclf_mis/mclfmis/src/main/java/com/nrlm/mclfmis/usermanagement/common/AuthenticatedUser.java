package com.nrlm.mclfmis.usermanagement.common;

import java.util.List;

public class AuthenticatedUser {

	private Long userId;
	private List<String> locations;
	private String levelName;
	
	public AuthenticatedUser() {
		
	}
	
	public AuthenticatedUser(Long userId, List<String> locations,String levelName){
		this.userId = userId;
		this.locations = locations;
		this.levelName = levelName;
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	

}
