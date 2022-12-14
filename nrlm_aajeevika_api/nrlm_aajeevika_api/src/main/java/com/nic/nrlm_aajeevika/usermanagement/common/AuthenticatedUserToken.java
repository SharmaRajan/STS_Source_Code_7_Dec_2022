package com.nic.nrlm_aajeevika.usermanagement.common;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

public class AuthenticatedUserToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final Object principal;

	private Object credentials;
	
	private AuthenticatedUser authUser;

	/**
	 * This constructor can be safely used by any code that wishes to create a
	 * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
	 * will return <code>false</code>.
	 *
	 */
	public AuthenticatedUserToken(Object principal, Object credentials,AuthenticatedUser authUser) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.authUser = authUser;
		setAuthenticated(false);
	}

	/**
	 * This constructor should only be used by <code>AuthenticationManager</code> or
	 * <code>AuthenticationProvider</code> implementations that are satisfied with
	 * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
	 * authentication token.
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public AuthenticatedUserToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, AuthenticatedUser authUser) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.authUser = authUser;
		super.setAuthenticated(true); // must use super, as we override
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		Assert.isTrue(!isAuthenticated,
				"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.credentials = null;
	}
	
	public AuthenticatedUser getAuthenticatedUser() {
		return this.authUser;
	}

}
