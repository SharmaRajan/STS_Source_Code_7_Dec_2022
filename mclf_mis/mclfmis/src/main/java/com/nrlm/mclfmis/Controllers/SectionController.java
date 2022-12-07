package com.nrlm.mclfmis.Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.nrlm.mclfmis.Helper.UtilFunctions;
import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.SectionSequenceRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionResponse;
import com.nrlm.mclfmis.Response.SectionSequenceResponse;
import com.nrlm.mclfmis.Services.SectionService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.validation.CutOffFormValidator;
import com.nrlm.mclfmis.validation.SectionFormValidator;

@RestController
@RequestMapping("/mclf/section")
public class SectionController {

	@Autowired
	SectionService sectionService;
	
	@Autowired
	SectionFormValidator sectionValidator;
	
	/*@GetMapping("/")
	public ResponseEntity<?> getSectionPermissions(){
		 Response<SectionPermissionResponse> response = sectionService.getSectionPermissions();
	      return new ResponseEntity<Response<SectionPermissionResponse>>(response, HttpStatus.OK);
	}*/

	//private static final Logger logger = LoggerFactory.getLogger(SectionController.class);
	 
		
	@GetMapping
	public ResponseEntity<?> getSectionListNew(@RequestParam(required=false,name="tabId") String tabId,@RequestParam(required=false) Map<String, String> queryParams){
		SectionRequest filterdto = null;
		try {
			if(tabId == null ||  Integer.parseInt(tabId) == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Tab Id is mandatory");
			}
		
			filterdto = new SectionRequest();
			filterdto.setTabId(Integer.parseInt(tabId));
			
			if(queryParams.get("sectionId") != null && !queryParams.get("sectionId").equals("")) {
				 filterdto.setSectionId(Long.parseLong(queryParams.get("sectionId")));
			}
			if(queryParams.get("sectionName") != null && !queryParams.get("sectionName").equals("")) {
				 filterdto.setSectionName(queryParams.get("sectionName"));
			}
			if(queryParams.get("status") != null && !queryParams.get("status").equals("")) {
				 filterdto.setStatus(Integer.parseInt(queryParams.get("status")));
			}
			
			if(queryParams.get("sortCol") != null && !queryParams.get("sortCol").equals("")) {
				 filterdto.setSortCol(queryParams.get("sortCol"));
			}
			if(queryParams.get("sortOrder") != null && !queryParams.get("sortOrder").equals("")) {
				 filterdto.setSortOrder(queryParams.get("sortOrder"));
			}
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}

		if (queryParams.get("page") != null && !queryParams.get("page").equals("")) {
			Response<SectionResponse> response = sectionService.getSectionList(filterdto);
			return new ResponseEntity<Response<SectionResponse>>(response, HttpStatus.OK);
		} else {

			Response<SectionResponse> response = sectionService.getSectionDropDownList(filterdto);
			return new ResponseEntity<Response<SectionResponse>>(response, HttpStatus.OK);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<?> createSection(@Valid @RequestBody SectionRequest request,BindingResult errors) {
		
		try {
			 sectionValidator.validate(request, errors); 
			
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
		
		Response<String> response = sectionService.createSection(auth.getUserId(), request);
		
		
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<?> updateSectionNew(@RequestParam(required=false,name="sectionId") Long sectionId ,@Valid @RequestBody SectionRequest request,BindingResult errors) {
		
		try {
			if(sectionId == null) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Section Id is mandatory");
			}
			else {
				request.setSectionId(sectionId);
			}
			
			 sectionValidator.validate(request, errors); 
			
			if (errors.hasErrors()){
				
				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
			}
		}
		catch(ValidationHandledException e) {
			throw new ValidationHandledException(e.getCode(),e.getErrors());
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = sectionService.updateSection(auth.getUserId(),request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}

	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteSectionNew(@RequestParam(required=false,name="sectionId") String sectionId) {
		try {
			if(sectionId == null ||  Long.parseLong(sectionId) == 0) {
				
				throw new HandledException(HttpStatus.BAD_REQUEST, "Section Id is mandatory");
			}
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = sectionService.deleteSection(auth.getUserId(),Long.parseLong(sectionId));
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/sequence/list")
	public ResponseEntity<?> getSectionSequenceList(@RequestParam(required=false,name="tabId") String tabId){
		SectionRequest filterdto = null;
		try {
			if(tabId == null ||  Integer.parseInt(tabId) == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Tab Id is mandatory");
			}
		
			filterdto = new SectionRequest();
			filterdto.setTabId(Integer.parseInt(tabId));
			
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}

		Response<SectionSequenceResponse> response = sectionService.getSectionSequenceList(filterdto);
		return new ResponseEntity<Response<SectionSequenceResponse>>(response, HttpStatus.OK);

	}
	
	@PostMapping("/sequence/update")
	public ResponseEntity<?> updateSectionSequenceList(@RequestBody List<SectionSequenceRequest> request) {
		
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = sectionService.updateSectionSequenceList(auth.getUserId(),request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}

}
