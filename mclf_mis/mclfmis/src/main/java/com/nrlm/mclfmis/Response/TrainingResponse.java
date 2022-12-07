package com.nrlm.mclfmis.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TrainingResponse {

	private Long id;	
	
	private String clfCode ;		
	private Integer trainingConduct;	
	private Integer[] clfStaffType;	
	//private Integer trainingModule;	
	//private Integer partIntrNo;
	private List<HashMap<String,Integer>> modulePart;
	private String status;
	private String stateCode;
	private String stateName;
	private String blockCode;
	private String blockName;
	private String districtCode;
	private String districtName;
	private String clfName;
	private String startDate;
	private String endDate;
	
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public Integer getTrainingConduct() {
		return trainingConduct;
	}
	public void setTrainingConduct(Integer trainingConduct) {
		this.trainingConduct = trainingConduct;
	}
	public Integer[] getClfStaffType() {
		return clfStaffType;
	}
	public void setClfStaffType(Integer[] clfStaffType) {
		this.clfStaffType = clfStaffType;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public TrainingResponse(Long id,String stateCode, String stateName,String districtCode, String districtName, String blockCode, String blockName, String clfCode, String clfName,Integer trainingConduct, Integer[] clfStaffType,
			List<HashMap<String, Integer>> modulePart, String status, String startDate, String endDate) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.trainingConduct = trainingConduct;
		this.clfStaffType = clfStaffType;
		this.modulePart = modulePart;
		this.status = status;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.clfName = clfName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public TrainingResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public List<HashMap<String,Integer>> getModulePart() {
		return modulePart;
	}
	public void setModulePart(List<HashMap<String,Integer>> modulePart) {
		this.modulePart = modulePart;
	}
		
	
}
