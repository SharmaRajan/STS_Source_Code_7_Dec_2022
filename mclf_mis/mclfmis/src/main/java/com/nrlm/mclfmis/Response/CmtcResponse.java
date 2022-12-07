package com.nrlm.mclfmis.Response;

import java.util.Date;

public class CmtcResponse {
	
	private Long id;
	private String clfCode;
	private Integer cmtcEst;
	private Date cmtcDate;
	private Integer cmtcFunc;
	private String stateCode;
	private String stateName;
	private String blockCode;
	private String blockName;
	private String districtCode;
	private String districtName;
	private String clfName;
	private String status;

	public CmtcResponse(Long id,  String stateCode, String stateName,String districtCode, String districtName,
			String blockCode, String blockName, String clfCode, String clfName,
			Integer cmtcEst, Date cmtcDate, Integer cmtcFunc, String status) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.cmtcEst = cmtcEst;
		this.cmtcDate = cmtcDate;
		this.cmtcFunc = cmtcFunc;
		this.status = status;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.districtCode = districtCode;
		this.districtName = districtName;
	}
	public CmtcResponse() {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	
}
