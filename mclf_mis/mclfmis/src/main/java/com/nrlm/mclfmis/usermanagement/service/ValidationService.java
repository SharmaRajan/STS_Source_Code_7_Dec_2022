package com.nrlm.mclfmis.usermanagement.service;

import java.util.Map;

import com.nrlm.mclfmis.Request.ValidateRequest;
import com.nrlm.mclfmis.Response.Response;

public interface ValidationService {

	Response<String> validateClfForm(ValidateRequest request);

	Response<Map<String,Boolean>> validateCLFForm(String clfCode);

}
