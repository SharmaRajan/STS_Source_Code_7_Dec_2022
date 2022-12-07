package com.nrlm.mclfmis.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Entity.SubCommCSTEntity;
import com.nrlm.mclfmis.Entity.SubCommDetailsEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.SubCommCSTRepository;
import com.nrlm.mclfmis.Repository.SubCommDetRepository;
import com.nrlm.mclfmis.Request.SubCommCSTFilterRequest;
import com.nrlm.mclfmis.Request.SubCommCSTRequest;
import com.nrlm.mclfmis.Request.SubCommDetFilterRequest;
import com.nrlm.mclfmis.Request.SubCommDetailsRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SubCommCSTResponse;
import com.nrlm.mclfmis.Response.SubCommDetailsResponse;
import com.nrlm.mclfmis.customEntity.SubCommCSTCustomEntity;
import com.nrlm.mclfmis.customEntity.SubCommDetCustomEntity;
import com.nrlm.mclfmis.customRepository.SubCommCSTCustomRepository;
import com.nrlm.mclfmis.customRepository.SubCommDetCustomRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@Service
public class SubCommitteesServiceImpl implements SubCommitteesService {
	
	@Autowired
	private SubCommDetRepository subDetRepository; 
	
	@Autowired
	private SubCommCSTRepository subCSTRepository; 
	
	@Autowired
	private SubCommDetCustomRepository subDetCustomRepository;
	
	@Autowired
	private SubCommCSTCustomRepository subCSTCustomRepository;
	
	@Autowired
	private PageModel pageModel;
	
	@Autowired
	LocationService locationService;

	@Override
	public Response<String> createSubCommDetails(Long loginId, SubCommDetailsRequest request) {
		
		Response<String> response = new Response<String>();
		
		try {
			SubCommDetailsEntity se = subDetCustomRepository.getSubDetByClfCode(request.getId(),request.getClfCode());
					
			SubCommDetailsEntity subcomm  = null;
			
			if(se != null) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Sub Committee Details Already Exist");
//				if(se.getCommStatus() == ProjectConstants.COMPLETED) {
//					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Sub Committee Details Already Exist");
//				}
//				else {
					//subcomm = se;
			//	}
			}
			else {
				subcomm = new SubCommDetailsEntity();
				subcomm.setCreatedAt(new Date());
				subcomm.setCreatedBy(loginId);
			}
			
			
			subcomm.setStatus(ProjectConstants.ACTIVE_STATUS);
			
			if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
				subcomm.setCommStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				subcomm.setCommStatus(ProjectConstants.COMPLETED);
			}
			
			
			subcomm.setUpdatedAt(new Date());
			
			subcomm.setUpdatedBy(loginId);
			subcomm.setAnyOther(request.getAnyOther());
			subcomm.setAnyOtherName(request.getAnyOtherName());
			subcomm.setAnyOtherMemb(request.getAnyOtherMemb());
			subcomm.setBnkLinkMemb(request.getBnkLinkMemb());
			subcomm.setClfAstMemb(request.getClfAstMemb());
			subcomm.setClfCode(request.getClfCode());
			subcomm.setLivHoodMemb(request.getLivHoodMemb());
			subcomm.setMoniMemb(request.getMoniMemb());
			subcomm.setSocActMemb(request.getSocActMemb());
				
