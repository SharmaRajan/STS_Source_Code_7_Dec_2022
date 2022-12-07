package com.nrlm.mclfmis.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="mclf_subcommittees_details")
public class SubCommDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="clf_code")
	private String clfCode;
	
	@Column(name="moni_memb")
	private Integer moniMemb;
	
	@Column(name="clf_asset_memb")
	private Integer clfAstMemb;
	
	@Column(name="livhood_prom_memb")
	private Integer livHoodMemb;
	
	@Column(name="bank_link_memb")
	private Integer	bnkLinkMemb;
	
	@Column(name="soc_act_memb")
	private Integer	socActMemb;
	
	@Column(name="any_other")
	private Integer	anyOther;
	
	@Column(name="any_other_name")
	private String anyOtherName;
	
	@Column(name="any_other_memb")
	private Integer anyOtherMemb;
	
	@CreationTimestamp
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@CreationTimestamp
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@Column(name="created_by")
	private Long createdBy;
	
	@Column(name="updated_by")
	private Long updatedBy;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="comm_status")
	private Integer commStatus;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clf_code",insertable = false, updatable = false)
	private ClfMaster clfmaster;

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

	public Integer getMoniMemb() {
		return moniMemb;
	}

	public void setMoniMemb(Integer moniMemb) {
		this.moniMemb = moniMemb;
	}

	public Integer getClfAstMemb() {
		return clfAstMemb;
	}

	public void setClfAstMemb(Integer clfAstMemb) {
		this.clfAstMemb = clfAstMemb;
	}

	public Integer getLivHoodMemb() {
		return livHoodMemb;
	}

	public void setLivHoodMemb(Integer livHoodMemb) {
		this.livHoodMemb = livHoodMemb;
	}

	public Integer getBnkLinkMemb() {
		return bnkLinkMemb;
	}

	public void setBnkLinkMemb(Integer bnkLinkMemb) {
		this.bnkLinkMemb = bnkLinkMemb;
	}

	public Integer getSocActMemb() {
		return socActMemb;
	}

	public void setSocActMemb(Integer socActMemb) {
		this.socActMemb = socActMemb;
	}

	public Integer getAnyOther() {
		return anyOther;
	}

	public void setAnyOther(Integer anyOther) {
		this.anyOther = anyOther;
	}

	public String getAnyOtherName() {
		return anyOtherName;
	}

	public void setAnyOtherName(String anyOtherName) {
		this.anyOtherName = anyOtherName;
	}

	public Integer getAnyOtherMemb() {
		return anyOtherMemb;
	}

	public void setAnyOtherMemb(Integer anyOtherMemb) {
		this.anyOtherMemb = anyOtherMemb;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCommStatus() {
		return commStatus;
	}

	public void setCommStatus(Integer commStatus) {
		this.commStatus = commStatus;
	}
	
	
	

}
