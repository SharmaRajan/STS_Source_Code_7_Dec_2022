package com.nrlm.mclfmis.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.AnswerOptionRepository;
import com.nrlm.mclfmis.Repository.IndicatorRepository;
import com.nrlm.mclfmis.Request.AnswerOptionDataRequest;
import com.nrlm.mclfmis.Request.IndicatorFormRequest;
import com.nrlm.mclfmis.Request.IndicatorSequenceRequest;
import com.nrlm.mclfmis.Request.SectionSequenceRequest;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.IndicatorListResponse;
import com.nrlm.mclfmis.Response.IndicatorResponse;
import com.nrlm.mclfmis.Response.IndicatorSequenceResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionSequenceResponse;
import com.nrlm.mclfmis.Specification.IndicatorSpecification;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;
import com.nrlm.mclfmis.exception.HandledException;

@Service
public class IndicatorServiceImpl implements IndicatorService {

    @Autowired
    IndicatorRepository indicatorRepository;
    @Autowired
    IndicatorSpecification indicatorSpecification;
    @Autowired
    CustomCutOffRepository customCutOffRepository;
    @Autowired
    private AnswerOptionRepository ansOpRepository;
    @Autowired
    private PageModel pageModel;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Response<String> saveIndicator(Long loginId, IndicatorFormRequest req) {

        Response<String> response = new Response<String>();
        try {
        	int i = 0;
            Optional<Indicator> optionalindi = indicatorRepository
                    .findBySectionIdAndIndctrName(req.getSectionId(), req.getIndctrName().toLowerCase());
            if (!optionalindi.isPresent()) {
            	
            	Integer seqNo = 0;
            	
            	Integer sequence = indicatorRepository.findMaxSeqBySection(req.getSectionId());
            	if (sequence == null) {
					seqNo = 1;
				} else {
					seqNo = sequence + 1;
				}
            	
                Indicator incr = new Indicator();
                incr.setCreatedBy(loginId);
                incr.setSectionId(req.getSectionId());
                incr.setIndctrName(req.getIndctrName());
                incr.setSequence(seqNo);
                incr.setIndctrType(req.getIndctrType());
                incr.setStartMonth(req.getStartMonth());
                incr.setEndMonth(req.getEndMonth());
                incr.setStartFnclYear(req.getStartFnclYear());
                incr.setEndFnclYear(req.getEndFnclYear());
                incr.setDescription(req.getDescription());
                incr.setMandatory(req.getMandatory());
                incr.setMinValue(req.getMinValue());
                incr.setMaxValue(req.getMaxValue());
                incr.setFreezeFlag(req.getFreezeFlag());
                incr.setUpdatedBy(loginId);
                incr.setStatus(ProjectConstants.ACTIVE_STATUS);
                incr.setCreatedAt(new Date());
                incr.setUpdatedAt(new Date());
                Indicator incrSaved = indicatorRepository.save(incr);
                if (incrSaved.getId() != 0 && (incrSaved.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN 
                		// || incrSaved.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO 
                		|| incrSaved.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN)) {
                	i = 0;
                	for (AnswerOptionDataRequest ansReq : req.getAnsweroptionData()) {
                		i++;
                		AnswerOptionEntity ans = new AnswerOptionEntity();
                        ans.setTableName("cut_off");
                        ans.setShortKey("indicator_" + incrSaved.getId());
                        ans.setOptionId(ansReq.getOptionId());
                        ans.setOptionValue(ansReq.getOptionValue());
                        ans.setStatus(ProjectConstants.ACTIVE_STATUS);
                        if(ansReq.getSeq() != null) {
                        	ans.setSequence(ansReq.getSeq());
                        }
                        else {
                        	ans.setSequence(i);
                        }
                        ansOpRepository.save(ans);
                    }
                }
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Indicator Already exist with this name");
            }
        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Response<IndicatorListResponse> getIndicatorList(HttpServletRequest request) {
        Response<IndicatorListResponse> response = new Response<IndicatorListResponse>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Indicator> indicatorList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
        if (request.getParameter("tabid") != null) {
            Page<Indicator> indicatorpageList = indicatorRepository.findAll(
                    indicatorSpecification.getIndicators(Integer.parseInt(request.getParameter("tabid")), request),
                    PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
           // response.setTotalcount(indicatorpageList.getTotalElements());
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            
            totalCount = indicatorpageList.getTotalElements();

            List<IndicatorListResponse> indicatorResponse = indicatorpageList.getContent().stream().map(indctr -> {
                List<AnswerOptionResponse> optionlistdto = new ArrayList<AnswerOptionResponse>();
                if (indctr.getIndctrType() != null && (indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN
                        //|| indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO 
                        || indctr.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN)) {
                    List<AnswerOptionEntity> optionList = customCutOffRepository.getIndicatorOptions(
                            ProjectConstants.CUT_OFF, ProjectConstants.CUT_OFF_SHORT_KEY + indctr.getId());
                    if (optionList != null && optionList.size() != 0) {
                        optionlistdto = optionList.stream()
                                .map(opt -> new AnswerOptionResponse(opt.getOptionId(), opt.getOptionValue(),opt.getStatus(),opt.getSequence()))
                                .collect(Collectors.toList());
                    }
                }
                return new IndicatorListResponse(indctr.getId(), indctr.getIndctrName(), indctr.getDescription(),
                        indctr.getMandatory(), indctr.getIndctrType(), indctr.getMinValue(), indctr.getMaxValue(),
                        indctr.getSectionId(), indctr.getSequence(), indctr.getStartMonth(), indctr.getEndMonth(),
                        indctr.getStartFnclYear(), indctr.getEndFnclYear(), indctr.getFreezeFlag(), indctr.getStatus(),
                        indctr.getCreatedBy(), indctr.getUpdatedBy(), indctr.getSectionEntity().getSectionName(),
                        optionlistdto);
            }).collect(Collectors.toList());
            response.setWrapperList(indicatorResponse);
        } else {
            throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab Id Found");
        }
        response.setTotalcount(totalCount);
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());

        return response;
    }

    @Override
    public Response<String> updateIndicator(Long loginId, Long indId, IndicatorFormRequest req) {
        Response<String> response = new Response<String>();
        try {
        	int i = 0;
            Optional<Indicator> indi = indicatorRepository.findById(Integer.parseInt("" + indId));
            if (indi.isPresent()) {
                Optional<Indicator> optionalindi = indicatorRepository
                        .findBySectionIdAndIndctrName(req.getSectionId(), req.getIndctrName());
                Indicator inup = indi.get();
                Indicator indopt = (optionalindi.isPresent() ? optionalindi.get() : null);
                if (!optionalindi.isPresent()
                        || (indopt != null && (indopt.getId() == Long.parseLong(String.valueOf(indId))))) {
                    //inup.setSectionId(req.getSectionId());
                    inup.setIndctrName(req.getIndctrName());
//					inup.setIndctrType(indiupdatemodel.getIndctrType());
                    //inup.setSequence(req.getSequence());
                    inup.setStartMonth(req.getStartMonth());
                    inup.setEndMonth(req.getEndMonth());
                    inup.setStartFnclYear(req.getStartFnclYear());
                    inup.setEndFnclYear(req.getEndFnclYear());
                    inup.setDescription(req.getDescription());
                    inup.setMandatory(req.getMandatory());
                    inup.setMinValue(req.getMinValue());
                    inup.setMaxValue(req.getMaxValue());
                    inup.setFreezeFlag(req.getFreezeFlag());
                    inup.setUpdatedBy(loginId);
                    inup.setUpdatedAt(new Date());
                    
                    if(req.getStatus() != null) {
                    	
                    	if(req.getStatus() == ProjectConstants.ACTIVE_STATUS) {
                    		
                    		inup.setStatus(ProjectConstants.ACTIVE_STATUS);
                    	}
                    	if(req.getStatus() == ProjectConstants.INACTIVE_STATUS) {
                    		
                    		inup.setStatus(ProjectConstants.INACTIVE_STATUS);
                    	}
                    	
                    }
                    else {
                    	inup.setStatus(ProjectConstants.ACTIVE_STATUS);
                    }
                    
                    Indicator updatedInd = indicatorRepository.save(inup);
                    if (updatedInd.getId() != 0 && (updatedInd.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN 
                    	//|| updatedInd.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO 
                    	|| updatedInd.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN)) {
                    	i=0;
                        for (AnswerOptionDataRequest ansReq : req.getAnsweroptionData()) {
                           i++;
                        	Optional<AnswerOptionEntity> ansup = ansOpRepository.findFirstBykeyandtableanoptionid(
                        			ProjectConstants.CUT_OFF, ProjectConstants.CUT_OFF_SHORT_KEY + updatedInd.getId(), ansReq.getOptionId());
                            AnswerOptionEntity ansnewup = null;
                            if (ansup.isPresent()) {
                                ansnewup = ansup.get();
                            } else {
                                ansnewup = new AnswerOptionEntity();
                            }
                            ansnewup.setTableName(ProjectConstants.CUT_OFF);
                            ansnewup.setShortKey(ProjectConstants.CUT_OFF_SHORT_KEY + updatedInd.getId());
                            ansnewup.setOptionId(ansReq.getOptionId());
                            ansnewup.setOptionValue(ansReq.getOptionValue());
                            
                            if(ansReq.getSeq() != null) {
                            	 ansnewup.setSequence(ansReq.getSeq());
                            }
                            else {
                            	ansnewup.setSequence(i);
                            }
                           
                            if(ansReq.getStatus() != null) {
                            	if(ansReq.getStatus() == ProjectConstants.ACTIVE_STATUS) {
                            		ansnewup.setStatus(ProjectConstants.ACTIVE_STATUS);
                            	}
                            	else {
                            		ansnewup.setStatus(ProjectConstants.INACTIVE_STATUS);
                            	}
                            }
                            	
                            else {
                            	ansnewup.setStatus(ProjectConstants.ACTIVE_STATUS);
                            }
                            
                            ansOpRepository.save(ansnewup);
                        }
                    }
                    response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                    response.setResponseDesc(HttpStatus.OK.name());
                    return response;
                } else {
                    throw new HandledException(HttpStatus.BAD_REQUEST, "Indicator Already exist with this name");
                }

            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No indicator Found with given value");
            }
        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Indicator Not Updated");
        }

    }

    @Override
    public Response<String> deleteIndicator(Long loginId, Long indiId) {

        Response<String> response = new Response<String>();
        try {
            Optional<Indicator> optionalindi = indicatorRepository.findById(Integer.parseInt("" + indiId));
            if (optionalindi.isPresent()) {
                Indicator indi = optionalindi.get();
//				clfp.setUpdatedAt(new Date());
                indi.setUpdatedBy(loginId);
                indi.setStatus(ProjectConstants.INACTIVE_STATUS);
                indicatorRepository.save(indi);

                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Indicator Found with given value");
            }
        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Indicator Not Deleted");
        }
    }

    @Override
    public Response<AnswerOptionResponse> getIndicatorTabResponseList() {
        Response<AnswerOptionResponse> response = new Response<AnswerOptionResponse>();
        List<AnswerOptionResponse> wrappedList = new ArrayList<AnswerOptionResponse>();
        AnswerOptionResponse typeDto = null;
        try {
            List<AnswerOptionEntity> indicatorTypeList = indicatorRepository.getAnswerOptionMasterList(
                    ProjectConstants.INDICATOR_TABLE, ProjectConstants.INDICATOR_COL_SHORT_KEY);
            if (indicatorTypeList == null || indicatorTypeList.size() == 0) {
                throw new HandledException(HttpStatus.NOT_FOUND, "No Type Found");
            } else {
                for (AnswerOptionEntity type : indicatorTypeList) {
                    typeDto = new AnswerOptionResponse();
                    typeDto.setOptionId(type.getOptionId());
                    typeDto.setOptionName(type.getOptionValue());
                    typeDto.setIsDisable("N");
                    wrappedList.add(typeDto);
                }
                response.setWrapperList(wrappedList);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
            }

            return response;
        } catch (HandledException e) {
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

    }

	@Override
	public Response<IndicatorSequenceResponse> getIndctrSequenceList(HttpServletRequest request) {
		try {
			Response<IndicatorSequenceResponse> response = new Response<IndicatorSequenceResponse>();
			List<IndicatorSequenceResponse> wrappedList = new ArrayList<IndicatorSequenceResponse>();
			Long totalCount = 0L;
			String errorMsg = null;
			List<Indicator> indicatorlist = indicatorRepository.findAll(
                    indicatorSpecification.getIndicatorSequenceList(Long.parseLong(request.getParameter("sectionId"))));
            
			if(indicatorlist != null && indicatorlist.size() != 0) {
				wrappedList = indicatorlist.stream().map(indctr -> new IndicatorSequenceResponse(Long.valueOf(indctr.getId()),indctr.getIndctrName(),indctr.getSequence()))
						.collect(Collectors.toList());
				totalCount = Long.parseLong(String.valueOf(indicatorlist.size()));
			}
			
			
			response.setTotalcount(totalCount);
           
			response.setWrapperList(wrappedList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			response.setTotalcount(totalCount);
			response.setErrorMsg(errorMsg);
			return response;
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public Response<String> updateIndicatorSequenceList(Long userId, List<IndicatorSequenceRequest> request) {
		Response<String> response = new Response<String>();
		try {
			if(request != null && request.size() != 0) {
				
				for(IndicatorSequenceRequest req : request) {
					
					Optional<Indicator> optionalIndctr= indicatorRepository.findById(Integer.valueOf(req.getIndctrId().toString()));
					
					if (optionalIndctr.isPresent()) {
						Indicator indctr = optionalIndctr.get();
						indctr.setSequence(req.getSequence());
						indctr.setUpdatedAt(new Date());
						indctr.setUpdatedBy(userId);
						
						indicatorRepository.save(indctr);
						
					}
				}
				
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			}
			
			else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Indicator Request");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Sequence Not Updated");
		}

	}

}
