package com.nic.nrlm_aajeevika.usermanagement.service;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

	void updateUserDetail(String loginId,String jwt);
	
	void updateUserToken(String loginId,String jwt) ;
	
	int remainingAttempt(String loginId);
	
		void saveloginhistory(HttpServletRequest request,String loginid,int flag);

}
