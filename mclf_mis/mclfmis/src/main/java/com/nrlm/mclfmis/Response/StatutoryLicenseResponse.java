package com.nrlm.mclfmis.Response;

import java.util.Date;

public class StatutoryLicenseResponse {

	private Long id;
	private Long complianceId;
	private Integer licenseSelected;
	private Integer isLicense;
	private Date licenseDate;
	private Integer isLicenseRenewal;
	private Integer licenseRenewalMonth;
	
	
	
	

	public StatutoryLicenseResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatutoryLicenseResponse(Long id, Long complianceId, Integer licenseSelected, Integer isLicense,
			Date licenseDate, Integer isLicenseRenewal, Integer licenseRenewalMonth) {
		super();
		this.id = id;
		this.complianceId = complianceId;
		this.licenseSelected = licenseSelected;
		this.isLicense = isLicense;
		this.licenseDate = licenseDate;
		this.isLicenseRenewal = isLicenseRenewal;
		this.licenseRenewalMonth = licenseRenewalMonth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(Long complianceId) {
		this.complianceId = complianceId;
	}

	public Integer getLicenseSelected() {
		return licenseSelected;
	}

	public void setLicenseSelected(Integer licenseSelected) {
		this.licenseSelected = licenseSelected;
	}

	public Integer getIsLicense() {
		return isLicense;
	}

	public void setIsLicense(Integer isLicense) {
		this.isLicense = isLicense;
	}

	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public Integer getIsLicenseRenewal() {
		return isLicenseRenewal;
	}

	public void setIsLicenseRenewal(Integer isLicenseRenewal) {
		this.isLicenseRenewal = isLicenseRenewal;
	}

	public Integer getLicenseRenewalMonth() {
		return licenseRenewalMonth;
	}

	public void setLicenseRenewalMonth(Integer licenseRenewalMonth) {
		this.licenseRenewalMonth = licenseRenewalMonth;
	}
	
	
	
}
