package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

//@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class  CutOffSkipMapping {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="cut_off_id")
	private Long cutOffId;
	
	/*
	@Column(name="clfCode")
	private String clfCode;*/
	
	@Column(name="section_id")
	private Long sectionId;
	
	@Column(name="skip_value")
	private Integer skipValue;
	
	@Column(name="section_status")
	private Integer sectionStatus;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCutOffId() {
		return cutOffId;
	}

	public void setCutOffId(Long cutOffId) {
		this.cutOffId = cutOffId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getSkipValue() {
		return skipValue;
	}

	public void setSkipValue(Integer skipValue) {
		this.skipValue = skipValue;
	}
/*
	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}*/

	public Integer getSectionStatus() {
		return sectionStatus;
	}

	public void setSectionStatus(Integer sectionStatus) {
		this.sectionStatus = sectionStatus;
	}
	
	
}
