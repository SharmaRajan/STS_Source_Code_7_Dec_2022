package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.ContentFileMapper;
import com.nic.nrlm_aajeevika.entity.TickerPopupSlider;
import com.nic.nrlm_aajeevika.entity.Visitors;
import com.nic.nrlm_aajeevika.request.Faqrequest;
import com.nic.nrlm_aajeevika.request.TickerPopupSliderRequest;
import com.nic.nrlm_aajeevika.response.AuthorResponse;
import com.nic.nrlm_aajeevika.response.ContentListResponse;
import com.nic.nrlm_aajeevika.response.FaqResponse;
import com.nic.nrlm_aajeevika.response.FileTypeResponse;
import com.nic.nrlm_aajeevika.response.Response;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface ContentService {


    Response<ContentListResponse> getContentList(HttpServletRequest request);

    Response<String> getdownloadcount(HttpServletRequest request, Long requestId);

    Response<String> getdownloadcountContent(HttpServletRequest request);

    Response<String> visitorsland(HttpServletRequest request);

    Response<Visitors> vistorcount(HttpServletRequest request);

    Response<ContentListResponse> searchContent(HttpServletRequest request);

    Response<String> changeApprovedStatus(List<Long> values, HttpServletRequest request);

    Response<String> saveFaq(Integer userId, Faqrequest request);

    Response<FaqResponse> getfaqlist(HttpServletRequest request);
    

    Response<FileTypeResponse> getfiletype();

    //    Response<String> saveExtraContent(Integer userId, TickerPopupSliderRequest request);
    Response<String> getdowncount(HttpServletRequest request);

    //    Response<ContentListResponse> searchContent(HttpServletRequest request);
//    Response<String> changeApprovedStatus(List<Long> values,HttpServletRequest request);
//    Response<String> saveFaq(Integer userId,Faqrequest request);
//
//    Response<Faqresponse> getfaqlist(HttpServletRequest request);
//
    Response<String> saveExtraContent(Integer userId, TickerPopupSliderRequest request, MultipartFile file);

    Response<TickerPopupSlider> getTickerlist(HttpServletRequest request);

    Response<AuthorResponse> getDropDownList(String keyName,Integer moduleId,HttpServletRequest request);
    
    Response<String> updatefaq(Long id,Integer userId, Faqrequest faqReq, HttpServletRequest request);
   


    Response<String> deleteTicker(Long id, Integer userid, TickerPopupSlider request);
    Response<String> deleteFAQ(Long id, Integer userid, Faqrequest request);
    

    Response<String> updateTicker(Long id, Integer userid, TickerPopupSlider request, MultipartFile file);

}
