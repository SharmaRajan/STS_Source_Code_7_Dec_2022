package com.nic.nrlm_aajeevika.validator;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.nic.nrlm_aajeevika.data.MapperImageSlider;
import com.nic.nrlm_aajeevika.data.MyData;
import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.request.TickerPopupSliderRequest;

@Component("CustomValidation")
@PropertySource(value = "classpath:messages.properties")
public class TickerValidation implements Validator {

//	@Autowired
//	TickerPopupSliderRequest tickerrequestt; 
//	
	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return TickerPopupSliderRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("-----------in validation-------------------------");
		
		//TickerPopupSliderRequest ind = (TickerPopupSliderRequest)target;
		
		MapperImageSlider inda = (MapperImageSlider) target;
		
		TickerPopupSliderRequest ind = (TickerPopupSliderRequest)inda.getRequest();

		MultipartFile file = inda.getFile();
		
		if (ind.getContentType() == "" || ind.getContentType() == null || ind.getContentType().isEmpty()) {
			errors.rejectValue("contentType", "required", new Object[] { "contentType" },
					messageSource.getMessage("contentType.required", new Object[] { "'contentType'" }, null));
		} else {
			if (ind.getContentType().equals("ticker")) {

				if (ind.getTickerName() == ""  || ind.getTickerName() == null || ind.getTickerName().isEmpty()) {
					errors.rejectValue("tickerName", "ticker name required", new Object[] { "tickerName" },
							messageSource.getMessage("tickerName.required", new Object[] { "'tickerName'" }, null));
				}

			}

			if (ind.getContentType().equals("image")) {

				if (file==null ) {
					errors.rejectValue("file_url","required", "Please provide file");
				}
				if (ind.getRanking() == null) {
					errors.rejectValue("ranking", "please provide ranking required", new Object[] { "ranking" },
							messageSource.getMessage("ranking.required", new Object[] { "'ranking'" }, null));
				}

			}

			if (ind.getContentType().equals("popup")) {

				if (file==null) {
					errors.rejectValue("file_url","required", "Please provide file");
				}

			}
		}
	}

}
