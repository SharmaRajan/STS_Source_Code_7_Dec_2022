package com.nrlm.mclfmis.Request;

public class CutOffFilterRequest {

	private String stateCode;
	private String districtCode;
	private String blockCode;
	private String clfCode;
	private String clfName;
	private String voCode;
	private String voName;
	private String grampanchayatCode;
	private String grampanchayatName;
	private Integer reportYear;
	private Integer reportMonth;
	private Integer status;
	private String sortCol;
	private String sortOrder;
	
	
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
	public Integer getReportYear() {
		return reportYear;
	}
	public void setReportYear(Integer reportYear) {
		this.reportYear = reportYear;
	}
	public Integer getReportMonth() {
		return reportMonth;
	}
	public void setReportMonth(Integer reportMonth) {
		this.reportMonth = reportMonth;
	}
	
	public CutOffFilterRequest(String stateCode, String districtCode, String blockCode, String clfCode,
			Integer reportYear, Integer reportMonth,Integer status,String sortCol, String sortOrder) {
		super();
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.blockCode = blockCode;
		this.clfCode = clfCode;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.status = status;
		this.sortCol = sortCol;
		this.sortOrder = sortOrder;
	}
	
	
	public CutOffFilterRequest(String stateCode, String districtCode, String blockCode, String clfCode, String clfName,
			String voCode, String voName, String grampanchayatCode, String grampanchayatName, Integer reportYear,
			Integer reportMonth, Integer status,String sortCol, String sortOrder) {
		super();
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.blockCode = blockCode;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.voCode = voCode;
		this.voName = voName;
		this.grampanchayatCode = grampanchayatCode;
		this.grampanchayatName = grampanchayatName;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.status = status;
		this.sortCol = sortCol;
		this.sortOrder = sortOrder;
	}
	
	@Override
	public String toString() {
		return "CutOffFilterRequest [stateCode=" + stateCode + ", districtCode=" + districtCode + ", blockCode="
				+ blockCode + ", clfCode=" + clfCode + ", reportYear=" + reportYear + ", reportMonth=" + reportMonth
				+ "]";
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public String getVoCode() {
		return voCode;
	}
	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}
	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}
	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
	}
	public String getGrampanchayatName() {
		return grampanchayatName;
	}
	public void setGrampanchayatName(String grampanchayatName) {
		this.grampanchayatName = grampanchayatName;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
	
}
