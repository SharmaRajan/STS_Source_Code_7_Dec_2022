package com.nrlm.mclfmis.Entity;

import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "clf_profile")
public class ClfMaster {

	@Column(name = "clf_code_no")
	private Integer clfCodeNo;
	@Id
	@Column(name = "clf_code")
	private String clfCode;

	@Column(name = "block_code")
	private String blockCode;

	@Column(name = "clf_name")
	private String clfName;

	@Column(name = "monthly_ec_date_d1")
	private Integer monthlyEcDateD1;

	@Column(name = "monthly_ec_date_d2")
	private Integer monthlyEcDateD2;

	@Column(name = "cc_ac_name")
	private String ccAcName;

	@Column(name = "cc_ac_contact")
	private String ccAcContact;

	@Column(name = "clf_book_keeper_name")
	private String clfBookKeeperName;

	@Column(name = "clf_book_keeper_contact")
	private String clfBookKeeperContact;

	@Column(name = "formation_date")
	private String formationDate;

	@Column(name = "clf_office_building")
	private String clfOfficeBuilding;

	@Column(name = "clf_office_address")
	private String clfOfficeAddress;

	@Column(name = "monthly_subscription_amount_vo")
	private Integer monthlySubscriptionAmountVo;

	@Column(name = "created_on")
	private String createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_on")
	private String updatedOn;

	@Column(name = "annual_membership_fee")
	private Integer annualMembershipFee;

	@Column(name = "active_detail")
	private String activeDetail;

	@Column(name = "regs_no")
	private String regsNo;

	@Column(name = "clf_renvl_date")
	private String clfRenvlDate;

	@Column(name = "share_capital")
	private Integer shareCapital;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Block.class)
	@JoinColumn(name = "block_code", insertable = false, updatable = false)
	private Block block;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clfmaster")
	private List<ClfVoMapping> clfVomapping;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clfgpmaster")
	private List<Clfgpmapping> clfgpmapping;

