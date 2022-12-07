package com.nrlm.mclfmis.Response;

public class GpCutOffResponse {

	private String grampanchayatCode;
	private String grampanchayatName;
	private String reportMonth;
	private String status;
	private Integer cutOffMonth;
	private Integer cutOffYear;
	
	
	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}
	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
	}
	public String getGrampanchayatName() {
		return grampanchayatName;
	}
	public void setGrampanchayatName(String grampanchayatName) {
		this.grampanchayatName = grampanchayatName;
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
	
	public Integer getCutOffMonth() {
		return cutOffMonth;
	}
	public void setCutOffMonth(Integer cutOffMonth) {
		this.cutOffMonth = cutOffMonth;
	}
	public Integer getCutOffYear() {
		return cutOffYear;
	}
	public void setCutOffYear(Integer cutOffYear) {
		this.cutOffYear = cutOffYear;
	}
	
	
}
