package com.mingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingo.constants.AppConstants;
import com.mingo.pojo.UnlockAccount;
import com.mingo.services.UserServices;

@Controller
public class UnlockAccountController {

    @Autowired
    private UserServices userServices;

    @ModelAttribute(AppConstants.USER_ACCOUNT)
    private UnlockAccount loadModelObject() {
        return new UnlockAccount();
    }

    @GetMapping("/loadUnlockAccountForm")
    public String loadUnlockAccountPage(@RequestParam(AppConstants.EMAIL_PARAMETER) String email, Model model) {
        UnlockAccount unlockAccount = new UnlockAccount();
        unlockAccount.setEmail(email);
        model.addAttribute(AppConstants.USER_ACCOUNT, unlockAccount);
        return "unlockPage"; // Ensure a corresponding template is available at "unlockPage"
    }

    @PostMapping("/handleUnlockAccount")
    public String handleUnlockAccountBtn(@ModelAttribute(AppConstants.USER_ACCOUNT) UnlockAccount unlockAccount, RedirectAttributes redirectAttributes) {
        if (userServices.isTempPwdValid(unlockAccount.getEmail(), unlockAccount.getTempPassword())) {
            if (userServices.unlockAccount(unlockAccount.getEmail(), unlockAccount.getNewPassword())) {
                redirectAttributes.addFlashAttribute(AppConstants.SUCCESS_MESSAGE, AppConstants.UNLOCK_ACC_CTR_SUCCESS_RESPONSE);
                return AppConstants.REDIRECT_TO_UNLOCK_ACC_WITH_EMAIL + unlockAccount.getEmail(); // Redirect to a valid page such as login
            }
        }
        redirectAttributes.addFlashAttribute(AppConstants.FAILED_MESSAGE, AppConstants.UNLOCK_ACC_CTR_FAILED_RESPONSE);
        return AppConstants.REDIRECT_TO_UNLOCK_ACC_WITH_EMAIL + unlockAccount.getEmail();
    }
}
