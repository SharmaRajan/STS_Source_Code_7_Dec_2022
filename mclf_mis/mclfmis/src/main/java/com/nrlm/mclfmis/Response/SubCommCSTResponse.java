package com.nrlm.mclfmis.Response;

public class SubCommCSTResponse {

	private Long id;
	private String clfCode;
	private String clfName;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private Integer cstFormed;
	private Integer totCstMemb;
	private String status;
	
	public SubCommCSTResponse(Long id, String clfCode, String clfName, String stateCode, String stateName,
			String districtCode, String districtName, String blockCode, String blockName, Integer cstFormed,
			Integer totCstMemb, String status) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.cstFormed = cstFormed;
		this.totCstMemb = totCstMemb;
		this.status = status;
	}
	
	public SubCommCSTResponse() {}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
