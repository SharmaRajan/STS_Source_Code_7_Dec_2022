package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Dto.BlockDto;
import com.nrlm.mclfmis.Dto.ClfmasterDto;
import com.nrlm.mclfmis.Dto.DistrictDto;
import com.nrlm.mclfmis.Dto.GpDto;
import com.nrlm.mclfmis.Dto.VoProfileDto;
import com.nrlm.mclfmis.Entity.*;
import com.nrlm.mclfmis.Response.AnswerOptionListResponse;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.ClfInfoResponse;
import com.nrlm.mclfmis.Response.FinancialYearResponse;
import com.nrlm.mclfmis.Response.MonthResponse;
import com.nrlm.mclfmis.Response.Response;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface MasterService {
   /* public Response<State> getStates(String statecode);
    public Response<DistrictDto> getDistricts(HttpServletRequest request);
    public Response<BlockDto> getBlocks(HttpServletRequest request);
    public Response<GpDto> getGps(HttpServletRequest request);
    public Response<ClfmasterDto> getClfs(HttpServletRequest request);
    public Response<VoProfileDto> getVos(HttpServletRequest request);*/
    public Response<FinancialYearResponse> getFinancialYear(HttpServletRequest request);
    public Response<MonthResponse> getMonths(HttpServletRequest request);
	public Response<ClfInfoResponse> getClfInfo(String clfCode);
	public Response<AnswerOptionListResponse> getAnsweroptions(HttpServletRequest request);
}
