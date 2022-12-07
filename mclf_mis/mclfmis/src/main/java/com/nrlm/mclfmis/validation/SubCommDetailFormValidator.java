package com.nrlm.mclfmis.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Request.SubCommDetailsRequest;

@Component("subCommDetailValidator")
@PropertySource(value="classpath:messages.properties")
public class SubCommDetailFormValidator implements Validator {



	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return SubCommDetailsRequest.class.isAssignableFrom(paramClass);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		SubCommDetailsRequest ind = (SubCommDetailsRequest) obj;
				
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "clfCode", "nav", messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'clfCode'" },null));
		
				if(ind.getAnyOther()==1 || ind.getAnyOther()==2) {
					
					if(ind.getAnyOther()==1){
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anyOtherName", "nav", messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'anyOtherName'" },null));
						if(!(ind.getAnyOtherMemb()>=0 && ind.getAnyOtherMemb() <= 999)) {
							errors.rejectValue("anyOtherMemb", "negativeValue", new Object[] { "anyOtherMemb" },
									messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'anyOtherMemb'" },null));
						}
						else if(!Pattern.matches("[\\w\\s]+", ind.getAnyOtherName())) {
							errors.rejectValue("anyOtherName", "required", new Object[] { "'anyOtherName'" },
									messageSource.getMessage("section.sectionName.required",new Object[] { "'anyOtherName'" }, null));
						}
					}
					else {
						if(ind.getAnyOtherMemb()!= null && !ind.getAnyOtherName().isEmpty()) {
							errors.rejectValue("anyOtherMemb", "negativeValue", new Object[] { "anyOtherMemb" },
									messageSource.getMessage("subcommittee.anyOther.notallowed",new Object[] { "'anyOtherMemb'" },null));
							errors.rejectValue("anyOtherName", "negativeValue", new Object[] { "anyOtherName" },
									messageSource.getMessage("subcommittee.anyOther.notallowed",new Object[] { "'anyOtherName'" },null));
						}
					}
					
				}
				else {
					errors.rejectValue("anyOther", "negativeValue", new Object[] { "anyOther" },
							messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'anyOther'" },null));
				}
				
				if(!(ind.getMoniMemb()>=0 && ind.getMoniMemb() <= 999)) {
					errors.rejectValue("moniMemb", "negativeValue", new Object[] { "moniMemb" },
							messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'moniMemb'" },null));
				}
				if(!(ind.getClfAstMemb()>=0 && ind.getClfAstMemb() <= 999)) {
					errors.rejectValue("clfAstMemb", "negativeValue", new Object[] { "clfAstMemb" },
							messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'clfAstMemb'" },null));
				}
				if(!(ind.getLivHoodMemb()>=0 && ind.getLivHoodMemb() <= 999)) {
					errors.rejectValue("livHoodMemb", "negativeValue", new Object[] { "livHoodMemb" },
							messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'livHoodMemb'" },null));
				}
				if(!(ind.getBnkLinkMemb()>=0 && ind.getBnkLinkMemb() <= 999)) {
					errors.rejectValue("bnkLinkMemb", "negativeValue", new Object[] { "bnkLinkMemb" },
							messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'bnkLinkMemb'" },null));
				}
				if(!(ind.getSocActMemb()>=0 && ind.getSocActMemb() <= 999)) {
					errors.rejectValue("socActMemb", "negativeValue", new Object[] { "socActMemb" },
							messageSource.getMessage("subcommittee.anyOtherMemb.range",new Object[] { "'socActMemb'" },null));
				}
		
	}






}
