package com.nrlm.mclfmis.Response;

public class MonthResponse {

	private Integer id;
	private String monthName;
	
	public MonthResponse() {
		super();
	}

	public MonthResponse(Integer id, String monthName) {
		super();
		this.id = id;
		this.monthName = monthName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	
	
	
}
