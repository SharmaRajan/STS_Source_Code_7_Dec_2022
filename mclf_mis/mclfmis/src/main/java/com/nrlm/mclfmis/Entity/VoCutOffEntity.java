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
@Table(name="mclf_vo_cut_off_details")
public class VoCutOffEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long voCutOffId;
	
	@Column(name="vo_code")
	private String voCode;
	
	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vo_code",insertable = false, updatable = false)
	private VoProfile voprofile;
	
	@Column(name="cut_off_year")
	private Integer cutOffYear;
	
	@Column(name="cut_off_month")
	private Integer cutOffMonth;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cut_off_year",insertable = false, updatable = false)
	private Financial financeYear;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cut_off_month",insertable = false, updatable = false)
	private Month month;
	
	@Column(name="cut_off_status")
	private Integer cutOffStatus;
	
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

	public Long getVoCutOffId() {
		return voCutOffId;
	}

	public void setVoCutOffId(Long voCutOffId) {
		this.voCutOffId = voCutOffId;
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

	public VoProfile getVoprofile() {
		return voprofile;
	}

	public void setVoprofile(VoProfile voprofile) {
		this.voprofile = voprofile;
	}

	public String getVoCode() {
		return voCode;
	}

	public void setVoCode(String voCode) {
		this.voCode = voCode;
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
