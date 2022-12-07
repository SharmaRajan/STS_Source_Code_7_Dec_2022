package com.nic.nrlm_aajeevika.service;

import com.nic.nrlm_aajeevika.entity.Feedback;
import com.nic.nrlm_aajeevika.exception.HandledException;
import com.nic.nrlm_aajeevika.helper.PageModel;
import com.nic.nrlm_aajeevika.repository.FeedbackRepository;
import com.nic.nrlm_aajeevika.request.FeedbackRequest;
import com.nic.nrlm_aajeevika.response.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    @Autowired
    private FeedbackRepository feedRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PageModel pageModel;

    @Override
    public Response<Feedback> getFeedbackList(HttpServletRequest request) {
        Response<Feedback> response = new Response<Feedback>();
        PageModel.setSIZE(20);
        pageModel.initPageAndSize();
        Page<Feedback> feedBackList = null;
        Long totalCount = Long.valueOf(0);
        String errorMsg = null;

        Page<Feedback> feedbackpageList = feedRepo.findAll(PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
        List<Feedback> feedbackResponse = feedbackpageList.getContent().stream()
                .map(feeddto -> modelMapper.map(feeddto, Feedback.class)).collect(Collectors.toList());
        response.setWrapperList(feedbackResponse);
        response.setTotalcount(feedbackpageList.getTotalElements());
        response.setErrorMsg(errorMsg);
        response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
        response.setResponseDesc(HttpStatus.OK.name());
        return response;
    }

    @Override
    @Transactional
    @Async
    public Response<String> saveFeedback(FeedbackRequest request) {
        Response<String> response = new Response<String>();
        try {
            Feedback feedModel = new Feedback();
            feedModel.setName(request.getName());
            feedModel.setEmail(request.getEmail());
            feedModel.setFeedback(request.getFeedback());
            Feedback result = feedRepo.save(feedModel);
            if (result.getId() != 0) {
                String to = "pragathundal11@gmail.com";
                String from = "noreply@dhwaniris.in";//"pragat.singh@dhwaniris.com";
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(result.getName());
                message.setText(result.getFeedback());
                mailSender.send(message);
            }
            response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
            response.setResponseDesc(HttpStatus.OK.name());
            return response;

        } catch (HandledException e) {
            e.printStackTrace();
            throw new HandledException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Response<String> updateFeedback(Long id, Integer userId, FeedbackRequest feedReq, HttpServletRequest request) {
        Response<String> response = new Response<String>();
        try {
            Optional<Feedback> optionalFeedback = feedRepo.findById(id);
            if (optionalFeedback.isPresent()) {
                Feedback feedModel = optionalFeedback.get();
                feedModel.setUpdatedBy(userId);
                feedModel.setName(feedReq.getName());
                feedModel.setEmail(feedReq.getEmail());
                feedModel.setFeedback(feedReq.getFeedback());
                Feedback result = feedRepo.save(feedModel);
                response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
                response.setResponseDesc(HttpStatus.OK.name());
                return response;
            } else {
                throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Feedback entry not found");
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
