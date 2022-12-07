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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="mclf_compliance_int_ctrl")
public class InternalControlEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(name="clf_code")
	private String clfCode;
	
	@Column(name="fin_year_id")
	private Integer finYearId;
	
	@Column(name="qrtr_id")
	private Integer qrtrId;

	@Column(name="int_audit")
	private Integer intAudit;
	
	private Integer grb;
	

	@Column(name="gb_meeting")
	private Integer gbMeeting;
	
	private Integer status;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="updated_by")
	private Long updatedBy;
	
	@Column(name="compliance_status")
	private Integer complianceStatus;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fin_year_id",insertable = false, updatable = false)
	private Financial financial;
	
	
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

	public Integer getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(Integer finYearId) {
		this.finYearId = finYearId;
	}

	public Integer getQrtrId() {
		return qrtrId;
	}

	public void setQrtrId(Integer qrtrId) {
		this.qrtrId = qrtrId;
	}

	public Integer getIntAudit() {
		return intAudit;
	}

	public void setIntAudit(Integer intAudit) {
		this.intAudit = intAudit;
	}

	public Integer getGrb() {
		return grb;
	}

	public void setGrb(Integer grb) {
		this.grb = grb;
	}

	public Integer getGbMeeting() {
		return gbMeeting;
	}

	public void setGbMeeting(Integer gbMeeting) {
		this.gbMeeting = gbMeeting;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getComplianceStatus() {
		return complianceStatus;
	}

	public void setComplianceStatus(Integer complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

	public Financial getFinancial() {
		return financial;
	}

	public void setFinancial(Financial financial) {
		this.financial = financial;
	}

	public ClfMaster getClfmaster() {
		return clfmaster;
	}

	public void setClfmaster(ClfMaster clfmaster) {
		this.clfmaster = clfmaster;
	}
	
	
	
	
	

}
