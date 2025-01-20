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

import com.mingo.pojo.User;
import com.mingo.services.UserServices;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserServices userServices;
	
	@ModelAttribute
	private void loadModelObject(Model model) {
		User user=new User();
		model.addAttribute("userAcc", user);
		model.addAttribute("countries",userServices.loadCountries());
	}

	@GetMapping("/regForm")
	private String loadRegForm(Model model) {
		return "regForm";
	}

	/**
	 * @responsebody is use to send the response instead of presentation file
	 * @param email
	 * @return
	 */
	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isMailUnique(@RequestParam("email") String email) {
		return userServices.isUniqueEmail(email)?"UNIQUE":"DUPLICATE";
	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam("countryId") Integer countryId) {
		Map<Integer, String> response = userServices.loadStateByCountryId(countryId);
		return response;
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvnt(@RequestParam("stateId") Integer stateId) {
		Map<Integer, String> response = userServices.loadCityByStateId(stateId);
		return response;
	}

	@PostMapping("/registerUserbtn")
	public String handleRegBtn(User user, RedirectAttributes model) {
		
		if(userServices.saveUserAccount(user)) {
			model.addFlashAttribute("SuccessMsg","registration successful");
			return "redirect:/regForm";
		}
		model.addFlashAttribute("FailedMsg","registration failed");
		return "redirect:/regForm";
	}
}
