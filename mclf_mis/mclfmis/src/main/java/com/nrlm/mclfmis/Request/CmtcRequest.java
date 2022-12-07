package com.nrlm.mclfmis.Request;

import java.util.Date;

import javax.persistence.Column;

public class CmtcRequest {
	
    private Long id;
	private String clfCode;
	private Integer cmtcEst;
	private Date cmtcDate;
	private Integer cmtcFunc;
	
	private Integer isDraft;
	
	public CmtcRequest(Long id, String clfCode, Integer cmtcEst, Date cmtcDate, Integer cmtcFunc) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.cmtcEst = cmtcEst;
		this.cmtcDate = cmtcDate;
		this.cmtcFunc = cmtcFunc;
	}
	public CmtcRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public Integer getCmtcEst() {
		return cmtcEst;
	}
	public void setCmtcEst(Integer cmtcEst) {
		this.cmtcEst = cmtcEst;
	}
	public Date getCmtcDate() {
		return cmtcDate;
	}
	public void setCmtcDate(Date cmtcDate) {
		this.cmtcDate = cmtcDate;
	}
	public Integer getCmtcFunc() {
		return cmtcFunc;
	}
	public void setCmtcFunc(Integer cmtcFunc) {
		this.cmtcFunc = cmtcFunc;
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}


}
