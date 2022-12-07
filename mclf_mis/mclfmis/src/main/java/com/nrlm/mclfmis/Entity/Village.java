package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_village")
public class Village {

    @Id
    private Integer id;
    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "block_code")
    private String blockCode;

    @Column(name = "grampanchayat_code")
    private String grampanchayatCode;

    @Column(name = "village_code")
    private String villageCode;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Column(name = "modifed_by")
    private String modifiedBy;

    @Column(name = "rural_urban_area")
    private String ruralUrbanArea;

    @Column(name = "village_name_ll")
    private String villageNameTwo;

    @Column(name = "census2011")
    private String census2011;

    @Column(name = "village_name_hindi")
    private String villageNameHindi;

    @Column(name = "gp_id")
    private Integer gpId;

    public Village() {
    }

    public Village(Integer id, String stateCode, String districtCode, String blockCode, String grampanchayatCode,
                   String villageCode, String villageName, Timestamp createdOn, String createdBy, Timestamp modifiedOn,
                   String modifiedBy, String ruralUrbanArea, String villageNameTwo, String census2011, String villageNameHindi,
                   Integer gpId) {
        this.id = id;
        this.stateCode = stateCode;
        this.districtCode = districtCode;
        this.blockCode = blockCode;
        this.grampanchayatCode = grampanchayatCode;
        this.villageCode = villageCode;
        this.villageName = villageName;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.ruralUrbanArea = ruralUrbanArea;
        this.villageNameTwo = villageNameTwo;
        this.census2011 = census2011;
        this.villageNameHindi = villageNameHindi;
        this.gpId = gpId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getGrampanchayatCode() {
        return grampanchayatCode;
    }

    public void setGrampanchayatCode(String grampanchayatCode) {
        this.grampanchayatCode = grampanchayatCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getRuralUrbanArea() {
        return ruralUrbanArea;
    }

    public void setRuralUrbanArea(String ruralUrbanArea) {
        this.ruralUrbanArea = ruralUrbanArea;
    }

    public String getVillageNameTwo() {
        return villageNameTwo;
    }

    public void setVillageNameTwo(String villageNameTwo) {
        this.villageNameTwo = villageNameTwo;
    }

    public String getCensus2011() {
        return census2011;
    }

    public void setCensus2011(String census2011) {
        this.census2011 = census2011;
    }

    public String getVillageNameHindi() {
        return villageNameHindi;
    }

    public void setVillageNameHindi(String villageNameHindi) {
        this.villageNameHindi = villageNameHindi;
    }

    public Integer getGpId() {
        return gpId;
    }

    public void setGpId(Integer gpId) {
        this.gpId = gpId;
    }

    @Override
    public String toString() {
        return "Village{" +
                "id=" + id +
                ", stateCode='" + stateCode + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", blockCode='" + blockCode + '\'' +
                ", grampanchayatCode='" + grampanchayatCode + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", villageName='" + villageName + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedOn=" + modifiedOn +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", ruralUrbanArea='" + ruralUrbanArea + '\'' +
                ", villageNameTwo='" + villageNameTwo + '\'' +
                ", census2011='" + census2011 + '\'' +
                ", villageNameHindi='" + villageNameHindi + '\'' +
                ", gpId=" + gpId +
                '}';
    }
}
