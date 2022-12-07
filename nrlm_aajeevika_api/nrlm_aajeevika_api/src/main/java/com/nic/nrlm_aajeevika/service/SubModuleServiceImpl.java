package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.entity.SubModule;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.helper.PageModel;
import com.nic.nrlm_aajeevika.helper.ProjectConstants;
import com.nic.nrlm_aajeevika.repository.ContentRepository;
import com.nic.nrlm_aajeevika.repository.ModuleRepository;
import com.nic.nrlm_aajeevika.repository.SubModuleRepository;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.specification.ContentSpecification;
import com.nic.nrlm_aajeevika.specification.ModuleSpecification;
import com.nic.nrlm_aajeevika.specification.SubModuleSpecification;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
public class SubModuleServiceImpl implements SubModuleService {

    @Autowired
    private SubModuleRepository subModuleRepo;

    @Autowired
    private SubModuleSpecification submoduleSpeci;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PageModel pageModel;


    @Override
    public Response<SubModule> getSubModuleList(HttpServletRequest request) {
        Response<SubModule> response = new Response<SubModule>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<SubModule> contentList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;
  
            Page<SubModule> conetentpageList = subModuleRepo.findAll(
            		submoduleSpeci.getSubModules(request),
                    PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
            
           // modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            List<SubModule> contentResponse = conetentpageList.getContent().stream()
                    .map(condto -> modelMapper.map(condto, SubModule.class)).collect(Collectors.toList());
            response.setWrapperList(contentResponse);
            response.setTotalcount(conetentpageList.getTotalElements());
            response.setErrorMsg(errorMsg);
            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;
     
    }

    @Override
    @Transactional
    public Response<String> saveSubModule(Integer userId, SubModule modreq, HttpServletRequest req) {
        Response<String> response = new Response<String>();
        try {
            Optional<SubModule> optionalModule = subModuleRepo.findBySubModuleName(modreq.getSubModuleName());
            if (!optionalModule.isPresent()) {
                SubModule modModel = new SubModule();
                modModel.setCreatedBy(userId);
                modModel.setUpdatedBy(userId);
                modModel.setIsDeleted(String.valueOf(ProjectConstants.ACTIVE_STATUS));
                modModel.setSubModuleName(modreq.getSubModuleName());
                modModel.setModuleId(modreq.getModuleId());
                SubModule result = subModuleRepo.save(modModel);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Sub Module Already exist with given Name");
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
    public Response<String> updateSubModule(Long smodId, Integer userId, SubModule modreq, HttpServletRequest req) {
        Response<String> response = new Response<String>();
        try {
            Optional<SubModule> optionalModule = subModuleRepo.findById(smodId);
            if (optionalModule.isPresent()) {
                SubModule modModel = optionalModule.get();
                modModel.setUpdatedBy(userId);
                modModel.setIsDeleted(String.valueOf(ProjectConstants.ACTIVE_STATUS));
                modModel.setSubModuleName(modreq.getSubModuleName());
                modModel.setModuleId(modreq.getModuleId());
                SubModule result = subModuleRepo.save(modModel);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Sub Module not found");
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
