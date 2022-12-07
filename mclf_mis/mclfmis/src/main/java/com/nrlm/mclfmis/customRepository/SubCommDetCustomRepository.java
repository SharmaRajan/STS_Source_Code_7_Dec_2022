package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.SubCommDetailsEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.SubCommDetFilterRequest;
import com.nrlm.mclfmis.customEntity.SubCommDetCustomEntity;

public interface SubCommDetCustomRepository {

	List<SubCommDetCustomEntity> getSubDetList(SubCommDetFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel);
	Long getSubDetCount(SubCommDetFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	SubCommDetailsEntity getSubDetByClfCode(Long id, String clfCode);
}

