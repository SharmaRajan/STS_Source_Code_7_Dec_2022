package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.CmtcEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.TrainingFilterRequest;
import com.nrlm.mclfmis.customEntity.CmtcCustomEntity;

public interface CustomCmtcRepository {

	List<CmtcCustomEntity> getCmtc(TrainingFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	
	Long getCmtcCount(TrainingFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	CmtcEntity getCmtcClfCode(Long id, String clfCode);
	CmtcEntity getCmtcById(Long id);
	
}
