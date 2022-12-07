package com.nrlm.mclfmis.Entity;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clf_grampanchayat_mapping")
public class Clfgpmapping {

	@Id
	@Column(name = "gp_mapping_id")
	private int gpMappingId;

	@Column(name = "clf_code")
	private String clfCode;
	@Column(name = "grampanchayat_code")
	private String grampanchayatCode;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;

	@CreationTimestamp
	@Column(name = "created_on", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ClfMaster.class)
	@JoinColumn(name = "clf_code", insertable = false, updatable = false)
	private ClfMaster clfgpmaster;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="grampanchayat_code",insertable = false, updatable = false)
	private Grampanchayat gp;

	public ClfMaster getClfgpmaster() {
		return clfgpmaster;
	}

	public void setClfgpmaster(ClfMaster clfgpmaster) {
		this.clfgpmaster = clfgpmaster;
	}

	public Clfgpmapping() {
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

	public Clfgpmapping(int gpMappingId, String clfCode, String grampanchayatCode, String createdBy, String updatedBy,
			Date createdOn, ClfMaster clfgpmaster) {
		super();
		this.gpMappingId = gpMappingId;
		this.clfCode = clfCode;
		this.grampanchayatCode = grampanchayatCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdOn = createdOn;
		this.clfgpmaster = clfgpmaster;
	}

	public Grampanchayat getGp() {
		return gp;
	}

	public void setGp(Grampanchayat gp) {
		this.gp = gp;
	}
	
}
