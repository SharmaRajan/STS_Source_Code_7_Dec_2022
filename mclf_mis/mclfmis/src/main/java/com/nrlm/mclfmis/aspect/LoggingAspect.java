package com.nrlm.mclfmis.aspect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Helper.UtilFunctions;
import com.nrlm.mclfmis.Request.ClfBasicProfileRequest;
import com.nrlm.mclfmis.Request.ClfPoliciesRequest;
import com.nrlm.mclfmis.Request.CmtcRequest;
import com.nrlm.mclfmis.Request.CutOffFormRequest;
import com.nrlm.mclfmis.Request.CutOffMonthlyFormRequest;
import com.nrlm.mclfmis.Request.IndicatorFormRequest;
import com.nrlm.mclfmis.Request.IndicatorSequenceRequest;
import com.nrlm.mclfmis.Request.InternalControlRequest;
import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.SectionSequenceRequest;
import com.nrlm.mclfmis.Request.StaffRequest;
import com.nrlm.mclfmis.Request.StatutoryRequest;
import com.nrlm.mclfmis.Request.SubCommCSTRequest;
import com.nrlm.mclfmis.Request.SubCommDetailsRequest;
import com.nrlm.mclfmis.Request.TrainingRequest;
import com.nrlm.mclfmis.Response.ExceptionResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.ValidateResponse;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.exception.ValidationHandledException;

@Aspect  
@Component  
public class LoggingAspect {
	
	@Value("${log.file.path}")
	private String filePath;

	SimpleDateFormat logDateformat = new SimpleDateFormat("dd-MMM-yy");
	
	SimpleDateFormat logDateTimeformat = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
	
	private static File file;
	private static FileWriter fileWriter;
	private static BufferedWriter bufferWriter ;
	
	@Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {
    }
	
	@Pointcut("within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
    public void controlleradvice() {
    }
 
    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }

	
	//SECTION CONTROLLER
	
	//@Before(value = "execution(* com.nrlm.mclfmis.Controllers.SectionController.createSection(..)) && args(request,..)")  
	//public void createSectionAdvice(JoinPoint joinPoint, SectionRequest request) {  
	//	System.out.println("section request");
		//aspectexe("section.txt","Section Create",request,"request");
	
