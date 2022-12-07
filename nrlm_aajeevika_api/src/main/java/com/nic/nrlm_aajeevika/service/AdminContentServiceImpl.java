package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.entity.ContentFileMapper;
import com.nic.nrlm_aajeevika.entity.SubModule;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.helper.PageModel;
import com.nic.nrlm_aajeevika.helper.ProjectConstants;
import com.nic.nrlm_aajeevika.repository.ContentFileMapperRepository;
import com.nic.nrlm_aajeevika.repository.ContentRepository;
import com.nic.nrlm_aajeevika.repository.SubModuleRepository;
import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.response.ContentFileMapperResponse;
import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.specification.AdminContentSpecification;
import com.nic.nrlm_aajeevika.usermanagement.common.MyConstant;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminContentServiceImpl implements AdminContentService {

    @Autowired
    private ContentRepository contentRepo;

    @Autowired
    private ContentFileMapperRepository contentFileMapperRepo;

    @Autowired
    private SubModuleRepository submoduleRepo;

    @Autowired
    private AdminContentSpecification admincontentSpeci;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageModel pageModel;

//    @Value("${image.upload.dir}") String uploadDir,


    @Override
    public Response<ContentListResponse> getAdminContentList(HttpServletRequest request) {

        Response<ContentListResponse> response = new Response<ContentListResponse>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Content> contentList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;

        Page<Content> conetentpageList = contentRepo.findAll(admincontentSpeci.getAdminContents(request), PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
        response.setTotalcount(conetentpageList.getTotalElements());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        List<ContentListResponse> contentResponse = conetentpageList.getContent().stream().map(condto -> {
            List<ContentFileMapperResponse> fileMapper = new ArrayList<ContentFileMapperResponse>();
            List<ContentFileMapper> fileList = contentFileMapperRepo.findAllByContentIdAndIsDeleted(Math.toIntExact(condto.getId()), "1");
            if (fileList != null && fileList.size() != 0) {
                fileMapper = fileList.stream().map(file -> new ContentFileMapperResponse(file.getId(), file.getContentId(), ProjectConstants.FILE_URL_PREFIX + file.getFileUrl(), file.getFileSize(), file.getFileType(), file.getDownloadCount(), file.getViewCount())).collect(Collectors.toList());
            }
            
            Optional<SubModule> dataofCo=   submoduleRepo.findById(Long.valueOf(condto.getSubModuleId()) );
            SubModule _submodule = dataofCo.get();
            
            String submodule_name = _submodule.getSubModuleName();
            
            String path = _submodule.getPath();
            
            
            if(path.isBlank() || path.isEmpty() || path==null) {
            	path=ProjectConstants.BASE_PATH+condto.getId();
            }
            return new ContentListResponse(condto.getId(), condto.getModuleId(), condto.getSubModuleId(), condto.getTitle(), condto.getDocumentSource(), condto.getAuthor(), condto.getUploadedOn(), condto.getPublishedBy(), condto.getPublishedDate(), condto.getIssuedNo(), condto.getIssuedDate(), condto.getIssuedBy(), condto.getCorrigendum(), condto.getDueDateExtended(), condto.getAutoExpiry(), condto.getLastDateSubmission(), condto.getLocation(), condto.getLink(), condto.getFileNumber(), condto.getSource(), condto.getVacancyUploadedDate(), condto.getRtiQuery(), condto.getDescription(), condto.getIsUpdate(), condto.getIsHighlight(), condto.getApprovedRejectedStatus(), condto.getApprovedRejectedBy(), fileMapper,submodule_name,path);
        }).collect(Collectors.toList());

        response.setWrapperList(contentResponse);
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

    @Override
    @Transactional
    public Response<String> saveContent(Integer userId, ContentFormRequest req, MultipartFile[] files) {
        Response<String> response = new Response<String>();
        try {
            Optional<Content> optionalContent = contentRepo.findOneByTitle(req.getTitle());
            if (!optionalContent.isPresent()) {
                if (req.getModuleId() == null) {
                    throw new HandledException(HttpStatus.BAD_REQUEST, "No Module Id Found");
                }
//                if (req.getSubModuleId() == null) {
//                    throw new HandledException(HttpStatus.BAD_REQUEST, "No Sub Module Id Found");
//                }
                Date publishDate = null;
                Date autoexpiryDate = null;
                Date issuedDate = null;
                Date vacancyDate = null;
                Date lastSubmissionDate = null;
                Timestamp uploadedDate = null;
                try {
                    if (req.getPublishedDate() != null)
                        publishDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getPublishedDate());
                    if (req.getAutoExpiry() != null)
                        autoexpiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getAutoExpiry());
                    if (req.getIssuedDate() != null)
                        issuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getIssuedDate());
                    if (req.getVacancyUploadedDate() != null)
                        vacancyDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getVacancyUploadedDate());
                    if (req.getLastDateSubmission() != null)
                        lastSubmissionDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getLastDateSubmission());
                    if (req.getUploadedOn() != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                        Date parsedDate = dateFormat.parse(req.getUploadedOn());
                        uploadedDate = new java.sql.Timestamp(parsedDate.getTime());
                    }
//                        uploadedDate = (Timestamp) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(req.getUploadedOn());

                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, " Date is in wrong format.");
                }
                Integer maxRank = contentRepo.findMaxSeqByTabId(req.getModuleId(), req.getSubModuleId());
                Integer greatestRank = 0;
                if (maxRank == null) {
                    greatestRank = 1;
                } else {
                    greatestRank = maxRank + 1;
                }
                Content con = new Content();
                con.setCreatedBy(userId);
                con.setUpdatedBy(userId);
                con.setAuthor(req.getAuthor());
                con.setCorrigendum(req.getCorrigendum());
                con.setAutoExpiry(autoexpiryDate);
                con.setDescription(req.getDescription());
                con.setDocumentSource(req.getDocumentSource());
                con.setDueDateExtended(req.getDueDateExtended());
                con.setFileNumber(req.getFileNumber());
                con.setIsHighlight(req.getIsHighlight());
                con.setIssuedBy(req.getIssuedBy());
                con.setIssuedDate(issuedDate);
                con.setIssuedNo(req.getIssuedNo());
                con.setIsUpdate(req.getIsUpdate());
                con.setLastDateSubmission(lastSubmissionDate);
                con.setLink(req.getLink());
                con.setLocation(req.getLocation());
                con.setModuleId(req.getModuleId());
                con.setPublishedBy(req.getPublishedBy());
                con.setPublishedDate(publishDate);
                con.setRtiQuery(req.getRtiQuery());
                con.setSubModuleId(req.getSubModuleId());
                con.setSource(req.getSource());
                con.setTitle(req.getTitle());
                con.setUploadedOn(uploadedDate);
                con.setVacancyUploadedDate(vacancyDate);
                con.setIsDeleted("1");
                con.setRanking(greatestRank);
                con.setApprovedRejectedStatus(MyConstant.PENDING);
                con.setViewCount(0);

                Content result = contentRepo.save(con);
                if (result.getId() != 0) {
                	if(files != null)
                    Arrays.asList(files).stream().forEach(file -> {
                        if (!file.isEmpty()) {
                            ContentFileMapper fileModel = new ContentFileMapper();
                            float size = file.getSize() / 1024;
                            long timeMilli = System.currentTimeMillis();
                            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                            fileName = fileName.replaceAll("\\s", "_");
                            fileName = fileName.replace("." + extension, "");
                            fileName = StringUtils.cleanPath(fileName + "_" + timeMilli + "." + extension);
                            fileModel.setContentId(Math.toIntExact(result.getId()));
                            fileModel.setFileUrl(fileName);
                            fileModel.setCreatedBy(userId);
                            fileModel.setUpdatedBy(userId);
                            fileModel.setFileSize(String.valueOf(size));
                            fileModel.setFileType(extension);
                            fileModel.setIsDeleted("1");
                            fileModel.setDownloadCount(0);
                            fileModel.setViewCount(0);
                            fileModel.setContentType(ProjectConstants.CONTENT_TYPE);
                            Path uploadPath = Paths.get(ProjectConstants.UPLOADDIR);
                            if (!Files.exists(uploadPath)) {
                                try {
                                    Files.createDirectories(uploadPath);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not create directory " + ProjectConstants.UPLOADDIR);
                                }
                            }
                            try (InputStream inputStream = file.getInputStream()) {
                                Path filePath = uploadPath.resolve(fileName);
                                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                                contentFileMapperRepo.save(fileModel);
                            } catch (Exception ioe) {
                                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not save image file: " + fileName);
                            }
                        }
                    });
                }
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Content Already exist with this title");
            }
        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @Override
    @Transactional
    public Response<String> updateContent(Long contentId, Integer userId, ContentFormRequest req, MultipartFile[] files) {
        Response<String> response = new Response<String>();
        try {
            Optional<Content> optionalContent = contentRepo.findById(contentId);
            if (optionalContent.isPresent()) {
//                if (req.getModuleId() == null) {
//                    throw new HandledException(HttpStatus.BAD_REQUEST, "No Module Id Found");
//                }
//                if (req.getSubModuleId() == null) {
//                    throw new HandledException(HttpStatus.BAD_REQUEST, "No Sub Module Id Found");
//                }
                System.out.println("here");
                Date publishDate = null;
                Date autoexpiryDate = null;
                Date issuedDate = null;
                Date vacancyDate = null;
                Date lastSubmissionDate = null;
                Timestamp uploadedDate = null;
                try {
                    if (req.getPublishedDate() != null)
                        publishDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getPublishedDate());
                    if (req.getAutoExpiry() != null)
                        autoexpiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getAutoExpiry());
                    if (req.getIssuedDate() != null)
                        issuedDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getIssuedDate());
                    if (req.getVacancyUploadedDate() != null)
                        vacancyDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getVacancyUploadedDate());
                    if (req.getLastDateSubmission() != null)
                        lastSubmissionDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getLastDateSubmission());
                    if (req.getUploadedOn() != null)
                        uploadedDate = (Timestamp) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(req.getUploadedOn());

                } catch (ParseException e) {
                    throw new HandledException(HttpStatus.NOT_ACCEPTABLE, " Date is in wrong format.");
                }
                System.out.println("after here");
                Content con = optionalContent.get();
                con.setUpdatedBy(userId);
                con.setAuthor(req.getAuthor());
                con.setCorrigendum(req.getCorrigendum());
                con.setAutoExpiry(autoexpiryDate);
                con.setDescription(req.getDescription());
                con.setDocumentSource(req.getDocumentSource());
                con.setDueDateExtended(req.getDueDateExtended());
                con.setFileNumber(req.getFileNumber());
                con.setIsHighlight(req.getIsHighlight());
                con.setIssuedBy(req.getIssuedBy());
                con.setIssuedDate(issuedDate);
                con.setIssuedNo(req.getIssuedNo());
                con.setIsUpdate(req.getIsUpdate());
                con.setLastDateSubmission(lastSubmissionDate);
                con.setLink(req.getLink());
                con.setLocation(req.getLocation());
                con.setModuleId(req.getModuleId());
                con.setPublishedBy(req.getPublishedBy());
                con.setPublishedDate(publishDate);
                con.setRtiQuery(req.getRtiQuery());
                con.setSubModuleId(req.getSubModuleId());
                con.setSource(req.getSource());
                con.setTitle(req.getTitle());
                con.setUploadedOn((Timestamp) uploadedDate);
                con.setVacancyUploadedDate(vacancyDate);
                con.setRanking(req.getRanking());
                con.setApprovedRejectedStatus(MyConstant.PENDING);
                
                if (req.getIsDeleted() != null && req.getIsDeleted() != 1) {
                    con.setIsDeleted("0");
                    con.setDeletedAt(new Date());
                    con.setDeletedBy(userId);
                }

                Content result = contentRepo.save(con);
                if (result.getId() != 0) {
                    if (result.getRanking() != null) {
                        int value = contentRepo.updateAllRanking(result.getModuleId(), result.getSubModuleId(), con.getRanking(), result.getId());
                    }
                    Arrays.asList(files).stream().forEach(file -> {
                        if (!file.isEmpty()) {
                            List<ContentFileMapper> contentMappers = contentFileMapperRepo.findAllByContentIdAndIsDeleted((int) result.getId(), "1");
                            for (ContentFileMapper mapper : contentMappers) {
                                mapper.setIsDeleted("0");
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                mapper.setDeletedAt(timestamp);
                                contentFileMapperRepo.save(mapper);
                                Path fileToDeletePath = Paths.get(ProjectConstants.UPLOADDIR + mapper.getFileUrl());
                                try {
                                    Files.delete(fileToDeletePath);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }

                            ContentFileMapper fileModel = new ContentFileMapper();
                            float size = file.getSize() / 1024;
                            long timeMilli = System.currentTimeMillis();
                            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                            fileName = fileName.replaceAll("\\s", "_");
                            fileName = fileName.replace("." + extension, "");

                            fileName = StringUtils.cleanPath(fileName + "_" + timeMilli + "." + extension);
                            fileModel.setContentId(Math.toIntExact(result.getId()));
                            fileModel.setFileUrl(fileName);
//                            fileModel.setFileUrl(ProjectConstants.UPLOADDIR + "/" + fileName);
                            fileModel.setCreatedBy(userId);
                            fileModel.setUpdatedBy(userId);
                            fileModel.setFileSize(String.valueOf(size));
                            fileModel.setFileType(extension);
                            fileModel.setIsDeleted("1");
                            fileModel.setContentType(ProjectConstants.CONTENT_TYPE);
                            Path uploadPath = Paths.get(ProjectConstants.UPLOADDIR);
                            if (!Files.exists(uploadPath)) {
                                try {
                                    Files.createDirectories(uploadPath);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not create directory " + ProjectConstants.UPLOADDIR);
                                }
                            }
                            try (InputStream inputStream = file.getInputStream()) {
                                Path filePath = uploadPath.resolve(fileName);
                                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                                contentFileMapperRepo.save(fileModel);
                            } catch (Exception ioe) {
                                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not save image file: " + fileName);
                            }
                        }
                    });
                }
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Content Not found");
            }
        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response<String> deleteContent(Long contentId, Integer userId) {
        Response<String> response = new Response<String>();
        try {
            Optional<Content> optionalContent = contentRepo.findById(contentId);
            if (optionalContent.isPresent()) {
                Content contentModel = optionalContent.get();
                if (contentId == null) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Id Not Found");
                }
                contentModel.setIsDeleted("0");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                contentModel.setDeletedAt(timestamp);
                contentModel.setDeletedBy(userId);
                Content result = contentRepo.save(contentModel);
                if (result.getId() != 0) {
                    List<ContentFileMapper> contentMappers = contentFileMapperRepo.findAllByContentIdAndIsDeleted((int) result.getId(), "1");
                    for (ContentFileMapper mapper : contentMappers) {
                        mapper.setIsDeleted("0");
                        Timestamp timestampNew = new Timestamp(System.currentTimeMillis());
                        mapper.setDeletedAt(timestampNew);
                        mapper.setDeletedBy(userId);
                        contentFileMapperRepo.save(mapper);
                    }
                    response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                    response.setResponseDesc(HttpStatus.OK.name());
                    return response;
                }
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Entry Not found of given id.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Unable to delete Entry.Something went Worng.");
        }
        return response;
    }


}