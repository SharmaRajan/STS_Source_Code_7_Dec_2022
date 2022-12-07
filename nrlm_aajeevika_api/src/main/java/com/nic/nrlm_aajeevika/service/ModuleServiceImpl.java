package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.helper.PageModel;
import com.nic.nrlm_aajeevika.helper.ProjectConstants;
import com.nic.nrlm_aajeevika.repository.ModuleRepository;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.specification.ModuleSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepo;

    @Autowired
    private ModuleSpecification moduleSpeci;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageModel pageModel;


    @Override
    public Response<Module> getModuleList(HttpServletRequest request) {
        Response<Module> response = new Response<Module>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Module> contentList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;

        Page<Module> conetentpageList = moduleRepo.findAll(
                moduleSpeci.getModules(request),
                PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
        List<Module> contentResponse = conetentpageList.getContent().stream()
                .map(condto -> modelMapper.map(condto, Module.class)).collect(Collectors.toList());
        response.setWrapperList(contentResponse);
        response.setTotalcount(conetentpageList.getTotalElements());
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;

    }

    @Override
    @Transactional
    public Response<String> saveModule(Integer userId, Module modreq, HttpServletRequest req) {
        Response<String> response = new Response<String>();
        try {
            Optional<Module> optionalModule = moduleRepo.findByModuleName(modreq.getModuleName());
            if (!optionalModule.isPresent()) {
                Module modModel = new Module();
                modModel.setCreatedBy(userId);
                modModel.setUpdatedBy(userId);
                modModel.setIsDeleted(String.valueOf(ProjectConstants.ACTIVE_STATUS));
                modModel.setModuleName(modreq.getModuleName());
                Module result = moduleRepo.save(modModel);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Module Already exist with given Name");
            }
        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response<String> updateModule(Long modId, Integer userId, Module modreq, HttpServletRequest req) {
        Response<String> response = new Response<String>();
        try {
            Optional<Module> optionalModule = moduleRepo.findById(modId);
            if (optionalModule.isPresent()) {
                Module modModel = optionalModule.get();
                modModel.setUpdatedBy(userId);
                modModel.setModuleName(modreq.getModuleName());
                modModel.setIsDeleted(String.valueOf(ProjectConstants.ACTIVE_STATUS));
                Module result = moduleRepo.save(modModel);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Module not found");
            }
        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
