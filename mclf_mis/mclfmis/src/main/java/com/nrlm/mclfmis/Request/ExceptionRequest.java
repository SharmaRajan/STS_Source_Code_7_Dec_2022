package com.nrlm.mclfmis.Request;

import java.util.Date;

public class ExceptionRequest {
    private Integer responseCode;
    private Date requestDate;
    private String url;
    private String errorMsg;
    private String responseDesc;

    public ExceptionRequest(Integer responseCode,Date requestDate,String url,String errorMsg,String responseDesc){
        this.responseCode = responseCode;
        this.requestDate = requestDate;
        this.url = url;
        this.errorMsg = errorMsg;
        this.responseDesc = responseDesc;
    }

    public ExceptionRequest(){}

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

    @Override
    public String toString() {
        return "ExceptionRequest{" +
                "responseCode=" + responseCode +
                ", requestDate=" + requestDate +
                ", url='" + url + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                '}';
    }
}
