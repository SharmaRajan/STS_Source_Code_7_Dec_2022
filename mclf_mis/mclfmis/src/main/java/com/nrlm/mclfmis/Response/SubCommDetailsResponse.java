package com.nrlm.mclfmis.Response;


public class SubCommDetailsResponse {
	
	private Long id;
	private String clfCode;
	private String clfName;
	private String stateCode;
	private String stateName;
	private String districtCode;
	private String districtName;
	private String blockCode;
	private String blockName;
	private Integer moniMemb;
	private Integer clfAstMemb;
	private Integer livHoodMemb;
	private Integer	bnkLinkMemb;
	private Integer	socActMemb;
	private Integer	anyOther;
	private String anyOtherName;
	private Integer anyOtherMemb;
	private String status;
	
	public SubCommDetailsResponse(Long id, String clfCode, String clfName, String stateCode, String stateName,
			String districtCode, String districtName, String blockCode, String blockName, Integer moniMemb,
			Integer clfAstMemb, Integer livHoodMemb, Integer bnkLinkMemb, Integer socActMemb, Integer anyOther,
			String anyOtherName, Integer anyOtherMemb, String status) {
		super();
		this.id = id;
		this.clfCode = clfCode;
		this.clfName = clfName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.blockCode = blockCode;
		this.blockName = blockName;
		this.moniMemb = moniMemb;
		this.clfAstMemb = clfAstMemb;
		this.livHoodMemb = livHoodMemb;
		this.bnkLinkMemb = bnkLinkMemb;
		this.socActMemb = socActMemb;
		this.anyOther = anyOther;
		this.anyOtherName = anyOtherName;
		this.anyOtherMemb = anyOtherMemb;
		this.status = status;
	}
	
	public SubCommDetailsResponse() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClfCode() {
		return clfCode;
	}

	public void setClfCode(String clfCode) {
		this.clfCode = clfCode;
	}

	public String getClfName() {
		return clfName;
	}

	public void setClfName(String clfName) {
		this.clfName = clfName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public Integer getMoniMemb() {
		return moniMemb;
	}

	public void setMoniMemb(Integer moniMemb) {
		this.moniMemb = moniMemb;
	}

	public Integer getClfAstMemb() {
		return clfAstMemb;
	}

	public void setClfAstMemb(Integer clfAstMemb) {
		this.clfAstMemb = clfAstMemb;
	}

	public Integer getLivHoodMemb() {
		return livHoodMemb;
	}

	public void setLivHoodMemb(Integer livHoodMemb) {
		this.livHoodMemb = livHoodMemb;
	}

	public Integer getBnkLinkMemb() {
		return bnkLinkMemb;
	}

	public void setBnkLinkMemb(Integer bnkLinkMemb) {
		this.bnkLinkMemb = bnkLinkMemb;
	}

	public Integer getSocActMemb() {
		return socActMemb;
	}

	public void setSocActMemb(Integer socActMemb) {
		this.socActMemb = socActMemb;
	}

	public Integer getAnyOther() {
		return anyOther;
	}

	public void setAnyOther(Integer anyOther) {
		this.anyOther = anyOther;
	}

	public String getAnyOtherName() {
		return anyOtherName;
	}

	public void setAnyOtherName(String anyOtherName) {
		this.anyOtherName = anyOtherName;
	}

	public Integer getAnyOtherMemb() {
		return anyOtherMemb;
	}

	public void setAnyOtherMemb(Integer anyOtherMemb) {
		this.anyOtherMemb = anyOtherMemb;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
