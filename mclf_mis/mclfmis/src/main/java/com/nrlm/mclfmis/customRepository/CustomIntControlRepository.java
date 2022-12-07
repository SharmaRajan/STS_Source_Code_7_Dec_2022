package com.nrlm.mclfmis.customRepository;

import java.util.List;
import com.nrlm.mclfmis.Entity.InternalControlEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.customEntity.IntControlCustomEntity;

public interface CustomIntControlRepository {
	List<IntControlCustomEntity> getIntControl(ComplianceFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel);
	Long getIntControlCount(ComplianceFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	//InternalControlEntity getIntControlByClfCodeAndFinYearIdAndQrtrId(Long id, String clfCode);
	InternalControlEntity getIntControlByClfCodeAndFinYearIdAndQrtrId(Long id,String clfCode,Integer yearId, Integer qrtrId);
	//InternalControlEntity getIntControlByClfCodeAndFinYearIdAndQrtrIdAndIdNotIn(String clfCode, Integer finYearId,
	//		Integer qrtrId, Long id);
	
	public InternalControlEntity getLatestIntControlByClfCode(String clfCode);
}
