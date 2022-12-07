package com.nrlm.mclfmis.Controllers;

import java.util.Map;

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

import com.nrlm.mclfmis.Request.SubCommCSTFilterRequest;
import com.nrlm.mclfmis.Request.SubCommCSTRequest;
import com.nrlm.mclfmis.Request.SubCommDetFilterRequest;
import com.nrlm.mclfmis.Request.SubCommDetailsRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SubCommCSTResponse;
import com.nrlm.mclfmis.Response.SubCommDetailsResponse;
import com.nrlm.mclfmis.Services.SubCommitteesService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@RestController
@RequestMapping("mclf/subcom")
public class SubCommController {

	@Autowired
	private SubCommitteesService subCommService;
	
	
	@PostMapping("/details/create")
	public ResponseEntity<?> createSubComm(@RequestBody SubCommDetailsRequest request){
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = subCommService.createSubCommDetails(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/details/update")
	public ResponseEntity<?> updateSubComm(@RequestParam(required=false,name="id") Long id , @RequestBody SubCommDetailsRequest request) {
		try {
			if(id == null ) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Id is mandatory");
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = subCommService.updateSubCommDetails(auth.getUserId(), id, request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/details/list")
	public ResponseEntity<?> getSubCommList(@RequestParam(required=false) Map <String, String> queryParams){
		
		Long id = 0L;
		int status =0;
		try {
			if(queryParams.get("id") != null) {
				
				id = Long.parseLong(queryParams.get("id").toString());
			}
			
			if(queryParams.get("status") != null) {
				
				status = Integer.parseInt(queryParams.get("status").toString());
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		SubCommDetFilterRequest filterDto = new SubCommDetFilterRequest(id,queryParams.get("stateCode"),queryParams.get("districtCode"),
									queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status);
		
		Response<SubCommDetailsResponse> response = subCommService.getSubCommDetailsList(filterDto);
		return new ResponseEntity<Response<SubCommDetailsResponse>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/cst/create")
	public ResponseEntity<?> createCST(@RequestBody SubCommCSTRequest request){
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = subCommService.createCST(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/cst/update")
	public ResponseEntity<?> updateCST(@RequestParam(required=false,name="id") Long id , @RequestBody SubCommCSTRequest request) {
		try {
			if(id == null) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Id is mandatory");
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = subCommService.updateCST(auth.getUserId(),id, request);
				
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/cst/list")
	public ResponseEntity<?> getCSTList(@RequestParam(required=false) Map<String, String> queryParams){
		
		
		Long id = 0L;
		int status =0;
		try {
			if(queryParams.get("id") != null) {
				
				id = Long.parseLong(queryParams.get("id").toString());
			}
			if(queryParams.get("status") != null) {
				
				status = Integer.parseInt(queryParams.get("status").toString());
			}
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		SubCommCSTFilterRequest filterDto = new SubCommCSTFilterRequest(id,queryParams.get("stateCode"),queryParams.get("districtCode"),
									queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status);
		
		Response<SubCommCSTResponse> response = subCommService.getCSTList(filterDto);
		
		return new ResponseEntity<Response<SubCommCSTResponse>>(response, HttpStatus.OK);
		
		

	}
	
}
