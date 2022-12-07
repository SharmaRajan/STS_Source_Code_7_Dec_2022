package com.nrlm.mclfmis.Response;

import java.util.List;

public class IndicatorListResponse {

	private Integer Id;
	private String indctrName;
	private String description;
	private Integer mandatory;
	private Integer indctrType;
	private String minValue;
	private String maxValue;
	private Long sectionId;
	private Integer sequence;
	private Integer startMonth;
	private Integer endMonth;
	private Integer startFnclYear;
	private Integer endFnclYear;
	private Integer freezeFlag;
	private Integer status;
	private Long createdBy;
	private Long updatedBy;
	private String sectionName;
	private List<AnswerOptionResponse> optionList;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getIndctrName() {
		return indctrName;
	}
	public void setIndctrName(String indctrName) {
		this.indctrName = indctrName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMandatory() {
		return mandatory;
	}
	public void setMandatory(Integer mandatory) {
		this.mandatory = mandatory;
	}
	public Integer getIndctrType() {
		return indctrType;
	}
	public void setIndctrType(Integer indctrType) {
		this.indctrType = indctrType;
	}
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}
	public Integer getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}
	public Integer getFreezeFlag() {
		return freezeFlag;
	}
	public void setFreezeFlag(Integer freezeFlag) {
		this.freezeFlag = freezeFlag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public Integer getStartFnclYear() {
		return startFnclYear;
	}
	public void setStartFnclYear(Integer startFnclYear) {
		this.startFnclYear = startFnclYear;
	}
	public Integer getEndFnclYear() {
		return endFnclYear;
	}
	public void setEndFnclYear(Integer endFnclYear) {
		this.endFnclYear = endFnclYear;
	}
	public List<AnswerOptionResponse> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<AnswerOptionResponse> optionList) {
		this.optionList = optionList;
	}
	public IndicatorListResponse(Integer id, String indctrName, String description, Integer mandatory,
			Integer indctrType, String minValue, String maxValue, Long sectionId, Integer sequence,
			Integer startMonth, Integer endMonth, Integer startFnclYear, Integer endFnclYear, Integer freezeFlag,
			Integer status, Long createdBy, Long updatedBy, String sectionName,
			List<AnswerOptionResponse> optionList) {
		super();
		this.Id = id;
		this.indctrName = indctrName;
		this.description = description;
		this.mandatory = mandatory;
		this.indctrType = indctrType;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.sectionId = sectionId;
		this.sequence = sequence;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.startFnclYear = startFnclYear;
		this.endFnclYear = endFnclYear;
		this.freezeFlag = freezeFlag;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.sectionName = sectionName;
		this.optionList = optionList;
	}
	
}
