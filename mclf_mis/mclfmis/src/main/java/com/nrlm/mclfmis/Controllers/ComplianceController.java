package com.nrlm.mclfmis.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.Request.InternalControlRequest;
import com.nrlm.mclfmis.Request.StatutoryRequest;
import com.nrlm.mclfmis.Request.ClfPoliciesRequest;
import com.nrlm.mclfmis.Response.ClfPoliciesResponse;
import com.nrlm.mclfmis.Response.InternalControlResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.StatutoryResponse;
import com.nrlm.mclfmis.Services.ComplianceService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.validation.ComplianceClfPoliciesFormValidator;
import com.nrlm.mclfmis.validation.IndicatorFormValidator;
import com.nrlm.mclfmis.validation.ComplianceIntCntrlFormValidator;

@RestController
@RequestMapping("/mclf/compliance")
public class ComplianceController {

	@Autowired
	ComplianceService complianceService;
	
	@Autowired
	ComplianceClfPoliciesFormValidator clfPoliciesValidator;
	
	@Autowired
	ComplianceIntCntrlFormValidator intCntrlValidator;

	
	@PostMapping("/policies/create")
	public ResponseEntity<?> createClfPolicies(@RequestParam(required=false,name="id") String id ,@RequestBody ClfPoliciesRequest clfRequest,
			BindingResult errors){
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		try {
			if(id != null &&  Long.parseLong(id) != 0) {
				
				clfRequest.setId(Long.parseLong(id));
			}
			
			/*
			 clfPoliciesValidator.validate(clfRequest, errors); 
			
			if (errors.hasErrors()){
				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
			}
			*/
			 
			
		} 
		catch(ValidationHandledException e) {
			throw new ValidationHandledException(e.getCode(),e.getErrors()); 
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		Response<String> response = complianceService.createClfPolicies(auth.getUserId(),clfRequest);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/policies/update")
	public ResponseEntity<?> updateClfPolicies(@RequestParam(required=false,name="id") Long id , @RequestBody ClfPoliciesRequest request) {
		try {
			if(id == null ) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Id is mandatory");
			}
			/*
			 clfPoliciesValidator.validate(request, errors); 
			
			if (errors.hasErrors()){
				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
			}
			*/
			
			request.setId(id);
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = complianceService.createClfPolicies(auth.getUserId(),request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/policies/list")
	public ResponseEntity<?> getClfPoliciesList(@RequestParam(required=false) Map <String, String> queryParams){
		
		Long id = 0L;
		int status =0;
		try {
			if(queryParams.get("id") != null && !queryParams.get("id").toString().isEmpty()) {
				
				id = Long.parseLong(queryParams.get("id").toString());
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		ComplianceFilterRequest filterDto = new ComplianceFilterRequest(id,queryParams.get("stateCode"),queryParams.get("districtCode"),
									queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status);
		Response<ClfPoliciesResponse> response = complianceService.getPoliciesList(filterDto);
		return new ResponseEntity<Response<ClfPoliciesResponse>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/statutory/create")
	public ResponseEntity<?> createStatutory(@RequestParam(required=false,name="id") String id ,@RequestBody StatutoryRequest request){
		
		try {
			if(id != null &&  Long.parseLong(id) != 0) {
					
				request.setId(Long.parseLong(id));
			}
			
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = complianceService.createStatutory(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/statutory/update")
	public ResponseEntity<?> updateStatutory(@RequestParam(required=false,name="id") String id , @RequestBody StatutoryRequest request) {
		try {
			if(id == null ||  Long.parseLong(id) == 0) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Id is mandatory");
			}
			request.setId(Long.parseLong(id));
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = complianceService.createStatutory(auth.getUserId(),request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/statutory/list")
	public ResponseEntity<?> getStatutoryList(@RequestParam(required=false) Map <String, String> queryParams){
		
		Long id = 0L;
		int status =0;
		try {
			if(queryParams.get("id") != null && !queryParams.get("id").toString().isEmpty()) {
				
				id = Long.parseLong(queryParams.get("id").toString());
			}
			if(queryParams.get("status") != null) {
				
				status = Integer.parseInt(queryParams.get("status").toString());
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		ComplianceFilterRequest filterDto = new ComplianceFilterRequest(id,queryParams.get("stateCode"),queryParams.get("districtCode"),
									queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status);
		Response<StatutoryResponse> response = complianceService.getStatutoryList(filterDto);
		return new ResponseEntity<Response<StatutoryResponse>>(response, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/intctrl/create")
	public ResponseEntity<?> createIntControl(@RequestParam(required=false,name="id") String id ,@RequestBody InternalControlRequest request,
			BindingResult errors){
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
		try {
			if(id != null &&  Long.parseLong(id) != 0) {
				
				request.setId(Long.parseLong(id));
			}
			
//			intCntrlValidator.validate(request, errors); 
//			
//			if (errors.hasErrors()){
//				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
//			}
			
		}
		catch(ValidationHandledException e) {
			throw new ValidationHandledException(e.getCode(),e.getErrors()); 
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		
		
		
		Response<String> response = complianceService.createIntControl(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/intctrl/update")
	public ResponseEntity<?> updateIntControl(@RequestParam(required=false,name="id") String id , @RequestBody InternalControlRequest request) {
		try {
			if(id == null ||  Long.parseLong(id) == 0) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Id is mandatory");
			}
//			intCntrlValidator.validate(request, errors); 
//			
//			if (errors.hasErrors()){
//				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
//			}
			request.setId(Long.parseLong(id));
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = complianceService.createIntControl(auth.getUserId(),request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/intctrl/list")
	public ResponseEntity<?> getIntControlList(@RequestParam(required=false) Map<String, String> queryParams){
		
		
		int status =0;
		int finYearId = 0;
		int qrtrId = 0;
		Long id = 0L;
		try {
			if(queryParams.get("finYearId") != null && !queryParams.get("finYearId").toString().isEmpty()) {
				finYearId = Integer.parseInt(queryParams.get("finYearId").toString());
			}
			if(queryParams.get("qrtrId") != null && !queryParams.get("qrtrId").toString().isEmpty()) {
				finYearId = Integer.parseInt(queryParams.get("qrtrId").toString());
			}
			if(queryParams.get("id") != null && !queryParams.get("id").toString().isEmpty()) {
				id = Long.parseLong(queryParams.get("id").toString());
			}
			if(queryParams.get("status") != null) {
				
				status = Integer.parseInt(queryParams.get("status").toString());
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		ComplianceFilterRequest filterDto = new ComplianceFilterRequest(id,queryParams.get("stateCode"),queryParams.get("districtCode"),
									queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status,finYearId,qrtrId);
		Response<InternalControlResponse> response = complianceService.getIntControlList(filterDto);
		return new ResponseEntity<Response<InternalControlResponse>>(response, HttpStatus.OK);
		
		

	}

}
