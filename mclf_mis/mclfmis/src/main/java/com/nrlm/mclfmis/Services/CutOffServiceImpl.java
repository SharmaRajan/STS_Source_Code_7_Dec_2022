package com.nrlm.mclfmis.Services;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMapping;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.CutOffSkipMapping;
import com.nrlm.mclfmis.Entity.Financial;
import com.nrlm.mclfmis.Entity.GpCutOffEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMapping;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.GpCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Entity.VoCutOffEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMapping;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.VoCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.VoProfile;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfBasicProfileRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMappingRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlyMappingRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlyRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffSkipMappingRepository;
import com.nrlm.mclfmis.Repository.ClfRepository;
import com.nrlm.mclfmis.Repository.FinancialYearRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMappingRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMonthlyMappingRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMonthlyRepository;
import com.nrlm.mclfmis.Repository.GpCutOffRepository;
import com.nrlm.mclfmis.Repository.GpCutOffSkipMappingRepository;
import com.nrlm.mclfmis.Repository.GpRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMappingRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMonthlyMappingRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMonthlyRepository;
import com.nrlm.mclfmis.Repository.VoCutOffRepository;
import com.nrlm.mclfmis.Repository.VoCutOffSkipMappingRepository;
import com.nrlm.mclfmis.Repository.VoRepository;
import com.nrlm.mclfmis.Request.CutOffDataRequest;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.Response.GpCutOffResponse;
import com.nrlm.mclfmis.Response.IndicatorResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.VoCutOffResponse;
import com.nrlm.mclfmis.Specification.CutOffSpecification;
import com.nrlm.mclfmis.customEntity.ClfCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.ClfMasterEntity;
import com.nrlm.mclfmis.customEntity.GpCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.IndicatorCustomEntity;
import com.nrlm.mclfmis.customEntity.VoCustomCutOffEntity;
import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustom;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CutOffServiceImpl implements CutOffService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ClfCutOffRepository clfCutOffRepository;

    @Autowired
    CutOffSpecification cutOffSpecification;

    @Autowired
    VoCutOffRepository voCutOffRepository;

    @Autowired
    GpCutOffRepository gpCutOffRepository;

    @Autowired
    ClfRepository clfRepository;

    @Autowired
    VoRepository voRepository;

    @Autowired
    GpRepository gpRepository;

    @Autowired
    ClfBasicProfileRepository clfBasicRepository;

    @Autowired
    LocationService locationService;
    @Autowired
    CustomCutOffRepository customCutOffRepository;
    @Autowired
    ClfCutOffMappingRepository clfCutOffMappingRepository;
    @Autowired
    VoCutOffMappingRepository voCutOffMappingRepository;
    @Autowired
    GpCutOffMappingRepository gpCutOffMappingRepository;
    
    @Autowired
    ClfCutOffSkipMappingRepository clfCutOffskipRepository;
    
    @Autowired
    VoCutOffSkipMappingRepository voCutOffskipRepository;
    
    @Autowired
    GpCutOffSkipMappingRepository gpCutOffskipRepository;
    
   
    @Autowired
    ClfCutOffMonthlyRepository clfCutOffMonthRepository;
    @Autowired
    VoCutOffMonthlyRepository voCutOffMonthRepository;
    @Autowired
    GpCutOffMonthlyRepository gpCutOffMonthRepository;
    @Autowired
    FinancialYearRepository finyearRepository;
    @Autowired
    private PageModel pageModel;

    @Override
    public Response<ClfCutOffResposne> getClfCutOffList(CutOffFilterRequest filterDto) {

        Response<ClfCutOffResposne> response = new Response<ClfCutOffResposne>();
        List<ClfCutOffResposne> wrappedList = new ArrayList<ClfCutOffResposne>();
        // PageModel.setSIZE(20);
        // pageModel.initPageAndSize();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        pageModel.initOffsetAndLimit();
        try {
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            Long userId = 0L;
            String levelName = "";

            if (authUser != null && authUser.getUserId() != null && authUser.getLevelName() != null) {
                userId = authUser.getUserId();
                levelName = authUser.getLevelName();

            }
            List<String> locations = new ArrayList<String>();
            if (userId == 0 || levelName.isEmpty()) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
            }
            locations = locationService.getLocations(userId, levelName);

            List<ClfCustomCutOffEntity> clfCutOffList = customCutOffRepository.getClfCutOff(filterDto, locations,
                    levelName, pageModel);
            if (clfCutOffList == null || clfCutOffList.size() == 0) {

                response.setWrapperList(wrappedList);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                response.setErrorMsg("No CLF CUT OFF list  Found");
                return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Clf  Found");
            } else {
                wrappedList = clfCutOffList.stream().map(cutoff -> {
                    return new ClfCutOffResposne(cutoff.getId(), cutoff.getClfCode(), cutoff.getStateName(),
                            cutoff.getDistrictName(), cutoff.getBlockName(), cutoff.getClfName(),
                            cutoff.getReportMonth(), cutoff.getOverallStatus(), cutoff.getClfStatus(),
                            cutoff.getRegistrationDate(), cutoff.getBlockCode(), cutoff.getCutOffYear(), cutoff.getCutOffMonth());
                }).collect(Collectors.toList());

                response.setWrapperList(wrappedList);
                response.setTotalcount(
                        customCutOffRepository.getClfCutOffCount(filterDto, locations, levelName, pageModel));
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            }

        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @Override
    public Response<VoCutOffResponse> getVoCutOffList(CutOffFilterRequest filterDto) {

        Response<VoCutOffResponse> response = new Response<VoCutOffResponse>();
        List<VoCutOffResponse> wrappedList = new ArrayList<VoCutOffResponse>();
        pageModel.initOffsetAndLimit();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        try {
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            Long userId = 0L;
            String levelName = null;

            if (authUser != null && authUser.getUserId() != null && authUser.getLevelName() != null) {
                userId = authUser.getUserId();
                levelName = authUser.getLevelName();
            }
            List<String> locations = new ArrayList<String>();
            if (userId == 0 || levelName == null) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
            }
            locations = locationService.getLocations(userId, levelName);
            List<VoCustomCutOffEntity> voCutOffList = customCutOffRepository.getVoCutOff(filterDto, locations,
                    levelName, pageModel);
            if (voCutOffList == null || voCutOffList.size() == 0) {
                response.setWrapperList(wrappedList);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                response.setErrorMsg("No VO  Found");
                return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Vo  Found");
            } else {
                wrappedList = voCutOffList.stream().map(cutoff -> modelMapper.map(cutoff, VoCutOffResponse.class))
                        .collect(Collectors.toList());
                response.setWrapperList(wrappedList);
                response.setTotalcount(
                        customCutOffRepository.getVoCutOffCount(filterDto, locations, levelName, pageModel));
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            }

        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @Override
    public Response<GpCutOffResponse> getGpCutOffList(CutOffFilterRequest filterDto) {

        Response<GpCutOffResponse> response = new Response<GpCutOffResponse>();
        List<GpCutOffResponse> wrappedList = new ArrayList<GpCutOffResponse>();
        pageModel.initOffsetAndLimit();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        try {
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            Long userId = 0L;
            String levelName = null;

            if (authUser != null && authUser.getUserId() != null) {
                userId = authUser.getUserId();
                levelName = authUser.getLevelName();
            }
            List<String> locations = new ArrayList<String>();
            locations = locationService.getLocations(userId, levelName);
            if (userId == 0 || levelName == null) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
            }
            /*
             * if(levelId != ProjectConstants.ADMIN) { locations =
             * locationService.getLocations(userId,levelId); }
             */

            List<GpCustomCutOffEntity> gpCutOffList = customCutOffRepository.getGpCutOff(filterDto, locations,
                    levelName, pageModel);
            if (gpCutOffList == null || gpCutOffList.size() == 0) {
                response.setWrapperList(wrappedList);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                response.setErrorMsg("No GP  Found");
                return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Gp  Found");
            } else {
                wrappedList = gpCutOffList.stream().map(cutoff -> modelMapper.map(cutoff, GpCutOffResponse.class))
                        .collect(Collectors.toList());
                response.setWrapperList(wrappedList);
                response.setTotalcount(
                        customCutOffRepository.getGpCutOffCount(filterDto, locations, levelName, pageModel));
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            }

        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @Override
    public Response<FormResponse> getTabForm(Integer tabId, HttpServletRequest request) {

        Response<FormResponse> response = new Response<FormResponse>();
        List<FormResponse> wrappedList = new ArrayList<FormResponse>();

        try {

            List<SectionEntity> sectionList = customCutOffRepository.getSectionByTabId(tabId);
            if (sectionList == null || sectionList.size() == 0) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No Section Found with given value");
            } else {
                wrappedList = sectionList.stream().map(section -> {
                			Integer skipValue = null;
                			String sectionStatus = ProjectConstants.PENDING_TEXT;
                			//System.out.println(section.getId() + "->" + section.getSectionStatus());
                			
                			//sectionStatus = section.getSectionStatus() == null ? ProjectConstants.PENDING_TEXT : section.getSectionStatus()== ProjectConstants.IN_PROGRESS? ProjectConstants.IN_PROGRESS_TEXT:ProjectConstants.COMPLETED_TEXT;
                			
                			if(tabId == ProjectConstants.TAB_CLF) {
                				ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(request.getParameter("clfCode"));
                				if(clfCutOff != null) {
                					Optional<CutOffSkipMapping> sectionSkipOptional = clfCutOffskipRepository.findByCutOffIdAndSectionId(clfCutOff.getId(),section.getId());
                    				if(sectionSkipOptional.isPresent()) {
                    					skipValue = sectionSkipOptional.get().getSkipValue();
                    					
                    					if(sectionSkipOptional.get().getSectionStatus() != null && sectionSkipOptional.get().getSectionStatus() == ProjectConstants.IN_PROGRESS) {
                            				sectionStatus = ProjectConstants.IN_PROGRESS_TEXT;
                            			}
                            			else if(sectionSkipOptional.get().getSectionStatus() != null && sectionSkipOptional.get().getSectionStatus() == ProjectConstants.COMPLETED) {
                            				sectionStatus = ProjectConstants.COMPLETED_TEXT;
                            			}
                    				}	
                				}
                			}
                			else if(tabId == ProjectConstants.TAB_VO) {
                				VoCutOffEntity voCutOff = customCutOffRepository.checkCutOffExistByVoCode(request.getParameter("voCode"));
                				if(voCutOff != null) {
                					Optional<CutOffSkipMapping> sectionSkipOptional = voCutOffskipRepository.findByCutOffIdAndSectionId(voCutOff.getVoCutOffId(),section.getId());
                    				if(sectionSkipOptional.isPresent()) {
                    					skipValue = sectionSkipOptional.get().getSkipValue();
                    					
                    					if(sectionSkipOptional.get().getSectionStatus() != null && sectionSkipOptional.get().getSectionStatus() == ProjectConstants.IN_PROGRESS) {
                            				sectionStatus = ProjectConstants.IN_PROGRESS_TEXT;
                            			}
                            			else if(sectionSkipOptional.get().getSectionStatus() != null && sectionSkipOptional.get().getSectionStatus() == ProjectConstants.COMPLETED) {
                            				sectionStatus = ProjectConstants.COMPLETED_TEXT;
                            			}
                    				}	
                				}
                				
                			}
                			else if(tabId == ProjectConstants.TAB_GP) {
                				GpCutOffEntity gpCutOff = customCutOffRepository.checkCutOffExistByGpCode(request.getParameter("gpCode"));
                				if(gpCutOff != null) {
                					Optional<CutOffSkipMapping> sectionSkipOptional = gpCutOffskipRepository.findByCutOffIdAndSectionId(gpCutOff.getId(),section.getId());
                    				if(sectionSkipOptional.isPresent()) {
                    					skipValue = sectionSkipOptional.get().getSkipValue();
                    					
                    					if(sectionSkipOptional.get().getSectionStatus() != null && sectionSkipOptional.get().getSectionStatus() == ProjectConstants.IN_PROGRESS) {
                            				sectionStatus = ProjectConstants.IN_PROGRESS_TEXT;
                            			}
                            			else if(sectionSkipOptional.get().getSectionStatus() != null && sectionSkipOptional.get().getSectionStatus() == ProjectConstants.COMPLETED) {
                            				sectionStatus = ProjectConstants.COMPLETED_TEXT;
                            			}
                    				}	
                				}
                			}
                            List<IndicatorCustomEntity> indicatorList = customCutOffRepository
                                    .getIndicatorsValueBySectionId(section.getId(), tabId, request);

                            List<IndicatorResponse> indctrList = new ArrayList<IndicatorResponse>();
                            if (indicatorList != null && indicatorList.size() != 0) {
                                indctrList = indicatorList.stream().map(indctr -> {
                                    List<AnswerOptionResponse> optionlistdto = new ArrayList<AnswerOptionResponse>();
                                    if (indctr.getIndctrType() != null
                                            && (indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN
                                            || indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO || indctr.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN)) {
                                        List<AnswerOptionEntity> optionList = customCutOffRepository.getIndicatorOptions(
                                                ProjectConstants.CUT_OFF, ProjectConstants.CUT_OFF_SHORT_KEY + indctr.getId());
                                        if (optionList != null && optionList.size() != 0) {
                                            optionlistdto = optionList.stream().map(
                                                            opt -> new AnswerOptionResponse(opt.getOptionId(), opt.getOptionValue(),opt.getStatus()))
                                                    .collect(Collectors.toList());
                                        }
                                    }
                               // Need to do logic based on indicator type- Done
                                   if(indctr.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                                      List<Integer> values =   (indctr.getOptionId() != null && !indctr.getOptionId().isEmpty())  ? Arrays.stream(indctr.getOptionId().split(",")).map(Integer::parseInt).collect(Collectors.toList()):null;
                                       return new IndicatorResponse(indctr.getId(), indctr.getIndctrName(),
                                               indctr.getDescription(), indctr.getMandatory(), indctr.getIndctrType(),
                                               indctr.getMinValue(), indctr.getMaxValue(), null,values , optionlistdto);
                                   }
                                   else if(indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO || indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN ){
                                	   return new IndicatorResponse(indctr.getId(), indctr.getIndctrName(),
                                               indctr.getDescription(), indctr.getMandatory(), indctr.getIndctrType(),
                                               indctr.getMinValue(), indctr.getMaxValue(), indctr.getOptionId(),null , optionlistdto);
                                   }
                                   else {
                                	   return new IndicatorResponse(indctr.getId(), indctr.getIndctrName(),
                                               indctr.getDescription(), indctr.getMandatory(), indctr.getIndctrType(),
                                               indctr.getMinValue(), indctr.getMaxValue(), indctr.getIndctrValue(),null , optionlistdto);
                                   }
                                }).collect(Collectors.toList());
                            }
                            return new FormResponse(section.getId(), section.getSectionName(),section.getSkipFlag(),skipValue,sectionStatus, indctrList);
                        }).collect(Collectors.toList()).stream().filter(wrap -> wrap.getIndctrList().size() != 0)
                        .collect(Collectors.toList());

                response.setWrapperList(wrappedList);
                response.setTotalcount(Long.parseLong(String.valueOf(wrappedList.size())));
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            }

        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Response<String> saveCutOffData(CutOffFormRequest request) {

        Response<String> response = new Response<String>();

        try {
            AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            Long userId = 0L;
            String level = null;

            if (authUser != null && authUser.getUserId() != null) {
                userId = authUser.getUserId();
                level = authUser.getLevelName();
            }

            if (userId == 0 || level == null) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
            }

            boolean exist = false;
            boolean isUpdate = false;
            Integer tabId = request.getTabId();

            if (request.getClfCode() == null || request.getClfCode().isEmpty()) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
            } 
            /*else if (request.getCutOffData() == null || request.getCutOffData().size() == 0) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No CutOff Indicator Found");
            }*/

            if (tabId.equals(ProjectConstants.TAB_CLF)) {

                ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(request.getClfCode());
                if (clfCutOff != null) {
                    if (clfCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
                        exist = true;
                    } else {
                        isUpdate = true;
                    }
                }

                if (exist) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
                }

                List<ClfMasterEntity> clfList = clfRepository.findByClfcode(request.getClfCode());
                if (clfList == null || clfList.size() == 0) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
                }

                request = validateCutOffData(request);

                ClfCutOffEntity cutoff = null;

                if (isUpdate) {
                    cutoff = clfCutOff;
                    
                } else {
                    cutoff = new ClfCutOffEntity();
                    cutoff.setCreatedBy(userId);
                    cutoff.setCreatedAt(new Date());
                }
                cutoff.setCutOffMonth(request.getReportMonth());
                cutoff.setCutOffYear(request.getReportYear());
                cutoff.setClfCode(request.getClfCode());
                cutoff.setUpdatedAt(new Date());
                cutoff.setUpdatedBy(userId);
                if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
                    cutoff.setCutOffStatus(ProjectConstants.IN_PROGRESS);
                    cutoff.setOverallStatus(ProjectConstants.IN_PROGRESS);
                } else {
                    cutoff.setCutOffStatus(ProjectConstants.COMPLETED);
                    cutoff.setOverallStatus(this.getOverAllStatus(request.getClfCode()));
                }
                ClfCutOffEntity clfCutoff1 = clfCutOffRepository.save(cutoff);
                if (clfCutoff1.getId() != 0) {
                	
                	request = processCutOffData(request.getTabId(),request,clfCutoff1.getId());
                	
                	//if(request.getSkipList() != null && request.getSkipList().size() != 0) {
                	request = processSkipLogic(request,clfCutoff1.getId());	
                	//}
                	

                    if (request.getErrorMsg() != null && !request.getErrorMsg().isEmpty()) {
                        throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, request.getErrorMsg());
                    }
                }

            } 
            else if (tabId.equals(ProjectConstants.TAB_VO)) {

                if (request.getVoCode() == null || request.getVoCode().isEmpty()) {
                    throw new HandledException(HttpStatus.BAD_REQUEST, "Vo Code is mandatory");
                }
                Optional<VoProfile> optionalVo = voRepository.findByVoCode(request.getVoCode());
                if (!optionalVo.isPresent()) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Vo Exist");
                }

                VoCutOffEntity voCutOff = customCutOffRepository.checkCutOffExistByVoCode(request.getVoCode());

                if (voCutOff != null) {

                    if (voCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
                        exist = true;
                    } else {
                        isUpdate = true;
                    }
                }

                if (exist) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
                }

                request = validateCutOffData(request);

                VoCutOffEntity cutoff = null;

                if (isUpdate) {
                    cutoff = voCutOff;
                } else {
                    cutoff = new VoCutOffEntity();
                    cutoff.setCreatedBy(userId);
                    cutoff.setCreatedAt(new Date());
                }
                cutoff.setVoCode(request.getVoCode());
                cutoff.setCutOffMonth(request.getReportMonth());
                cutoff.setCutOffYear(request.getReportYear());
                cutoff.setUpdatedAt(new Date());
                cutoff.setUpdatedBy(userId);
                if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
                    cutoff.setCutOffStatus(ProjectConstants.IN_PROGRESS);
                } else {
                    cutoff.setCutOffStatus(ProjectConstants.COMPLETED);
                }
                VoCutOffEntity voCutoff1 = voCutOffRepository.save(cutoff);
                
                if (voCutoff1.getVoCutOffId() != 0) {
                	
                	request = processCutOffData(request.getTabId(),request,voCutoff1.getVoCutOffId());  
                	
                	//if(request.getSkipList() != null && request.getSkipList().size() != 0) {
                	request = processSkipLogic(request,voCutoff1.getVoCutOffId());	
                	//}
                	
                             
                	
                    if (request.getErrorMsg() != null && !request.getErrorMsg().isEmpty()) {
                        throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, request.getErrorMsg());
                    }

                    Integer overallStatus = null;
                    if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {

                        overallStatus = ProjectConstants.IN_PROGRESS;
                    } else {
                        overallStatus = this.getOverAllStatus(request.getClfCode());
                    }

                    if (overallStatus != null) {

                        this.saveUpdateOverallStatus(request.getClfCode(), overallStatus);
                    }

                }
            } 
            else if (tabId.equals(ProjectConstants.TAB_GP)) {
                if (request.getGrampanchayatCode() == null || request.getGrampanchayatCode().isEmpty()) {
                    throw new HandledException(HttpStatus.BAD_REQUEST, "Gp Code is mandatory");
                }
                Optional<Grampanchayat> optionalGp = gpRepository.findByGrampanchayatCode(request.getGrampanchayatCode());
                if (!optionalGp.isPresent()) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Grampanchayat Exist");
                }

                GpCutOffEntity gpCutOff = customCutOffRepository.checkCutOffExistByGpCode(request.getGrampanchayatCode());

                if (gpCutOff != null) {

                    if (gpCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
                        exist = true;
                    } else {
                        isUpdate = true;
                    }
                }

                if (exist) {
                    throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
                }

                request = validateCutOffData(request);

                GpCutOffEntity cutoff = null;

                if (isUpdate) {
                    cutoff = gpCutOff;
                } else {
                    cutoff = new GpCutOffEntity();
                    cutoff.setCreatedBy(userId);
                    cutoff.setCreatedAt(new Date());
                }

                cutoff.setGrampanchayatCode(request.getGrampanchayatCode());
                cutoff.setCutOffMonth(request.getReportMonth());
                cutoff.setCutOffYear(request.getReportYear());
                cutoff.setUpdatedAt(new Date());
                cutoff.setUpdatedBy(userId);

                if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
                    cutoff.setCutOffStatus(ProjectConstants.IN_PROGRESS);
                } else {
                    cutoff.setCutOffStatus(ProjectConstants.COMPLETED);
                }
                GpCutOffEntity gpCutoff1 = gpCutOffRepository.save(cutoff);
                if (gpCutoff1.getId() != 0) {
                	
                	request = processCutOffData(request.getTabId(),request,gpCutoff1.getId()); 

                	//if(request.getSkipList() != null && request.getSkipList().size() != 0) {
                	request = processSkipLogic(request,gpCutoff1.getId());	
                	//}
                	
                	
                    if (request.getErrorMsg() != null && !request.getErrorMsg().isEmpty()) {
                        throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, request.getErrorMsg());
                    }

                    Integer overallStatus = null;
                    if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
                        overallStatus = ProjectConstants.IN_PROGRESS;
                    } else {
                        overallStatus = this.getOverAllStatus(request.getClfCode());
                    }

                    if (overallStatus != null) {
                        saveUpdateOverallStatus(request.getClfCode(), overallStatus);

                    }
                }
            } else {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab Found");
            }

            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;

        } catch (HandledException e) {
        	e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

        // return null;
    }


	@Transactional
    private CutOffFormRequest processCutOffData(Integer tabId,CutOffFormRequest request, Long cutOffId) { 
    	try {
    		for (CutOffDataRequest indReq : request.getCutOffData()) {
                if(request.getSkipList() == null || !request.getSkipList().contains(indReq.getSectionId())) {
                   if(tabId == ProjectConstants.TAB_CLF) {

                   	ClfCutOffMapping ind = null;
                    	Optional<ClfCutOffMapping> optionalInd = null;
                    	
                       if (indReq.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                           clfCutOffMappingRepository.deleteAllByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                           for (Integer optionid : indReq.getMultiselectOptionValue()) {
                               ind = new ClfCutOffMapping();
                               indReq.setIndctrValue(null);
                               this.saveIndicatorMapping(ind, indReq, cutOffId, optionid);
                           }
                       }
                       else {
                           optionalInd = clfCutOffMappingRepository.findByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                           if (optionalInd.isPresent()) {
                               ind = optionalInd.get();
                           } 
                           else {
                               ind = new ClfCutOffMapping();
                           }
                           if(indReq.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN || indReq.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO) {
                        	   
                        	   if(indReq.getOptionId() != null) {
                        		   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                        	   }
                        	   else if(indReq.getIndctrValue() != null && !indReq.getIndctrValue().trim().isEmpty()) {
                        		   indReq.setOptionId(Integer.parseInt(indReq.getIndctrValue()));
                        		   indReq.setIndctrValue(null);
                        		   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                        	   }
                        	   
                           }
                           else {
                        	   indReq.setOptionId(null);
                        	   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                           }
                                                  
                       }
       
                	}
                	else if(tabId == ProjectConstants.TAB_VO) {
                		
                		VoCutOffMapping ind = null;
                    	Optional<VoCutOffMapping> optionalInd = null;

                        if (indReq.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                        	voCutOffMappingRepository.deleteAllByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            for (Integer optionid : indReq.getMultiselectOptionValue()) {
                                ind = new VoCutOffMapping();
                                indReq.setIndctrValue(null);
                                this.saveIndicatorMapping(ind, indReq, cutOffId, optionid);
                            }
                        }
                        else {
                        	optionalInd = voCutOffMappingRepository.findByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            if (optionalInd.isPresent()) {
                                ind = optionalInd.get();
                            } 
                            else {
                                ind = new VoCutOffMapping();
                            }
                            if(indReq.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN || indReq.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO) {
                         	   
                         	   if(indReq.getOptionId() != null) {
                         		   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                         	   }
                         	   else if(indReq.getIndctrValue() != null && !indReq.getIndctrValue().trim().isEmpty()) {
                         		   indReq.setOptionId(Integer.parseInt(indReq.getIndctrValue()));
                         		   indReq.setIndctrValue(null);
                         		   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                         	   }
                         	   
                            }
                            else {
                         	   indReq.setOptionId(null);
                         	   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                            }
                                                   
                        }
                	}
                	else if(tabId == ProjectConstants.TAB_GP) {
                		GpCutOffMapping ind = null;
                    	Optional<GpCutOffMapping> optionalInd = null;

                    	
                        if (indReq.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                            gpCutOffMappingRepository.deleteAllByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            for (Integer optionid : indReq.getMultiselectOptionValue()) {
                                ind = new GpCutOffMapping();
                                indReq.setIndctrValue(null);
                                this.saveIndicatorMapping(ind, indReq, cutOffId, optionid);
                            }
                        }
                        else {
                            optionalInd = gpCutOffMappingRepository.findByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            if (optionalInd.isPresent()) {
                                ind = optionalInd.get();
                            } 
                            else {
                                ind = new GpCutOffMapping();
                            }
                            if(indReq.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN || indReq.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO) {
                         	   
                         	   if(indReq.getOptionId() != null) {
                         		   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                         	   }
                         	   else if(indReq.getIndctrValue() != null && !indReq.getIndctrValue().trim().isEmpty()) {
                         		   indReq.setOptionId(Integer.parseInt(indReq.getIndctrValue()));
                         		   indReq.setIndctrValue(null);
                         		   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                         	   }
                         	   
                            }
                            else {
                         	   indReq.setOptionId(null);
                         	   this.saveIndicatorMapping(ind, indReq, cutOffId, indReq.getOptionId());
                            }
                                                   
                        }
                	}
                	
                }
            }
    	}
    	catch(Exception e) {
    		request.setErrorMsg("Error in Processing Indicators");
    	}
    	
    	return request;
    }

	private CutOffFormRequest processSkipLogic(CutOffFormRequest request,Long cutOffId) {
    	try {
	    	Integer tabId = request.getTabId();
	    	
	    	Optional<CutOffSkipMapping> cutOffSkipOptional = null;
	    	CutOffSkipMapping cutOffSkip = null;
	    	
	    	for(Long skip  : request.getSkipList()) {
	    		
	    		if(tabId == ProjectConstants.TAB_CLF) {
		    		cutOffSkipOptional  = 	clfCutOffskipRepository.findByCutOffIdAndSectionId(cutOffId, skip);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		cutOffSkipOptional = 	voCutOffskipRepository.findByCutOffIdAndSectionId(cutOffId, skip);		
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		cutOffSkipOptional = 	gpCutOffskipRepository.findByCutOffIdAndSectionId(cutOffId, skip);
				}
	    		
	    		if(cutOffSkipOptional.isPresent()) {
	    			cutOffSkip = cutOffSkipOptional.get();
	    		}
	    		else {
	    		
	    			if(tabId == ProjectConstants.TAB_CLF) {
		     			cutOffSkip = new ClfCutOffSkipMapping();
		        	}
		        	else if(tabId == ProjectConstants.TAB_VO) {
		        		cutOffSkip = new VoCutOffSkipMapping();
		    		}
		        	else if(tabId == ProjectConstants.TAB_GP) {
		        		cutOffSkip = new GpCutOffSkipMapping();
		    		}
	    			
	    		}
	    		cutOffSkip.setCutOffId(cutOffId);
		     	cutOffSkip.setSectionId(skip);
		     	cutOffSkip.setSkipValue(ProjectConstants.SKIP_APPLIED);
		     	cutOffSkip.setSectionStatus(ProjectConstants.IN_PROGRESS);
		     	
		     	if(tabId == ProjectConstants.TAB_CLF) {
		 			clfCutOffskipRepository.save((ClfCutOffSkipMapping)cutOffSkip);
		 	     	clfCutOffMappingRepository.deleteAllByCutOffIdAndSectionId(cutOffId,skip);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		voCutOffskipRepository.save((VoCutOffSkipMapping)cutOffSkip);
		 	     	voCutOffMappingRepository.deleteAllByCutOffIdAndSectionId(cutOffId,skip);
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		gpCutOffskipRepository.save((GpCutOffSkipMapping)cutOffSkip);
		    		gpCutOffMappingRepository.deleteAllByCutOffIdAndSectionId(cutOffId,skip);
				}
	    	}
	    	 	
	    	for(Long filledSection  : request.getFilledSection()) {
	    		
	    		if(tabId == ProjectConstants.TAB_CLF) {
		    		cutOffSkipOptional  = 	clfCutOffskipRepository.findByCutOffIdAndSectionId(cutOffId, filledSection);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		cutOffSkipOptional = 	voCutOffskipRepository.findByCutOffIdAndSectionId(cutOffId, filledSection);		
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		cutOffSkipOptional = 	gpCutOffskipRepository.findByCutOffIdAndSectionId(cutOffId, filledSection);
				}
	    		
	    		if(cutOffSkipOptional.isPresent()) {
	    			cutOffSkip = cutOffSkipOptional.get();
	    		}
	    		else {
	    		
	    			if(tabId == ProjectConstants.TAB_CLF) {
		     			cutOffSkip = new ClfCutOffSkipMapping();
		        	}
		        	else if(tabId == ProjectConstants.TAB_VO) {
		        		cutOffSkip = new VoCutOffSkipMapping();
		    		}
		        	else if(tabId == ProjectConstants.TAB_GP) {
		        		cutOffSkip = new GpCutOffSkipMapping();
		    		}
	    			
	    		}
	    		cutOffSkip.setCutOffId(cutOffId);
		     	cutOffSkip.setSectionId(filledSection);
		     	cutOffSkip.setSkipValue(ProjectConstants.SKIP_NOT_APPLIED);
		     	cutOffSkip.setSectionStatus(customCutOffRepository.getCutOffSectionStatusByCutOffIdAndSectionId(tabId, cutOffId, filledSection));
		     	
		     	if(tabId == ProjectConstants.TAB_CLF) {
		 			clfCutOffskipRepository.save((ClfCutOffSkipMapping)cutOffSkip);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		voCutOffskipRepository.save((VoCutOffSkipMapping)cutOffSkip);
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		gpCutOffskipRepository.save((GpCutOffSkipMapping)cutOffSkip);
				}
	    	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		request.setErrorMsg("Error in Processing Skip");
    	}
    	return request;
    }

	private void saveIndicatorMapping(Object model, CutOffDataRequest request, Long cutoffid, Integer optionid) {

//        Object obejctmodel;
//        if (model instanceof ClfCutOffMapping) {
//            Class cls = Class.forName("com.nrlm.mclfmis.Entity.ClfCutOffMapping");
//            ClfCutOffMapping obj = (ClfCutOffMapping) cls.newInstance();
//        }
//        if (model instanceof VoCutOffMapping) {
//            obejctmodel = model;
//        }
//        if (model instanceof GpCutOffMapping) {
//            obejctmodel = model;
//        }
        if (model instanceof ClfCutOffMapping) {
            ClfCutOffMapping clfcutoffMapping = (ClfCutOffMapping) model;
            clfcutoffMapping.setCutOffId(cutoffid);
            clfcutoffMapping.setIndctrId(request.getIndctrId());
            clfcutoffMapping.setIndctrValue(request.getIndctrValue());
            clfcutoffMapping.setOptionId(optionid);
            clfcutoffMapping.setSectionId(request.getSectionId());
            clfCutOffMappingRepository.save(clfcutoffMapping);
        }
        if (model instanceof VoCutOffMapping) {
            VoCutOffMapping vocutoffMapping = (VoCutOffMapping) model;
            vocutoffMapping.setCutOffId(cutoffid);
            vocutoffMapping.setIndctrId(request.getIndctrId());
            vocutoffMapping.setIndctrValue(request.getIndctrValue());
            vocutoffMapping.setOptionId(optionid);
            vocutoffMapping.setSectionId(request.getSectionId());
            voCutOffMappingRepository.save(vocutoffMapping);
        }
        if (model instanceof GpCutOffMapping) {
            GpCutOffMapping gpcutoffMapping = (GpCutOffMapping) model;
            gpcutoffMapping.setCutOffId(cutoffid);
            gpcutoffMapping.setIndctrId(request.getIndctrId());
            gpcutoffMapping.setIndctrValue(request.getIndctrValue());
            gpcutoffMapping.setOptionId(optionid);
            gpcutoffMapping.setSectionId(request.getSectionId());
            gpCutOffMappingRepository.save(gpcutoffMapping);
        }
    }

    private void saveUpdateOverallStatus(String clfCode, Integer overallStatus) {
        ClfCutOffEntity clfCutoff = customCutOffRepository.checkCutOffExistByClfCode(clfCode);

        ClfCutOffEntity clfcutOffEntity = null;
        if (clfCutoff == null) {

            clfcutOffEntity = new ClfCutOffEntity();
            clfcutOffEntity.setClfCode(clfCode);
            clfcutOffEntity.setCreatedAt(new Date());
            clfcutOffEntity.setUpdatedAt(new Date());
            clfcutOffEntity.setCreatedBy(0L);
            clfcutOffEntity.setUpdatedBy(0L);
            clfcutOffEntity.setCutOffMonth(0);
            clfcutOffEntity.setCutOffYear(0);
            clfcutOffEntity.setCutOffStatus(ProjectConstants.PENDING);
        } else {
            clfcutOffEntity = clfCutoff;
        }

        clfcutOffEntity.setOverallStatus(overallStatus);

        clfCutOffRepository.save(clfcutOffEntity);
    }


    private CutOffFormRequest validateCutOffData(CutOffFormRequest request) {
        try {
            Optional<ClfBasicProfile> optionalBasicProfile = clfBasicRepository.findByClfCode(request.getClfCode());
            if (!optionalBasicProfile.isPresent()) {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Basic Profile is Pending");
            }
            if (optionalBasicProfile.get().getProfileStatus() != ProjectConstants.BASICPROFILE_COMPLETED) {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Basic Profile is Pending");
            }

            String errorMsg = "";

            if (request.getReportYear() == null) {
                request.setReportYear(0);
            }
            if (request.getReportMonth() == null) {
                request.setReportMonth(0);
            }


            if (request.getReportYear() != 0) {

                Optional<Financial> optionalFinYear = finyearRepository.findById(request.getReportYear());
                if (optionalFinYear.isPresent()) {

                    Date curDate = new Date();
                    boolean isCurYear = false;
                    Date finStartDate = optionalFinYear.get().getFnclStartDate();
                    Date finEndDate = optionalFinYear.get().getFnclEndDate();

                    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                    //Date cutOffStartDate = formatter.parse(ProjectConstants.CUTOFF_START_DATE);
                    if (request.getReportYear() == ProjectConstants.CUT_OFF_START_YEAR_ID) {
                        if (request.getReportMonth() != ProjectConstants.MARCH) {
                            if (request.getIsDraft() != null && request.getIsDraft() == 0) {
                                errorMsg = "Invalid month. Data is saved as draft";
                            }
                            request.setIsDraft(ProjectConstants.IS_DRAFT);
                        }

                    } else if (request.getReportYear() > ProjectConstants.CUT_OFF_START_YEAR_ID) {

                        if (curDate.compareTo(finStartDate) >= 0 && curDate.compareTo(finEndDate) <= 0) {
                            isCurYear = true;
                        }

                        if (isCurYear) {
                            Integer curMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                            if (request.getReportMonth() == 0 || request.getReportMonth() > curMonth) {
                                if (request.getIsDraft() != null && request.getIsDraft() == 0) {
                                    errorMsg = "Invalid month. Data is saved as draft";
                                }
                                request.setIsDraft(ProjectConstants.IS_DRAFT);
                            }
                        } else {
                            if (request.getReportMonth() < 1 || request.getReportMonth() > 12) {
                                if (request.getIsDraft() != null && request.getIsDraft() == 0) {
                                    errorMsg = "Invalid month. Data is saved as draft";
                                }
                                request.setIsDraft(ProjectConstants.IS_DRAFT);
                            }
                        }
                    }
                } else {
                    if (request.getIsDraft() != null && request.getIsDraft() == 0) {
                        errorMsg = "Invalid year. Data is saved as draft";
                    }
                    request.setIsDraft(ProjectConstants.IS_DRAFT);
                }
            } else {
                if (request.getIsDraft() != null && request.getIsDraft() == 0) {
                    errorMsg = "Invalid year. Data is saved as draft";
                }
                request.setIsDraft(ProjectConstants.IS_DRAFT);
            }
            request.setErrorMsg(errorMsg);
            return request;
        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        //return null;
    }

    /**
     * Get Form Data Value
     **/
    @Override
    public Response<FormResponse> getTabFormData(Integer tabId, Integer cutOffId, HttpServletRequest request) {
        Response<FormResponse> response = new Response<FormResponse>();
        List<FormResponse> wrappedList = new ArrayList<FormResponse>();
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        try {
            List<SectionEntity> sectionList = customCutOffRepository.getSectionByTabId(tabId);
            if (sectionList == null || sectionList.size() == 0) {
                throw new HandledException(HttpStatus.BAD_REQUEST, "No Section Found");
            } else {
                wrappedList = sectionList.stream().map(section -> {
                            List<IndicatorCustomEntity> indicatorList = customCutOffRepository
                                    .getIndicatorsValueBySectionId(section.getId(), tabId, request);
                            List<IndicatorResponse> indctrList = new ArrayList<IndicatorResponse>();
                            if (indicatorList != null && indicatorList.size() != 0) {
                                indctrList = indicatorList.stream().map(indctr -> {
                                    List<AnswerOptionResponse> optionlistdto = new ArrayList<AnswerOptionResponse>();
                                    if (indctr.getIndctrType() != null
                                            && (indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN
                                            || indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO)) {
                                        List<AnswerOptionEntity> optionList = customCutOffRepository.getIndicatorOptions(
                                                ProjectConstants.CUT_OFF, ProjectConstants.CUT_OFF_SHORT_KEY + indctr.getId());
                                        if (optionList != null && optionList.size() != 0) {
                                            optionlistdto = optionList.stream().map(
                                                            opt -> new AnswerOptionResponse(opt.getOptionId(), opt.getOptionValue(),opt.getStatus()))
                                                    .collect(Collectors.toList());
                                        }
                                    }
                                    return new IndicatorResponse(indctr.getId(), indctr.getIndctrName(),
                                            indctr.getDescription(), indctr.getMandatory(), indctr.getIndctrType(),
                                            indctr.getMinValue(), indctr.getMaxValue(), indctr.getIndctrValue(),null, optionlistdto);
                                }).collect(Collectors.toList());
                            }
                            return new FormResponse(section.getId(), section.getSectionName(),0,0,null, indctrList);
                        }).collect(Collectors.toList()).stream().filter(wrap -> wrap.getIndctrList().size() != 0)
                        .collect(Collectors.toList());

                response.setWrapperList(wrappedList);
                response.setTotalcount(Long.parseLong(String.valueOf(wrappedList.size())));
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            }
        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    /*
     * Get OverAll Status
     */

    @Override
    public Integer getOverAllStatus(String clfCode) {
        Optional<ClfCutOffEntity> optionalclfcutoff = clfCutOffRepository.findByClfCode(clfCode);
        if (optionalclfcutoff.isPresent()) {
            CutOffFilterRequest filterDto = new CutOffFilterRequest(null, null, null, clfCode, null, null, null,null,null);
            if (optionalclfcutoff.get().getCutOffStatus() == ProjectConstants.PENDING || optionalclfcutoff.get().getCutOffStatus() == null) {
                return ProjectConstants.PENDING;
            } else if (optionalclfcutoff.get().getCutOffStatus() == ProjectConstants.IN_PROGRESS) {
                return ProjectConstants.IN_PROGRESS;
            } else {
                List<VoCustomCutOffEntity> voCutOffList = customCutOffRepository.getVoCutOffStatus(filterDto);
                List<GpCustomCutOffEntity> gpCutOffList = customCutOffRepository.getGpCutOffStatus(filterDto);

                boolean progressVOStatus = voCutOffList.stream().filter(vo -> vo.getStatus().equals(ProjectConstants.IN_PROGRESS_TEXT))
                        .findFirst().isPresent();
                boolean progressGPStatus = gpCutOffList.stream().filter(gp -> gp.getStatus().equals(ProjectConstants.IN_PROGRESS_TEXT))
                        .findFirst().isPresent();
                boolean pendVOStatus = voCutOffList.stream().filter(vo -> vo.getStatus().equals(ProjectConstants.PENDING_TEXT)).findFirst()
                        .isPresent();
                boolean pendGPStatus = gpCutOffList.stream().filter(gp -> gp.getStatus().equals(ProjectConstants.PENDING_TEXT)).findFirst()
                        .isPresent();
                if (progressVOStatus || progressGPStatus) {
                    return ProjectConstants.IN_PROGRESS;
                } else if (pendVOStatus || pendGPStatus) {
                    return optionalclfcutoff.get().getCutOffStatus() > 1 ? ProjectConstants.IN_PROGRESS : ProjectConstants.PENDING;
                } else {
                    return ProjectConstants.COMPLETED;
                }
            }
        }
        return ProjectConstants.PENDING;
    }

    @Override
    public Response<Map<String, Integer>> getCutOffYearMonth(String clfCode, String voCode, String gpCode,
                                                             String formKey) {
        Response<Map<String, Integer>> response = new Response<Map<String, Integer>>();
        List<Map<String, Integer>> wrapperList = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> wrapperObj = new HashMap<String, Integer>();
        try {
            if (formKey.equals("cut-off")) {
                String code = "";
                if (clfCode != null && !clfCode.equals("")) {
                    code = clfCode;
                } else if (voCode != null && !voCode.equals("")) {
                    code = voCode;
                } else if (gpCode != null && !gpCode.equals("")) {
                    code = gpCode;
                }
                if (!code.isEmpty()) {
                    Optional<ClfBasicProfile> optionalBasicProfile = clfBasicRepository.findByClfCode(clfCode);
                    if (optionalBasicProfile.isPresent()) {
                        ClfBasicProfile basicProfile = optionalBasicProfile.get();
                        if (basicProfile.getProfileStatus() == ProjectConstants.BASICPROFILE_COMPLETED) {
                            wrapperObj.put("yearId", basicProfile.getFinYearId());
                            if (basicProfile.getMonthId() == 12) {
                                wrapperObj.put("monthId", 0);
                            } else {
                                wrapperObj.put("monthId", basicProfile.getMonthId());
                            }

                        } else {
                            throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Basic Profile is Pending");
                        }

                    } else {
                        throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Basic Profile is Pending");
                    }
                } else {
                    throw new HandledException(HttpStatus.BAD_REQUEST, "No Code Found");
                }

            } else if (formKey.equals("monthlyInputs")) {
                if (clfCode != null && !clfCode.isEmpty()) {
                    ClfCutOffMonthlyEntity clfCutOffMonthly = clfCutOffMonthRepository
                            .getLatestMonthlyClfCutOff(clfCode);
                    if (clfCutOffMonthly != null) {
                        wrapperObj.put("yearId", clfCutOffMonthly.getCutOffYear());
                        if (clfCutOffMonthly.getCutOffMonth() == 12) {
                            wrapperObj.put("monthId", 0);
                        } else {
                            wrapperObj.put("monthId", clfCutOffMonthly.getCutOffMonth());
                        }
                    } else {
                        ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(clfCode);
                        if (clfCutOff != null && clfCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
                            wrapperObj.put("yearId", clfCutOff.getCutOffYear());
                            if (clfCutOff.getCutOffMonth() == 12) {
                                wrapperObj.put("monthId", 0);
                            } else {
                                wrapperObj.put("monthId", clfCutOff.getCutOffMonth());
                            }
                        } else {
                            throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Cut Off is Pending");
                        }
                    }
                } else if (voCode != null && !voCode.isEmpty()) {
                    VoCutOffMonthlyEntity cutOffMonthly = voCutOffMonthRepository.getLatestVoCutOff(voCode);
                    if (cutOffMonthly != null) {
                        wrapperObj.put("yearId", cutOffMonthly.getCutOffYear());
                        if (cutOffMonthly.getCutOffMonth() == 12) {
                            wrapperObj.put("monthId", 0);
                        } else {
                            wrapperObj.put("monthId", cutOffMonthly.getCutOffMonth());
                        }
                    } else {
                        VoCutOffEntity cutOff = customCutOffRepository.checkCutOffExistByVoCode(voCode);
                        if (cutOff != null && cutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
                            wrapperObj.put("yearId", cutOff.getCutOffYear());
                            if (cutOff.getCutOffMonth() == 12) {
                                wrapperObj.put("monthId", 0);
                            } else {
                                wrapperObj.put("monthId", cutOff.getCutOffMonth());
                            }
                        } else {
                            throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Cut Off is Pending");
                        }
                    }
                } else if (gpCode != null && !gpCode.isEmpty()) {
                    GpCutOffMonthlyEntity cutOffMonthly = gpCutOffMonthRepository.getLatestGpCutOff(gpCode);
                    if (cutOffMonthly != null) {
                        wrapperObj.put("yearId", cutOffMonthly.getCutOffYear());
                        if (cutOffMonthly.getCutOffMonth() == 12) {
                            wrapperObj.put("monthId", 0);
                        } else {
                            wrapperObj.put("monthId", cutOffMonthly.getCutOffMonth());
                        }
                    } else {
                        GpCutOffEntity cutOff = customCutOffRepository.checkCutOffExistByGpCode(gpCode);
                        if (cutOff != null && cutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
                            wrapperObj.put("yearId", cutOff.getCutOffYear());
                            if (cutOff.getCutOffMonth() == 12) {
                                wrapperObj.put("monthId", 0);
                            } else {
                                wrapperObj.put("monthId", cutOff.getCutOffMonth());
                            }
                        } else {
                            throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Cut Off is Pending");
                        }
                    }
                }
            }
            wrapperList.add(wrapperObj);
            response.setWrapperList(wrapperList);
            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return response;
    }

}