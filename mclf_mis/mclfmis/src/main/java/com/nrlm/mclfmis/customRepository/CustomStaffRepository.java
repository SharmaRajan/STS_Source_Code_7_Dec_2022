package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.StaffEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.StaffFilterRequest;
import com.nrlm.mclfmis.customEntity.StaffCustomEntity;

public interface CustomStaffRepository {

	List<StaffCustomEntity> getStaff(StaffFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel);
	
	Long getStaffCount(StaffFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);

	StaffEntity getStaffDetailsById(Long id);

	StaffEntity getStaffDetailsByNameAndPhone(String name, String phone);
}
