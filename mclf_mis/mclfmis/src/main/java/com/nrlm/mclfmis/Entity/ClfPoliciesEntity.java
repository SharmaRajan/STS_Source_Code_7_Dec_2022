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
@Table(name="mclf_compliance_clf_policies")
public class ClfPoliciesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "clf_code")
	private String clfCode;
	
	@Column(name = "cbo_hr_policies")
	private int cboHrPolicies;
	
	@Column(name = "gov_policies")
	private int govPolicies;
	
	@Column(name = "fin_policies")
	private int finPolicies;
	
	@Column(name = "status")
	private int status;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	
	@Column(name="compliance_status")
	private Integer complianceStatus;
	
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

	public int getCboHrPolicies() {
		return cboHrPolicies;
	}

	public void setCboHrPolicies(int cboHrPolicies) {
		this.cboHrPolicies = cboHrPolicies;
	}

	public int getGovPolicies() {
		return govPolicies;
	}

	public void setGovPolicies(int govPolicies) {
		this.govPolicies = govPolicies;
	}

	public int getFinPolicies() {
		return finPolicies;
	}

	public void setFinPolicies(int finPolicies) {
		this.finPolicies = finPolicies;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
	
	
	
	
	
	
	
	
}
