package com.mingo.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mingo.constants.AppConstants;
import com.mingo.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ForgetPasswordController {
	@Autowired
	private UserServices userServices;

	@GetMapping("/forgetPassword")
	public String loadForgetPasswordPage() {
		return AppConstants.FORGET_VIEW;
	}
	@PostMapping("/forgetPasswordBtn")
	public String handleForgetPasswordBtn(HttpServletRequest req ,Model model) {
		String email=req.getParameter(AppConstants.EMAIL_PARAMETER);
		String status=userServices.recoverPassword(email);
		if(status.equals(AppConstants.SUCCESS)) {
			model.addAttribute(AppConstants.SUCCESS_MESSAGE,AppConstants.FORGET_PASSWORD_SUCCESS_RESPONSE);
			return "forgetPwd";
		}
		model.addAttribute(AppConstants.FAILED_MESSAGE,AppConstants.FORGET_PASSWORD_FAILED_RESPONSE);
		return AppConstants.FORGET_VIEW;
	}
}
