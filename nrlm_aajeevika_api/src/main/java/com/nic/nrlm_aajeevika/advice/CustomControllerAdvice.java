package com.nic.nrlm_aajeevika.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(HandledException.class)
    public final ResponseEntity<ExceptionResponse> handleHandledException(HandledException ex, HttpServletRequest httpRequest) throws Exception {
        String message = ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getCode().value(), new Date(), httpRequest.getRequestURI(), message, ex.getCode().name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest httpRequest) throws Exception {
        String message = ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
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
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getStatusCode());
        //}

    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    public ResponseEntity<ExceptionResponse> fileUploadException(MaxUploadSizeExceededException ex, HttpServletRequest request) {
        String message = "File size too large to upload";
        ExceptionResponse response = new ExceptionResponse(HttpStatus.PAYLOAD_TOO_LARGE.value(), new Date(),
                request.getRequestURI(), message, HttpStatus.PAYLOAD_TOO_LARGE.name());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.PAYLOAD_TOO_LARGE);
    }

//    @ExceptionHandler(InputMismatchException.class)
//    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
//    public ResponseEntity<ExceptionResponse> handleInputTypeFieldException(InputMismatchException ex, HttpServletRequest request){
//    	String message = "Please enter valid input";
//    	ExceptionResponse response =  new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), 
//    			request.getRequestURI(), message, HttpStatus.NOT_ACCEPTABLE.name());
//    	
//    	return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
//    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String message = "Please provide valid parameters";
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), new Date(),
                request.getRequestURI(), message, HttpStatus.NOT_ACCEPTABLE.name());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
    }


////    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
//    public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
//    	String message = "Please enter valid input";
//    	ExceptionResponse response =  new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), new Date(), 
//    			request.getRequestURI(), message, HttpStatus.METHOD_NOT_ALLOWED.name());
//    	
//    	return new ResponseEntity<ExceptionResponse>(response, HttpStatus.METHOD_NOT_ALLOWED);
//    }
    
    
    
    

    
    
    
	/*@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleException(Exception ex, HttpServletRequest httpRequest) throws Exception {
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),httpRequest.getRequestURI(),message,HttpStatus.UNAUTHORIZED.name());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

}
