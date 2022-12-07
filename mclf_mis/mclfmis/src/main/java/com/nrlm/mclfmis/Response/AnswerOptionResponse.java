package com.nrlm.mclfmis.Response;

public class AnswerOptionResponse {

	private Integer optionId;
	private String optionName;
	private String isDisable;
	private Integer status;
	private Integer seq;
	
	public AnswerOptionResponse() {
		super();
	}
	
	public AnswerOptionResponse(Integer optionId, String optionName) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
	}
	public AnswerOptionResponse(Integer optionId, String optionName,Integer status) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.setStatus(status);
	}
	public AnswerOptionResponse(Integer optionId, String optionName,Integer status,Integer seq) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.setStatus(status);
		this.seq  = seq;
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}


}
