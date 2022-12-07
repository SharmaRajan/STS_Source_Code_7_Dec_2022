package com.nrlm.mclfmis.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.TrainingRequest;

import io.jsonwebtoken.lang.Collections;

@Component("trainingwiseValidator")
@PropertySource(value="classpath:messages.properties")
public class TrainingWiseFormValidator  implements Validator{



	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return TrainingRequest.class.isAssignableFrom(paramClass);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		
		TrainingRequest treq = (TrainingRequest)obj;
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clfCode", "nav", messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'clfCode'" },null));
				
				//Unique Fields start
				if(treq.getTrainingConduct() <= 0 || treq.getTrainingConduct() == null) {
					errors.rejectValue("trainingConduct", "negativeValue", new Object[] { "trainingConduct" },
							messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'trainingConduct'" },null));
				}
				if(treq.getModulePart().get(0).get("trainingModule") == null || treq.getModulePart().get(0).get("trainingModule") <=0) {
					errors.rejectValue("trainingModule", "negativeValue", new Object[] { "trainingModule" },
							messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'trainingModule'" },null));
				}
				if(treq.getStartDate() == null) {
					errors.rejectValue("startDate", "negativeValue", new Object[] { "startDate" },
							messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'startDate'" },null));
				}
				//Unique fields ends
				
		if(treq.getIsDraft() != ProjectConstants.IS_DRAFT) {
				
				if(treq.getTrainingConduct() == ProjectConstants.CLFSTAFF) {
					
					if(treq.getClfStaffType().contains(null)) {
						errors.rejectValue("clfStaffType", "negativeValue", new Object[] { "clfStaffType" },
								messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'clfStaffType'" },null));
					}
					
					if(treq.getClfStaffType().isEmpty()) {
						errors.rejectValue("clfStaffType", "negativeValue", new Object[] { "clfStaffType" },
								messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'clfStaffType'" },null));
					}
				}else {
					if(!treq.getClfStaffType().isEmpty()) {
						errors.rejectValue("clfStaffType", "negativeValue", new Object[] { "clfStaffType" },
								messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'clfStaffType'" },null));
					}
				}
				
				
				//doubt case
				if(treq.getModulePart().stream().anyMatch(s->s.get("participantNo")<0 || s.get("participantNo")>99)) {
					errors.rejectValue("participantNo", "negativeValue", new Object[] { "participantNo" },
							messageSource.getMessage("subcommittee.participant.range",new Object[] { "'participantNo'" },null));
				}
				
	
				if(treq.getEndDate() == null) {
					errors.rejectValue("endDate", "negativeValue", new Object[] { "endDate" },
							messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'endDate'" },null));
				}
		}
		
		
	}

}
