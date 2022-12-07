package com.nrlm.mclfmis.Controllers;

import com.nrlm.mclfmis.Dto.BlockDto;
import com.nrlm.mclfmis.Dto.ClfmasterDto;
import com.nrlm.mclfmis.Dto.DistrictDto;
import com.nrlm.mclfmis.Dto.GpDto;
import com.nrlm.mclfmis.Dto.VoProfileDto;
import com.nrlm.mclfmis.Entity.*;
import com.nrlm.mclfmis.Helper.MakeRestTemplateRequest;
import com.nrlm.mclfmis.Repository.AnswerOptionRepository;
import com.nrlm.mclfmis.Response.AnswerOptionListResponse;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.BlockDropDownResponse;
import com.nrlm.mclfmis.Response.ClfDropdownResponse;
import com.nrlm.mclfmis.Response.ClfMasterResponse;
import com.nrlm.mclfmis.Response.DistrictDropDownResponse;
import com.nrlm.mclfmis.Response.FinancialYearResponse;
import com.nrlm.mclfmis.Response.GpDropDownResponse;
import com.nrlm.mclfmis.Response.MonthResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Services.MasterService;
import com.nrlm.mclfmis.Response.StateDropDownResponse;
import com.nrlm.mclfmis.Response.VoProfileResponse;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/mclf/master")
public class MasterController {

	@Autowired
	private MasterService masterService;
	@Autowired
	AnswerOptionRepository ansl;
	@Autowired
	MakeRestTemplateRequest requestTemplate;

	@GetMapping("/statelist")
	public ResponseEntity<?> getStateList(HttpServletRequest request)
			throws JsonMappingException, JsonProcessingException {
		if (request.getParameter("page") == null) {
			ResponseEntity<Response<StateDropDownResponse>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/statelist", request, StateDropDownResponse.class);
			return res;
		} else {
			ResponseEntity<Response<State>> res = requestTemplate.makeRequest("http://MASTER-SERVICE/master/statelist",
					request, State.class);
			return res;
		}

	}

	@GetMapping("/districtlist")
	public ResponseEntity<?> getDistrictList(HttpServletRequest request) {

		if (request.getParameter("page") == null) {
			ResponseEntity<Response<DistrictDropDownResponse>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/districtlist", request, DistrictDropDownResponse.class);
			return res;
		} else {
			ResponseEntity<Response<DistrictDto>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/districtlist", request, DistrictDto.class);
			return res;
		}
	}

	@GetMapping("/blocklist")
	public ResponseEntity<?> getBlockList(HttpServletRequest request) {

		if (request.getParameter("page") == null) {
			ResponseEntity<Response<BlockDropDownResponse>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/blocklist", request, BlockDropDownResponse.class);
			return res;

		} else {
			ResponseEntity<Response<BlockDto>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/blocklist", request, BlockDto.class);
			return res;

		}
	}

	@GetMapping("/gplist")
	public ResponseEntity<?> getGpList(HttpServletRequest request) {

		if (request.getParameter("page") == null) {
			ResponseEntity<Response<GpDropDownResponse>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/gplist", request, GpDropDownResponse.class);
			return res;
		} else {
			ResponseEntity<Response<GpDto>> res = requestTemplate.makeRequest("http://MASTER-SERVICE/master/gplist",
					request, GpDto.class);
			return res;
		}
	}

	@GetMapping("/clflist")
	public ResponseEntity<?> getClfList(HttpServletRequest request) {
		if (request.getParameter("page") == null) {
			ResponseEntity<Response<ClfDropdownResponse>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/clflist", request, ClfDropdownResponse.class);
			return res;
		} else {
			ResponseEntity<Response<ClfMasterResponse>> res = requestTemplate
					.makeRequest("http://MASTER-SERVICE/master/clflist", request, ClfMasterResponse.class);
			return res;
		}
	}

	@GetMapping("/volist")
	public ResponseEntity<?> getVoList(HttpServletRequest request) {

		ResponseEntity<Response<VoProfileResponse>> res = requestTemplate
				.makeRequest("http://MASTER-SERVICE/master/volist", request, VoProfileResponse.class);
		return res;
	}

	@GetMapping("/fnclyearlist")
	public ResponseEntity<?> getFinancialyearList(HttpServletRequest request) {
		Response<FinancialYearResponse> response = masterService.getFinancialYear(request);
		return new ResponseEntity<Response<FinancialYearResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/monthlist")
	public ResponseEntity<?> getMonthList(HttpServletRequest request) {
		Response<MonthResponse> response = masterService.getMonths(request);
		return new ResponseEntity<Response<MonthResponse>>(response, HttpStatus.OK);
	}
	

	@GetMapping("/answeroption")
	public ResponseEntity<?> getAnswerOptionList(HttpServletRequest request) {
		Response<AnswerOptionListResponse> response = masterService.getAnsweroptions(request);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/statedropdownlist")
	public ResponseEntity<?> getStateDropdownList(HttpServletRequest request) {
		ResponseEntity<Response<StateDropDownResponse>> res = requestTemplate
				.makeRequest("http://MASTER-SERVICE/master/statelist", request, StateDropDownResponse.class);
		return res;
	}

	@GetMapping("/districtdropdownlist")
	public ResponseEntity<?> getDistrictDropdownList(HttpServletRequest request) {
		ResponseEntity<Response<DistrictDropDownResponse>> res = requestTemplate
				.makeRequest("http://MASTER-SERVICE/master/districtlist", request, DistrictDropDownResponse.class);
		return res;
	}

	@GetMapping("/blockdropdownlist")
	public ResponseEntity<?> getBlockDropdownList(HttpServletRequest request) {
		ResponseEntity<Response<BlockDropDownResponse>> res = requestTemplate
				.makeRequest("http://MASTER-SERVICE/master/blocklist", request, BlockDropDownResponse.class);
		return res;

	}

	@GetMapping("/gpdropdownlist")
	public ResponseEntity<?> getGpDropdownList(HttpServletRequest request) {
		ResponseEntity<Response<GpDropDownResponse>> res = requestTemplate
				.makeRequest("http://MASTER-SERVICE/master/gplist", request, GpDropDownResponse.class);
		return res;
	}

	@GetMapping("/clfdropdownlist")
	public ResponseEntity<?> getClfDropdownList(HttpServletRequest request) {
		ResponseEntity<Response<ClfDropdownResponse>> res = requestTemplate
				.makeRequest("http://MASTER-SERVICE/master/clflist", request, ClfDropdownResponse.class);
		return res;

	}
}
