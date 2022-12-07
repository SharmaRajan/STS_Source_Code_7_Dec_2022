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
import com.nrlm.mclfmis.Request.InternalControlRequest;

@Component("intCntrlValidator")
@PropertySource(value="classpath:messages.properties")
public class ComplianceIntCntrlFormValidator implements Validator{
	
	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return InternalControlRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		InternalControlRequest req = (InternalControlRequest) obj;
		
		if(req.getClfCode() == null || req.getClfCode().isEmpty()) {
			errors.rejectValue("clfCode", "required", new Object[] { "'clfCode'" },
					messageSource.getMessage("compliance.clfCode.required",new Object[] { "'clfCode'" }, null));
		}
		else if(req.getClfCode().contains(" ")) {
			errors.rejectValue("clfCode", "invalid", new Object[] { "'clfCode'" },
					messageSource.getMessage("compliance.clfCode.invalid",new Object[] { "'clfCode'" }, null));
		}else if(!Pattern.matches("[\\w\\s]+", req.getClfCode())) {
			errors.rejectValue("clfCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'clfCode'" },null));	
		}
		

		if(req.getFinYearId() == null || req.getFinYearId() <= 0) {
			errors.rejectValue("finYearId", "invalid", new Object[] { "'finYearId'" },
					messageSource.getMessage("compliance.intCtrl.finYearId.invalid",new Object[] { "'finYearId'" }, null));
		}
		if(req.getQrtrId() == null || req.getQrtrId() <= 0 || req.getQrtrId() > 4) {
			errors.rejectValue("qrtrId", "invalid", new Object[] { "'qrtrId'" },
					messageSource.getMessage("compliance.intCtrl.qrtrId.invalid",new Object[] { "'qrtrId'" }, null));
		}
		
		if(req.getIsDraft() != ProjectConstants.IS_DRAFT) {
			
			if(req.getIntAudit() == null) {
				errors.rejectValue("intAudit", "required", new Object[] { "'intAudit'" },
						messageSource.getMessage("compliance.intCtrl.intAudit.required",new Object[] { "'intAudit'" }, null));
			}
			else if(req.getIntAudit() <= 0 || req.getIntAudit() > 2) {
				errors.rejectValue("intAudit", "invalid", new Object[] { "'intAudit'" },
						messageSource.getMessage("compliance.intCtrl.intAudit.invalid",new Object[] { "'intAudit'" }, null));
			}
			if(req.getGrb() == null) {
				errors.rejectValue("grb", "required", new Object[] { "'grb'" },
						messageSource.getMessage("compliance.intCtrl.grb.required",new Object[] { "'grb'" }, null));
			}
			else if(req.getGrb() <= 0 || req.getGrb() > 2) {
				errors.rejectValue("grb", "invalid", new Object[] { "'grb'" },
						messageSource.getMessage("compliance.intCtrl.grb.invalid",new Object[] { "'grb'" }, null));
			}
			if(req.getGbMeeting() == null) {
				errors.rejectValue("gbMeeting", "required", new Object[] { "'gbMeeting'" },
						messageSource.getMessage("compliance.intCtrl.gbMeeting.required",new Object[] { "'gbMeeting'" }, null));
			}
			else if(req.getGbMeeting() <= 0 || req.getGbMeeting() > 2) {
				errors.rejectValue("gbMeeting", "invalid", new Object[] { "'gbMeeting'" },
						messageSource.getMessage("compliance.intCtrl.gbMeeting.invalid",new Object[] { "'gbMeeting'" }, null));
			}
			
		
		}
		
	}

}
