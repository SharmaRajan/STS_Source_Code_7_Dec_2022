package com.nrlm.mclfmis.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IndicatorValidator implements ConstraintValidator<CompositeUnique, String> {

	@Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return true;

    }
}
