package com.nrlm.mclfmis.validation;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.StaffRequest;

@Component("staffValidator")
@PropertySource(value="classpath:messages.properties")
public class StaffFormValidator implements Validator{
	
	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> paramClass) {
		return StaffRequest.class.isAssignableFrom(paramClass);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		
		StaffRequest req = (StaffRequest) obj;
		
		if(req.getName() == null || req.getName().isEmpty()) {

			errors.rejectValue("name", "required", new Object[] { "'name'" },
					messageSource.getMessage("staff.required",new Object[] { "'name'" }, null));			
			
		}
		else if(!Pattern.matches("[A-Za-z\\s]+", req.getName())) {
			errors.rejectValue("name", "invalid", new Object[] { "'name'" },
					messageSource.getMessage("staff.invalid",new Object[] { "'name'" }, null));
		}
		
		if(req.getPhone() == null || req.getPhone().isEmpty()) {

			errors.rejectValue("phone", "required", new Object[] { "'phone'" },
					messageSource.getMessage("staff.required",new Object[] { "'phone'" }, null));			
			
		}
		else if(!Pattern.matches("[7-9]{1}//d{9}]+", req.getPhone())) {
			errors.rejectValue("name", "invalid", new Object[] { "'phone'" },
					messageSource.getMessage("staff.invalid",new Object[] { "'phone'" }, null));
		}
		
		
		if(req.getIsDraft() != ProjectConstants.IS_DRAFT) {
			
			if(req.getStaffType() == null) {
				errors.rejectValue("staffType", "required", new Object[] { "'staffType'" },
						messageSource.getMessage("staff.required",new Object[] { "'staffType'" }, null));
			}
			else if(req.getStaffType() <=0 || req.getStaffType() > 5) {
				errors.rejectValue("staffType", "invalid", new Object[] { "'staffType'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'staffType'" }, null));
			}
			
			if(req.getGender() == null) {
				errors.rejectValue("gender", "required", new Object[] { "'gender'" },
						messageSource.getMessage("staff.required",new Object[] { "'gender'" }, null));
			}
			else if(req.getGender() <=0 || req.getGender() > 3) {
				errors.rejectValue("gender", "invalid", new Object[] { "'gender'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'gender'" }, null));
			}
			
			if(req.getSocialCategory() == null) {
				errors.rejectValue("socialCategory", "required", new Object[] { "'socialCategory'" },
						messageSource.getMessage("staff.required",new Object[] { "'socialCategory'" }, null));
			}
			else if(req.getSocialCategory() <=0 || req.getSocialCategory() > 3) {
				errors.rejectValue("socialCategory", "invalid", new Object[] { "'socialCategory'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'socialCategory'" }, null));
			}
			
			if(req.getMinority() == null) {
				errors.rejectValue("minority", "required", new Object[] { "'minority'" },
						messageSource.getMessage("staff.required",new Object[] { "'minority'" }, null));
			}
			else if(req.getMinority() != 1 && req.getMinority() != 2) {
				errors.rejectValue("minority", "invalid", new Object[] { "'minority'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'minority'" }, null));
			}
			
			if(req.getDiffAble() == null) {
				errors.rejectValue("diffAble", "required", new Object[] { "'diffAble'" },
						messageSource.getMessage("staff.required",new Object[] { "'diffAble'" }, null));
			}
			else if(req.getDiffAble() != 1 && req.getDiffAble() != 2) {
				errors.rejectValue("diffAble", "invalid", new Object[] { "'diffAble'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'diffAble'" }, null));
			}
			
			if(req.getMonthRemu() == null) {
				errors.rejectValue("monthRemu", "required", new Object[] { "'monthRemu'" },
						messageSource.getMessage("staff.required",new Object[] { "'monthRemu'" }, null));
			}
			else if(req.getMonthRemu() < 1  ||  req.getMonthRemu() > 99999) {
				errors.rejectValue("monthRemu", "invalid", new Object[] { "'monthRemu'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'monthRemu'" }, null));
			}
			
			if(req.getEduQual() == null) {
				errors.rejectValue("eduQual", "required", new Object[] { "'eduQual'" },
						messageSource.getMessage("staff.required",new Object[] { "'eduQual'" }, null));
			}
			else if(req.getEduQual() < 1 || req.getEduQual() < 7) {
				errors.rejectValue("eduQual", "invalid", new Object[] { "'eduQual'" },
						messageSource.getMessage("staff.invalid",new Object[] { "'eduQual'" }, null));
			}
			if(req.getExpYrs() <0 || req.getExpYrs() > 99) {
							errors.rejectValue("expYrs", "negativeValue", new Object[] { "expYrs" },
									messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'expYrs'" },null));
						}
						if(countWords(req.getExpDesc()) > 150) {
							errors.rejectValue("expDesc", "negativeValue", new Object[] { "expDesc" },
									messageSource.getMessage("subcommittee.expDesc.exceeds",new Object[] { "'expDesc'" },null));
						}
						if(req.getJoinDate().after(new Date()) ) {
							errors.rejectValue("joinDate", "negativeValue", new Object[] { "joinDate" },
									messageSource.getMessage("subcommittee.joindate.future",new Object[] { "'joinDate'" },null));
						}
						if(req.getLeaveDate().after(new Date()) ) {
							errors.rejectValue("leaveDate", "negativeValue", new Object[] { "leaveDate" },
									messageSource.getMessage("subcommittee.joindate.future",new Object[] { "'leaveDate'" },null));
						}
						if(req.getLeaveReason() < 0 || req.getLeaveReason() == null) {
							errors.rejectValue("leaveReason", "negativeValue", new Object[] { "leaveReason" },
									messageSource.getMessage("subcommittee.anyOther.invalid",new Object[] { "'leaveReason'" },null));
						}
						if(req.getLeaveReason()== ProjectConstants.STAFF_RSN_AnyOther) {
							ValidationUtils.rejectIfEmptyOrWhitespace(errors, "otherRes", "nav", messageSource.getMessage("subcommittee.clfCode.notNull",new Object[] { "'otherRes'" },null));
							
						}
						
						
					}
					
			
		}
		
		//update case
