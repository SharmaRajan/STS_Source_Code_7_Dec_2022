package com.nrlm.mclfmis.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "mclf_financial_year")

public class Financial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fncl_year")
	private String fnclYear;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fncl_start_date")
	private Date fnclStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fncl_end_date")
	private Date fnclEndDate;
	
	private Integer status;
	
	//private Integer cutOff;
	//private Integer monthlyCutOff;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@CreationTimestamp
	@Column(name = "updated_at", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFnclYear() {
		return fnclYear;
	}
	public void setFnclYear(String fnclYear) {
		this.fnclYear = fnclYear;
	}
	public Date getFnclStartDate() {
		return fnclStartDate;
	}
	public void setFnclStartDate(Date fnclStartDate) {
		this.fnclStartDate = fnclStartDate;
	}
	public Date getFnclEndDate() {
		return fnclEndDate;
	}
	public void setFnclEndDate(Date fnclEndDate) {
		this.fnclEndDate = fnclEndDate;
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
	public Financial(Integer id, String fnclYear, Date fnclStartDate, Date fnclEndDate, Integer status, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.fnclYear = fnclYear;
		this.fnclStartDate = fnclStartDate;
		this.fnclEndDate = fnclEndDate;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Financial() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*public Integer getCutOff() {
		return cutOff;
	}
	public void setCutOff(Integer cutOff) {
		this.cutOff = cutOff;
	}
	public Integer getMonthlyCutOff() {
		return monthlyCutOff;
	}
	public void setMonthlyCutOff(Integer monthlyCutOff) {
		this.monthlyCutOff = monthlyCutOff;
	}
	*/


}
