package com.nic.nrlm_aajeevika.response;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ContentListResponse {

    private long id;
    private Integer moduleId;
    private Integer subModuleId;
    private String title;
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

    private List<ContentFileMapperResponse> fileList;
    private String path;
    private String submodulename;

    public ContentListResponse(long id, Integer moduleId, Integer subModuleId, String title, String documentSource, String author, Timestamp uploadedOn, String publishedBy, Date publishedDate, Integer issuedNo, Date issuedDate, String issuedBy, Integer corrigendum, Integer dueDateExtended, Date autoExpiry, Date lastDateSubmission, String location, String link, String fileNumber, String source, Date vacancyUploadedDate, String rtiQuery, String description, Integer isUpdate, Integer isHighlight, Integer approvedRejectedStatus, Integer approvedRejectedBy, List<ContentFileMapperResponse> fileList, String submodulename , String path) {
        this.id = id;
        this.moduleId = moduleId;
        this.subModuleId = subModuleId;
        this.title = title;
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
        this.fileList = fileList;
        this.submodulename=submodulename;
        this.path=path;
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

    public List<ContentFileMapperResponse> getFileList() {
        return fileList;
    }

    public void setFileList(List<ContentFileMapperResponse> fileList) {
        this.fileList = fileList;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSubmodulename() {
		return submodulename;
	}

	public void setSubmodulename(String submodulename) {
		this.submodulename = submodulename;
	}
    
}
