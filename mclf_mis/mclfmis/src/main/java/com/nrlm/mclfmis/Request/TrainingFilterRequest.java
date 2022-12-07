package com.nrlm.mclfmis.Request;

public class TrainingFilterRequest {

	private Long id;
	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String clfCode;
	private String clfName;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public TrainingFilterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TrainingFilterRequest(Long id, String stateCode, String districtCode, String blockCode, String clfCode,
			String clfName, Integer status) {
		super();
		this.id = id;
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.blockCode = blockCode;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "TrainingFilterRequest [id=" + id + ", stateCode=" + stateCode + ", districtCode=" + districtCode
				+ ", blockCode=" + blockCode + ", clfCode=" + clfCode + ", clfName=" + clfName + ", status=" + status
				+ "]";
	}
	
	
	
}
