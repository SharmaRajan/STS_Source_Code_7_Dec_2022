package com.nrlm.mclfmis.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.IndicatorFormRequest;
import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.SectionSequenceRequest;
import com.nrlm.mclfmis.exception.HandledException;

@Component("sectionValidator")
@PropertySource(value="classpath:messages.properties")
public class SectionFormValidator implements Validator{

	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return SectionRequest.class.isAssignableFrom(paramClass);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		
		SectionRequest req = (SectionRequest) obj;
		
		if(req.getSectionId() ==  null) {
			
			if(req.getTabId() == null) {
				errors.rejectValue("tabId", "required", new Object[] { "'tabId'" },
						messageSource.getMessage("section.tabId.required",new Object[] { "'tabId'" }, null));			
				}
			else if(req.getTabId() < 1 || req.getTabId() > 3) {
				errors.rejectValue("tabId", "required", new Object[] { "'tabId'" },
						messageSource.getMessage("section.tabId.invalid",new Object[] { "'tabId'" }, null));					
				}
		}
		
		if(req.getSectionName() == null || req.getSectionName().trim().isEmpty()) {
			errors.rejectValue("sectionName", "required", new Object[] { "'sectionName'" },
					messageSource.getMessage("section.sectionName.required",new Object[] { "'sectionName'" }, null));		
		}
		else if(!Pattern.matches("[\\w\\s]+", req.getSectionName())) {
			errors.rejectValue("sectionName", "required", new Object[] { "'sectionName'" },
					messageSource.getMessage("section.sectionName.required",new Object[] { "'sectionName'" }, null));	
			}
		if(req.getSectionDesc() != null && !Pattern.matches("[\\w\\s]+", req.getSectionDesc())) {
			errors.rejectValue("sectionDesc", "required", new Object[] { "'sectionDesc'" },
					messageSource.getMessage("section.sectionDesc.invalid",new Object[] { "'sectionDesc'" }, null));	
		}
		
		//update case
		if(req.getSectionId() !=  null) {
			
			if(req.getSectionId() <= 0) {
				errors.rejectValue("sectionId", "required", new Object[] { "'sectionId'" },
						messageSource.getMessage("section.sectionId.invalid",new Object[] { "'sectionId'" }, null));
			}
			
			if(req.getStatus() == null ) {
				errors.rejectValue("status", "required", new Object[] { "'status'" },
						messageSource.getMessage("section.status.required",new Object[] { "'status'" }, null));
			}
			if(req.getStatus() != 1 && req.getStatus() != 0) {
				
				errors.rejectValue("status", "required", new Object[] { "'status'" },
						messageSource.getMessage("section.status.invalid",new Object[] { "'status'" }, null));

			}
			if(req.getIsSkip() == null ) {
				
				errors.rejectValue("isSkip", "required", new Object[] { "'isSkip'" },
						messageSource.getMessage("section.isSkip.required",new Object[] { "'isSkip'" }, null));
			}
			if(req.getIsSkip() != 1 && req.getIsSkip() != 2) {
				
				errors.rejectValue("isSkip", "required", new Object[] { "'isSkip'" },
						messageSource.getMessage("section.isSkip.invalid",new Object[] { "'isSkip'" }, null));
			}
			
		}
		
		
	}
}
