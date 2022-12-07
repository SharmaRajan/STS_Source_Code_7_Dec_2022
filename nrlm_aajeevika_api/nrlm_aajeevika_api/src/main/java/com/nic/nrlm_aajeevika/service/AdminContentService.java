package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.request.ContentFormRequest;
import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.entity.Content;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


public interface AdminContentService {

    Response<String> saveContent(Integer userId, ContentFormRequest req, MultipartFile[] files);
    Response<String> updateContent(Long contentId,Integer userId, ContentFormRequest req, MultipartFile[] files);

    Response<ContentListResponse> getAdminContentList(HttpServletRequest request);

}
