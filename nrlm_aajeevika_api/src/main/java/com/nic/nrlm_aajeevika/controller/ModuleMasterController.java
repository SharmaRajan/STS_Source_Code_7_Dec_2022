package com.nic.nrlm_aajeevika.controller;


import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.entity.SubModule;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.service.ModuleService;
import com.nic.nrlm_aajeevika.service.SubModuleService;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("/aajeevika/modules")
public class ModuleMasterController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private SubModuleService submoduleService;

    @Autowired
    private Validator validator;

    /* Module Save API */
    @RequestMapping(value = "/savemodule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveModule(@RequestBody Module modreq, HttpServletRequest req) {
        Set<ConstraintViolation<Module>> violations = validator.validate(modreq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = moduleService.saveModule(Math.toIntExact(auth.getUserId()), modreq, req);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    /* Module Update API */
    @RequestMapping(value = "/updatemodule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateModule(@RequestParam(value = "id", required = true) Long id, @RequestBody Module modreq, Module modReq, HttpServletRequest req) {
        Set<ConstraintViolation<Module>> violations = validator.validate(modreq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = moduleService.updateModule(id, Math.toIntExact(auth.getUserId()), modreq, req);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    /*Sub Module Save API */
    @RequestMapping(value = "/savesubmodule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveSubModule(@RequestBody SubModule modreq, HttpServletRequest req) {
        Set<ConstraintViolation<SubModule>> violations = validator.validate(modreq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = submoduleService.saveSubModule(Math.toIntExact(auth.getUserId()), modreq, req);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    /*Sub Module Update API */
    @RequestMapping(value = "/updatesubmodule", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateSubModule(@RequestParam(value = "id", required = true) Long id, @RequestBody SubModule modreq, Module modReq, HttpServletRequest req) {
        Set<ConstraintViolation<SubModule>> violations = validator.validate(modreq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Response<String> response = submoduleService.updateSubModule(id, Math.toIntExact(auth.getUserId()), modreq, req);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

}
