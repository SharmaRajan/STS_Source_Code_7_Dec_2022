package com.nrlm.mclfmis.Response;

public class SectionSequenceResponse {

	private Long sectionId;
	private String sectionName;
	private Integer sequence;
	
	
	
	public SectionSequenceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SectionSequenceResponse(Long sectionId, String sectionName, Integer sequence) {
		super();
		this.sectionId = sectionId;
		this.sectionName = sectionName;
		this.sequence = sequence;
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
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
}
