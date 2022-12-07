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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="mclf_clf_training_cmtc_details")
public class CmtcEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(name="clf_code")
	private String clfCode;
	
	@Column(name="cmtc_est")
	private Integer cmtcEst;
	
	@Temporal(TemporalType.DATE)
	@Column(name="cmtc_date")
	private Date cmtcDate;
	
	@Column(name="cmtc_func")
	private Integer cmtcFunc;
	
	@Column(name="cmtc_status")
	private Integer cmtcStatus;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="created_by")
	private Long createdBy;
	
	@Column(name="updated_by")
	private Long updatedBy;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getCmtcStatus() {
		return cmtcStatus;
	}

	public void setCmtcStatus(Integer cmtcStatus) {
		this.cmtcStatus = cmtcStatus;
	}

	}

	

