package com.nrlm.mclfmis.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "mst_district")
public class District {

    @Id
    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Column(name = "modifed_by")
    private String modifedBy;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "fundreleaseflag")
    private String fundReleaseFlag;

    @Column(name = "district_short_name")
    private String districtShortName;

    @Column(name = "district_name_ll")
    private String districtNameTwo;

    @Column(name = "census2011")
    private String census2011;

    @ManyToOne( fetch = FetchType.LAZY,targetEntity = State.class)
    @JoinColumn( name = "state_code", insertable = false, updatable = false )
    private State state;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
    private List<Block> blocks;

    public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifedBy() {
        return modifedBy;
    }

    public void setModifedBy(String modifedBy) {
        this.modifedBy = modifedBy;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getFundReleaseFlag() {
        return fundReleaseFlag;
    }

    public void setFundReleaseFlag(String fundReleaseFlag) {
        this.fundReleaseFlag = fundReleaseFlag;
    }

    public String getDistrictShortName() {
        return districtShortName;
    }

    public void setDistrictShortName(String districtShortName) {
        this.districtShortName = districtShortName;
    }

    public String getDistrictNameTwo() {
        return districtNameTwo;
    }

    public void setDistrictNameTwo(String districtNameTwo) {
        this.districtNameTwo = districtNameTwo;
    }

    public String getCensus2011() {
        return census2011;
    }

    public void setCensus2011(String census2011) {
        this.census2011 = census2011;
    }

    public District() {
    }

    public District(String districtCode, String districtName, Timestamp createdOn, String createdBy, Timestamp modifiedOn, String modifedBy, String stateCode, String fundReleaseFlag, String districtShortName, String districtNameTwo, String census2011, State state) {
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
        this.state = state;
    }
}
