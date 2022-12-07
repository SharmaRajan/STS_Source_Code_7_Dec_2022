package com.nrlm.mclfmis.Response;

public class InternalControlResponse {
	
	private Long id;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private String clfCode;
	private String clfName;
	private Integer finYearId;
	private Integer qrtrId;
	private String finYear;
	private String qrtr;
	private Integer intAudit;
	private Integer grb;
	private Integer gbMeeting;
	private String status;
	
	
	public InternalControlResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InternalControlResponse(Long id, String stateCode, String stateName, String districtCode,
			String districtName, String blockCode, String blockName, String clfCode, String clfName, Integer finYearId,
			Integer qrtrId, Integer intAudit, Integer grb, Integer gbMeeting, String status,String finYear,String qrtr) {
		super();
		this.id = id;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.finYearId = finYearId;
		this.qrtrId = qrtrId;
		this.intAudit = intAudit;
		this.grb = grb;
		this.gbMeeting = gbMeeting;
		this.status = status;
		this.finYear = finYear;
		this.qrtr = qrtr;
	}
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public String getQrtr() {
		return qrtr;
	}
	public void setQrtr(String qrtr) {
		this.qrtr = qrtr;
	}
	@Override
	public String toString() {
		return "InternalControlResponse [id=" + id + ", stateCode=" + stateCode + ", stateName=" + stateName
				+ ", districtCode=" + districtCode + ", districtName=" + districtName + ", blockCode=" + blockCode
				+ ", blockName=" + blockName + ", clfCode=" + clfCode + ", clfName=" + clfName + ", finYearId="
				+ finYearId + ", qrtrId=" + qrtrId + ", intAudit=" + intAudit + ", grb=" + grb + ", gbMeeting="
				+ gbMeeting + ", status=" + status +  "]";
	}
	
	
}
