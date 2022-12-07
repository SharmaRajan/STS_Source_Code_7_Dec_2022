package com.nrlm.mclfmis.customEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClfCustomCutOffEntity {

	@Id
	private Long id;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private String clfCode;
	private String clfName;
	private String reportMonth;
	private String overallStatus;
	private String clfStatus;
	private String registrationDate;
	private Integer cutOffYear;
	private Integer cutOffMonth;
	
	public ClfCustomCutOffEntity(Long cutOffId,String stateCode, String stateName, String districtCode, String districtName,
			String blockCode, String blockName, String clfCode, String clfName, String reportMonth,String overallStatus, String clfStatus,String registrationDate,
			Integer cutOffYear, Integer cutOffMonth) {
		super();
		this.id = cutOffId;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.reportMonth = reportMonth;
		this.overallStatus = overallStatus;
		this.clfStatus = clfStatus;
		this.registrationDate = registrationDate;
		this.cutOffYear = cutOffYear;
		this.cutOffMonth = cutOffMonth;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public String getReportMonth() {
		return reportMonth;
	}
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}
	

	public String getOverallStatus() {
		return overallStatus;
	}

	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

	public String getClfStatus() {
		return clfStatus;
	}

	public void setClfStatus(String clfStatus) {
		this.clfStatus = clfStatus;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setCutOffId(Long cutOffId) {
		this.id = cutOffId;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
}
