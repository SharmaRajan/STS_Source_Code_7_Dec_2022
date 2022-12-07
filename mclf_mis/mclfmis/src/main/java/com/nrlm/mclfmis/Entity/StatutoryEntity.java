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
@Table(name="mclf_compliance_statutory")
public class StatutoryEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="clf_code")
	private String clfCode;
	
	@Column(name="fin_year_id")
	private Integer finYearId;
	
	@Column(name="is_ext_audit")
	private Integer isExtAudit;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_ext_audit_date")
	private Date lastExtAuditDate;
	
	@Column(name="is_agbm_conduct")
	private Integer isAgbmConduct;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_agbm_conduct_date")
	private Date lastAgbmConductDate;
	
	@Column(name="last_agbm_participant")
	private Integer lastAgbmParticipant;
	
	@Column(name="is_ldr_rotation_due")
	private Integer isLdrRotationDue;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_ldr_rotation_date")
	private Date lastLdrRotationDate;
	
	@Column(name="is_it_returns")
	private Integer isItReturns;
	
	@Column(name="it_returns_fin_year_id")
	private Integer itReturnsFinYearId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_it_returns_date")
	private Date lastItReturnsDate;
	
	@Column(name="is_annual_returns")
	private Integer isAnnualReturns;
	
	@Column(name="annual_returns_fin_year_id")
	private Integer annualReturnsFinYearId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="annual_returns_date")
	private Date annualReturnsDate;
	
	@Column(name="is_input_license")
	private Integer isInputLicense;
	
	@Column(name="is_rating")
	private Integer isRating;
	
	@Column(name="rating_agency")
	private Integer ratingAgency;
	
	@Temporal(TemporalType.DATE)
	@Column(name="last_rating_date")
	private Date lastRatingDate;
	
	@Column(name="gov_rating")
	private Integer govRating;
	
	@Column(name="fin_perform_rating")
	private Integer finPerformRating;
	
	@Column(name="mgmt_rating")
	private Integer mgmtRating;
	
	
	@Column(name="overall_rating")
	private Integer overallRating;
	
	@Column(name="compliance_status")
	private Integer complianceStatus;
	
	@Column(name="status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="created_by")
	private Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name="updated_by")
	private Long updatedBy;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clf_code",insertable = false, updatable = false)
	private ClfMaster clfmaster;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fin_year_id",insertable = false, updatable = false)
	private Financial financial;
	
	public ClfMaster getClfmaster() {
		return clfmaster;
	}

	public void setClfmaster(ClfMaster clfmaster) {
		this.clfmaster = clfmaster;
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

	public Integer getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(Integer finYearId) {
		this.finYearId = finYearId;
	}

	public Integer getIsExtAudit() {
		return isExtAudit;
	}

	public void setIsExtAudit(Integer isExtAudit) {
		this.isExtAudit = isExtAudit;
	}

	public Date getLastExtAuditDate() {
		return lastExtAuditDate;
	}

	public void setLastExtAuditDate(Date lastExtAuditDate) {
		this.lastExtAuditDate = lastExtAuditDate;
	}

	public Integer getIsAgbmConduct() {
		return isAgbmConduct;
	}

	public void setIsAgbmConduct(Integer isAgbmConduct) {
		this.isAgbmConduct = isAgbmConduct;
	}

	public Date getLastAgbmConductDate() {
		return lastAgbmConductDate;
	}

	public void setLastAgbmConductDate(Date lastAgbmConductDate) {
		this.lastAgbmConductDate = lastAgbmConductDate;
	}

	public Integer getLastAgbmParticipant() {
		return lastAgbmParticipant;
	}

	public void setLastAgbmParticipant(Integer lastAgbmParticipant) {
		this.lastAgbmParticipant = lastAgbmParticipant;
	}

	public Integer getIsLdrRotationDue() {
		return isLdrRotationDue;
	}

	public void setIsLdrRotationDue(Integer isLdrRotationDue) {
		this.isLdrRotationDue = isLdrRotationDue;
	}

	public Date getLastLdrRotationDate() {
		return lastLdrRotationDate;
	}

	public void setLastLdrRotationDate(Date lastLdrRotationDate) {
		this.lastLdrRotationDate = lastLdrRotationDate;
	}

	public Integer getIsItReturns() {
		return isItReturns;
	}

	public void setIsItReturns(Integer isItReturns) {
		this.isItReturns = isItReturns;
	}

	public Integer getItReturnsFinYearId() {
		return itReturnsFinYearId;
	}

	public void setItReturnsFinYearId(Integer itReturnsFinYearId) {
		this.itReturnsFinYearId = itReturnsFinYearId;
	}

	public Date getLastItReturnsDate() {
		return lastItReturnsDate;
	}

	public void setLastItReturnsDate(Date lastItReturnsDate) {
		this.lastItReturnsDate = lastItReturnsDate;
	}

	public Integer getIsAnnualReturns() {
		return isAnnualReturns;
	}

	public void setIsAnnualReturns(Integer isAnnualReturns) {
		this.isAnnualReturns = isAnnualReturns;
	}

	public Integer getAnnualReturnsFinYearId() {
		return annualReturnsFinYearId;
	}

	public void setAnnualReturnsFinYearId(Integer annualReturnsFinYearId) {
		this.annualReturnsFinYearId = annualReturnsFinYearId;
	}

	public Date getAnnualReturnsDate() {
		return annualReturnsDate;
	}

	public void setAnnualReturnsDate(Date annualReturnsDate) {
		this.annualReturnsDate = annualReturnsDate;
	}

	public Integer getIsInputLicense() {
		return isInputLicense;
	}

	public void setIsInputLicense(Integer isInputLicense) {
		this.isInputLicense = isInputLicense;
	}

	
	public Integer getIsRating() {
		return isRating;
	}

	public void setIsRating(Integer isRating) {
		this.isRating = isRating;
	}

	public Integer getRatingAgency() {
		return ratingAgency;
	}

	public void setRatingAgency(Integer ratingAgency) {
		this.ratingAgency = ratingAgency;
	}

	public Date getLastRatingDate() {
		return lastRatingDate;
	}

	public void setLastRatingDate(Date lastRatingDate) {
		this.lastRatingDate = lastRatingDate;
	}

	public Integer getGovRating() {
		return govRating;
	}

	public void setGovRating(Integer govRating) {
		this.govRating = govRating;
	}

	public Integer getFinPerformRating() {
		return finPerformRating;
	}

	public void setFinPerformRating(Integer finPerformRating) {
		this.finPerformRating = finPerformRating;
	}

	public Integer getMgmtRating() {
		return mgmtRating;
	}

	public void setMgmtRating(Integer mgmtRating) {
		this.mgmtRating = mgmtRating;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public Integer getComplianceStatus() {
		return complianceStatus;
	}

	public void setComplianceStatus(Integer complianceStatus) {
		this.complianceStatus = complianceStatus;
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Financial getFinancial() {
		return financial;
	}

	public void setFinancial(Financial financial) {
		this.financial = financial;
	}
	
	
}
