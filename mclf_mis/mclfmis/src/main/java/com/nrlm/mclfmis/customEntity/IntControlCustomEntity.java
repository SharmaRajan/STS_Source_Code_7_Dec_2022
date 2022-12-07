package com.nrlm.mclfmis.customEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class IntControlCustomEntity {

	@Id
	private Long id;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private String clfCode;
	private String clfName;
	private Integer finYearId;
	private Integer qrtrId;
	private String finYear;
	private String qrtr;
	private Integer intAudit;
	private Integer grb;
	private Integer gbMeeting;
	private String complianceStatus;
	private Integer status;
	private Date createdAt;
	private Date updatedAt;
	private Long createdBy;
	private Long updatedBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getClfCode() {
		return clfCode;
	}
	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public Integer getFinYearId() {
		return finYearId;
	}
	public void setFinYearId(Integer finYearId) {
		this.finYearId = finYearId;
	}
	public Integer getQrtrId() {
		return qrtrId;
	}
	public void setQrtrId(Integer qrtrId) {
		this.qrtrId = qrtrId;
	}
	public Integer getIntAudit() {
		return intAudit;
	}
	public void setIntAudit(Integer intAudit) {
		this.intAudit = intAudit;
	}
	public Integer getGrb() {
		return grb;
	}
	public void setGrb(Integer grb) {
		this.grb = grb;
	}
	public Integer getGbMeeting() {
		return gbMeeting;
	}
	public void setGbMeeting(Integer gbMeeting) {
		this.gbMeeting = gbMeeting;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public IntControlCustomEntity(Long id, String stateCode, String stateName, String districtCode, String districtName,
			String blockCode, String blockName, String clfCode, String clfName, Integer finYearId, Integer qrtrId,
			Integer intAudit, Integer grb, Integer gbMeeting,Integer status, Date createdAt,
			Date updatedAt, Long createdBy, Long updatedBy,String complianceStatus,String finYear, String qrtr) {
		super();
		this.id = id;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.finYearId = finYearId;
		this.qrtrId = qrtrId;
		this.intAudit = intAudit;
		this.grb = grb;
		this.gbMeeting = gbMeeting;
		this.complianceStatus = complianceStatus;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.finYear = finYear;
		this.qrtr = qrtr;
	}
	public IntControlCustomEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "IntControlCustomEntity [id=" + id + ", stateCode=" + stateCode + ", stateName=" + stateName
				+ ", districtCode=" + districtCode + ", districtName=" + districtName + ", blockCode=" + blockCode
				+ ", blockName=" + blockName + ", clfCode=" + clfCode + ", clfName=" + clfName + ", finYearId="
				+ finYearId + ", qrtrId=" + qrtrId + ", intAudit=" + intAudit + ", grb=" + grb + ", gbMeeting="
				+ gbMeeting + ", complianceStatus=" + complianceStatus + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}
	public String getComplianceStatus() {
		return complianceStatus;
	}
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public String getQrtr() {
		return qrtr;
	}
	public void setQrtr(String qrtr) {
		this.qrtr = qrtr;
	}
	
	
	
}
