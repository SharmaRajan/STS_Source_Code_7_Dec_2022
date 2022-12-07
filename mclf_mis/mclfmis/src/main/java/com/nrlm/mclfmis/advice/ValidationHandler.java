package com.nrlm.mclfmis.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nrlm.mclfmis.Response.ValidateResponse;
import com.nrlm.mclfmis.exception.ValidationHandledException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
	

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ValidateResponse> handleConstraintViolationException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		Map<String, String> messages = new HashMap<>(constraintViolations.size());
		messages.putAll(constraintViolations.stream()
				.collect(Collectors.toMap(
						constraintViolation -> String.format("%s", constraintViolation.getPropertyPath()),
						constraintViolation -> String.format("%s %s", constraintViolation.getPropertyPath(),
								constraintViolation.getMessage()))));
		ValidateResponse validationResponse = new ValidateResponse(HttpStatus.BAD_REQUEST.value(), messages,
				HttpStatus.NOT_ACCEPTABLE.name());
		return new ResponseEntity<ValidateResponse>(validationResponse, HttpStatus.BAD_REQUEST);

	}
//	@ExceptionHandler(UnexpectedTypeException.class)
//	protected ResponseEntity<ValidateResponse> handleUnexpectedTypeException(UnexpectedTypeException ex) {
//		//Set<ConstraintViolation<?>> constraintViolations = ex.;
//		Map<String, String> messages = new HashMap<>();
//		messages.putAll(constraintViolations.stream()
//				.collect(Collectors.toMap(
//						constraintViolation -> String.format("%s", constraintViolation.getPropertyPath()),
//						constraintViolation -> String.format("%s %s", constraintViolation.getPropertyPath(),
//								constraintViolation.getMessage()))));
//		ValidateResponse validationResponse = new ValidateResponse(HttpStatus.BAD_REQUEST.value(), messages,
//				HttpStatus.NOT_ACCEPTABLE.name());
//		return new ResponseEntity<ValidateResponse>(validationResponse, HttpStatus.BAD_REQUEST);
//
//	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", new Date());
//		body.put("status", status.value());
//
//		// Get all errors
//		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
//				.collect(Collectors.toList());
//
//		body.put("errors", errors);
//
//		return new ResponseEntity<>(body, headers, status);
////		Map<String, String> messages = new HashMap<>(ex.getBindingResult().getErrorCount());
////		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
////			messages.put(fieldError.getField(), fieldError.getDefaultMessage());
////		}
////		ValidateResponse validationResponse = new ValidateResponse(HttpStatus.BAD_REQUEST.value(), messages,
////				HttpStatus.NOT_ACCEPTABLE.name());
////		return new ResponseEntity<ValidateResponse>(validationResponse, HttpStatus.BAD_REQUEST);
//
//	}

	@ExceptionHandler(ValidationHandledException.class)
	public final ResponseEntity<ValidateResponse> handleHandledException(ValidationHandledException ex) throws Exception {
		List<FieldError> errs = ex.getErrors();
		Map<String, String> messages = new HashMap<>(errs.size());
		for (FieldError fieldError : errs) {
//			if (fieldError.getDefaultMessage() != null) {
				messages.put(fieldError.getField(), fieldError.getDefaultMessage());
//			}
		}
		ValidateResponse validationResponse = new ValidateResponse(HttpStatus.BAD_REQUEST.value(), messages,
				HttpStatus.NOT_ACCEPTABLE.name());
		return new ResponseEntity<ValidateResponse>(validationResponse, HttpStatus.BAD_REQUEST);
	}

}