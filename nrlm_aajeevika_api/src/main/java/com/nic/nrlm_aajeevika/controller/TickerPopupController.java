package com.nic.nrlm_aajeevika.controller;

import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.data.MapperImageSlider;
import com.nic.nrlm_aajeevika.data.MyData;
import com.nic.nrlm_aajeevika.entity.Content;
import com.nic.nrlm_aajeevika.entity.Faq;
import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.entity.SubModule;
import com.nic.nrlm_aajeevika.entity.TickerPopupSlider;
import com.nic.nrlm_aajeevika.exception.ValidationHandledException;
import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.request.Faqrequest;
import com.nic.nrlm_aajeevika.request.IdWrapper;
import com.nic.nrlm_aajeevika.request.TickerPopupSliderRequest;
import com.nic.nrlm_aajeevika.service.ContentService;
import com.nic.nrlm_aajeevika.service.ModuleService;
import com.nic.nrlm_aajeevika.service.SubModuleService;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import com.nic.nrlm_aajeevika.validator.TickerValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;


@RestController
//@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/aajeevika/extracontent/")
public class TickerPopupController {

    @Autowired
    private ContentService contentService;
    
    @Autowired
    private Validator validator;

 
    @Autowired
    TickerValidation custom;
 
    @InitBinder("request")
    protected void initMessageBinder(WebDataBinder binder) {
    binder.addValidators(custom);
   
 }
    
    @GetMapping("/welcomee")
    public String welcome() {
        return "Welcome to website";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = {"*/*"})
    @ResponseBody
    public ResponseEntity<?> saveContent(TickerPopupSliderRequest request,HttpServletRequest req,@RequestParam(value = "file", required = false) MultipartFile file , BindingResult errors) {
    	
    	try {
    	MapperImageSlider data=new MapperImageSlider(request,file);
    	custom.validate(data, errors); 
    	if (errors.hasErrors())
    	{ 
    		throw new ValidationHandledException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()); }
    	}
    	catch(ValidationHandledException v) {
    	throw new ValidationHandledException(v.getCode(),
    	v.getErrors());
    	}
    	AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
 	   System.out.println("----pp-----"+request);
        Response<String> response = contentService.saveExtraContent(Math.toIntExact(auth.getUserId()),request,file);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateticker(@RequestParam(value = "id", required = true) Long id,TickerPopupSlider tickerreq, HttpServletRequest req,@RequestParam(value = "file", required = false) MultipartFile file) {
        Set<ConstraintViolation<TickerPopupSlider>> violations = validator.validate(tickerreq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.updateTicker(id, Math.toIntExact(auth.getUserId()), tickerreq,file);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }
   
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deleteTicker(@RequestParam(value = "id", required = true) Long id,TickerPopupSlider tickerreq) {
        
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.deleteTicker(id, Math.toIntExact(auth.getUserId()), tickerreq);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }
  
   


}
