package com.nrlm.mclfmis.Response;

import java.util.Date;

public class StaffResponse {
	
	private Long id;
	private String clfCode;
	private String clfName;
	private String stateCode;
	private String stateName;
	private String blockCode;
	private String blockName;
	private String districtCode;
	private String districtName;
	private Integer staffType;
	private String name;
	private String phone;
	private Integer gender;
	private Integer socialCategory;
	private Integer minority;
	private Integer diffAble;
	private Integer monthRemu;
	private String address;
	private Integer eduQual;
	private String eduArea;
	private String addInfo;
	private Integer expYrs;
	private String expDesc;
	private Date joinDate;
	private Integer actStatus;
	private Date leaveDate;
	private Integer leaveReason;
	private String otherRes;
	private String status;
	
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public Integer getStaffType() {
		return staffType;
	}
	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(Integer socialCategory) {
		this.socialCategory = socialCategory;
	}
	public Integer getMinority() {
		return minority;
	}
	public void setMinority(Integer minority) {
		this.minority = minority;
	}
	public Integer getDiffAble() {
		return diffAble;
	}
	public void setDiffAble(Integer diffAble) {
		this.diffAble = diffAble;
	}
	public Integer getMonthRemu() {
		return monthRemu;
	}
	public void setMonthRemu(Integer monthRemu) {
		this.monthRemu = monthRemu;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getEduQual() {
		return eduQual;
	}
	public void setEduQual(Integer eduQual) {
		this.eduQual = eduQual;
	}
	public String getEduArea() {
		return eduArea;
	}
	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public Integer getExpYrs() {
		return expYrs;
	}
	public void setExpYrs(Integer expYrs) {
		this.expYrs = expYrs;
	}
	public String getExpDesc() {
		return expDesc;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public Integer getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(Integer leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getOtherRes() {
		return otherRes;
	}
	public void setOtherRes(String otherRes) {
		this.otherRes = otherRes;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
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
	public StaffResponse(Long id,String clfCode, String clfName, String stateCode, String stateName, String blockCode,
			String blockName, String districtCode, String districtName, Integer staffType, String name, String phone,
			Integer gender, Integer socialCategory, Integer minority, Integer diffAble, Integer monthRemu,
			String address, Integer eduQual, String eduArea, String addInfo, Integer expYrs, String expDesc,
			Date joinDate, Integer actStatus, Date leaveDate, Integer leaveReason, String otherRes,String status) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.staffType = staffType;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.socialCategory = socialCategory;
		this.minority = minority;
		this.diffAble = diffAble;
		this.monthRemu = monthRemu;
		this.address = address;
		this.eduQual = eduQual;
		this.eduArea = eduArea;
		this.addInfo = addInfo;
		this.expYrs = expYrs;
		this.expDesc = expDesc;
		this.joinDate = joinDate;
		this.actStatus = actStatus;
		this.leaveDate = leaveDate;
		this.leaveReason = leaveReason;
		this.otherRes = otherRes;
		this.status = status;
	}
	
	
	public StaffResponse() {}
	
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
