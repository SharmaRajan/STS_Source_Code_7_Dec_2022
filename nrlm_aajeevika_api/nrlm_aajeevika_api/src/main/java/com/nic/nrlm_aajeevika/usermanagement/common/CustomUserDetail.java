package com.nic.nrlm_aajeevika.usermanagement.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nic.nrlm_aajeevika.usermanagement.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthenticatedUser user;
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
////		user.getRole()
//		SimpleGrantedAuthority simpleGrantedAuthority =new SimpleGrantedAuthority("ROLE_ADMIN");
//		return java.util.List.of(simpleGrantedAuthority);
//	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
////		user.getRole()
//		authorities.add(authority);
//		return authorities;
//	}
//	@Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//    final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//
//    Set<Role> roles = null;
//
//    if (user != null) {
//        roles = user.getUserRoles();
//    }
//    if (roles != null) {
//        for (Role role : roles) {
//        authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//    }
//    return authorities;
//    }
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles = user.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
//        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority((user.getLevelId()+"").toUpperCase()));
//        }
         
        return authorities;
    }

	public CustomUserDetail(AuthenticatedUser user) {
		super();
		this.user = user;
	}

	public long getUserId() {
		return this.user.getUserId();
	}
	
	public Integer getLevelId() {
		return this.user.getLevelId();
	}

//	 public String getFullName() {
//	        return this.user.getFullName();
//	    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		if(user.getLoginflag() == null)return true;
//		return !user.getLoginflag().equals("Y");
		return true;
	}

}