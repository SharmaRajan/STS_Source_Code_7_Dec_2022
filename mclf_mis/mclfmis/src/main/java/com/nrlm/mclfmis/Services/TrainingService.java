package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Request.CmtcRequest;
import com.nrlm.mclfmis.Request.TrainingFilterRequest;
import com.nrlm.mclfmis.Request.TrainingRequest;
import com.nrlm.mclfmis.Response.CmtcResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.TrainingResponse;

public interface TrainingService {
	
	Response<String> createCmtc(Long loginId, CmtcRequest cmtcreq);
	
	//Response<String >updateCmtc(Long loginId,CmtcRequest cmtcreq);
	
	Response<CmtcResponse> getCmtcList(TrainingFilterRequest filterDto);
	
	
	 Response<String> createTraining(Long loginId,TrainingRequest request);
	 
	 //Response<String >updateTraining(Long loginId,TrainingRequest trainingreq);
	 
	 Response<TrainingResponse> getTrainingList(TrainingFilterRequest filterDto);



}
