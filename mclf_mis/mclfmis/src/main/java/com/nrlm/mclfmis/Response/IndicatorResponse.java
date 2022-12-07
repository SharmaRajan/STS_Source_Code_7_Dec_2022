package com.nrlm.mclfmis.Response;

import java.util.List;

public class IndicatorResponse {

    private Integer indctrId;
    private String indctrName;
    private String description;
    private Integer mandatory;
    private Integer indctrType;
    private String minValue;
    private String maxValue;
    private String indctrValue;
    private List<Integer> multiselectOptionValue;
    private List<AnswerOptionResponse> optionList;

    public IndicatorResponse(Integer indctrId, String indctrName, String description, Integer mandatory, Integer indctrType, String minValue, String maxValue, String indctrValue, List<Integer> multiselectOptionValue, List<AnswerOptionResponse> optionList) {
        this.indctrId = indctrId;
        this.indctrName = indctrName;
        this.description = description;
        this.mandatory = mandatory;
        this.indctrType = indctrType;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.indctrValue = indctrValue;
        this.multiselectOptionValue = multiselectOptionValue;
        this.optionList = optionList;
    }

    public List<Integer> getMultiselectOptionValue() {
        return multiselectOptionValue;
    }

    public void setMultiselectOptionValue(List<Integer> multiselectOptionValue) {
        this.multiselectOptionValue = multiselectOptionValue;
    }

    public Integer getIndctrId() {
        return indctrId;
    }

    public void setIndctrId(Integer indctrId) {
        this.indctrId = indctrId;
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

    public List<AnswerOptionResponse> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<AnswerOptionResponse> optionList) {
        this.optionList = optionList;
    }

    public String getIndctrValue() {
        return indctrValue;
    }

    public void setIndctrValue(String indctrValue) {
        this.indctrValue = indctrValue;
    }


}
