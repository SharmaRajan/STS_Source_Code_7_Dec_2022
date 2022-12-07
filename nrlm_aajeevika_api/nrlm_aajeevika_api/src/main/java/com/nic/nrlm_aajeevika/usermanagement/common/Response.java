package com.nic.nrlm_aajeevika.usermanagement.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response<T> {
	private T wrappedObj ;
	private Map<String, String> errorsMap = new HashMap<String, String>();
	private Integer responseCode;
	private String responseDesc;

	public Response() {
	}

	public Response(T wrappedObj) {
		this.wrappedObj = wrappedObj;
	}

	public Response(T wrappedObj, Map<String, String> errorsMap) {
		this.wrappedObj = wrappedObj;
		this.errorsMap = errorsMap;
	}

	public Response(T wrappedObj, Map<String, String> errorsMap, Integer responseCode, String responseDesc) {
		this.wrappedObj = wrappedObj;
		this.errorsMap = errorsMap;
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;
	}

	public T getWrappedObj() {
		return this.wrappedObj;
	}

	public void setWrappedObj(T wrappedObj) {
		this.wrappedObj = wrappedObj;
	}

	public Map<String, String> getErrorsMap() {
		return this.errorsMap;
	}

	public void setErrorsMap(Map<String, String> errorsMap) {
		this.errorsMap = errorsMap;
	}

	public Integer getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDesc() {
		return this.responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}


}
