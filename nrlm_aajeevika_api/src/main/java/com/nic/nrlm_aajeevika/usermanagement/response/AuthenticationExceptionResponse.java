package com.nic.nrlm_aajeevika.usermanagement.response;

import java.util.Date;

public class AuthenticationExceptionResponse {
	
	private Integer responseCode;
	private Date requestDate;
	private String url;
	private String errorMsg;
	private String responseDesc;
	
	public AuthenticationExceptionResponse(Integer responseCode,Date requestDate,String url,String errorMsg,String responseDesc){
		super();
		this.responseCode = responseCode;
		this.requestDate = requestDate;
		this.url = url;
		this.errorMsg = errorMsg;
		this.responseDesc = responseDesc;
	}
	
	public AuthenticationExceptionResponse() {
		super();
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	

}
