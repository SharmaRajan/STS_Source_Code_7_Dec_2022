package com.nic.nrlm_aajeevika.usermanagement.request;

public class ValidateTokenRequest {

	private String endPoint;
	private String isAuthorize;
	
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getIsAuthorize() {
		return isAuthorize;
	}
	public void setIsAuthorize(String isAuthorize) {
		this.isAuthorize = isAuthorize;
	}
}
