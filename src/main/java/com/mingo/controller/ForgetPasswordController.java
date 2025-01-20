package com.mingo.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mingo.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ForgetPasswordController {
	@Autowired
	private UserServices userServices;

	@GetMapping("/forgetPassword")
	public String loadForgetPasswordPage() {
		return "forgetPwd";
	}
	@PostMapping("/forgetPasswordBtn")
	public String handleForgetPasswordBtn(HttpServletRequest req ,Model model) {
		String email=req.getParameter("email");
		String status=userServices.recoverPassword(email);
		if(status.equals("SUCCESS")) {
			model.addAttribute("SuccessMsg","Password send to your email");
			return "forgetPwd";
		}
		model.addAttribute("FailedMsg","please enter valid email ID");
		return "forgetPwd";
	}
}
