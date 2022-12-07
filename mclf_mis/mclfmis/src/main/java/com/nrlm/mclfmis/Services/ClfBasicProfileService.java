package com.nrlm.mclfmis.Services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Dto.BlockDto;
import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Request.ClfBasicProfileRequest;
import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Response.ClfBasicProfileResponse;
import com.nrlm.mclfmis.Response.ClfBasicprofileResposne;
import com.nrlm.mclfmis.Response.Response;

@Service
public interface ClfBasicProfileService {
	
	public Response<String> saveClfBasicProfile(Integer loginId,ClfBasicProfileRequest clf);
	
	public Response<ClfBasicprofileResposne> getProfilelist(HttpServletRequest request);
	
	Response<String> updateClfProfile(Long loginId, ClfBasicProfileRequest request);

	Response<String> deleteClfProfile(Long loginId, Long profileId);
	
}
