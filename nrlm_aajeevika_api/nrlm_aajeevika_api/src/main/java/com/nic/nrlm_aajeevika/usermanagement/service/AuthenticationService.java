package com.nic.nrlm_aajeevika.usermanagement.service;



public interface AuthenticationService {

	void updateUserDetail(String loginId,String jwt);
	
	void updateUserToken(String loginId,String jwt) ;
	
	int remainingAttempt(String loginId);

}
