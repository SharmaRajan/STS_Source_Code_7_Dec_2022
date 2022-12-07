package com.nrlm.mclfmis.Request;

public class ClfPoliciesRequest {

	private Long id;
	private String clfCode;
	private Integer cboHrPolicies;
	private Integer govPolicies;
	private Integer finPolicies;
	
	private Integer isDraft;
	
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public Integer getCboHrPolicies() {
		return cboHrPolicies;
	}
	public void setCboHrPolicies(Integer cboHrPolicies) {
		this.cboHrPolicies = cboHrPolicies;
	}
	public Integer getGovPolicies() {
		return govPolicies;
	}
	public void setGovPolicies(Integer govPolicies) {
		this.govPolicies = govPolicies;
	}
	public Integer getFinPolicies() {
		return finPolicies;
	}
	public void setFinPolicies(Integer finPolicies) {
		this.finPolicies = finPolicies;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	
	
	
	
	
	
}
