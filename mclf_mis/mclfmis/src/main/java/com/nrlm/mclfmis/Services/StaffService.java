package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Request.StaffFilterRequest;
import com.nrlm.mclfmis.Request.StaffRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.StaffResponse;

public interface StaffService {

	Response<String> createStaff(Long loginId, StaffRequest request);
	
	Response<StaffResponse> getStaffList(StaffFilterRequest filterDto);
 }
