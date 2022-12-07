package com.nic.nrlm_aajeevika.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer moduleId;
    private Integer subModuleId;
    private String title;
    private String fileUrl;
    private String documentSource;
    private String author;
    private Timestamp uploadedOn;
    private String publishedBy;
    private Date publishedDate;
    private Integer issuedNo;
    private Date issuedDate;
    private String issuedBy;
    private Integer corrigendum;
    private Integer dueDateExtended;
    private Date autoExpiry;
    private Date lastDateSubmission;
    private String location;
    private String link;
    private String fileNumber;
    private String source;
    private Date vacancyUploadedDate;
    private String rtiQuery;
    private String description;
    private Integer isUpdate;
    private Integer isHighlight;
    private Integer approvedRejectedStatus;
    private Integer approvedRejectedBy;
    private Integer viewCount;
    private String isDeleted;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private Date deletedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private Integer deletedBy;
    private Integer ranking;


    public Content(long id, Integer moduleId, Integer subModuleId, String title, String fileUrl, String documentSource, String author, Timestamp uploadedOn, String publishedBy, Date publishedDate, Integer issuedNo, Date issuedDate, String issuedBy, Integer corrigendum, Integer dueDateExtended, Date autoExpiry, Date lastDateSubmission, String location, String link, String fileNumber, String source, Date vacancyUploadedDate, String rtiQuery, String description, Integer isUpdate, Integer isHighlight, Integer approvedRejectedStatus, Integer approvedRejectedBy, String isDeleted, Date createdAt, Date updatedAt, Date deletedAt, Integer createdBy, Integer updatedBy, Integer deletedBy, Integer ranking) {
        this.id = id;
        this.moduleId = moduleId;
        this.subModuleId = subModuleId;
        this.title = title;
        this.fileUrl = fileUrl;
        this.documentSource = documentSource;
        this.author = author;
        this.uploadedOn = uploadedOn;
        this.publishedBy = publishedBy;
        this.publishedDate = publishedDate;
        this.issuedNo = issuedNo;
        this.issuedDate = issuedDate;
        this.issuedBy = issuedBy;
        this.corrigendum = corrigendum;
        this.dueDateExtended = dueDateExtended;
        this.autoExpiry = autoExpiry;
        this.lastDateSubmission = lastDateSubmission;
        this.location = location;
        this.link = link;
        this.fileNumber = fileNumber;
        this.source = source;
        this.vacancyUploadedDate = vacancyUploadedDate;
        this.rtiQuery = rtiQuery;
        this.description = description;
        this.isUpdate = isUpdate;
        this.isHighlight = isHighlight;
        this.approvedRejectedStatus = approvedRejectedStatus;
        this.approvedRejectedBy = approvedRejectedBy;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.deletedBy = deletedBy;
        this.ranking = ranking;
    }

    public Content() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getSubModuleId() {
        return subModuleId;
    }

    public void setSubModuleId(Integer subModuleId) {
        this.subModuleId = subModuleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDocumentSource() {
        return documentSource;
    }

    public void setDocumentSource(String documentSource) {
        this.documentSource = documentSource;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Timestamp uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Integer getIssuedNo() {
        return issuedNo;
    }

    public void setIssuedNo(Integer issuedNo) {
        this.issuedNo = issuedNo;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Integer getCorrigendum() {
        return corrigendum;
    }

    public void setCorrigendum(Integer corrigendum) {
        this.corrigendum = corrigendum;
    }

    public Integer getDueDateExtended() {
        return dueDateExtended;
    }

    public void setDueDateExtended(Integer dueDateExtended) {
        this.dueDateExtended = dueDateExtended;
    }

    public Date getAutoExpiry() {
        return autoExpiry;
    }

    public void setAutoExpiry(Date autoExpiry) {
        this.autoExpiry = autoExpiry;
    }

    public Date getLastDateSubmission() {
        return lastDateSubmission;
    }

    public void setLastDateSubmission(Date lastDateSubmission) {
        this.lastDateSubmission = lastDateSubmission;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getVacancyUploadedDate() {
        return vacancyUploadedDate;
    }

    public void setVacancyUploadedDate(Date vacancyUploadedDate) {
        this.vacancyUploadedDate = vacancyUploadedDate;
    }

    public String getRtiQuery() {
        return rtiQuery;
    }

    public void setRtiQuery(String rtiQuery) {
        this.rtiQuery = rtiQuery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getIsHighlight() {
        return isHighlight;
    }

    public void setIsHighlight(Integer isHighlight) {
        this.isHighlight = isHighlight;
    }

    public Integer getApprovedRejectedStatus() {
        return approvedRejectedStatus;
    }

    public void setApprovedRejectedStatus(Integer approvedRejectedStatus) {
        this.approvedRejectedStatus = approvedRejectedStatus;
    }

    public Integer getApprovedRejectedBy() {
        return approvedRejectedBy;
    }

    public void setApprovedRejectedBy(Integer approvedRejectedBy) {
        this.approvedRejectedBy = approvedRejectedBy;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
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

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }


}