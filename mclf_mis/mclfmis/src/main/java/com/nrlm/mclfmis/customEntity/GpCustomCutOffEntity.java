package com.nrlm.mclfmis.customEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GpCustomCutOffEntity {

	@Id
	private Long gpCutOffId;
	
	private String grampanchayatCode;
	private String grampanchayatName;
	private String reportMonth;
	private String status;
	private Integer cutOffYear;
	private Integer cutOffMonth;
	
	
	public GpCustomCutOffEntity(Long gpCutOffId, String grampanchayatCode, String grampanchayatName, String reportMonth,
			String status,Integer cutOffYear, Integer cutOffMonth) {
		super();
		this.gpCutOffId = gpCutOffId;
		this.grampanchayatCode = grampanchayatCode;
		this.grampanchayatName = grampanchayatName;
		this.reportMonth = reportMonth;
		this.setCutOffYear(cutOffYear);
		this.setCutOffMonth(cutOffMonth);
		this.status = status;
	}
	public Long getGpCutOffId() {
		return gpCutOffId;
	}
	public void setGpCutOffId(Long gpCutOffId) {
		this.gpCutOffId = gpCutOffId;
	}
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
