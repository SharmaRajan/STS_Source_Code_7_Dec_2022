package com.nrlm.mclfmis.validation;

import java.util.regex.Pattern;

import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Request.IndicatorFormRequest;

@Component("indicatorValidator")
@PropertySource(value="classpath:messages.properties")
public class IndicatorFormValidator implements Validator {

	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return IndicatorFormRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		IndicatorFormRequest ind = (IndicatorFormRequest) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indctrName","indicator.indctrName.required", new Object[] {"indctrName"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sectionId", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sequence", "nav", messageSource.getMessage("indicator.indctrName.required",new Object[] { "'SectionId'" },null));
		
		
		if (ind.getSectionId() != null && ind.getSectionId() <= 0) {
			
			errors.rejectValue("sectionId", "negativeValue", new Object[] { "'sectionId'" },
					messageSource.getMessage("indicator.sectionId.negativeValue",new Object[] { "'sectionId'" }, null));
		} 
		
		if (ind.getIndctrType() == null || ind.getIndctrType()<0) {

			 errors.rejectValue("indctrType", "negativeValue", new Object[] { "'indctrType'" },

			 messageSource.getMessage("indicator.sectionId.negativeValue",new Object[] { "'indctrType'" }, null));

			 }

			 if (ind.getIndctrType()>7) {

			 errors.rejectValue("indctrType", "negativeValue", new Object[] { "'indctrType'" },

			 messageSource.getMessage("section.sectionName.invalid",new Object[] { "'indctrType'" }, null));

			 }
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mandatory", "indicator.mandatory.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indctrType", "indicator.indctrType.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startMonth", "indicator.startMonth.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startFnclYear", "indicator.startFnclYear.required");
		
		if(ind.getStartMonth()==null || ind.getStartMonth()<=0) {
						errors.rejectValue("startMonth", "negativeValue", new Object[] { "'startMonth'" },
								messageSource.getMessage("section.sectionName.invalid",new Object[] { "'startMonth'" }, null));
					}
					if(ind.getStartFnclYear()==null || ind.getStartFnclYear()<=0) {
						errors.rejectValue("startFnclYear", "negativeValue", new Object[] { "'startFnclYear'" },
								messageSource.getMessage("section.sectionName.invalid",new Object[] { "'startFnclYear'" }, null));
					}
		
		if(ind.getIndctrName() != null && !Pattern.matches("[\\w\\s]+", ind.getIndctrName())) {
			errors.rejectValue("indctrName", "negativeValue", new Object[] { "'indctrName'" },
					messageSource.getMessage("section.sectionName.invalid",new Object[] { "'indctrName'" }, null));
		}
		if(ind.getDescription() != null && !Pattern.matches("[\\w\\s]+", ind.getDescription())) {
			errors.rejectValue("description", "negativeValue", new Object[] { "'description'" },
					messageSource.getMessage("section.sectionName.invalid",new Object[] { "'description'" }, null));
		}
		if(ind.getMinValue() != null && !Pattern.matches("[\\w\\s]+", ind.getMinValue())) {
			errors.rejectValue("minValue", "negativeValue", new Object[] { "'minValue'" },
					messageSource.getMessage("section.sectionName.invalid",new Object[] { "'minValue'" }, null));
		}
		if(ind.getMaxValue() != null && !Pattern.matches("[\\w\\s]+", ind.getMaxValue())) {
			errors.rejectValue("maxValue", "negativeValue", new Object[] { "'maxValue'" },
					messageSource.getMessage("section.sectionName.invalid",new Object[] { "'maxValue'" }, null));
		}
		

//		if (ind.getSequence() != null && ind.getSequence() < 0) {
//			errors.rejectValue("sequence", "negativeValue", new Object[] { "'Sequence'" },
//					"indicator.sequence.negativeValue");
//		}
		if (ind.getMandatory() != null && ind.getMandatory() < 0) {
			
			errors.rejectValue("mandatory", "negativeValue", new Object[] { "'mandatory'" },
					messageSource.getMessage("indicator.mandatory.negativeValue",new Object[] { "'mandatory'" }, null));
			
		}
		if (ind.getIndctrType() != null && ind.getIndctrType() == 1) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minValue", "indicator.minValue.required");
			
		}
	}
}
