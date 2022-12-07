package com.nic.nrlm_aajeevika.usermanagement.common;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class AuthenticatedUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginId;
	private String password;
	private List<GrantedAuthority> ga;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialNonExpired;
	private boolean isEnabled;
	private String userToken;
	private String tokenType;
	private Integer levelId;
	private String levelName;
	private Long userId;
	
	public AuthenticatedUser() {
		
	}
	public AuthenticatedUser(String loginId,String password,List<GrantedAuthority> ga,
			boolean isAccountNonExpired,boolean isAccountNonLocked,boolean isCredentialNonExpired,boolean isEnabled){
		this.loginId = loginId;
		this.password = password;
		this.ga = ga;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialNonExpired = isCredentialNonExpired;
		this.isEnabled = isEnabled;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.ga;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.loginId;
	}

	@Override
	public boolean isAccountNonExpired() { //AccountExpiredException
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() { //LockedException
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() { //CredentialsExpiredException
		return this.isCredentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}
	

	public void setUsername(String loginId) {
		this.loginId = loginId;
	}

	public void setAuthorities(List<GrantedAuthority> ga) {
		this.ga = ga;
	}
	public void setCredentialNonExpired(boolean isCredentialNonExpired) {
		this.isCredentialNonExpired = isCredentialNonExpired;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	@Override
	public String toString() {
		return "AuthenticatedUser [loginId=" + loginId + ", password=" + password + ", ga=" + ga
				+ ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked
				+ ", isCredentialNonExpired=" + isCredentialNonExpired + ", isEnabled=" + isEnabled + ", userToken="
				+ userToken + "]";
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
}
