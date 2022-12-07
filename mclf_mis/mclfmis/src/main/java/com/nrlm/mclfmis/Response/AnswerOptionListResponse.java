package com.nrlm.mclfmis.Response;

public class AnswerOptionListResponse {

	private Integer optionId;
	private String optionValue;
	private String tableName;
	private String shortKey;
	public AnswerOptionListResponse() {
		super();
	}
	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getShortKey() {
		return shortKey;
	}
	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}
	public AnswerOptionListResponse(Integer optionId, String optionValue, String tableName, String shortKey) {
		super();
		this.optionId = optionId;
		this.optionValue = optionValue;
		this.tableName = tableName;
		this.shortKey = shortKey;
	}
}
