package com.nrlm.mclfmis.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Request.CmtcRequest;

@Component("cmtcValidator")
@PropertySource(value="classpath:messages.properties")
public class TrainingCmtcFormValidator implements Validator{
	

	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return CmtcRequest.class.isAssignableFrom(paramClass);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		
		CmtcRequest ind = (CmtcRequest) obj;
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clfCode", "nav", messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'clfCode'" },null));
		
				
				if(ind.getCmtcEst() ==1 || ind.getCmtcEst()==2) {
					if(ind.getCmtcEst()==2){
						if(!(ind.getCmtcDate()!=null)) {
							errors.rejectValue("cmtcDate", "negativeValue", new Object[] { "cmtcDate" },
									messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'cmtcDate'" },null));
						}
						if(!(ind.getCmtcFunc()!=null)) {
							errors.rejectValue("cmtcFunc", "negativeValue", new Object[] { "cmtcFunc" },
									messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'cmtcFunc'" },null));
						}
						if(!(ind.getCmtcFunc()==1 || ind.getCmtcFunc() == 2)) {
							errors.rejectValue("cmtcFunc", "negativeValue", new Object[] { "cmtcFunc" },
									messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'cmtcFunc'" },null));
						}
					}
					else {
						if(ind.getCmtcDate()!=null ) {
							errors.rejectValue("cmtcDate", "negativeValue", new Object[] { "cmtcDate" },
									messageSource.getMessage("subcommittee.anyOther.notallowed",new Object[] { "'cmtcDate'" },null));
						if(ind.getCmtcFunc() !=null) {
							errors.rejectValue("cmtcFunc", "negativeValue", new Object[] { "cmtcFunc" },
									messageSource.getMessage("subcommittee.anyOther.notallowed",new Object[] { "'cmtcFunc'" },null));
							}
						}
					}
					
				}else {
					errors.rejectValue("cmtcEst", "negativeValue", new Object[] { "cmtcEst" },
							messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'cmtcEst'" },null));
				}
		
		
		
		
		
	}




}