			subDetRepository.save(subcomm);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
			
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Sub Committee Details Not Created");
		}
	}

	@Override
	public Response<String> updateSubCommDetails(Long loginId, Long subCommId, SubCommDetailsRequest request) {
		
		Response<String> response = new Response<String>();
		try {
			Optional<SubCommDetailsEntity> optionalSub = subDetRepository.findById(subCommId);
			if (optionalSub.isPresent()) {
				SubCommDetailsEntity subcomm = optionalSub.get();
				
			
				subcomm.setUpdatedAt(new Date());
				subcomm.setUpdatedBy(loginId);
				subcomm.setAnyOther(request.getAnyOther());
				subcomm.setAnyOtherName(request.getAnyOtherName());
				subcomm.setAnyOtherMemb(request.getAnyOtherMemb());
				subcomm.setBnkLinkMemb(request.getBnkLinkMemb());
				subcomm.setClfAstMemb(request.getClfAstMemb());
				subcomm.setClfCode(request.getClfCode());
				subcomm.setLivHoodMemb(request.getLivHoodMemb());
				subcomm.setMoniMemb(request.getMoniMemb());
				subcomm.setSocActMemb(request.getSocActMemb());
				
				if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
					subcomm.setCommStatus(ProjectConstants.IN_PROGRESS);
				}
				else {
					subcomm.setCommStatus(ProjectConstants.COMPLETED);
				}
				
				subDetRepository.save(subcomm);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
				
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Sub Committee Details Found with given value");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Sub Committee Details Not Updated");
		}
	}

	@Override
	public Response<SubCommDetailsResponse> getSubCommDetailsList(SubCommDetFilterRequest filterdto) {
		
		Response<SubCommDetailsResponse> response = new Response<SubCommDetailsResponse>();
		List<SubCommDetailsResponse> wrappedList = new ArrayList<SubCommDetailsResponse>();
//		 PageModel.setSIZE(20);
//		 pageModel.initPageAndSize();
		 
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

			List<SubCommDetCustomEntity> subCommList = subDetCustomRepository.getSubDetList(filterdto, locations, levelName, pageModel);
	//id is added to response
			wrappedList = subCommList.stream().map(subComm -> {
				return new SubCommDetailsResponse(subComm.getId(), subComm.getClfCode(), subComm.getClfName(), subComm.getStateCode(),
						subComm.getStateName(), subComm.getDistrictCode(), subComm.getDistrictName(),
						subComm.getBlockCode(), subComm.getBlockName(), subComm.getMoniMemb(), subComm.getClfAstMemb(), subComm.getLivHoodMemb(),
						subComm.getBnkLinkMemb(), subComm.getSocActMemb(), subComm.getAnyOther(), subComm.getAnyOtherName(), subComm.getAnyOtherMemb(),
						subComm.getCommStatus());
			
			}).collect(Collectors.toList());
			
				response.setWrapperList(wrappedList);

				response.setTotalcount(
						subDetCustomRepository.getSubDetCount(filterdto, locations, levelName, pageModel));
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
		
	}

	@Override
	public Response<String> createCST(Long loginId, SubCommCSTRequest request) {
		
		Response<String> response = new Response<String>();
		
		try {
			SubCommCSTEntity se = subCSTCustomRepository.getCSTByClfCode(request.getId(),request.getClfCode());
					
			SubCommCSTEntity cst = null;
			if(se != null) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "CST Details Already Exist");
//				if(se.getCommStatus() == ProjectConstants.COMPLETED) {
//					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "CST Details Already Exist");
//				}
//				else {
//					cst = se;
//				}
				
			}
			else {
				
				cst = new SubCommCSTEntity();
				cst.setCreatedAt(new Date());
				cst.setCreatedBy(loginId);
			}
			
			cst.setStatus(ProjectConstants.ACTIVE_STATUS);
			if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
				cst.setCommStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				cst.setCommStatus(ProjectConstants.COMPLETED);
			}
			
			cst.setUpdatedAt(new Date());
			
			cst.setUpdatedBy(loginId);
			
			cst.setClfCode(request.getClfCode());
			cst.setCstFormed(request.getCstFormed());
			cst.setTotCstMemb(request.getTotCstMemb());
			
			
			subCSTRepository.save(cst);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
			
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "CST Details Not Created");
		}
	}

	@Override
	public Response<String> updateCST(Long loginId, Long subCommId, SubCommCSTRequest request) {
		
		Response<String> response = new Response<String>();
		try {
			Optional<SubCommCSTEntity> optionalSub = subCSTRepository.findById(subCommId);
			if (optionalSub.isPresent()) {
				SubCommCSTEntity cst = optionalSub.get();
				
				
				cst.setUpdatedAt(new Date());
				cst.setUpdatedBy(loginId);
				cst.setClfCode(request.getClfCode());
				cst.setCstFormed(request.getCstFormed());
				cst.setTotCstMemb(request.getTotCstMemb());
				if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
					cst.setCommStatus(ProjectConstants.IN_PROGRESS);
				}
				else {
					cst.setCommStatus(ProjectConstants.COMPLETED);
				}
	
				subCSTRepository.save(cst);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
				
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No CST Details Found with given value");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "CST Details Not Updated");
		}
	}

	@Override
	public Response<SubCommCSTResponse> getCSTList(SubCommCSTFilterRequest filterdto) {
		
		Response<SubCommCSTResponse> response = new Response<SubCommCSTResponse>();
		List<SubCommCSTResponse> wrappedList = new ArrayList<SubCommCSTResponse>();

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

			List<SubCommCSTCustomEntity> subCSTList = subCSTCustomRepository.getCSTList(filterdto, locations, levelName, pageModel);
				
	//id is added to response
			wrappedList = subCSTList.stream().map(subCST -> {
				return new SubCommCSTResponse(subCST.getId(), subCST.getClfCode(), subCST.getClfName(), subCST.getStateCode(),
						subCST.getStateName(), subCST.getDistrictCode(), subCST.getDistrictName(),
						subCST.getBlockCode(), subCST.getBlockName(), subCST.getCstFormed(), subCST.getTotCstMemb(),
						subCST.getCommStatus());
			
			}).collect(Collectors.toList());
			
				response.setWrapperList(wrappedList);

				response.setTotalcount(
						subCSTCustomRepository.getCSTCount(filterdto, locations, levelName, pageModel));
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
	}

}
