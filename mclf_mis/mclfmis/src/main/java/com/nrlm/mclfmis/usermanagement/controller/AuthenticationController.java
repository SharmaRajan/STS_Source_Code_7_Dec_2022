package com.nrlm.mclfmis.usermanagement.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.usermanagement.constant.MyConstant;
import com.nrlm.mclfmis.usermanagement.request.LoginRequest;
import com.nrlm.mclfmis.usermanagement.request.ValidateTokenRequest;
import com.nrlm.mclfmis.usermanagement.response.LoginResponseDto;
import com.nrlm.mclfmis.usermanagement.response.ValidateTokenResponse;
import com.nrlm.mclfmis.usermanagement.service.AuthenticationService;
import com.nrlm.mclfmis.usermanagement.util.JwtUtil;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/mclf/auth")
public class AuthenticationController {
	
	private  Bucket bucket ;
	
	public AuthenticationController() {
        Bandwidth limit = Bandwidth.classic(MyConstant.API_LIMIT_NO, Refill.greedy(MyConstant.API_LIMIT_NO, Duration.ofMinutes(MyConstant.API_TIME_LIMIT)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }


	@Autowired
	AuthenticationService authService;
	
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(HttpServletRequest request)  {
		if (bucket.tryConsume(1)) {
			
			LoginRequest lrdto = null;
			try {
				lrdto = (LoginRequest)request.getAttribute("loginReq");
				request.removeAttribute("loginReq");
			} catch (Exception e1) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error!");
			}
			Map<String,String> loginrequest = new HashMap<String,String>();
			LoginResponseDto response = null;
			try {
				loginrequest.put("loginId", lrdto.getLoginId());
				loginrequest.put("password",lrdto.getPassword());
			} 
			catch (Exception e) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error!");
			}
			
			response = authService.processLogin(loginrequest,request);
			
			return new ResponseEntity<LoginResponseDto>(response, HttpStatus.OK);
		}
		else {
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
		}
		
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Response<String>> logOut(HttpServletRequest request){
		String token = null;
		Long userId = null;
		try {
			token = request.getHeader("Authorization");
			if(token != null && token.startsWith("Bearer ")) {
				
				token = token.replace("Bearer ", "");
				
				String tokenType = jwtUtil.validateApiTokenType(token);
				if(tokenType.equals(MyConstant.VALIDATED_JWT))	{
	     			userId = Long.parseLong(jwtUtil.extractTokenUsername(token));
	     		}
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
				}
				
				Response<String> response =  authService.processLogout(userId,token);
				return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
				
			}
			else {
				throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
			}
		}
		
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	
	}
	
