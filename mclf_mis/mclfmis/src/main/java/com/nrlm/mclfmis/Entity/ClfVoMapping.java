package com.nrlm.mclfmis.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clf_vo_mapping")
public class ClfVoMapping {

	
	@Column(name = "mapping_id")
	private int mappingId;
	@Column(name = "clf_code")
	private String clfCode;
	@Column(name = "gp_code")
	private String gpCode;
	@Id
	@Column(name = "vo_code")
	private String voCode;
	@Column(name = "vo_joining_date")
	private String voJoiningDate;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_on")
	private String createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "updated_on")
	private String updatedOn;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ClfMaster.class)
	@JoinColumn(name = "clf_code", insertable = false, updatable = false)
	private ClfMaster clfmaster;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vo_code", insertable = false, updatable = false)
	private VoProfile voprofile;

	
	
	public VoProfile getVoprofile() {
		return voprofile;
	}

	public void setVoprofile(VoProfile voprofile) {
		this.voprofile = voprofile;
	}

	public ClfMaster getClfmaster() {
		return clfmaster;
	}

	public void setClfmaster(ClfMaster clfmaster) {
		this.clfmaster = clfmaster;
	}

	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public String getVoJoiningDate() {
		return voJoiningDate;
	}

	public void setVoJoiningDate(String voJoiningDate) {
		this.voJoiningDate = voJoiningDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getGpCode() {
		return gpCode;
	}

	public void setGpCode(String gpCode) {
		this.gpCode = gpCode;
	}

	public String getVoCode() {
		return voCode;
	}

	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public ClfVoMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClfVoMapping(int mappingId, String clfCode, String gpCode, String voCode, String voJoiningDate,
			String createdBy, String createdOn, String updatedBy, String updatedOn, ClfMaster clfmaster,
			VoProfile voprofile) {
		super();
		this.mappingId = mappingId;
		this.clfCode = clfCode;
		this.gpCode = gpCode;
		this.voCode = voCode;
		this.voJoiningDate = voJoiningDate;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.clfmaster = clfmaster;
		this.voprofile = voprofile;
	}
	
	

}
