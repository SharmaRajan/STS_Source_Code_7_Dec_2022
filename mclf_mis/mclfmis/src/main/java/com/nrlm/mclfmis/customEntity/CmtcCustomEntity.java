package com.nrlm.mclfmis.customEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CmtcCustomEntity {
	
		@Id
	    private Long id;
	    private String stateCode;
		private String stateName;
		private String blockCode;
		private String blockName;
		private String districtCode;
		private String districtName;
		private String clfName;
		private String clfCode;
		private Integer cmtcEst;
		private Date cmtcDate;
		private Integer cmtcFunc;
		private Integer status;
		private Long createdBy;
		private Long updatedBy;
		private Date createdAt;
		private Date updatedAt;
		private String cmtcStatus;
		
		public CmtcCustomEntity(Long id, String stateCode, String stateName, String blockCode, String blockName,
				String districtCode, String districtName, String clfCode, String clfName, Integer cmtcEst, Date cmtcDate,
				Integer cmtcFunc, Integer status, Long createdBy, Long updatedBy, Date createdAt, Date updatedAt,String cmtcStatus) {
			super();
			this.id = id;
			this.stateCode = stateCode;
			this.stateName = stateName;
			this.blockCode = blockCode;
			this.blockName = blockName;
			this.districtCode = districtCode;
			this.districtName = districtName;
			this.clfName=clfName;
			this.clfCode = clfCode;
			this.cmtcEst = cmtcEst;
			this.cmtcDate = cmtcDate;
			this.cmtcFunc = cmtcFunc;
			this.status = status;
			this.createdBy = createdBy;
			this.updatedBy = updatedBy;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.cmtcStatus = cmtcStatus;
		}
		public CmtcCustomEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
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
		public String getClfName() {
			return clfName;
		}
		public void setClfName(String clfName) {
			this.clfName = clfName;
		}
		public String getClfCode() {
			return clfCode;
		}
		public void setClfCode(String clfCode) {
			this.clfCode = clfCode;
		}
		public Integer getCmtcEst() {
			return cmtcEst;
		}
		public void setCmtcEst(Integer cmtcEst) {
			this.cmtcEst = cmtcEst;
		}
		public Date getCmtcDate() {
			return cmtcDate;
		}
		public void setCmtcDate(Date cmtcDate) {
			this.cmtcDate = cmtcDate;
		}
		public Integer getCmtcFunc() {
			return cmtcFunc;
		}
		public void setCmtcFunc(Integer cmtcFunc) {
			this.cmtcFunc = cmtcFunc;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
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
		public String getCmtcStatus() {
			return cmtcStatus;
		}
		public void setCmtcStatus(String cmtcStatus) {
			this.cmtcStatus = cmtcStatus;
		}
}