	@GetMapping("/sessiontimer")
	public ResponseEntity<Response<Map<String,Long>>> getUserSessionTimer(HttpServletRequest request){
		String token = null;
		Long userId = null;
		try {
			token = request.getHeader("Authorization");
			if(token != null && token.startsWith("Bearer ")) {
				
				token = token.replace("Bearer ", "");
				
				String tokenType = jwtUtil.validateApiTokenType(token);
				if(tokenType.equals(MyConstant.VALIDATED_JWT))	{
	     			userId = Long.parseLong(jwtUtil.extractTokenUsername(token));
	     		}
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
				}
				
				Response<Map<String,Long>> response =  authService.getUserSessionTimer(userId,token);
				return new ResponseEntity<Response<Map<String,Long>>>(response, HttpStatus.OK);
				
			}
			else {
				throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
			}
		}
		
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	}
	
	@GetMapping("/refreshtoken")
	public ResponseEntity<Map<String,String>> refreshToken(HttpServletRequest request){
		String token = null;
		Long userId = null;
		String userLevel = null;
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			token = request.getHeader("Authorization");
			if(token != null && token.startsWith("Bearer ")) {
				
				token = token.replace("Bearer ", "");
				
				String tokenType = jwtUtil.validateApiTokenType(token);
				
				if(tokenType.equals(MyConstant.VALIDATED_JWT))	{
	     			userId = Long.parseLong(jwtUtil.extractTokenUsername(token));
	     			Claims claims =  jwtUtil.extractAllClaims(token);
	     			userLevel = claims.get("userLevel").toString();
	     		}
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
				}
				
				if(userId != null) {
					authService.isUserAuthenticated(userId,token);
					
					String refreshToken = jwtUtil.generateToken(userId, userLevel);
					authService.updateUserLastAccessAndToken(userId,refreshToken);
					map.put("refreshtoken",refreshToken);
					map.put("responseCode",String.valueOf(HttpStatus.OK.value()));
					map.put("responseDesc",HttpStatus.OK.name());
					
					return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
					
				}
				
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
				}
			}
			else {
				throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
			}
		}
		
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	}
	
	@PostMapping("/validateToken")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidateTokenResponse> validateToken(HttpServletRequest request) {
		
		boolean isAuthenticated = false;
		boolean isAuthorized = false;
		String token = null;
		Long userId = null;
		String level = null;
		try {
			token = request.getHeader("Authorization");
			if(token != null && token.startsWith("Bearer ")) {
				
				token = token.replace("Bearer ", "");
				
				String tokenType = jwtUtil.validateApiTokenType(token);
				//String tokenType = MyConstant.VALIDATED_JWT;
				if(tokenType.equals(MyConstant.VALIDATED_JWT))	{
	     			userId = Long.parseLong(jwtUtil.extractTokenUsername(token));
					//userId = 122399L;
	     		}
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
				}
			}
			else {
				throw new HandledException(HttpStatus.UNAUTHORIZED,MyConstant.UNAUTHORIZED_TOKEN_MSG);
			}
		}
		
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
		if(userId != null) {
			
			isAuthenticated =  authService.isUserAuthenticated(userId,token);
			//isAuthenticated = true;
			ValidateTokenRequest tokenRequest = null;
		
			try {
			
				tokenRequest = new ObjectMapper().readValue(request.getInputStream(), ValidateTokenRequest.class);
			}
			catch (Exception e) {
				throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
			}
			if(tokenRequest.getIsAuthorize() != null && tokenRequest.getIsAuthorize().equals("Y")) {
			
				Claims claims = null;
				claims = jwtUtil.extractAllClaims(token);
				level =  new ObjectMapper().convertValue(claims.get("userLevel"), new TypeReference<String>() {});
     			//level = "national";
     			//isAuthorized = true;
				isAuthorized = authService.isUserAuthorized(level, tokenRequest.getEndPoint());
			}
			else {
				isAuthorized = true;
			}
			if(isAuthorized) {
				
				authService.updateUserLastAccess(userId);
				
				ValidateTokenResponse response = new ValidateTokenResponse();
     			response.setUserId(userId);
     			response.setLevelName(level);
     			response.setLocations(null);
     			response.setAuthorities(null);
     			response.setResponseCode(HttpStatus.OK.value());
     			response.setResponseDesc(HttpStatus.OK.name());
     			return new ResponseEntity<ValidateTokenResponse>(response, HttpStatus.OK);
			}
			else {
				throw new HandledException(HttpStatus.FORBIDDEN,"Unauthorized User");
			}
		}
		return null;
	
	}
	
	
	/*@GetMapping("/refreshToken")
	public ResponseEntity<LoginResponseDto> refreshToken() throws Exception {
		
		AuthenticatedUserToken authUserToken = (AuthenticatedUserToken) SecurityContextHolder.getContext().getAuthentication();
		AuthenticatedUser authUser = authUserToken.getAuthenticatedUser();
		
		String jwt = "";
		LoginResponseDto response = new LoginResponseDto();
		if(authUser.getTokenType()!= null && authUser.getTokenType().equals(MyConstant.EXPIRED_JWT_EXCEPTION)){
			List<String> authList = new ArrayList<String>();
			if(authUser.getAuthorities() != null && authUser.getAuthorities().size() != 0) {
				authList = authUser.getAuthorities().stream().map(g->g.getAuthority()).collect(Collectors.toList());
			}
			//jwt = jwtUtil.generateToken(authUser.getUsername(),authList);
			jwt = "";
			//authService.updateUserDetail(authUser.getUsername(), jwt);
			response.setAccessToken(jwt);
			response.setResponseCode(HttpStatus.OK.value());
			response.setResponseDesc(HttpStatus.OK.name());
			return new ResponseEntity<LoginResponseDto>(response, HttpStatus.OK);
		}
		else {
			response.setResponseCode(HttpStatus.UNAUTHORIZED.value());
			response.setResponseDesc(HttpStatus.UNAUTHORIZED.name());
			return new ResponseEntity<LoginResponseDto>(response, HttpStatus.UNAUTHORIZED);
		}
		
	
	}*/

	

}
