package com.nrlm.mclfmis.usermanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;
import com.nrlm.mclfmis.usermanagement.constant.MyConstant;
import com.nrlm.mclfmis.usermanagement.entity.LocationEntity;
import com.nrlm.mclfmis.usermanagement.entity.LoginHistoryEntity;
import com.nrlm.mclfmis.usermanagement.entity.UserEntity;
import com.nrlm.mclfmis.usermanagement.repository.AuthenticationRepository;
import com.nrlm.mclfmis.usermanagement.repository.LoginHistoryRepository;
import com.nrlm.mclfmis.usermanagement.response.AuthenticationExceptionResponse;
import com.nrlm.mclfmis.usermanagement.response.LoginResponseDto;
import com.nrlm.mclfmis.usermanagement.response.ValidateTokenResponse;
import com.nrlm.mclfmis.usermanagement.util.JwtUtil;

import io.jsonwebtoken.Claims;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationRepository authRepository;

	@Autowired
	RestTemplate externalRestTemplate;

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	LoginHistoryRepository loginHistoryRepository;
	
	@Override
	public List<String> getUserAuthorities(String level) {
		List<String> authorities = null;
		List<Object[]> authoritiesList = authRepository.getUserAuthorities(level);
		if (authoritiesList != null && authoritiesList.size() != 0) {
			authorities = authoritiesList.stream().map(auth -> auth[0].toString()).collect(Collectors.toList());
		}
		return authorities;
	}

	@Override
	public Map<String, Object> getModulePermissions(String level) {
		try {
			List<Object[]> permissionList = authRepository.getModulePermissions(level);
			if (permissionList != null && permissionList.size() != 0) {
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> permissionMap = new HashMap<String, Object>();
				Map<String, Boolean> perm = null;
				for (Object[] obj : permissionList) {
					perm = mapper.readValue(new JSONObject(obj[1].toString()).toString(),
							new TypeReference<Map<String, Boolean>>() {
							});
					permissionMap.put(obj[0].toString(), perm);
				}
				return permissionMap;
			} 
			else {
				throw new HandledException(HttpStatus.NOT_FOUND, "No Module Permission Found");
			}
		} 
		catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} 
		catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}

	@Override
	public LoginResponseDto processLogin(Map<String, String> loginrequest,HttpServletRequest request) {
		Integer loginStatus = 0;
		String loginMsg = null;

		LoginResponseDto resp = new LoginResponseDto();
		boolean appFlag = false;
		String level = "";
		String levelName = "";
		Integer levelId = null;
		Map<String, Object> permissions = new HashMap<String, Object>();
		Map<String, Object> userInfo = new HashMap<String, Object>();
		Map<String, Object> userGeo = new HashMap<String, Object>();
		List<String> locations = new ArrayList<String>();
		Long userId = 0L;
		HttpHeaders headers = new HttpHeaders();
        headers.set("channel","1001");
		HttpEntity<Map<String, String>> req = new HttpEntity<Map<String, String>>(loginrequest,headers);
		ResponseEntity<Object> res = null;
		ObjectMapper mapper = new ObjectMapper();
		JSONObject result = null;
		JSONObject data = null;

		try {
			
			res = externalRestTemplate.postForEntity(MyConstant.AUTHENTICATION_URL, req, Object.class);
			//System.out.println("Response Body"+res.getBody());
			if (res.getStatusCodeValue() == HttpStatus.CREATED.value()) {
				String resString = mapper.writeValueAsString(res.getBody());
				result = new JSONObject(resString);

				if (result.getInt("statusCode") == 200 && result.has("data")) {
					data = (JSONObject) result.get("data");
					userInfo.put("loginId", data.get("loginId"));
					userInfo.put("userName", data.get("userName"));
					userInfo.put("mobNumPrimary", data.get("mobNumPrimary"));
					userId = Long.parseLong(String.valueOf(data.get("id")));
					if(userId == 0) {
						throw new HandledException(HttpStatus.UNAUTHORIZED, "Unauthorized User");
					}
					if (data.has("applications")) {
						JSONArray applicationArray = (JSONArray) data.get("applications");
						JSONObject application = null;

						for (int i = 0; i < applicationArray.length(); i++) {
							application = (JSONObject) applicationArray.get(i);
							if ((Integer) application.get("id") == 8) {
								appFlag = true;
								break;
							}
						}
						if (appFlag) {
							if (application.has("roles")) {
								JSONArray roleArray = (JSONArray) application.get("roles");
								JSONObject role = null;
								JSONArray roleCboArray  = null;
								JSONArray cbosLevelsArray = null;
								JSONArray cboDetailsArray = null;
								for (int j = 0; j < roleArray.length(); j++) {
									role = (JSONObject) roleArray.get(j);
									
									if(role.has("cbosLevelsPermitted")) {
										cbosLevelsArray = (JSONArray) role.get("cbosLevelsPermitted");
										if(cbosLevelsArray != null && cbosLevelsArray.length() != 0 ) {
											if(cbosLevelsArray.getInt(0) != 0) {
												if(role.has("rolesCBO")) {
													roleCboArray = (JSONArray) role.get("rolesCBO");
													if(roleCboArray == null || roleCboArray.length() == 0) {
														throw new HandledException(HttpStatus.UNAUTHORIZED, "Cbos mapping not found");
													}
													
												}
											}
											
										}
										
									}
									
									levelId = (Integer)role.get("roleId");
									if (levelId == 207) {
										level = "super_admin";
										levelName = "Super Admin"; 
										break;
									} 
									else if (levelId == 208) {
										level = "national";
										levelName = "National Manager";
										break;
									}
									else if (levelId == 209) {
										level = "state";
										levelName = "State Manager";
										String loc = ((JSONObject) ((JSONObject) role.get("roleGeos"))
												.get("stateData")).get("stateCode").toString();
										if(String.valueOf(loc).length() == 2) {
											locations.add(String.valueOf(loc));
											userInfo.put("locationCode", loc);
										}
										else {
											locations.add("0"+ loc);
											userInfo.put("locationCode", "0"+loc);
										}
										
										break;
									} 
									else if (levelId == 210) {
										level = "district";
										levelName = "District Manager";
										String loc = ((JSONObject) ((JSONObject) role.get("roleGeos"))
												.get("districtData")).get("districtCode").toString();
										 String stCode = ((JSONObject) ((JSONObject) role.get("roleGeos"))
												.get("stateData")).get("stateCode").toString();
										 if(stCode.length() == 2) {
											 locations.add(loc);
											userInfo.put("locationCode", loc);
										 }
										 else {
											 locations.add("0"+loc);
											userInfo.put("locationCode", "0"+loc);
										 }
										
										break;
									} 
									else if (levelId == 211) {
										level = "block";
										levelName = "MCLF Block Anchor";
										
										String loc = ((JSONObject) ((JSONObject) role.get("roleGeos"))
												.get("blockData")).get("blockCode").toString();
										
										String stCode = ((JSONObject) ((JSONObject) role.get("roleGeos"))
												.get("stateData")).get("stateCode").toString();
										 if(stCode.length() == 2) {
											 locations.add(loc);
											userInfo.put("locationCode",loc);
										 }
										 else {
											 locations.add("0"+loc);
											userInfo.put("locationCode", "0"+loc);
										 }
										break;
									} 
									
									else if (levelId == 212) {
										level = "clf";
										levelName = "MCLF MIS Assistant";
										break;
									} 
								}
								
								
								if(roleCboArray != null && roleCboArray.length() != 0){
									JSONObject roleCbo = null;
									roleCbo = (JSONObject) roleCboArray.get(0);
									
									if(roleCbo.get("cboLevel").toString().equals("2")) {
										cboDetailsArray = (JSONArray)roleCbo.get("cboDetails");
										//JSONArray clfCodeArray = (JSONArray)roleCbo.get("cboDetails");
										if(cboDetailsArray != null && cboDetailsArray.length() != 0) {
											for(int cbo = 0; cbo < cboDetailsArray.length();) {
												JSONObject clf = (JSONObject)cboDetailsArray.get(cbo);
												locations.add(clf.getString("federationCode"));
												userInfo.put("locationCode", clf.getString("federationCode"));
												break;
											}
										}
										
									}
										
								}

							} 
							else {
								throw new HandledException(HttpStatus.UNAUTHORIZED, "Roles Not Found");
							}

							if (!level.isEmpty()) {
								Object[] geoInfo = null;
								if(!level.equals(ProjectConstants.SUPER_ADMIN) && !level.equals(ProjectConstants.NATIONAL)) {
									List<Object[]> geoDetailsList = authRepository.getUserLocationDetails(level,userInfo.get("locationCode").toString());
									if(geoDetailsList != null && geoDetailsList.size() != 0) {
										geoInfo = geoDetailsList.get(0);	
									}
									else {
										throw new HandledException(HttpStatus.UNAUTHORIZED, "Locations Not Found");
									}
								}
								
								if(level.equals(ProjectConstants.STATE)) {
									if(geoInfo[0] != null) {
										userGeo.put("stateName", geoInfo[0].toString());
									}
									else {
										userGeo.put("stateName", null);
									}
									
								}
								else if(level.equals(ProjectConstants.STATE)) {
									if(geoInfo[0] != null) {
										userGeo.put("stateName", geoInfo[0].toString());
									}
									else {
										userGeo.put("stateName", null);
									}
									
								}
								else if(level.equals(ProjectConstants.DISTRICT)) {
									if(geoInfo[0] != null) {
										userGeo.put("stateName", geoInfo[0].toString());
									}
									else {
										userGeo.put("stateName", null);
									}
									if(geoInfo[1] != null) {
										userGeo.put("districtName", geoInfo[1].toString());
									}
									else {
										userGeo.put("districtName", null);
									}
									
								}
								else if(level.equals(ProjectConstants.BLOCK)) {
									if(geoInfo[0] != null) {
										userGeo.put("stateName", geoInfo[0].toString());
									}
									else {
										userGeo.put("stateName", null);
									}
									if(geoInfo[1] != null) {
										userGeo.put("districtName", geoInfo[1].toString());
									}
									else {
										userGeo.put("districtName", null);
									}
									if(geoInfo[2] != null) {
										userGeo.put("blockName", geoInfo[2].toString());
									}
									else {
										userGeo.put("blockName", null);
									}
									
								}
								else if(level.equals(ProjectConstants.CLF)) {
									if(geoInfo[0] != null) {
										userGeo.put("stateName", geoInfo[0].toString());
									}
									else {
										userGeo.put("stateName", null);
									}
									if(geoInfo[1] != null) {
										userGeo.put("districtName", geoInfo[1].toString());
									}
									else {
										userGeo.put("districtName", null);
									}
									if(geoInfo[2] != null) {
										userGeo.put("blockName", geoInfo[2].toString());
									}
									else {
										userGeo.put("blockName", null);
									}
									if(geoInfo[3] != null) {
										userGeo.put("clfName", geoInfo[3].toString());
									}
									else {
										userGeo.put("clfName", null);
									}
									
								}
								userInfo.put("userLevel", level);
								userInfo.put("userLevelName", levelName);
								userInfo.put("userLevelId", levelId);
								permissions = getModulePermissions(level);
								
							} 
							else {
								throw new HandledException(HttpStatus.UNAUTHORIZED, "User Level not found");
							}
						} 
						else {
							throw new HandledException(HttpStatus.UNAUTHORIZED, "Application Not Found");
						}
					} 
					else {
						throw new HandledException(HttpStatus.UNAUTHORIZED, "Applications key not found");
					}

				}
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED, "Invalid LoginId/Password");
				}

			} 
			else {
				throw new HandledException(HttpStatus.UNAUTHORIZED, "Unauthorized User");
			}
			
			resp.setUserInfo(userInfo);
			resp.setUserGeo(userGeo);
	
			Optional<UserEntity> optionalUser = authRepository.findByUserId(userId);
			
		
			
			UserEntity user = null;
			if(optionalUser.isPresent()) {
				user = optionalUser.get();
				if(user.getLoginStatus() != null && user.getLoginStatus() == 1) {
					if(user.getLastAccess() != null) {
						Long lastTimeDiff = System.currentTimeMillis() - user.getLastAccess().getTime();
						if(lastTimeDiff < (MyConstant.LAST_ACCESS_LIMIT_SECONDS*1000)) {
							throw new HandledException(HttpStatus.UNAUTHORIZED, "User is already logged in");
						}	
					}
				}
				
			}
			else {
				user = new UserEntity();
				user.setCreatedAt(new Date());
			}
			
			String token = jwtUtil.generateToken(userId, level);
			
			user.setUserId(userId);
			user.setLoginId(String.valueOf(data.get("loginId")));
			user.setPrimaryMobile(String.valueOf(data.get("mobNumPrimary")));
			user.setUserName(String.valueOf(data.get("userName")));
			user.setLevelName(level);
			user.setToken(token);
			user.setLastAccess(new Date());
			user.setStatus(String.valueOf(data.get("status")));
			user.setUpdatedAt(new Date());
			user.setLoginStatus(1);
			UserEntity user1 = (UserEntity)authRepository.save(user);
			if(user1 == null || user1.getUserId() == 0) {
				throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "User not saved");
			}
			
			if(!level.equals("super_admin") && !level.equals("national")) {
				List<LocationEntity> locationList = authRepository.findUserLocations(userId);	
				LocationEntity le = null;
				if(locationList != null && locationList.size() != 0) {
					le = locationList.get(0);
					le.setId(le.getId());
					le.setLevel(level);
					le.setLocationCode(userInfo.get("locationCode").toString());
					le.setStatus(1);
					le.setUpdatedAt(new Date());
					le.setUserId(userId);
					
				}
				else {
					le = new LocationEntity();
					le.setLevel(level);
					le.setLocationCode(userInfo.get("locationCode").toString());
					le.setStatus(1);
					le.setCreatedAt(new Date());
					le.setUpdatedAt(new Date());
					le.setUserId(userId);
				}
				Long locId = authRepository.saveUpdateUserLocation(le);
				if(locId == null || locId == 0) {
					throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "User Locations Not Saved");
				}
			}
			
			/*
			userInfo.put("loginId", "HP1234");
			userInfo.put("userName", "Hunny Pal");
			userInfo.put("mobNumPrimary", "9968421536");
			userInfo.put("userLevel", "national");
			resp.setUserInfo(userInfo);
			String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjIzOTkiLCJ1c2VyTGV2ZWwiOiJuYXRpb25hbCIsInVzZXJMb2NhdGlvbnMiOlsiMTcxMDAwMSJdLCJpYXQiOjE2NDcyNzAxNDMsImF1dGhvcml0aWVzIjpbIi9tY2xmL21hc3Rlci9ibG9ja2xpc3QiLCIvbWNsZi9hdXRoL3ZhbGlkYXRlVG9rZW4iLCIvbWFzdGVyL2Jsb2NrbGlzdCIsIi9tY2xmL21hc3Rlci9ncGxpc3QiLCIvbWFzdGVyL2dwbGlzdCIsIi9tY2xmL21hc3Rlci9jbGZsaXN0IiwiL21hc3Rlci9jbGZsaXN0IiwiL21jbGYvbWFzdGVyL3ZvbGlzdCIsIi9tYXN0ZXIvdm9saXN0IiwiL21jbGYvbWFzdGVyL2ZuY2x5ZWFybGlzdCIsIi9tY2xmL21hc3Rlci9tb250aGxpc3QiLCIvbWNsZi9tYXN0ZXIvYW5zd2Vyb3B0aW9uIiwiL21jbGYvbWFzdGVyL3N0YXRlZHJvcGRvd25saXN0IiwiL21jbGYvbWFzdGVyL2Rpc3RyaWN0ZHJvcGRvd25saXN0IiwiL21jbGYvbWFzdGVyL2Jsb2NrZHJvcGRvd25saXN0IiwiL21jbGYvbWFzdGVyL2dwZHJvcGRvd25saXN0IiwiL21jbGYvbWFzdGVyL2NsZmRyb3Bkb3dubGlzdCIsIi9tY2xmL2NsZmJhc2ljL3NhdmUiLCIvbWNsZi9jbGZiYXNpYy9wcm9maWxlbGlzdCIsIi9tY2xmL2NsZmJhc2ljL3VwZGF0ZSIsIi9tY2xmL2NsZmJhc2ljL2RlbGV0ZSIsIi9tY2xmL2N1dG9mZi9saXN0IiwiL21jbGYvY3V0b2ZmL2Zvcm0iLCIvbWNsZi9jdXRvZmYvc2F2ZSIsIi9tY2xmIiwiL21jbGYvY2xmIiwiL21jbGYvaW5kaWNhdG9yL3NhdmUiLCIvbWNsZi9pbmRpY2F0b3IvdHlwZWxpc3QiLCIvbWNsZi9pbmRpY2F0b3IvaW5kaWNhdG9ybGlzdCIsIi9tY2xmL2luZGljYXRvci91cGRhdGUiLCIvbWNsZi9pbmRpY2F0b3IvZGVsZXRlIiwiL21jbGYvc2VjdGlvbi8iLCIvbWNsZi9zZWN0aW9uL2NyZWF0ZSIsIi9tY2xmL3NlY3Rpb24vZGVsZXRlIiwiL21jbGYvc2VjdGlvbi91cGRhdGUiXX0.HhdnTckRqt_SOolHZTgR4KwWcSbCHv7j60zG7PK5I8DJn1KITCxxDsvqjwhz0aBqdiqql6BcPQEug5cG6gZ5lg";
			permissions = getModulePermissions("national");
			*/
			
			resp.setToken(token);
			resp.setResponseCode(HttpStatus.OK.value());
			resp.setResponseDesc(HttpStatus.OK.name());
			resp.setPermissions(permissions);
			
			loginMsg = "Success";
			loginStatus = 1;
			
			saveloggedhistory(request,loginMsg,loginStatus,loginrequest.get("loginId"));
			return resp;
			
		} 
		catch (HandledException e) {
			//e.printStackTrace();
			
			loginMsg = e.getMessage();
			loginStatus = 0;
			saveloggedhistory(request,loginMsg,loginStatus,loginrequest.get("loginId"));
			throw new HandledException(e.getCode(), e.getMessage());
		} 
		catch (HttpClientErrorException e) {
			//e.printStackTrace();
			loginMsg = e.getMessage();
			loginStatus = 0;
			saveloggedhistory(request,loginMsg,loginStatus,loginrequest.get("loginId"));
			throw new HandledException(HttpStatus.UNAUTHORIZED, "Unauthorized User");
		} 
		catch (Exception e) {
			e.printStackTrace();
			//saveloggedhistory(request,loginMsg,loginStatus);
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}


	private void saveloggedhistory(HttpServletRequest request, String loginMsg, Integer loginStatus,String loginId) {
		LoginHistoryEntity lhe = new LoginHistoryEntity();
		lhe.setLoginId(loginId);
		lhe.setStatus(loginStatus);
		lhe.setMessage(loginMsg);
		lhe.setClientAddress(request.getRemoteAddr());
		lhe.setClientDevice(request.getHeader("User-Agent"));
		lhe.setLoggedTime(new Date());
		loginHistoryRepository.save(lhe);
		
	}

	@Override
	public boolean isUserAuthenticated(Long userId, String token) {
		try {
		        Optional<UserEntity> optionalUser = authRepository.findByUserId(userId);
    	        if(optionalUser.isPresent()) {
    	       		UserEntity user = optionalUser.get();
    	       		if(user.getLoginStatus() != null && user.getLoginStatus() != 0 && user.getLastAccess() != null) {
    					if(user.getToken().equals(token)) {
    							Long lastTimeDiff = System.currentTimeMillis() - user.getLastAccess().getTime();
        						if(lastTimeDiff > (MyConstant.LAST_ACCESS_LIMIT_SECONDS*1000)) {
            						throw new HandledException(HttpStatus.UNAUTHORIZED, "Session Expired. Login again to continue");
            					}
            	       			return true;	
        	       		}
    					else {
    						throw new HandledException(HttpStatus.UNAUTHORIZED, MyConstant.UNAUTHORIZED_TOKEN_MSG);
    					}
    				}
    	       		else {
    		        	throw new HandledException(HttpStatus.UNAUTHORIZED, MyConstant.UNAUTHORIZED_TOKEN_MSG);
    		        }
    	       	}
    	        else {
    	        	throw new HandledException(HttpStatus.UNAUTHORIZED, MyConstant.UNAUTHORIZED_TOKEN_MSG);
    	        }
    	}
     		
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
	}

	@Override
	public boolean isUserAuthorized(String level, String uri) {
		try {
			Long authFlag = authRepository.checkUserAuthoritiesByLevelAndUri(level, uri);
			if(authFlag != 0) {
				return true;
			}
			else {
				throw new HandledException(HttpStatus.FORBIDDEN, "Unauthorized User");
			}
			
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	}

	@Override
	public void updateUserLastAccess(Long userId) {
		try {
			authRepository.updateUserLastAccess(userId);
			
		}
		
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	}
	
	@Override
	public void updateUserLastAccessAndToken(Long userId,String token) {
		try {
			authRepository.updateUserLastAccessAndToken(userId,token);
			
		}
		
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	}

	@Override
	public Response<String> processLogout(Long userId,String token) {
		Response<String> response = new Response<String>();
		try {
			Optional<UserEntity> optionalUser = authRepository.findByUserId(userId);
			if(optionalUser.isPresent()) {
				UserEntity user = optionalUser.get();
				if(user.getToken() !=  null && user.getToken().equals(token)) {
					user.setLoginStatus(0);
					authRepository.save(user);		
				}
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
			}
			else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY,"User not found");
			}
			return response;
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
	}

	@Override
	public Response<Map<String, Long>> getUserSessionTimer(Long userId,String token) {
		Response<Map<String, Long>> response = new Response<Map<String, Long>>();
		List<Map<String,Long>> wrapperList = new ArrayList<Map<String,Long>>();
		Map<String,Long> wrapperObj = new HashMap<String,Long>();
		try {
			Optional<UserEntity> optionalUser = authRepository.findByUserId(userId);
			if(optionalUser.isPresent() && optionalUser.get().getLoginStatus() != 0) {
				if(optionalUser.get().getToken() != null && optionalUser.get().getToken().equals(token)) {
					Long lastTimeDiff = System.currentTimeMillis() - optionalUser.get().getLastAccess().getTime();
					if(lastTimeDiff > (MyConstant.LAST_ACCESS_LIMIT_SECONDS*1000)) {
						throw new HandledException(HttpStatus.UNAUTHORIZED, "Session Expired. Login again to continue");
					}
					wrapperObj.put("timer", (optionalUser.get().getLastAccess().getTime()/1000));
				}
				else {
					throw new HandledException(HttpStatus.UNAUTHORIZED, "Unauthorized token");
				}
				
			}
			else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY,"User Not Found/User Already Logged Out");
			}
			wrapperList.add(wrapperObj);
			response.setWrapperList(wrapperList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(),e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
	}
}
