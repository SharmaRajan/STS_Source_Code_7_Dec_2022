package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Entity.LocationMapper;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.LocationMapperRepository;
import com.nrlm.mclfmis.usermanagement.entity.LocationEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationMapperRepository locationRepository;

	@Override
	public List<String> getLocations(Long userId, String level) {
		List<String> locationids = new ArrayList<String>();
		if (!level.equals(ProjectConstants.SUPER_ADMIN) && !level.equals(ProjectConstants.NATIONAL)) {
			List<LocationEntity> locations = locationRepository.findAllByUserIdAndLevel(userId, level);
			if (locations != null & locations.size() != 0) {
				locationids = locations.stream().map(loc -> loc.getLocationCode()).collect(Collectors.toList());
			}
		}
		return locationids;
	}

}
