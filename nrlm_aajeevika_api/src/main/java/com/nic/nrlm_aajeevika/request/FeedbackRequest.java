package com.nic.nrlm_aajeevika.request;

import javax.validation.constraints.NotNull;

public class FeedbackRequest {

    @NotNull(message = "Name is required.")
    private String name;
    @NotNull(message = "Email is required.")
    private String email;
    @NotNull(message = "feedback is required.")
    private String feedback;
    @NotNull(message = "captcha is required.")
    private String captcha;
    @NotNull(message = "session ID is required.")
    private String sessionId;

    public FeedbackRequest() {
    }

    public FeedbackRequest(String name, String email, String feedback) {
        this.name = name;
        this.email = email;
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
    
	
}
