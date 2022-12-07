package com.nrlm.mclfmis.Response;

public class IndicatorSequenceResponse {

	private Long indctrId;
	private String indctrName;
	private Integer sequence;
	
	
	public String getIndctrName() {
		return indctrName;
	}
	public void setIndctrName(String indctrName) {
		this.indctrName = indctrName;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Long getIndctrId() {
		return indctrId;
	}
	public void setIndctrId(Long indctrId) {
		this.indctrId = indctrId;
	}
	
	
	public IndicatorSequenceResponse(Long indctrId, String indctrName, Integer sequence) {
		super();
		this.indctrId = indctrId;
		this.indctrName = indctrName;
		this.sequence = sequence;
	}
	public IndicatorSequenceResponse() {
		super();
	}
	
	
	
}
