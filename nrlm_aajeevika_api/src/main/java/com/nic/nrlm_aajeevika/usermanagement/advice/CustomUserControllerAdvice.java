package com.nic.nrlm_aajeevika.usermanagement.advice;

import com.nic.nrlm_aajeevika.response.ExceptionResponse;
import com.nic.nrlm_aajeevika.usermanagement.common.MyConstant;
import com.nic.nrlm_aajeevika.usermanagement.common.RequestContext;
import com.nic.nrlm_aajeevika.usermanagement.request.LoginRequest;
import com.nic.nrlm_aajeevika.usermanagement.service.AuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
@SuppressWarnings("unchecked")
public class CustomUserControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    AuthenticationService authService;
    @Inject
    private RequestContext<LoginRequest> requestContext;

    /*@ExceptionHandler(CustomExceptionHandler.class)
    public final ResponseEntity<Object> handleCustomException(CustomExceptionHandler ex, WebRequest request) throws Exception {
        //ex.printStackTrace();
        System.out.println(ex.getMessage());
        //ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        //return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
        return handleExceptionInternal(ex, "CustomExceptionHandler", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
        //return new ResponseEntity<Object>("Wrong Credentials",HttpStatus.NOT_FOUND);
    }
    */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "Either user not found or something is wrong with user";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountExpiredException.class)
    public final ResponseEntity<ExceptionResponse> handleAccountExpiredException(AccountExpiredException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "User is logged in from other device";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LockedException.class)
    public final ResponseEntity<ExceptionResponse> handleLockedException(LockedException ex, WebRequest request, HttpServletRequest httpRequest) throws Exception {
        String message = "User Account is locked for 10 minutes";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public final ResponseEntity<ExceptionResponse> handleCredentialsExpiredException(CredentialsExpiredException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "User credentials are expired";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public final ResponseEntity<ExceptionResponse> handleDisabledException(DisabledException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "User is disabled.Contact to administrator";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest httpRequest) throws Exception {
        String message = ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "";
        //ex.printStackTrace();
        if (ex.getMessage().equals(MyConstant.USER_NOT_FOUND)) {
            message = MyConstant.USER_NOT_FOUND;
        } else {
            LoginRequest lr = requestContext.getRequestBody();
            int remAttempt = authService.remainingAttempt(lr.getLoginId());

            if (remAttempt == 0) {
                message = "Account is locked for 10 minutes";
            } else {
                message = "Remaining Attempt left " + remAttempt;
            }
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public final ResponseEntity<ExceptionResponse> handleTokenException(MalformedJwtException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "Malformed Token";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "User not found";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);

    }

    /*@ExceptionHandler(InternalAuthenticationServiceException.class)
    public final ResponseEntity<Object> handleException(InternalAuthenticationServiceException ex, WebRequest request) throws Exception {

        //ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        //return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>("Authentication Error",HttpStatus.UNAUTHORIZED);
    }*/
    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ExceptionResponse> handleException(NullPointerException ex, HttpServletRequest httpRequest) throws Exception {
        String message = ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<ExceptionResponse> handleExpiredJwtException(ExpiredJwtException ex, HttpServletRequest httpRequest) throws Exception {
        String message = "Token Expired!";
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public final ResponseEntity<ExceptionResponse> handleJwtException(JwtException ex, HttpServletRequest httpRequest) throws Exception {
        String message = ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), new Date(), httpRequest.getRequestURI(), message, HttpStatus.UNAUTHORIZED.name());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }
}
