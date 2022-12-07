package com.nrlm.mclfmis.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "mst_state")
public class State {

    @Id
    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "state_name")
    private String stateName;
    
    @Column(name = "state_short_name")
    private String stateShortName;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Column(name = "modifed_by")
    private String modifedBy;

    @Column(name = "center_share")
    private String centerShare;

    @Column(name = "state_share")
    private String stateShare;

    private String category;

    @Column(name = "state_name_ll")
    private String stateNameTwo;

    @Column(name = "census2011")
    private String census2011;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private List<District> districts;

    public State() {
    }

    public State(String stateName, String stateCode, String stateShortName, Timestamp createdOn,
                 String createdBy, Timestamp modifiedOn, String modifedBy, String centerShare, String stateShare,
                 String category, String stateNameTwo, String census2011) {
       this.stateName = stateName;
        this.stateCode = stateCode;
        this.stateShortName = stateShortName;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifedBy = modifedBy;
        this.centerShare = centerShare;
        this.stateShare = stateShare;
        this.category = category;
        this.stateNameTwo = stateNameTwo;
        this.census2011 = census2011;
    }


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateShortName() {
        return stateShortName;
    }

    public void setStateShortName(String stateShortName) {
        this.stateShortName = stateShortName;
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

    public String getCenterShare() {
        return centerShare;
    }

    public void setCenterShare(String centerShare) {
        this.centerShare = centerShare;
    }

    public String getStateShare() {
        return stateShare;
    }

    public void setStateShare(String stateShare) {
        this.stateShare = stateShare;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStateNameTwo() {
        return stateNameTwo;
    }

    public void setStateNameTwo(String stateNameTwo) {
        this.stateNameTwo = stateNameTwo;
    }

    public String getCensus2011() {
        return census2011;
    }

    public void setCensus2011(String census2011) {
        this.census2011 = census2011;
    }

    @Override
    public String toString() {
        return "State{" +
                " stateName='" + stateName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", stateShortName='" + stateShortName + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedOn=" + modifiedOn +
                ", modifedBy='" + modifedBy + '\'' +
                ", centerShare='" + centerShare + '\'' +
                ", stateShare='" + stateShare + '\'' +
                ", category='" + category + '\'' +
                ", stateNameTwo='" + stateNameTwo + '\'' +
                ", census2011='" + census2011 + '\'' +
                '}';
    }
}
