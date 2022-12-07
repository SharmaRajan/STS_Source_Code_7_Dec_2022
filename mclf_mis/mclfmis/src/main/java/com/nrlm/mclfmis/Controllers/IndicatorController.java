package com.nrlm.mclfmis.Controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nrlm.mclfmis.Request.IndicatorFormRequest;
import com.nrlm.mclfmis.Request.IndicatorSequenceRequest;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.IndicatorListResponse;
import com.nrlm.mclfmis.Response.IndicatorSequenceResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Services.IndicatorService;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.validation.IndicatorFormValidator;

@RestController
@RequestMapping("/mclf/indicator")
public class IndicatorController {

	@Autowired
	private IndicatorService indicatorService;

	@Autowired
	IndicatorFormValidator indicatorValidator;


	/* Indicator Save API */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveIndicator(@RequestBody IndicatorFormRequest indirequest, HttpServletRequest req,
            BindingResult errors) {
		
		
		indicatorValidator.validate(indirequest, errors);
		  if (errors.hasErrors()){
			  throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors());
		  }
		 
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = indicatorService.saveIndicator(auth.getUserId(),indirequest);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}
	
	/* Indicator Type Master List API */
	@GetMapping("/typelist")
	public ResponseEntity<?> getSectionList() {
		Response<AnswerOptionResponse> response = indicatorService.getIndicatorTabResponseList();
		return new ResponseEntity<Response<AnswerOptionResponse>>(response, HttpStatus.OK);
	}

	/* Indicator List API */
	@GetMapping("/indicatorlist")
	public ResponseEntity<?> getIndctrList(HttpServletRequest request) {
		Response<IndicatorListResponse> response = indicatorService.getIndicatorList(request);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/* Indicator Update API */
	/*@PutMapping("/update/{indicatorId}")
	public ResponseEntity<?> updateIndicatorOld(@PathVariable("indicatorId") Long indicatorId,
			@RequestBody IndicatorFormRequest request) {
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = indicatorService.updateIndicator(auth.getUserId(), indicatorId, request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}*/

	/* Indicator Update API */
	@PostMapping("/update")
	public ResponseEntity<?> updateIndicator(@RequestParam(value = "indicatorId", required = true) Long indicatorId,
			@RequestBody IndicatorFormRequest request,BindingResult errors) {
		
		indicatorValidator.validate(request, errors);
		  if (errors.hasErrors()){
			  throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors());
		  }
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = indicatorService.updateIndicator(auth.getUserId(), indicatorId, request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}

	/* Indicator Delete API */
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteIndicator(@RequestParam(value = "indicatorId", required = true) Long indicatorId) {
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = indicatorService.deleteIndicator(auth.getUserId(), indicatorId);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/sequence/list")
	public ResponseEntity<?> getIndctrSequenceList(HttpServletRequest request) {
		Response<IndicatorSequenceResponse> response = indicatorService.getIndctrSequenceList(request);
		return new ResponseEntity<Response<IndicatorSequenceResponse>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/sequence/update")
	public ResponseEntity<?> updateIndicatorSequenceList(@RequestBody List<IndicatorSequenceRequest> request) {
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = indicatorService.updateIndicatorSequenceList(auth.getUserId(), request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}

}
