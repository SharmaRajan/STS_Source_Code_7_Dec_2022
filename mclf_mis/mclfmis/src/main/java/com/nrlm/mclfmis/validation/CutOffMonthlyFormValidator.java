package com.nrlm.mclfmis.validation;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfCutOffMonthlyRepository;
import com.nrlm.mclfmis.Request.ClfPoliciesRequest;
import com.nrlm.mclfmis.Request.CutOffDataRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.customEntity.IndicatorCustomEntity;
import com.nrlm.mclfmis.customRepository.ClfCutOffMonthlyRepositoryCustomImpl;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;

@Component("cutoffmonthlyvalidator")
@PropertySource(value="classpath:messages.properties")
public class CutOffMonthlyFormValidator implements Validator {


	@Autowired
	MessageSource messageSource;
	
	@Autowired
	ClfCutOffMonthlyRepositoryCustomImpl customCutOffMonthlyRepository;

	@Override
	public boolean supports(Class<?> paramClass) {
		return CutOffFormRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		CutOffFormRequest req = (CutOffFormRequest) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tabId", messageSource.getMessage("cutoff.tabId.required",new Object[] { "'tabId'" },null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clfCode", messageSource.getMessage("cutoff.clfCode.required",new Object[] { "'clfCode'" },null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportYear", messageSource.getMessage("cutoff.reportYear.required",new Object[] { "'reportYear'" },null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportMonth", messageSource.getMessage("cutoff.reportMonth.required",new Object[] { "'reportMonth'" },null));
		
		if(req.getClfCode()!=null && !Pattern.matches("[\\w\\s]+", req.getClfCode())) {
			errors.rejectValue("clfCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'clfCode'" },null));	
		}
		if(req.getVoCode()!=null && !Pattern.matches("[\\w\\s]+", req.getVoCode())) {
			errors.rejectValue("voCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'voCode'" },null));	
		}
		if(req.getGrampanchayatCode()!=null && !Pattern.matches("[\\w\\s]+", req.getGrampanchayatCode())) {
			errors.rejectValue("grampanchayatCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'grampanchayatCode'" },null));	
		}
		
		
		
		if(req.getTabId() < 0) {
			errors.rejectValue("tabId", messageSource.getMessage("cutoff.negativeValue",new Object[] { "'tabId'" },null));	
		}
		else {
			if(req.getTabId() == ProjectConstants.TAB_VO) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "voCode", messageSource.getMessage("cutoff.voCode.required",new Object[] { "'voCode'" },null));
			}
			else if(req.getTabId() == ProjectConstants.TAB_GP) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gpCode", messageSource.getMessage("cutoff.gpCode.required",new Object[] { "'gpCode'" },null));
			}
		}
		
		
		if(req.getIsDraft() != ProjectConstants.IS_DRAFT) {
			boolean flag = false;
			boolean indNotFound = true;
			List<IndicatorCustomEntity> indctrlist = customCutOffMonthlyRepository.getIndicatorsValueByTabIdAndSectionIdIn(req);
			
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
						}
					}
					if(indNotFound) {
						errors.rejectValue("indctr", "required", new Object[] { "'indctr'" },
								messageSource.getMessage("compliance.indctr.required",new Object[] { "'indctr'" }, null));
						break;
					}
					else if(flag) {
						errors.rejectValue("indctr", "invalid", new Object[] { "'indctr'" },
								messageSource.getMessage("compliance.indctr.invalid",new Object[] { "'indctr'" }, null));
						break;
					}
					
				}
			}
			
		}
		
		
	}
}
