package com.nrlm.mclfmis.Helper;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.nrlm.mclfmis.Response.Response;

@Component
public class MakeRestTemplateRequest {

	@Autowired
	RestTemplate restTemplate;
	
	public  <T> ResponseEntity<Response<T>> makeRequest(String uri,HttpServletRequest request, final Class<T> clazz) {
		HttpHeaders headers = new HttpHeaders();
    	headers.set(ProjectConstants.AUTHORIZATION_HDR, request.getHeader(ProjectConstants.AUTHORIZATION_HDR));
    	HttpEntity<String> requestEntity = new HttpEntity<>(request.getParameterMap().toString(), headers);
    	
    	UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
    	Map<String, String[]> param = request.getParameterMap();
    	for(Map.Entry<String, String[]> entry : param.entrySet()) {
    		builder.queryParam(entry.getKey(), entry.getValue()[0]);
    	}
    
	   ResponseEntity<Response<T>> response = restTemplate.exchange(
			  builder.toUriString(),
	        HttpMethod.GET,
	        requestEntity,
	        new ParameterizedTypeReference<Response<T>>() {
	            public Type getType() {
	                return new MyParameterizedTypeImpl((ParameterizedType) super.getType(), new Type[] {clazz});
	        }
	    });
	    return response;
	}
	
	public  <T> ResponseEntity<T> makeRequest1(String uri,HttpServletRequest request, final Class<T> clazz) {
		HttpHeaders headers = new HttpHeaders();
    	headers.set(ProjectConstants.AUTHORIZATION_HDR, request.getHeader(ProjectConstants.AUTHORIZATION_HDR));
    	HttpEntity<String> requestEntity = new HttpEntity<>(request.getParameterMap().toString(), headers);
    	
    	UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
    	Map<String, String[]> param = request.getParameterMap();
    	for(Map.Entry<String, String[]> entry : param.entrySet()) {
    		builder.queryParam(entry.getKey(), entry.getValue()[0]);
    	}
    
	   ResponseEntity<T> response = restTemplate.exchange(
			  builder.toUriString(),
	        HttpMethod.GET,
	        requestEntity,
	        new ParameterizedTypeReference<T>() {
	            public Type getType() {
	                return new MyParameterizedTypeImpl((ParameterizedType) super.getClass().getGenericSuperclass(), new Type[] {clazz});
	        }
	    });
	    return response;
	}
}
