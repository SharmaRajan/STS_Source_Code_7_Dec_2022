package com.nrlm.mclfmis.usermanagement.constant;

public class MyConstant {

	public static final Integer MAX_PASSWORD_EXPIRATION_DAYS = 90;
	public static final String MAX_LOGIN_ATTEMPT = "5";
	public static final Integer MAX_ACCOUNT_LOCKED_MINUTES = 10;
	public static final String USER_LOGGED_IN = "Y";
	public static final String USER_STATUS = "Y";
	public static final String JWT_SECRET_KEY = "NRLM_USER_AUTHENTICATION123!@#";
	public static final Long JWT_EXPIRATION_SECONDS = 30 * 24 * 60 * 60L;
	public static final String RESET_LOGIN_ATTEMPT = "0";
	public static final String LOGIN_URL = "/mclf/auth/login";
	//public static final String AUTHENTICATION_URL = "http://13.232.102.209/vprp/api/security/user/authentication";
	public static final String AUTHENTICATION_URL = "http://13.232.102.209/vprpclfdev/api/security/user/authentication";
	//public static final String AUTHENTICATION_URL = "https://prodmclf-um.dhwaniris.in/vprp/api/security/user/authentication";
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
	public static final Integer MCLF_APPLICATION_ID = 8;
	public static final String UNAUTHORIZED_USER = "Unauthorized User";
	public static final Long LAST_ACCESS_LIMIT_SECONDS = 30 * 60L;
	public static final Integer API_LIMIT_NO = 20;
	public static final Integer API_TIME_LIMIT = 1;
	
}
