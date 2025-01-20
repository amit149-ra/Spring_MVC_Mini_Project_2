package com.mingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		return "index";
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
		String email=req.getParameter("email");
		String password=req.getParameter("pwd");
		String status=services.loginCheck(email, password);
		if(status.equals("VALID")) {
			viewName="dashboard";
		}else {
			viewName="index";
			model.addAttribute("msg", status);
		}
		return viewName;
	}
}
