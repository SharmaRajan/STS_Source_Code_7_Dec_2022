package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "mst_grampanchayat")
public class Grampanchayat {

    @Id
    @Column(name = "grampanchayat_code")
    private String grampanchayatCode;

    @Column(name = "rural_urban_area")
    private String ruralUrbanArea;

    @Column(name = "grampanchayat_name")
    private String grampanchayatName;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "block_code")
    private String blockCode;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @Column(name = "modifed_by")
    private String modifedBy;

    @Column(name = "grampanchayat_name_ll")
    private String grampanchayatNameTwo;
    
    @ManyToOne( fetch = FetchType.LAZY,targetEntity = Block.class)
    @JoinColumn( name = "block_code", insertable = false, updatable = false )
    private Block block;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gp")
    private List<VoProfile> voprofiles;

    
    @JsonBackReference
    @OneToOne(mappedBy = "gp")
	private GpCutOffEntity gpcutoff;
    
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gp")
	private List<GpCutOffMonthlyEntity> gpcutoffmonthly;
  
    
    public Grampanchayat() {
    }

    public Grampanchayat( String grampanchayatCode, String ruralUrbanArea, String grampanchayatName,
                         String stateCode, String districtCode, String blockCode, Timestamp createdOn, String createdBy,
                         Timestamp modifiedOn, String modifedBy, String grampanchayatNameTwo) {
        this.grampanchayatCode = grampanchayatCode;
        this.ruralUrbanArea = ruralUrbanArea;
        this.grampanchayatName = grampanchayatName;
        this.stateCode = stateCode;
        this.districtCode = districtCode;
        this.blockCode = blockCode;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifedBy = modifedBy;
        this.grampanchayatNameTwo = grampanchayatNameTwo;
    }
    

    public List<VoProfile> getVoprofiles() {
		return voprofiles;
	}

	public void setVoprofiles(List<VoProfile> voprofiles) {
		this.voprofiles = voprofiles;
	}

	public String getGrampanchayatCode() {
        return grampanchayatCode;
    }

    public void setGrampanchayatCode(String grampanchayatCode) {
        this.grampanchayatCode = grampanchayatCode;
    }

    public String getRuralUrbanArea() {
        return ruralUrbanArea;
    }

    public void setRuralUrbanArea(String ruralUrbanArea) {
        this.ruralUrbanArea = ruralUrbanArea;
    }

    public String getGrampanchayatName() {
        return grampanchayatName;
    }

    public void setGrampanchayatName(String grampanchayatName) {
        this.grampanchayatName = grampanchayatName;
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

    public String getGrampanchayatNameTwo() {
        return grampanchayatNameTwo;
    }

    public void setGrampanchayatNameTwo(String grampanchayatNameTwo) {
        this.grampanchayatNameTwo = grampanchayatNameTwo;
    }

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public GpCutOffEntity getGpcutoff() {
		return gpcutoff;
	}

	public void setGpcutoff(GpCutOffEntity gpcutoff) {
		this.gpcutoff = gpcutoff;
	}

	public List<GpCutOffMonthlyEntity> getGpcutoffmonthly() {
		return gpcutoffmonthly;
	}

	public void setGpcutoffmonthly(List<GpCutOffMonthlyEntity> gpcutoffmonthly) {
		this.gpcutoffmonthly = gpcutoffmonthly;
	}


}
