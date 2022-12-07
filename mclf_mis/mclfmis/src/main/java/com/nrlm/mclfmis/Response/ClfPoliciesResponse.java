package com.nrlm.mclfmis.Response;

public class ClfPoliciesResponse {
	
	private Long id;
	private String clfCode;
	private String clfName;
	private String stateCode;
	private String stateName;
	private String blockCode;
	private String blockName;
	private String districtCode;
	private String districtName;
	private Integer cboHrPolicies;
	private Integer govPolicies;
	private Integer finPolicies;
	private String status;
	
	
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	
	
	
	public ClfPoliciesResponse(Long id,String stateCode, String stateName,String districtCode, String districtName,
			String blockCode, String blockName, String clfCode, String clfName, 
			Integer cboHrPolicies,Integer govPolicies, Integer finPolicies, String status) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.cboHrPolicies = cboHrPolicies;
		this.govPolicies = govPolicies;
		this.finPolicies = finPolicies;
		this.status = status;
	}
	public ClfPoliciesResponse() 
	{
		super();
	}
	
	
	
	
	

}
