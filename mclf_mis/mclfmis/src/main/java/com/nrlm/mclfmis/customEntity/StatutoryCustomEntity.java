package com.nrlm.mclfmis.customEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StatutoryCustomEntity {

	@Id
	private Long id;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private String clfCode;
	private String clfName;
    private Integer finYearId ;
    private Integer isExtAudit ;
    private Date lastExtAuditDate ;
    private Integer isAgbmConduct ;
    private Date lastAgbmConductDate ;
    private Integer lastAgbmParticipant ;
    private Integer isLdrRotationDue ;
    private Date lastLdrRotationDate ;
    private Integer isItReturns ;
    private Integer itReturnsFinYearId ;
    private Date lastItReturnsDate ;
    private Integer isAnnualReturns ;
    private Integer annualReturnsFinYearId ;
    private Date annualReturnsDate ;
    private Integer isInputLicense ;
    private Integer isRating;
    private Integer ratingAgency;
    private Date lastRatingDate ;
    private Integer govRating ;
    private Integer finPerformRating ;
    private Integer mgmtRating ;
    private Integer overallRating ;
    private Long createBy;
    private Long updatedBy;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;
    private String complianceStatus; 
    private String finYear;
    //private Integer statusValue;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	
	public StatutoryCustomEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatutoryCustomEntity(Long id, String stateCode, String stateName, String districtCode, String districtName,
			String blockCode, String blockName, String clfCode, String clfName, Integer finYearId, Integer isExtAudit,
			Date lastExtAuditDate, Integer isAgbmConduct, Date lastAgbmConductDate, Integer lastAgbmParticipant,
			Integer isLdrRotationDue, Date lastLdrRotationDate, Integer isItReturns, Integer itReturnsFinYearId,
			Date lastItReturnsDate, Integer isAnnualReturns, Integer annualReturnsFinYearId, Date annualReturnsDate,
			Integer isInputLicense, Integer isRating, Integer ratingAgency, Date lastRatingDate, Integer govRating,
			Integer finPerformRating, Integer mgmtRating, Integer overallRating,
			Long createdBy,Long updatedBy,Date createdAt, Date updatedAt,Integer status,String complianceStatus,String finYear) {
		super();
		this.id = id;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.finYearId = finYearId;
		this.isExtAudit = isExtAudit;
		this.lastExtAuditDate = lastExtAuditDate;
		this.isAgbmConduct = isAgbmConduct;
		this.lastAgbmConductDate = lastAgbmConductDate;
		this.lastAgbmParticipant = lastAgbmParticipant;
		this.isLdrRotationDue = isLdrRotationDue;
		this.lastLdrRotationDate = lastLdrRotationDate;
		this.isItReturns = isItReturns;
		this.itReturnsFinYearId = itReturnsFinYearId;
		this.lastItReturnsDate = lastItReturnsDate;
		this.isAnnualReturns = isAnnualReturns;
		this.annualReturnsFinYearId = annualReturnsFinYearId;
		this.annualReturnsDate = annualReturnsDate;
		this.isInputLicense = isInputLicense;
		this.isRating = isRating;
		this.ratingAgency = ratingAgency;
		this.lastRatingDate = lastRatingDate;
		this.govRating = govRating;
		this.finPerformRating = finPerformRating;
		this.mgmtRating = mgmtRating;
		this.overallRating = overallRating;
		this.createBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.complianceStatus = complianceStatus;
		this.finYear = finYear;
	}
	public String getComplianceStatus() {
		return complianceStatus;
	}
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	
	
	
	
//	public Integer getStatusValue() {
//		return statusValue;
//	}
//	public void setStatusValue(Integer statusValue) {
//		this.statusValue = statusValue;
//	}
	
    
    
}
