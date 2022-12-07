package com.nrlm.mclfmis.customEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ElementCollection;
import javax.persistence.ColumnResult;

@Entity
@SqlResultSetMapping(name="JediResult", classes = {
	    @ConstructorResult(targetClass = TrainingCustomEntity.class, 
	    columns = {@ColumnResult(name="id",type = Long.class), @ColumnResult(name="state_code",type = String.class),@ColumnResult(name="state_name",type = String.class),@ColumnResult(name="block_code",type = String.class),
	    		@ColumnResult(name="block_name",type = String.class), @ColumnResult(name="district_code",type = String.class),@ColumnResult(name="district_name",type = String.class),@ColumnResult(name="clf_code",type = String.class),
	    		@ColumnResult(name="clf_name",type = String.class), @ColumnResult(name="training_conduct",type = Integer.class),@ColumnResult(name="clf_staff_type",type = String.class),@ColumnResult(name="training_module",type = String.class),
	    		@ColumnResult(name="participant_no",type = String.class), @ColumnResult(name="start_date",type = String.class),@ColumnResult(name="end_date",type = String.class),@ColumnResult(name="created_by",type = Long.class),
	    		@ColumnResult(name="updated_by",type = Long.class), @ColumnResult(name="created_at",type = Date.class),@ColumnResult(name="updated_at",type = Date.class),@ColumnResult(name="status",type = Integer.class),@ColumnResult(name="training_status",type = String.class)})
	})
public class TrainingCustomEntity {
	
	@Id
	private Long id;
	//@Column(name="state_code")
	private String stateCode;
	//@Column(name="state_name")
	private String stateName;
	//@Column(name="block_code")
	private String blockCode;
	//@Column(name="block_name")
	private String blockName;
	//@Column(name="district_code")
	private String districtCode;
	//@Column(name="district_name")
	private String districtName;
	//@Column(name="clf_code")
	private String clfCode ;
	//@Column(name="clf_name")
	private String clfName;
	//@Column(name="training_conduct")
	private Integer trainingConduct;
//	@ElementCollection
//	private List<Integer> clfStaffType= new ArrayList<Integer>();
//	@ElementCollection
//	private List<Integer> trainingModule= new ArrayList<Integer>();
//	@ElementCollection
//	private List<Integer> partIntrNo= new ArrayList<Integer>();
	//@ElementCollection
	//@OrderColumn
	//@Column(name="clf_staff_type")
	private String clfStaffType;
	//@ElementCollection
	//@OrderColumn
	//@Column(name="training_module")
	private String trainingModule;
	//@ElementCollection
	//@OrderColumn
	//@Column(name="participant_no")
	private String partIntrNo;
	//@Column(name="start_date")
	//@Temporal(TemporalType.DATE)
	private String startDate;
	//@Column(name="end_date")
	//@Temporal(TemporalType.DATE)
	private String endDate;	
	//@Column(name="created_by")
	private long createdBy;	
	//@Column(name="updated_by")
	private long updatedBy;	
	//@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;	
	//@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;	
	//@Column(name="status")
	private Integer status;
	//@Column(name="training_status")
	private String trainingStatus;
	
	
	
//	public TrainingCustomEntity(Long id, String stateCode, String stateName, String blockCode, String blockName,
//			String districtCode, String districtName, String clfCode, String clfName, Integer trainingConduct,
//			List<Integer> clfStaffType, List<Integer> trainingModule, List<Integer> partIntrNo, Date startDate,
//			Date endDate, long createdBy, long updatedBy, Date createdAt, Date updatedAt, Integer status,
//			String trainingStatus) {
//		super();
//		this.id = id;
//		this.stateCode = stateCode;
//		this.stateName = stateName;
//		this.blockCode = blockCode;
//		this.blockName = blockName;
//		this.districtCode = districtCode;
//		this.districtName = districtName;
//		this.clfCode = clfCode;
//		this.clfName = clfName;
//		this.trainingConduct = trainingConduct;
//		this.clfStaffType = clfStaffType;
//		this.trainingModule = trainingModule;
//		this.partIntrNo = partIntrNo;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.createdBy = createdBy;
//		this.updatedBy = updatedBy;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.status = status;
//		this.trainingStatus = trainingStatus;
//	}
	
	public TrainingCustomEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainingCustomEntity(Long id, String stateCode, String stateName, String blockCode, String blockName,
		String districtCode, String districtName, String clfCode, String clfName, Integer trainingConduct,
		String clfStaffType, String trainingModule, String partIntrNo, String startDate, String endDate,
		long createdBy, long updatedBy, Date createdAt, Date updatedAt, Integer status, String trainingStatus) {
	super();
	this.id = id;
	this.stateCode = stateCode;
	this.stateName = stateName;
	this.blockCode = blockCode;
	this.blockName = blockName;
	this.districtCode = districtCode;
	this.districtName = districtName;
	this.clfCode = clfCode;
	this.clfName = clfName;
	this.trainingConduct = trainingConduct;
	this.clfStaffType = clfStaffType;
	this.trainingModule = trainingModule;
	this.partIntrNo = partIntrNo;
	this.startDate = startDate;
	this.endDate = endDate;
	this.createdBy = createdBy;
	this.updatedBy = updatedBy;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.status = status;
	this.trainingStatus = trainingStatus;
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
	
	
	
	public String getClfStaffType() {
		return clfStaffType;
	}
	public void setClfStaffType(String clfStaffType) {
		this.clfStaffType = clfStaffType;
	}
	public String getTrainingModule() {
		return trainingModule;
	}
	public void setTrainingModule(String trainingModule) {
		this.trainingModule = trainingModule;
	}
	public String getPartIntrNo() {
		return partIntrNo;
	}
	public void setPartIntrNo(String partIntrNo) {
		this.partIntrNo = partIntrNo;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getClfName() {
		return clfName;
	}
	public void setClfName(String clfName) {
		this.clfName = clfName;
	}
	public String getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	@Override
	public String toString() {
		return "TrainingCustomEntity [id=" + id + ", stateCode=" + stateCode + ", stateName=" + stateName
				+ ", blockCode=" + blockCode + ", blockName=" + blockName + ", districtCode=" + districtCode
				+ ", districtName=" + districtName + ", clfCode=" + clfCode + ", clfName=" + clfName
				+ ", trainingConduct=" + trainingConduct + ", clfStaffType=" + clfStaffType + ", trainingModule="
				+ trainingModule + ", partIntrNo=" + partIntrNo + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", status=" + status + ", trainingStatus=" + trainingStatus + "]";
	}
	
	
	}