	//}  
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SectionController.createSection(..)) && args(request,..)")
    public ResponseEntity<?> logAround(ProceedingJoinPoint joinPoint,SectionRequest request) throws Throwable {
		aspectexe("section.txt","Section Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("section.txt","Section Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SectionController.createSection(..))", throwing = "exception")
	public void logAfterException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("section.txt","Section Create",exception,"/mclf/section/create");
		
	}
	

	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SectionController.updateSectionNew(..)) && args(sectionId,request,..)")
    public ResponseEntity<?> updateSectionAdvice(ProceedingJoinPoint joinPoint,Long sectionId, SectionRequest request) throws Throwable {
		request.setSectionId(sectionId);
		aspectexe("section.txt","Section Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("section.txt","Section Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SectionController.updateSectionNew(..))", throwing = "exception")
	public void updateSectionAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("section.txt","Section Update",exception,"/mclf/section/update");
		
	}
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SectionController.updateSectionSequenceList(..)) && args(request,..)")
    public ResponseEntity<?> updateSectionSequenceListAdvice(ProceedingJoinPoint joinPoint,List<SectionSequenceRequest> request) throws Throwable {
		aspectexe("section.txt","Section Sequence Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("section.txt","Section Sequence Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SectionController.updateSectionSequenceList(..))", throwing = "exception")
	public void updateSectionSequenceListAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("section.txt","Section Sequence Update",exception,"/mclf/section/sequence/update");
		
	}
	
	//INDICATOR CONTROLLER
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.IndicatorController.saveIndicator(..)) && args(indirequest,..)")
    public ResponseEntity<?> saveIndicatorAdvice(ProceedingJoinPoint joinPoint,IndicatorFormRequest indirequest) throws Throwable {
		aspectexe("Indicator.txt","Indicator Create",indirequest,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Indicator.txt","Indicator Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.IndicatorController.saveIndicator(..))", throwing = "exception")
	public void saveIndicatorAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Indicator.txt","Indicator Create",exception,"/mclf/indicator/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.IndicatorController.updateIndicator(..)) && args(indicatorId,request,..)")
    public ResponseEntity<?> updateIndicatorAdvice(ProceedingJoinPoint joinPoint,Long indicatorId, IndicatorFormRequest request) throws Throwable {
		String str = "Indicator Update : Id=" + indicatorId;
		aspectexe("Indicator.txt",str,request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Indicator.txt",str,result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.IndicatorController.updateIndicator(..))", throwing = "exception")
	public void updateIndicatorAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("Indicator.txt","Indicator Update",exception,"/mclf/indicator/update");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.IndicatorController.updateIndicatorSequenceList(..)) && args(request,..)")
    public ResponseEntity<?> updateIndicatorSequenceListAdvice(ProceedingJoinPoint joinPoint,List<IndicatorSequenceRequest> request) throws Throwable {
		aspectexe("Indicator.txt","Indicator Sequence Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Indicator.txt","Indicator Sequence Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.IndicatorController.updateIndicatorSequenceList(..))", throwing = "exception")
	public void updateIndicatorSequenceListAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("Indicator.txt","Indicator Sequence Update",exception,"/mclf/indicator/sequence/update");
		
	}
	
	
	//ClfBasicProfileController

	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ClfBasicProfileController.saveClfBasicprofile(..)) && args(clfbasic,..)")
    public ResponseEntity<?> saveClfBasicprofileAdvice(ProceedingJoinPoint joinPoint,ClfBasicProfileRequest clfbasic) throws Throwable {
		aspectexe("ClfBasicProfile.txt","CLF Basic Create",clfbasic,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("ClfBasicProfile.txt","CLF Basic Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ClfBasicProfileController.saveClfBasicprofile(..))", throwing = "exception")
	public void saveClfBasicprofileAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("ClfBasicProfile.txt","CLF Basic Create",exception,"/mclf/indicator/sequence/update");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ClfBasicProfileController.updateProfile(..)) && args(clfCode,request,..)")
    public ResponseEntity<?> updateProfileAdvice(ProceedingJoinPoint joinPoint,String clfCode,ClfBasicProfileRequest request) throws Throwable {
		request.setClfCode(clfCode);
		aspectexe("ClfBasicProfile.txt","CLF Basic Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("ClfBasicProfile.txt","CLF Basic Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ClfBasicProfileController.updateProfile(..))", throwing = "exception")
	public void updateProfileAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("ClfBasicProfile.txt","CLF Basic Update",exception,"/mclf/indicator/sequence/update");
		
	}
	
	//CutOffController
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.CutOffController.saveCutOffData(..)) && args(request,..)")
    public ResponseEntity<?> saveCutOffDataAdvice(ProceedingJoinPoint joinPoint,CutOffFormRequest request) throws Throwable {
		aspectexe("CutOff.txt","CutOffData Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("CutOff.txt","CutOffData Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.CutOffController.saveCutOffData(..))", throwing = "exception")
	public void saveCutOffDataAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("CutOff.txt","CutOffData Create",exception,"/mclf/indicator/sequence/update");
		
	}
	
	//CutOffMonthlyController
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.CutOffMonthlyController.saveCutOffMonthlyData(..)) && args(request,..)")
    public ResponseEntity<?> saveCutOffMonthlyDataAdvice(ProceedingJoinPoint joinPoint,CutOffFormRequest request) throws Throwable {
		aspectexe("CutOffMonthly.txt","CutOffMonthlyData Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("CutOffMonthly.txt","CutOffMonthlyData Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.CutOffMonthlyController.saveCutOffMonthlyData(..))", throwing = "exception")
	public void saveCutOffMonthlyDataAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("CutOffMonthly.txt","CutOffMonthlyData Create",exception,"/mclf/indicator/sequence/update");
		
	}
	
		
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.CutOffMonthlyController.saveCutOffMonthYearData(..)) && args(request,..)")
    public ResponseEntity<?> saveCutOffMonthYearDataAdvice(ProceedingJoinPoint joinPoint,CutOffMonthlyFormRequest request) throws Throwable {
		aspectexe("CutOffMonthly.txt","CutOffMonthlyData Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("CutOffMonthly.txt","CutOffMonthYearData Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.CutOffMonthlyController.saveCutOffMonthYearData(..))", throwing = "exception")
	public void saveCutOffMonthYearDataAdviceException(JoinPoint joinPoint, Exception exception) {
		processExceptionAspect("CutOffMonthly.txt","CutOffMonthYearData Create",exception,"/mclf/indicator/sequence/update");
		
	}
	
	
	
	
	//STAFF CONTROLLER
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.StaffController.createStaff(..)) && args(staffId,request,..)")
    public ResponseEntity<?> createStaffAdvice(ProceedingJoinPoint joinPoint, Long staffId, StaffRequest request) throws Throwable {
		aspectexe("Staff.txt","Staff Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Staff.txt","Staff Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.StaffController.createStaff(..))", throwing = "exception")
	public void createStaffAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Staff.txt","Staff Create",exception,"/mclf/section/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.StaffController.updateStaff(..)) && args(staffId,request,..)")
    public ResponseEntity<?> updateStaffAdvice(ProceedingJoinPoint joinPoint, Long staffId, StaffRequest request) throws Throwable {
		request.setId(staffId);
		aspectexe("Staff.txt","Staff Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Staff.txt","Staff Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.StaffController.updateStaff(..))", throwing = "exception")
	public void updateStaffAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Staff.txt","Staff Update",exception,"/mclf/section/create");
		
	}
	
	
	//SUBCOMM CONTROLLER
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.createSubComm(..)) && args(request)")
    public ResponseEntity<?> createSubCommAdvice(ProceedingJoinPoint joinPoint,SubCommDetailsRequest request) throws Throwable {
		aspectexe("SubCommittee.txt","SubCommittee Details Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("SubCommittee.txt","SubCommittee Details Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.createSubComm(..))", throwing = "exception")
	public void createSubCommAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("SubCommittee.txt","SubCommittee Details Create",exception,"/mclf/section/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.updateSubComm(..)) && args(id,request)")
    public ResponseEntity<?> updateSubCommAdvice(ProceedingJoinPoint joinPoint,Long id, SubCommDetailsRequest request) throws Throwable {
		request.setId(id);
		aspectexe("SubCommittee.txt","SubCommittee Details Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("SubCommittee.txt","SubCommittee Details Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.updateSubComm(..))", throwing = "exception")
	public void updateSubCommAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("SubCommittee.txt","SubCommittee Details Update",exception,"/mclf/section/create");
		
	}
	
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.createCST(..)) && args(request)")
    public ResponseEntity<?> createCSTAdvice(ProceedingJoinPoint joinPoint,SubCommCSTRequest request) throws Throwable {
		aspectexe("SubCommittee.txt","SubCommittee CST Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("SubCommittee.txt","SubCommittee CST Create",result.getBody(),"response");
		return result;
    }
	

	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.createCST(..))", throwing = "exception")
	public void createCSTAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("SubCommittee.txt","SubCommittee CST Create",exception,"/mclf/section/create");
		
	}
	

	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.updateCST(..)) && args(id,request)")
    public ResponseEntity<?> updateCSTAdvice(ProceedingJoinPoint joinPoint,Long id, SubCommDetailsRequest request) throws Throwable {
		request.setId(id);
		aspectexe("SubCommittee.txt","SubCommittee CST Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("SubCommittee.txt","SubCommittee CST Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.SubCommController.updateCST(..))", throwing = "exception")
	public void updateCSTAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("SubCommittee.txt","SubCommittee CST Update",exception,"/mclf/section/create");
		
	}
	
	
	// TRAINING CONTROLLER

	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.createCmtc(..)) && args(cmtcId,request)")
    public ResponseEntity<?> createCmtcAdvice(ProceedingJoinPoint joinPoint,Long cmtcId, CmtcRequest request) throws Throwable {
		aspectexe("Training.txt","CMTC Details Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Training.txt","CMTC Details Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.createCmtc(..))", throwing = "exception")
	public void createCmtcAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Training.txt","CMTC Details Create",exception,"/mclf/section/create");
		
	}
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.updateCmtc(..)) && args(cmtcId,cmtcreq)")
    public ResponseEntity<?> updateCmtcAdvice(ProceedingJoinPoint joinPoint,Long cmtcId, CmtcRequest cmtcreq) throws Throwable {
		cmtcreq.setId(cmtcId);
		aspectexe("Training.txt","CMTC Details Update",cmtcreq,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Training.txt","CMTC Details Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.updateCmtc(..))", throwing = "exception")
	public void updateCmtcAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Training.txt","CMTC Details Update",exception,"/mclf/section/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.createTraining(..)) && args(trainId,request)")
    public ResponseEntity<?> createTrainingAdvice(ProceedingJoinPoint joinPoint,Long trainId, TrainingRequest request) throws Throwable {
		aspectexe("Training.txt","Training Details Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Training.txt","Training Details Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.createTraining(..))", throwing = "exception")
	public void createTrainingAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Training.txt","Training Details Update",exception,"/mclf/section/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.updateTraining(..)) && args(trainingId,request)")
    public ResponseEntity<?> updateTrainingAdvice(ProceedingJoinPoint joinPoint,Long trainingId, TrainingRequest request) throws Throwable {
		request.setId(trainingId);
		aspectexe("Training.txt","Training Details Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Training.txt","Training Details Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.TrainingController.updateTraining(..))", throwing = "exception")
	public void updateTrainingAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Training.txt","Training Details Update",exception,"/mclf/section/create");
		
	}
	
	
	//ComplianceController
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.createClfPolicies(..)) && args(id,clfRequest,..)")
    public ResponseEntity<?> createClfPoliciesAdvice(ProceedingJoinPoint joinPoint,String id ,ClfPoliciesRequest clfRequest) throws Throwable {
		aspectexe("Compliance.txt","ClfPolicies Create",clfRequest,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Compliance.txt","ClfPolicies Create",result.getBody(),"response");
		return result;
    }
	
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.createClfPolicies(..))", throwing = "exception")
	public void createClfPoliciesAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Compliance.txt","ClfPolicies Create",exception,"/mclf/section/create");
		
	}
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.updateClfPolicies(..)) && args(id,request,..)")
    public ResponseEntity<?> updateClfPoliciesAdvice(ProceedingJoinPoint joinPoint,Long id ,ClfPoliciesRequest request) throws Throwable {
		request.setId(id);
		aspectexe("Compliance.txt","ClfPolicies Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Compliance.txt","ClfPolicies Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.updateClfPolicies(..))", throwing = "exception")
	public void updateClfPoliciesAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Compliance.txt","ClfPolicies Update",exception,"/mclf/section/create");
		
	}
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.createStatutory(..)) && args(id,request,..)")
    public ResponseEntity<?> createStatutoryAdvice(ProceedingJoinPoint joinPoint,String id ,StatutoryRequest request) throws Throwable {
		aspectexe("Compliance.txt","Statutory Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Compliance.txt","Statutory Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.createStatutory(..))", throwing = "exception")
	public void createStatutoryAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Compliance.txt","Statutory Create",exception,"/mclf/section/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.updateStatutory(..)) && args(id,request,..)")
    public ResponseEntity<?> updateStatutoryAdvice(ProceedingJoinPoint joinPoint,Long id ,StatutoryRequest request) throws Throwable {
		request.setId(id);
		aspectexe("Compliance.txt","Statutory Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Compliance.txt","Statutory Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.updateStatutory(..))", throwing = "exception")
	public void updateStatutoryAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Compliance.txt","Statutory Update",exception,"/mclf/section/create");
		
	}
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.createIntControl(..)) && args(id,request,..)")
    public ResponseEntity<?> createIntControlAdvice(ProceedingJoinPoint joinPoint,String id ,InternalControlRequest request) throws Throwable {
		aspectexe("Compliance.txt","Internal Control Create",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Compliance.txt","Internal Control Create",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.createIntControl(..))", throwing = "exception")
	public void createIntControlAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Compliance.txt","Internal Control Create",exception,"/mclf/section/create");
		
	}
	
	
	@Around(value = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.updateIntControl(..)) && args(id,request,..)")
    public ResponseEntity<?> updateIntControlAdvice(ProceedingJoinPoint joinPoint,Long id ,InternalControlRequest request) throws Throwable {
		request.setId(id);
		aspectexe("Compliance.txt","Internal Control Update",request,"request");
		ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
		aspectexe("Compliance.txt","Internal Control Update",result.getBody(),"response");
		return result;
    }
	
	@AfterThrowing(pointcut = "execution(* com.nrlm.mclfmis.Controllers.ComplianceController.updateIntControl(..))", throwing = "exception")
	public void updateIntControlAdviceException(JoinPoint joinPoint, Exception exception) {
		
		processExceptionAspect("Compliance.txt","Internal Control Update",exception,"/mclf/section/create");
		
	}
	

	private void processExceptionAspect(String fileName,String title,Exception exception,String url) {
		
		if(exception instanceof ValidationHandledException) {
			ValidationHandledException ex = (ValidationHandledException)exception;
			List<FieldError> errs = ex.getErrors();
			Map<String, String> messages = new HashMap<>(errs.size());
			for (FieldError fieldError : errs) {
					messages.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			ValidateResponse exceptionResponse = new ValidateResponse(HttpStatus.BAD_REQUEST.value(), messages,
					HttpStatus.NOT_ACCEPTABLE.name());
			aspectexe(fileName,title,exceptionResponse,"response");
		}
		if(exception instanceof HandledException) {
			HandledException ex = (HandledException)exception;
			String message = ex.getMessage();
			ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getCode().value(),new Date(),url,message,ex.getCode().name());
			aspectexe(fileName,title,exceptionResponse,"response");
		}
		else {
			Exception ex = (Exception)exception;
			String message = ex.getMessage();
			ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),new Date(),url,message,HttpStatus.INTERNAL_SERVER_ERROR.name());
			aspectexe(fileName,title,exceptionResponse,"response");
		}
	}
	
	private void aspectexe(String fileName, String title,Object request,String type) {
		
		try {

			file = new File(filePath+logDateformat.format(new Date())+"/"+fileName);
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			
			fileWriter =  new FileWriter(file,true); // true means append data
			
			bufferWriter = new BufferedWriter(fileWriter);   
			
			if(type.equals("request")) {
				bufferWriter.write(title+"	|	"+logDateTimeformat.format(new Date())+"\n\n"
						+type+"	|	"+UtilFunctions.printJsonObject(request)+"\n\n");
			}
			else {
				bufferWriter.write(type+"	|	"+UtilFunctions.printJsonObject(request)+"\n\n");
			}
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				bufferWriter.flush();
				bufferWriter.close();
				fileWriter.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
