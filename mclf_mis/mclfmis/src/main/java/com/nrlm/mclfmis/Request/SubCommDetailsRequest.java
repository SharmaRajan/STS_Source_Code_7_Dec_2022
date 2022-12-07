package com.nrlm.mclfmis.Request;



public class SubCommDetailsRequest {

	private Long id;
	private String clfCode;
	private Integer moniMemb;
	private Integer clfAstMemb;
	private Integer livHoodMemb;
	private Integer	bnkLinkMemb;
	private Integer	socActMemb;
	private Integer	anyOther;
	private String anyOtherName;
	private Integer anyOtherMemb;
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
	public Integer getMoniMemb() {
		return moniMemb;
	}
	public void setMoniMemb(Integer moniMemb) {
		this.moniMemb = moniMemb;
	}
	public Integer getClfAstMemb() {
		return clfAstMemb;
	}
	public void setClfAstMemb(Integer clfAstMemb) {
		this.clfAstMemb = clfAstMemb;
	}
	public Integer getLivHoodMemb() {
		return livHoodMemb;
	}
	public void setLivHoodMemb(Integer livHoodMemb) {
		this.livHoodMemb = livHoodMemb;
	}
	public Integer getBnkLinkMemb() {
		return bnkLinkMemb;
	}
	public void setBnkLinkMemb(Integer bnkLinkMemb) {
		this.bnkLinkMemb = bnkLinkMemb;
	}
	public Integer getSocActMemb() {
		return socActMemb;
	}
	public void setSocActMemb(Integer socActMemb) {
		this.socActMemb = socActMemb;
	}
	public Integer getAnyOther() {
		return anyOther;
	}
	public void setAnyOther(Integer anyOther) {
		this.anyOther = anyOther;
	}
	public String getAnyOtherName() {
		return anyOtherName;
	}
	public void setAnyOtherName(String anyOtherName) {
		this.anyOtherName = anyOtherName;
	}
	public Integer getAnyOtherMemb() {
		return anyOtherMemb;
	}
	public void setAnyOtherMemb(Integer anyOtherMemb) {
		this.anyOtherMemb = anyOtherMemb;
	}
//	public Integer getStatus() {
//		return status;
//	}
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	
	
}