//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "clfmaster")
//	private List<ClfBasicProfile> clfbasicprofile;
	
	@JsonBackReference
	@OneToOne(mappedBy = "clfmaster")
	private ClfBasicProfile clfbasicprofile;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "clfprofile")
	private List<ClfCutOffEntity> clfcutoff;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "clfprofile")
	private List<ClfCutOffMonthlyEntity> clfcutoffmonthly;
	
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "clfmaster")
	private List<StaffEntity> clfstaff;
	
	
	
	public ClfMaster() {
	}

	public ClfMaster(Integer clfCodeNo, String clfCode, String blockCode, String clfName, Integer monthlyEcDateD1,
			Integer monthlyEcDateD2, String ccAcName, String ccAcContact, String clfBookKeeperName,
			String clfBookKeeperContact, String formationDate, String clfOfficeBuilding, String clfOfficeAddress,
			Integer monthlySubscriptionAmountVo, String createdOn, String createdBy, String updatedBy, String updatedOn,
			Integer annualMembershipFee, String activeDetail, String regsNo, String clfRenvlDate, Integer shareCapital,
			List<ClfVoMapping> clfVomapping, List<Clfgpmapping> clfgpmapping) {
		this.clfCodeNo = clfCodeNo;
		this.clfCode = clfCode;
		this.blockCode = blockCode;
		this.clfName = clfName;
		this.monthlyEcDateD1 = monthlyEcDateD1;
		this.monthlyEcDateD2 = monthlyEcDateD2;
		this.ccAcName = ccAcName;
		this.ccAcContact = ccAcContact;
		this.clfBookKeeperName = clfBookKeeperName;
		this.clfBookKeeperContact = clfBookKeeperContact;
		this.formationDate = formationDate;
		this.clfOfficeBuilding = clfOfficeBuilding;
		this.clfOfficeAddress = clfOfficeAddress;
		this.monthlySubscriptionAmountVo = monthlySubscriptionAmountVo;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.annualMembershipFee = annualMembershipFee;
		this.activeDetail = activeDetail;
		this.regsNo = regsNo;
		this.clfRenvlDate = clfRenvlDate;
		this.shareCapital = shareCapital;
		this.clfVomapping = clfVomapping;
		this.clfgpmapping = clfgpmapping;
	}
	
	public ClfBasicProfile getClfbasicprofile() {
		return clfbasicprofile;
	}

	public void setClfbasicprofile(ClfBasicProfile clfbasicprofile) {
		this.clfbasicprofile = clfbasicprofile;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public List<ClfVoMapping> getClfVomapping() {
		return clfVomapping;
	}

	public void setClfVomapping(List<ClfVoMapping> clfVomapping) {
		this.clfVomapping = clfVomapping;
	}

	public List<Clfgpmapping> getClfgpmapping() {
		return clfgpmapping;
	}

	public void setClfgpmapping(List<Clfgpmapping> clfgpmapping) {
		this.clfgpmapping = clfgpmapping;
	}

	public Integer getClfCodeNo() {
		return clfCodeNo;
	}

	public void setClfCodeNo(Integer clfCodeNo) {
		this.clfCodeNo = clfCodeNo;
	}

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getClfName() {
		return clfName;
	}

	public void setClfName(String clfName) {
		this.clfName = clfName;
	}

	public Integer getMonthlyEcDateD1() {
		return monthlyEcDateD1;
	}

	public void setMonthlyEcDateD1(Integer monthlyEcDateD1) {
		this.monthlyEcDateD1 = monthlyEcDateD1;
	}

	public Integer getMonthlyEcDateD2() {
		return monthlyEcDateD2;
	}

	public void setMonthlyEcDateD2(Integer monthlyEcDateD2) {
		this.monthlyEcDateD2 = monthlyEcDateD2;
	}

	public String getCcAcName() {
		return ccAcName;
	}

	public void setCcAcName(String ccAcName) {
		this.ccAcName = ccAcName;
	}

	public String getCcAcContact() {
		return ccAcContact;
	}

	public void setCcAcContact(String ccAcContact) {
		this.ccAcContact = ccAcContact;
	}

	public String getClfBookKeeperName() {
		return clfBookKeeperName;
	}

	public void setClfBookKeeperName(String clfBookKeeperName) {
		this.clfBookKeeperName = clfBookKeeperName;
	}

	public String getClfBookKeeperContact() {
		return clfBookKeeperContact;
	}

	public void setClfBookKeeperContact(String clfBookKeeperContact) {
		this.clfBookKeeperContact = clfBookKeeperContact;
	}

	public String getFormationDate() {
		return formationDate;
	}

	public void setFormationDate(String formationDate) {
		this.formationDate = formationDate;
	}

	public String getClfOfficeBuilding() {
		return clfOfficeBuilding;
	}

	public void setClfOfficeBuilding(String clfOfficeBuilding) {
		this.clfOfficeBuilding = clfOfficeBuilding;
	}

	public String getClfOfficeAddress() {
		return clfOfficeAddress;
	}

	public void setClfOfficeAddress(String clfOfficeAddress) {
		this.clfOfficeAddress = clfOfficeAddress;
	}

	public Integer getMonthlySubscriptionAmountVo() {
		return monthlySubscriptionAmountVo;
	}

	public void setMonthlySubscriptionAmountVo(Integer monthlySubscriptionAmountVo) {
		this.monthlySubscriptionAmountVo = monthlySubscriptionAmountVo;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getAnnualMembershipFee() {
		return annualMembershipFee;
	}

	public void setAnnualMembershipFee(Integer annualMembershipFee) {
		this.annualMembershipFee = annualMembershipFee;
	}

	public String getActiveDetail() {
		return activeDetail;
	}

	public void setActiveDetail(String activeDetail) {
		this.activeDetail = activeDetail;
	}

	public String getRegsNo() {
		return regsNo;
	}

	public void setRegsNo(String regsNo) {
		this.regsNo = regsNo;
	}

	public String getClfRenvlDate() {
		return clfRenvlDate;
	}

	public void setClfRenvlDate(String clfRenvlDate) {
		this.clfRenvlDate = clfRenvlDate;
	}

	public Integer getShareCapital() {
		return shareCapital;
	}

	public void setShareCapital(Integer shareCapital) {
		this.shareCapital = shareCapital;
	}

	@Override
	public String toString() {
		return "ClfProfile{" + "clfCodeNo=" + clfCodeNo + ", clfCode='" + clfCode + '\'' + ", blockCode='" + blockCode
				+ '\'' + ", clfName='" + clfName + '\'' + ", monthlyEcDateD1=" + monthlyEcDateD1 + ", monthlyEcDateD2="
				+ monthlyEcDateD2 + ", ccAcName='" + ccAcName + '\'' + ", ccAcContact='" + ccAcContact + '\''
				+ ", clfBookKeeperName='" + clfBookKeeperName + '\'' + ", clfBookKeeperContact='" + clfBookKeeperContact
				+ '\'' + ", formationDate='" + formationDate + '\'' + ", clfOfficeBuilding='" + clfOfficeBuilding + '\''
				+ ", clfOfficeAddress='" + clfOfficeAddress + '\'' + ", monthlySubscriptionAmountVo="
				+ monthlySubscriptionAmountVo + ", createdOn='" + createdOn + '\'' + ", createdBy='" + createdBy + '\''
				+ ", updatedBy='" + updatedBy + '\'' + ", updatedOn='" + updatedOn + '\'' + ", annualMembershipFee="
				+ annualMembershipFee + ", activeDetail='" + activeDetail + '\'' + ", regsNo='" + regsNo + '\''
				+ ", clfRenvlDate='" + clfRenvlDate + '\'' + ", shareCapital=" + shareCapital + '}';
	}

	public void removeClfgpmapping(Clfgpmapping clfgp) {
		clfgp.setClfgpmaster(null);
		this.getClfgpmapping().remove(clfgp);
	}

	//@OneToOne(fetch = FetchType.LAZY,optional=true,mappedBy = "clfprofile")
	//@LazyToOne(LazyToOneOption.NO_PROXY)
	public List<ClfCutOffEntity> getClfcutoff() {
		return clfcutoff;
	}

	public void setClfcutoff(List<ClfCutOffEntity> clfcutoff) {
		this.clfcutoff = clfcutoff;
	}

	public List<ClfCutOffMonthlyEntity> getClfcutoffmonthly() {
		return clfcutoffmonthly;
	}

	public void setClfcutoffmonthly(List<ClfCutOffMonthlyEntity> clfcutoffmonthly) {
		this.clfcutoffmonthly = clfcutoffmonthly;
	}
}
