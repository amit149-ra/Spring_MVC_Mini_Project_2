package com.mingo.constants;

public interface AppConstants {
	
	
	public final static String DASHBOARD_VIEW ="dashboard";
	public final static String FORGET_VIEW="forgetPwd";
	public final static String INDEX_VIEW="index";
	public final static String REGISTRATION_FORM_VIEW="regForm";
	public final static String UNLOCK_PAGE_VIEW="unlockPage";
	
	public final static String EMAIL_PARAMETER="email";
	public final static String PWD_PARAMETER="pwd";
	public final static String SUCCESS="SUCCESS";
	public final static String FAILED="FAILED";
	public final static String VALID="VALID";
	
	public final static String SUCCESS_MESSAGE="SuccessMsg";
	public final static String FORGET_PASSWORD_SUCCESS_RESPONSE="Password send to your email";
	public final static String FAILED_MESSAGE="FailedMsg";
	public final static String FORGET_PASSWORD_FAILED_RESPONSE="please enter valid email ID";
	
	public final static String LOGIN_CTR_MSG_RESPONSE="msg";
	
	public final static String USER_ACCOUNT="userAcc";
	public final static String CONTRIES="countries";
	public final static String UNIQUE="UNIQUE";
	public final static String DUPLICATE="DUPLICATE";
	public final static String COUNTRY_ID="countryId";
	public final static String STATE_ID="stateId";
	public final static String CITY_ID="countryId";
	public final static String REG_CTR_SUCCESS_RESPONSE="registration successful";
	public final static String REG_CTR_FAILED_RESPONSE="registration failed";
	
	public final static String REDIRECT_TO_UNLOCK_ACC_WITH_EMAIL="redirect:/loadUnlockAccountForm?email=";
	public final static String UNLOCK_ACC_CTR_SUCCESS_RESPONSE="Password changed successfully. <a href=\"index\">Login here</a>";
	public final static String UNLOCK_ACC_CTR_FAILED_RESPONSE="Invalid temporary password or failed to unlock account";
	
	
	
	public final static String TEMPORARY_PASSWORD="temporaryPassword";
	public final static String NEW_ACC_STATUS="LOCKED";
	public final static String AFTER_VERIFICATION_ACC_STATUS="UNLOCKED";
	public final static String EMAIL_TITLE_FOR_VERIFICATION_EMAIL="Verification Link";
	public final static String EMAIL_TITLE_FOR_FORGOT_PAZZWORD="Recover Password";
	public final static String VERIFICATION_EMAIL_TEMPLATE_TXT="templates/UNLOCK_ACCOUNT_EMAIL_TEMPLATE.txt";
	public final static String RECOVERY_EMAIL_TEMPLATE_TXT="templates/RECOVER_PASSWORD_EMAIL_TEMPLATE.txt";
	public final static String VERIFICATION_EMAIL_TEMPLATE_TEMP_PASSWORD="{{TEMP_PASSWORD}}";
	public final static String VERIFICATION_EMAIL_TEMPLATE_RESET_LINK="{{RESET_LINK}}";
	public final static String VERIFICATION_EMAIL_TEMPLATE_RESET_LINK_VALUE_WITH_EMAIL="http://localhost:8081/loadUnlockAccountForm?email=";
	public final static String VERIFICATION_EMAIL_TEMPLATE_USER_FIRST_NAME="{{USER_FIRST_NAME}}";
	public final static String VERIFICATION_EMAIL_TEMPLATE_USER_LAST_NAME="{{USER_LAST_NAME}}";
	public final static String LOGIN_CHECK_FAILED_MSG="invalid credential";
	
	
}
