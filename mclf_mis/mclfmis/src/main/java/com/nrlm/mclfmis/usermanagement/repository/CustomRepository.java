package com.nrlm.mclfmis.usermanagement.repository;

import java.util.List;

import com.nrlm.mclfmis.usermanagement.entity.LocationEntity;

public interface CustomRepository {

	List<Object[]> getUserAuthorities(String level);
	
	List<Object[]> getModulePermissions(String level);
	
	Long checkUserAuthoritiesByLevelAndUri(String level,String uri);
	
	List<LocationEntity> findUserLocations(Long userId);
	

	Long saveUpdateUserLocation(LocationEntity le);

	List<Object[]> getUserLocationDetails(String level, String locationCode);
	
	void updateUserLastAccess(Long userId);
	
	void updateUserLastAccessAndToken(Long userId,String token);
	
	//int updateUserLoginStatus(Long userId);

}
