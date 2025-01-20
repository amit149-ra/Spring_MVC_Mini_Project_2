package com.mingo.services;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.mingo.entiry.CitiesMaster;
import com.mingo.entiry.CountryMaster;
import com.mingo.entiry.StateMaster;
import com.mingo.entiry.UserDetails;
import com.mingo.pojo.User;
import com.mingo.repo.CityRepository;
import com.mingo.repo.CountryRepository;
import com.mingo.repo.StateRepository;
import com.mingo.repo.UserRepository;
import com.mingo.utils.JavaEmailSender;

@Service
public class UserServiceImp implements UserServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private JavaEmailSender javeEmailSender;

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer, String> countryMap = new HashMap<Integer, String>();
		List<CountryMaster> listOfCountry = countryRepository.findAll();
		listOfCountry.forEach(entity -> countryMap.put(entity.getCountryId(), entity.getCountryName()));
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStateByCountryId(Integer countryId) {
		Map<Integer, String> mapOfState = new HashMap<Integer, String>();
		List<StateMaster> listOfState = stateRepository.findByCountryCountryId(countryId);
		listOfState.forEach(entity -> mapOfState.put(entity.getStateId(), entity.getStateName()));
		return mapOfState;
	}

	@Override
	public Map<Integer, String> loadCityByStateId(Integer stateId) {
		Map<Integer, String> mapOfCity = new HashMap<Integer, String>();
		List<CitiesMaster> listOfCity = cityRepository.findByStateStateId(stateId);
		listOfCity.forEach(entity -> mapOfCity.put(entity.getCityId(), entity.getCityName()));
		return mapOfCity;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		userRepository.findByUserEmail(email);

		return userRepository.findByUserEmail(email) != null ? false : true;
	}

	@Override
	public String generateTempPazzword() {
		// TODO Auto-generated method stub
		return "temporaryPassword";
	}

	@Override
	public boolean saveUserAccount(User user) {
		user.setAccStatus("LOCKED");
		user.setUserPassword(generateTempPazzword());
		UserDetails userEntity = new UserDetails();
		BeanUtils.copyProperties(user, userEntity);
		UserDetails savedEntity = userRepository.save(userEntity);
		if(savedEntity.getUserId() != null) {
			sendRegSuccMsg(user.getUserEmail(), "Verification Link", getSuccRegMsg(user));
			return true;
		}
		
		return false;

	}

	@Override
	public String getSuccRegMsg(User user) {
		try {
			String fileName = "templates/UNLOCK_ACCOUNT_EMAIL_TEMPLATE.txt";
			ClassPathResource resources = new ClassPathResource(fileName);
			String body = new String(Files.readAllBytes(Paths.get(resources.getURI())), StandardCharsets.UTF_8);
			body = body.replace("{{TEMP_PASSWORD}}", user.getUserPassword())
                    .replace("{{RESET_LINK}}", "http://localhost:8081/loadUnlockAccountForm?email="+user.getUserEmail())
                    .replace("{{USER_FIRST_NAME}}", user.getFirstName())
                    .replace("{{USER_LAST_NAME}}", user.getLastName());
			return body;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean sendRegSuccMsg(String to, String subject, String body) {
		return javeEmailSender.sendEmail(to, subject, body);

	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		return userRepository.findByUserEmailAndUserPassword(email, tempPwd) != null ? true : false;
	}

	@Override
	public boolean unlockAccount(String email, String password) {
		UserDetails user=userRepository.findByUserEmail(email);
		if(user!=null){
			user.setAccStatus("UNLOCKED");
			user.setUserPassword(password);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public String loginCheck(String email, String password) {
		UserDetails entity=userRepository.findByUserEmailAndUserPassword(email, password);
		if(entity==null) {
			return "invalid credential";
		}else if(entity.getAccStatus().equals("LOCKED")) {
			return "your Account it locked unlocked it from<a href=\"http://localhost:8081/loadUnlockAccountForm?email=\""+entity.getUserEmail()+"here</a>";
		}
		return "VALID";
	}

	@Override
	public String recoverPassword(String email) {
		UserDetails entity=userRepository.findByUserEmail(email);
		if(entity!=null) {
			User user=new User();
			BeanUtils.copyProperties(entity, user);
			String emailBody=getRecoveryEmailPasswordBody(user);
			String subject="Recover Password";
			sendPasswordToEmail(user.getUserEmail(), subject, emailBody);
			return "SUCCESS";
		}
		return "FAILED";
	}

	@Override
	public String getRecoveryEmailPasswordBody(User user) {
		try {
			String fileName = "templates/RECOVER_PASSWORD_EMAIL_TEMPLATE.txt";
			ClassPathResource resources = new ClassPathResource(fileName);
			String body = new String(Files.readAllBytes(Paths.get(resources.getURI())), StandardCharsets.UTF_8);
			body = body.replace("{{TEMP_PASSWORD}}", user.getUserPassword())
                    .replace("{{RESET_LINK}}", "http://localhost:8081/loadUnlockAccountForm?email="+user.getUserEmail())
                    .replace("{{USER_FIRST_NAME}}", user.getFirstName())
                    .replace("{{USER_LAST_NAME}}", user.getLastName());
			return body;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean sendPasswordToEmail(String to, String subject, String body) {
		return javeEmailSender.sendEmail(to, subject, body);
	}

}
