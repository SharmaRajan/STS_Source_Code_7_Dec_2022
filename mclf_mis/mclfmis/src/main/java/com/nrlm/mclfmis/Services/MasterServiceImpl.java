package com.nrlm.mclfmis.Services;

import com.nrlm.mclfmis.Entity.*;
import com.nrlm.mclfmis.Dto.*;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.*;
import com.nrlm.mclfmis.Response.AnswerOptionListResponse;
import com.nrlm.mclfmis.Response.AnswerOptionResponse;
import com.nrlm.mclfmis.Response.ClfInfoResponse;
import com.nrlm.mclfmis.Response.FinancialYearResponse;
import com.nrlm.mclfmis.Response.MonthResponse;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionTabResponse;
import com.nrlm.mclfmis.Specification.BlockSpecification;
import com.nrlm.mclfmis.Specification.ClfmasterSpecification;
import com.nrlm.mclfmis.Specification.DistrictSpecification;
import com.nrlm.mclfmis.Specification.GpSpecification;
import com.nrlm.mclfmis.Specification.VoProfileSpecification;
import com.nrlm.mclfmis.customEntity.ClfMasterEntity;
import com.nrlm.mclfmis.customRepository.CustomCutOffRepository;
import com.nrlm.mclfmis.exception.HandledException;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@Service
public class MasterServiceImpl implements MasterService {

	

	@Autowired
	private ClfRepository clfRepository;
	
	@Autowired
	private FinancialYearRepository finyearRepository;

	@Autowired
	private MonthRepository monthRepository;

	@Autowired
	private AnswerOptionRepository ansOpRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	ClfCutOffMonthlyRepository clfCutOffMonthRepository;
	
	@Autowired
	CustomCutOffRepository customCutOffRepository;


	/*@Override
	public Response<State> getStates(String statecode) {
		Map<String, String> errorMap = new HashMap<>();
		Response<State> response = new Response<State>();
		PageModel.setSIZE(10);
		pageModel.initPageAndSize();

		if (statecode != null) {
			List<State> stateres = stateRepository.findByStateCode(statecode);
			response.setWrapperList(stateres);
		} else {
			Page<State> states = stateRepository.findAll(PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
			response.setTotalcount(states.getTotalElements());
			List<State> stateres = states.getContent();
			response.setWrapperList(stateres);
		}
		return response;
	}

	@Override
	public Response<DistrictDto> getDistricts(HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		Response<DistrictDto> response = new Response<DistrictDto>();
		PageModel.setSIZE(10);
		pageModel.initPageAndSize();
		int levelid = 2;
		List<String> locationids = Arrays.asList("12", "31");
		Page<District> districts = disRepository.findAll(disSpecification.getDistricts(request, locationids, levelid),
				PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
		response.setTotalcount(districts.getTotalElements());
		List<DistrictDto> districtdto = districts.getContent().stream()
				.map(disdto -> modelMapper.map(disdto, DistrictDto.class)).collect(Collectors.toList());
		response.setWrapperList(districtdto);
		return response;
	}

	@Override
	public Response<BlockDto> getBlocks(HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		Response<BlockDto> response = new Response<BlockDto>();
		PageModel.setSIZE(10);
		pageModel.initPageAndSize();
		Integer levelid = 2;
		List<String> locationids = Arrays.asList("12", "31");
		Page<Block> blocks = blockRepository.findAll(blockSpecification.getBlocks(request, locationids, levelid),
				PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
		response.setTotalcount(blocks.getTotalElements());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<BlockDto> blockdto = blocks.getContent().stream().map(bldto -> modelMapper.map(bldto, BlockDto.class))
				.collect(Collectors.toList());
		response.setWrapperList(blockdto);
		return response;
	}

	@Override
	public Response<GpDto> getGps(HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		Response<GpDto> response = new Response<GpDto>();
		PageModel.setSIZE(10);
		pageModel.initPageAndSize();
		Integer levelid = 2;
		List<String> locationids = Arrays.asList("12", "31");
		Page<Grampanchayat> gps = gpRepository.findAll(gpSpecification.getGps(request, locationids, levelid),
				PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
		response.setTotalcount(gps.getTotalElements());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<GpDto> gpdtos = gps.getContent().stream().map(gdto -> modelMapper.map(gdto, GpDto.class))
				.collect(Collectors.toList());
		response.setWrapperList(gpdtos);
		return response;
	}

	@Override
	public Response<ClfmasterDto> getClfs(HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		Response<ClfmasterDto> response = new Response<ClfmasterDto>();
		PageModel.setSIZE(10);
		pageModel.initPageAndSize();
		Integer levelid = 2;
		List<String> locationids = Arrays.asList("12", "31");
		Page<ClfMaster> clfs = clfRepository.findAll(
				clfMasterSpecification.getClfMasters(request, locationids, levelid),
				PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
		this.modelMapper = new ModelMapper();
//		System.out.println(clfs.getContent().get(0));
//		System.out.println(clfs.getContent().get(0).getClfgpmapping());
//		System.out.println(clfs.getContent().get(0).getClfVomapping());
		response.setTotalcount(clfs.getTotalElements());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		PropertyMap<ClfMaster, ClfmasterDto> personMap = new PropertyMap<ClfMaster, ClfmasterDto>() {
			protected void configure() {
				map().setClfgpmappings(source.getClfgpmapping());
				map().setClfVoMappings(source.getClfVomapping());
			}
		};
		modelMapper.addMappings(personMap);
		List<ClfmasterDto> clfdtos = clfs.getContent().stream()
				.map(clfdto -> modelMapper.map(clfdto, ClfmasterDto.class)).collect(Collectors.toList());
		response.setWrapperList(clfdtos);
		return response;
	}

	@Override
	public Response<VoProfileDto> getVos(HttpServletRequest request) {
		Map<String, String> errorMap = new HashMap<>();
		Response<VoProfileDto> response = new Response<VoProfileDto>();
		PageModel.setSIZE(10);
		pageModel.initPageAndSize();
		Integer levelid = 2;
		List<String> locationids = Arrays.asList("12", "31");
		Page<VoProfile> vos = voRepository.findAll(voprofileSpecification.getVoProfiles(request, locationids, levelid),
				PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));
		response.setTotalcount(vos.getTotalElements());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		List<VoProfileDto> vodtos = vos.getContent().stream().map(vdto -> modelMapper.map(vdto, VoProfileDto.class))
				.collect(Collectors.toList());
		response.setWrapperList(vodtos);
		return response;
	}*/

