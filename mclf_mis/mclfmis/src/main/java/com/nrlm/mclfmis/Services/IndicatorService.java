package com.nrlm.mclfmis.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nrlm.mclfmis.Entity.Indicator;
import com.nrlm.mclfmis.Request.IndicatorFormRequest;
import com.nrlm.mclfmis.Request.IndicatorSequenceRequest;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.IndicatorListResponse;
import com.nrlm.mclfmis.Response.IndicatorSequenceResponse;
import com.nrlm.mclfmis.Response.Response;

public interface IndicatorService {
	Response<String> saveIndicator(Long loginid, IndicatorFormRequest req);

	Response<IndicatorListResponse> getIndicatorList(HttpServletRequest request);

	Response<String> updateIndicator(Long loginId, Long indiid, IndicatorFormRequest request);

	Response<String> deleteIndicator(Long loginId, Long indiid);

	Response<AnswerOptionResponse> getIndicatorTabResponseList();

	Response<IndicatorSequenceResponse> getIndctrSequenceList(HttpServletRequest request);

	Response<String> updateIndicatorSequenceList(Long userId, List<IndicatorSequenceRequest> request);
	
}
