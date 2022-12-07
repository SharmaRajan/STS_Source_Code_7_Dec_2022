package com.nrlm.mclfmis.validation;


import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.CutOffDataRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.customEntity.IndicatorCustomEntity;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;

@Component("cutoffvalidator")
@PropertySource(value="classpath:messages.properties")
public class CutOffFormValidator implements Validator{


	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CustomCutOffRepository customCutOffRepository;

	@Override
	public boolean supports(Class<?> paramClass) {
		return CutOffFormRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		CutOffFormRequest req = (CutOffFormRequest) obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabId", messageSource.getMessage("cutoff.tabId.required",new Object[] { "'tabId'" },null));
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clfCode", messageSource.getMessage("cutoff.clfCode.required",new Object[] { "'clfCode'" },null));
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportYear", messageSource.getMessage("cutoff.reportYear.required",new Object[] { "'reportYear'" },null));
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportMonth", messageSource.getMessage("cutoff.reportMonth.required",new Object[] { "'reportMonth'" },null));
		
		if(req.getTabId() == null) {
			errors.rejectValue("tabId", messageSource.getMessage("cutoff.tabId.required",new Object[] { "'tabId'" },null));
		}
		
		else if(req.getTabId() < 0 || req.getTabId() > 3) {
			errors.rejectValue("tabId", messageSource.getMessage("cutoff.tabId.invalid",new Object[] { "'tabId'" },null));	
		}
		else {
			
			if(req.getTabId() == ProjectConstants.TAB_VO) {
				if(req.getVoCode() == null) {
					errors.rejectValue("voCode", messageSource.getMessage("cutoff.voCode.required",new Object[] { "'voCode'" },null));
				}
				
				else if(req.getVoCode().contains(" ")) {
					errors.rejectValue("voCode", messageSource.getMessage("cutoff.voCode.invalid",new Object[] { "'voCode'" },null));	
				}
				else if(!Pattern.matches("[\\w\\s]+", req.getVoCode())) {
					errors.rejectValue("voCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'voCode'" },null));	
				}
				//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voCode", messageSource.getMessage("cutoff.voCode.required",new Object[] { "'voCode'" },null));
			}
			else if(req.getTabId() == ProjectConstants.TAB_GP) {
				if(req.getGrampanchayatCode() == null) {
					errors.rejectValue("grampanchayatCode", messageSource.getMessage("cutoff.grampanchayatCode.required",new Object[] { "'grampanchayatCode'" },null));
				}
				
				else if(req.getGrampanchayatCode().contains(" ")) {
					errors.rejectValue("grampanchayatCode", messageSource.getMessage("cutoff.grampanchayatCode.invalid",new Object[] { "'grampanchayatCode'" },null));	
				}
				else if(!Pattern.matches("[\\w\\s]+", req.getGrampanchayatCode())) {
					errors.rejectValue("grampanchayatCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'grampanchayatCode'" },null));	
				}
				
				//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gpCode", messageSource.getMessage("cutoff.gpCode.required",new Object[] { "'gpCode'" },null));
			}
		}
		if(req.getClfCode() == null) {
			errors.rejectValue("clfCode", messageSource.getMessage("cutoff.clfCode.required",new Object[] { "'clfCode'" },null));
		}
		
		else if(req.getClfCode().contains(" ")) {
			errors.rejectValue("clfCode", messageSource.getMessage("cutoff.clfCode.invalid",new Object[] { "'clfCode'" },null));	
		}
		else if(!Pattern.matches("[\\w\\s]+", req.getClfCode())) {
			errors.rejectValue("clfCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'clfCode'" },null));	
		}
		
		if(req.getReportYear() == null) {
			errors.rejectValue("reportYear", messageSource.getMessage("cutoff.reportYear.required",new Object[] { "'reportYear'" },null));
		}
		
		else if(req.getReportYear() < 0 ) {
			errors.rejectValue("reportYear", messageSource.getMessage("cutoff.reportYear.invalid",new Object[] { "'reportYear'" },null));	
		}
		if(req.getReportMonth() == null) {
			errors.rejectValue("reportYear", messageSource.getMessage("cutoff.reportYear.required",new Object[] { "'reportYear'" },null));
		}
		
		else if(req.getReportMonth() < 0 ) {
			errors.rejectValue("reportMonth", messageSource.getMessage("cutoff.reportMonth.invalid",new Object[] { "'reportMonth'" },null));	
		}
		
		if(req.getIsDraft() != ProjectConstants.IS_DRAFT) {
			boolean flag = false;
			boolean indNotFound = true;
			List<IndicatorCustomEntity> indctrlist = customCutOffRepository.getIndicatorsValueByTabIdAndSectionIdIn(req);
			
			if(indctrlist != null && indctrlist.size() != 0 && req.getFilledSection() != null) {
			for(CutOffDataRequest indReq : req.getCutOffData()) {
					flag = false;
					indNotFound = true;
					for(IndicatorCustomEntity indctr : indctrlist) {
						
						if(indReq.getIndctrId() == Long.valueOf(indctr.getId())) {
							
							indNotFound = false;
							
							if(indctr.getMandatory() == 1 && indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_RADIO) {
								if(indReq.getIndctrValue() == null || (!indReq.getIndctrValue().equals("1") && !indReq.getIndctrValue().equals("2"))){
									flag = true;
								}
							}
							else if(indctr.getMandatory() == 1 && indctr.getIndctrType() == ProjectConstants.INDCTR_TYPE_INTEGER) {
								
								if(indReq.getIndctrValue() == null 
									|| (Integer.valueOf(indReq.getIndctrValue()) <= Integer.valueOf(indctr.getMinValue())) 
									|| (Integer.valueOf(indReq.getIndctrValue()) >= Integer.valueOf(indctr.getMaxValue()))
								){
									flag = true;
								}
							}
							
							break;
						}
						
					}
					if(indNotFound) {
						errors.rejectValue("cutOffData", "required", new Object[] { "'indctr'" },
								messageSource.getMessage("cutoff.cutOffData.required",new Object[] { "'indctr'" }, null));
						break;
					}
					else if(flag) {
						errors.rejectValue("cutOffData", "invalid", new Object[] { "'indctr'" },
								messageSource.getMessage("cutoff.cutOffData.invalid",new Object[] { "'indctr'" }, null));
						break;
					}
					
				}
			}
			
		}
		
		
	}
}
