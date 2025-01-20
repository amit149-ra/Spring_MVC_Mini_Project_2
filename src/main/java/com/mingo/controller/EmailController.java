package com.mingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingo.utils.JavaEmailSender;

@RestController
public class EmailController {

//    @Autowired
//    private JavaEmailSender emailService;
//
//    @GetMapping("/send-email")
//    public String sendEmail(
//            @RequestParam String to,
//            @RequestParam String subject,
//            @RequestParam String text) {
//        emailService.sendSimpleEmail(to, subject, text);
//        return "Email sent successfully!";
//    }
}
