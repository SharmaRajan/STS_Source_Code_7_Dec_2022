package com.nrlm.mclfmis.Response;

import java.util.List;

import com.nrlm.mclfmis.Entity.Indicator;

public class FormResponse {

	private Long sectionId;
	private String sectionName;
	private Integer isSkip;
	private Integer skipValue;
	private String sectionStatus;
	private List<IndicatorResponse> indctrList;
	
	
	public FormResponse(Long sectionId, String sectionName,Integer isSkip,Integer skipValue,String sectionStatus, List<IndicatorResponse> indicatorList) {
		super();
		this.sectionId = sectionId;
		this.sectionName = sectionName;
		this.isSkip = isSkip;
		this.skipValue = skipValue;
		this.sectionStatus = sectionStatus;
		this.indctrList = indicatorList;
	}
	
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public List<IndicatorResponse> getIndctrList() {
		return indctrList;
	}
	public void setIndctrList(List<IndicatorResponse> indicatorList) {
		this.indctrList = indicatorList;
	}

	public Integer getIsSkip() {
		return isSkip;
	}

	public void setIsSkip(Integer isSkip) {
		this.isSkip = isSkip;
	}

	public Integer getSkipValue() {
		return skipValue;
	}

	public void setSkipValue(Integer skipValue) {
		this.skipValue = skipValue;
	}

	public String getSectionStatus() {
		return sectionStatus;
	}

	public void setSectionStatus(String sectionStatus) {
		this.sectionStatus = sectionStatus;
	}
	
	
}
