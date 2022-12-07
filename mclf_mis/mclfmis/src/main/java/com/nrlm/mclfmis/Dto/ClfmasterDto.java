package com.nrlm.mclfmis.Dto;

import com.nrlm.mclfmis.Entity.ClfVoMapping;
import com.nrlm.mclfmis.Entity.Clfgpmapping;

import java.util.List;

public class ClfmasterDto {
    private Integer clfCodeNo;
    private String clfCode;
    private String blockCode;
    private String clfName;
    private Integer monthlyEcDateD1;
    private Integer monthlyEcDateD2;
    private String ccAcName;
    private String ccAcContact;
    private String clfBookKeeperName;
    private String clfBookKeeperContact;
    private String formationDate;
    private String clfOfficeBuilding;
    private String clfOfficeAddress;
    private Integer monthlySubscriptionAmountVo;
    private String createdOn;
    private String createdBy;
    private String updatedBy;
    private String updatedOn;
    private Integer annualMembershipFee;
    private String activeDetail;
    private String regsNo;
    private String clfRenvlDate;
    private Integer shareCapital;
    private String districtName;
	private String stateName;
	private String blockName;
    private List<Clfgpmapping> clfgpmappings;
	private List<ClfVoMapping> clfVoMappings;
	public ClfmasterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClfmasterDto(Integer clfCodeNo, String clfCode, String blockCode, String clfName, Integer monthlyEcDateD1,
			Integer monthlyEcDateD2, String ccAcName, String ccAcContact, String clfBookKeeperName,
			String clfBookKeeperContact, String formationDate, String clfOfficeBuilding, String clfOfficeAddress,
			Integer monthlySubscriptionAmountVo, String createdOn, String createdBy, String updatedBy, String updatedOn,
			Integer annualMembershipFee, String activeDetail, String regsNo, String clfRenvlDate, Integer shareCapital,
			String districtName, String stateName, String blockName, List<Clfgpmapping> clfgpmappings,
			List<ClfVoMapping> clfVoMappings) {
		super();
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
		this.districtName = districtName;
		this.stateName = stateName;
		this.blockName = blockName;
		this.clfgpmappings = clfgpmappings;
		this.clfVoMappings = clfVoMappings;
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
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public List<Clfgpmapping> getClfgpmappings() {
		return clfgpmappings;
	}
	public void setClfgpmappings(List<Clfgpmapping> clfgpmappings) {
		this.clfgpmappings = clfgpmappings;
	}
	public List<ClfVoMapping> getClfVoMappings() {
		return clfVoMappings;
	}
	public void setClfVoMappings(List<ClfVoMapping> clfVoMappings) {
		this.clfVoMappings = clfVoMappings;
	}


}
