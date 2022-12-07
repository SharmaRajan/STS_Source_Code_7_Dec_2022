package com.nrlm.mclfmis.Dto;


import java.util.Date;

public class ClfGpMappingDto {
	private int gpMappingId;
	private String clfCode;
	private String grampanchayatCode;
	private String createdBy;
	private String updatedBy;
	private Date createdOn;

	public ClfGpMappingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClfGpMappingDto(int gpMappingId, String clfCode, String grampanchayatCode, String createdBy,
			String updatedBy, Date createdOn) {
		super();
		this.gpMappingId = gpMappingId;
		this.clfCode = clfCode;
		this.grampanchayatCode = grampanchayatCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdOn = createdOn;
	}

	public int getGpMappingId() {
		return gpMappingId;
	}

	public void setGpMappingId(int gpMappingId) {
		this.gpMappingId = gpMappingId;
	}

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}

	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
}
