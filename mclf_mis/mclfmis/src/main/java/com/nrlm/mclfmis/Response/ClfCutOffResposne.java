package com.nrlm.mclfmis.Response;

public class ClfCutOffResposne {

	private Long cutOffId;
	private String clfCode;
	private String stateName;
	private String districtName;
	private String blockName;
	private String clfName;
	private String reportMonth;
	private String status;
	private String clfStatus;
	private String registrationDate;
	private Integer cutOffMonth;
	private Integer cutOffYear;
	private String blockCode;
	

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Long getCutOffId() {
		return cutOffId;
	}

	public void setCutOffId(Long cutOffId) {
		this.cutOffId = cutOffId;
	}

	public String getClfStatus() {
		return clfStatus;
	}

	public void setClfStatus(String clfStatus) {
		this.clfStatus = clfStatus;
	}

	public ClfCutOffResposne(Long cutOffId, String clfCode, String stateName, String districtName, String blockName,
			String clfName, String reportMonth, String status, String clfStatus, String registrationDate,String blockCode,Integer cutOffYear,Integer cutOffMonth) {
		super();
		this.cutOffId = cutOffId;
		this.clfCode = clfCode;
		this.stateName = stateName;
		this.districtName = districtName;
		this.blockName = blockName;
		this.clfName = clfName;
		this.reportMonth = reportMonth;
		this.status = status;
		this.clfStatus = clfStatus;
		this.registrationDate = registrationDate;
		this.cutOffMonth = cutOffMonth;
		this.cutOffYear = cutOffYear;
		this.blockCode = blockCode;
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

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

}
