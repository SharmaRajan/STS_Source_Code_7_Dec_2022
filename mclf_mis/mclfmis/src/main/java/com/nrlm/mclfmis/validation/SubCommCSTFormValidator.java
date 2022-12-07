package com.nrlm.mclfmis.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Request.SubCommCSTRequest;

@Component("cstValidator")
@PropertySource(value="classpath:messages.properties")
public class SubCommCSTFormValidator implements Validator {


	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return SubCommCSTRequest.class.isAssignableFrom(paramClass);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		
		SubCommCSTRequest ind = (SubCommCSTRequest) obj;
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clfCode", "nav", messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'clfCode'" },null));
				
				if(ind.getCstFormed()==1 || ind.getCstFormed()==2) {
					if(ind.getCstFormed()==1){
						if(!(ind.getTotCstMemb()>=0 && ind.getTotCstMemb() <= 999)) {
							errors.rejectValue("totCstMemb", "negativeValue", new Object[] { "totCstMemb" },
									messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'totCstMemb'" },null));
						}
					}
					else {
						if(ind.getTotCstMemb()!= null ) {
							errors.rejectValue("totCstMemb", "negativeValue", new Object[] { "totCstMemb" },
									messageSource.getMessage("subcommittee.anyOther.notallowed",new Object[] { "'totCstMemb'" },null));
						}
					}
					
				}else {
					errors.rejectValue("cstFormed", "negativeValue", new Object[] { "cstFormed" },
							messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'cstFormed'" },null));
				}
		
	}




}
