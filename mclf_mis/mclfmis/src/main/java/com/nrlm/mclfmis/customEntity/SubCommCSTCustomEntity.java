package com.nrlm.mclfmis.customEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class SubCommCSTCustomEntity {

	@Id
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
	private Long createdBy;
	private Long updatedBy;
	private Date createdAt;
	private Date updatedAt;
	private Integer status;
	private String commStatus;
	
	public SubCommCSTCustomEntity(Long id, String clfCode, String clfName, String stateCode, String stateName,
			String districtCode, String districtName, String blockCode, String blockName, Integer cstFormed,
			Integer totCstMemb, Long createdBy, Long updatedBy, Date createdAt, Date updatedAt, Integer status,String commStatus) {
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
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.setCommStatus(commStatus);
	}
	
	public SubCommCSTCustomEntity() {}

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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCommStatus() {
		return commStatus;
	}

	public void setCommStatus(String commStatus) {
		this.commStatus = commStatus;
	}

	
	
	
	
	
}
