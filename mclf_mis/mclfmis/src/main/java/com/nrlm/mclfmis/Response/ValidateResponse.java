package com.nrlm.mclfmis.Response;

import java.util.HashMap;
import java.util.Map;

public class ValidateResponse {
	private Integer responseCode;
	private Map<String, String> errorMap;
	private String responseDesc;

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public void setErrorMap(HashMap<String, String> errorMap) {
		this.errorMap = errorMap;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public ValidateResponse(Integer responseCode, Map<String, String> errorMap, String responseDesc) {
		super();
		this.responseCode = responseCode;
		this.errorMap = errorMap;
		this.responseDesc = responseDesc;
	}

	public ValidateResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
