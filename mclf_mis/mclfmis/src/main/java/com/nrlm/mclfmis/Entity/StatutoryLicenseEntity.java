package com.nrlm.mclfmis.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="mclf_compliance_statutory_license")
public class StatutoryLicenseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	
	@Column(name="compliance_id")
	private Long complianceId;
	
	@Column(name="license_selected")
	private Integer licenseSelected;
	
	@Column(name="is_license")
	private Integer isLicense;
	
	@Temporal(TemporalType.DATE)
	@Column(name="license_date")
	private Date licenseDate;
	
	@Column(name="is_license_renewal")
	private Integer isLicenseRenewal;
	
	@Column(name="license_renewal_month")
	private Integer licenseRenewalMonth;

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
