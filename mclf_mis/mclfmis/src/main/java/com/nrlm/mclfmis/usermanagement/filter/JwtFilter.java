package com.nrlm.mclfmis.usermanagement.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.usermanagement.constant.MyConstant;
import com.nrlm.mclfmis.usermanagement.entity.UserEntity;
import com.nrlm.mclfmis.usermanagement.repository.AuthenticationRepository;
import com.nrlm.mclfmis.usermanagement.response.AuthenticationExceptionResponse;
import com.nrlm.mclfmis.usermanagement.util.JwtUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import io.jsonwebtoken.Claims;


@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationRepository authRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException,UsernameNotFoundException {
		
		String uri = request.getRequestURI();
		HttpStatus httpStatus = null;
        if(uri.equals(MyConstant.LOGIN_URL)) {
        	 filterChain.doFilter(request, response);
		}
        else if(uri.equals("/mclf/captcha")) {
       	 	filterChain.doFilter(request, response);
		}
        else if(uri.contains("/mclf/auth")) {
       	 	filterChain.doFilter(request, response);
		}
		/*
		 * else if(uri.equals("/mclf/auth/sessiontimer")) {
		 * filterChain.doFilter(request, response); } else
		 * if(uri.equals("/mclf/auth/logout")) { filterChain.doFilter(request,
		 * response); } else if(uri.equals("/mclf/auth/refreshtoken")) {
		 * filterChain.doFilter(request, response); }
		 */
        else {
        	String jwt = getJwt(request);
 	        Long userId = null;
 	        String tokenType = null;
 	        String msg = null;
 	        Claims claims = null;
 	        AuthenticatedUser authenticatedUser = null;
 	        
 	        if(jwt != null) {
 	        	//userId = 122399L;
 	        	
 	        	tokenType = jwtUtil.validateApiTokenType(jwt);
 	        	if(tokenType.equals(MyConstant.VALIDATED_JWT))	{
         			userId = Long.parseLong(jwtUtil.extractTokenUsername(jwt));
         			
         		}
         		else {
         			 msg = MyConstant.UNAUTHORIZED_TOKEN_MSG;
         		}	
 	        }
 	        else {
 	        	msg = MyConstant.UNAUTHORIZED_TOKEN_MSG;
 	        }
 	        
 	        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
 	        	boolean authUserFlag = false;
 	        	String level = null;
        		Optional<UserEntity> optionalUser = authRepository.findByUserId(userId);
        		if(optionalUser.isPresent()) {
        			UserEntity user = optionalUser.get();
        			if(user.getLoginStatus() != null && user.getLoginStatus() != 0) {
    					if(user.getToken().equals(jwt)) {
    						if(user.getLastAccess() != null) {
    							Long lastTimeDiff = System.currentTimeMillis() - user.getLastAccess().getTime();
        						if(lastTimeDiff > (MyConstant.LAST_ACCESS_LIMIT_SECONDS*1000)) {
        							authUserFlag = false;
                     				httpStatus = HttpStatus.UNAUTHORIZED;
                    				msg = "Session Expired.Login again to continue";
            					}
        						else {
    								claims = jwtUtil.extractAllClaims(jwt);
                    				level =  new ObjectMapper().convertValue(claims.get("userLevel"), new TypeReference<String>() {});
                         			Long authFlag = authRepository.checkUserAuthoritiesByLevelAndUri(level, uri);
                         			if(authFlag != 0) {
                         				authUserFlag = true;
                         			}
                         			else {
                         				authUserFlag = false;
                         				httpStatus = HttpStatus.FORBIDDEN;
                        				msg = MyConstant.UNAUTHORIZED_USER;
                         			}	
        						}
    						}
    						else {
    							authUserFlag = false;
    	            			httpStatus = HttpStatus.UNAUTHORIZED;
    	        				msg = MyConstant.UNAUTHORIZED_TOKEN_MSG;
    						}
    						
        	       		}
    					else {
    						authUserFlag = false;
                			httpStatus = HttpStatus.UNAUTHORIZED;
            				msg = MyConstant.UNAUTHORIZED_TOKEN_MSG;
    					}
    				}
        			else {
        				authUserFlag = false;
            			httpStatus = HttpStatus.UNAUTHORIZED;
        				msg = MyConstant.UNAUTHORIZED_TOKEN_MSG;
        			}
        		}
        		else {
        			authUserFlag = false;
        			httpStatus = HttpStatus.UNAUTHORIZED;
    				msg = MyConstant.UNAUTHORIZED_TOKEN_MSG;
    			}
        		
        		//String level = "national";
     			//boolean authUserFlag = true;
     			
	       
 	        	if(authUserFlag) {
 	        		authRepository.updateUserLastAccess(userId);
 	        		authenticatedUser = new AuthenticatedUser(userId, null, level);
 	        		
 	        		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authenticatedUser,null,null);
                    
 	        		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                    filterChain.doFilter(request, response);
 	        	}
 	        	else {
 	        			AuthenticationExceptionResponse ex = new AuthenticationExceptionResponse(httpStatus.value(),new Date(),uri,msg,httpStatus.name());
 	         			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
 	         			response.setStatus(httpStatus.value());
 	         	        final ObjectMapper mapper = new ObjectMapper();
 	         	        mapper.writeValue(response.getOutputStream(), ex);
 	        	}
 	        }
 	        else {
 	        	AuthenticationExceptionResponse ex = new AuthenticationExceptionResponse(HttpStatus.UNAUTHORIZED.value(),new Date(),request.getRequestURI(),msg,HttpStatus.UNAUTHORIZED.name());
     			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
     			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     	        final ObjectMapper mapper = new ObjectMapper();
     	        mapper.writeValue(response.getOutputStream(), ex);
 	        }
        }
	}
	
	private String getJwt(HttpServletRequest request) {
		
        String authHeader = request.getHeader(MyConstant.AUTHORIZATION_HDR);
        	
        if (authHeader != null && authHeader.startsWith(MyConstant.TOKEN_STARTS_WITH)) {
        	return authHeader.replace(MyConstant.TOKEN_STARTS_WITH,"");
        }

        return null;
    }
	
}


