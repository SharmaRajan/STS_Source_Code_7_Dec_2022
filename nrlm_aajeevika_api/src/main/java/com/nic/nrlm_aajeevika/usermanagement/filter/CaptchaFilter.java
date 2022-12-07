package com.nic.nrlm_aajeevika.usermanagement.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.nrlm_aajeevika.entity.Feedback;
import com.nic.nrlm_aajeevika.request.FeedbackRequest;
import com.nic.nrlm_aajeevika.usermanagement.common.MyConstant;
import com.nic.nrlm_aajeevika.usermanagement.entity.CaptchaEntity;
import com.nic.nrlm_aajeevika.usermanagement.repository.CaptchaRepository;
import com.nic.nrlm_aajeevika.usermanagement.request.LoginRequest;
import com.nic.nrlm_aajeevika.usermanagement.response.AuthenticationExceptionResponse;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

	@Autowired
	CaptchaRepository captchaRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// System.out.println("--------request.getRequestURI()
		// captcha--------"+request.getRequestURI());

		if (request.getRequestURI().equals(MyConstant.LOGIN_URL)) {
			System.out.println("++++++++++++++++ inner request.getRequestURI() " + request.getRequestURI());
			boolean authFlag = false;
			String msg = null;
			LoginRequest lrdto = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			request.setAttribute("loginReq", lrdto);
			String nocaptcha = lrdto.getNoCaptcha();
			// System.out.println("********no cpatch*********" + nocaptcha);
			nocaptcha = (nocaptcha == null ? "No" : nocaptcha);
			// System.out.println("********after no cpatch*********" + nocaptcha);
			if (!nocaptcha.equals(MyConstant.NOCAPTCHA)) {
				if (lrdto.getCaptcha() == null || lrdto.getCaptcha().isEmpty()) {
					msg = "Missing Captcha";
					authFlag = false;
				} else if (lrdto.getSessionId() == null || lrdto.getSessionId().isEmpty()) {
					msg = "Missing Session Info";
					authFlag = false;
				} else {
					CaptchaEntity ce = captchaRepository.findBySessionId(lrdto.getSessionId());
					// System.out.println("--------ce--------"+ce.getCaptcha());
					// System.out.println("--------request--------"+lrdto.getCaptcha());
					if (ce != null && ce.getId() != 0) {
						/*
						 * if((new Date().getTime() - ce.getCreatedAt().getTime()) > (10*60*1000)) {
						 * 
						 * // msg = "Captcha Expired!"; authFlag = false; }
						 */
						if (!lrdto.getCaptcha().equals(ce.getCaptcha())) {
							msg = "Invalid Captcha";
							authFlag = false;
						} else {
							authFlag = true;
						}
					} else {
						msg = "No Captcha Found";
						authFlag = false;
					}
				}
			} else {
				authFlag = true;
			}
			if (authFlag) {
				filterChain.doFilter(request, response);
			} else {
				AuthenticationExceptionResponse ex = new AuthenticationExceptionResponse(
						HttpStatus.UNAUTHORIZED.value(), new Date(), request.getRequestURI(), msg,
						HttpStatus.UNAUTHORIZED.name());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				final ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), ex);
			}
		} else if (request.getRequestURI().equals(MyConstant.FEEDBACK_URL)) {
			System.out.println("++++++++++++++++ inner request.feedback() " + request.getRequestURI());
			boolean authFlag = false;
			String msg = null;
			FeedbackRequest feedbackdto = new ObjectMapper().readValue(request.getInputStream(), FeedbackRequest.class);
			request.setAttribute("feedReq", feedbackdto);

			if (feedbackdto.getCaptcha() == null || feedbackdto.getCaptcha().isEmpty()) {
				msg = "Missing Captcha";
				authFlag = false;
			} else if (feedbackdto.getSessionId() == null || feedbackdto.getSessionId().isEmpty()) {
				msg = "Missing Session Info";
				authFlag = false;
			} else {
				CaptchaEntity ce = captchaRepository.findBySessionId(feedbackdto.getSessionId());
				if (ce != null && ce.getId() != 0) {

					if (!feedbackdto.getCaptcha().equals(ce.getCaptcha())) {
						msg = "Invalid Captcha";
						authFlag = false;
					} else {
						authFlag = true;
					}
				} else {
					msg = "No Captcha Found";
					authFlag = false;
				}
			}
			if (authFlag) {
				filterChain.doFilter(request, response);
			} else {
				AuthenticationExceptionResponse ex = new AuthenticationExceptionResponse(
						HttpStatus.UNAUTHORIZED.value(), new Date(), request.getRequestURI(), msg,
						HttpStatus.UNAUTHORIZED.name());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				final ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), ex);
			}

		}

		else {
			filterChain.doFilter(request, response);
		}

	}

}
