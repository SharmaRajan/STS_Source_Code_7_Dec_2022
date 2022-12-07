package com.nrlm.mclfmis.Controllers;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.StaffFilterRequest;
import com.nrlm.mclfmis.Request.StaffRequest;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.StaffResponse;
import com.nrlm.mclfmis.Services.StaffService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@RestController
@RequestMapping("/mclf/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createStaff(@RequestParam(name="id",required=false) String staffId,@RequestBody StaffRequest request){
		
		try {
			if(staffId !=  null && Long.parseLong(staffId) != 0) {
				request.setId(Long.parseLong(staffId));
			}
			
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
		}

		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
	   
		Response<String> response = staffService.createStaff(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateStaff(@RequestParam(name="id",required=true) Long staffId, @RequestBody StaffRequest request){
		
		try {
			Long id = staffId;
			request.setId(id);
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
		}
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = staffService.createStaff(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> staffList(@RequestParam(required=false) Map<String,String> queryParams){
		
		int status =0;
		Long staffId = 0L;
		if(queryParams.get("id") != null) {
			staffId = Long.parseLong(queryParams.get("id").toString());
		}
		if(queryParams.get("status") != null) {
			status = Integer.parseInt(queryParams.get("status").toString());
		}
		StaffFilterRequest filterDto = new StaffFilterRequest(queryParams.get("stateCode"),queryParams.get("districtCode"),
									queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status,staffId);
		
		Response<StaffResponse> response = staffService.getStaffList(filterDto);
		return new ResponseEntity<Response<StaffResponse>>(response, HttpStatus.OK);
		
	}
	
}
