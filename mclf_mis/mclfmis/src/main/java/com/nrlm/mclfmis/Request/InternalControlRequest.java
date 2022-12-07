package com.nrlm.mclfmis.Request;



public class InternalControlRequest {
	
	private Long id;
	private String clfCode;
	private Integer finYearId;
	private Integer qrtrId;
	private Integer intAudit;
	private Integer grb;
	private Integer gbMeeting;
	
	private Integer isDraft;
	
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
	public Integer getFinYearId() {
		return finYearId;
	}
	public void setFinYearId(Integer finYearId) {
		this.finYearId = finYearId;
	}
	public Integer getQrtrId() {
		return qrtrId;
	}
	public void setQrtrId(Integer qrtrId) {
		this.qrtrId = qrtrId;
	}
	public Integer getIntAudit() {
		return intAudit;
	}
	public void setIntAudit(Integer intAudit) {
		this.intAudit = intAudit;
	}
	public Integer getGrb() {
		return grb;
	}
	public void setGrb(Integer grb) {
		this.grb = grb;
	}
	public Integer getGbMeeting() {
		return gbMeeting;
	}
	public void setGbMeeting(Integer gbMeeting) {
		this.gbMeeting = gbMeeting;
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	
	
	
	

}
