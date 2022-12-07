package com.nrlm.mclfmis.Services;

import java.util.*;
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

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMapping;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlySkipMapping;
import com.nrlm.mclfmis.Entity.ClfCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Entity.CutOffMonthlySkipMapping;
import com.nrlm.mclfmis.Entity.CutOffSkipMapping;
import com.nrlm.mclfmis.Entity.Financial;
import com.nrlm.mclfmis.Entity.GpCutOffEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMapping;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlySkipMapping;
import com.nrlm.mclfmis.Entity.GpCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.Grampanchayat;
import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Entity.VoCutOffEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMapping;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyMapping;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlySkipMapping;
import com.nrlm.mclfmis.Entity.VoCutOffSkipMapping;
import com.nrlm.mclfmis.Entity.VoProfile;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfCutOffMappingRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlyMappingRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlyRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlySkipMappingRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffSkipMappingRepository;
import com.nrlm.mclfmis.Repository.ClfRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMappingRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMonthlyMappingRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMonthlyRepository;
import com.nrlm.mclfmis.Repository.GpCutOffMonthlySkipMappingRepository;
import com.nrlm.mclfmis.Repository.GpCutOffRepository;
import com.nrlm.mclfmis.Repository.GpCutOffSkipMappingRepository;
import com.nrlm.mclfmis.Repository.GpRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMappingRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMonthlyMappingRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMonthlyRepository;
import com.nrlm.mclfmis.Repository.VoCutOffMonthlySkipMappingRepository;
import com.nrlm.mclfmis.Repository.VoCutOffRepository;
import com.nrlm.mclfmis.Repository.VoCutOffSkipMappingRepository;
import com.nrlm.mclfmis.Repository.VoRepository;
import com.nrlm.mclfmis.Request.CutOffDataRequest;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Request.CutOffMonthlyFormRequest;
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
import com.nrlm.mclfmis.customEntity.VoCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.IndicatorCustomEntity;

