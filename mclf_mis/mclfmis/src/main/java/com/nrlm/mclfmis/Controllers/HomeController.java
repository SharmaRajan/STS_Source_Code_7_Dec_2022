package com.nrlm.mclfmis.Controllers;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrlm.mclfmis.Entity.ClfMaster;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.Response.ClfCutOffResposne;
import com.nrlm.mclfmis.Response.ClfInfoResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionTabResponse;
import com.nrlm.mclfmis.Services.CutOffService;
import com.nrlm.mclfmis.Services.MasterService;
import com.nrlm.mclfmis.Services.SectionService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
@RequestMapping("/mclf")
public class HomeController {
	
	private  Bucket bucket ;
	
	public HomeController() {
        Bandwidth limit = Bandwidth.classic(15, Refill.greedy(15, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

	@Autowired
	SectionService sectionService;
	
	@Autowired
	MasterService masterService;

	@GetMapping("/tabs")
	public ResponseEntity<?> getTabList(){
		if (bucket.tryConsume(1)) {
			 Response<SectionTabResponse> response = sectionService.getTabList();
		      return new ResponseEntity<Response<SectionTabResponse>>(response, HttpStatus.OK);
		
		}
		else {
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
		}
		
	}
	
	@GetMapping("/clf")	
	public ResponseEntity<?> getClfInfo(@RequestParam("clfCode") String clfCode ){
		 Response<ClfInfoResponse> response = masterService.getClfInfo(clfCode);
	      return new ResponseEntity<Response<ClfInfoResponse>>(response, HttpStatus.OK);
	}

}
