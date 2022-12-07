package com.nic.nrlm_aajeevika.controller;

import com.nic.nrlm_aajeevika.entity.Feedback;
import com.nic.nrlm_aajeevika.request.FeedbackRequest;
import com.nic.nrlm_aajeevika.response.Response;
import com.nic.nrlm_aajeevika.service.FeedbackService;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@RestController
@RequestMapping("/aajeevika/feedback")
public class FeedbackController {
    
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private Validator validator;

//    @Value("${spring.mail.username}")
//    private String mailFrom;

    //    @Async
    @GetMapping("/sendMail")
    public void sendEmail() throws MessagingException, UnsupportedEncodingException, MessagingException {
//        String to = "pragathundal11@gmail.com";
//        String from = "pragat.singh@dhwaniris.com";
//        System.out.println("aaaaaaaaaaaa");
//        SimpleMailMessage message = new SimpleMailMessage();
//        System.out.println("bbbbbbbbbbbbbb");
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject("This is a plain text email");
//        message.setText("Hello guys! This is a plain text email.");
//        System.out.println("cccccccccccccccccccc");
//        mailSender.send(message);
//        System.out.println("ggggggggggggggg");
////
//        MimeMessage message1 = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message1);
//
//        helper.setFrom("pragat.singh@dhwaniris.com");//mailFrom
//        helper.setTo(to);
//
//        String subject = "Here's a link to reset your password";
//        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>" + "<p>Click the link below to change your password:</p>"
//
//                + "<br>" + "<p>Ignore this email if you do remember your password, " + "or you have not made the request.</p>";
//
//        helper.setSubject(subject);
//        helper.setText(content, true);
//        mailSender.send(message1);
    }

    /*
     * Feedback Get api
     * */
    @GetMapping("/feedbacklist")
    public ResponseEntity<?> getFeedbackList(HttpServletRequest request) {
        Response<Feedback> response = feedbackService.getFeedbackList(request);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }


    /* FeedBack Create API */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveFeedBack(HttpServletRequest req) {
//    	@RequestBody FeedbackRequest feedReq, 
    	FeedbackRequest feedReq = (FeedbackRequest) req.getAttribute("feedReq");
        Set<ConstraintViolation<FeedbackRequest>> violations = validator.validate(feedReq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Response<String> response = feedbackService.saveFeedback(feedReq);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }

    /* FeedBack Update API */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateFeedback(@RequestParam(value = "id", required = true) Long id,@RequestBody FeedbackRequest feedReq, HttpServletRequest req) {
        AuthenticatedUser auth = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<ConstraintViolation<FeedbackRequest>> violations = validator.validate(feedReq);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Response<String> response = feedbackService.updateFeedback(id, Math.toIntExact(auth.getUserId()), feedReq, req);
        return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
    }


}
