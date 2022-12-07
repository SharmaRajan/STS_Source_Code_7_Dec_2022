package com.nrlm.mclfmis.Request;

import java.util.List;

public class CutOffFormRequest {

	private Integer tabId;
	private String clfCode;
	private String voCode;
	private String grampanchayatCode;
	private Integer isDraft;
	private Integer reportYear;
	private Integer reportMonth;
	private List<CutOffDataRequest> cutOffData;
	private List<Long> skipList;
	private List<Long> filledSection;
	private String errorMsg;
	
	
	
	
	public CutOffFormRequest() {
		super();
	}
	public CutOffFormRequest(Integer tabId, String clfCode, String voCode, String grampanchayatCode, Integer isDraft,
			Integer reportYear, Integer reportMonth, List<CutOffDataRequest> cutOffData,List<Long> skiplist, String errorMsg) {
		super();
		this.tabId = tabId;
		this.clfCode = clfCode;
		this.voCode = voCode;
		this.grampanchayatCode = grampanchayatCode;
		this.isDraft = isDraft;
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.cutOffData = cutOffData;
		this.skipList = skiplist;
		this.errorMsg = errorMsg;
	}
	public Integer getTabId() {
		return tabId;
	}
	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public String getVoCode() {
		return voCode;
	}
	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}
	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}
	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
	}
	public List<CutOffDataRequest> getCutOffData() {
		return cutOffData;
	}
	public void setCutOffData(List<CutOffDataRequest> cutOffData) {
		this.cutOffData = cutOffData;
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
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
	
	@Override
	public String toString() {
		return "CutOffFormRequest [tabId=" + tabId + ", clfCode=" + clfCode + ", voCode=" + voCode
				+ ", grampanchayatCode=" + grampanchayatCode + ", isDraft=" + isDraft + ", cutOffData=" + cutOffData
				+ "]";
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<Long> getSkipList() {
		return skipList;
	}
	public void setSkipList(List<Long> skipList) {
		this.skipList = skipList;
	}
	public List<Long> getFilledSection() {
		return filledSection;
	}
	public void setFilledSection(List<Long> filledSection) {
		this.filledSection = filledSection;
	}
	
	
	
}
