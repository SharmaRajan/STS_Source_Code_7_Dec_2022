package com.nic.nrlm_aajeevika.service;


import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.entity.*;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.helper.PageModel;
import com.nic.nrlm_aajeevika.helper.ProjectConstants;
import com.nic.nrlm_aajeevika.repository.*;
import com.nic.nrlm_aajeevika.request.Faqrequest;
import com.nic.nrlm_aajeevika.request.TickerPopupSliderRequest;
import com.nic.nrlm_aajeevika.response.*;
import com.nic.nrlm_aajeevika.specification.ContentSpecification;
import com.nic.nrlm_aajeevika.specification.FaqSpecification;
import com.nic.nrlm_aajeevika.specification.TickerSpecification;
import com.nic.nrlm_aajeevika.specification.VisitorsSpecification;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepo;
    @Autowired
    private ContentFileMapperRepository contentFileMapperRepo;

    @Autowired
    private FaqRepository faqRepo;

    @Autowired
    private TickerRepository tickerRepo;


    @Autowired
    private VisitorsRepository visitRepo;

    @Autowired
    private VisitorsSpecification visitSpeci;


    @Autowired
    private FaqSpecification faqspeci;

    @Autowired
    private TickerSpecification tickerspeci;

    @Autowired
    private ContentSpecification contentSpeci;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageModel pageModel;


    @Override
    public Response<ContentListResponse> getContentList(HttpServletRequest request) {
        Response<ContentListResponse> response = new Response<ContentListResponse>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Content> contentList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        if (request.getParameter("moduleId") == null || request.getParameter("moduleId").isEmpty()) {
            throw new HandledException(HttpStatus.BAD_REQUEST, "No Module Id Found");
        } else {
            System.out.println("before   query");
            Page<Content> conetentpageList = contentRepo.findAll(
                    contentSpeci.getContents(request),
                    PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
            response.setTotalcount(conetentpageList.getTotalElements());
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            System.out.println("after query");
            List<ContentListResponse> contentResponse = conetentpageList.getContent().stream()
                    .map(condto -> {
                        List<ContentFileMapperResponse> fileMapper = new ArrayList<ContentFileMapperResponse>();
                        List<ContentFileMapper> fileList = contentFileMapperRepo.findAllByContentIdAndIsDeleted(Math.toIntExact(condto.getId()), "1");
                        if (fileList != null && fileList.size() != 0) {
                            fileMapper = fileList.stream()
                                    .map(file -> new ContentFileMapperResponse(file.getId(), file.getContentId(), ProjectConstants.FILE_URL_PREFIX + file.getFileUrl(), file.getFileSize(), file.getFileType()))
                                    .collect(Collectors.toList());
                        }
                        return new ContentListResponse(condto.getId(), condto.getModuleId(), condto.getSubModuleId(), condto.getTitle(), condto.getDocumentSource(), condto.getAuthor(), condto.getUploadedOn(), condto.getPublishedBy(), condto.getPublishedDate(),
                                condto.getIssuedNo(), condto.getIssuedDate(), condto.getIssuedBy(), condto.getCorrigendum(), condto.getDueDateExtended(),
                                condto.getAutoExpiry(), condto.getLastDateSubmission(), condto.getLocation(), condto.getLink(), condto.getFileNumber(), condto.getSource(),
                                condto.getVacancyUploadedDate(), condto.getRtiQuery(), condto.getDescription(), condto.getIsUpdate(), condto.getIsHighlight(),
                                condto.getApprovedRejectedStatus(), condto.getApprovedRejectedBy(), fileMapper);
                    }).collect(Collectors.toList());

//            List<Content> contentResponse = conetentpageList.getContent().stream()
//                    .map(condto -> modelMapper.map(condto, Content.class)).collect(Collectors.toList());

            response.setWrapperList(contentResponse);
            response.setErrorMsg(errorMsg);
            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;
        }
    }

    @Override
    public Response<ContentListResponse> searchContent(HttpServletRequest request) {
        Response<ContentListResponse> response = new Response<ContentListResponse>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Content> contentList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;

        Page<Content> conetentpageList = contentRepo.findAll(contentSpeci.getSearchContent(request), PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
        response.setTotalcount(conetentpageList.getTotalElements());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        List<ContentListResponse> contentResponse = conetentpageList.getContent().stream().map(condto -> {
            List<ContentFileMapperResponse> fileMapper = new ArrayList<ContentFileMapperResponse>();
            List<ContentFileMapper> fileList = contentFileMapperRepo.findAllByContentIdAndIsDeleted(Math.toIntExact(condto.getId()), "1");
            if (fileList != null && fileList.size() != 0) {
                fileMapper = fileList.stream().map(file -> new ContentFileMapperResponse(file.getId(), file.getContentId(), ProjectConstants.FILE_URL_PREFIX + file.getFileUrl(), file.getFileSize(), file.getFileType())).collect(Collectors.toList());
            }
            return new ContentListResponse(condto.getId(), condto.getModuleId(), condto.getSubModuleId(), condto.getTitle(), condto.getDocumentSource(), condto.getAuthor(), condto.getUploadedOn(), condto.getPublishedBy(), condto.getPublishedDate(), condto.getIssuedNo(), condto.getIssuedDate(), condto.getIssuedBy(), condto.getCorrigendum(), condto.getDueDateExtended(), condto.getAutoExpiry(), condto.getLastDateSubmission(), condto.getLocation(), condto.getLink(), condto.getFileNumber(), condto.getSource(), condto.getVacancyUploadedDate(), condto.getRtiQuery(), condto.getDescription(), condto.getIsUpdate(), condto.getIsHighlight(), condto.getApprovedRejectedStatus(), condto.getApprovedRejectedBy(), fileMapper);
        }).collect(Collectors.toList());

        response.setWrapperList(contentResponse);
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

    @Override
    public Response<String> changeApprovedStatus(List<Long> values, HttpServletRequest request) {
        Response<String> response = new Response<String>();

        String errorMsg = null;
        long contentId = 0;

        if (values == null || values.isEmpty()) {
            throw new HandledException(HttpStatus.BAD_REQUEST, "No Content Id Found");
        } else {
            for (int i = 0; i < values.size(); i++) {

                try {
                    contentId = values.get(i);
                    Content contentrowdata = contentRepo.findById(contentId).orElse(null);
                    if (contentrowdata == null) {
                        continue;
                    }

                    if (Long.parseLong(request.getParameter("flag")) == 1) {
                        contentrowdata.setApprovedRejectedStatus(MyConstant.ACTIVATE);
                    } else {
                        contentrowdata.setApprovedRejectedStatus(MyConstant.INACTIVATE);
                    }

                    contentRepo.save(contentrowdata);

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new HandledException(HttpStatus.NOT_FOUND, "Content id not found");

                }


            }

        }
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

//    @Override
//    public Response<String> saveFaq(Integer userId, Faqrequest request) {
//        Response<String> response = new Response<String>();
//        try {
//            Faq con = new Faq();
//            con.setCreatedBy(userId);
//            con.setUpdatedBy(userId);
//            con.setQuestion(request.getQuestion());
//            con.setAnswer(request.getAnswer());
//
//            Faq result = faqRepo.save(con);
//
//            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
//            response.setResponseDesc(HttpStatus.OK.name());
//            return response;
//
//        } catch (HandledException e) {
//            e.printStackTrace();
//            throw new HandledException(e.getCode(), e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//
//    }

    @Override
    public Response<FaqResponse> getfaqlist(HttpServletRequest request) {
        System.out.println("--------------inside service");
        Response<FaqResponse> response = new Response<FaqResponse>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        System.out.println("--------------befer ftech service");
        Page<Faq> conetentpageList = faqRepo.findAll(
                faqspeci.getFaq(request),
                PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
        List<FaqResponse> contentResponse = conetentpageList.getContent().stream()
                .map(condto -> modelMapper.map(condto, FaqResponse.class)).collect(Collectors.toList());
        conetentpageList.getContent().forEach(c -> {
            System.out.println("dsss" + c.getAnswer());
            System.out.println("dsss" + c.getQuestion());
            System.out.println("dsss" + c.getCreatedAt());
        });
        response.setWrapperList(contentResponse);
        response.setTotalcount(conetentpageList.getTotalElements());
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

    @Override
    public Response<String> saveFaq(Integer userId, Faqrequest request) {
        Response<String> response = new Response<String>();
        try {
            Faq con = new Faq();
            con.setCreatedBy(userId);
            con.setUpdatedBy(userId);
            con.setQuestion(request.getQuestion());
            con.setAnswer(request.getAnswer());

            Faq result = faqRepo.save(con);

            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;

        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }


    @Override
    public Response<String> saveExtraContent(Integer userId, TickerPopupSliderRequest request, MultipartFile file) {
        Response<String> response = new Response<String>();
        try {
            TickerPopupSlider con = new TickerPopupSlider();
            if (file != null) {
                long timeMilli = System.currentTimeMillis();
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                fileName = fileName.replaceAll("\\s", "_");
                fileName = fileName.replace("." + extension, "");
                fileName = StringUtils.cleanPath(fileName + "_" + timeMilli + "." + extension);
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
                    con.setFile_url(fileName);
                } catch (Exception ioe) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not save file: " + fileName);
                }
            }


            con.setCreatedBy(userId);
            con.setUpdatedBy(userId);
            con.setContentType(request.getContentType());

            con.setTickerName(request.getTickerName());
            con.setRanking(request.getRanking());

            System.out.println("----------" + request.getTickerName());

            TickerPopupSlider result = tickerRepo.save(con);

            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;

        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    public Response<TickerPopupSlider> getTickerlist(HttpServletRequest request) {
        Response<TickerPopupSlider> response = new Response<TickerPopupSlider>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Module> contentList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;

        Page<TickerPopupSlider> conetentpageList = tickerRepo.findAll(
                tickerspeci.getticker(request),
                PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
//        this.modelMapper = new ModelMapper();
//        Converter<String, String> toUppercase = new
//                AbstractConverter<String, String>() {
//                    protected String convert(String source) {
//                        return source == null ? null : source;
//                    }
//                };
//        PropertyMap<TickerPopupSlider, TickerPopupSlider> tickerMap = new PropertyMap<TickerPopupSlider, TickerPopupSlider>() {
//            protected void configure() {
//                if (source.getFile_url() != null)
//                    using(toUppercase).map().setFile_url(ProjectConstants.FILE_URL_PREFIX + source.getFile_url());
//            }
//        };
        List<TickerPopupSlider> contentResponse = conetentpageList.getContent().stream()
                .map(condto -> {
                    return new TickerPopupSlider(condto.getId(), condto.getContentType(), condto.getTickerName(), (condto.getFile_url() != null ? (ProjectConstants.FILE_URL_PREFIX + condto.getFile_url()) : null), condto.getRanking(),
                            condto.getCreatedBy(), condto.getUpdatedBy(), condto.getDeletedBy(), condto.getIsDeleted(), condto.getCreatedAt(), condto.getUpdatedAt(), condto.getDeletedAt());
                }).collect(Collectors.toList());
        response.setWrapperList(contentResponse);
        response.setTotalcount(conetentpageList.getTotalElements());
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

    @Override
    public Response<String> getdowncount(HttpServletRequest request) {
        Response<String> response = new Response<String>();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            throw new HandledException(HttpStatus.BAD_REQUEST, "No Id Found");
        } else {

            try {
                Optional<ContentFileMapper> dataofCo = contentFileMapperRepo.findById(Long.parseLong(request.getParameter("id")));
                ContentFileMapper _content = dataofCo.get();
                Integer da = _content.getDownloadCount();

                System.out.println("------------hikk----" + da);
                if (da == null || da == 0) {
                    System.out.println("------------hi----");
                    _content.setDownloadCount(1);
                } else {
                    _content.setDownloadCount((int) _content.getDownloadCount() + 1);
                }
                contentFileMapperRepo.save(_content);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;

            } catch (Exception e) {
                e.printStackTrace();
                throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }


        }
    }

    @Override

    public Response<AuthorResponse> getDropDownList(String keyName, Integer moduleId, HttpServletRequest request) {
        Response<AuthorResponse> response = new Response<AuthorResponse>();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        try {


            if (moduleId == null) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No Module Id Found");
            }
            if (keyName == null || keyName.isEmpty()) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No Key name Found");
            }
//        List<String> checkKeyNames = Arrays.asList("author", "source");
            List<Object[]> masterList = null;
            if (keyName.equals("author")) {
                masterList = contentRepo.findByAuhtor(moduleId);
            } else if (keyName.equals("source")) {
                masterList = contentRepo.findBySource(moduleId);
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Key name is Invalid");
            }
            List<AuthorResponse> contentResponse = new ArrayList<AuthorResponse>();
            ;
            for (Object[] master : masterList) {
                if (master != null) {
                    AuthorResponse auth = new AuthorResponse();
                    auth.setOptionValue(master[0].toString());
                    contentResponse.add(auth);
                }
            }
            response.setWrapperList(contentResponse);
            response.setTotalcount(Long.valueOf(contentResponse.size()));
            response.setErrorMsg(errorMsg);
            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went worng!");

        }
    }

    @Override
    public Response<String> getdownloadcount(HttpServletRequest request, Long requestId) {
        Response<String> response = new Response<String>();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            throw new HandledException(HttpStatus.BAD_REQUEST, "No Id Found");
        } else {

            try {

                Optional<ContentFileMapper> dataofCo = contentFileMapperRepo.findById(Long.parseLong(request.getParameter("id")));
                ContentFileMapper _content = dataofCo.get();
                Integer getdown = _content.getDownloadCount();
                Integer getview = _content.getViewCount();

                System.out.println("------------getdown----" + getdown + "-------getview--------" + getview);
                if (requestId == 1) {
                    _content.setDownloadCount((int) _content.getDownloadCount() + 1);
                } else if (requestId == 2) {
                    _content.setViewCount((int) _content.getViewCount() + 1);
                } else {
                    throw new HandledException(HttpStatus.BAD_REQUEST, "No RequestId Found");
                }
                contentFileMapperRepo.save(_content);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;

            } catch (Exception e) {
                e.printStackTrace();
                throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }


        }
    }

    @Override
    public Response<String> getdownloadcountContent(HttpServletRequest request) {
        Response<String> response = new Response<String>();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            throw new HandledException(HttpStatus.BAD_REQUEST, "No Id Found");
        } else {

            try {

                Optional<Content> dataofCo = contentRepo.findById(Long.parseLong(request.getParameter("id")));
                Content _content = dataofCo.get();

                Integer getview = _content.getViewCount();

                System.out.println("-------getview--------" + getview);

                _content.setViewCount((int) _content.getViewCount() + 1);


                contentRepo.save(_content);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;

            } catch (Exception e) {
                e.printStackTrace();
                throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }


        }
    }

    @Override
    public Response<String> visitorsland(HttpServletRequest request) {
        Response<String> response = new Response<String>();


        try {

            Optional<Visitors> dataofCo = visitRepo.findById(Long.valueOf(1));
            Visitors _content = dataofCo.get();

            Integer getview = (int) _content.getVisitorsCount();

            System.out.println("-------getview--------" + getview);

            _content.setVisitorsCount(getview + 1);


            visitRepo.save(_content);
            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Response<Visitors> vistorcount(HttpServletRequest request) {

        Response<Visitors> response = new Response<Visitors>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;

        Page<Visitors> conetentpageList = visitRepo.findAll(
                visitSpeci.getvisitcount(request),
                PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
        List<Visitors> contentResponse = conetentpageList.getContent().stream()
                .map(condto -> modelMapper.map(condto, Visitors.class)).collect(Collectors.toList());
//        conetentpageList.getContent().forEach(c -> {
//            System.out.println("dsss" + c.getAnswer());
//            System.out.println("dsss" + c.getQuestion());
//            System.out.println("dsss" + c.getCreatedAt());
//        });
        response.setWrapperList(contentResponse);
        response.setTotalcount(conetentpageList.getTotalElements());
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

    @Override
    @Transactional
    public Response<String> updatefaq(Long modId, Integer userId, Faqrequest modreq, HttpServletRequest req) {
        Response<String> response = new Response<String>();
        try {
            Optional<Faq> optionalModule = faqRepo.findById(modId);
            if (optionalModule.isPresent()) {
                Faq modModel = optionalModule.get();
                modModel.setUpdatedBy(userId);

                if (modreq.getQuestion() == null && modreq.getAnswer() != null) {
                    System.out.println("---------first------");
                    modModel.setAnswer(modreq.getAnswer());
                }
                if (modreq.getQuestion() != null && modreq.getAnswer() == null) {
                    System.out.println("---------Second------");
                    modModel.setQuestion(modreq.getQuestion());
                }
                if (modreq.getQuestion() != null && modreq.getAnswer() != null) {
                    System.out.println("---------Third------");
                    modModel.setAnswer(modreq.getAnswer());
                    modModel.setQuestion(modreq.getQuestion());
                }
                modModel.setIsDeleted(String.valueOf(ProjectConstants.ACTIVE_STATUS));
                Faq result = faqRepo.save(modModel);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "FAQ not found");
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
    public Response<String> updateTicker(Long id, Integer userid, TickerPopupSlider request, MultipartFile file) {
        Response<String> response = new Response<String>();
        try {
            Optional<TickerPopupSlider> optionalModule = tickerRepo.findById(id);
            if (optionalModule.isPresent()) {
                TickerPopupSlider modModel = optionalModule.get();
                try {

                    if (file != null) {
                        long timeMilli = System.currentTimeMillis();
                        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                        fileName = fileName.replaceAll("\\s", "_");
                        fileName = fileName.replace("." + extension, "");
                        fileName = StringUtils.cleanPath(fileName + "_" + timeMilli + "." + extension);
                        Path uploadPath = Paths.get(ProjectConstants.UPLOADDIR);
                        System.out.println("--------fileName------" + ProjectConstants.UPLOADDIR + "/" + fileName);
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
                            request.setFile_url(fileName);
                        } catch (Exception ioe) {
                            throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Could not save file: " + fileName);
                        }
                    }

                    modModel.setContentType(request.getContentType());

                    modModel.setTickerName(request.getTickerName());
                    modModel.setRanking(request.getRanking());


                    TickerPopupSlider result = tickerRepo.save(request);

                    response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                    response.setResponseDesc(HttpStatus.OK.name());
                    return response;

                } catch (HandledException e) {
                    e.printStackTrace();
                    throw new HandledException(e.getCode(), e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Module not found");
            }


        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }


    }


}
