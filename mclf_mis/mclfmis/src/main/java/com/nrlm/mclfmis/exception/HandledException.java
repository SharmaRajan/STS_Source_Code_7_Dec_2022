package com.nrlm.mclfmis.exception;

import org.springframework.http.HttpStatus;

public class HandledException extends RuntimeException {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus code;

	 public HandledException(HttpStatus code, String message) {
	       super(message);
	       this.setCode(code);
	 }

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}
}
