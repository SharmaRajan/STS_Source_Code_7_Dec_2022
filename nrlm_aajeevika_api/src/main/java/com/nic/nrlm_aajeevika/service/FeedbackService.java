package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.Feedback;
import com.nic.nrlm_aajeevika.request.FeedbackRequest;
import com.nic.nrlm_aajeevika.response.Response;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface FeedbackService {


    Response<Feedback> getFeedbackList(HttpServletRequest request);

    Response<String> saveFeedback(FeedbackRequest request);


    Response<String> updateFeedback(Long id, Integer userId, FeedbackRequest feedReq, HttpServletRequest request);
}
