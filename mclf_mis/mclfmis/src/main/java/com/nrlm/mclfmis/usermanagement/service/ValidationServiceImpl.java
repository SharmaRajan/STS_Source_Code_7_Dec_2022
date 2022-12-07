package com.nrlm.mclfmis.usermanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Entity.ClfCutOffEntity;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfBasicProfileRepository;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlyRepository;
import com.nrlm.mclfmis.Request.ValidateRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.ValidateResponse;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;
import com.nrlm.mclfmis.exception.HandledException;

@Service
public class ValidationServiceImpl implements ValidationService{

	@Autowired
	ClfBasicProfileRepository clfBasicProfileRepository;
	
	@Autowired
	CustomCutOffRepository customCutOffRepository;
	
	@Autowired
	ClfCutOffMonthlyRepository clfCutOffMonthlyRepository;
	
	@Override
	public Response<String> validateClfForm(ValidateRequest request) {
		try {
			Response<String> response = new Response<String>();
			
			if(request.getFormKey().equals("cut-off")) {
				
				Optional<ClfBasicProfile> optionalBasicProfile = clfBasicProfileRepository.findByClfCode(request.getClfCode());
				
				if(optionalBasicProfile.isPresent()) {
					response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
					response.setResponseDesc(HttpStatus.OK.name());
				}
				else {
					throw new HandledException(HttpStatus.NOT_FOUND, "Clf Basic Profile not Found");
				}
			}
			else if(request.getFormKey().equals("monthlyInputs")) {
				
				ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(request.getClfCode());
				
				if(clfCutOff != null) {
					response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
					response.setResponseDesc(HttpStatus.OK.name());
				}
				else {
					throw new HandledException(HttpStatus.NOT_FOUND, "Cut Off not Found");
				}
			}
			return response;
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
			
	}

	@Override
	public Response<Map<String, Boolean>> validateCLFForm(String clfCode) {
		try {
			Response<Map<String, Boolean>> response = new Response<Map<String, Boolean>>();
			List<Map<String, Boolean>> wrappedList = new ArrayList<Map<String, Boolean>>();
			Map<String,Boolean> map = new HashMap<String, Boolean>();
			Optional<ClfBasicProfile> optionalBasicProfile = clfBasicProfileRepository.findByClfCode(clfCode);
			if(optionalBasicProfile.isPresent() && (optionalBasicProfile.get().getProfileStatus() == ProjectConstants.BASICPROFILE_COMPLETED)) {
				
				map.put("cut-off",true);
			}
			else {
				
				map.put("cut-off",false);
			}
			
			if(map.get("cut-off")) {
				ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(clfCode);
				if(clfCutOff != null && clfCutOff.getOverallStatus() == ProjectConstants.COMPLETED) {
					map.put("monthlyInputs",true);
				}
				else {
					map.put("monthlyInputs",false);
				}	
			}
			else {
				map.put("monthlyInputs",false);
			}
			
			/*if(map.get("monthlyInputs")) {
				boolean isCutOffExist = clfCutOffMonthlyRepository.(clfCode);
				if(isCutOffExist) {
				
					map.put("yearId",true);
				}
				else {
					map.put("monthId",false);
				}	
			}*/
			
			wrappedList.add(map);
			response.setWrapperList(wrappedList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
		}
		/*catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}*/
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
			
	}

}
