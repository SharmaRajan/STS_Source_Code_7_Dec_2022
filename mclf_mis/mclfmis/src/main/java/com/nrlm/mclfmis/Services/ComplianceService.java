package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Request.ClfPoliciesRequest;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.Request.InternalControlRequest;
import com.nrlm.mclfmis.Request.StatutoryRequest;
import com.nrlm.mclfmis.Response.ClfPoliciesResponse;
import com.nrlm.mclfmis.Response.InternalControlResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.StatutoryResponse;

public interface ComplianceService {

	Response<String> createClfPolicies(Long userId, ClfPoliciesRequest request);

	//Response<String> updateClfPolicies(Long userId, ClfPoliciesRequest request);

	Response<ClfPoliciesResponse> getPoliciesList(ComplianceFilterRequest filterDto);

	Response<String> createIntControl(Long userId, InternalControlRequest request);

	//Response<String> updateIntControl(Long userId,InternalControlRequest request);

	Response<InternalControlResponse> getIntControlList(ComplianceFilterRequest filterDto);

	Response<String> createStatutory(Long userId, StatutoryRequest request);

	Response<StatutoryResponse> getStatutoryList(ComplianceFilterRequest filterDto);

}
