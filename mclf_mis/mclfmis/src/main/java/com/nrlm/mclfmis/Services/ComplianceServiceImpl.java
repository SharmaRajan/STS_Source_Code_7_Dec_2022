package com.nrlm.mclfmis.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.nrlm.mclfmis.Entity.ClfPoliciesEntity;
import com.nrlm.mclfmis.Entity.InternalControlEntity;
import com.nrlm.mclfmis.Entity.StatutoryEntity;
import com.nrlm.mclfmis.Entity.StatutoryLicenseEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfPoliciesRepository;
import com.nrlm.mclfmis.Repository.ClfRepository;
import com.nrlm.mclfmis.Repository.InternalControlRepository;
import com.nrlm.mclfmis.Repository.StatutoryLicenseRepository;
import com.nrlm.mclfmis.Repository.StatutoryRepository;
import com.nrlm.mclfmis.Request.ClfPoliciesRequest;
import com.nrlm.mclfmis.Request.ComplianceFilterRequest;
import com.nrlm.mclfmis.Request.InternalControlRequest;
import com.nrlm.mclfmis.Request.StatutoryLicenseRequest;
import com.nrlm.mclfmis.Request.StatutoryRequest;
import com.nrlm.mclfmis.Response.ClfPoliciesResponse;
import com.nrlm.mclfmis.Response.FinancialYearResponse;
import com.nrlm.mclfmis.Response.InternalControlResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.StatutoryLicenseResponse;
import com.nrlm.mclfmis.Response.StatutoryResponse;
import com.nrlm.mclfmis.Response.VoCutOffResponse;
import com.nrlm.mclfmis.Specification.ClfpoliciesSpecification;
import com.nrlm.mclfmis.customEntity.ClfMasterEntity;
import com.nrlm.mclfmis.customEntity.ClfPoliCustomEntity;
import com.nrlm.mclfmis.customEntity.IntControlCustomEntity;
import com.nrlm.mclfmis.customEntity.StatutoryCustomEntity;
import com.nrlm.mclfmis.customRepository.CustomIntControlRepository;
import com.nrlm.mclfmis.customRepository.CustomPoliciesRepository;
import com.nrlm.mclfmis.customRepository.CustomStatutoryRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@Service
public class ComplianceServiceImpl implements ComplianceService {

	@Autowired
	private ClfPoliciesRepository clfPoliciesRepository;

	@Autowired
	private PageModel pageModel;

	@Autowired
	ClfpoliciesSpecification clfpoliciesSpecification;

	@Autowired
	LocationService locationService;

	@Autowired
	private CustomPoliciesRepository customPoliciesRepository;

	@Autowired
	private InternalControlRepository intControlRepository;

	@Autowired
	private CustomIntControlRepository customIntCtrllRepository;

	@Autowired
	private ClfRepository clfRepository;

	@Autowired
	private CustomStatutoryRepository customStatutoryRepository;

	@Autowired
	private StatutoryRepository statutoryRepository;
	@Autowired
	private StatutoryLicenseRepository statutoryLicenseRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response<String> createClfPolicies(Long userId, ClfPoliciesRequest request) {
		Response<String> response = new Response<String>();

		try {

			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(request.getClfCode());
			if (clfList == null || clfList.size() == 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
			}

			ClfPoliciesEntity se = customPoliciesRepository.getClfPoliciesByClfCode(request.getClfCode());
			
			ClfPoliciesEntity clfPolicies  = null;
			
			if(request.getId() != null && request.getId() != 0) { //update
				
				if(se == null) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data not found");
				}
				else if(se.getId() != se.getId()) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "CLF Policies Already Exist");
				}
				else if(se.getComplianceStatus() != null && se.getComplianceStatus() == ProjectConstants.COMPLETED) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "CLF Policies Already Submitted");
				}
				else {
					clfPolicies =  se;
				}
			}
			else {
				
				if (se != null) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "CLF Policies Already Exist");
				}
				
				clfPolicies = new ClfPoliciesEntity();
				clfPolicies.setStatus(ProjectConstants.ACTIVE_STATUS);
				clfPolicies.setCreatedAt(new Date());
				clfPolicies.setCreatedBy(userId);
			}
			
			clfPolicies.setUpdatedAt(new Date());
			clfPolicies.setUpdatedBy(userId);
			clfPolicies.setClfCode(request.getClfCode());
			clfPolicies.setGovPolicies(request.getGovPolicies());
			clfPolicies.setCboHrPolicies(request.getCboHrPolicies());
			clfPolicies.setFinPolicies(request.getFinPolicies());
			
			if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
				clfPolicies.setComplianceStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				clfPolicies.setComplianceStatus(ProjectConstants.COMPLETED);
			}

			clfPoliciesRepository.save(clfPolicies);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Clf Policies Not Created");
		}

	}
