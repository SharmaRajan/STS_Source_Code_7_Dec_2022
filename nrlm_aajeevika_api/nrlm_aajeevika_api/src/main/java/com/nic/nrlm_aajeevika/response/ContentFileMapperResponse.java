package com.nic.nrlm_aajeevika.response;

public class ContentFileMapperResponse {

    private Long id;
    private Integer contentId;
    private String fileUrl;
    private String fileSize;
    private String fileType;

    public ContentFileMapperResponse() {
        super();
    }

    public ContentFileMapperResponse(Integer contentId, String fileUrl, String fileSize, String fileType) {
        this.contentId = contentId;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public ContentFileMapperResponse(Long id, Integer contentId, String fileUrl, String fileSize, String fileType) {
        this.id = id;
        this.contentId = contentId;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
