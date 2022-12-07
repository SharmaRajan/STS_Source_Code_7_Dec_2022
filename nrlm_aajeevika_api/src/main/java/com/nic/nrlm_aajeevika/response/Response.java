package com.nic.nrlm_aajeevika.response;


import java.util.HashMap;
import java.util.List;

public class Response<T> {

    private List<T> wrapperList;
    private HashMap<String,String> errorMap;
    private String errorMsg;
    private String responseCode;
    private String responseDesc;
    private Long totalcount;

    public Response() {
    }

//    public Response(List<T> wrapperList, HashMap<String, String> errorMap, String responseCode, String responseDesc) {
//        this.wrapperList = wrapperList;
//       this.errorMap = errorMap;
//        this.responseCode = responseCode;
//        this.responseDesc = responseDesc;
//    }

    public Response(List<T> wrapperList, HashMap<String, String> errorMap, String responseCode,String errorMsg, String responseDesc,Long totalcount) {
        this.wrapperList = wrapperList;
        this.errorMap = errorMap;
        this.errorMsg = errorMsg;
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.totalcount = totalcount;
    }

    public List<T> getWrapperList() {
        return wrapperList;
    }

    public void setWrapperList(List<T> wrapperList) {
        this.wrapperList = wrapperList;
    }

    public HashMap<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(HashMap<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Long getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(Long totalcount) {
        this.totalcount = totalcount;
    }

    @Override
    public String toString() {
        return "Response{" +
                "wrapperList=" + wrapperList +
                ", errorMap=" + errorMap +
                ", responseCode='" + responseCode + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                '}';
    }
}
