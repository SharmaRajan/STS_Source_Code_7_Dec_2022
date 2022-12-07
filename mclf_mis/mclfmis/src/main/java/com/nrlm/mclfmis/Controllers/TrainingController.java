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

import com.nrlm.mclfmis.Request.CmtcRequest;
import com.nrlm.mclfmis.Request.TrainingFilterRequest;
import com.nrlm.mclfmis.Request.TrainingRequest;
import com.nrlm.mclfmis.Response.CmtcResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.TrainingResponse;
import com.nrlm.mclfmis.Services.TrainingService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@RestController
@RequestMapping("/mclf/training")
public class TrainingController {
	
	@Autowired
	private TrainingService trainingService;
	
	@PostMapping("/cmtc/create")
	public ResponseEntity<?> createCmtc(@RequestParam(name="id",required=false) String cmtcId,@RequestBody CmtcRequest request) {
		
		try {
			if(cmtcId != null && !cmtcId.isEmpty()) {
				Long id = Long.parseLong(cmtcId);
				request.setId(id);
			}
			
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
		}
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = trainingService.createCmtc(auth.getUserId(),request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
		
	}
	
	@PostMapping("/cmtc/update")
	public ResponseEntity<?> updateCmtc(@RequestParam(name="id",required=true) Long cmtcId, @RequestBody CmtcRequest cmtcreq){
		
		try {
			Long id = cmtcId;
			cmtcreq.setId(id);
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
		}
		
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = trainingService.createCmtc(auth.getUserId(),cmtcreq);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
		
	}
		

	@GetMapping("/cmtc/list") 
	public ResponseEntity<?> cmtcList(@RequestParam(required=false) Map<String,String> queryParams){
			
			Integer status =0;
			Long cmtcId = 0L;
			try {
				if(queryParams.get("id") != null) {
					cmtcId = Long.parseLong(queryParams.get("id").toString());
				}
				if(queryParams.get("status") != null) {
					status = Integer.parseInt(queryParams.get("status").toString());
				}
			}
			catch(Exception e) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
			}
			
			TrainingFilterRequest filterDto = new TrainingFilterRequest(cmtcId,queryParams.get("stateCode"),queryParams.get("districtCode"),
										queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status);
			
			Response<CmtcResponse> response = trainingService.getCmtcList(filterDto);
			return new ResponseEntity<Response<CmtcResponse>>(response, HttpStatus.OK);
	
	}
	
	//PostMapping....	
		@PostMapping("conduct/create")
		public ResponseEntity<?> createTraining(@RequestParam(name="id",required=false) String trainId, @RequestBody TrainingRequest request) {
			
			try {
				if(trainId != null && !trainId.isEmpty()) {
					Long id = Long.parseLong(trainId);
					request.setId(id);
				}
			}
			catch(ValidationHandledException v) {
				throw new ValidationHandledException(v.getCode(),
						  v.getErrors());
			}
			catch(Exception e) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
			}
			
			
			AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			Response<String> response = trainingService.createTraining(auth.getUserId(),request);
			return new ResponseEntity<Response<String>>(response, HttpStatus.OK);		
			
		}
		
		@PostMapping("conduct/update")
		public ResponseEntity<?> updateTraining(@RequestParam(name="id",required=true) Long trainingId, @RequestBody TrainingRequest request){
			
			try {
				Long id = trainingId;
				request.setId(id);
			}
			catch(ValidationHandledException v) {
				throw new ValidationHandledException(v.getCode(),
						  v.getErrors());
			}
			catch(Exception e) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
			}
			
			AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			Response<String> response = trainingService.createTraining(auth.getUserId(),request);
			return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
			
		}
		
		
		@GetMapping("conduct/list") 
		public ResponseEntity<?> trainingList(@RequestParam(required=false) Map<String,String> queryParams){
			
			int status =0;
			Long trainingId = 0L;
			try {
				if(queryParams.get("id") != null) {
					trainingId = Long.parseLong(queryParams.get("id").toString());
				}
				if(queryParams.get("status") != null) {
					status = Integer.parseInt(queryParams.get("status").toString());
				}
			}
			catch(Exception e) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
			}
			TrainingFilterRequest filterDto = new TrainingFilterRequest(trainingId,queryParams.get("stateCode"),queryParams.get("districtCode"),
										queryParams.get("blockCode"),queryParams.get("clfCode"),queryParams.get("clfName"),status);
			
			Response<TrainingResponse> response = trainingService.getTrainingList(filterDto);
			return new ResponseEntity<Response<TrainingResponse>>(response, HttpStatus.OK);
			
		}


}
