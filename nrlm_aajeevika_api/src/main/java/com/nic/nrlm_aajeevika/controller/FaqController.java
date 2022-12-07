package com.nic.nrlm_aajeevika.controller;

import com.nic.nrlm_aajeevika.entity.TickerPopupSlider;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.request.Faqrequest;
import com.nic.nrlm_aajeevika.response.FaqResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.service.ContentService;
import com.nic.nrlm_aajeevika.service.ModuleService;
import com.nic.nrlm_aajeevika.service.SubModuleService;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("/aajeevika/faq")
public class FaqController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private SubModuleService submoduleService;

	@Autowired
	private Validator validator;

	@PostMapping(value = "/save")
	@ResponseBody
	public ResponseEntity<?> saveContent(@RequestBody Faqrequest faqrequest, HttpServletRequest req) {

		Set<ConstraintViolation<Faqrequest>> violations = validator.validate(faqrequest);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}

		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		System.out.println("---------" + faqrequest);
		Response<String> response = contentService.saveFaq(Math.toIntExact(auth.getUserId()), faqrequest);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}

	@GetMapping("/faqlist")
	public ResponseEntity<?> getFaqList(HttpServletRequest request) {
		Response<FaqResponse> response = contentService.getfaqlist(request);

		// System.out.println("-------------"+response.getWrapperList());

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateModule(@RequestParam(value = "id", required = true) Long id,
			@RequestBody Faqrequest modreq, Faqrequest modReq, HttpServletRequest req) {
		Set<ConstraintViolation<Faqrequest>> violations = validator.validate(modreq);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = contentService.updatefaq(id, Math.toIntExact(auth.getUserId()), modreq, req);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> deleteFAQ(@RequestParam(value = "id", required = true) Long id, Faqrequest req) {

		AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Response<String> response = contentService.deleteFAQ(id, Math.toIntExact(auth.getUserId()), req);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
	}

}
