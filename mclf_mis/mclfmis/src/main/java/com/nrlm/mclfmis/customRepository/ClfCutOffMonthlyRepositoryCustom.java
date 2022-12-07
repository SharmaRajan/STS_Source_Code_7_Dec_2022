package com.nrlm.mclfmis.customRepository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.ClfCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.GpCutOffMonthlyEntity;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Entity.VoCutOffMonthlyEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Request.CutOffFilterRequest;
import com.nrlm.mclfmis.customEntity.ClfCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.GpCustomCutOffEntity;
import com.nrlm.mclfmis.customEntity.IndicatorCustomEntity;
import com.nrlm.mclfmis.customEntity.VoCustomCutOffEntity;

public interface ClfCutOffMonthlyRepositoryCustom {

	ClfCutOffMonthlyEntity checkCutOffExistByClfCodeWithMonthYear(String clfCode, Integer monthId, Integer yearId);

	VoCutOffMonthlyEntity checkCutOffExistByVoCodeWithMonthYear(String clfCode, Integer monthId, Integer yearId);

	GpCutOffMonthlyEntity checkCutOffExistByGpCodeWithMonthYear(String clfCode, Integer monthId, Integer yearId);

	List<IndicatorCustomEntity> getIndicatorsBySectionId(Long sectionId, Integer tabId, Integer cutOffId);

	List<IndicatorCustomEntity> getIndicatorsValueBySectionId(Long sectionId, Integer tabId,
			HttpServletRequest request);

	List<SectionEntity> getSectionByTabId(Integer tabId);

	List<AnswerOptionEntity> getIndicatorOptions(String tableName, String shortKey);

	List<VoCustomCutOffEntity> getVoCutOffStatus(CutOffFilterRequest filterdto);

	List<GpCustomCutOffEntity> getGpCutOffStatus(CutOffFilterRequest filterdto);

	Page<ClfCustomCutOffEntity> getClfCutOff(CutOffFilterRequest filterdto, List<String> locations, String levelId,
			PageModel pageModel, Pageable pag);

	Page<VoCustomCutOffEntity> getVoCutOff(CutOffFilterRequest filterdto, List<String> locations, String levelId,
			PageModel pageModel, Pageable pag);

	Page<GpCustomCutOffEntity> getGpCutOff(CutOffFilterRequest filterdto, List<String> locations, String levelId,
			PageModel pageModel, Pageable pag);
	
	public ClfCutOffMonthlyEntity getLatestMonthlyClfCutOff(String clfCode);
	public VoCutOffMonthlyEntity getLatestVoCutOff(String voCode);
	public GpCutOffMonthlyEntity getLatestGpCutOff(String gpCode);
	
	public Integer getCutOffSectionStatusByCutOffIdAndSectionId(Integer tabId, Long cutOffId, Long sectionId);
	
	public List<String> getClfVoMapping(String clfCode);
	
	public List<String> getClfGpMapping(String clfCode);

}
