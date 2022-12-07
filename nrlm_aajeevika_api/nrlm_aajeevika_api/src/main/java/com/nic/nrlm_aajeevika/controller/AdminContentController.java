package com.nic.nrlm_aajeevika.controller;

import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.request.IdWrapper;
import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.service.AdminContentService;
import com.nic.nrlm_aajeevika.service.ContentService;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/aajeevika/admincontent")
public class AdminContentController {

    @Autowired
    private AdminContentService contentService;

    	
    @Autowired
    private ContentService contentServiceContent;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to website";
    }

    /* Content List API */
    @GetMapping("/list")
    public ResponseEntity<?> getContentList(HttpServletRequest request) {
        Response<ContentListResponse> response = contentService.getAdminContentList(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    /* Content Save API */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json", consumes = {"*/*"})
    @ResponseBody
    public ResponseEntity<?> saveContent(ContentFormRequest conReq, HttpServletRequest req, @RequestParam(value = "files", required = false) MultipartFile[] multipartFile) {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.saveContent(Math.toIntExact(auth.getUserId()), conReq, multipartFile);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    /* Content Update API */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = {"*/*"})
    @ResponseBody
    public ResponseEntity<?> updateContent(@RequestParam(value = "id", required = true) Long id, ContentFormRequest conReq, HttpServletRequest req, @RequestParam(value = "files", required = false) MultipartFile[] multipartFile) {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = contentService.updateContent(id, Math.toIntExact(auth.getUserId()), conReq, multipartFile);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }
    
    @PostMapping("/changeApprovedStatus")
    public ResponseEntity<?> changestatuss(@RequestBody IdWrapper id ,HttpServletRequest request) {
 	System.out.println("----------"+id);
 	System.out.println("----------"+request.getParameter("flag"));
 	List<Long> ids=id.getId();
 	System.out.println("--------ids--"+ids);
 	   Response<String> response = contentServiceContent.changeApprovedStatus(ids,request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
