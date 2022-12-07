package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "mst_block")
public class Block {

    @Id
    @Column(name = "block_code")
    private String blockCode;
    
    @Column(name = "rural_urban_area")
    private String ruralUrbanArea;

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Column(name = "modifed_by")
    private String modifiedBy;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "district_code")
    private String districtCode;

    private String intensive;

    @Column(name = "block_name_ll")
    private String blockNameTwo;

    @Column(name = "census2011")
    private String census2011;
    
    @ManyToOne( fetch = FetchType.LAZY,targetEntity = District.class)
    @JoinColumn( name = "district_code", insertable = false, updatable = false )
    private District district;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "block")
    private List<Grampanchayat> gps;
    
    
    public List<Grampanchayat> getGps() {
		return gps;
	}

	public void setGps(List<Grampanchayat> gps) {
		this.gps = gps;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Block() {
    }

    public Block(String blockCode, String ruralUrbanArea, String blockName, Timestamp createdOn, String createdBy, Timestamp modifiedOn, String modifiedBy, String stateCode, String districtCode, String intensive, String blockNameTwo, String census2011) {
        this.blockCode = blockCode;
        this.ruralUrbanArea = ruralUrbanArea;
        this.blockName = blockName;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.stateCode = stateCode;
        this.districtCode = districtCode;
        this.intensive = intensive;
        this.blockNameTwo = blockNameTwo;
        this.census2011 = census2011;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public String getRuralUrbanArea() {
        return ruralUrbanArea;
    }

    public void setRuralUrbanArea(String ruralUrbanArea) {
        this.ruralUrbanArea = ruralUrbanArea;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
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

    public String getIntensive() {
        return intensive;
    }

    public void setIntensive(String intensive) {
        this.intensive = intensive;
    }

    public String getBlockNameTwo() {
        return blockNameTwo;
    }

    public void setBlockNameTwo(String blockNameTwo) {
        this.blockNameTwo = blockNameTwo;
    }

    public String getCensus2011() {
        return census2011;
    }

    public void setCensus2011(String census2011) {
        this.census2011 = census2011;
    }
}
