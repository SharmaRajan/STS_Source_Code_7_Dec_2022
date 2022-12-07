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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="mclf_clf_cut_off_monthly_details")
public class ClfCutOffMonthlyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="clf_code")
	private String clfCode;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clf_code",insertable = false, updatable = false)
	private ClfMaster clfprofile;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cut_off_year",insertable = false, updatable = false)
	private Financial financeYear;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cut_off_month",insertable = false, updatable = false)
	private Month month;
	
	@Column(name="cut_off_month")
	private Integer cutOffMonth;
	
	@Column(name="cut_off_year")
	private Integer cutOffYear;
	
	@Column(name="cut_off_status")
	private Integer cutOffStatus;
	
	@Column(name="overall_status")
	private Integer overallStatus;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Financial getFinanceYear() {
		return financeYear;
	}

	public void setFinanceYear(Financial financeYear) {
		this.financeYear = financeYear;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Integer getCutOffMonth() {
		return cutOffMonth;
	}

	public void setCutOffMonth(Integer cutOffMonth) {
		this.cutOffMonth = cutOffMonth;
	}

	public Integer getCutOffYear() {
		return cutOffYear;
	}

	public void setCutOffYear(Integer cutOffYear) {
		this.cutOffYear = cutOffYear;
	}

	public Integer getCutOffStatus() {
		return cutOffStatus;
	}

	public void setCutOffStatus(Integer cutOffStatus) {
		this.cutOffStatus = cutOffStatus;
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

	public ClfMaster getClfprofile() {
		return clfprofile;
	}

	public void setClfprofile(ClfMaster clfmaster) {
		this.clfprofile = clfmaster;
	}

	/*public String getClfMasterCode() {
		return clfMasterCode;
	}

	public void setClfMasterCode(String clfMasterCode) {
		this.clfMasterCode = clfMasterCode;
	}*/

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public Integer getOverallStatus() {
		return overallStatus;
	}

	public void setOverallStatus(Integer overallStatus) {
		this.overallStatus = overallStatus;
	}

	/*public ClfMaster getClfcutoff() {
		return clfcutoff;
	}

	public void setClfcutoff(ClfMaster clfcutoff) {
		this.clfcutoff = clfcutoff;
	}*/
	
	
	
}
