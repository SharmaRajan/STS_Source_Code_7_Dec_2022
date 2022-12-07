package com.nrlm.mclfmis.Controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.Response.GpCutOffResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.VoCutOffResponse;
import com.nrlm.mclfmis.Services.CutOffService;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;
import com.nrlm.mclfmis.validation.CutOffFormValidator;

@RestController
@RequestMapping("/mclf/cutoff")
public class CutOffController {

	@Autowired
	CutOffService cutOffService;
	
	@Autowired
	CutOffFormValidator cutoffvalidator;

	@GetMapping("/list")
	public ResponseEntity<?> getCutOffList(@RequestParam(required = false) Map<String, String> queryParams) {
		Integer reportYear = 0;
		Integer reportMonth = 0;
		Integer status = 0;
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
			
			CutOffFilterRequest filterDto = new CutOffFilterRequest(queryParams.get("stateCode"),
					queryParams.get("districtCode"), queryParams.get("blockCode"), queryParams.get("clfCode"),
					reportYear, reportMonth, status,queryParams.get("sortCol"),queryParams.get("sortOrder"));
			
			Response<ClfCutOffResposne> response = cutOffService.getClfCutOffList(filterDto);
			
			return new ResponseEntity<Response<ClfCutOffResposne>>(response, HttpStatus.OK);
		} 
		else if (queryParams.get("tabId") != null
				&& queryParams.get("tabId").equals(ProjectConstants.TAB_VO.toString())) {
		
			CutOffFilterRequest filterDto = new CutOffFilterRequest(null, null, null, queryParams.get("clfCode"), null,
					queryParams.get("voCode"), queryParams.get("voName"), queryParams.get("grampanchayatCode"), null,
					reportYear, reportMonth, status,queryParams.get("sortCol"),queryParams.get("sortOrder"));
			if (filterDto.getClfCode() == null || filterDto.getClfCode().isEmpty()) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
			}
			
			Response<VoCutOffResponse> response = cutOffService.getVoCutOffList(filterDto);
			
			return new ResponseEntity<Response<VoCutOffResponse>>(response, HttpStatus.OK);
		} 
		else if (queryParams.get("tabId") != null
				&& queryParams.get("tabId").equals(ProjectConstants.TAB_GP.toString())) {
			CutOffFilterRequest filterDto = new CutOffFilterRequest(null, null, null, queryParams.get("clfCode"), null,
					null, null, queryParams.get("grampanchayatCode"), queryParams.get("grampanchayatName"), reportYear,
					reportMonth, status,queryParams.get("sortCol"),queryParams.get("sortOrder"));

			if (filterDto.getClfCode() == null || filterDto.getClfCode().isEmpty()) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Clf Code is mandatory");
			}
			
			Response<GpCutOffResponse> response = cutOffService.getGpCutOffList(filterDto);
		
			return new ResponseEntity<Response<GpCutOffResponse>>(response, HttpStatus.OK);
		} 
		else {
			throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab  Found");
		}

	}

	@GetMapping("/form")
	public ResponseEntity<?> getTabForm(@RequestParam("tabId") Integer tabId, HttpServletRequest request) {
		try {
			if (tabId == ProjectConstants.TAB_CLF && (request.getParameter("clfCode") == null || request.getParameter("clfCode").isEmpty())) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Clf code is mandatory");
			}
			else if(tabId == ProjectConstants.TAB_VO && (request.getParameter("voCode") == null || request.getParameter("voCode").isEmpty())) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Vo code is mandatory");
			}
			else if(tabId == ProjectConstants.TAB_GP && (request.getParameter("gpCode") == null || request.getParameter("gpCode").isEmpty())) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Gp code is mandatory");
			}
			if(tabId != ProjectConstants.TAB_CLF && tabId != ProjectConstants.TAB_VO && tabId != ProjectConstants.TAB_GP) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "Tab Id not found");
			}
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		Response<FormResponse> response = cutOffService.getTabForm(tabId, request);
		return new ResponseEntity<Response<FormResponse>>(response, HttpStatus.OK);
		
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveCutOffData(@RequestBody CutOffFormRequest request, HttpServletRequest req,BindingResult errors) {
		try {
			
			
//			 cutoffvalidator.validate(request, errors); 
//			
//			if (errors.hasErrors()){
//				throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); 
//			}
			
			
		}
		catch(ValidationHandledException e) {
			e.printStackTrace();
			throw new ValidationHandledException(e.getCode(),e.getErrors());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.BAD_REQUEST,"Request Error");
		}
		Response<String> response = cutOffService.saveCutOffData(request);
		return new ResponseEntity<Response<String>>(response, HttpStatus.OK);

	}

	@GetMapping("/getformdata")
	public ResponseEntity<?> getTabForm(@RequestParam("tabId") Integer tabId,
			@RequestParam("cutOffId") Integer cutOffId, HttpServletRequest request) {
		Response<FormResponse> response = cutOffService.getTabFormData(tabId, cutOffId, request);
		return new ResponseEntity<Response<FormResponse>>(response, HttpStatus.OK);
	}
	
	/*@GetMapping("/yearmonth")
	public ResponseEntity<?> getCutOffYearMonth(@RequestParam(required=false,name="clfCode") String clfCode,@RequestParam(required=false,name="voCode") String voCode,@RequestParam(required = false,name="gpCode") String gpCode,@RequestParam("formKey") String formKey) {
		Response<Map<String,Integer>> response = cutOffService.getCutOffYearMonth(clfCode,voCode,gpCode,formKey);
		return new ResponseEntity<Response<Map<String,Integer>>>(response, HttpStatus.OK);
	}*/
}
