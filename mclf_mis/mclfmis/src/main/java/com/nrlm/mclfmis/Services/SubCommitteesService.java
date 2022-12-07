package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Request.SubCommCSTFilterRequest;
import com.nrlm.mclfmis.Request.SubCommCSTRequest;
import com.nrlm.mclfmis.Request.SubCommDetFilterRequest;
import com.nrlm.mclfmis.Request.SubCommDetailsRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SubCommCSTResponse;
import com.nrlm.mclfmis.Response.SubCommDetailsResponse;

public interface SubCommitteesService {

	Response<String> createSubCommDetails( Long loginId, SubCommDetailsRequest request) ;
	Response<String> updateSubCommDetails(Long loginId ,Long subCommId ,SubCommDetailsRequest request);
	Response<SubCommDetailsResponse> getSubCommDetailsList(SubCommDetFilterRequest filterdto);
	
	Response<String> createCST( Long loginId, SubCommCSTRequest request) ;
	Response<String> updateCST(Long loginId ,Long subCommId,SubCommCSTRequest request);
	Response<SubCommCSTResponse> getCSTList(SubCommCSTFilterRequest filterdto);
}
