package com.nrlm.mclfmis.Dto;

import java.sql.Timestamp;

public class GpDto {
	private String grampanchayatCode;
	private String ruralUrbanArea;
	private String grampanchayatName;
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private Timestamp createdOn;
	private String createdBy;
	private Timestamp modifiedOn;
	private String modifedBy;
	private String grampanchayatNameTwo;
	private String districtName;
	private String stateName;
	private String blockName;

	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}

	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
	}

	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}

	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}

	public String getGrampanchayatName() {
		return grampanchayatName;
	}

	public void setGrampanchayatName(String grampanchayatName) {
		this.grampanchayatName = grampanchayatName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifedBy() {
		return modifedBy;
	}

	public void setModifedBy(String modifedBy) {
		this.modifedBy = modifedBy;
	}

	public String getGrampanchayatNameTwo() {
		return grampanchayatNameTwo;
	}

	public void setGrampanchayatNameTwo(String grampanchayatNameTwo) {
		this.grampanchayatNameTwo = grampanchayatNameTwo;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public GpDto(String grampanchayatCode, String ruralUrbanArea, String grampanchayatName, String stateCode,
			String districtCode, String blockCode, Timestamp createdOn, String createdBy, Timestamp modifiedOn,
			String modifedBy, String grampanchayatNameTwo, String districtName, String stateName, String blockName) {
		super();
		this.grampanchayatCode = grampanchayatCode;
		this.ruralUrbanArea = ruralUrbanArea;
		this.grampanchayatName = grampanchayatName;
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.blockCode = blockCode;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifedBy = modifedBy;
		this.grampanchayatNameTwo = grampanchayatNameTwo;
		this.districtName = districtName;
		this.stateName = stateName;
		this.blockName = blockName;
	}

	public GpDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
