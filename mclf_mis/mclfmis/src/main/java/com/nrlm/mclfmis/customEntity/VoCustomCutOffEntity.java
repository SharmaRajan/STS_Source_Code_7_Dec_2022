package com.nrlm.mclfmis.customEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VoCustomCutOffEntity {
	
	@Id
	private Long id;
	private String grampanchayatCode;
	private String grampanchayatName;
	private String voCode;
	private String voName;
	private String reportMonth;
	private String status;
	private Integer cutOffYear;
	private Integer cutOffMonth;
	

	public VoCustomCutOffEntity(Long id,String grampanchayatCode,String grampanchayatName, String voCode, String voName, String reportMonth, String status,
			Integer cutOffYear, Integer cutOffMonth) {
		super();
		this.id = id;
		this.grampanchayatCode = grampanchayatCode;
		this.grampanchayatName = grampanchayatName;
		this.voCode = voCode;
		this.voName = voName;
		this.reportMonth = reportMonth;
		this.status = status;
		this.cutOffYear = cutOffYear;
		this.cutOffMonth = cutOffMonth;
	}
	
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

	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}

	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
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