//
//	@Override
//	public Response<String> updateClfPolicies(Long userId, ClfPoliciesRequest request) {
//		Response<String> response = new Response<String>();
//		try {
//			Optional<ClfPoliciesEntity> optionalClf = clfPoliciesRepository.findById(request.getId());
//			if (optionalClf.isPresent()) {
//				ClfPoliciesEntity clfEntity = optionalClf.get();
//
//				/*
//				 * if (!clfEntity.getClfCode().equals(request.getClfCode())) { throw new
//				 * HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Clf Code not found"); }
//				 */
//
//				clfEntity.setUpdatedAt(new Date());
//				clfEntity.setUpdatedBy(userId);
//				clfEntity.setCboHrPolicies(request.getCboHrPolicies());
//				// clfEntity.setClfCode(request.getClfCode());
//				clfEntity.setFinPolicies(request.getFinPolicies());
//				clfEntity.setGovPolicies(request.getGovPolicies());
//
//				clfPoliciesRepository.save(clfEntity);
//				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
//				response.setResponseDesc(HttpStatus.OK.name());
//				return response;
//
//			} else {
//				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No ClfPolicies Found with given value");
//			}
//		} catch (HandledException e) {
//			throw new HandledException(e.getCode(), e.getMessage());
//		} catch (Exception e) {
//			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "ClfPolicies Not Updated");
//		}
//	}

	@Override
	public Response<ClfPoliciesResponse> getPoliciesList(ComplianceFilterRequest filterdto) {

		Response<ClfPoliciesResponse> response = new Response<ClfPoliciesResponse>();
		List<ClfPoliciesResponse> wrappedList = new ArrayList<ClfPoliciesResponse>();
//		 PageModel.setSIZE(20);
//		 pageModel.initPageAndSize();

		Long totalCount = Long.valueOf(0);
		String errorMsg = null;
		pageModel.initOffsetAndLimit();

		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelName = "";

			if (authUser != null && authUser.getUserId() != null && authUser.getLevelName() != null) {
				userId = authUser.getUserId();
				levelName = authUser.getLevelName();

			}
			List<String> locations = new ArrayList<String>();
			if (userId == 0 || levelName.isEmpty()) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelName);

			List<ClfPoliCustomEntity> policiesList = customPoliciesRepository.getClfPolicies(filterdto, locations,
					levelName, pageModel);

			wrappedList = policiesList.stream().map(policy -> 
			{
				return new ClfPoliciesResponse(policy.getId(),policy.getStateCode(),policy.getStateName(),
						policy.getDistrictCode(),policy.getDistrictName(),policy.getBlockCode(),policy.getBlockName(),
						policy.getClfCode(),policy.getClfName(),policy.getCboHrPolicies(),policy.getGovPolicies(),
						policy.getFinPolicies(),policy.getComplianceStatus());
			
			}).collect(Collectors.toList());

			response.setWrapperList(wrappedList);

			response.setTotalcount(
					customPoliciesRepository.getClfPoliciesCount(filterdto, locations, levelName, pageModel));
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}

	@Override
	public Response<String> createIntControl(Long userId, InternalControlRequest request) {
		Response<String> response = new Response<String>();

		try {

			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(request.getClfCode());
			if (clfList == null || clfList.size() == 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
			}

			InternalControlEntity intctrl = customIntCtrllRepository.getIntControlByClfCodeAndFinYearIdAndQrtrId(null,
					request.getClfCode(), request.getFinYearId(), request.getQrtrId());
			
			InternalControlEntity latestData = customIntCtrllRepository.getLatestIntControlByClfCode(request.getClfCode());	
			
			InternalControlEntity obj = null;
			
			if (latestData != null) {
				if (latestData.getComplianceStatus() == ProjectConstants.COMPLETED) {
					
					if(latestData.getQrtrId() == 4) {
						if(request.getFinYearId() != (latestData.getFinYearId()+1) && request.getQrtrId() != 1) {
							throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Year and Qrtr");
						}
					}
					else {
						if(request.getFinYearId() != latestData.getFinYearId() || request.getQrtrId() <= latestData.getQrtrId()) {
							throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Year and Qrtr");
						}
					}

				
				} else if (latestData.getComplianceStatus() == ProjectConstants.IN_PROGRESS) {
					
					if(request.getFinYearId() != latestData.getFinYearId() || request.getQrtrId() != latestData.getQrtrId()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Year and Qrtr");
					}
				}

			}
			
			if(request.getId() != null && request.getId() != 0) { //update
				Optional<InternalControlEntity> intctrloptional = intControlRepository.findById(request.getId());
				if(intctrloptional.isPresent()) {
					
					if(intctrloptional.get().getComplianceStatus() != null && intctrloptional.get().getComplianceStatus() == ProjectConstants.COMPLETED) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data already submitted");
					}
					
					else if(intctrl != null && intctrl.getId() != intctrloptional.get().getId()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data already exist");
					}
					else {
						obj = intctrloptional.get();
					}
				}
				else {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data not found");
				}
				
			}
			else { // insert
				if (intctrl != null) {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Internal Control Already Exist");
				}
				
				obj = new InternalControlEntity();
				obj.setStatus(ProjectConstants.ACTIVE_STATUS);
				obj.setCreatedAt(new Date());
				obj.setCreatedBy(userId);
			}
			
			
			obj.setUpdatedAt(new Date());
			
			obj.setUpdatedBy(userId);
			
			obj.setClfCode(request.getClfCode());
			obj.setFinYearId(request.getFinYearId());
			obj.setGbMeeting(request.getGbMeeting());
			obj.setGrb(request.getGrb());
			obj.setIntAudit(request.getIntAudit());
			obj.setQrtrId(request.getQrtrId());
			
			if(request.getIsDraft() == ProjectConstants.IS_DRAFT) {
				obj.setComplianceStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				obj.setComplianceStatus(ProjectConstants.COMPLETED);
			}

			intControlRepository.save(obj);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Control Not Created");
		}
	}