//		if(req.getSectionId() !=  null) {
//			
//			if(req.getSectionId() <= 0) {
//				errors.rejectValue("sectionId", "required", new Object[] { "'sectionId'" },
//						messageSource.getMessage("section.sectionId.invalid",new Object[] { "'sectionId'" }, null));
//			}
//			
//			if(req.getStatus() == null ) {
//				errors.rejectValue("status", "required", new Object[] { "'status'" },
//						messageSource.getMessage("section.status.required",new Object[] { "'status'" }, null));
//			}
//			if(req.getStatus() != 1 && req.getStatus() != 0) {
//				
//				errors.rejectValue("status", "required", new Object[] { "'status'" },
//						messageSource.getMessage("section.status.invalid",new Object[] { "'status'" }, null));
//
//			}
//			if(req.getIsSkip() == null ) {
//				
//				errors.rejectValue("isSkip", "required", new Object[] { "'isSkip'" },
//						messageSource.getMessage("section.isSkip.required",new Object[] { "'isSkip'" }, null));
//			}
//			if(req.getIsSkip() != 1 && req.getIsSkip() != 2) {
//				
//				errors.rejectValue("isSkip", "required", new Object[] { "'isSkip'" },
//						messageSource.getMessage("section.isSkip.invalid",new Object[] { "'isSkip'" }, null));
//			}
//			
//		}
		
		
	

public boolean isPhone(String num) {
		String strPattern = "^[0-9]{10}$";
		if(num.matches(strPattern)) {
			return true;
		}
		return false;
	}
	
	public static int countWords(String input) { 
		if (input == null || input.isEmpty()) {
			return 0;
			} 
		String[] words = input.split("\\s");
		return words.length; }


}
