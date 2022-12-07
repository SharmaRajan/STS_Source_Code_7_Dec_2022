package com.nic.nrlm_aajeevika.controller;


import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.request.IdWrapper;
import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.service.AdminContentService;
import com.nic.nrlm_aajeevika.service.ContentService;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import com.nic.nrlm_aajeevika.validator.ContentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import java.util.List;

@RestController
@RequestMapping("/aajeevika/admincontent")
public class AdminContentController {

    @Autowired
    ContentValidation custom;
    @Autowired
    private AdminContentService contentService;
    @Autowired
    private ContentService contentServiceContent;

    @Autowired
    private Validator validator;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to website";
    }


//	@InitBinder("conReq")
//	protected void initMessageBinder(WebDataBinder binder) {
//		binder.addValidators(custom);
//
//	}

    /* Content Save API */
//    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = {"*/*"})
//    @ResponseBody
//    public ResponseEntity<?> saveContent(ContentFormRequest conReq, BindingResult result, HttpServletRequest req,
//                                         @RequestParam(value = "files", required = false) MultipartFile[] multipartFile) {
//        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        Response<String> response = contentService.saveContent(Math.toIntExact(auth.getUserId()), conReq, multipartFile);
//        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
//    }


    /* Content List API */
    @GetMapping("/list")
    public ResponseEntity<?> getContentList(HttpServletRequest request) {
        Response<ContentListResponse> response = contentService.getAdminContentList(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    /* Content Save API */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = {"*/*"})
    @ResponseBody
    public ResponseEntity<?> saveContent(ContentFormRequest conReq, HttpServletRequest req,
                                         @RequestParam(value = "files", required = false) MultipartFile[] multipartFile) {

//		try {
//
//			MyData mydata = new MyData(conReq, multipartFile);
//			custom.validate(mydata, errors);
//			if (errors.hasErrors()) {
//				throw new ValidationHandledException(HttpStatus.BAD_REQUEST, errors.getFieldErrors());
//			}
//		} catch (ValidationHandledException v) {
//			v.printStackTrace();
//			throw new ValidationHandledException(v.getCode(), v.getErrors());
//		}

        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.saveContent(Math.toIntExact(auth.getUserId()), conReq,
                multipartFile);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    /* Content Update API */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = {"*/*"})
    @ResponseBody
    public ResponseEntity<?> updateContent(@RequestParam(value = "id", required = true) Long id,
                                           ContentFormRequest conReq, HttpServletRequest req,
                                           @RequestParam(value = "files", required = false) MultipartFile[] multipartFile) {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.updateContent(id, Math.toIntExact(auth.getUserId()), conReq,
                multipartFile);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> deleteSoftContent(@RequestParam(value = "id", required = true) Long id,
                                               HttpServletRequest req) {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.deleteContent(id, Math.toIntExact(auth.getUserId()));
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    @PostMapping("/changeApprovedStatus")
    public ResponseEntity<?> changestatuss(@RequestBody IdWrapper id, HttpServletRequest request) {
        System.out.println("----------" + id);
        System.out.println("----------" + request.getParameter("flag"));
        List<Long> ids = id.getId();
        System.out.println("--------ids--" + ids);
        Response<String> response = contentServiceContent.changeApprovedStatus(ids, request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}