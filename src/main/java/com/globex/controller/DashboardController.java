package com.globex.controller;

import com.globex.model.entity.Message;
import com.globex.security.CurrentUserDO;
import com.globex.service.MessageService;
import com.globex.service.UserService;
import com.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ramesh on 17-12-2016.
 */
@Controller
public class DashboardController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @RequestMapping("/secure/dashboard")
    public ModelAndView userDashboard(){
        return getDashboardData();
    }

    @RequestMapping("/secure/getDashboardData")
    public ModelAndView getDashboardData(){

        String view ="globex/dashboard/dashboard";
        Map<String, Object> model = new HashMap<String, Object>();
        CurrentUserDO userDO=userService.getCurrentUserDO();
        model.put("user",userDO);
        return new ModelAndView(view, "model", model);
    }


    @RequestMapping("/secure/viewMessages")
    @ResponseBody
    public Map<String, Object> getMessages(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                     @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo+1;
        pageSize=pageSize==null?AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        List<Message> messages=messageService.list(pageNo, pageSize);
        model.put("messages",messages);
        return model;
    }




}
