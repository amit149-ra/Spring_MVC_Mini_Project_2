package com.mingo.services;

import java.util.Map;

import com.mingo.pojo.User;

public interface UserServices {
	public String loginCheck(String email,String password);
	public Map<Integer, String> loadCountries();
	public Map<Integer, String> loadStateByCountryId(Integer countryId);
	public Map<Integer, String> loadCityByStateId(Integer stateId);
	public boolean isUniqueEmail(String email);
	public String generateTempPazzword();
	public boolean saveUserAccount(User user);
	public String getSuccRegMsg(User user);
	public boolean sendRegSuccMsg(String to,String subject,String body);
	public boolean isTempPwdValid(String email,String tempPwd);
	public boolean unlockAccount(String email,String password);
	public String recoverPassword(String email);
	public String getRecoveryEmailPasswordBody(User user);
	public boolean sendPasswordToEmail(String to,String sunject,String body);
	
}
