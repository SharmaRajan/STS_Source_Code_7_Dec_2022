package com.nrlm.mclfmis.customEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClfMasterEntity {

	@Id
	private String clfCode;
	
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private String clfName;
	private String formationDate;
	
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
	public ClfMasterEntity(String stateCode, String stateName, String districtCode, String districtName,
			String blockCode, String blockName,String clfCode, String clfName,String formationDate) {
		super();
		this.clfCode = clfCode;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.clfName = clfName;
		this.formationDate = formationDate;
	}
	public String getFormationDate() {
		return formationDate;
	}
	public void setFormationDate(String formationDate) {
		this.formationDate = formationDate;
	}

	
	

}
