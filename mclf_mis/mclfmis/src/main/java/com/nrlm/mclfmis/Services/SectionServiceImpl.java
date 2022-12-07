package com.nrlm.mclfmis.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nrlm.mclfmis.Entity.AnswerOptionEntity;
import com.nrlm.mclfmis.Entity.SectionEntity;
import com.nrlm.mclfmis.Helper.PageModel;
import com.nrlm.mclfmis.Helper.ProjectConstants;
import com.nrlm.mclfmis.Repository.SectionRepository;
import com.nrlm.mclfmis.Request.SectionRequest;
import com.nrlm.mclfmis.Request.SectionSequenceRequest;
import com.nrlm.mclfmis.Response.Response;
import com.nrlm.mclfmis.Response.SectionPermissionResponse;
import com.nrlm.mclfmis.Response.SectionResponse;
import com.nrlm.mclfmis.Response.SectionSequenceResponse;
import com.nrlm.mclfmis.Response.SectionTabResponse;
import com.nrlm.mclfmis.Specification.SectionSpecification;
import com.nrlm.mclfmis.exception.HandledException;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	SectionSpecification sectionSpecification;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PageModel pageModel;

	@Override
	public Response<SectionPermissionResponse> getSectionPermissions() {
		Response<SectionPermissionResponse> response = new Response<SectionPermissionResponse>();
		List<SectionPermissionResponse> wrappedList = new ArrayList<SectionPermissionResponse>();
		SectionPermissionResponse permissionDto = new SectionPermissionResponse();
		// List<SectionTabResponse> sectionDtoList = new
		// ArrayList<SectionTabResponse>();
		// SectionTabResponse sectionDto = null;

		permissionDto.setIsCreateBtn("Y");
		permissionDto.setIsAction("Y");
		wrappedList.add(permissionDto);
		/*
		 * List<AnswerOptionEntity> sectionTabList =
		 * sectionRepository.getSectionTabList(ProjectConstants.SECTION_TABLE,
		 * ProjectConstants.SECTION_COL_SHORT_KEY); if(sectionTabList == null ||
		 * sectionTabList.size() == 0) { //return throw new NullPointerException(); }
		 * else { for(AnswerOptionEntity section : sectionTabList) { sectionDto = new
		 * SectionTabResponse(); sectionDto.setTabId(section.getOptionId());
		 * sectionDto.setTabName(section.getOptionValue());
		 * sectionDto.setIsDisable("Y"); sectionDtoList.add(sectionDto); }
		 * permissionDto.setTabList(sectionDtoList);
		 * response.setWrapperList(wrappedList);
		 * response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
		 * response.setResponseDesc(HttpStatus.OK.name()); }
		 */
		response.setWrapperList(wrappedList);
		response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
		response.setResponseDesc(HttpStatus.OK.name());
		return response;
	}

	@Override
	public Response<SectionResponse> getSectionList(SectionRequest filterdto) {
		try {
			Response<SectionResponse> response = new Response<SectionResponse>();
			List<SectionResponse> wrappedList = new ArrayList<SectionResponse>();
			PageModel.setSIZE(20);
			pageModel.initPageAndSize();
			Long totalCount = Long.valueOf(0);
			String errorMsg = null;
			if (filterdto.getTabId() == null || filterdto.getTabId() == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab Id Found");
			}
			Page<SectionEntity> sectionList = sectionRepository.findAll(sectionSpecification.getSectionList(filterdto),
					PageRequest.of(PageModel.getPAGE(), PageModel.getSIZE()));

			if (sectionList != null && sectionList.getSize() != 0) {
				wrappedList = sectionList
						.getContent().stream().map(secdto -> new SectionResponse(secdto.getId(),
								secdto.getSectionName(), secdto.getDescription(), secdto.getSectionStatus(),secdto.getSkipFlag()))
						.collect(Collectors.toList());
				totalCount = sectionList.getTotalElements();
			} else {
				errorMsg = "No Section Found";
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Section Found");
			}
			response.setWrapperList(wrappedList);
			response.setTotalcount(totalCount);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			response.setErrorMsg(errorMsg);
			return response;
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public Response<SectionResponse> getSectionDropDownList(SectionRequest filterdto) {
		try {
			Response<SectionResponse> response = new Response<SectionResponse>();
			List<SectionResponse> wrappedList = new ArrayList<SectionResponse>();
			// PageModel.setSIZE(20);
			// pageModel.initPageAndSize();
			Long totalCount = Long.valueOf(0);
			String errorMsg = null;
			if (filterdto.getTabId() == null || filterdto.getTabId() == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab ID Found");
			}
			List<SectionEntity> sectionList = null;
			if(filterdto.getSectionId() != null && filterdto.getSectionId() != 0) {
				sectionList = sectionRepository.getSectionsBySectionId(Long.parseLong(String.valueOf(filterdto.getSectionId())));
			}
			else {
				sectionList = sectionRepository.getSectionsByTabId(filterdto.getTabId());
			}
			

			if (sectionList != null && sectionList.size() != 0) {
				wrappedList = sectionList.stream().map(secdto -> new SectionResponse(secdto.getId(),
						secdto.getSectionName(), secdto.getDescription(), secdto.getSectionStatus(),secdto.getSkipFlag()))
						.collect(Collectors.toList());
				totalCount = Long.parseLong(String.valueOf(sectionList.size()));
			} else {
				errorMsg = "No Section Found";
//				throw new HandledException(HttpStatus.NOT_FOUND, "No Section Found");
			}

			response.setWrapperList(wrappedList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			response.setTotalcount(totalCount);
			response.setErrorMsg(errorMsg);
			return response;
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public Response<String> createSection(Long loginId, SectionRequest request) {
		Response<String> response = new Response<String>();
		try {
			Optional<SectionEntity> optionalSection = sectionRepository
					.findBySectionNameAndTabId(request.getSectionName().toLowerCase(), request.getTabId());
			if (optionalSection.isPresent()) {
				Integer seqNo = 0;
				Integer sequence = sectionRepository.findMaxSeqByTabId(request.getTabId());
				if (sequence == null) {
					seqNo = 1;
				} else {
					seqNo = sequence + 1;
				}
				SectionEntity section = new SectionEntity();
				section.setCreatedAt(new Date());
				section.setCreatedBy(loginId);
				section.setDescription(request.getSectionDesc());
				section.setSectionName(request.getSectionName());
				section.setSectionStatus(request.getStatus());
				section.setTabType(request.getTabId());
				section.setSequence(seqNo);
				section.setStatus(ProjectConstants.ACTIVE_STATUS);
				
				
				if(request.getIsSkip() != null && request.getIsSkip() == ProjectConstants.SKIP_APPLIED) {
					section.setSkipFlag(ProjectConstants.SKIP_APPLIED);
				}
				else {
					section.setSkipFlag(ProjectConstants.SKIP_NOT_APPLIED);
				}
				

				sectionRepository.save(section);

				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "Section Already exist with this Name");
			}

		} catch (HandledException e) {
			e.printStackTrace();
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Section Not Created");
		}

	}

	@Override
	public Response<String> updateSection(Long loginId,SectionRequest request) {
		Response<String> response = new Response<String>();
		try {
			Optional<SectionEntity> optionalSection = sectionRepository.findById(request.getSectionId());
			if (optionalSection.isPresent()) {
				SectionEntity section = optionalSection.get();
				section.setUpdatedAt(new Date());
				section.setUpdatedBy(loginId);
				section.setDescription(request.getSectionDesc());
				section.setSectionName(request.getSectionName());
				section.setSectionStatus(request.getStatus());
				section.setTabType(request.getTabId());
				if(request.getIsSkip() != null && request.getIsSkip() == ProjectConstants.SKIP_APPLIED) {
					section.setSkipFlag(ProjectConstants.SKIP_APPLIED);
				}
				else {
					section.setSkipFlag(ProjectConstants.SKIP_NOT_APPLIED);
				}
				sectionRepository.save(section);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Section Found with given value");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Section Not Updated");
		}

	}

	@Override
	public Response<String> deleteSection(Long loginId, Long sectionId) {

		Response<String> response = new Response<String>();
		try {
			Optional<SectionEntity> optionalSection = sectionRepository.findById(sectionId);
			if (optionalSection.isPresent()) {
				SectionEntity section = optionalSection.get();
				section.setUpdatedAt(new Date());
				section.setUpdatedBy(loginId);
				section.setSectionStatus(ProjectConstants.INACTIVE_STATUS);
				section.setStatus(ProjectConstants.INACTIVE_STATUS);

				sectionRepository.save(section);

				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			} else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Section Found with given value");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Section Not Deleted");
		}

	}

	@Override
	public Response<SectionTabResponse> getTabList() {

		Response<SectionTabResponse> response = new Response<SectionTabResponse>();
		List<SectionTabResponse> wrappedList = new ArrayList<SectionTabResponse>();
		SectionTabResponse tabDto = null;
		try {
			List<AnswerOptionEntity> sectionTabList = sectionRepository
					.getSectionTabList(ProjectConstants.SECTION_TABLE, ProjectConstants.SECTION_COL_SHORT_KEY);
			if (sectionTabList == null || sectionTabList.size() == 0) {
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				response.setErrorMsg("No Tab ID Found");
//				throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab ID Found");
			} else {
				for (AnswerOptionEntity section : sectionTabList) {
					tabDto = new SectionTabResponse();
					tabDto.setTabId(section.getOptionId());
					tabDto.setTabName(section.getOptionValue());
					tabDto.setIsDisable("N");
					wrappedList.add(tabDto);
				}
				response.setWrapperList(wrappedList);
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
			}

			return response;
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

	}

	@Override
	public Response<SectionSequenceResponse> getSectionSequenceList(SectionRequest filterdto) {
		try {
			Response<SectionSequenceResponse> response = new Response<SectionSequenceResponse>();
			List<SectionSequenceResponse> wrappedList = new ArrayList<SectionSequenceResponse>();
			Long totalCount = 0L;
			String errorMsg = null;
			if (filterdto.getTabId() == null || filterdto.getTabId() == 0) {
				throw new HandledException(HttpStatus.BAD_REQUEST, "No Tab ID Found");
			}
			List<SectionEntity> sectionList = null;
			
			sectionList = sectionRepository.getSectionsSequenceByTabId(filterdto.getTabId());
			

			if (sectionList != null && sectionList.size() != 0) {
				wrappedList = sectionList.stream().map(secdto -> new SectionSequenceResponse(secdto.getId(),
						secdto.getSectionName(),secdto.getSequence()))
						.collect(Collectors.toList());
				totalCount = Long.parseLong(String.valueOf(sectionList.size()));
			} 
			else {
				errorMsg = "No Section Found";
			}

			response.setWrapperList(wrappedList);
			response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
			response.setResponseDesc(HttpStatus.OK.name());
			response.setTotalcount(totalCount);
			response.setErrorMsg(errorMsg);
			return response;
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public Response<String> updateSectionSequenceList(Long userId, List<SectionSequenceRequest> request) {
		Response<String> response = new Response<String>();
		try {
			if(request != null && request.size() != 0) {
				
				for(SectionSequenceRequest req : request) {
					
					Optional<SectionEntity> optionalSection = sectionRepository.findById(req.getSectionId());
					
					if (optionalSection.isPresent()) {
						SectionEntity section = optionalSection.get();
						section.setSequence(req.getSequence());
						section.setUpdatedAt(new Date());
						section.setUpdatedBy(userId);
						
						sectionRepository.save(section);
						
					}
				}
				
				response.setResponseCode(String.valueOf(HttpStatus.OK.value()));
				response.setResponseDesc(HttpStatus.OK.name());
				return response;
			}
			
			else {
				throw new HandledException(HttpStatus.UNPROCESSABLE_ENTITY, "No Section Request");
			}
		} catch (HandledException e) {
			throw new HandledException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new HandledException(HttpStatus.INTERNAL_SERVER_ERROR, "Sequence Not Updated");
		}

	}

}
