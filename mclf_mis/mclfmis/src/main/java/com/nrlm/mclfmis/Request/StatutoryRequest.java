package com.nrlm.mclfmis.Request;

import java.util.Date;
import java.util.List;

public class StatutoryRequest {
	
	private Long id;
	private String clfCode;
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
    private List<StatutoryLicenseRequest> licenseList;
    private Integer isRating;
    private Integer ratingAgency ;
    private Date lastRatingDate ;
    private Integer govRating ;
    private Integer finPerformRating ;
    private Integer mgmtRating ;
    private Integer overallRating ;
    private Integer isDraft ;
    
    
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
	
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	public List<StatutoryLicenseRequest> getLicenseList() {
		return licenseList;
	}
	public void setLicenseList(List<StatutoryLicenseRequest> licenseList) {
		this.licenseList = licenseList;
	}
    
    
    
    
}
