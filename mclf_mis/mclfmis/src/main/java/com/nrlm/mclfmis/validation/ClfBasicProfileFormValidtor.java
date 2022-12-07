package com.nrlm.mclfmis.validation;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Entity.ClfBasicProfile;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfBasicProfileRepository;
import com.nrlm.mclfmis.Request.ClfBasicProfileRequest;

@Component("clfbasicProfileValidator")
@PropertySource(value="classpath:messages.properties")
public class ClfBasicProfileFormValidtor implements Validator {

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private ClfBasicProfileRepository clfBasicProfileRepository;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		return ClfBasicProfileRequest.class.isAssignableFrom(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClfBasicProfileRequest req = (ClfBasicProfileRequest) obj;
		
		List<ClfBasicProfile> ent = null;
				

		
		if(req.getClfCode() == null || req.getClfCode().trim().isEmpty()) {
			errors.rejectValue("clfCode", "required", new Object[] { "'clfCode'" },
					messageSource.getMessage("clfbasic.clfcode.required",new Object[] { "'clfCode'" }, null));
		}
		else if(req.getClfCode().contains(" ")) {
			errors.rejectValue("clfCode", "invalid", new Object[] { "'clfCode'" },
					messageSource.getMessage("clfbasic.clfCode.invalid",new Object[] { "'clfCode'" }, null));
		}
		else if(!Pattern.matches("[\\w\\s]+", req.getClfCode())) {
			errors.rejectValue("clfCode", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'clfCode'" },null));	
		}
		
		if(req.getRecgnDate() != null && !Pattern.matches("[\\w\\s]+", req.getRecgnDate())) {
			errors.rejectValue("recgnDate", messageSource.getMessage("section.sectionName.invalid",new Object[] { "'recgnDate'" },null));	
		}
		
		
		

		
		
		
		
		
		
		if(req.getDraft() != ProjectConstants.IS_DRAFT) {
			
			if(req.getClfRecgn() == null) {
				errors.rejectValue("clfRecgn", "required", new Object[] { "'clfRecgn'" },
						messageSource.getMessage("clfbasic.clfRecgn.required",new Object[] { "'clfRecgn'" }, null));
			}
			else if(req.getClfRecgn() != 1 && req.getClfRecgn() != 2) {
				errors.rejectValue("clfRecgn", "invalid", new Object[] { "'clfRecgn'" },
						messageSource.getMessage("clfbasic.clfRecgn.invalid",new Object[] { "'clfRecgn'" }, null));
			}
			if(req.getNpName() == null || req.getNpName().trim().isEmpty()) {
				errors.rejectValue("clfRecgn", "required", new Object[] { "'clfRecgn'" },
						messageSource.getMessage("clfbasic.clfRecgn.required",new Object[] { "'clfRecgn'" }, null));
			}
			else if(!Pattern.matches("[a-zA-Z\\s]+", req.getNpName())) {
				errors.rejectValue("npName", "invalid", new Object[] { "'npName'" },
						messageSource.getMessage("clfbasic.npName.invalid",new Object[] { "'npName'" }, null));
			}
			if(req.getNpDesgn() == null) {
				errors.rejectValue("npDesgn", "required", new Object[] { "'npDesgn'" },
						messageSource.getMessage("clfbasic.npDesgn.required",new Object[] { "'npDesgn'" }, null));
			}
			else if(req.getNpDesgn() < 1 || req.getNpDesgn() > 6) {
				errors.rejectValue("npDesgn", "invalid", new Object[] { "'npDesgn'" },
						messageSource.getMessage("clfbasic.npDesgn.invalid",new Object[] { "'npDesgn'" }, null));
			}
			
			if(req.getNpDesgn() != null) {
			
				if(req.getNpDesgn() == 6) {
					
					if(req.getOtherDesgn() == null || req.getOtherDesgn().trim().isEmpty()) {
						errors.rejectValue("otherDesgn", "required", new Object[] { "'otherDesgn'" },
								messageSource.getMessage("clfbasic.otherDesgn.required",new Object[] { "'otherDesgn'" }, null));
					}
					else if(!Pattern.matches("[a-zA-Z\\s]+", req.getOtherDesgn())) {
						errors.rejectValue("otherDesgn", "invalid", new Object[] { "'otherDesgn'" },
								messageSource.getMessage("clfbasic.otherDesgn.invalid",new Object[] { "'otherDesgn'" }, null));	
					}
				
				}
				else {
					if(req.getOtherDesgn() != null && !req.getOtherDesgn().trim().isEmpty()) {
						errors.rejectValue("otherDesgn", "invalid", new Object[] { "'otherDesgn'" },
								messageSource.getMessage("clfbasic.otherDesgn.invalid",new Object[] { "'otherDesgn'" }, null));
					}
				}
				
			}
			
			
			if(req.getOfficeStatus() == null) {
				
				errors.rejectValue("officeStatus", "required", new Object[] { "'officeStatus'" },
						messageSource.getMessage("clfbasic.officeStatus.required",new Object[] { "'officeStatus'" }, null));
			}
			else if(req.getOfficeStatus() < 1 || req.getOfficeStatus() > 3) {
				
				errors.rejectValue("officeStatus", "invalid", new Object[] { "'officeStatus'" },
						messageSource.getMessage("clfbasic.officeStatus.invalid",new Object[] { "'officeStatus'" }, null));
			}
			
			ent  = clfBasicProfileRepository.findByTan(req.getTan());
			if(req.getTan() != null && !req.getTan().isEmpty() && !Pattern.matches("[a-zA-Z0-9]{10}", req.getTan())) {
				
				errors.rejectValue("tan", "invalid", new Object[] { "'tan'" },
						messageSource.getMessage("clfbasic.tan.invalid",new Object[] { "'tan'" }, null));
				
			}
			else if(!ent.isEmpty() && !ent.stream().anyMatch(s->s.getClfCode().equals(req.getClfCode()))) {
				errors.rejectValue("tan", "invalid", new Object[] { "'tan'" },
						messageSource.getMessage("clfbasic.pan.unique",new Object[] { "'tan'" }, null));
				ent=null;
			}
			
			ent  = clfBasicProfileRepository.findByPan(req.getPan());
			
			if(req.getPan() != null && !req.getPan().isEmpty() && !Pattern.matches("[a-zA-Z0-9]{10}", req.getPan())) {
							errors.rejectValue("pan", "invalid", new Object[] { "'pan'" },
									messageSource.getMessage("clfbasic.pan.invalid",new Object[] { "'pan'" }, null));
						}
			else if(!ent.isEmpty() && !ent.stream().anyMatch(s->s.getClfCode().equals(req.getClfCode()))) {
				errors.rejectValue("pan", "invalid", new Object[] { "'pan'" },
						messageSource.getMessage("clfbasic.pan.unique",new Object[] { "'pan'" }, null));
				ent=null;
			}
			ent=clfBasicProfileRepository.findByGstin(req.getGstin());
			if(req.getGstin() != null && !req.getGstin().isEmpty() && !Pattern.matches("[a-zA-Z0-9]{15}", req.getGstin())) {
				
				errors.rejectValue("gstin", "invalid", new Object[] { "'gstin'" },
						messageSource.getMessage("clfbasic.gstin.invalid",new Object[] { "'gstin'" }, null));
			}
			else if(!ent.isEmpty() && !ent.stream().anyMatch(s->s.getClfCode().equals(req.getClfCode()))) {
				errors.rejectValue("gstin", "invalid", new Object[] { "'gstin'" },
						messageSource.getMessage("clfbasic.pan.unique",new Object[] { "'gstin'" }, null));
				ent=null;
			}
			
			if(req.getFinYearId() == null) {
				errors.rejectValue("finYearId", "required", new Object[] { "'finYearId'" },
						messageSource.getMessage("clfbasic.finYearId.required",new Object[] { "'finYearId'" }, null));
			}
			else if(req.getFinYearId() <= 0) {
				errors.rejectValue("finYearId", "invalid", new Object[] { "'finYearId'" },
						messageSource.getMessage("clfbasic.finYearId.invalid",new Object[] { "'finYearId'" }, null));
			}
			
			if(req.getMonthId() == null) {
				errors.rejectValue("monthId", "required", new Object[] { "'monthId'" },
						messageSource.getMessage("clfbasic.monthId.required",new Object[] { "'monthId'" }, null));
			}
			else if(req.getMonthId() <= 0) {
				errors.rejectValue("monthId", "invalid", new Object[] { "'finYearId'" },
						messageSource.getMessage("clfbasic.monthId.invalid",new Object[] { "'monthId'" }, null));
			}
			
		}
		
	}

}
