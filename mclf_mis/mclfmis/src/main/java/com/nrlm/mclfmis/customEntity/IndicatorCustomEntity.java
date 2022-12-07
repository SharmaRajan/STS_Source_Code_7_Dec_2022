package com.nrlm.mclfmis.customEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class IndicatorCustomEntity {

	@Id
	private Integer id;
	private String indctrName;
	private String description;
	private Integer mandatory;
	private Integer indctrType;
	private String minValue;
	private String maxValue;

	private String indctrValue;

//	@Column(columnDefinition = "Integer[]")
	private String optionId;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndctrName() {
		return indctrName;
	}

	public void setIndctrName(String indctrName) {
		this.indctrName = indctrName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMandatory() {
		return mandatory;
	}

	public void setMandatory(Integer mandatory) {
		this.mandatory = mandatory;
	}

	public Integer getIndctrType() {
		return indctrType;
	}

	public void setIndctrType(Integer indctrType) {
		this.indctrType = indctrType;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getIndctrValue() {
		return indctrValue;
	}

	public void setIndctrValue(String indctrValue) {
		this.indctrValue = indctrValue;
	}

//	public Integer[] getOptionId() {
//		return optionId;
//	}
//
//	public void setOptionId(Integer[] optionId) {
//		this.optionId = optionId;
//	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public IndicatorCustomEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public IndicatorCustomEntity(Integer id, String indctrName, String description, Integer mandatory, Integer indctrType, String minValue, String maxValue, String indctrValue, Integer[] optionId) {
//		this.id = id;
//		this.indctrName = indctrName;
//		this.description = description;
//		this.mandatory = mandatory;
//		this.indctrType = indctrType;
//		this.minValue = minValue;
//		this.maxValue = maxValue;
//		this.indctrValue = indctrValue;
//		this.optionId = optionId;
//	}

	public IndicatorCustomEntity(Integer id, String indctrName, String description, Integer mandatory, Integer indctrType, String minValue, String maxValue, String indctrValue, String optionId) {
		this.id = id;
		this.indctrName = indctrName;
		this.description = description;
		this.mandatory = mandatory;
		this.indctrType = indctrType;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.indctrValue = indctrValue;
		this.optionId = optionId;
	}

//	public IndicatorCustomEntity(Integer id, String indctrName, String description, Integer mandatory, Integer indctrType, String minValue, String maxValue, String indctrValue) {
//		this.id = id;
//		this.indctrName = indctrName;
//		this.description = description;
//		this.mandatory = mandatory;
//		this.indctrType = indctrType;
//		this.minValue = minValue;
//		this.maxValue = maxValue;
//		this.indctrValue = indctrValue;
//		System.out.println("Inside Constructor = "+indctrValue);
//	}

}
