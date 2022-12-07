package com.nrlm.mclfmis.customRepository;

import java.util.List;

import com.nrlm.mclfmis.Entity.StaffEntity;
import com.nrlm.mclfmis.Entity.TrainingEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.TrainingFilterRequest;
import com.nrlm.mclfmis.Request.TrainingRequest;
import com.nrlm.mclfmis.customEntity.TrainingCustomEntity;

public interface CustomTrainingRepository {
	
	List<TrainingCustomEntity> getTraining(TrainingFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	
	Long getTrainingCount(TrainingFilterRequest filterdto, List<String> locations, String levelName,
			PageModel pageModel);
	
	//TrainingEntity getTrainingByClfCode(Long id, String clfCode);
    
	TrainingEntity getTrainingById(Long id);

	TrainingEntity getTrainingByClfCodeAndDetails(TrainingRequest req);
}
