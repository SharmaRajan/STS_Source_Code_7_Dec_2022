package com.nic.nrlm_aajeevika.response;

public class ContentFileMapperResponse {

    private Long id;
    private Integer contentId;
    private String fileUrl;
    private String fileSize;
    private String fileType;
    private Integer downloadCount;
    private Integer viewCount;


    public ContentFileMapperResponse() {
        super();
    }

    public ContentFileMapperResponse(Integer contentId, String fileUrl, String fileSize, String fileType) {
        this.contentId = contentId;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public ContentFileMapperResponse(Long id, Integer contentId, String fileUrl, String fileSize, String fileType, Integer downloadCount, Integer viewCount) {
        this.id = id;
        this.contentId = contentId;
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.downloadCount = downloadCount;
        this.viewCount = viewCount;
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

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
}
