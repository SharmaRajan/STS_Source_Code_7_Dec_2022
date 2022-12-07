package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.customEntity.ClfMasterEntity;

public interface ClfRepositoryCustom {

	List<ClfMasterEntity> findByClfcode(String clfcode);
}
