package com.nic.nrlm_aajeevika.usermanagement.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.nrlm_aajeevika.response.ExceptionResponse;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUserToken;
import com.nic.nrlm_aajeevika.usermanagement.common.MyConstant;
import com.nic.nrlm_aajeevika.usermanagement.service.UserDetailsServiceImpl;
import com.nic.nrlm_aajeevika.usermanagement.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, UsernameNotFoundException, JwtException {
		System.out.println("--------------request.getRequestURI() jwt------" + request.getRequestURI());
		if (!request.getRequestURI().equals(MyConstant.LOGIN_URL)
				&& !request.getRequestURI().equals(MyConstant.CAPTCHA_URL)) {

			String jwt = getJwt(request);
			String loginId = null;
			String tokenType = null;
			AuthenticatedUser authenticatedUser = null;

			if (jwt != null) {
				tokenType = jwtUtil.validateApiTokenType(jwt);
				if (tokenType.equals(MyConstant.VALIDATED_JWT) || tokenType.equals(MyConstant.EXPIRED_JWT_EXCEPTION)) {
					loginId = jwtUtil.extractTokenUsername(jwt);
				} else {
					String message = MyConstant.UNAUTHORIZED_TOKEN_MSG;
					ExceptionResponse ex = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(),
							request.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					final ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(response.getOutputStream(), ex);
				}
			} else {
				filterChain.doFilter(request, response);
			}
			if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				if (request.getRequestURI().equals(MyConstant.REFRESH_URL)) {
					if (tokenType.equals(MyConstant.EXPIRED_JWT_EXCEPTION)) {
						authenticatedUser = (AuthenticatedUser) userDetailsService.validateUser(loginId);
						authenticatedUser.setTokenType(tokenType);

						filterChain.doFilter(request, response);
					} else {
						String message = MyConstant.NOT_EXPIRED_TOKEN_MSG;
						ExceptionResponse ex = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(),
								request.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
						response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						final ObjectMapper mapper = new ObjectMapper();
						mapper.writeValue(response.getOutputStream(), ex);
					}

				} else if (tokenType.equals(MyConstant.VALIDATED_JWT)) { // request token if validated
					authenticatedUser = (AuthenticatedUser) userDetailsService.validateUser(loginId); // use a different
																										// method

					if (loginId.equals(authenticatedUser.getUsername())
							&& jwt.equals(authenticatedUser.getUserToken())) {
						// String dbTokenType =
						// jwtUtil.validateApiTokenType(authenticatedUser.getUserToken());
						// if(dbTokenType.equals(MyConstant.VALIDATED_JWT)) {
						authenticatedUser.setTokenType(tokenType);
						UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
								authenticatedUser, null, null);
//                    AuthenticatedUserToken authToken = new AuthenticatedUserToken(authenticatedUser.getUsername(), authenticatedUser.getPassword(), authenticatedUser.getAuthorities(), authenticatedUser);
						authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authToken);

					}
					filterChain.doFilter(request, response);
				} else {

					String message = "";
					if (tokenType.equals(MyConstant.EXPIRED_JWT_EXCEPTION)) {
						message = MyConstant.EXPIRED_TOKEN_MSG;
					} else {
						message = MyConstant.UNAUTHORIZED_TOKEN_MSG;
					}

					ExceptionResponse ex = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(),
							request.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					final ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(response.getOutputStream(), ex);
				}
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	private String getJwt(HttpServletRequest request) {
		if (request.getRequestURI().equals(MyConstant.LOGIN_URL)
				|| request.getRequestURI().equals(MyConstant.CAPTCHA_URL)) {
			return null;
		}
		String authHeader = request.getHeader(MyConstant.AUTHORIZATION_HDR);

		if (authHeader != null && authHeader.startsWith(MyConstant.TOKEN_STARTS_WITH)) {
			return authHeader.replace(MyConstant.TOKEN_STARTS_WITH, "");
		}

		return null;
	}
}
