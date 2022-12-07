package com.nrlm.mclfmis.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

    @Entity
    @Table(name = "vo_profile")
    public class VoProfile {

        @Column(name = "vo_code_no")
        private Integer voCodeNo;

        @Id
        @Column(name = "vo_code")
        private String voCode;

        @Column(name = "gp_code")
        private String gpCode;

        @Column(name = "vo_name")
        private String voName;

        @Column(name = "monthly_ec_date_d1")
        private Integer monthlyEcDateD1;

        @Column(name = "monthly_ec_date_d2")
        private Integer monthlyEcDateD2;

        @Column(name = "cc_ac_name")
        private String ccAcName;

        @Column(name = "cc_ac_contact")
        private String ccAcContact;

        @Column(name = "vo_book_keeper_name")
        private String voBookKeeperName;

        @Column(name = "vo_book_keeper_contact")
        private String voBookKeeperContact;

        @Column(name = "formation_date")
        private String formationDate;

        @Column(name = "vo_office_building")
        private String voOfficeBuilding;

        @Column(name = "vo_office_address")
        private String voOfficeAddress;

        @Column(name = "monthly_subscription_amount_shg")
        private Integer monthlySubscriptionAmountshg;
        @Column(name = "vo_general_branch_code")
        private String voGeneralBranchcode;
        @Column(name = "vo_general_account_no")
        private String voGeneralAccountno;

        @Column(name = "created_on")
        private String createdOn;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "updated_on")
        private String updatedOn;

        @Column(name = "annual_membership_fee")
        private Integer annualMembershipFee;

        @Column(name = "active_detail")
        private String activeDetail;

        @Column(name = "vo_reg_no")
        private String voregsNo;

        @Column(name = "vo_renvl_date")
        private String voRenvlDate;

        @Column(name = "share_capital_shg")
        private String shareCapitalshg;
        @Column(name = "gen_bank_open_date")
        private String genbankOpendata;

        
        @ManyToOne( fetch = FetchType.LAZY,targetEntity = Grampanchayat.class)
        @JoinColumn( name = "gp_code", insertable = false, updatable = false )
        private Grampanchayat gp;
        
        @OneToOne(mappedBy = "voprofile")
        private ClfVoMapping clfvomapping;
        
        @JsonBackReference
        @OneToOne(mappedBy = "voprofile")
    	private VoCutOffEntity vocutoff;
        
        @JsonBackReference
        @OneToMany(fetch = FetchType.LAZY,mappedBy = "voprofile")
    	private List<VoCutOffMonthlyEntity> vocutoffmonthly;
        
        
        public ClfVoMapping getClfvomapping() {
			return clfvomapping;
		}

		public void setClfvomapping(ClfVoMapping clfvomapping) {
			this.clfvomapping = clfvomapping;
		}

		public Grampanchayat getGp() {
			return gp;
		}

		public void setGp(Grampanchayat gp) {
			this.gp = gp;
		}

		public Integer getVoCodeNo() {
            return voCodeNo;
        }

        public void setVoCodeNo(Integer voCodeNo) {
            this.voCodeNo = voCodeNo;
        }

        public String getVoCode() {
            return voCode;
        }

        public void setVoCode(String voCode) {
            this.voCode = voCode;
        }

        public String getGpCode() {
            return gpCode;
        }

        public void setGpCode(String gpCode) {
            this.gpCode = gpCode;
        }

        public String getVoName() {
            return voName;
        }

        public void setVoName(String voName) {
            this.voName = voName;
        }

        public Integer getMonthlyEcDateD1() {
            return monthlyEcDateD1;
        }

        public void setMonthlyEcDateD1(Integer monthlyEcDateD1) {
            this.monthlyEcDateD1 = monthlyEcDateD1;
        }

        public Integer getMonthlyEcDateD2() {
            return monthlyEcDateD2;
        }

        public void setMonthlyEcDateD2(Integer monthlyEcDateD2) {
            this.monthlyEcDateD2 = monthlyEcDateD2;
        }

        public String getCcAcName() {
            return ccAcName;
        }

        public void setCcAcName(String ccAcName) {
            this.ccAcName = ccAcName;
        }

        public String getCcAcContact() {
            return ccAcContact;
        }

        public void setCcAcContact(String ccAcContact) {
            this.ccAcContact = ccAcContact;
        }

        public String getVoBookKeeperName() {
            return voBookKeeperName;
        }

        public void setVoBookKeeperName(String voBookKeeperName) {
            this.voBookKeeperName = voBookKeeperName;
        }

        public String getVoBookKeeperContact() {
            return voBookKeeperContact;
        }

        public void setVoBookKeeperContact(String voBookKeeperContact) {
            this.voBookKeeperContact = voBookKeeperContact;
        }

        public String getFormationDate() {
            return formationDate;
        }

        public void setFormationDate(String formationDate) {
            this.formationDate = formationDate;
        }

        public String getVoOfficeBuilding() {
            return voOfficeBuilding;
        }

        public void setVoOfficeBuilding(String voOfficeBuilding) {
            this.voOfficeBuilding = voOfficeBuilding;
        }

        public String getVoOfficeAddress() {
            return voOfficeAddress;
        }

        public void setVoOfficeAddress(String voOfficeAddress) {
            this.voOfficeAddress = voOfficeAddress;
        }

        public Integer getMonthlySubscriptionAmountshg() {
            return monthlySubscriptionAmountshg;
        }

        public void setMonthlySubscriptionAmountshg(Integer monthlySubscriptionAmountshg) {
            this.monthlySubscriptionAmountshg = monthlySubscriptionAmountshg;
        }

        public String getVoGeneralBranchcode() {
            return voGeneralBranchcode;
        }

        public void setVoGeneralBranchcode(String voGeneralBranchcode) {
            this.voGeneralBranchcode = voGeneralBranchcode;
        }

        public String getVoGeneralAccountno() {
            return voGeneralAccountno;
        }

        public void setVoGeneralAccountno(String voGeneralAccountno) {
            this.voGeneralAccountno = voGeneralAccountno;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String getUpdatedOn() {
            return updatedOn;
        }

        public void setUpdatedOn(String updatedOn) {
            this.updatedOn = updatedOn;
        }

        public Integer getAnnualMembershipFee() {
            return annualMembershipFee;
        }

        public void setAnnualMembershipFee(Integer annualMembershipFee) {
            this.annualMembershipFee = annualMembershipFee;
        }

        public String getActiveDetail() {
            return activeDetail;
        }

        public void setActiveDetail(String activeDetail) {
            this.activeDetail = activeDetail;
        }

        public String getVoregsNo() {
            return voregsNo;
        }

        public void setVoregsNo(String voregsNo) {
            this.voregsNo = voregsNo;
        }

        public String getVoRenvlDate() {
            return voRenvlDate;
        }

        public void setVoRenvlDate(String voRenvlDate) {
            this.voRenvlDate = voRenvlDate;
        }

        public String getShareCapitalshg() {
            return shareCapitalshg;
        }

        public void setShareCapitalshg(String shareCapitalshg) {
            this.shareCapitalshg = shareCapitalshg;
        }

        public String getGenbankOpendata() {
            return genbankOpendata;
        }

        public void setGenbankOpendata(String genbankOpendata) {
            this.genbankOpendata = genbankOpendata;
        }

        public VoProfile(Integer voCodeNo, String voCode, String gpCode, String voName, Integer monthlyEcDateD1, Integer monthlyEcDateD2, String ccAcName, String ccAcContact, String voBookKeeperName, String voBookKeeperContact, String formationDate, String voOfficeBuilding, String voOfficeAddress, Integer monthlySubscriptionAmountshg, String voGeneralBranchcode, String voGeneralAccountno, String createdOn, String createdBy, String updatedBy, String updatedOn, Integer annualMembershipFee, String activeDetail, String voregsNo, String voRenvlDate, String shareCapitalshg, String genbankOpendata) {
            this.voCodeNo = voCodeNo;
            this.voCode = voCode;
            this.gpCode = gpCode;
            this.voName = voName;
            this.monthlyEcDateD1 = monthlyEcDateD1;
            this.monthlyEcDateD2 = monthlyEcDateD2;
            this.ccAcName = ccAcName;
            this.ccAcContact = ccAcContact;
            this.voBookKeeperName = voBookKeeperName;
            this.voBookKeeperContact = voBookKeeperContact;
            this.formationDate = formationDate;
            this.voOfficeBuilding = voOfficeBuilding;
            this.voOfficeAddress = voOfficeAddress;
            this.monthlySubscriptionAmountshg = monthlySubscriptionAmountshg;
            this.voGeneralBranchcode = voGeneralBranchcode;
            this.voGeneralAccountno = voGeneralAccountno;
            this.createdOn = createdOn;
            this.createdBy = createdBy;
            this.updatedBy = updatedBy;
            this.updatedOn = updatedOn;
            this.annualMembershipFee = annualMembershipFee;
            this.activeDetail = activeDetail;
            this.voregsNo = voregsNo;
            this.voRenvlDate = voRenvlDate;
            this.shareCapitalshg = shareCapitalshg;
            this.genbankOpendata = genbankOpendata;
        }

        public VoProfile() {
        }

	public VoCutOffEntity getVocutoff() {
			return vocutoff;
		}

		public void setVocutoff(VoCutOffEntity vocutoff) {
			this.vocutoff = vocutoff;
		}

		public List<VoCutOffMonthlyEntity> getVocutoffmonthly() {
			return vocutoffmonthly;
		}

		public void setVocutoffmonthly(List<VoCutOffMonthlyEntity> vocutoffmonthly) {
			this.vocutoffmonthly = vocutoffmonthly;
		}
    }
