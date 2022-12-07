package com.nic.nrlm_aajeevika.validator;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.nic.nrlm_aajeevika.data.MyData;
import com.nic.nrlm_aajeevika.entity.ContentFileMapper;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.helper.ProjectConstants;
import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.request.TickerPopupSliderRequest;

@Component("ContentValidation")
@PropertySource(value = "classpath:messages.properties")
public class ContentValidation implements Validator {

//	@Autowired
//	TickerPopupSliderRequest tickerrequestt; 
//	
	@Autowired
	MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ContentFormRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("-----------in validation-------------------------");
		MyData inda = (MyData) target;
		
		ContentFormRequest ind = (ContentFormRequest)inda.getRequest();

		MultipartFile[] list = inda.getFile();
		 System.out.println(list+"=============================");
		
		
		if(ind.getTitle()=="" || ind.getTitle()==null || ind.getTitle().isEmpty()) {
			System.out.println("title is null++++++++++++");
			errors.rejectValue("title", "required", new Object[] { "title" },
					messageSource.getMessage("title.required", new Object[] { "'title'" }, null));
		}
		
		
		
		if(ind.getSubModuleId()==1) {
		if(ind.getAuthor()=="" || ind.getAuthor()==null || ind.getAuthor().isEmpty()) {
			errors.rejectValue("Author", "required", new Object[] { "Author" },
					messageSource.getMessage("Author.required", new Object[] { "'Author'" }, null));
		}
		
		if(ind.getDocumentSource()=="" || ind.getDocumentSource()==null || ind.getDocumentSource().isEmpty()) {
			errors.rejectValue("documentSource", "required", new Object[] { "documentSource" },
					messageSource.getMessage("documentSource.required", new Object[] { "'documentSource'" }, null));
		}
		if(ind.getPublishedDate()=="" || ind.getPublishedDate()==null || ind.getPublishedDate().isEmpty()) {
			errors.rejectValue("publishedDate", "required", new Object[] { "publishedDate" },
					messageSource.getMessage("publishedDate.required", new Object[] { "'publishedDate'" }, null));
		}
		if(ind.getPublishedBy()=="" || ind.getPublishedBy()==null || ind.getPublishedBy().isEmpty()) {
			errors.rejectValue("publishedBy", "required", new Object[] { "publishedBy" },
					messageSource.getMessage("publishedBy.required", new Object[] { "'publishedBy'" }, null));
		}
		
		if(list==null) {
			errors.rejectValue("file","required", "Please provide file");
	
		}
		
		}
		
		
		if(ind.getSubModuleId()==2 || ind.getSubModuleId()==3 || ind.getSubModuleId()==4 || ind.getSubModuleId()==5 || ind.getSubModuleId()==6 || ind.getSubModuleId()==7 || ind.getSubModuleId()==8) {
			if(ind.getAuthor()=="" || ind.getAuthor()==null || ind.getAuthor().isEmpty()) {
				errors.rejectValue("Author", "required", new Object[] { "Author" },
						messageSource.getMessage("Author.required", new Object[] { "'Author'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
			
		
		}
		
		
		if(ind.getModuleId()==2) {
			//News Letters
			if(ind.getIssuedNo()==null ) {
				errors.rejectValue("issuedNo", "required", new Object[] { "issuedNo" },
						messageSource.getMessage("issuedNo.required", new Object[] { "'issuedNo'" }, null));
			}
			if(ind.getIssuedDate()=="" || ind.getIssuedDate()==null || ind.getIssuedDate().isEmpty()) {
				errors.rejectValue("issuedDate", "required", new Object[] { "issuedDate" },
						messageSource.getMessage("issuedDate.required", new Object[] { "'issuedDate'" }, null));
			}
			if(ind.getPublishedBy()=="" || ind.getPublishedBy()==null || ind.getPublishedBy().isEmpty()) {
				errors.rejectValue("publishedBy", "required", new Object[] { "publishedBy" },
						messageSource.getMessage("publishedBy.required", new Object[] { "'publishedBy'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
		
		}
		
		if(ind.getModuleId()==3) {
			//Case Study
			if(ind.getSource()=="" || ind.getSource()==null || ind.getSource().isEmpty()) {
				errors.rejectValue("source", "required", new Object[] { "source" },
						messageSource.getMessage("source.required", new Object[] { "'source'" }, null));
			}
			if(ind.getAuthor()=="" || ind.getAuthor()==null || ind.getAuthor().isEmpty()) {
				errors.rejectValue("Author", "required", new Object[] { "Author" },
						messageSource.getMessage("Author.required", new Object[] { "'Author'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
		}
		
		
		if(ind.getModuleId()==4) {
			//Letters/Circulars
			if(ind.getIssuedBy()==null || ind.getIssuedBy()==null || ind.getIssuedBy().isEmpty()) {
				errors.rejectValue("issuedBy", "required", new Object[] { "issuedBy" },
						messageSource.getMessage("issuedBy.required", new Object[] { "'issuedBy'" }, null));
			}
			
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
		}
		
		
		if(ind.getModuleId()==5) {
			//Tenders
			if(ind.getCorrigendum()==null || ind.getCorrigendum()==null ) {
				errors.rejectValue("corrigendum", "required", new Object[] { "corrigendum" },
						messageSource.getMessage("corrigendum.required", new Object[] { "'corrigendum'" }, null));
			}
			if( ind.getDueDateExtended()==null ) {
				errors.rejectValue("dueDateExtended", "required", new Object[] { "dueDateExtended" },
						messageSource.getMessage("dueDateExtended.required", new Object[] { "'dueDateExtended'" }, null));
			}
			if(ind.getAutoExpiry()=="" || ind.getAutoExpiry()==null || ind.getAutoExpiry().isEmpty()) {
				errors.rejectValue("autoExpiry", "required", new Object[] { "autoExpiry" },
						messageSource.getMessage("autoExpiry.required", new Object[] { "'autoExpiry'" }, null));
			}
			if(ind.getLastDateSubmission()=="" || ind.getLastDateSubmission()==null || ind.getLastDateSubmission().isEmpty()) {
				errors.rejectValue("lastDateSubmission", "required", new Object[] { "lastDateSubmission" },
						messageSource.getMessage("lastDateSubmission.required", new Object[] { "'lastDateSubmission'" }, null));
			}
			
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
		}
		
		
		
		if(ind.getModuleId()==6) {
			//Events
			if(ind.getLocation()=="" || ind.getLocation()==null || ind.getLocation().isEmpty() ) {
				errors.rejectValue("location", "required", new Object[] { "location" },
						messageSource.getMessage("location.required", new Object[] { "'location'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
		
		}
		
		
		if(ind.getModuleId()==7) {
			//Media/News
			if(ind.getLink()=="" || ind.getLink()==null || ind.getLink().isEmpty() ) {
				errors.rejectValue("link", "required", new Object[] { "link" },
						messageSource.getMessage("link.required", new Object[] { "'link'" }, null));
			}
		
		}
		
		
		
		if(ind.getSubModuleId()==10) {
			//Document
			if(ind.getAuthor()=="" || ind.getAuthor()==null || ind.getAuthor().isEmpty()) {
				errors.rejectValue("Author", "required", new Object[] { "Author" },
						messageSource.getMessage("Author.required", new Object[] { "'Author'" }, null));
			}
			if(ind.getSource()=="" || ind.getSource()==null || ind.getSource().isEmpty()) {
				errors.rejectValue("source", "required", new Object[] { "source" },
						messageSource.getMessage("source.required", new Object[] { "'source'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
			}
		
		
		
		if(ind.getSubModuleId()==11 || ind.getSubModuleId()==14 ) {
			//videos
			if(ind.getLink()=="" || ind.getLink()==null || ind.getLink().isEmpty() ) {
				errors.rejectValue("link", "required", new Object[] { "link" },
						messageSource.getMessage("link.required", new Object[] { "'link'" }, null));
			}
			if(ind.getSource()=="" || ind.getSource()==null || ind.getSource().isEmpty()) {
				errors.rejectValue("source", "required", new Object[] { "source" },
						messageSource.getMessage("source.required", new Object[] { "'source'" }, null));
			}
		
			}
		
		
		
		if(ind.getSubModuleId()==12) {
			//RTI received and responses given
			if(ind.getFileNumber()=="" || ind.getFileNumber()==null || ind.getFileNumber().isEmpty() ) {
				errors.rejectValue("fileNumber", "required", new Object[] { "fileNumber" },
						messageSource.getMessage("fileNumber.required", new Object[] { "'fileNumber'" }, null));
			}
			if(ind.getRtiQuery()=="" || ind.getRtiQuery()==null || ind.getRtiQuery().isEmpty()) {
				errors.rejectValue("rtiQuery", "required", new Object[] { "rtiQuery" },
						messageSource.getMessage("rtiQuery.required", new Object[] { "'rtiQuery'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
			}
		
		
		if(ind.getSubModuleId()==13) {
			//Compliance under Section 4, RTI Act
			if(ind.getFileNumber()=="" || ind.getFileNumber()==null || ind.getFileNumber().isEmpty() ) {
				errors.rejectValue("fileNumber", "required", new Object[] { "fileNumber" },
						messageSource.getMessage("fileNumber.required", new Object[] { "'fileNumber'" }, null));
			}
			if(ind.getRtiQuery()=="" || ind.getRtiQuery()==null || ind.getRtiQuery().isEmpty()) {
				errors.rejectValue("rtiQuery", "required", new Object[] { "rtiQuery" },
						messageSource.getMessage("rtiQuery.required", new Object[] { "'rtiQuery'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
			}
		
		if(ind.getSubModuleId()==15) {
			
			//Photo
			if(ind.getSource()=="" || ind.getSource()==null || ind.getSource().isEmpty()) {
				errors.rejectValue("source", "required", new Object[] { "source" },
						messageSource.getMessage("source.required", new Object[] { "'source'" }, null));
			}
			
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
			}
		
		
		if(ind.getModuleId()==12) {
			//Career
			if(ind.getVacancyUploadedDate()=="" || ind.getVacancyUploadedDate()==null || ind.getVacancyUploadedDate().isEmpty()) {
				errors.rejectValue("vacancyUploadedDate", "required", new Object[] { "vacancyUploadedDate" },
						messageSource.getMessage("vacancyUploadedDate.required", new Object[] { "'vacancyUploadedDate'" }, null));
			}
			if(ind.getLastDateSubmission()=="" || ind.getLastDateSubmission()==null || ind.getLastDateSubmission().isEmpty()) {
				errors.rejectValue("lastDateSubmission", "required", new Object[] { "lastDateSubmission" },
						messageSource.getMessage("lastDateSubmission.required", new Object[] { "'lastDateSubmission'" }, null));
			}
			if(list==null) {
				errors.rejectValue("file","required", "Please provide file");
		
			}
			}
		
		if(list != null)
		 Arrays.asList(list).stream().forEach(file -> {
			 int i=0;
			 System.out.println(i+"=============================");
             if (!file.isEmpty()) {
                 float size = file.getSize() / 1024;
                 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                 String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                 
               	if(ind.getSubModuleId()==12) {
        			if(!"jpg".equals(extension) && !"png".equals(extension) && !"jpeg".equals(extension)  ) {
        				errors.rejectValue("file"+i, "required", new Object[] { "fileType" },
        						messageSource.getMessage("fileTypeimage.required", new Object[] { "'fileType'" }, null));
        			}
        			 
        		}
               	else {
               		if(!"pdf".equals(extension) ) {
        				errors.rejectValue("file"+i, "required", new Object[] { "fileType" },
        						messageSource.getMessage("fileTypepdf.required", new Object[] { "'fileType'" }, null));
        			}
               		
               	}
               
               	i++;
             }
             
            
         });
		
		}
}