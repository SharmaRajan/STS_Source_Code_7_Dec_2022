package com.nic.nrlm_aajeevika.usermanagement.service;

import com.nic.nrlm_aajeevika.usermanagement.entity.User;
import com.nic.nrlm_aajeevika.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier(value = "authService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    AuthenticationRepository authRepository;

    @Override
    public void updateUserDetail(String loginId, String jwt) {
        User user = null;
        Optional<User> optionalUser = authRepository.findByUsername(loginId);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
//			user.setLoginAttempt(MyConstant.RESET_LOGIN_ATTEMPT);
//			user.setLastAttemptOn(new Date());
//			user.setLoginFlag(MyConstant.USER_LOGGED_IN);
            user.setToken(jwt);
            authRepository.save(user);
        }
    }

    @Override
    public void updateUserToken(String loginId, String jwt) {
        User user = null;
        Optional<User> optionalUser = authRepository.findByUsername(loginId);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
//			user.setLastAttemptOn(new Date());
            user.setToken(jwt);
            authRepository.save(user);
        }
    }

    public int remainingAttempt(String loginId) {

        Optional<User> optionalUser = authRepository.findByUsername(loginId);
//		if(optionalUser.isPresent()) {
//			User user = optionalUser.get();
//			if(user.getLoginAttempt() == null || user.getLoginAttempt().equals(MyConstant.MAX_LOGIN_ATTEMPT)|| user.getLoginAttempt().equals(MyConstant.RESET_LOGIN_ATTEMPT) || user.getLastAttemptOn() == null) {
//				user.setLoginAttempt("1");
//				user.setLastAttemptOn(new Date());
//			}
//			else {
//				/*long diff = new Date().getTime() - user.getLastAttemptOn().getTime();
//				long diffMinutes = diff / (60 * 1000);
//
//				if(diffMinutes > 10) {
//					user.setLoginAttempt("1");
//					user.setLastAttemptOn(new Date());
//				}*/
//				//else {
//					String logAttempt = String.valueOf((Integer.parseInt(user.getLoginAttempt()) + 1));
//					user.setLoginAttempt(logAttempt);
//					user.setLastAttemptOn(new Date());
//				//}
//			}

        int remAttempt = 5; // Integer.parseInt(MyConstant.MAX_LOGIN_ATTEMPT) - Integer.parseInt(user.getLoginAttempt());
        //if(remAttempt == 0) {
        //user.set(false);
        //}
//			authRepository.save(user);
        return remAttempt;
//		}
//		return 0;

    }

}
