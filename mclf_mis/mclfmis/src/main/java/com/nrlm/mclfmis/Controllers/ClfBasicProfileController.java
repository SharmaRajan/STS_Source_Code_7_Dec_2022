package com.nrlm.mclfmis.Controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrlm.mclfmis.Request.ClfBasicProfileRequest;
import com.nrlm.mclfmis.Response.ClfBasicprofileResposne;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Services.ClfBasicProfileService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.validation.ClfBasicProfileFormValidtor;

@RestController
@RequestMapping("/mclf/clfbasic")
public class ClfBasicProfileController {

	@Autowired
	private ClfBasicProfileService clfbasicProfileService;

	@Autowired
	private ClfBasicProfileFormValidtor clfbasicProfileValidator;
	

	@PostMapping("/save")
	public ResponseEntity<?> saveClfBasicprofile(@RequestBody ClfBasicProfileRequest clfbasic, BindingResult errors) {
		
		try {
			
			clfbasicProfileValidator.validate(clfbasic, errors); 
			
			if (errors.hasErrors()){
				
				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
			}
		}
		catch(ValidationHandledException e) {
			throw new ValidationHandledException(e.getCode(),e.getErrors());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		Response<String> response = clfbasicProfileService.saveClfBasicProfile(Math.toIntExact(auth.getUserId()),
				clfbasic);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}


	@PostMapping("/update")
	public ResponseEntity<?> updateProfile(@RequestParam(value = "clfCode", required = true) String clfCode,
			@RequestBody ClfBasicProfileRequest request, BindingResult errors) {
		
		try {
			request.setClfCode(clfCode);
			
			clfbasicProfileValidator.validate(request, errors); 
			
			if (errors.hasErrors()){
				
				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
			}
		}
		catch(ValidationHandledException e) {
			throw new ValidationHandledException(e.getCode(),e.getErrors());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		Response<String> response = clfbasicProfileService.updateClfProfile(auth.getUserId(), request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}
	

	@GetMapping("/profilelist")
	public ResponseEntity<?> getProfileList(HttpServletRequest request) {
		Response<ClfBasicprofileResposne> response = clfbasicProfileService.getProfilelist(request);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProfile(@RequestParam("clfProfileId") Long clfProfileId) {
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = clfbasicProfileService.deleteClfProfile(auth.getUserId(), clfProfileId);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}

}
