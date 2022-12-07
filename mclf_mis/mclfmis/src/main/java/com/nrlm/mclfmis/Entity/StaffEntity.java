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
@Table(name="mclf_clf_staff_details")
public class StaffEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "clf_code")
	private String clfCode;
	
	@Column(name = "staff_type")
	private Integer staffType;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_no")
	private String phone;
	
	@Column(name = "gender")
	private Integer gender;
	
	@Column(name = "social_category")
	private Integer socialCategory;
	
	@Column(name = "minority")
	private Integer minority;
	
	@Column(name = "differently_abled")
	private Integer diffAble;
	
	@Column(name = "monthly_remuneration")
	private Integer monthRemu;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "edu_qual")
	private Integer eduQual;
	
	@Column(name = "edu_area")
	private String eduArea;
	
	@Column(name = "additional_info")
	private String addInfo;
	
	@Column(name = "exp_yrs")
	private Integer expYrs;
	
	@Column(name = "exp_desc")
	private String expDesc;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "joining_date")
	private Date joinDate;
	
	@Column(name = "staff_status")
	private Integer  staffStatus;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "leaving_date")
	private Date leaveDate;
	
	@Column(name = "leaving_rsn")
	private Integer leaveReason;
	
	@Column(name = "other_rsn")
	private String otherRes;
	
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
	
	@Column(name = "status")
	private Integer status;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clf_code",insertable = false, updatable = false)
	private ClfMaster clfmaster;
	
	
	
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

	public Integer getStaffType() {
		return staffType;
	}

	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getSocialCategory() {
		return socialCategory;
	}

	public void setSocialCategory(Integer socialCategory) {
		this.socialCategory = socialCategory;
	}

	public Integer getMinority() {
		return minority;
	}

	public void setMinority(Integer minority) {
		this.minority = minority;
	}

	public Integer getDiffAble() {
		return diffAble;
	}

	public void setDiffAble(Integer diffAble) {
		this.diffAble = diffAble;
	}

	public Integer getMonthRemu() {
		return monthRemu;
	}

	public void setMonthRemu(Integer monthRemu) {
		this.monthRemu = monthRemu;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getEduQual() {
		return eduQual;
	}

	public void setEduQual(Integer eduQual) {
		this.eduQual = eduQual;
	}

	public String getEduArea() {
		return eduArea;
	}

	public void setEduArea(String eduArea) {
		this.eduArea = eduArea;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public Integer getExpYrs() {
		return expYrs;
	}

	public void setExpYrs(Integer expYrs) {
		this.expYrs = expYrs;
	}

	public String getExpDesc() {
		return expDesc;
	}

	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Integer getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(Integer staffStatus) {
		this.staffStatus = staffStatus;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Integer getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(Integer leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getOtherRes() {
		return otherRes;
	}

	public void setOtherRes(String otherRes) {
		this.otherRes = otherRes;
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

	@Override
	public String toString() {
		return "StaffEntity [id=" + id + ", clfCode=" + clfCode + ", staffType=" + staffType + ", name=" + name
				+ ", phone=" + phone + ", gender=" + gender + ", socialCategory=" + socialCategory + ", minority="
				+ minority + ", diffAble=" + diffAble + ", monthRemu=" + monthRemu + ", address=" + address
				+ ", eduQual=" + eduQual + ", eduArea=" + eduArea + ", addInfo=" + addInfo + ", expYrs=" + expYrs
				+ ", expDesc=" + expDesc + ", joinDate=" + joinDate + ", actStatus=" + staffStatus + ", leaveDate="
				+ leaveDate + ", leaveReason=" + leaveReason + ", otherRes=" + otherRes + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", status="
				+ status + "]";
	}
	
	
	
	
	
}
