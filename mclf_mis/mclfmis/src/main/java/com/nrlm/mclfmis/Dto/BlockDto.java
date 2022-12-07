package com.nrlm.mclfmis.Dto;

import java.sql.Timestamp;


public class BlockDto {

	private String blockCode;
	
	private String ruralUrbanArea;

	private String blockName;

	private Timestamp createdOn;

	private String createdBy;

	private Timestamp modifiedOn;

	private String modifiedBy;

	private String stateCode;

	private String districtCode;

	private String intensive;

	private String blockNameTwo;

	private String census2011;
	private String districtName;
	private String stateName;
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getRuralUrbanArea() {
		return ruralUrbanArea;
	}
	public void setRuralUrbanArea(String ruralUrbanArea) {
		this.ruralUrbanArea = ruralUrbanArea;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	public String getIntensive() {
		return intensive;
	}
	public void setIntensive(String intensive) {
		this.intensive = intensive;
	}
	public String getBlockNameTwo() {
		return blockNameTwo;
	}
	public void setBlockNameTwo(String blockNameTwo) {
		this.blockNameTwo = blockNameTwo;
	}
	public String getCensus2011() {
		return census2011;
	}
	public void setCensus2011(String census2011) {
		this.census2011 = census2011;
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
	public BlockDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlockDto(String blockCode, String ruralUrbanArea, String blockName, Timestamp createdOn, String createdBy,
			Timestamp modifiedOn, String modifiedBy, String stateCode, String districtCode, String intensive,
			String blockNameTwo, String census2011, String districtName, String stateName) {
		super();
		this.blockCode = blockCode;
		this.ruralUrbanArea = ruralUrbanArea;
		this.blockName = blockName;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.intensive = intensive;
		this.blockNameTwo = blockNameTwo;
		this.census2011 = census2011;
		this.districtName = districtName;
		this.stateName = stateName;
	}
	
	

}
