package com.nic.nrlm_aajeevika.usermanagement.service;

import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import com.nic.nrlm_aajeevika.usermanagement.common.CustomUserDetail;
import com.nic.nrlm_aajeevika.usermanagement.common.MyConstant;
import com.nic.nrlm_aajeevika.usermanagement.entity.User;
import com.nic.nrlm_aajeevika.repository.AuthenticationRepository;
import com.nic.nrlm_aajeevika.usermanagement.entity.UserLevel;
import com.nic.nrlm_aajeevika.usermanagement.handler.CustomExceptionHandler;
import com.nic.nrlm_aajeevika.usermanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    AuthenticationRepository authRepo;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = null;
        Optional<User> optionalUser = authRepo.findByUsername(loginId);
        if (!optionalUser.isPresent()) {
//            throw new CustomExceptionHandler("User Not Found");
            MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
            //throw new UsernameNotFoundException(MyConstant.USER_NOT_FOUND);
            //throw new CustomExceptionHandler(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        	
        	throw new CustomExceptionHandler(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badRequest", "Invalid Username / Password"));
        	
        } else {
				List<UserLevel> roleList = authRepo.getUserRoles(Long.valueOf(optionalUser.get().getLevelId()));
				if(roleList != null && roleList.size() != 0) {
					grantedAuthorities = roleList.stream().map(r -> {
						return new SimpleGrantedAuthority(r.getName());
					}).collect(Collectors.toList());
				}
        }
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();

        User user = optionalUser.get();
        authenticatedUser.setUserId(user.getId());
        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setPassword(user.getPassword());
        authenticatedUser.setAuthorities(grantedAuthorities);
        authenticatedUser.setUserToken(user.getToken());

        authenticatedUser.setLevelId(user.getLevelId());
        authenticatedUser.setLevelName("Admin");
        if (user.getIsDeleted() != null && user.getIsDeleted().equals(MyConstant.USER_STATUS)) {
            authenticatedUser.setEnabled(true);
        } else {
            authenticatedUser.setEnabled(false);
        }


//			if(user.getLoginFlag() != null && user.getLoginFlag().equals(MyConstant.USER_LOGGED_IN)) {
        String userToken = user.getToken();
        if (jwtUtil.validateToken(userToken)) {
            authenticatedUser.setAccountNonExpired(false);
        } else {
            authenticatedUser.setAccountNonExpired(true);
        }
//			}
//			else {
//				authenticatedUser.setAccountNonExpired(true);
//			}
//			if(user.getPasswordModifiedOn() != null) {
//				long pwdDiffDays = (new Date().getTime() - user.getPasswordModifiedOn().getTime())/(24*60*1000);
//
//				if(pwdDiffDays > MyConstant.MAX_PASSWORD_EXPIRATION_DAYS) {
//					authenticatedUser.setCredentialNonExpired(false);
//				}
//				else {
        authenticatedUser.setCredentialNonExpired(true);
//				}
//			}
//			else {
//				authenticatedUser.setCredentialNonExpired(true);
//			}

//			if(user.getLoginAttempt() != null && user.getLoginAttempt().equals(MyConstant.MAX_LOGIN_ATTEMPT)) {
//				long diffMinutes = (new Date().getTime() - user.getLastAttemptOn().getTime()) / (60 * 1000);
//				if(diffMinutes > MyConstant.MAX_ACCOUNT_LOCKED_MINUTES) {
//					authenticatedUser.setAccountNonLocked(true);
//				}
//				else {
//					authenticatedUser.setAccountNonLocked(false);
//				}
//			}
//			else {
        authenticatedUser.setAccountNonLocked(true);
//			}
        return new CustomUserDetail(authenticatedUser);

    }

    public UserDetails validateUser(String loginId) throws UsernameNotFoundException {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        List<GrantedAuthority> grantedAuthorities = null;
        Optional<User> optionalUser = authRepo.findByUsername(loginId);
        if (!optionalUser.isPresent()) {
            return authenticatedUser;
        } else {
            List<UserLevel> roleList = authRepo.getUserRoles(Long.valueOf(optionalUser.get().getLevelId()));
            if(roleList != null && roleList.size() != 0) {
                grantedAuthorities = roleList.stream().map(r -> {
                    return new SimpleGrantedAuthority(r.getName());
                }).collect(Collectors.toList());
            }
        }

        User user = optionalUser.get();
        authenticatedUser.setUserId(user.getId());
        authenticatedUser.setUsername(user.getUsername());
        authenticatedUser.setPassword(user.getPassword());
        authenticatedUser.setAuthorities(grantedAuthorities);
        authenticatedUser.setUserToken(user.getToken());
        authenticatedUser.setLevelId(user.getLevelId());
        authenticatedUser.setLevelName("Admin");
        if (user.getIsDeleted() != null && user.getIsDeleted().equals(MyConstant.USER_STATUS)) {
            authenticatedUser.setEnabled(true);
        } else {
            authenticatedUser.setEnabled(false);
        }

//		if(user.getLoginFlag() != null && user.getLoginFlag().equals(MyConstant.USER_LOGGED_IN)) {
        String userToken = user.getToken();
        if (jwtUtil.validateToken(userToken)) {
            authenticatedUser.setAccountNonExpired(true);
        } else {
            authenticatedUser.setAccountNonExpired(false);
        }
//		}
//		else {
//			authenticatedUser.setAccountNonExpired(false);
//		}
//		if(user.getPasswordModifiedOn() != null) {
//			long pwdDiffDays = (new Date().getTime() - user.getPasswordModifiedOn().getTime())/(24*60*1000);
//
//			if(pwdDiffDays > MyConstant.MAX_PASSWORD_EXPIRATION_DAYS) {
//				authenticatedUser.setCredentialNonExpired(false);
//			}
//			else {
//				authenticatedUser.setCredentialNonExpired(true);
//			}
//		}
//		else {
        authenticatedUser.setCredentialNonExpired(true);
//		}

//		if(user.getLoginAttempt() != null && user.getLoginAttempt().equals(MyConstant.MAX_LOGIN_ATTEMPT)) {
//			long diffMinutes = (new Date().getTime() - user.getLastAttemptOn().getTime()) / (60 * 1000);
//			if(diffMinutes > MyConstant.MAX_ACCOUNT_LOCKED_MINUTES) {
//				authenticatedUser.setAccountNonLocked(true);
//			}
//			else {
//				authenticatedUser.setAccountNonLocked(false);
//			}
//		}
//		else {
        authenticatedUser.setAccountNonLocked(true);
//		}
        return authenticatedUser;

    }

}