import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustomImpl;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.usermanagement.response.ValidateTokenResponse;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CutOffMonthlyServiceImpl implements CutOffMonthlyService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ClfCutOffMonthlyRepository clfCutOffMonthRepository;

	@Autowired
	VoCutOffMonthlyRepository voCutOffMonthRepository;

	@Autowired
	GpCutOffMonthlyRepository gpCutOffMonthRepository;

	@Autowired
	ClfRepository clfRepository;
	
	@Autowired
	VoRepository voRepository;
	
	@Autowired
	GpRepository gpRepository;
	
	@Autowired
	LocationService locationService;

	@Autowired
	private PageModel pageModel;

	@Autowired
	ClfCutOffMonthlyRepositoryCustomImpl customCutOffMonthlyRepository;

	@Autowired
	ClfCutOffMonthlyMappingRepository clfCutOffMonthlyMappingRepository;

	@Autowired
	VoCutOffMonthlyMappingRepository voCutOffMonthlyMappingRepository;

	@Autowired
	GpCutOffMonthlyMappingRepository gpCutOffMonthlyMappingRepository;
	
	@Autowired
	CustomCutOffRepository customCutOffRepository;
	
	@Autowired
    ClfCutOffMonthlySkipMappingRepository clfCutOffMonthlySkipRepository;
    
    @Autowired
    VoCutOffMonthlySkipMappingRepository voCutOffMonthlySkipRepository;
    
    @Autowired
    GpCutOffMonthlySkipMappingRepository gpCutOffMonthlySkipRepository;
	

	// Monthly CLF List

	@Override
	public Response<ClfCutOffResposne> getClfCutOffMonthlyList(CutOffFilterRequest filterDto) {

		Response<ClfCutOffResposne> response = new Response<ClfCutOffResposne>();
		List<ClfCutOffResposne> wrappedList = new ArrayList<ClfCutOffResposne>();
		// PageModel.setSIZE(20);
		// pageModel.initPageAndSize();
		pageModel.initOffsetAndLimit();
		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			Long userId = 0L;
			String levelId = null;

			if (authUser != null && authUser.getUserId() != null) {
				userId = authUser.getUserId();
				levelId = authUser.getLevelName();

			}
			List<String> locations = new ArrayList<String>();

			if (userId == 0 || levelId == null) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelId);
			Page<ClfCustomCutOffEntity> clfCutOffList = clfCutOffMonthRepository.getClfCutOff(filterDto, locations,
					levelId, pageModel, PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
			if (clfCutOffList == null || clfCutOffList.getSize() == 0) {
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				response.setErrorMsg("No CLF monthly list Found");
				return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Clf  Found");
			} else {
//				wrappedList = clfCutOffList.stream().map(cutoff -> modelMapper.map(cutoff, ClfCutOffResposne.class))
//						.collect(Collectors.toList());

				wrappedList = clfCutOffList.stream().map(cutoff -> {
					return new ClfCutOffResposne(cutoff.getId(), cutoff.getClfCode(), cutoff.getStateName(),
							cutoff.getDistrictName(), cutoff.getBlockName(), cutoff.getClfName(),
							cutoff.getReportMonth(), cutoff.getOverallStatus(), cutoff.getClfStatus(),
							cutoff.getRegistrationDate(), cutoff.getBlockCode(),cutoff.getCutOffYear(), cutoff.getCutOffMonth());
				}).collect(Collectors.toList());
				response.setWrapperList(wrappedList);
				response.setTotalcount(clfCutOffList.getTotalElements());
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

	// Monthly VO List

	@Override
	public Response<VoCutOffResponse> getVoCutOffMonthlyList(CutOffFilterRequest filterDto) {

		Response<VoCutOffResponse> response = new Response<VoCutOffResponse>();
		List<VoCutOffResponse> wrappedList = new ArrayList<VoCutOffResponse>();
		pageModel.initOffsetAndLimit();
		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelId = null;

			if (authUser != null && authUser.getUserId() != null) {
				userId = authUser.getUserId();
				levelId = authUser.getLevelName();

			}
			List<String> locations = new ArrayList<String>();
			if (userId == 0 || levelId == null) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelId);
			Page<VoCustomCutOffEntity> voCutOffList = clfCutOffMonthRepository.getVoCutOff(filterDto, locations,
					levelId, pageModel, PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
			if (voCutOffList == null || voCutOffList.getSize() == 0) {
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				response.setErrorMsg("No VO monthly list Found");
				return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Vo  Found");
			} else {
				wrappedList = voCutOffList.stream().map(cutoff -> modelMapper.map(cutoff, VoCutOffResponse.class))
						.collect(Collectors.toList());
				response.setWrapperList(wrappedList);
				response.setTotalcount(voCutOffList.getTotalElements());
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

	// Monthly GP List

	@Override
	public Response<GpCutOffResponse> getGpCutOffMonthlyList(CutOffFilterRequest filterDto) {

		Response<GpCutOffResponse> response = new Response<GpCutOffResponse>();
		List<GpCutOffResponse> wrappedList = new ArrayList<GpCutOffResponse>();
		pageModel.initOffsetAndLimit();
		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelId = null;

			if (authUser != null && authUser.getUserId() != null) {
				userId = authUser.getUserId();
				levelId = authUser.getLevelName();

			}
			List<String> locations = new ArrayList<String>();
			if (userId == 0 || levelId == null) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelId);
			/*
			 * if(levelId != ProjectConstants.ADMIN) { locations =
			 * locationService.getLocations(userId,levelId); }
			 */

			Page<GpCustomCutOffEntity> gpCutOffList = clfCutOffMonthRepository.getGpCutOff(filterDto, locations,
					levelId, pageModel, PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
			if (gpCutOffList == null || gpCutOffList.getSize() == 0) {
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				response.setErrorMsg("No Gp monthly list Found");
				return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Gp  Found");
			} else {
				wrappedList = gpCutOffList.stream().map(cutoff -> modelMapper.map(cutoff, GpCutOffResponse.class))
						.collect(Collectors.toList());
				response.setWrapperList(wrappedList);
				response.setTotalcount(gpCutOffList.getTotalElements());
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

// save Monthly Data

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Response<String> saveCutOffMonthlyData(CutOffFormRequest request) {
		Response<String> response = new Response<String>();
		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelId = null;

			if (authUser != null && authUser.getUserId() != null) {
				userId = authUser.getUserId();
				levelId = authUser.getLevelName();

			}

			if (userId == 0 || levelId == null) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			boolean exist = false;
			boolean isUpdate = false;
			Integer tabId = request.getTabId();
			
			if(request.getClfCode() == null || request.getClfCode().isEmpty()) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
			}
			/*else if(request.getCutOffData() == null || request.getCutOffData().size() == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No CutOff Indicator Found");
			}*/

			
			if (tabId.equals(ProjectConstants.TAB_CLF)) {
				ClfCutOffMonthlyEntity clfCutOff = clfCutOffMonthRepository.checkCutOffExistByClfCodeWithMonthYear(
						request.getClfCode(), request.getReportMonth(), request.getReportYear());

				if (clfCutOff != null) {
					
					if (clfCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
						exist = true;
					}
					else {
						isUpdate = true;
					}
				}

				

				if (exist) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
				}
				List<ClfMasterEntity> clfList = clfRepository.findByClfcode(request.getClfCode());
				if(clfList == null || clfList.size() == 0) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
				}
				
				request = validateCutOffMonthlyData(request);
				
				ClfCutOffMonthlyEntity cutoff = null;
				
				if (isUpdate) {
					cutoff = clfCutOff;
				} 
				else {
					cutoff = new ClfCutOffMonthlyEntity();
					cutoff.setCreatedBy(userId);
					cutoff.setCreatedAt(new Date());
				}
				cutoff.setClfCode(request.getClfCode());
				cutoff.setCutOffMonth(request.getReportMonth());
				cutoff.setCutOffYear(request.getReportYear());
				cutoff.setUpdatedAt(new Date());
				cutoff.setUpdatedBy(userId);
				if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
					cutoff.setCutOffStatus(ProjectConstants.IN_PROGRESS);
					cutoff.setOverallStatus(ProjectConstants.IN_PROGRESS);
				}
				else {
					cutoff.setCutOffStatus(ProjectConstants.COMPLETED);
					cutoff.setOverallStatus(this.getOverAllStatus(request.getClfCode(),request.getReportYear(),request.getReportMonth()));
				}
				ClfCutOffMonthlyEntity clfCutoff1 = clfCutOffMonthRepository.save(cutoff);
				
				if (clfCutoff1.getId() != 0) {
					
					
                	
					request = processCutOffData(request.getTabId(),request,clfCutoff1.getId());
					
					request = processSkipLogic(request,clfCutoff1.getId());	
				
					/*for (CutOffDataRequest indReq : request.getCutOffData()) {
						Optional<ClfCutOffMonthlyMapping> optionalInd = null;
						ClfCutOffMonthlyMapping ind = null;
						optionalInd = clfCutOffMonthlyMappingRepository.findByCutOffIdAndIndctrId(clfCutoff1.getId(),
								indReq.getIndctrId());
						if (optionalInd.isPresent()) {
							ind = optionalInd.get();
						} else {
							ind = new ClfCutOffMonthlyMapping();
						}

						ind.setCutOffId(clfCutoff1.getId());
						ind.setIndctrId(indReq.getIndctrId());
						ind.setIndctrValue(indReq.getIndctrValue());
						ind.setOptionId(indReq.getOptionId());
						clfCutOffMonthlyMappingRepository.save(ind);
					}*/
					if(request.getErrorMsg() != null && !request.getErrorMsg().isEmpty()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, request.getErrorMsg());
					}
					
				}
			

			} else if (tabId.equals(ProjectConstants.TAB_VO)) {

				if(request.getVoCode() == null || request.getVoCode().isEmpty()) {
					throw new HandledException(HttpStatus.BAD_REQUEST, "Vo Code is mandatory");
				}
				Optional<VoProfile> optionalVo = voRepository.findByVoCode(request.getVoCode());
				if(!optionalVo.isPresent()) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Vo Exist");
				}
				
				VoCutOffMonthlyEntity voCutOff = clfCutOffMonthRepository.checkCutOffExistByVoCodeWithMonthYear(
						request.getVoCode(), request.getReportMonth(), request.getReportYear());

				if (voCutOff != null) {

					if (voCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
						exist = true;
					}
					else {
						isUpdate = true;
					}
				}

				

				if (exist) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
				} 
				
				request = validateCutOffMonthlyData(request);
				
				VoCutOffMonthlyEntity cutoff = null;
				
				if (isUpdate) {
					cutoff = voCutOff;
				}
				else {
					cutoff = new VoCutOffMonthlyEntity();
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
				VoCutOffMonthlyEntity voCutoff1 = voCutOffMonthRepository.save(cutoff);

				if (voCutoff1.getVoCutOffId() != 0) {
					
					request = processCutOffData(request.getTabId(),request,voCutoff1.getVoCutOffId());
					
					request = processSkipLogic(request,voCutoff1.getVoCutOffId());	
//					for (CutOffDataRequest indReq : request.getCutOffData()) {
//
//						Optional<VoCutOffMonthlyMapping> optionalInd = null;
//						VoCutOffMonthlyMapping ind = null;
//						optionalInd = voCutOffMonthlyMappingRepository
//								.findByCutOffIdAndIndctrId(voCutoff1.getVoCutOffId(), indReq.getIndctrId());
//						if (optionalInd.isPresent()) {
//							ind = optionalInd.get();
//						} else {
//							ind = new VoCutOffMonthlyMapping();
//						}
//						ind.setCutOffId(voCutoff1.getVoCutOffId());
//						ind.setIndctrId(indReq.getIndctrId());
//						ind.setIndctrValue(indReq.getIndctrValue());
//						ind.setOptionId(indReq.getOptionId());
//						voCutOffMonthlyMappingRepository.save(ind);
//					}
					if(request.getErrorMsg() != null && !request.getErrorMsg().isEmpty()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, request.getErrorMsg());
					}
					
					Integer overallStatus = null;
					if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
						
						overallStatus = ProjectConstants.IN_PROGRESS;
					} 
					else {
						overallStatus = this.getOverAllStatus(request.getClfCode(),request.getReportYear(),request.getReportMonth());
					}
					
					if(overallStatus != null) {
						
						this.saveUpdateOverallStatus(request.getClfCode(),request.getReportYear(),request.getReportMonth(), overallStatus);
					}
				}

			} else if (tabId.equals(ProjectConstants.TAB_GP)) {

				if(request.getGrampanchayatCode() == null || request.getGrampanchayatCode().isEmpty()) {
					throw new HandledException(HttpStatus.BAD_REQUEST, "Gp Code is mandatory");
				}
				Optional<Grampanchayat> optionalGp = gpRepository.findByGrampanchayatCode(request.getGrampanchayatCode());
				if(!optionalGp.isPresent()) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Grampanchayat Exist");
				}
				
				
				GpCutOffMonthlyEntity gpCutOff = gpCutOffMonthRepository.checkCutOffExistByGpCodeWithMonthYear(
						request.getGrampanchayatCode(), request.getReportMonth(), request.getReportYear());

				if (gpCutOff != null) {

					if (gpCutOff.getCutOffStatus() == ProjectConstants.COMPLETED) {
						exist = true;
					}
					else {
						isUpdate = true;
					}
				}

				if (exist) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
				} 
				
				request = validateCutOffMonthlyData(request);
				
				GpCutOffMonthlyEntity cutoff = null;
				
				if (isUpdate) {
					cutoff = gpCutOff;
				} 
				else {
					cutoff = new GpCutOffMonthlyEntity();
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
				GpCutOffMonthlyEntity gpCutoff1 = gpCutOffMonthRepository.save(cutoff);
				if (gpCutoff1.getId() != 0) {
					
					request = processCutOffData(request.getTabId(),request,gpCutoff1.getId());
					
					request = processSkipLogic(request,gpCutoff1.getId());	
					
//					for (CutOffDataRequest indReq : request.getCutOffData()) {
//						Optional<GpCutOffMonthlyMapping> optionalInd = null;
//						GpCutOffMonthlyMapping ind = null;
//						optionalInd = gpCutOffMonthlyMappingRepository.findByCutOffIdAndIndctrId(gpCutoff1.getId(),
//								indReq.getIndctrId());
//						if (optionalInd.isPresent()) {
//							ind = optionalInd.get();
//						} else {
//							ind = new GpCutOffMonthlyMapping();
//						}
//
//						ind.setCutOffId(gpCutoff1.getId());
//						ind.setIndctrId(indReq.getIndctrId());
//						ind.setIndctrValue(indReq.getIndctrValue());
//						ind.setOptionId(indReq.getOptionId());
//						gpCutOffMonthlyMappingRepository.save(ind);
//					}
					
					if(request.getErrorMsg() != null && !request.getErrorMsg().isEmpty()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, request.getErrorMsg());
					}
					
					Integer overallStatus = null;
					if (request.getIsDraft() == ProjectConstants.IS_DRAFT) {
						overallStatus = ProjectConstants.IN_PROGRESS;
					} else {
						overallStatus = this.getOverAllStatus(request.getClfCode(),request.getReportYear(),request.getReportMonth());
					}
					
					if(overallStatus !=  null) {
						saveUpdateOverallStatus(request.getClfCode(),request.getReportYear(),request.getReportMonth(),overallStatus);
					}
				}
			} else {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab Found");
			}
			
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
		// return null;
	}

	private CutOffFormRequest processCutOffData(Integer tabId, CutOffFormRequest request, Long cutOffId) { 
    	try {
    		for (CutOffDataRequest indReq : request.getCutOffData()) {

    			if(request.getSkipList() == null || !request.getSkipList().contains(indReq.getSectionId())) {
                 
    				if(tabId == ProjectConstants.TAB_CLF) {

                	   ClfCutOffMonthlyMapping ind = null;
                    	Optional<ClfCutOffMonthlyMapping> optionalInd = null;
                    	
                       if (indReq.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                    	   clfCutOffMonthlyMappingRepository.deleteAllByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                           for (Integer optionid : indReq.getMultiselectOptionValue()) {
                               ind = new ClfCutOffMonthlyMapping();
                               indReq.setIndctrValue(null);
                               this.saveIndicatorMapping(ind, indReq, cutOffId, optionid);
                           }
                       }
                       else {
                           optionalInd = clfCutOffMonthlyMappingRepository.findByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                           if (optionalInd.isPresent()) {
                               ind = optionalInd.get();
                           } 
                           else {
                               ind = new ClfCutOffMonthlyMapping();
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
                		
                		VoCutOffMonthlyMapping ind = null;
                    	Optional<VoCutOffMonthlyMapping> optionalInd = null;

                        if (indReq.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                        	voCutOffMonthlyMappingRepository.deleteAllByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            for (Integer optionid : indReq.getMultiselectOptionValue()) {
                                ind = new VoCutOffMonthlyMapping();
                                indReq.setIndctrValue(null);
                                this.saveIndicatorMapping(ind, indReq, cutOffId, optionid);
                            }
                        }
                        else {
                        	optionalInd = voCutOffMonthlyMappingRepository.findByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            if (optionalInd.isPresent()) {
                                ind = optionalInd.get();
                            } 
                            else {
                                ind = new VoCutOffMonthlyMapping();
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
                		
                		GpCutOffMonthlyMapping ind = null;
                    	Optional<GpCutOffMonthlyMapping> optionalInd = null;

                    	
                        if (indReq.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN) {
                            gpCutOffMonthlyMappingRepository.deleteAllByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            for (Integer optionid : indReq.getMultiselectOptionValue()) {
                                ind = new GpCutOffMonthlyMapping();
                                indReq.setIndctrValue(null);
                                this.saveIndicatorMapping(ind, indReq, cutOffId, optionid);
                            }
                        }
                        else {
                            optionalInd = gpCutOffMonthlyMappingRepository.findByCutOffIdAndIndctrId(cutOffId,indReq.getIndctrId());
                            if (optionalInd.isPresent()) {
                                ind = optionalInd.get();
                            } 
                            else {
                                ind = new GpCutOffMonthlyMapping();
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

	private void saveIndicatorMapping(Object model, CutOffDataRequest request, Long cutoffid,
			Integer optionid) {

      if (model instanceof ClfCutOffMonthlyMapping) {
    	  ClfCutOffMonthlyMapping clfcutoffMapping = (ClfCutOffMonthlyMapping) model;
          clfcutoffMapping.setCutOffId(cutoffid);
          clfcutoffMapping.setIndctrId(request.getIndctrId());
          clfcutoffMapping.setIndctrValue(request.getIndctrValue());
          clfcutoffMapping.setOptionId(optionid);
          clfcutoffMapping.setSectionId(request.getSectionId());
          clfCutOffMonthlyMappingRepository.save(clfcutoffMapping);
      }
      if (model instanceof VoCutOffMonthlyMapping) {
    	  VoCutOffMonthlyMapping vocutoffMapping = (VoCutOffMonthlyMapping) model;
          vocutoffMapping.setCutOffId(cutoffid);
          vocutoffMapping.setIndctrId(request.getIndctrId());
          vocutoffMapping.setIndctrValue(request.getIndctrValue());
          vocutoffMapping.setOptionId(optionid);
          vocutoffMapping.setSectionId(request.getSectionId());
          voCutOffMonthlyMappingRepository.save(vocutoffMapping);
      }
      if (model instanceof GpCutOffMonthlyMapping) {
    	  GpCutOffMonthlyMapping gpcutoffMapping = (GpCutOffMonthlyMapping) model;
          gpcutoffMapping.setCutOffId(cutoffid);
          gpcutoffMapping.setIndctrId(request.getIndctrId());
          gpcutoffMapping.setIndctrValue(request.getIndctrValue());
          gpcutoffMapping.setOptionId(optionid);
          gpcutoffMapping.setSectionId(request.getSectionId());
          gpCutOffMonthlyMappingRepository.save(gpcutoffMapping);
      }
  }

	private CutOffFormRequest processSkipLogic(CutOffFormRequest request, Long cutOffId) {
    	try {
	    	Integer tabId = request.getTabId();
	    	
	    	Optional<CutOffMonthlySkipMapping> cutOffSkipOptional = null;
	    	CutOffMonthlySkipMapping cutOffSkip = null;
	    	
	    	for(Long skip  : request.getSkipList()) {
	    		
	    		if(tabId == ProjectConstants.TAB_CLF) {
		    		cutOffSkipOptional  = 	clfCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(cutOffId, skip);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		cutOffSkipOptional = 	voCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(cutOffId, skip);		
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		cutOffSkipOptional = 	gpCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(cutOffId, skip);
				}
	    		
	    		if(cutOffSkipOptional.isPresent()) {
	    			cutOffSkip = cutOffSkipOptional.get();
	    		}
	    		else {
	    		
	    			if(tabId == ProjectConstants.TAB_CLF) {
		     			cutOffSkip = new ClfCutOffMonthlySkipMapping();
		        	}
		        	else if(tabId == ProjectConstants.TAB_VO) {
		        		cutOffSkip = new VoCutOffMonthlySkipMapping();
		    		}
		        	else if(tabId == ProjectConstants.TAB_GP) {
		        		cutOffSkip = new GpCutOffMonthlySkipMapping();
		    		}
	    			
	    		}
	    		cutOffSkip.setCutOffId(cutOffId);
		     	cutOffSkip.setSectionId(skip);
		     	cutOffSkip.setSkipValue(ProjectConstants.SKIP_APPLIED);
		     	cutOffSkip.setSectionStatus(ProjectConstants.IN_PROGRESS);
		     	
		     	if(tabId == ProjectConstants.TAB_CLF) {
		     		
		 			clfCutOffMonthlySkipRepository.save((ClfCutOffMonthlySkipMapping)cutOffSkip);
		 	     	clfCutOffMonthlyMappingRepository.deleteAllByCutOffIdAndSectionId(cutOffId,skip);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		
		    		voCutOffMonthlySkipRepository.save((VoCutOffMonthlySkipMapping)cutOffSkip);
		 	     	voCutOffMonthlyMappingRepository.deleteAllByCutOffIdAndSectionId(cutOffId,skip);
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		
		    		gpCutOffMonthlySkipRepository.save((GpCutOffMonthlySkipMapping)cutOffSkip);
		    		gpCutOffMonthlyMappingRepository.deleteAllByCutOffIdAndSectionId(cutOffId,skip);
				}
	    	}
	    	 	
	    	for(Long filledSection  : request.getFilledSection()) {
	    		
	    		if(tabId == ProjectConstants.TAB_CLF) {
		    		cutOffSkipOptional  = 	clfCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(cutOffId, filledSection);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		cutOffSkipOptional = 	voCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(cutOffId, filledSection);		
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		cutOffSkipOptional = 	gpCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(cutOffId, filledSection);
				}
	    		
	    		if(cutOffSkipOptional.isPresent()) {
	    			cutOffSkip = cutOffSkipOptional.get();
	    		}
	    		else {
	    		
	    			if(tabId == ProjectConstants.TAB_CLF) {
		     			cutOffSkip = new ClfCutOffMonthlySkipMapping();
		        	}
		        	else if(tabId == ProjectConstants.TAB_VO) {
		        		cutOffSkip = new VoCutOffMonthlySkipMapping();
		    		}
		        	else if(tabId == ProjectConstants.TAB_GP) {
		        		cutOffSkip = new GpCutOffMonthlySkipMapping();
		    		}
	    			
	    		}
	    		cutOffSkip.setCutOffId(cutOffId);
		     	cutOffSkip.setSectionId(filledSection);
		     	cutOffSkip.setSkipValue(ProjectConstants.SKIP_NOT_APPLIED);
		     	cutOffSkip.setSectionStatus(customCutOffMonthlyRepository.getCutOffSectionStatusByCutOffIdAndSectionId(tabId, cutOffId, filledSection));
		     	
		     	
		     	if(tabId == ProjectConstants.TAB_CLF) {
		 			clfCutOffMonthlySkipRepository.save((ClfCutOffMonthlySkipMapping)cutOffSkip);
		    	}
		    	else if(tabId == ProjectConstants.TAB_VO) {
		    		voCutOffMonthlySkipRepository.save((VoCutOffMonthlySkipMapping)cutOffSkip);
				}
		    	else if(tabId == ProjectConstants.TAB_GP) {
		    		gpCutOffMonthlySkipRepository.save((GpCutOffMonthlySkipMapping)cutOffSkip);
				}
	    	}
    	}
    	catch(Exception e) {
    		request.setErrorMsg("Error in Processing Skip");
    	}
    	return request;
    }

	private void saveUpdateOverallStatus(String clfCode,Integer yearId,Integer monthId, Integer overallStatus) {
		ClfCutOffMonthlyEntity clfCutoff= customCutOffMonthlyRepository.checkCutOffExistByClfCodeWithMonthYear(clfCode,monthId,yearId);
	
		ClfCutOffMonthlyEntity clfcutOffEntity = null;
		if(clfCutoff != null) {
			clfcutOffEntity = clfCutoff;
			clfcutOffEntity.setOverallStatus(overallStatus);
			clfCutOffMonthRepository.save(clfcutOffEntity);
		}
		
		
	}

	private CutOffFormRequest validateCutOffMonthlyData(CutOffFormRequest request) {
		try {
			
			String errorMsg = "";
			
			if(request.getReportYear() == null) {
				request.setReportYear(0);
			}
			if(request.getReportMonth() == null) {
				request.setReportMonth(0);
			}
			
			ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(request.getClfCode());
			
			if (clfCutOff == null || (clfCutOff.getOverallStatus() != ProjectConstants.COMPLETED)) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Cut Off is Pending");
			}
			else {
				if(request.getReportYear() < clfCutOff.getCutOffYear()) {
					if(request.getIsDraft() != null && request.getIsDraft() == 0) {
						errorMsg = "Invalid year. Data is saved as draft";
					}
					request.setIsDraft(ProjectConstants.IS_DRAFT);
				}
				else if(request.getReportYear() ==  clfCutOff.getCutOffYear()) {
					if(request.getReportMonth() <= clfCutOff.getCutOffMonth()) {
						if(request.getIsDraft() != null && request.getIsDraft() == 0) {
							errorMsg = "Invalid month. Data is saved as draft";
						}
						request.setIsDraft(ProjectConstants.IS_DRAFT);	
					}
					
				}
			}
			
			request.setErrorMsg(errorMsg);
			return request;
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
	}

	// ** Month and Year save Api
	@Override
	public Response<String> saveFnclYearAndMonth(CutOffMonthlyFormRequest request) {
		Response<String> response = new Response<String>();
		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			Long userId = 0L;
			String levelId = null;
			if (authUser != null && authUser.getUserId() != null) {
				userId = authUser.getUserId();
				levelId = authUser.getLevelName();

			}
			if (userId == 0 || levelId == null) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			boolean exist = false;
			ClfCutOffMonthlyEntity clfmonthlyCutOff = clfCutOffMonthRepository.checkCutOffExistByClfCodeWithMonthYear(
					request.getClfCode(), request.getReportMonth(), request.getReportYear());
			if (clfmonthlyCutOff != null && clfmonthlyCutOff.getId() != 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
			} 
			else {
				CutOffFormRequest cutOffFormRequest = new CutOffFormRequest(ProjectConstants.TAB_CLF,request.getClfCode(),null,null,0,
						request.getReportYear(),request.getReportMonth(),null,null,null);
				
				cutOffFormRequest = validateCutOffMonthlyData(cutOffFormRequest);
			
				if(cutOffFormRequest.getErrorMsg() == null || cutOffFormRequest.getErrorMsg().isEmpty()) {
					
					saveClfCutOffMonthly(cutOffFormRequest,userId);
					saveVoCutOffMonthly(cutOffFormRequest,userId);
					saveGpCutOffMonthly(cutOffFormRequest,userId);
					
					
					
					response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
					response.setResponseDesc(HttpStatus.OK.name());
					return response;
				}
				else {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, cutOffFormRequest.getErrorMsg()); 
				}
			}
		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}
	private void saveGpCutOffMonthly(CutOffFormRequest request,Long userId) {
		
		List<String> gpCodeList = clfCutOffMonthRepository.getClfGpMapping(request.getClfCode());
		
		for(String gpCode : gpCodeList) {
			
			GpCutOffMonthlyEntity gpcutoffmonthly = clfCutOffMonthRepository.checkCutOffExistByGpCodeWithMonthYear(gpCode, request.getReportYear(), request.getReportMonth());
		
			GpCutOffMonthlyEntity gp = null;
			if(gpcutoffmonthly != null) {
				
				gp = gpcutoffmonthly;
			}
			else {
				gp = new GpCutOffMonthlyEntity();
				gp.setCreatedAt(new Date());
				gp.setCreatedBy(userId);
			}
			
			gp.setGrampanchayatCode(gpCode);
			gp.setCutOffMonth(request.getReportMonth());
			gp.setCutOffYear(request.getReportYear());
			gp.setUpdatedAt(new Date());
			gp.setUpdatedBy(userId);
			gp.setCutOffStatus(ProjectConstants.PENDING);
			gpCutOffMonthRepository.save(gp);
		}
		
	}

	private void saveVoCutOffMonthly(CutOffFormRequest request,Long userId) {
		
		List<String> voCodeList = clfCutOffMonthRepository.getClfVoMapping(request.getClfCode());
		
		for(String voCode : voCodeList) {
			VoCutOffMonthlyEntity vocutoffmonthly = clfCutOffMonthRepository.checkCutOffExistByVoCodeWithMonthYear(voCode, request.getReportYear(), request.getReportMonth());
			
			VoCutOffMonthlyEntity vo = null;
			if(vocutoffmonthly != null) {
				
				vo = vocutoffmonthly;
			}
			else {
				vo = new VoCutOffMonthlyEntity();
				vo.setCreatedAt(new Date());
				vo.setCreatedBy(userId);
			}
			
			vo.setVoCode(voCode);
			vo.setCutOffMonth(request.getReportMonth());
			vo.setCutOffYear(request.getReportYear());
			vo.setUpdatedAt(new Date());
			vo.setUpdatedBy(userId);
			vo.setCutOffStatus(ProjectConstants.PENDING);
			
			
			voCutOffMonthRepository.save(vo);
		}
	}

	private void saveClfCutOffMonthly(CutOffFormRequest request,Long userId) {
		ClfCutOffMonthlyEntity clf = new ClfCutOffMonthlyEntity();
		clf.setClfCode(request.getClfCode());
		clf.setCreatedAt(new Date());
		clf.setCreatedBy(userId);
		clf.setCutOffMonth(request.getReportMonth());
		clf.setCutOffYear(request.getReportYear());
		clf.setUpdatedAt(new Date());
		clf.setUpdatedBy(userId);
		clf.setCutOffStatus(ProjectConstants.IN_PROGRESS);
		clf.setOverallStatus(ProjectConstants.IN_PROGRESS);
		ClfCutOffMonthlyEntity clfCutoff = clfCutOffMonthRepository.save(clf);
	}

	/*
	 * Get Monthly Form Indicator And section wise with value
	 */

	@Override
	public Response<FormResponse> getTabForm(Integer tabId, HttpServletRequest request) {

		Response<FormResponse> response = new Response<FormResponse>();
		List<FormResponse> wrappedList = new ArrayList<FormResponse>();

		try {

			List<SectionEntity> sectionList = clfCutOffMonthRepository.getSectionByTabId(tabId);
			if (sectionList == null || sectionList.size() == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Section Found");
			} else {
				wrappedList = sectionList.stream().map(section -> {
					
					Integer skipValue = null;
					String sectionStatus = ProjectConstants.PENDING_TEXT;
        			if(tabId == ProjectConstants.TAB_CLF) {
        				ClfCutOffMonthlyEntity clfCutOff = clfCutOffMonthRepository.checkCutOffExistByClfCodeWithMonthYear(
        						request.getParameter("clfCode"), Integer.parseInt(request.getParameter("reportMonth")), Integer.parseInt(request.getParameter("reportYear")));

        				if(clfCutOff != null) {
        					Optional<CutOffMonthlySkipMapping> sectionSkipOptional = clfCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(clfCutOff.getId(),section.getId());
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
        				VoCutOffMonthlyEntity voCutOff = voCutOffMonthRepository.checkCutOffExistByVoCodeWithMonthYear(
        						request.getParameter("voCode"), Integer.parseInt(request.getParameter("reportMonth")), Integer.parseInt(request.getParameter("reportYear")));
        				if(voCutOff != null) {
        					Optional<CutOffMonthlySkipMapping> sectionSkipOptional = voCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(voCutOff.getVoCutOffId(),section.getId());
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
        				GpCutOffMonthlyEntity gpCutOff = gpCutOffMonthRepository.checkCutOffExistByGpCodeWithMonthYear(
        						request.getParameter("gpCode"), Integer.parseInt(request.getParameter("reportMonth")), Integer.parseInt(request.getParameter("reportYear")));
        				if(gpCutOff != null) {
        					Optional<CutOffMonthlySkipMapping> sectionSkipOptional = gpCutOffMonthlySkipRepository.findByCutOffIdAndSectionId(gpCutOff.getId(),section.getId());
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
					
					List<IndicatorCustomEntity> indicatorList = clfCutOffMonthRepository
							.getIndicatorsValueBySectionId(section.getId(), tabId, request);

//					List<Indicator> indicatorList = customCutOffRepository.getIndicatorsBySectionId(section.getId());
					List<IndicatorResponse> indctrList = new ArrayList<IndicatorResponse>();
					if (indicatorList != null && indicatorList.size() != 0) {
						indctrList = indicatorList.stream().map(indctr -> {
							List<AnswerOptionResponse> optionlistdto = new ArrayList<AnswerOptionResponse>();
							if (indctr.getIndctrType() != null
									&& (indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN
											|| indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO || indctr.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN)) {
								List<AnswerOptionEntity> optionList = clfCutOffMonthRepository.getIndicatorOptions(
										ProjectConstants.CUT_OFF, ProjectConstants.CUT_OFF_SHORT_KEY + indctr.getId());
								if (optionList != null && optionList.size() != 0) {
									optionlistdto = optionList.stream().map(
											opt -> new AnswerOptionResponse(opt.getOptionId(), opt.getOptionValue(),opt.getStatus()))
											.collect(Collectors.toList());
								}
							}
							List<Integer> values =   (indctr.getOptionId() != null && !indctr.getOptionId().isEmpty())  ? Arrays.stream(indctr.getOptionId().split(",")).map(Integer::parseInt).collect(Collectors.toList()):null;

							return new IndicatorResponse(indctr.getId(), indctr.getIndctrName(),
									indctr.getDescription(), indctr.getMandatory(), indctr.getIndctrType(),
									indctr.getMinValue(), indctr.getMaxValue(), indctr.getIndctrValue(), values,optionlistdto);
						}).collect(Collectors.toList());
					}
					return new FormResponse(section.getId(), section.getSectionName(),section.getSkipFlag(),skipValue, sectionStatus,indctrList);
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

	// Get Form Data Value

	@Override
	public Response<FormResponse> getTabFormData(Integer tabId, Integer cutOffId) {
		Response<FormResponse> response = new Response<FormResponse>();
		List<FormResponse> wrappedList = new ArrayList<FormResponse>();
		try {

			List<SectionEntity> sectionList = clfCutOffMonthRepository.getSectionByTabId(tabId);
			if (sectionList == null || sectionList.size() == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Section Found");
			} else {
				wrappedList = sectionList.stream().map(section -> {
					List<IndicatorCustomEntity> indicatorList = clfCutOffMonthRepository
							.getIndicatorsBySectionId(section.getId(), tabId, cutOffId);
					List<IndicatorResponse> indctrList = new ArrayList<IndicatorResponse>();
					if (indicatorList != null && indicatorList.size() != 0) {
						indctrList = indicatorList.stream().map(indctr -> {
							List<AnswerOptionResponse> optionlistdto = new ArrayList<AnswerOptionResponse>();
							if (indctr.getIndctrType() != null
									&& (indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_DROPDOWN
											|| indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO || indctr.getIndctrType() == ProjectConstants.MULTISELECT_DROPDOWN)) {
								List<AnswerOptionEntity> optionList = clfCutOffMonthRepository.getIndicatorOptions(
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
					return new FormResponse(section.getId(), section.getSectionName(), 0,0,null,indctrList);
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
	 * Get OverAll Monthly Status
	 */

	@Override
	public Integer getOverAllStatus(String clfCode,Integer yearId,Integer monthId) {
		Optional<ClfCutOffMonthlyEntity> optionalclfcutoff = clfCutOffMonthRepository.findByClfCodeAndCutOffYearAndCutOffMonth(clfCode,yearId,monthId);
		if (optionalclfcutoff.isPresent()) {
			CutOffFilterRequest filterDto = new CutOffFilterRequest(null, null, null,
					optionalclfcutoff.get().getClfCode(), yearId, monthId, null,null,null);
			if (optionalclfcutoff.get().getCutOffStatus() == null || optionalclfcutoff.get().getCutOffStatus() == ProjectConstants.PENDING) {
				return ProjectConstants.PENDING;
			} else if (optionalclfcutoff.get().getCutOffStatus() == ProjectConstants.IN_PROGRESS) {
				return ProjectConstants.IN_PROGRESS;
			} else {
				List<VoCustomCutOffEntity> voCutOffList = clfCutOffMonthRepository.getVoCutOffStatus(filterDto);
				List<GpCustomCutOffEntity> gpCutOffList = clfCutOffMonthRepository.getGpCutOffStatus(filterDto);

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
					return optionalclfcutoff.get().getCutOffStatus() > ProjectConstants.PENDING ? ProjectConstants.IN_PROGRESS : ProjectConstants.PENDING;
				} else {
					return ProjectConstants.COMPLETED;
				}
			}
		}
		return ProjectConstants.PENDING;
	}
}