	@Override
	public Response<FinancialYearResponse> getFinancialYear(HttpServletRequest request) {
		Response<FinancialYearResponse> response = new Response<FinancialYearResponse>();
		List<FinancialYearResponse> wrapperList  = new ArrayList<FinancialYearResponse>();
		FinancialYearResponse wrapperObj = null;
		
		String formName = request.getParameter("formName");
		String clfCode = request.getParameter("clfCode");
		try {
			if(formName != null && formName.equals("basicProfile")) {
				List<Financial> fins = finyearRepository.findAll();
				wrapperList = fins.stream().map(fin -> modelMapper.map(fin, FinancialYearResponse.class))
						.collect(Collectors.toList());
			}
			else if(formName != null && (formName.equals("cut-off") || formName.equals("intCtrl"))) {
				//List<Financial> fins = finyearRepository.findByCutOff(1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = sdf.parse(ProjectConstants.CUTOFF_FINANCIAL_START_DATE);
				//Date startDate = new Date();
				List<Financial> fins = finyearRepository.findByFnclStartDateGreaterThanEqual(startDate);
				wrapperList = fins.stream().map(fin -> modelMapper.map(fin, FinancialYearResponse.class))
						.collect(Collectors.toList());
				
			}
			else if(formName != null && formName.equals("indctr")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = sdf.parse(ProjectConstants.CUTOFF_FINANCIAL_START_DATE);
				List<Financial> fins = finyearRepository.findByFnclStartDateGreaterThanEqual(startDate);
				wrapperList = fins.stream().map(fin -> modelMapper.map(fin, FinancialYearResponse.class))
						.collect(Collectors.toList());
				
			}
			else if(formName != null && formName.equals("monthlyInputs")) {
				Integer yearId = 0;
				if(clfCode != null && !clfCode.isEmpty()) {
					
					ClfCutOffMonthlyEntity clfCutOffMonthly = clfCutOffMonthRepository.getLatestMonthlyClfCutOff(clfCode);
						if(clfCutOffMonthly != null) {
							yearId  = clfCutOffMonthly.getCutOffYear();
							if(clfCutOffMonthly.getCutOffMonth() == ProjectConstants.MARCH) {
								yearId = yearId + 1;
							}
							Optional<Financial> optionalFinYear = finyearRepository.findById(yearId);
							if(optionalFinYear.isPresent()) {
								Financial fin = optionalFinYear.get();
								wrapperObj = modelMapper.map(fin, FinancialYearResponse.class);
								wrapperList.add(wrapperObj);
							}
						}
						else {
							ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(clfCode);
							if(clfCutOff != null && clfCutOff.getOverallStatus() == ProjectConstants.COMPLETED) {
								yearId  = clfCutOff.getCutOffYear();
								if(clfCutOff.getCutOffMonth() == ProjectConstants.MARCH) {
									yearId = yearId + 1;
								}
								Optional<Financial> optionalFinYear = finyearRepository.findById(yearId);
								if(optionalFinYear.isPresent()) {
									Financial fin = optionalFinYear.get();
									wrapperObj = modelMapper.map(fin, FinancialYearResponse.class);
									wrapperList.add(wrapperObj);
								}
							}
						
						}	
				
				}
				else {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate = sdf.parse(ProjectConstants.CUTOFF_FINANCIAL_START_DATE);
					List<Financial> fins = finyearRepository.findByFnclStartDateGreaterThan(startDate);
					wrapperList = fins.stream().map(fin -> modelMapper.map(fin, FinancialYearResponse.class))
							.collect(Collectors.toList());
				}
				/*else {
					List<Financial> fins = finyearRepository.findAll();
					wrapperList = fins.stream().map(fin -> modelMapper.map(fin, FinancialYearResponse.class))
							.collect(Collectors.toList());
				}*/
			}
			else {
				List<Financial> fins = finyearRepository.findAll();
				wrapperList = fins.stream().map(fin -> modelMapper.map(fin, FinancialYearResponse.class))
						.collect(Collectors.toList());
			}
			//PageModel.setSIZE(10);
			//pageModel.initPageAndSize();
			response.setTotalcount(Long.parseLong(String.valueOf(wrapperList.size())));
			/*List<Financial> finans = fins.stream().map(fin -> modelMapper.map(fin, Financial.class))
					.collect(Collectors.toList());*/
			response.setWrapperList(wrapperList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
		}
		catch(HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
		}

	@Override
	public Response<MonthResponse> getMonths(HttpServletRequest request) {
		Response<MonthResponse> response = new Response<MonthResponse>();
		List<MonthResponse> wrapperList = new ArrayList<MonthResponse>();
		
		String formName = request.getParameter("formName");
		String clfCode = request.getParameter("clfCode");
		String year = request.getParameter("yearId");
		String monthFlag = request.getParameter("monthFlag");
		
		List<Month> monthList = new ArrayList<Month>();
		
		try {
			if(formName != null && formName.equals("cut-off")) {
				Integer yearId = 0;
				
				if(year != null && !year.isEmpty()) {
					yearId = Integer.parseInt(year);
					//Integer curYear = Calendar.getInstance().get(Calendar.DATE);
					Date curDate = new Date();
					Optional<Financial> optionalFinYear = finyearRepository.findById(yearId);
					if(optionalFinYear.isPresent()) {
						Financial finYear = optionalFinYear.get();
						SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
						Date startDate = sdformat.parse(ProjectConstants.CUTOFF_FINANCIAL_START_DATE);
						//String newStartDateString = sdformat.format(startDate);
						//LocalDateTime ldt = LocalDateTime.now();
						//System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt));
						//sdformat.format(startDate);
						//startDate.
					    //Date d2 = sdformat.parse("2019-08-10");
						if(finYear.getFnclStartDate().compareTo(startDate) == 0) {
							Optional<Month> optionalMonth = monthRepository.findById(3);
							monthList.add(optionalMonth.get());
						}
						else if(curDate.compareTo(finYear.getFnclStartDate()) >=0 && curDate.compareTo(finYear.getFnclEndDate()) <=0) { // current date between lie in start/end fin year i.e it is current year
							
							Integer curMonth = Calendar.getInstance().get(Calendar.MONTH)+1; //current month
						
							//monthList = monthRepository.findByIdLessThanEqualOrderBySequenceAsc(curMonth);
							if(curMonth < 4) {
								monthList = monthRepository.findByIdBetweenOrderBySequenceAsc(4, curMonth);
								List<Month>monthList1 = monthRepository.findByIdBetweenOrderBySequenceAsc(1, curMonth);
								monthList.addAll(monthList1);
							}
							else {
								monthList = monthRepository.findByIdBetweenOrderBySequenceAsc(4, curMonth);
							}
						}
						else {
							monthList = monthRepository.findAllByOrderBySequenceAsc();
						}
					}
					
				}
				/*else {
					monthList = monthRepository.findAllByOrderBySequenceAsc();
				}*/
			}
			else if(formName != null && formName.equals("monthlyInputs")) {
				
				Integer yearId = 0;
				Integer lastClfYear = 0;
				Integer lastClfMonth = 0;
				
				if(year != null && !year.isEmpty()) {
					yearId = Integer.parseInt(year);
				}
				
				if(clfCode != null && !clfCode.isEmpty()) {
					
					ClfCutOffMonthlyEntity clfCutOffMonthly = clfCutOffMonthRepository.getLatestMonthlyClfCutOff(clfCode);
					if(clfCutOffMonthly != null) {
						 lastClfYear = clfCutOffMonthly.getCutOffYear();
						 lastClfMonth = clfCutOffMonthly.getCutOffMonth();
					}
					
					else {
						ClfCutOffEntity clfCutOff = customCutOffRepository.checkCutOffExistByClfCode(clfCode);
						
						if(clfCutOff != null && clfCutOff.getOverallStatus() == ProjectConstants.COMPLETED) {
							lastClfYear  = clfCutOff.getCutOffYear();
							lastClfMonth = clfCutOff.getCutOffMonth();
						}
						
					}
				}
				
				if(lastClfYear !=0 && yearId != 0 && yearId > ProjectConstants.CUT_OFF_START_YEAR_ID && yearId >= lastClfYear) {
					Integer startMonth = 0;
					Integer endMonth = 0;
					if(lastClfMonth != 0) {
						if(yearId == lastClfYear && lastClfMonth == 12) {
							startMonth = 13;
						}
						else if(yearId > lastClfYear && lastClfMonth == 12) {
							startMonth = 1;
						}
						else {
							startMonth = lastClfMonth+1;
						}
					}
					Date curDate = new Date();
					Optional<Financial> optionalFinYear = finyearRepository.findById(yearId);
					if(optionalFinYear.isPresent()) {
						Financial finYear = optionalFinYear.get();
						if(curDate.compareTo(finYear.getFnclStartDate()) >=0 && curDate.compareTo(finYear.getFnclEndDate()) <=0) { // current date between lie in start/end fin year i.e it is current year
							endMonth = Calendar.getInstance().get(Calendar.MONTH)+1; //current month
							
							
						}
						else {
							endMonth = 12;
							//monthList = monthRepository.findByIdBetweenOrderBySequenceAsc(4, endMonth);
							//List<Month>monthList1 = monthRepository.findByIdBetweenOrderBySequenceAsc(startMonth, endMonth);
							//monthList.addAll(monthList1);
						}
						
						monthList = monthRepository.findByIdBetweenOrderBySequenceAsc(startMonth,endMonth);
					}
				}
				
			}
			
			else if(monthFlag != null) {
				Integer curMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
				if(monthFlag.equals(ProjectConstants.PAST)) {
					monthList = monthRepository.findByIdLessThanEqualOrderBySequenceAsc(curMonth);
				}
				else if(monthFlag.equals(ProjectConstants.CURRENT)) {
					Optional<Month> optionalMonth = monthRepository.findById(curMonth);
					monthList.add(optionalMonth.get());
				}
				else if(monthFlag.equals(ProjectConstants.FUTURE)) {
					monthList = monthRepository.findByIdGreaterThanEqualOrderBySequenceAsc(curMonth);
				}
			}
			else {
				monthList = monthRepository.findAllByOrderBySequenceAsc();
			}
				
			
			if(monthList != null && monthList.size() != 0) {
				wrapperList = monthList.stream().map(mon -> modelMapper.map(mon, MonthResponse.class))
						.collect(Collectors.toList());
				
			}
			response.setWrapperList(wrapperList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			return response;
		}
		catch(HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		}
		catch(Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
		}
		
		}

	@Override
	public Response<AnswerOptionListResponse> getAnsweroptions(HttpServletRequest request) {
		Response<AnswerOptionListResponse> response = new Response<AnswerOptionListResponse>();
		List<AnswerOptionEntity> answers = null;
		if (request.getParameter("tableName") != null && request.getParameter("shortKey") != null) {
			answers = ansOpRepository.findAllBykeyandtable(request.getParameter("tableName"),
					request.getParameter("shortKey"));
		} else if (request.getParameter("tableName") != null) {
			answers = ansOpRepository.findAllBytable(request.getParameter("tableName"));
		} else {
			throw new HandledException(HttpStatus.BAD_REQUEST, "No Table Name and Short Key Found");
		}
		List<AnswerOptionListResponse> finaloptions = answers.stream()
				.map(ans -> modelMapper.map(ans, AnswerOptionListResponse.class)).collect(Collectors.toList());
		response.setWrapperList(finaloptions);
		response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
		response.setResponseDesc(HttpStatus.OK.name());
		return response;
	}

	public Response<ClfInfoResponse> getClfInfo(String clfCode) {

		Response<ClfInfoResponse> response = new Response<ClfInfoResponse>();
		List<ClfInfoResponse> wrappedList = new ArrayList<ClfInfoResponse>();
		try {
			List<ClfMasterEntity> clfList = clfRepository.findByClfcode(clfCode);
			if (clfList == null && clfList.size() != 0) {
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				response.setErrorMsg("No CLF Found");
				return response;
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Clf Found");
			} else {
				ClfMasterEntity clf = clfList.get(0);
				ClfInfoResponse clfdto = modelMapper.map(clf, ClfInfoResponse.class);
				
				wrappedList.add(clfdto);
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
			}

			return response;
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}
	}
}
