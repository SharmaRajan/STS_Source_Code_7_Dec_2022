package com.nrlm.mclfmis.usermanagement.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.usermanagement.entity.UserEntity;
import com.nrlm.mclfmis.usermanagement.response.LoginResponseDto;
import com.nrlm.mclfmis.usermanagement.response.ValidateTokenResponse;

public interface AuthenticationService {

	List<String> getUserAuthorities(String level);

	Map<String,Object> getModulePermissions(String level);

	LoginResponseDto processLogin(Map<String, String> loginrequest, HttpServletRequest request);


	boolean isUserAuthenticated(Long userId,String token);

	boolean isUserAuthorized(String level, String endPoint);

	void updateUserLastAccess(Long userId);

	Response<String> processLogout(Long userId, String token);

	Response<Map<String, Long>> getUserSessionTimer(Long userId, String token);

	void updateUserLastAccessAndToken(Long userId, String refreshToken);

}
