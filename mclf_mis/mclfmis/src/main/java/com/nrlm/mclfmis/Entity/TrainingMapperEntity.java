package com.nrlm.mclfmis.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="mclf_clf_training_details_mapper")
public class TrainingMapperEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="training_id")
	private Long trainId;
	
	@Column(name="training_module")
	private Integer trainingModule;
	
	@Column(name="clf_staff_type")
	private Integer clfStaffType;
	
	@Column(name="participant_no")
	private Integer participantNo;
	
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="training_id",insertable = false, updatable = false)
	private TrainingEntity trainEnt;
	
	
	

	public TrainingMapperEntity(Integer trainingModule, Integer clfStaffType, Integer participantNo) {
		super();
		this.trainingModule = trainingModule;
		this.clfStaffType = clfStaffType;
		this.participantNo = participantNo;
	}
	
	public TrainingMapperEntity() {}

//	public TrainingEntity getTrainEnt() {
//		return trainEnt;
//	}
//
//	public void setTrainEnt(TrainingEntity trainEnt) {
//		this.trainEnt = trainEnt;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrainId() {
		return trainId;
	}

	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}

	

	public Integer getTrainingModule() {
		return trainingModule;
	}

	public void setTrainingModule(Integer trainingModule) {
		this.trainingModule = trainingModule;
	}

	public Integer getClfStaffType() {
		return clfStaffType;
	}

	public void setClfStaffType(Integer clfStaffType) {
		this.clfStaffType = clfStaffType;
	}

	public Integer getParticipantNo() {
		return participantNo;
	}

	public void setParticipantNo(Integer participantNo) {
		this.participantNo = participantNo;
	}

	

	
	
	
	
}
