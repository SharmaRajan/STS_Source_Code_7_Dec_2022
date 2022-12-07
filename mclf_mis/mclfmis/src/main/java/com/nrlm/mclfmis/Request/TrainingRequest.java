package com.nrlm.mclfmis.Request;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TrainingRequest {

	private Long id;	
	private String clfCode ;		
	private Integer trainingConduct;	
	private List<Integer> clfStaffType;	
//	private List<Integer> trainingModule;	
//	private Integer participantNo;
	private Date startDate;
	private Date endDate;
	private Integer isDraft;
	private List<HashMap<String,Integer>> modulePart;
	
	
	
	public TrainingRequest(Long id, String clfCode, Integer trainingConduct, List<Integer> clfStaffType, Date startDate,
			Date endDate, Integer isDraft, List<HashMap<String, Integer>> modulePart) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.trainingConduct = trainingConduct;
		this.clfStaffType = clfStaffType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isDraft = isDraft;
		this.modulePart = modulePart;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public Integer getTrainingConduct() {
		return trainingConduct;
	}
	public void setTrainingConduct(Integer trainingConduct) {
		this.trainingConduct = trainingConduct;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public TrainingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	public List<HashMap<String,Integer>> getModulePart() {
		return modulePart;
	}
	public void setModulePart(List<HashMap<String,Integer>> modulePart) {
		this.modulePart = modulePart;
	}
	public List<Integer> getClfStaffType() {
		return clfStaffType;
	}
	public void setClfStaffType(List<Integer> clfStaffType) {
		this.clfStaffType = clfStaffType;
	}
	
	

}
