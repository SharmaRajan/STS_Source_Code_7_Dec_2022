package com.nrlm.mclfmis.Services;

import java.util.List;

import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.SectionSequenceRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionPermissionResponse;
import com.nrlm.mclfmis.Response.SectionResponse;
import com.nrlm.mclfmis.Response.SectionSequenceResponse;
import com.nrlm.mclfmis.Response.SectionTabResponse;
import com.nrlm.mclfmis.exception.HandledException;

public interface SectionService {

	
	Response<SectionPermissionResponse> getSectionPermissions();

	Response<SectionResponse> getSectionList(SectionRequest filterdto) ;
	Response<SectionResponse> getSectionDropDownList(SectionRequest filterdto) ;
	Response<String> createSection(Long loginId, SectionRequest request) ;

	Response<String> updateSection(Long loginId,SectionRequest request);

	Response<String> deleteSection(Long loginId, Long sectionId);

	Response<SectionTabResponse> getTabList();

	Response<SectionSequenceResponse> getSectionSequenceList(SectionRequest filterdto);

	Response<String> updateSectionSequenceList(Long userId, List<SectionSequenceRequest> request);

}