//	@Override
//	public Response<String> updateIntControl(Long userId, InternalControlRequest request) {
//		Response<String> response = new Response<String>();
//		try {
//			Optional<InternalControlEntity> optionalClf = intControlRepository.findById(request.getId());
//			if (optionalClf.isPresent()) {
//				InternalControlEntity clfEntity = optionalClf.get();
//
//				InternalControlEntity se = customIntCtrllRepository.getIntControlByClfCodeAndFinYearIdAndQrtrId(
//						request.getId(), request.getClfCode(), request.getFinYearId(), request.getQrtrId());
//				if (se != null && se.getId() != request.getId()) {
//					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Internal Control Already Exist");
//				}
//
//				clfEntity.setUpdatedAt(new Date());
//				clfEntity.setUpdatedBy(userId);
//				// clfEntity.setClfCode(request.getClfCode());
//				clfEntity.setFinYearId(request.getFinYearId());
//				clfEntity.setGbMeeting(request.getGbMeeting());
//				clfEntity.setGrb(request.getGrb());
//				clfEntity.setIntAudit(request.getIntAudit());
//				clfEntity.setQrtrId(request.getQrtrId());
//				clfEntity.setStatus(ProjectConstants.ACTIVE_STATUS);
//
//				intControlRepository.save(clfEntity);
//				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
//				response.setResponseDesc(HttpStatus.OK.name());
//				return response;
//
//			} else {
//				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY,
//						"No Internal Control Found with given value");
//			}
//		} catch (HandledException e) {
//			throw new HandledException(e.getCode(), e.getMessage());
//		} catch (Exception e) {
//			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Control Not Updated");
//		}
//	}

	@Override
	public Response<InternalControlResponse> getIntControlList(ComplianceFilterRequest filterdto) {

		Response<InternalControlResponse> response = new Response<InternalControlResponse>();
		List<InternalControlResponse> wrappedList = new ArrayList<InternalControlResponse>();

		pageModel.initOffsetAndLimit();

		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelName = "";

			if (authUser != null && authUser.getUserId() != null && authUser.getLevelName() != null) {
				userId = authUser.getUserId();
				levelName = authUser.getLevelName();

			}
			List<String> locations = new ArrayList<String>();
			if (userId == 0 || levelName.isEmpty()) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelName);

			List<IntControlCustomEntity> controlList = customIntCtrllRepository.getIntControl(filterdto, locations,
					levelName, pageModel);

			wrappedList = controlList.stream().map(ctrl -> {

				return new InternalControlResponse(ctrl.getId(), ctrl.getStateCode(), ctrl.getStateName(),
						ctrl.getDistrictCode(), ctrl.getDistrictName(), ctrl.getBlockCode(), ctrl.getBlockName(),
						ctrl.getClfCode(), ctrl.getClfName(), ctrl.getFinYearId(), ctrl.getQrtrId(), ctrl.getIntAudit(),
						ctrl.getGrb(), ctrl.getGbMeeting(), ctrl.getComplianceStatus(),ctrl.getFinYear(),ctrl.getQrtr());
			}).collect(Collectors.toList());

			response.setWrapperList(wrappedList);

			response.setTotalcount(
					customIntCtrllRepository.getIntControlCount(filterdto, locations, levelName, pageModel));
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public Response<String> createStatutory(Long userId, StatutoryRequest req) {
		Response<String> response = new Response<String>();

		try {

			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(req.getClfCode());
			if (clfList == null || clfList.size() == 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
			}

			StatutoryEntity latestData = customStatutoryRepository.getLatestStatutoryByClfCode(req.getClfCode());
			if (latestData != null) {
				if (latestData.getComplianceStatus() == ProjectConstants.COMPLETED) {

					if (req.getFinYearId() - latestData.getFinYearId() > 1) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY,
								"Data is Pending for Previous Year");
					} else if (req.getFinYearId() - latestData.getFinYearId() < 0) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data is already submitted");
					}
				} else if (latestData.getComplianceStatus() == ProjectConstants.IN_PROGRESS) {

					if (req.getFinYearId() - latestData.getFinYearId() > 0) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY,
								"Data is Pending for Previous Year");
					} else if (req.getFinYearId() - latestData.getFinYearId() < 0) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data is already submitted");
					}
				}

			}

			StatutoryEntity se = customStatutoryRepository.getStatutoryByIdAndClfCodeAndFinYearId(req.getId(),
					req.getClfCode(), req.getFinYearId());

			StatutoryEntity obj = null;

			if (req.getId() != null && req.getId() != 0) { // update case

				if (se != null) {

					if (se.getId() != req.getId()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Exist");
					} else if (se.getComplianceStatus() == ProjectConstants.COMPLETED) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
					} else {

						// if(req.getFinYearId() >)

						obj = se;

						obj.setUpdatedAt(new Date());
						obj.setUpdatedBy(userId);

					}
				} else {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Not Found");
				}
			} else { // insert case

				if (se != null) {

					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Exist");
				} else {

					obj = new StatutoryEntity();

					obj.setStatus(ProjectConstants.ACTIVE_STATUS);
					obj.setCreatedAt(new Date());
					obj.setUpdatedAt(new Date());
					obj.setCreatedBy(userId);
					obj.setUpdatedBy(userId);

				}

			}

			obj.setClfCode(req.getClfCode());
			obj.setFinYearId(req.getFinYearId());
			obj.setIsExtAudit(req.getIsExtAudit());
			obj.setLastExtAuditDate(req.getLastExtAuditDate());
			obj.setIsAgbmConduct(req.getIsAgbmConduct());
			obj.setLastAgbmConductDate(req.getLastAgbmConductDate());
			obj.setLastAgbmParticipant(req.getLastAgbmParticipant());
			obj.setIsLdrRotationDue(req.getIsLdrRotationDue());
			obj.setLastLdrRotationDate(req.getLastLdrRotationDate());
			obj.setIsItReturns(req.getIsItReturns());
			obj.setItReturnsFinYearId(req.getItReturnsFinYearId());
			obj.setLastItReturnsDate(req.getLastItReturnsDate());
			obj.setIsAnnualReturns(req.getIsAnnualReturns());
			obj.setAnnualReturnsFinYearId(req.getAnnualReturnsFinYearId());
			obj.setAnnualReturnsDate(req.getAnnualReturnsDate());
			obj.setIsInputLicense(req.getIsInputLicense());
			obj.setIsRating(req.getIsRating());
			obj.setRatingAgency(req.getRatingAgency());
			obj.setLastRatingDate(req.getLastRatingDate());
			obj.setGovRating(req.getGovRating());
			obj.setFinPerformRating(req.getFinPerformRating());
			obj.setMgmtRating(req.getMgmtRating());
			obj.setOverallRating(req.getOverallRating());

			if (req.getIsDraft() == ProjectConstants.IS_DRAFT) {
				obj.setComplianceStatus(ProjectConstants.IN_PROGRESS);
			} else {

				obj.setComplianceStatus(ProjectConstants.COMPLETED);
			}

			StatutoryEntity se1 = (StatutoryEntity) statutoryRepository.save(obj);

			if (se1.getId() != 0) {

				List<Integer> licSelectedList = new ArrayList<Integer>();

				if (req.getLicenseList() != null && req.getLicenseList().size() != 0) {

					for (StatutoryLicenseRequest lic : req.getLicenseList()) {
						licSelectedList.add(lic.getLicenseSelected());
						Optional<StatutoryLicenseEntity> optionalLicense = statutoryLicenseRepository
								.findByComplianceIdAndLicenseSelected(se1.getId(), lic.getLicenseSelected());
						StatutoryLicenseEntity stlic = null;
						if (optionalLicense.isPresent()) {
							stlic = optionalLicense.get();

						} else {
							stlic = new StatutoryLicenseEntity();
							stlic.setComplianceId(se1.getId());
						}

						stlic.setIsLicense(lic.getIsLicense());
						stlic.setIsLicenseRenewal(lic.getIsLicenseRenewal());
						stlic.setLicenseDate(lic.getLicenseDate());
						stlic.setLicenseRenewalMonth(lic.getLicenseRenewalMonth());
						stlic.setLicenseSelected(lic.getLicenseSelected());

						statutoryLicenseRepository.save(stlic);

					}

					statutoryLicenseRepository.deleteAllByComplianceIdAndLicenseSelectedNotIn(se1.getId(),
							licSelectedList);
				} else {
					statutoryLicenseRepository.deleteAllByComplianceId(se1.getId());
				}
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
			} else {
				throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Data Not saved");
			}

			return response;

		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}

	@Override
	public Response<StatutoryResponse> getStatutoryList(ComplianceFilterRequest filterdto) {

		Response<StatutoryResponse> response = new Response<StatutoryResponse>();
		List<StatutoryResponse> wrappedList = new ArrayList<StatutoryResponse>();

		pageModel.initOffsetAndLimit();

		try {
			AuthenticatedUser authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			Long userId = 0L;
			String levelName = "";

			if (authUser != null && authUser.getUserId() != null && authUser.getLevelName() != null) {
				userId = authUser.getUserId();
				levelName = authUser.getLevelName();

			}
			List<String> locations = new ArrayList<String>();
			if (userId == 0 || levelName.isEmpty()) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No User Info Found");
			}
			locations = locationService.getLocations(userId, levelName);

			List<StatutoryCustomEntity> statList = customStatutoryRepository.getStatutoryList(filterdto, locations,
					levelName, pageModel);

			wrappedList = statList.stream().map(stat -> {
				return new StatutoryResponse(stat.getId(), stat.getStateCode(), stat.getStateName(),
						stat.getDistrictCode(), stat.getDistrictName(), stat.getBlockCode(), stat.getBlockName(),
						stat.getClfCode(), stat.getClfName(), stat.getFinYearId(), stat.getIsExtAudit(),
						stat.getLastExtAuditDate(), stat.getIsAgbmConduct(), stat.getLastAgbmConductDate(),
						stat.getLastAgbmParticipant(), stat.getIsLdrRotationDue(), stat.getLastLdrRotationDate(),
						stat.getIsItReturns(), stat.getItReturnsFinYearId(), stat.getLastItReturnsDate(),
						stat.getIsAnnualReturns(), stat.getAnnualReturnsFinYearId(), stat.getAnnualReturnsDate(),
						stat.getIsInputLicense(), stat.getIsRating(), stat.getRatingAgency(), stat.getLastRatingDate(),
						stat.getGovRating(), stat.getFinPerformRating(), stat.getMgmtRating(), stat.getOverallRating(),
						stat.getComplianceStatus(),stat.getFinYear());
			}).collect(Collectors.toList());

			if (filterdto.getId() != null && filterdto.getId() != 0) {
				if (wrappedList != null && wrappedList.size() == 1
						&& wrappedList.get(0).getIsInputLicense() == ProjectConstants.YES) {
					List<StatutoryLicenseEntity> statLicList = statutoryLicenseRepository
							.findAllByComplianceId(filterdto.getId());
					if (statLicList != null) {
						List<StatutoryLicenseResponse> liclist = new ArrayList<StatutoryLicenseResponse>();
						liclist = statLicList.stream().map(lic -> modelMapper.map(lic, StatutoryLicenseResponse.class))
								.collect(Collectors.toList());
						wrappedList.get(0).setLicenseList(liclist);
					}
				}
			}

			response.setWrapperList(wrappedList);

			response.setTotalcount(
					customStatutoryRepository.getStatutoryCount(filterdto, locations, levelName, pageModel));
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}

}
