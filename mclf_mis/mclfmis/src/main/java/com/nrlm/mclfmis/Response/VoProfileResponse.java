package com.nrlm.mclfmis.Response;

public class VoProfileResponse {

	private String voCode;
	private String stateName;
	private String districtName;
	private String blockName;
	private String grampanchayatName;
	private String clfName;
	private String voName;
	
	public VoProfileResponse() {
		
	}
	
	public VoProfileResponse(String stateName,String districtName,String blockName,String grampanchayatName,String clfName,String voName,String voCode) {
		this.stateName = stateName;
		this.districtName = districtName;
		this.blockName = blockName;
		this.grampanchayatName = grampanchayatName;
		this.clfName = clfName;
		this.voName = voName;
		this.voCode = voCode;
	}
	
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}
	public String getVoCode() {
		return voCode;
	}
	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}

	public String getGrampanchayatName() {
		return grampanchayatName;
	}

	public void setGrampanchayatName(String grampanchayatName) {
		this.grampanchayatName = grampanchayatName;
	}
	
	
}
