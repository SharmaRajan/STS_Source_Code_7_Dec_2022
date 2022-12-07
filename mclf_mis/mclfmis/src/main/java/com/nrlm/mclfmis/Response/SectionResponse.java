package com.nrlm.mclfmis.Response;

public class SectionResponse {

	private Long sectionId;
	private String sectionName;
	private String sectionDesc;
	private Integer isSkip;
	private Integer status;
	
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
	public SectionResponse(Long sectionId, String sectionName, String sectionDesc, Integer status,Integer isSkip) {
		super();
		this.sectionId = sectionId;
		this.sectionName = sectionName;
		this.sectionDesc = sectionDesc;
		this.status = status;
		this.isSkip = isSkip;
	}
	public Integer getIsSkip() {
		return isSkip;
	}
	public void setIsSkip(Integer isSkip) {
		this.isSkip = isSkip;
	}
	
	
}
