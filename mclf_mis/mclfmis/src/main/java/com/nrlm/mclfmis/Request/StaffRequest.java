package com.nrlm.mclfmis.Request;

import java.util.Date;



public class StaffRequest {
	
	private Long id;
	private String clfCode;
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
	private Integer actStatus; // active/inactive
	private Date leaveDate;
	private Integer leaveReason;
	private String otherRes;
	private Integer isDraft;
	
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
	@Override
	public String toString() {
		return "StaffRequest [clfCode=" + clfCode + ", staffType=" + staffType + ", name=" + name + ", phone=" + phone
				+ ", gender=" + gender + ", socialCategory=" + socialCategory + ", minority=" + minority + ", diffAble="
				+ diffAble + ", monthRemu=" + monthRemu + ", address=" + address + ", eduQual=" + eduQual + ", eduArea="
				+ eduArea + ", addInfo=" + addInfo + ", expYrs=" + expYrs + ", expDesc=" + expDesc + ", joinDate="
				+ joinDate + ", actStatus=" + actStatus + ", leaveDate=" + leaveDate + ", leaveReason=" + leaveReason
				+ ", otherRes=" + otherRes + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	
	

}
