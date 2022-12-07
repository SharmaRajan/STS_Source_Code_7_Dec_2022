package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.SubModule;
import com.nic.nrlm_aajeevika.response.Response;

import javax.servlet.http.HttpServletRequest;


public interface SubModuleService {

    Response<SubModule> getSubModuleList(HttpServletRequest request);

    Response<String> saveSubModule(Integer userId, SubModule modreq, HttpServletRequest request);

    Response<String> updateSubModule(Long id, Integer userId, SubModule modreq, HttpServletRequest request);

}
