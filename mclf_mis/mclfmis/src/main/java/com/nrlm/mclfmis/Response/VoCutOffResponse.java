package com.nrlm.mclfmis.Response;

public class VoCutOffResponse {

	private String grampanchayatName;
	private String voCode;
	private String voName;
	private String reportMonth;
	private Integer cutOffYear;
	private Integer cutOffMonth;
	private String status;
	
	public String getGrampanchayatName() {
		return grampanchayatName;
	}
	public void setGrampanchayatName(String grampanchayatName) {
		this.grampanchayatName = grampanchayatName;
	}
	public String getVoCode() {
		return voCode;
	}
	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}
	public String getReportMonth() {
		return reportMonth;
	}
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getCutOffYear() {
		return cutOffYear;
	}
	public void setCutOffYear(Integer cutOffYear) {
		this.cutOffYear = cutOffYear;
	}
	public Integer getCutOffMonth() {
		return cutOffMonth;
	}
	public void setCutOffMonth(Integer cutOffMonth) {
		this.cutOffMonth = cutOffMonth;
	}
	
	
	
}
