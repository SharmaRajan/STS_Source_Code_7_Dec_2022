package com.nrlm.mclfmis.Response;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FinancialYearResponse {

	private Integer id;
	
	private String fnclYear;
	
	private Date fnclStartDate;
	
	private Date fnclEndDate;
	
	private String formName;
	
	

	public FinancialYearResponse() {
		super();
	}

	public FinancialYearResponse(Integer id, String fnclYear, Date fnclStartDate, Date fnclEndDate, String formName) {
		super();
		this.id = id;
		this.fnclYear = fnclYear;
		this.fnclStartDate = fnclStartDate;
		this.fnclEndDate = fnclEndDate;
		this.formName = formName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFnclYear() {
		return fnclYear;
	}

	public void setFnclYear(String fnclYear) {
		this.fnclYear = fnclYear;
	}

	public Date getFnclStartDate() {
		return fnclStartDate;
	}

	public void setFnclStartDate(Date fnclStartDate) {
		this.fnclStartDate = fnclStartDate;
	}

	public Date getFnclEndDate() {
		return fnclEndDate;
	}

	public void setFnclEndDate(Date fnclEndDate) {
		this.fnclEndDate = fnclEndDate;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	
}
