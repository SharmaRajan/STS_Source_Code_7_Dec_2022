package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mclf_vo_cut_off_skip_mapping")
public class VoCutOffSkipMapping extends CutOffSkipMapping {/*

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="cut_off_id")
	private Long cutOffId;
	
	//@Column(name="voCode")
	private String voCode;
	
	@Column(name="section_id")
	private Long sectionId;
	
	@Column(name="skip_value")
	private Integer skipValue;

	public VoCutOffSkipMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoCutOffSkipMapping(Long id, Long cutOffId,String voCode, Long sectionId, Integer skipValue) {
		super();
		this.id = id;
		this.cutOffId = cutOffId;
		this.voCode = voCode;
		this.sectionId = sectionId;
		this.skipValue = skipValue;
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

	public String getVoCode() {
		return voCode;
	}

	public void setVoCode(String voCode) {
		this.voCode = voCode;
	}
	
	
*/}
