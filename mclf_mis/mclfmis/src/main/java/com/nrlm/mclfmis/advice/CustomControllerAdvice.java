package com.nrlm.mclfmis.advice;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrlm.mclfmis.Response.ExceptionResponse;
import com.nrlm.mclfmis.exception.HandledException;


@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler{


	@ExceptionHandler(HandledException.class)
	public final ResponseEntity<ExceptionResponse> handleHandledException(HandledException ex, HttpServletRequest httpRequest) throws Exception {
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getCode().value(),new Date(),httpRequest.getRequestURI(),message,ex.getCode().name());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getCode());
	}
		
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest httpRequest) throws Exception {
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(),new Date(),httpRequest.getRequestURI(),message,HttpStatus.UNAUTHORIZED.name());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}
	/*
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ExceptionResponse> handleException(NullPointerException ex, HttpServletRequest httpRequest) throws Exception {
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),httpRequest.getRequestURI(),message,HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<ExceptionResponse> handleHttpClientErrorException(HttpClientErrorException ex, HttpServletRequest httpRequest) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
     	ExceptionResponse exceptionResponse = mapper.readValue(ex.getResponseBodyAsString(), ExceptionResponse.class);
     	/*if(ex.getRawStatusCode() == 400) {
     		return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.BAD_REQUEST);
     	}
     	else {*/
     		return new ResponseEntity<ExceptionResponse>(exceptionResponse,ex.getStatusCode());	
     	//}
     	
	}
	
	/*@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleException(Exception ex, HttpServletRequest httpRequest) throws Exception {
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),httpRequest.getRequestURI(),message,HttpStatus.UNAUTHORIZED.name());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
}
