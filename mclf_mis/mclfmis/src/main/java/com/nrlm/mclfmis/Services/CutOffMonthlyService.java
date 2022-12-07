package com.nrlm.mclfmis.Services;

import javax.servlet.http.HttpServletRequest;

import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Request.CutOffMonthlyFormRequest;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.FormResponse;
import com.nrlm.mclfmis.Response.GpCutOffResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.VoCutOffResponse;

public interface CutOffMonthlyService {
	
	Response<ClfCutOffResposne> getClfCutOffMonthlyList(CutOffFilterRequest filterDto);

	Response<String> saveFnclYearAndMonth(CutOffMonthlyFormRequest request);

	Response<VoCutOffResponse> getVoCutOffMonthlyList(CutOffFilterRequest filterDto);

	Response<GpCutOffResponse> getGpCutOffMonthlyList(CutOffFilterRequest filterDto);

	Response<FormResponse> getTabFormData(Integer tabId, Integer cutOffId);

	Response<String> saveCutOffMonthlyData(CutOffFormRequest request);

	Response<FormResponse> getTabForm(Integer tabId, HttpServletRequest request);

	Integer getOverAllStatus(String clfCode,Integer yearId,Integer monthId);
	
}
