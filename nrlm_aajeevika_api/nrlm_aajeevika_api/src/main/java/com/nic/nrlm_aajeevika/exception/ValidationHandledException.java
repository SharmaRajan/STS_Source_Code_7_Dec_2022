package com.nic.nrlm_aajeevika.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationHandledException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
	List<FieldError> errors;

	public ValidationHandledException(HttpStatus code, List<FieldError> errors) {
		super();
		this.code = code;
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}
}
