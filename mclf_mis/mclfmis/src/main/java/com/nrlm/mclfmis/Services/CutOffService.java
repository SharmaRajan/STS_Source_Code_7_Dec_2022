package com.nrlm.mclfmis.Services;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.Response.GpCutOffResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.VoCutOffResponse;

public interface CutOffService {

	Response<ClfCutOffResposne> getClfCutOffList(CutOffFilterRequest filterDto);

	Response<VoCutOffResponse> getVoCutOffList(CutOffFilterRequest filterDto);

	Response<GpCutOffResponse> getGpCutOffList(CutOffFilterRequest filterDto);

	Response<FormResponse> getTabForm(Integer tabId,HttpServletRequest request);

	Response<String> saveCutOffData(CutOffFormRequest request);
	Response<FormResponse> getTabFormData(Integer tabId,Integer cutOffId,HttpServletRequest request);
	
	Integer getOverAllStatus(String clfCode);

	Response<Map<String, Integer>> getCutOffYearMonth(String clfCode,String voCode,String gpCode, String formKey);

}
