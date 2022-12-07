package com.nic.nrlm_aajeevika.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "visitors")
public class Visitors {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	private long visitorsCount;

private Integer createdBy;
private Integer updatedBy;
private Integer deletedBy;

@CreationTimestamp
@Column(name = "created_at", nullable = false, updatable = true)
@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

@UpdateTimestamp
@Column(name = "updated_at", nullable = false, updatable = true)
@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

@Column(name = "deleted_at", nullable = false, updatable = true)
@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public long getVisitorsCount() {
	return visitorsCount;
}

public void setVisitorsCount(long visitorsCount) {
	this.visitorsCount = visitorsCount;
}

public Integer getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
}

public Integer getUpdatedBy() {
	return updatedBy;
}

public void setUpdatedBy(Integer updatedBy) {
	this.updatedBy = updatedBy;
}

public Integer getDeletedBy() {
	return deletedBy;
}

public void setDeletedBy(Integer deletedBy) {
	this.deletedBy = deletedBy;
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

public Date getDeletedAt() {
	return deletedAt;
}

public void setDeletedAt(Date deletedAt) {
	this.deletedAt = deletedAt;
}





}
