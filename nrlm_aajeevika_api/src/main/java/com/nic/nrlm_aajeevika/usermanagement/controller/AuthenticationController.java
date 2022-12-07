package com.nic.nrlm_aajeevika.usermanagement.controller;

import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUser;
import com.nic.nrlm_aajeevika.usermanagement.common.AuthenticatedUserToken;
import com.nic.nrlm_aajeevika.usermanagement.common.CustomUserDetail;
import com.nic.nrlm_aajeevika.usermanagement.common.MyConstant;
import com.nic.nrlm_aajeevika.usermanagement.common.RequestContext;
import com.nic.nrlm_aajeevika.usermanagement.request.LoginRequest;
import com.nic.nrlm_aajeevika.usermanagement.request.LoginRequestNew;
import com.nic.nrlm_aajeevika.usermanagement.response.LoginResponseDto;
import com.nic.nrlm_aajeevika.usermanagement.service.AuthenticationService;
import com.nic.nrlm_aajeevika.usermanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Inject
    private RequestContext<LoginRequest> requestContext;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to user management";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(HttpServletRequest req) throws Exception {
    	LoginRequest lrdto = (LoginRequest) req.getAttribute("loginReq");
    	System.out.println("********contrrrrrrrr***************"+lrdto);
    	req.removeAttribute("loginReq");
        Authentication auth = null;
        auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lrdto.getLoginId(), lrdto.getPassword()));
        LoginResponseDto response = new LoginResponseDto();
        CustomUserDetail userDetails = (CustomUserDetail) auth.getPrincipal();
        List<String> authList = new ArrayList<String>();
        if (auth.getAuthorities() != null && auth.getAuthorities().size() != 0) {
            authList = auth.getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toList());
        }

        String jwt = jwtUtil.generateToken(auth.getName(), authList);
        authService.updateUserDetail(lrdto.getLoginId(), jwt);
        response.setAccessToken(jwt);
        response.setAuthorities(authList);
        response.setUsername(auth.getName());
        response.setLevelId(userDetails.getLevelId());
        response.setResponseCode(HttpStatus.OK.value());
        response.setResponseDesc(HttpStatus.OK.name());
        
        authService.saveloginhistory(req, auth.getName(), 1);
        
        return new ResponseEntity<LoginResponseDto>(response, HttpStatus.OK);
    }


    @GetMapping("/refreshToken")
    public ResponseEntity<LoginResponseDto> refreshToken() throws Exception {

        AuthenticatedUserToken authUserToken = (AuthenticatedUserToken) SecurityContextHolder.getContext().getAuthentication();
        AuthenticatedUser authUser = authUserToken.getAuthenticatedUser();

        String jwt = "";
        LoginResponseDto response = new LoginResponseDto();
        if (authUser.getTokenType() != null && authUser.getTokenType().equals(MyConstant.EXPIRED_JWT_EXCEPTION)) {
            List<String> authList = new ArrayList<String>();
            if (authUser.getAuthorities() != null && authUser.getAuthorities().size() != 0) {
                authList = authUser.getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toList());
            }
            jwt = jwtUtil.generateToken(authUser.getUsername(), authList);
            authService.updateUserDetail(authUser.getUsername(), jwt);
            response.setAccessToken(jwt);
            response.setResponseCode(HttpStatus.OK.value());
            response.setResponseDesc(HttpStatus.OK.name());
            return new ResponseEntity<LoginResponseDto>(response, HttpStatus.OK);
        } else {
            response.setResponseCode(HttpStatus.UNAUTHORIZED.value());
            response.setResponseDesc(HttpStatus.UNAUTHORIZED.name());
            return new ResponseEntity<LoginResponseDto>(response, HttpStatus.UNAUTHORIZED);
        }


    }

//    @PostMapping("/validateToken")
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ValidateTokenResponse> validateToken(@RequestBody ValidateTokenRequest vtrdto) {
//
//        AuthenticatedUserToken authUserToken = (AuthenticatedUserToken) SecurityContextHolder.getContext().getAuthentication();
//        AuthenticatedUser authUser = authUserToken.getAuthenticatedUser();
//
//        ValidateTokenResponse response = new ValidateTokenResponse();
//
//        if (authUser.getTokenType() != null && authUser.getTokenType().equals(MyConstant.VALIDATED_JWT)) {
//            response.setLoginId(authUser.getUserId());
//            List<String> authList = new ArrayList<String>();
//            if (authUser.getAuthorities() != null && authUser.getAuthorities().size() != 0) {
//                authList = authUser.getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toList());
//            }
//            response.setLevelId(authUser.getLevelId());
//            response.setLevelName(authUser.getLevelName());
//            response.setAuthorities(authList);
//            response.setResponseCode(HttpStatus.OK.value());
//            response.setResponseDesc(HttpStatus.OK.name());
//        } else {
//            throw new JwtException("Token Expired");
//        }
//
//        if (vtrdto.getIsAuthorize() != null && vtrdto.getIsAuthorize().equals("Y")) {
//            // do authorization for that endpoint
//        }
//
//        return new ResponseEntity<ValidateTokenResponse>(response, HttpStatus.OK);
//
//    }
}
