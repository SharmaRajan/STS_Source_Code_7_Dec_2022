package com.nrlm.mclfmis.Controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Request.CutOffMonthlyFormRequest;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.Response.GpCutOffResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionResponse;
import com.nrlm.mclfmis.Response.VoCutOffResponse;
import com.nrlm.mclfmis.Services.CutOffMonthlyService;
import com.nrlm.mclfmis.Services.CutOffService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.validation.CutOffFormValidator;
import com.nrlm.mclfmis.validation.CutOffMonthlyFormValidator;

@RestController
@RequestMapping("/mclf/cutoffmonthly")
public class CutOffMonthlyController {

	@Autowired
	CutOffMonthlyService cutOffMonthService;
	
	@Autowired
	CutOffMonthlyFormValidator cutoffmonthlyvalidator;

	@GetMapping("/list")
	public ResponseEntity<?> getCutOffList(@RequestParam(required = false) Map<String, String> queryParams) {
	
		Integer reportYear = 0;
		Integer reportMonth = 0;
		Integer status = 0;
		Integer tabId = 0;
		
		CutOffFilterRequest filterDto = null;
		
		try {
			if (queryParams.get("yearId") != null && !queryParams.get("yearId").equals("")) {
				reportYear = Integer.parseInt(queryParams.get("yearId"));
			}
			if (queryParams.get("monthId") != null && !queryParams.get("monthId").equals("")) {
				reportMonth = Integer.parseInt(queryParams.get("monthId"));
			}
			if (queryParams.get("status") != null && !queryParams.get("status").equals("")) {
				status = Integer.parseInt(queryParams.get("status"));
			}
			if (queryParams.get("tabId") != null && queryParams.get("tabId").equals(ProjectConstants.TAB_CLF.toString())) {
				
				tabId = ProjectConstants.TAB_CLF ;
				
				filterDto = new CutOffFilterRequest(queryParams.get("stateCode"),
						queryParams.get("districtCode"), queryParams.get("blockCode"), queryParams.get("clfCode"),
						reportYear, reportMonth, status,queryParams.get("sortCol"),queryParams.get("sortOrder"));
			
//				if (filterDto.getClfCode() == null || filterDto.getClfCode().isEmpty()) {
//					
//					throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
//				}
				
			}
			else if (queryParams.get("tabId") != null && queryParams.get("tabId").equals(ProjectConstants.TAB_VO.toString())) {
				
				tabId = ProjectConstants.TAB_VO ;
				
				filterDto =  new CutOffFilterRequest(null, null,null,queryParams.get("clfCode"),
					null,queryParams.get("voCode"),queryParams.get("voName"),queryParams.get("grampanchayatCode"),null,
					reportYear,reportMonth,status,queryParams.get("sortCol"),queryParams.get("sortOrder"));
				

				if (filterDto.getClfCode() == null || filterDto.getClfCode().isEmpty()) {
				
					throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
				}
				if (filterDto.getReportMonth() == null || filterDto.getReportMonth() == 0) {
					
					throw new HandledException(HttpStatus.BAD_REQUEST, "Invalid Month");
				}
				if (filterDto.getReportYear() == null || filterDto.getReportYear() == 0) {
					
					throw new HandledException(HttpStatus.BAD_REQUEST, "Invalid Year");
				}
				
			}
			else if (queryParams.get("tabId") != null && queryParams.get("tabId").equals(ProjectConstants.TAB_GP.toString())) {
			
				tabId = ProjectConstants.TAB_GP ;
				
				filterDto =  new CutOffFilterRequest(null, null,null,queryParams.get("clfCode"),
						null,null,null,queryParams.get("grampanchayatCode"),queryParams.get("grampanchayatName"),
						reportYear,reportMonth,status,queryParams.get("sortCol"),queryParams.get("sortOrder"));
				
				if (filterDto.getClfCode() == null || filterDto.getClfCode().isEmpty()) {
					
					throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
				}
				if (filterDto.getReportMonth() == null || filterDto.getReportMonth() == 0) {
					
					throw new HandledException(HttpStatus.BAD_REQUEST, "Invalid Month");
				}
				if (filterDto.getReportYear() == null || filterDto.getReportYear() == 0) {
					
					throw new HandledException(HttpStatus.BAD_REQUEST, "Invalid Year");
				}
				
			}
			else {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab  Found");
			}
			
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST, "Request Error");
		}
		
		if (tabId == ProjectConstants.TAB_CLF) {
			
			Response<ClfCutOffResposne> response = cutOffMonthService.getClfCutOffMonthlyList(filterDto);
		
			return new ResponseEntity<Response<ClfCutOffResposne>>(response, HttpStatus.OK);
		} 
		else if (tabId == ProjectConstants.TAB_VO) {
			
			Response<VoCutOffResponse> response = cutOffMonthService.getVoCutOffMonthlyList(filterDto);
			
			return new ResponseEntity<Response<VoCutOffResponse>>(response, HttpStatus.OK);
		} 
		else if (tabId == ProjectConstants.TAB_GP) {
			
			Response<GpCutOffResponse> response = cutOffMonthService.getGpCutOffMonthlyList(filterDto);
			
			return new ResponseEntity<Response<GpCutOffResponse>>(response, HttpStatus.OK);
		} 
		else {
			throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab  Found");
		}
		

	}

	@GetMapping("/form")
	public ResponseEntity<?> getTabForm(@RequestParam("tabId") Integer tabId, HttpServletRequest request) {
		Response<FormResponse> response = cutOffMonthService.getTabForm(tabId, request);
		return new ResponseEntity<Response<FormResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/getformdata")
	public ResponseEntity<?> getTabForm(@RequestParam("tabId") Integer tabId,
			@RequestParam("cutOffId") Integer cutOffId) {
		Response<FormResponse> response = cutOffMonthService.getTabFormData(tabId, cutOffId);
		return new ResponseEntity<Response<FormResponse>>(response, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveCutOffMonthlyData(@RequestBody CutOffFormRequest request, HttpServletRequest req) {
		
		try {
			
			/*
			 cutoffmonthlyvalidator.validate(request, errors); 
			
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
		
		Response<String> response = cutOffMonthService.saveCutOffMonthlyData(request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}

	@PostMapping("/saveMonthYear")
	public ResponseEntity<?> saveCutOffMonthYearData(@RequestBody CutOffMonthlyFormRequest request,
			HttpServletRequest req) {
		Response<String> response = cutOffMonthService.saveFnclYearAndMonth(request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}
}
