package com.nrlm.mclfmis.Entity;

import java.util.Date;

import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mclf_clf_basic_details", uniqueConstraints = @UniqueConstraint(columnNames = { "clfCode" }))
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public class ClfBasicProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//	@NotNull
	private String clfCode;
//	@NotNull
	private Integer clfRecgn;
	private String recgnDate;

//	@NotNull	
	private Integer monthId;

//	@NotNull
	private Integer finYearId;

//	@NotNull
	private String npName;
//	@NotNull
	private Integer npDesgn;
	private String otherDesgn;
//	@NotNull
	private Integer officeStatus;
	private Integer profileStatus;
	private String pan;
	private String tan;
	private String gstin;
	private Integer status;
	private Integer createdBy;
	private Integer updatedBy;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@CreationTimestamp
	@Column(name = "updated_at", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@JsonIgnore
//	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clfCode", referencedColumnName = "clf_code", insertable = false, updatable = false)
	private ClfMaster clfmaster;

	public ClfMaster getClfmaster() {
		return clfmaster;
	}

	public void setClfmaster(ClfMaster clfmaster) {
		this.clfmaster = clfmaster;
	}
	

	public Integer getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(Integer profileStatus) {
		this.profileStatus = profileStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public Integer getClfRecgn() {
		return clfRecgn;
	}

	public void setClfRecgn(Integer clfRecgn) {
		this.clfRecgn = clfRecgn;
	}

	public String getRecgnDate() {
		return recgnDate;
	}

	public void setRecgnDate(String recgnDate) {
		this.recgnDate = recgnDate;
	}

	public String getNpName() {
		return npName;
	}

	public void setNpName(String npName) {
		this.npName = npName;
	}

	public Integer getNpDesgn() {
		return npDesgn;
	}

	public void setNpDesgn(Integer npDesgn) {
		this.npDesgn = npDesgn;
	}

	public String getOtherDesgn() {
		return otherDesgn;
	}

	public void setOtherDesgn(String otherDesgn) {
		this.otherDesgn = otherDesgn;
	}

	public Integer getOfficeStatus() {
		return officeStatus;
	}

	public void setOfficeStatus(Integer officeStatus) {
		this.officeStatus = officeStatus;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTan() {
		return tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
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

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public Integer getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(Integer finYearId) {
		this.finYearId = finYearId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public ClfBasicProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClfBasicProfile(Integer id, String clfCode, Integer clfRecgn, String recgnDate, Integer monthId,
			Integer finYearId, String npName, Integer npDesgn, String otherDesgn, Integer officeStatus, String pan,
			String tan, String gstin, Integer status, Integer createdBy, Integer updatedBy, Date createdAt,
			Date updatedAt, ClfMaster clfmaster) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.clfRecgn = clfRecgn;
		this.recgnDate = recgnDate;
		this.monthId = monthId;
		this.finYearId = finYearId;
		this.npName = npName;
		this.npDesgn = npDesgn;
		this.otherDesgn = otherDesgn;
		this.officeStatus = officeStatus;
		this.pan = pan;
		this.tan = tan;
		this.gstin = gstin;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.clfmaster = clfmaster;
	}
}
