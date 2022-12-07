package com.nrlm.mclfmis.validation;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.StatutoryRequest;

@Component("statutoryValidator")
@PropertySource(value="classpath:messages.properties")
public class ComplianceStatutoryFormValidator implements Validator {
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return StatutoryRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		StatutoryRequest req = (StatutoryRequest) obj;
		
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
					messageSource.getMessage("compliance.stat.finYearId.invalid",new Object[] { "'finYearId'" }, null));
		}
		
		if(req.getIsDraft() != ProjectConstants.IS_DRAFT) {
			
			
			if(req.getIsExtAudit() == null) {
				errors.rejectValue("isExtAudit", "required", new Object[] { "'isExtAudit'" },
						messageSource.getMessage("compliance.stat.isExtAudit.required",new Object[] { "'isExtAudit'" }, null));
			}
			else if(req.getIsExtAudit() < 0 || req.getIsExtAudit() > 2) {
				errors.rejectValue("isExtAudit", "invalid", new Object[] { "'isExtAudit'" },
						messageSource.getMessage("compliance.stat.isExtAudit.invalid",new Object[] { "'isExtAudit'" }, null));
			}
			
			if(req.getIsExtAudit() != null && req.getIsExtAudit() == 1) {
				
				if(req.getLastExtAuditDate() == null) {
					errors.rejectValue("lastExtAuditDate", "required", new Object[] { "'lastExtAuditDate'" },
							messageSource.getMessage("compliance.stat.lastExtAuditDate.required",new Object[] { "'lastExtAuditDate'" }, null));
				}
				else if (req.getIsExtAudit() == 1 && req.getLastExtAuditDate() != null) {
					Date curDt = new Date();
					if(req.getLastExtAuditDate().compareTo(curDt) > 0) {
						errors.rejectValue("lastExtAuditDate", "invalid", new Object[] { "'lastExtAuditDate'" },
								messageSource.getMessage("compliance.stat.lastExtAuditDate.invalid",new Object[] { "'lastExtAuditDate'" }, null));
					}
				} 
			} 
			
			if(req.getIsAgbmConduct() == null) {
				errors.rejectValue("isAgbmConduct", "required", new Object[] { "'isAgbmConduct'" },
						messageSource.getMessage("compliance.stat.isAgbmConduct.required",new Object[] { "'isAgbmConduct'" }, null));
			}
			else if(req.getIsAgbmConduct() < 0 || req.getIsAgbmConduct() > 2) {
				errors.rejectValue("isAgbmConduct", "invalid", new Object[] { "'isAgbmConduct'" },
						messageSource.getMessage("compliance.stat.isAgbmConduct.invalid",new Object[] { "'isAgbmConduct'" }, null));
			}
			
			if(req.getIsAgbmConduct() != null && req.getIsAgbmConduct() == 1) {
				if(req.getLastAgbmConductDate() == null) {
					errors.rejectValue("lastAgbmConductDate", "required", new Object[] { "'lastAgbmConductDate'" },
							messageSource.getMessage("compliance.stat.lastAgbmConductDate.required",new Object[] { "'lastAgbmConductDate'" }, null));
				}
				else if (req.getIsAgbmConduct() == 1 && req.getLastAgbmConductDate() != null) {
					Date curDt = new Date();
					if(req.getLastAgbmConductDate().compareTo(curDt) > 0) {
						errors.rejectValue("lastAgbmConductDate", "invalid", new Object[] { "'lastAgbmConductDate'" },
								messageSource.getMessage("compliance.stat.lastAgbmConductDate.invalid",new Object[] { "'lastAgbmConductDate'" }, null));
					}
				} 
				
				if(req.getLastAgbmParticipant() == null) {
					errors.rejectValue("lastAgbmParticipant", "required", new Object[] { "'lastAgbmParticipant'" },
							messageSource.getMessage("compliance.stat.lastAgbmParticipant.required",new Object[] { "'lastAgbmParticipant'" }, null));
				}
				else if (req.getIsAgbmConduct() == 1 && req.getLastAgbmParticipant() != null) {
					if(req.getLastAgbmParticipant() < 0 || req.getLastAgbmParticipant() > 99) {
						errors.rejectValue("lastAgbmParticipant", "invalid", new Object[] { "'lastAgbmParticipant'" },
								messageSource.getMessage("compliance.stat.lastAgbmParticipant.invalid",new Object[] { "'lastAgbmParticipant'" }, null));
					}
				} 
			} 
			
			if(req.getIsLdrRotationDue() == null) {
				errors.rejectValue("isLdrRotationDue", "required", new Object[] { "'isLdrRotationDue'" },
						messageSource.getMessage("compliance.stat.isLdrRotationDue.required",new Object[] { "'isLdrRotationDue'" }, null));
			}
			else if(req.getIsLdrRotationDue() < 0 || req.getIsLdrRotationDue() > 3) {
				errors.rejectValue("isLdrRotationDue", "invalid", new Object[] { "'isLdrRotationDue'" },
						messageSource.getMessage("compliance.stat.isLdrRotationDue.invalid",new Object[] { "'isLdrRotationDue'" }, null));
			}
			
			if(req.getIsLdrRotationDue() != null && req.getIsLdrRotationDue() == 2) {
				if(req.getLastLdrRotationDate() == null) {
					errors.rejectValue("lastLdrRotationDate", "required", new Object[] { "'lastLdrRotationDate'" },
							messageSource.getMessage("compliance.stat.lastLdrRotationDate.required",new Object[] { "'lastLdrRotationDate'" }, null));
				}
				else if (req.getIsLdrRotationDue() == 2 && req.getLastLdrRotationDate() != null) {
					Date curDt = new Date();
					if(req.getLastLdrRotationDate().compareTo(curDt) > 0) {
						errors.rejectValue("lastLdrRotationDate", "invalid", new Object[] { "'lastLdrRotationDate'" },
								messageSource.getMessage("compliance.stat.lastLdrRotationDate.invalid",new Object[] { "'lastLdrRotationDate'" }, null));
					}
				} 
				
			} 
			
			
			if(req.getIsItReturns() == null) {
				errors.rejectValue("isItReturns", "required", new Object[] { "'isItReturns'" },
						messageSource.getMessage("compliance.stat.isItReturns.required",new Object[] { "'isItReturns'" }, null));
			}
			else if(req.getIsItReturns() < 0 || req.getIsItReturns() > 2) {
				errors.rejectValue("isItReturns", "invalid", new Object[] { "'isItReturns'" },
						messageSource.getMessage("compliance.stat.isItReturns.invalid",new Object[] { "'isItReturns'" }, null));
			}
			
			if(req.getIsItReturns() != null && req.getIsItReturns() == 1) {
				if(req.getLastItReturnsDate() == null) {
					errors.rejectValue("lastItReturnsDate", "required", new Object[] { "'lastItReturnsDate'" },
							messageSource.getMessage("compliance.stat.lastItReturnsDate.required",new Object[] { "'lastItReturnsDate'" }, null));
				}
				else if (req.getLastItReturnsDate() != null) {
					Date curDt = new Date();
					if(req.getLastItReturnsDate().compareTo(curDt) > 0) {
						errors.rejectValue("lastItReturnsDate", "invalid", new Object[] { "'lastItReturnsDate'" },
								messageSource.getMessage("compliance.stat.lastItReturnsDate.invalid",new Object[] { "'lastItReturnsDate'" }, null));
					}
				} 
				
			} 
			
			if(req.getIsAnnualReturns() == null) {
				errors.rejectValue("isAnnualReturns", "required", new Object[] { "'isAnnualReturns'" },
						messageSource.getMessage("compliance.stat.isAnnualReturns.required",new Object[] { "'isAnnualReturns'" }, null));
			}
			else if(req.getIsAnnualReturns() < 0 || req.getIsAnnualReturns() > 2) {
				errors.rejectValue("isAnnualReturns", "invalid", new Object[] { "'isAnnualReturns'" },
						messageSource.getMessage("compliance.stat.isAnnualReturns.invalid",new Object[] { "'isAnnualReturns'" }, null));
			}
			
			if(req.getIsAnnualReturns() != null && req.getIsAnnualReturns() == 1) {
				if(req.getAnnualReturnsDate() == null) {
					errors.rejectValue("annualReturnsDate", "required", new Object[] { "'annualReturnsDate'" },
							messageSource.getMessage("compliance.stat.annualReturnsDate.required",new Object[] { "'annualReturnsDate'" }, null));
				}
				else if (req.getAnnualReturnsDate() != null) {
					Date curDt = new Date();
					if(req.getAnnualReturnsDate().compareTo(curDt) > 0) {
						errors.rejectValue("annualReturnsDate", "invalid", new Object[] { "'annualReturnsDate'" },
								messageSource.getMessage("compliance.stat.annualReturnsDate.invalid",new Object[] { "'annualReturnsDate'" }, null));
					}
				} 
				
			} 
			
			if(req.getIsInputLicense() == null) {
				errors.rejectValue("isInputLicense", "required", new Object[] { "'isInputLicense'" },
						messageSource.getMessage("compliance.stat.isInputLicense.required",new Object[] { "'isInputLicense'" }, null));
			}
			else if(req.getIsInputLicense() < 0 || req.getIsInputLicense() > 2) {
				errors.rejectValue("isInputLicense", "invalid", new Object[] { "'isInputLicense'" },
						messageSource.getMessage("compliance.stat.isInputLicense.invalid",new Object[] { "'isInputLicense'" }, null));
			}
			
			
			
		
		}
		
	}

}
