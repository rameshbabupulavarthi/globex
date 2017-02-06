package com.globex.controller;

import com.globex.constants.Role;
import com.globex.security.CurrentUserDO;
import com.globex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ramesh on 17-12-2016.
 */
@Controller
public class WelcomeController {

    @Autowired
    UserService userService;


    @RequestMapping("/secure/welcome")
    public String welcome(HttpServletRequest request){

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        CurrentUserDO userDO = userService.getCurrentUserDO();
        String redirectUrl="/secure/dashboard";
        if(userDO.getCurrentUserRole().equals(Role.ROLE_GLOBEX.getRoleName())){
            redirectUrl="/secure/dashboard";
        }else if(userDO.getCurrentUserRole().equals(Role.ROLE_PM_USER.getRoleName())){
            redirectUrl="/secure/pmDashboard";
        }else if(userDO.getCurrentUserRole().equals(Role.ROLE_LM_USER.getRoleName())){
            redirectUrl="/secure/partnerRegistrationForm";
        }
        return "redirect:"+redirectUrl;
    }
}
