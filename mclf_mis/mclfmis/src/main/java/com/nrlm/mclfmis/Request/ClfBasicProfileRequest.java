package com.nrlm.mclfmis.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClfBasicProfileRequest {

	//@NotNull(message = "{clfbasic.clfcode.notNull}")
	//@NotEmpty(message = "{clfbasic.clfcode.notEmpty}")
	private String clfCode;
	//@NotNull(message = "{clfbasic.clfRecgn.notNull}")
	private Integer clfRecgn;
	private String recgnDate;
	//@NotNull(message = "{clfbasic.monthId.notNull}")
	private Integer monthId;
	//@NotNull(message = "{clfbasic.finYearId.notNull}")
	private Integer finYearId;
	//@NotNull(message = "{clfbasic.npName.notNull}")
	private String npName;
	//@NotNull(message = "{clfbasic.npDesgn.notNull}")
	private Integer npDesgn;
	private String otherDesgn;

	private Integer officeStatus;
	private Integer profileStatus;
	private String pan;
	private String tan;
	private String gstin;
	private Integer status;
	private Integer draft;

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public Integer getClfRecgn() {
		return clfRecgn;
	}

	public void setClfRecgn(Integer clfRecgn) {
		this.clfRecgn = clfRecgn;
	}

	public String getRecgnDate() {
		return recgnDate;
	}

	public void setRecgnDate(String recgnDate) {
		this.recgnDate = recgnDate;
	}

	public Integer getMonthId() {
		return monthId;
	}

	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public Integer getFinYearId() {
		return finYearId;
	}

	public void setFinYearId(Integer finYearId) {
		this.finYearId = finYearId;
	}

	public String getNpName() {
		return npName;
	}

	public void setNpName(String npName) {
		this.npName = npName;
	}

	public Integer getNpDesgn() {
		return npDesgn;
	}

	public void setNpDesgn(Integer npDesgn) {
		this.npDesgn = npDesgn;
	}

	public String getOtherDesgn() {
		return otherDesgn;
	}

	public void setOtherDesgn(String otherDesgn) {
		this.otherDesgn = otherDesgn;
	}

	public Integer getOfficeStatus() {
		return officeStatus;
	}

	public void setOfficeStatus(Integer officeStatus) {
		this.officeStatus = officeStatus;
	}

	public Integer getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(Integer profileStatus) {
		this.profileStatus = profileStatus;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTan() {
		return tan;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDraft() {
		return draft;
	}

	public void setDraft(Integer draft) {
		this.draft = draft;
	}

}
