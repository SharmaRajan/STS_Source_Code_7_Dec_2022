package com.nic.nrlm_aajeevika.controller;

import com.nic.nrlm_aajeevika.entity.ContentFileMapper;
import com.nic.nrlm_aajeevika.entity.Module;
import com.nic.nrlm_aajeevika.entity.SubModule;
import com.nic.nrlm_aajeevika.entity.TickerPopupSlider;
import com.nic.nrlm_aajeevika.entity.Visitors;
import com.nic.nrlm_aajeevika.helper.ImageResourceHttpRequestHandler;
import com.nic.nrlm_aajeevika.request.IdWrapper;
import com.nic.nrlm_aajeevika.response.AuthorResponse;
import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.FaqResponse;
import com.nic.nrlm_aajeevika.response.FileTypeResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.service.ContentService;
import com.nic.nrlm_aajeevika.service.ModuleService;
import com.nic.nrlm_aajeevika.service.SubModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Properties;


@RestController
//@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping("/aajeevika/content")
public class ContentController {

    @Autowired
    ServletContext context;
    @Autowired
    Environment env;
    @Autowired
    private ContentService contentService;
    @Resource
    private ImageResourceHttpRequestHandler imageResourceHttpRequestHandler;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private SubModuleService submoduleService;
    @Value("${check.debug}")
    private String mailFrom;

    @GetMapping("/welcome")
    public String welcome(@Value("${check.debug}") String ccc) throws IOException {
//        return "Welcome to website";
//        PropertiesConfiguration configuration = new PropertiesConfiguration();
//        configuration.setProperty("check.debug","CheckDebugConfig");
//        System.out.println("First ********************************="+ccc);
//        System.out.println("Second ********************************="+env.getProperty("check.debug"));
//        System.out.println("Third ********************************="+configuration.getProperty("check.debug"));

//        FileReader reader = null;
//        FileWriter writer = null;
//
//        File file = new File("src/main/resources/application.properties");
//        Properties p = null;
//        try {
//            reader = new FileReader(file);
//            writer = new FileWriter(file);
//
//            p = new Properties();
//            p.load(reader);
//
//            p.setProperty("check.debug", "dev.com");
//            p.store(writer, "write a file");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("First ********************************=" + ccc);
//        System.out.println("Second ********************************=" + env.getProperty("check.debug"));
//        System.out.println("Third ********************************=" + p.getProperty("check.debug"));
//        FileOutputStream out = new FileOutputStream("src/main/resources/application.properties");
//        Properties props = new Properties();
//        props.setProperty("check.debug", "setting herer");
//        props.store(out, null);
//        out.close();
        System.out.println("after Firts********************************=" + env.getProperty("check.debug"));
//        System.out.println("after second********************************=" + props.getProperty("check.debug"));
        return "bbbbbbbbbbbbbb"
                ;
    }

    /* Content List API */
    @GetMapping("/list")
    public ResponseEntity<?> getContentList(HttpServletRequest request) {
        Response<ContentListResponse> response = contentService.getContentList(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    /* Content Search API */
    @GetMapping("/search")
    public ResponseEntity<?> searchContent(HttpServletRequest request) {
        Response<ContentListResponse> response = contentService.searchContent(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/modulelist")
    public ResponseEntity<?> getModuleList(HttpServletRequest request) {
        Response<Module> response = moduleService.getModuleList(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


    @GetMapping("/submodulelist")
    public ResponseEntity<?> getSubModuleList(HttpServletRequest request) {
        Response<SubModule> response = submoduleService.getSubModuleList(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


    @PostMapping("/changeApprovedStatus")
    public ResponseEntity<?> changestatuss(@RequestBody IdWrapper id, HttpServletRequest request) {
        System.out.println("----------" + id);
        System.out.println("----------" + request.getParameter("flag"));
        List<Long> ids = id.getId();
        System.out.println("--------ids--" + ids);
        Response<String> response = contentService.changeApprovedStatus(ids, request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


    @PostMapping("/downloadviewcountfile")
    public ResponseEntity<?> getdownloadcount(@RequestParam(value = "requestId", required = true) Long requestId, HttpServletRequest request) {
        Response<String> response = contentService.getdownloadcount(request, requestId);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


    @GetMapping("/faqlistfe")
    public ResponseEntity<?> getFaqList(HttpServletRequest request) {
        Response<FaqResponse> response = contentService.getfaqlist(request);


        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("/downloadviewcountcontent")
    public ResponseEntity<?> getdownloadcountContent(HttpServletRequest request) {
        Response<String> response = contentService.getdownloadcountContent(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/extralist")
    public ResponseEntity<?> getTickerList(HttpServletRequest request) {
        Response<TickerPopupSlider> response = contentService.getTickerlist(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/getDropDown")
    public ResponseEntity<?> getSource(@RequestParam(value = "keyName", required = true) String keyName, @RequestParam(value = "moduleId", required = true) Integer moduleId, HttpServletRequest request) {
        Response<AuthorResponse> response = contentService.getDropDownList(keyName, moduleId, request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/images")
    public void download(@RequestParam(value = "fileName", required = true) String fileName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        File file = new File("images/" + fileName);
        httpServletRequest.setAttribute(ImageResourceHttpRequestHandler.ATTRIBUTE_FILE, file);
        imageResourceHttpRequestHandler.handleRequest(httpServletRequest, httpServletResponse);
    }

    @PostMapping("/visitorsland")
    public ResponseEntity<?> visitorsland(HttpServletRequest request) {
        Response<String> response = contentService.visitorsland(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/getvisitorscount")
    public ResponseEntity<?> visitorscount(HttpServletRequest request) {
        Response<Visitors> response = contentService.vistorcount(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
    
    @GetMapping("/getfiletype")
    public ResponseEntity<?> getfiletype() {
        Response<FileTypeResponse> response = contentService.getfiletype();
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

}
