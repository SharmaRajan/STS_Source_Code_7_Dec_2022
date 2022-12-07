package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.ClfPoliciesEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.customEntity.ClfPoliCustomEntity;

public interface CustomPoliciesRepository {

	List<ClfPoliCustomEntity> getClfPolicies(ComplianceFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel);
	Long getClfPoliciesCount(ComplianceFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	ClfPoliciesEntity getClfPoliciesByClfCode(String clfCode);
	

}
