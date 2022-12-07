package com.nrlm.mclfmis.Request;

public class SubCommCSTRequest {

	
	private Long id;
	private String clfCode;
	private Integer cstFormed;
	private Integer totCstMemb;
	private Integer isDraft;
	//private Integer status;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public Integer getCstFormed() {
		return cstFormed;
	}

	public void setCstFormed(Integer cstFormed) {
		this.cstFormed = cstFormed;
	}

	public Integer getTotCstMemb() {
		return totCstMemb;
	}

	public void setTotCstMemb(Integer totCstMemb) {
		this.totCstMemb = totCstMemb;
	}

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
	
	
	
	
}
