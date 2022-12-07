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

import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.ValidateRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.usermanagement.service.ValidationService;

@RestController
@RequestMapping("/mclf/validate")
public class ValidationController {
	
	@Autowired
	ValidationService validationService;

	/*@PostMapping("/clfform")
	public ResponseEntity<?> validateClfForm(@RequestBody ValidateRequest request) {
		AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<String> response = validationService.validateClfForm(request);
	    return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}
	*/
	
	@GetMapping("/clfform")
	public ResponseEntity<?> validateCLFForm(@RequestParam("clfCode") String clfCode) {
		//AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<Map<String,Boolean>> response = validationService.validateCLFForm(clfCode);
	    return new ResponseEntity<Response<Map<String,Boolean>>>(response, HttpStatus.OK);
	}
	/*
	@GetMapping("/cutoff")
	public ResponseEntity<?> validateCutOff(@RequestParam("clfCode") String clfCode,@RequestParam("formKey")String formKey) {
		//AuthenticatedUser auth = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Response<Map<String,Boolean>> response = validationService.validateCLFForm(clfCode);
	    return new ResponseEntity<Response<Map<String,Boolean>>>(response, HttpStatus.OK);
	}*/
}
