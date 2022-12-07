package com.nrlm.mclfmis.Request;

import java.util.List;

public class CutOffDataRequest {

	private Long sectionId;
	private Long indctrId;
	private String indctrValue;
	private Integer optionId;
	private List<Integer> multiselectOptionValue;
	private Integer indctrType;

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getIndctrId() {
		return indctrId;
	}

	public void setIndctrId(Long indctrId) {
		this.indctrId = indctrId;
	}

	public String getIndctrValue() {
		return indctrValue;
	}

	public void setIndctrValue(String indctrValue) {
		this.indctrValue = indctrValue;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getIndctrType() {
		return indctrType;
	}

	public void setIndctrType(Integer indctrType) {
		this.indctrType = indctrType;
	}

	public List<Integer> getMultiselectOptionValue() {
		return multiselectOptionValue;
	}

	public void setMultiselectOptionValue(List<Integer> multiselectOptionValue) {
		this.multiselectOptionValue = multiselectOptionValue;
	}

	
}
