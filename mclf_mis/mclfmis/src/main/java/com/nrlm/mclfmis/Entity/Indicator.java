package com.nrlm.mclfmis.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nrlm.mclfmis.validation.CompositeUnique;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mclf_mst_indicator", uniqueConstraints = @UniqueConstraint(columnNames = { "section_id",
		"indctr_name" }))
public class Indicator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "section_id")
	private Long sectionId;

	@Column(name = "indctr_name")
	private String indctrName;
	
	
	private String description;

	private Integer sequence;

	private Integer mandatory;

	private Integer indctrType;
	private String minValue;
	private String maxValue;
	private Integer startMonth;
	private Integer startFnclYear;

	private Integer endMonth;
	private Integer endFnclYear;

	private Integer freezeFlag;
	private Integer status;
	private Long createdBy;
	private Long updatedBy;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = SectionEntity.class)
	@JoinColumn(name = "section_id", insertable = false, updatable = false)
//	@JsonManagedReference
	private SectionEntity sectionEntity;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private List<VoCutOffMonthlyMapping> voMonthMapping;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private List<GpCutOffMonthlyMapping> gpMonthMapping;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private List<ClfCutOffMonthlyMapping> clfMonthMapping;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private List<VoCutOffMapping> voMapping;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private List<GpCutOffMapping> gpMapping;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private List<ClfCutOffMapping> clfMapping;

	public SectionEntity getSectionEntity() {
		return sectionEntity;
	}

	public void setSectionEntity(SectionEntity sectionEntity) {
		this.sectionEntity = sectionEntity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public Integer getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}

	public Integer getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}

	public Integer getFreezeFlag() {
		return freezeFlag;
	}

	public void setFreezeFlag(Integer freezeFlag) {
		this.freezeFlag = freezeFlag;
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

	public Integer getStartFnclYear() {
		return startFnclYear;
	}

	public void setStartFnclYear(Integer startFnclYear) {
		this.startFnclYear = startFnclYear;
	}

	public Integer getEndFnclYear() {
		return endFnclYear;
	}

	public void setEndFnclYear(Integer endFnclYear) {
		this.endFnclYear = endFnclYear;
	}

	public Indicator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Indicator(Integer id,  Long sectionId, String indctrName, String description,
			Integer sequence, Integer mandatory,  Integer indctrType, String minValue,
			String maxValue,  Integer startMonth,  Integer startFnclYear, Integer endMonth,
			Integer endFnclYear, Integer freezeFlag, Integer status, Long createdBy, Long updatedBy,
			Date createdAt, Date updatedAt, SectionEntity sectionEntity) {
		super();
		this.id = id;
		this.sectionId = sectionId;
		this.indctrName = indctrName;
		this.description = description;
		this.sequence = sequence;
		this.mandatory = mandatory;
		this.indctrType = indctrType;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.startMonth = startMonth;
		this.startFnclYear = startFnclYear;
		this.endMonth = endMonth;
		this.endFnclYear = endFnclYear;
		this.freezeFlag = freezeFlag;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.sectionEntity = sectionEntity;
	}

//	@JsonIgnore
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="clfCode", referencedColumnName="clf_code",insertable = false, updatable = false)
//	private ClfMaster clfmaster;
//	
//	
//
//	public ClfMaster getClfmaster() {
//		return clfmaster;
//	}
//
//	public void setClfmaster(ClfMaster clfmaster) {
//		this.clfmaster = clfmaster;
//	}

}
