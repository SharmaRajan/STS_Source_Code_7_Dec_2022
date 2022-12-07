package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mclf_gp_cut_off_skip_mapping")
public class GpCutOffSkipMapping extends CutOffSkipMapping {/*

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="cut_off_id")
	private Long cutOffId;
	
	@Column(name="gpCode")
	private String gpCode;
	
	@Column(name="section_id")
	private Long sectionId;
	
	@Column(name="skip_value")
	private Integer skipValue;

	public GpCutOffSkipMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GpCutOffSkipMapping(Long id, Long cutOffId,String gpCode, Long sectionId, Integer skipValue) {
		super();
		this.id = id;
		this.cutOffId = cutOffId;
		this.sectionId = sectionId;
		this.skipValue = skipValue;
		this.gpCode = gpCode;
	}

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

	public String getGpCode() {
		return gpCode;
	}

	public void setGpCode(String gpCode) {
		this.gpCode = gpCode;
	}
	
	
*/}
