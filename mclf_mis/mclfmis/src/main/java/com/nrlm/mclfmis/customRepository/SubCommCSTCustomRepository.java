package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.SubCommCSTEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.SubCommCSTFilterRequest;
import com.nrlm.mclfmis.customEntity.SubCommCSTCustomEntity;

public interface SubCommCSTCustomRepository {

	List<SubCommCSTCustomEntity> getCSTList(SubCommCSTFilterRequest filterdto, List<String> locations,
			String levelName, PageModel pageModel);
	Long getCSTCount(SubCommCSTFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	SubCommCSTEntity getCSTByClfCode(Long id, String clfCode);
}
