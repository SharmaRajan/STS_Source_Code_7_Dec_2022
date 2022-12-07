package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.response.Response;

import javax.servlet.http.HttpServletRequest;


public interface ModuleService {

    Response<Module> getModuleList(HttpServletRequest request);

    Response<String> saveModule(Integer userId, Module modreq, HttpServletRequest request);

    Response<String> updateModule(Long id,Integer userId, Module modreq, HttpServletRequest request);
}
