package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.InternalControlEntity;
import com.nrlm.mclfmis.Entity.StatutoryEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.customEntity.StatutoryCustomEntity;

public interface CustomStatutoryRepository {

	//public StatutoryEntity getStatutoryByClfCodeAndFinYearId(Object object, String clfCode, Integer finYearId);

	public StatutoryEntity getStatutoryByIdAndClfCodeAndFinYearId(Long id,String clfCode, Integer finYearId);

	public List<StatutoryCustomEntity> getStatutoryList(ComplianceFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel);

	public Long getStatutoryCount(ComplianceFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);

	public StatutoryEntity getLatestStatutoryByClfCode(String clfCode);

	public StatutoryEntity getLastSubmitStatutoryByClfCode(String clfCode);

	

}
