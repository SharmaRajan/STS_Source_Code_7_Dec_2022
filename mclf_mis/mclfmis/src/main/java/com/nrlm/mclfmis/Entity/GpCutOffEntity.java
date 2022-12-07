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
@Table(name="mclf_gp_cut_off_details")
public class GpCutOffEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="grampanchayat_code")
	private String grampanchayatCode;
	
	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="grampanchayat_code",insertable = false, updatable = false)
	private Grampanchayat gp;
	
	@Column(name="cut_off_year")
	private Integer cutOffYear;
	
	@Column(name="cut_off_month")
	private Integer cutOffMonth;
	
	@Column(name="cut_off_status")
	private Integer cutOffStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cut_off_year",insertable = false, updatable = false)
	private Financial financeYear;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cut_off_month",insertable = false, updatable = false)
	private Month month;
	
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

	public Grampanchayat getGp() {
		return gp;
	}

	public void setGp(Grampanchayat gp) {
		this.gp = gp;
	}

	public Integer getCutOffYear() {
		return cutOffYear;
	}

	public void setCutOffYear(Integer cutOffYear) {
		this.cutOffYear = cutOffYear;
	}

	public Integer getCutOffMonth() {
		return cutOffMonth;
	}

	public void setCutOffMonth(Integer cutOffMonth) {
		this.cutOffMonth = cutOffMonth;
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

	public String getGrampanchayatCode() {
		return grampanchayatCode;
	}

	public void setGrampanchayatCode(String grampanchayatCode) {
		this.grampanchayatCode = grampanchayatCode;
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

	
}
