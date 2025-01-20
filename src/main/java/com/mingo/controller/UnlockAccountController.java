package com.mingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingo.pojo.UnlockAccount;
import com.mingo.services.UserServices;

@Controller
public class UnlockAccountController {

    @Autowired
    private UserServices userServices;

    @ModelAttribute("userAcc")
    private UnlockAccount loadModelObject() {
        return new UnlockAccount();
    }

    @GetMapping("/loadUnlockAccountForm")
    public String loadUnlockAccountPage(@RequestParam("email") String email, Model model) {
        UnlockAccount unlockAccount = new UnlockAccount();
        unlockAccount.setEmail(email);
        model.addAttribute("userAcc", unlockAccount);
        return "unlockPage"; // Ensure a corresponding template is available at "unlockPage"
    }

    @PostMapping("/handleUnlockAccount")
    public String handleUnlockAccountBtn(@ModelAttribute("userAcc") UnlockAccount unlockAccount, RedirectAttributes redirectAttributes) {
        if (userServices.isTempPwdValid(unlockAccount.getEmail(), unlockAccount.getTempPassword())) {
            if (userServices.unlockAccount(unlockAccount.getEmail(), unlockAccount.getNewPassword())) {
                redirectAttributes.addFlashAttribute("SuccessMsg", "Password changed successfully. <a href=\"index\">Login here</a>");
                return "redirect:/loadUnlockAccountForm?email=" + unlockAccount.getEmail(); // Redirect to a valid page such as login
            }
        }
        redirectAttributes.addFlashAttribute("FailedMsg", "Invalid temporary password or failed to unlock account");
        return "redirect:/loadUnlockAccountForm?email=" + unlockAccount.getEmail();
    }
}
