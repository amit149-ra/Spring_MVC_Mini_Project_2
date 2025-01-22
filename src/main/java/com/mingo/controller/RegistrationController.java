package com.mingo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingo.constants.AppConstants;
import com.mingo.pojo.User;
import com.mingo.services.UserServices;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserServices userServices;
	
	@ModelAttribute
	private void loadModelObject(Model model) {
		User user=new User();
		model.addAttribute(AppConstants.USER_ACCOUNT, user);
		model.addAttribute(AppConstants.CONTRIES,userServices.loadCountries());
	}

	@GetMapping("/regForm")
	private String loadRegForm(Model model) {
		return AppConstants.REGISTRATION_FORM_VIEW;
	}

	/**
	 * @responsebody is use to send the response instead of presentation file
	 * @param email
	 * @return
	 */
	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isMailUnique(@RequestParam(AppConstants.EMAIL_PARAMETER) String email) {
		return userServices.isUniqueEmail(email)?AppConstants.UNIQUE:AppConstants.DUPLICATE;
	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam(AppConstants.COUNTRY_ID) Integer countryId) {
		Map<Integer, String> response = userServices.loadStateByCountryId(countryId);
		return response;
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvnt(@RequestParam(AppConstants.STATE_ID) Integer stateId) {
		Map<Integer, String> response = userServices.loadCityByStateId(stateId);
		return response;
	}

	@PostMapping("/registerUserbtn")
	public String handleRegBtn(User user, RedirectAttributes model) {
		
		if(userServices.saveUserAccount(user)) {
			model.addFlashAttribute(AppConstants.SUCCESS_MESSAGE,AppConstants.REG_CTR_SUCCESS_RESPONSE);
			return "redirect:/regForm";
		}
		model.addFlashAttribute(AppConstants.FAILED_MESSAGE,AppConstants.REG_CTR_FAILED_RESPONSE);
		return "redirect:/"+AppConstants.REGISTRATION_FORM_VIEW;
	}
}
