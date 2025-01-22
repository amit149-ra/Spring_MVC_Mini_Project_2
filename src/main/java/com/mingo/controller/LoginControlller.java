package com.mingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mingo.constants.AppConstants;
import com.mingo.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginControlller {
	@Autowired
	private UserServices services;
	/*
	 * this controller is use to manage login page
	 */
	@GetMapping(value= {"/","index"})
	public String index() {
		return AppConstants.INDEX_VIEW;
	}
	/**
	 * this mathod is use to handle login button request
	 * @param req
	 * @param model
	 * @return string
	 */
	
	@PostMapping("/signin")
	public String handleSignInBtn(HttpServletRequest req,Model model) {
		String viewName="";
		String email=req.getParameter(AppConstants.EMAIL_PARAMETER);
		String password=req.getParameter(AppConstants.PWD_PARAMETER);
		String status=services.loginCheck(email, password);
		if(status.equals(AppConstants.VALID)) {
			viewName=AppConstants.DASHBOARD_VIEW;
		}else {
			viewName=AppConstants.INDEX_VIEW;
			model.addAttribute(AppConstants.LOGIN_CTR_MSG_RESPONSE, status);
		}
		return viewName;
	}
}
