package com.nrlm.mclfmis.Request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SectionRequest {

	//@NotNull(message = "Tab Type is required.")
	//@Min(value=1, message="Invalid Tab")
	//@Max(value=3,message="Invalid Tab")
	private Integer tabId;
	
	private Long sectionId;
	
	//@NotNull(message = "Section Name is required.")
	//@NotEmpty(message = "Section Name cannot be Empty.")
	private String sectionName;
	
	private String sectionDesc;
	private Integer isSkip;
	private Integer status;
	private String sortCol;
	private String sortOrder;
	
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTabId() {
		return tabId;
	}
	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getIsSkip() {
		return isSkip;
	}
	public void setIsSkip(Integer isSkip) {
		this.isSkip = isSkip;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
}
