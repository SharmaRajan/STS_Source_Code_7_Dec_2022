package com.nic.nrlm_aajeevika.usermanagement.common;

public class MyConstant {

	public static final Integer MAX_PASSWORD_EXPIRATION_DAYS = 90;
	public static final String MAX_LOGIN_ATTEMPT = "5";
	public static final Integer MAX_ACCOUNT_LOCKED_MINUTES = 10;
	public static final String USER_LOGGED_IN = "Y";
	public static final String USER_STATUS = "1";
	public static final String JWT_SECRET_KEY = "NRLM_AAJEEVIKA_AUTHENTICATION123!@#";
	public static final Integer JWT_EXPIRATION_SECONDS = 30 * 24 * 60 * 60;
	public static final String RESET_LOGIN_ATTEMPT = "0";
	public static final String LOGIN_URL = "auth/login";
	public static final String SIGNATURE_EXCEPTION = "SignatureException";
	public static final String MALFORMED_JWT_EXCEPTION = "MalformedJwtException";
	public static final String EXPIRED_JWT_EXCEPTION = "ExpiredJwtException";
	public static final String UNSUPPORTED_JWT_EXCEPTION = "UnsupportedJwtException";
	public static final String ILLEGAL_ARGUMENT_EXCEPTION = "IllegalArgumentException";
	public static final String JWT_EXCEPTION = "JWTException";
	public static final String VALIDATED_JWT = "ValidatedJWT";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE = "FAILURE";
	public static final String REFRESH_URL = "auth/refreshToken";
	public static final String USER_NOT_FOUND = "User Not Found";
	public static final String UNAUTHORIZED_TOKEN_MSG = "UnAuthorized Token";
	public static final String EXPIRED_TOKEN_MSG = "Token Expired !";
	public static final String NOT_EXPIRED_TOKEN_MSG = "Token Not Expired !";
	public static final String AUTHORIZATION_HDR = "Authorization";
	public static final String TOKEN_STARTS_WITH = "Bearer ";
	public static final int INACTIVATE = 2;
	public static final int ACTIVATE = 1;
	
}
