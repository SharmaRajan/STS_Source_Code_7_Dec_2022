package com.nrlm.mclfmis.usermanagement.request;

public class ValidateTokenRequest {
    private String endPoint;
    private String isAuthorize;
    
    

    public ValidateTokenRequest() {
		super();
	}
	public ValidateTokenRequest(String endPoint, String isAuthorize) {
		super();
		this.endPoint = endPoint;
		this.isAuthorize = isAuthorize;
	}
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
