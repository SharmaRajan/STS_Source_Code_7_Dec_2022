package com.nrlm.mclfmis.usermanagement.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrlm.mclfmis.usermanagement.constant.MyConstant;
import com.nrlm.mclfmis.usermanagement.entity.CaptchaEntity;
import com.nrlm.mclfmis.usermanagement.repository.CaptchaRepository;
import com.nrlm.mclfmis.usermanagement.request.LoginRequest;
import com.nrlm.mclfmis.usermanagement.response.AuthenticationExceptionResponse;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

	@Autowired
	CaptchaRepository captchaRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getRequestURI().equals(MyConstant.LOGIN_URL)) {
			boolean authFlag = false;
			String msg = null;
			LoginRequest lrdto = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			request.setAttribute("loginReq", lrdto);
			if(lrdto.getCaptcha() == null || lrdto.getCaptcha().isEmpty()) {
				msg = "Missing Captcha";
				authFlag = false;
			}
			else if(lrdto.getSessionId() == null || lrdto.getSessionId().isEmpty()) {
				msg = "Missing Session Info";
				authFlag = false;
			}
			else {
				CaptchaEntity ce = captchaRepository.findBySessionId(lrdto.getSessionId());
				if(ce != null && ce.getId() != 0) {
					/*if((new Date().getTime() - ce.getCreatedAt().getTime()) > (10*60*1000)) {
						
						 msg = "Captcha Expired!";
						authFlag = false;
					}*/
					if(!lrdto.getCaptcha().equals(ce.getCaptcha())) {
						msg = "Invalid Captcha";
						authFlag = false;
					}
					else {
						authFlag = true;
					}
				}
				else {
					msg = "No Captcha Found";
					authFlag = false;
				}
			}
			
			if(authFlag) {
				filterChain.doFilter(request, response);
			}
			else {
				AuthenticationExceptionResponse ex = new AuthenticationExceptionResponse(HttpStatus.UNAUTHORIZED.value(),new Date(),request.getRequestURI(),msg,HttpStatus.UNAUTHORIZED.name());
    			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	        final ObjectMapper mapper = new ObjectMapper();
    	        mapper.writeValue(response.getOutputStream(), ex);
			}
		}
		else {
			filterChain.doFilter(request, response);
		}
		
	}

}
