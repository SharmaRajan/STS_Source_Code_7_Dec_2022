package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mclf_clf_cut_off_monthly_mapping")
public class ClfCutOffMonthlyMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="cut_off_id")
	private Long cutOffId;
	
	@Column(name="indctr_id")
	private Long indctrId;
	
	@Column(name="indctr_value")
	private String indctrValue;
	
	@Column(name="option_id")
	private Integer optionId;
	
	@Column(name="section_id")
	private Long sectionId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Indicator.class)
	@JoinColumn(name = "indctr_id", insertable = false, updatable = false)
	private Indicator indicator;
	
	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
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

	public Long getIndctrId() {
		return indctrId;
	}

	public void setIndctrId(Long indctrId) {
		this.indctrId = indctrId;
	}

	public String getIndctrValue() {
		return indctrValue;
	}

	public void setIndctrValue(String indctrValue) {
		this.indctrValue = indctrValue;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	
	
}
