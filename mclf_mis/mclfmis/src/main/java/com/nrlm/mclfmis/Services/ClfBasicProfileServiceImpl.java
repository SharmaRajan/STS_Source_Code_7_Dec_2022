package com.nrlm.mclfmis.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfBasicProfileRepository;
import com.nrlm.mclfmis.Request.ClfBasicProfileRequest;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Response.ClfBasicProfileResponse;
import com.nrlm.mclfmis.Response.ClfBasicprofileResposne;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Specification.ClfBasicProfileSpecification;
import com.nrlm.mclfmis.customEntity.ClfBasicProfileCustomEntity;
import com.nrlm.mclfmis.customEntity.ClfCustomCutOffEntity;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@Service
public class ClfBasicProfileServiceImpl implements ClfBasicProfileService {

	@Autowired
	private ClfBasicProfileRepository clfbasicRepository;

	@Autowired
	ClfBasicProfileSpecification clfbasicProfileSpecification;

	@Autowired
	LocationService locationService;

	@Autowired
	private PageModel pageModel;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomCutOffRepository customCutOffRepository;


	@Transactional
	public Response<String> saveClfBasicProfile(Integer loginId, ClfBasicProfileRequest clfbasic) {

		Response<String> response = new Response<String>();
		try {
			Optional<ClfBasicProfile> optionalbasic = clfbasicRepository.findByClfCode(clfbasic.getClfCode());
			
			if (!optionalbasic.isPresent()) {
				
				ClfBasicProfile profile = new ClfBasicProfile();
				profile.setCreatedBy(loginId);
				profile.setUpdatedBy(Integer.parseInt("" + loginId));
				profile.setClfCode(clfbasic.getClfCode());
				profile.setClfRecgn(clfbasic.getClfRecgn());
				profile.setFinYearId(clfbasic.getFinYearId());
				profile.setMonthId(clfbasic.getMonthId());
				profile.setGstin(clfbasic.getGstin());
				profile.setPan(clfbasic.getPan());
				profile.setTan(clfbasic.getTan());
				profile.setNpDesgn(clfbasic.getNpDesgn());
				profile.setNpName(clfbasic.getNpName());
				profile.setOtherDesgn(clfbasic.getOtherDesgn());
				profile.setOfficeStatus(clfbasic.getOfficeStatus());
				if (clfbasic.getDraft() == ProjectConstants.IS_DRAFT) {
					
					profile.setProfileStatus(ProjectConstants.BASICPROFILE_IN_PROGRESS);
				} else {
					profile.setProfileStatus(ProjectConstants.BASICPROFILE_COMPLETED);
				}
				profile.setStatus(ProjectConstants.ACTIVE_STATUS);
				clfbasicRepository.save(profile);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.BAD_REQUEST,
						"CLF Basic Profile already exist with this CLF Code");
			}
		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "CLF Basic Profile Not Created");
		}
	}

	

	@Override
	public Response<String> updateClfProfile(Long userId,ClfBasicProfileRequest request) {
		Response<String> response = new Response<String>();
		try {
			Optional<ClfBasicProfile> optionalProfile = clfbasicRepository.findByClfCode(request.getClfCode());
			
			if (optionalProfile.isPresent()) {
				ClfBasicProfile profile = optionalProfile.get();
				profile.setUpdatedBy(Integer.parseInt("" + userId));
				profile.setClfCode(request.getClfCode());
				profile.setClfRecgn(request.getClfRecgn());
				profile.setFinYearId(request.getFinYearId());
				profile.setMonthId(request.getMonthId());
				profile.setGstin(request.getGstin());
				profile.setPan(request.getPan());
				profile.setTan(request.getTan());
				profile.setNpDesgn(request.getNpDesgn());
				profile.setOtherDesgn(request.getOtherDesgn());
				profile.setNpName(request.getNpName());
				if (request.getDraft() == ProjectConstants.IS_DRAFT) {
					profile.setProfileStatus(ProjectConstants.BASICPROFILE_IN_PROGRESS);
				} else {
					profile.setProfileStatus(ProjectConstants.BASICPROFILE_COMPLETED);
				}
				profile.setOfficeStatus(request.getOfficeStatus());
				clfbasicRepository.save(profile);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No CLF Profile found with given CLF Code");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "CLF Profile Not Updated");
		}

	}
	
	@Override
	public Response<ClfBasicprofileResposne> getProfilelist(HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		Response<ClfBasicprofileResposne> response = new Response<ClfBasicprofileResposne>();
		List<ClfBasicprofileResposne> wrappedList = new ArrayList<ClfBasicprofileResposne>();
		PageModel.setSIZE(20);
		pageModel.initPageAndSize();
//		pageModel.initOffsetAndLimit();
		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelId = null;
			if (authUser != null && authUser.getLevelName() != null) {
				userId = authUser.getUserId();
				levelId = authUser.getLevelName();
			}
			List<String> locations = new ArrayList<String>();
			if (userId == 0 || levelId == null) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelId);
			Page<ClfBasicProfileCustomEntity> clfCutOffList = customCutOffRepository.getClfbasicProfileList(request,
					locations, levelId, pageModel, PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
			
			if (clfCutOffList == null || clfCutOffList.getSize() == 0) {
				response.setWrapperList(wrappedList);
				response.setTotalcount(Long.parseLong(String.valueOf(clfCutOffList.getTotalElements())));
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				response.setErrorMsg("No CLF Basic Profile Found");
				return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Clf  Found");
			} else {
				wrappedList = clfCutOffList.getContent().stream()
						.map(cutoff -> modelMapper.map(cutoff, ClfBasicprofileResposne.class))
						.collect(Collectors.toList());
				response.setWrapperList(wrappedList);
				response.setTotalcount(Long.parseLong(String.valueOf(clfCutOffList.getTotalElements())));
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			}

		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		}
	}

	@Override
	public Response<String> deleteClfProfile(Long loginId, Long profileId) {

		Response<String> response = new Response<String>();
		try {
			Optional<ClfBasicProfile> optionalclfprofile = clfbasicRepository
					.findById(Integer.parseInt("" + profileId));
			if (optionalclfprofile.isPresent()) {
				ClfBasicProfile clfp = optionalclfprofile.get();
				clfp.setUpdatedBy(Integer.parseInt("" + loginId));
				clfp.setStatus(ProjectConstants.INACTIVE_STATUS);
				clfbasicRepository.save(clfp);

				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No CLF Profile found with given CLF Code");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "CLF Profile Not Deleted");
		}
	}

}
