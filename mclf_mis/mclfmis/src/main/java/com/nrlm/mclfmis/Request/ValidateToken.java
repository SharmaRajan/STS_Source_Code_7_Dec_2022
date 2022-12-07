package com.nrlm.mclfmis.Request;

import java.util.List;

public class ValidateToken {

    private String loginId;
    private List<String> authorities;
    private Integer responseCode;
    private String responseDesc;


    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public List<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
    public Integer getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
    public String getResponseDesc() {
        return responseDesc;
    }
    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    @Override
    public String toString() {
        return "ValidateToken{" +
                "loginId='" + loginId + '\'' +
                ", authorities=" + authorities +
                ", responseCode=" + responseCode +
                ", responseDesc='" + responseDesc + '\'' +
                '}';
    }

}
