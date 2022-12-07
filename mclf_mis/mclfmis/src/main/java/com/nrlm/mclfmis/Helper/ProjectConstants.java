package com.nrlm.mclfmis.Helper;

public class ProjectConstants {
	public static final String AUTHORIZATION_HDR = "Authorization";
	public static final String IS_AUTHORIZE_URL = "N";
	// public static final String VALIDATE_TOKEN_URL =
	// "http://AUTH-SERVICE/auth/validateToken";
	public static final String VALIDATE_TOKEN_URL = "http://MCLF-SERVICE/mclf/auth/validateToken";

//	public static final String VALIDATE_TOKEN_URL = "http://3.6.120.64:8587/auth/validateToken";

	public static String UNAUTHORIZED = "UNAUTHORIZED";
	public static String ERROR = "error";
	public static String WENT_WRONG = "Something went wrong";
	public static String USER_ADDED = "User added";
	public static String USER_UPDATED = "User updated";
	public static String NOT_FOUND = "Not found";
	public static String SUCCESS = "Successfull";
	public static String FAILIURE = "Failed";

	// Response code
	public static String OK_200 = "200";
	public static String BAD_REQUEST_400 = "400";
	public static String UNAUTHORIZED_401 = "401";
	public static String NOT_FOUND_404 = "404";
	public static String METHOD_NOT_ALLOWED_405 = "405";
	public static String TOO_MANY_REQUEST_429 = "429";
	public static String INTERNAL_SERVER_ERROR_500 = "500";
	public static String BAD_GATEWAY_502 = "502";

	public static String LOGIN_STATUS = "Active";
	public static String FLAG_Y = "Y";
	public static String FLAG_N = "N";

	public static String STATE = "state";
	public static String DISTRICT = "district";
	public static String BLOCK = "block";
	public static String CLF = "clf";

	public static final Integer ACTIVE_STATUS = 1;
	public static final Integer INACTIVE_STATUS = 0;
	public static final String SECTION_TABLE = "mclf_mst_section";
	public static final String SECTION_COL_SHORT_KEY = "tab_type";
	public static final String INDICATOR_TABLE = "mclf_mst_indicator";
	public static final String INDICATOR_COL_SHORT_KEY = "indctr_type";

	public static final String NATIONAL = "national";
	public static final Integer TAB_CLF = 1;
	public static final Integer TAB_VO = 2;
	public static final Integer TAB_GP = 3;

	public static final Integer INDCTR_TYPE_INTEGER = 1;
	public static final Integer INDCTR_TYPE_TEXT = 2;
	public static final Integer INDCTR_TYPE_DROPDOWN = 3;
	public static final Integer INDCTR_TYPE_RADIO = 4;
	public static final Integer INDCTR_TYPE_DECIMAL = 5;
	public static final Integer INDCTR_TYPE_DATE = 6;
	public static final String CUT_OFF = "cut_off";
	public static final String CUT_OFF_SHORT_KEY = "indicator_";
	public static final Integer IS_DRAFT = 1;
	public static final Integer PENDING = 1;
	public static final Integer IN_PROGRESS = 2;
	public static final Integer COMPLETED = 3;
	public static final String IN_PROGRESS_TEXT = "In Progress";
	public static final String PENDING_TEXT = "Pending";
	public static final String COMPLETED_TEXT = "Completed";
	public static final String SUPER_ADMIN = "super_admin";

	public static final Integer BASICPROFILE_PENDING = 1;
	public static final Integer BASICPROFILE_IN_PROGRESS = 2;
	public static final Integer BASICPROFILE_COMPLETED = 3;
	public static final String PAST = "P";
	public static final String FUTURE = "F";
	public static final String CURRENT = "C";
	public static final Integer CUTOFF_START_YEAR_2022 = 1;
	public static final Integer CUTOFF_START_MONTH_APR = 4;
	public static final String CUTOFF_START_DATE = "2022-03-01";
	public static final String CUTOFF_FINANCIAL_START_DATE = "2021-04-01";
	public static final Integer SKIP_NOT_APPLIED = 2;
	public static final Integer SKIP_APPLIED = 1;

	public static final Integer MULTISELECT_DROPDOWN = 7;
	public static final Integer DECEMBER = 12;
	public static final Integer MARCH = 3;
	public static final Integer CUT_OFF_START_YEAR_ID = 5;
	public static final Integer YES = 1;
	public static final Integer NO = 2;
	public static final Integer Q1 = 1;
	public static final Integer Q2 = 2;
	public static final Integer Q3 = 3;
	public static final Integer Q4 = 4;
	
	public static final String Q1_TEXT = "Q1 (Apr-Jun)";
	public static final String Q2_TEXT = "Q2 (Jul-Sep)";
	public static final String Q3_TEXT = "Q3 (Oct-Dec)";
	public static final String Q4_TEXT = "Q4 (Jan-Mar)";
	public static final String FIILE_BASE_PATH = "/Users/hunny/Dhwani/MCLF_Logs/";
	//public static final String FIILE_BASE_PATH = "C:/Users/Admin/Desktop/MCLF_Logs/";
	
	public static final Integer STAFF_RSN_AnyOther = 8;
	public static final Integer CLFSTAFF = 2;

}
