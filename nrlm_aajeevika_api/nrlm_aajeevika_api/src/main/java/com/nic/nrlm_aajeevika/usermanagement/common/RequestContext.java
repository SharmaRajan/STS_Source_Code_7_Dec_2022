package com.nic.nrlm_aajeevika.usermanagement.common;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;


@ManagedBean
@RequestScope
public class RequestContext<T> {

	private T requestBody;

	
	public void setRequestBody(T req){
		this.requestBody = req;
	}

	public T getRequestBody() {
		return this.requestBody;
	}
	
}
