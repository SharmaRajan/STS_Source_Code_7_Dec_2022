package com.nic.nrlm_aajeevika.usermanagement.handler;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomExceptionHandler extends UsernameNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomExceptionHandler(String msg){
		super(msg);
	}
}
