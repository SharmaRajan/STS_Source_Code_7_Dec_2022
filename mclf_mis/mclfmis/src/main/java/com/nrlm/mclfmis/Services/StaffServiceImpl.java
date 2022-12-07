package com.nrlm.mclfmis.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Entity.StaffEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfRepository;
import com.nrlm.mclfmis.Repository.StaffRepository;
import com.nrlm.mclfmis.Request.StaffFilterRequest;
import com.nrlm.mclfmis.Request.StaffRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.StaffResponse;
import com.nrlm.mclfmis.customEntity.ClfMasterEntity;
import com.nrlm.mclfmis.customEntity.StaffCustomEntity;
import com.nrlm.mclfmis.customRepository.CustomStaffRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@Service
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private PageModel pageModel;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	private CustomStaffRepository customStaffRepository;
	
	@Autowired
	private ClfRepository clfRepository;

	@Override
	public Response<String> createStaff(Long loginId, StaffRequest request) {
		Response<String> response = new Response<String>();
		
		try {
			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(request.getClfCode());
			if (clfList == null || clfList.size() == 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
			}
			
			StaffEntity se = customStaffRepository.getStaffDetailsByNameAndPhone(request.getName(),request.getPhone());

			
			StaffEntity staff = null;
			
			if(request.getId() != null && request.getId() != 0) {//update
				
				Long temp = request.getId();
				Long temp2 = se.getId();
				
				staff = customStaffRepository.getStaffDetailsById(request.getId());
				
				if(staff == null) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Staff Not Found");
				}
//				else if(staff != null && staff.getStaffStatus() == ProjectConstants.COMPLETED) {
//					
//					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
//				}
				else if(se != null && (temp2.longValue() != temp.longValue())) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Staff Already Exist");
				}
				
			}
			else {
				
				if(se != null) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Staff Already Exist");
				}
				
				staff =  new StaffEntity();
				staff.setCreatedAt(new Date());
				staff.setCreatedBy(loginId);
			}
			
			staff.setAddInfo(request.getAddInfo());
			staff.setAddress(request.getAddress());
			staff.setClfCode(request.getClfCode());
			staff.setDiffAble(request.getDiffAble());
			staff.setEduArea(request.getEduArea());
			staff.setEduQual(request.getEduQual());
			staff.setExpDesc(request.getExpDesc());
			staff.setExpYrs(request.getExpYrs());
			staff.setGender(request.getGender());
			staff.setJoinDate(request.getJoinDate());
			staff.setLeaveDate(request.getLeaveDate());
			staff.setLeaveReason(request.getLeaveReason());
			staff.setMinority(request.getMinority());
			staff.setMonthRemu(request.getMonthRemu());
			staff.setName(request.getName());
			staff.setOtherRes(request.getOtherRes());
			staff.setPhone(request.getPhone());
			staff.setSocialCategory(request.getSocialCategory());
			staff.setStaffType(request.getStaffType());
			
			if(request.getActStatus() != null && request.getActStatus() == ProjectConstants.INACTIVE_STATUS) {
				
				staff.setStatus(ProjectConstants.INACTIVE_STATUS);
			}
			else {
				staff.setStatus(ProjectConstants.ACTIVE_STATUS);
			}
			if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
				staff.setStaffStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				staff.setStaffStatus(ProjectConstants.COMPLETED);
			}
			
			staff.setUpdatedAt(new Date());
			staff.setUpdatedBy(loginId);
			
			staffRepository.save(staff);
			
			
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
			
		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Staff Not Created");
		}

	}

	@Override
	public Response<StaffResponse> getStaffList(StaffFilterRequest filterDto) {
		
		Response<StaffResponse> response = new Response<StaffResponse>();
		List<StaffResponse> wrappedList = new ArrayList<StaffResponse>();

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

			List<StaffCustomEntity> staffList = customStaffRepository.getStaff(filterDto, locations,levelName, pageModel);

			wrappedList = staffList.stream().map(staff -> {
				return new StaffResponse(staff.getId(),staff.getClfCode(), staff.getClfName(), staff.getStateCode(), staff.getStateName(), staff.getBlockCode(),
						staff.getBlockName(), staff.getDistrictCode(), staff.getDistrictName(), staff.getStaffType(), staff.getName(), staff.getPhone(), staff.getGender(),
						staff.getSocialCategory(), staff.getMinority(), staff.getDiffAble(), staff.getMonthRemu(), staff.getAddress(), staff.getEduQual(),
						staff.getEduArea(), staff.getAddInfo(), staff.getExpYrs(), staff.getExpDesc(), staff.getJoinDate(), staff.getStatus(), 
						staff.getLeaveDate(), staff.getLeaveReason(), staff.getOtherRes(),staff.getStaffStatus());
			}).collect(Collectors.toList());
			
				response.setWrapperList(wrappedList);

				response.setTotalcount(
						customStaffRepository.getStaffCount(filterDto, locations, levelName, pageModel));
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
		
	

