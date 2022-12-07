package com.nrlm.mclfmis.Response;

import java.util.List;

import com.nrlm.mclfmis.Entity.ClfVoMapping;
import com.nrlm.mclfmis.Entity.Clfgpmapping;

public class ClfMasterResponse {
    private Integer clfCodeNo;
    private String clfCode;
    private String clfName;
    private String districtName;
	private String stateName;
	private String blockName;
	
	public ClfMasterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClfMasterResponse(Integer clfCodeNo, String clfCode, String clfName, String districtName, String stateName,
			String blockName) {
		super();
		this.clfCodeNo = clfCodeNo;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.districtName = districtName;
		this.stateName = stateName;
		this.blockName = blockName;
	}
	
	public Integer getClfCodeNo() {
		return clfCodeNo;
	}
	public void setClfCodeNo(Integer clfCodeNo) {
		this.clfCodeNo = clfCodeNo;
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
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	
}
