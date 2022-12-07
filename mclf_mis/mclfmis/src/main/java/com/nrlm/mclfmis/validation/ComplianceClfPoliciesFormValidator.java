package com.nrlm.mclfmis.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.ClfPoliciesRequest;

@Component("clfPoliciesValidator")
@PropertySource(value="classpath:messages.properties")
public class ComplianceClfPoliciesFormValidator implements Validator{

	@Autowired
	MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return ClfPoliciesRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClfPoliciesRequest req = (ClfPoliciesRequest) obj;
		
		if(req.getClfCode() == null || req.getClfCode().isEmpty()) {
			errors.rejectValue("clfCode", "required", new Object[] { "'clfCode'" },
					messageSource.getMessage("compliance.clfCode.required",new Object[] { "'clfCode'" }, null));
		}
		else if(req.getClfCode().contains(" ")) {
			errors.rejectValue("clfCode", "invalid", new Object[] { "'clfCode'" },
					messageSource.getMessage("compliance.clfCode.invalid",new Object[] { "'clfCode'" }, null));
		}
		else if(!Pattern.matches("[\\w\\s]+", req.getClfCode())) {
			errors.rejectValue("clfCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'clfCode'" },null));	
		}
		
		if(req.getIsDraft() != ProjectConstants.IS_DRAFT) {
			
			
			if(req.getCboHrPolicies() == null) {
				errors.rejectValue("cboHrPolicies", "required", new Object[] { "'cboHrPolicies'" },
						messageSource.getMessage("compliance.clfpolicies.cboHrPolicies.required",new Object[] { "'cboHrPolicies'" }, null));
			}
			else if(req.getCboHrPolicies() < 0) {
				errors.rejectValue("cboHrPolicies", "negativeValue", new Object[] { "'cboHrPolicies'" },
						messageSource.getMessage("compliance.clfpolicies.cboHrPolicies.negativeValue",new Object[] { "'cboHrPolicies'" }, null));
			}
			
			if(req.getGovPolicies() == null) {
				errors.rejectValue("govPolicies", "required", new Object[] { "'govPolicies'" },
						messageSource.getMessage("compliance.clfpolicies.govPolicies.required",new Object[] { "'govPolicies'" }, null));
			}
			
			else if (req.getGovPolicies() != null && req.getGovPolicies() < 0) {
				errors.rejectValue("govPolicies", "negativeValue", new Object[] { "'govPolicies'" },
						messageSource.getMessage("compliance.clfpolicies.govPolicies.negativeValue",new Object[] { "'govPolicies'" }, null));
			} 
			
			if(req.getFinPolicies() == null) {
				errors.rejectValue("finPolicies", "required", new Object[] { "'finPolicies'" },
						messageSource.getMessage("compliance.clfpolicies.finPolicies.required",new Object[] { "'finPolicies'" }, null));
			}
			else if (req.getFinPolicies() != null && req.getFinPolicies() < 0) {
				errors.rejectValue("finPolicies", "negativeValue", new Object[] { "'finPolicies'" },
						"compliance.clfpolicies.finPolicies.negativeValue");
			} 
		
		}
		
	}

}
