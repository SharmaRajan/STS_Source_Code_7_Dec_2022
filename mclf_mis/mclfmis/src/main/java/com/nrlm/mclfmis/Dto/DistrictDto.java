package com.nrlm.mclfmis.Dto;

import java.sql.Timestamp;

public class DistrictDto {
    private String districtCode;
    private String districtName;
    private Timestamp createdOn;
    private String createdBy;
    private Timestamp modifiedOn;
    private String modifedBy;
    private String stateCode;
    private String fundReleaseFlag;
    private String districtShortName;
    private String districtNameTwo;
    private String census2011;
    private String stateName;

    public java.lang.String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(java.lang.String districtCode) {
        this.districtCode = districtCode;
    }

    public java.lang.String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(java.lang.String districtName) {
        this.districtName = districtName;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public java.lang.String getModifedBy() {
        return modifedBy;
    }

    public void setModifedBy(java.lang.String modifedBy) {
        this.modifedBy = modifedBy;
    }

    public java.lang.String getStateCode() {
        return stateCode;
    }

    public void setStateCode(java.lang.String stateCode) {
        this.stateCode = stateCode;
    }

    public java.lang.String getFundReleaseFlag() {
        return fundReleaseFlag;
    }

    public void setFundReleaseFlag(java.lang.String fundReleaseFlag) {
        this.fundReleaseFlag = fundReleaseFlag;
    }

    public java.lang.String getDistrictShortName() {
        return districtShortName;
    }

    public void setDistrictShortName(java.lang.String districtShortName) {
        this.districtShortName = districtShortName;
    }

    public java.lang.String getDistrictNameTwo() {
        return districtNameTwo;
    }

    public void setDistrictNameTwo(java.lang.String districtNameTwo) {
        this.districtNameTwo = districtNameTwo;
    }

    public java.lang.String getCensus2011() {
        return census2011;
    }

    public void setCensus2011(java.lang.String census2011) {
        this.census2011 = census2011;
    }

    public java.lang.String getStateName() {
        return stateName;
    }

    public void setStateName(java.lang.String stateName) {
        this.stateName = stateName;
    }

    public DistrictDto() {
    }

    public DistrictDto(java.lang.String districtCode, java.lang.String districtName, Timestamp createdOn, java.lang.String createdBy, Timestamp modifiedOn, java.lang.String modifedBy, java.lang.String stateCode, java.lang.String fundReleaseFlag, java.lang.String districtShortName, java.lang.String districtNameTwo, java.lang.String census2011, java.lang.String stateName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifedBy = modifedBy;
        this.stateCode = stateCode;
        this.fundReleaseFlag = fundReleaseFlag;
        this.districtShortName = districtShortName;
        this.districtNameTwo = districtNameTwo;
        this.census2011 = census2011;
        this.stateName = stateName;
    }
}
