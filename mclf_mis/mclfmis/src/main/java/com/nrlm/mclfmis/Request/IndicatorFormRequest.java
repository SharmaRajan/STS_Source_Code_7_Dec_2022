package com.nrlm.mclfmis.Request;

import java.util.List;


public class IndicatorFormRequest {
	private Long sectionId;
	private String indctrName;
	private String description;
	private Integer sequence;
	private Integer mandatory;
	private Integer indctrType;
	private String minValue;
	private String maxValue;
	private Integer startMonth;
	private Integer endMonth;
	private Integer startFnclYear;
	private Integer endFnclYear;
	private Integer freezeFlag;
	private Integer status;

	private List<AnswerOptionDataRequest> answeroptionData;

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public List<AnswerOptionDataRequest> getAnsweroptionData() {
		return answeroptionData;
	}

	public void setAnsweroptionData(List<AnswerOptionDataRequest> answeroptionData) {
		this.answeroptionData = answeroptionData;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "IndicatorFormRequest [sectionId=" + sectionId + ", indctrName=" + indctrName + ", description="
				+ description + ", sequence=" + sequence + ", mandatory=" + mandatory + ", indctrType=" + indctrType
				+ ", minValue=" + minValue + ", maxValue=" + maxValue + ", startMonth=" + startMonth + ", endMonth="
				+ endMonth + ", freezeFlag=" + freezeFlag + ", answeroptionData=" + answeroptionData + "]";
	}
	
}
