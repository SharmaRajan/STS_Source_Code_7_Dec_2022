package com.nrlm.mclfmis.Services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Entity.CmtcEntity;
import com.nrlm.mclfmis.Entity.TrainingEntity;
import com.nrlm.mclfmis.Entity.TrainingMapperEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.ClfRepository;
import com.nrlm.mclfmis.Repository.CmtcRepository;
import com.nrlm.mclfmis.Repository.TrainingRepository;
import com.nrlm.mclfmis.Request.CmtcRequest;
import com.nrlm.mclfmis.Request.TrainingFilterRequest;
import com.nrlm.mclfmis.Request.TrainingRequest;
import com.nrlm.mclfmis.Response.CmtcResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.TrainingResponse;
import com.nrlm.mclfmis.customEntity.ClfMasterEntity;
import com.nrlm.mclfmis.customEntity.CmtcCustomEntity;
import com.nrlm.mclfmis.customEntity.TrainingCustomEntity;
import com.nrlm.mclfmis.customRepository.CustomCmtcRepository;
import com.nrlm.mclfmis.customRepository.CustomTrainingRepository;
import com.nrlm.mclfmis.exception.HandledException;
import com.nrlm.mclfmis.usermanagement.common.AuthenticatedUser;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private CmtcRepository cmtcRepository;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private PageModel pageModel;

	@Autowired
	LocationService locationService;

	@Autowired
	private CustomCmtcRepository customCmtcRepository;

	@Autowired
	private CustomTrainingRepository customTrainingRepository;
	
	@Autowired
	private ClfRepository clfRepository;

	// create PostMapping

	public Response<String> createCmtc(Long userId, CmtcRequest req) {
		Response<String> response = new Response<String>();

		try {
			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(req.getClfCode());
			if (clfList == null || clfList.size() == 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
			}

			CmtcEntity cmtc = customCmtcRepository.getCmtcClfCode(req.getId(), req.getClfCode());
			
			CmtcEntity obj = null;
			
			if (req.getId() != null && req.getId() != 0) { // update case

				if (cmtc != null) {

					if (cmtc.getId() != req.getId()) {
						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Exist");
					} 
	//				else if (cmtc.getCmtcStatus() != null && cmtc.getCmtcStatus() == ProjectConstants.COMPLETED) {
//						throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
//					}
					else {

						obj = cmtc;

						obj.setUpdatedAt(new Date());
						obj.setUpdatedBy(userId);

					}
				} 
				else {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Not Found");
				}
			} 
			else { // insert case

				if (cmtc != null) {

					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Exist");
				} else {

					obj = new CmtcEntity();

					obj.setStatus(ProjectConstants.ACTIVE_STATUS);
					obj.setCreatedAt(new Date());
					obj.setUpdatedAt(new Date());
					obj.setCreatedBy(userId);
					obj.setUpdatedBy(userId);

				}

			}

		
			obj.setClfCode(req.getClfCode());
			obj.setCmtcEst(req.getCmtcEst());
			obj.setCmtcDate(req.getCmtcDate());
			obj.setCmtcFunc(req.getCmtcFunc());
		
			obj.setUpdatedBy(userId);
			obj.setUpdatedAt(new Date());

			if(req.getIsDraft() == ProjectConstants.IS_DRAFT) {
				obj.setCmtcStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				obj.setCmtcStatus(ProjectConstants.COMPLETED);
			}
			
			cmtcRepository.save(obj);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}

	// PutMapping
	/*
	public Response<String> updateCmtc(Long loginId, CmtcRequest cmtcreq) {

		Response<String> response = new Response<String>();
		try {

			Optional<CmtcEntity> optional = cmtcRepository.findById(cmtcreq.getId());

			if (optional.isPresent()) {
				CmtcEntity ent = optional.get();

				ent.setClfCode(cmtcreq.getClfCode());
				ent.setCmtcEst(cmtcreq.getCmtcEst());
				ent.setCmtcDate(cmtcreq.getCmtcDate());
				ent.setCmtcFunc(cmtcreq.getCmtcFunc());
				ent.setUpdatedBy(loginId);
				ent.setUpdatedAt(new Date());

				cmtcRepository.save(ent);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Cmtc Found with given value");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Cmtc Not Updated");
		}

	}
	*/

	// get all records from database....
	public Response<CmtcResponse> getCmtcList(TrainingFilterRequest filterDto) {

		Response<CmtcResponse> response = new Response<CmtcResponse>();
		List<CmtcResponse> wrappedList = new ArrayList<CmtcResponse>();

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

			List<CmtcCustomEntity> cmtcList = customCmtcRepository.getCmtc(filterDto, locations, levelName, pageModel);

			wrappedList = cmtcList.stream().map(cmtc -> {
				return new CmtcResponse(cmtc.getId(), cmtc.getStateCode(), cmtc.getStateName(), cmtc.getDistrictCode(),
						cmtc.getDistrictName(), cmtc.getBlockCode(), cmtc.getBlockName(), cmtc.getClfCode(),
						cmtc.getClfName(), cmtc.getCmtcEst(), cmtc.getCmtcDate(), cmtc.getCmtcFunc(), cmtc.getCmtcStatus());

			}).collect(Collectors.toList());

			response.setWrapperList(wrappedList);

			response.setTotalcount(customCmtcRepository.getCmtcCount(filterDto, locations, levelName, pageModel));
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

	// Create PostMapping....
	@Override
	public Response<String> createTraining(Long userId, TrainingRequest req) {
		
		Response<String> response = new Response<String>();

		try {
			
			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(req.getClfCode());
			if (clfList == null || clfList.size() == 0) {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Clf Exist");
			}

			TrainingEntity train = customTrainingRepository.getTrainingByClfCodeAndDetails(req);
			
			TrainingEntity obj = null;
			
			if (req.getId() != null && req.getId() != 0) { // update case
				TrainingEntity train1 = customTrainingRepository.getTrainingById(req.getId());
				
				if(train1 != null) {
					
					if (train != null) {

						if (train.getId() != req.getId()) {
							throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Exist");
							
						} 
//						else if (train.getTrainingStatus() != null && train.getTrainingStatus() == ProjectConstants.COMPLETED) {
//							throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Submitted");
//						}
						else {

							obj = train;

							obj.setUpdatedAt(new Date());
							obj.setUpdatedBy(userId);

						}
					} 
					else {
						obj = train1;

						obj.setUpdatedAt(new Date());
						obj.setUpdatedBy(userId);
					}
				}
				else {
					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Not Found");
				}
				
			} 
			else { // insert case

				if (train != null) {

					throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Data Already Exist");
				} else {

					obj = new TrainingEntity();

					obj.setStatus(ProjectConstants.ACTIVE_STATUS);
					obj.setCreatedAt(new Date());
					obj.setUpdatedAt(new Date());
					obj.setCreatedBy(userId);
					obj.setUpdatedBy(userId);

				}

			}


			obj.setClfCode(req.getClfCode());
			obj.setTrainingConduct(req.getTrainingConduct());
			//obj.setParticipantNo(req.getParticipantNo());
			obj.setStartDate(req.getStartDate());
			obj.setEndDate(req.getEndDate());
			obj.setUpdatedBy(userId);
			obj.setUpdatedAt(new Date());
			obj.setStatus(ProjectConstants.ACTIVE_STATUS);
			
			if(req.getIsDraft() == ProjectConstants.IS_DRAFT) {
				obj.setTrainingStatus(ProjectConstants.IN_PROGRESS);
			}
			else {
				obj.setTrainingStatus(ProjectConstants.COMPLETED);
			}
			List<TrainingMapperEntity> trnList = new ArrayList<TrainingMapperEntity>();
			 
			for(Integer x : req.getClfStaffType()) {
				TrainingMapperEntity clfstf = new TrainingMapperEntity(null,x,null);
				trnList.add(clfstf);
			}
			
//			for(Integer x : req.getTrainingModule()) {
//				TrainingMapperEntity clfMod = new TrainingMapperEntity(x,null,);
//				trnList.add(clfMod);
//			}
			for(HashMap<String,Integer> hashobj: req.getModulePart()) {
				TrainingMapperEntity clfMod = new TrainingMapperEntity(hashobj.get("trainingModule"),null,hashobj.get("participantNo"));
				trnList.add(clfMod);
			}
			
			obj.setTrainMappers(trnList);
			
			trainingRepository.save(obj);

			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;

		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Training Not Created");
		}

	}

	// Update PutMapping...
	/*
	@Override
	public Response<String> updateTraining(Long loginId, TrainingRequest trainingreq) {
		Response<String> response = new Response<String>();
		try {
			Optional<TrainingEntity> optional = trainingRepository.findById(trainingreq.getId());
			if (optional.isPresent()) {
				TrainingEntity obj = optional.get();

				obj.setClfCode(trainingreq.getClfCode());
				obj.setTrainingModule(trainingreq.getTrainingModule());
				obj.setClfStaffType(trainingreq.getClfStaffType());
				obj.setTrainingConduct(trainingreq.getTrainingConduct());
				obj.setParticipantNo(trainingreq.getParticipantNo());
				obj.setStartDate(trainingreq.getStartDate());
				obj.setEndDate(trainingreq.getEndDate());
				obj.setUpdatedBy(loginId);
				obj.setUpdatedAt(new Date());

				trainingRepository.save(obj);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Training Found with given value");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Training Not Updated");
		}
	}
	*/

	// Get all records from database....
	@Override
	public Response<TrainingResponse> getTrainingList(TrainingFilterRequest filterDto) {

		Response<TrainingResponse> response = new Response<TrainingResponse>();
		List<TrainingResponse> wrappedList = new ArrayList<TrainingResponse>();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");


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

			List<TrainingCustomEntity> trainingList = customTrainingRepository.getTraining(filterDto, locations,
					levelName, pageModel);


			wrappedList = trainingList.stream().map(training -> {
				return new TrainingResponse(training.getId(), training.getStateCode(), training.getStateName(),
						training.getDistrictCode(), training.getDistrictName(), training.getBlockCode(),
						training.getBlockName(), training.getClfCode(), training.getClfName(), training.getTrainingConduct(),
						strToArray(training.getClfStaffType()),arrToMap(strToArray(training.getTrainingModule()),strToArray(training.getPartIntrNo())),
						training.getTrainingStatus(), training.getStartDate(), training.getEndDate());

			}).collect(Collectors.toList());

			response.setWrapperList(wrappedList);

			response.setTotalcount(
					customTrainingRepository.getTrainingCount(filterDto, locations, levelName, pageModel));
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
	
	public Integer[] strToArray(String str)
    {
		
		if(str==null || str.isEmpty()) {
			return new Integer[0];
		}
        String newstr = "[" + str + "]";

        String[] string = newstr.replaceAll("\\[", "")
                              .replaceAll("]", "")
                              .split(",");
 
        // declaring an array with the size of string
        Integer[] arr = new Integer[string.length];
 
        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        for (Integer i = 0; i < string.length; i++) {
            arr[i] = Integer.valueOf(string[i]);
        }
        
        return arr;
    }
	
	public List<HashMap<String,Integer>> arrToMap(Integer[] x,Integer[] y){
		List<HashMap<String,Integer>> hmapList = new ArrayList<HashMap<String,Integer>>();


		for(int i = 0 ; i < x.length ; i++) {
			HashMap<String,Integer> hmap = new HashMap<String,Integer>();
			
			if(x[i] == 0) {
				hmap.put("trainingModule", null);
			}
			else {
				hmap.put("trainingModule", x[i]);
			}
			
			if(y.length==0) {
				hmap.put("participantNo", null);
			}else {
				if(y[i] == 0) {
					hmap.put("participantNo", null);
				}
				else {
					hmap.put("participantNo", y[i]);
				}
				


			}
			hmapList.add(hmap);
		}
		return hmapList;
	}


}
