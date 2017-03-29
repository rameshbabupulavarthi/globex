package com.globex.controller;

import com.globex.model.entity.Message;
import com.globex.model.entity.common.Communication;
import com.globex.model.entity.common.File;
import com.globex.model.entity.common.Reminder;
import com.globex.model.vo.CommunicationDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.ReminderDO;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.security.CurrentUserDO;
import com.globex.service.CommunicationService;
import com.globex.service.MessageService;
import com.globex.service.ReminderService;
import com.globex.service.UserService;
import com.globex.service.pm.FileService;
import com.utils.AppConstants;
import com.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @Autowired
    FileService fileService;

    @Autowired
    CommunicationService communicationService;

    @Autowired
    ReminderService reminderService;

    @RequestMapping("/secure/dashboard")
    public ModelAndView userDashboard(){
        return getDashboardData();
    }

    @RequestMapping("/secure/getDashboardData")
    public ModelAndView getDashboardData(){
        String view ="globex/dashboard/dashboard";
        CurrentUserDO userDO=userService.getCurrentUserDO();

        Integer pageNo=0;
        Integer pageSize=AppConstants.DEFAULT_PAGE_SIZE;
        Integer pageSizeLimit=5;

        PageModel<Communication> pageModel=new PageModel<Communication>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        communicationService.list(pageModel);
        List<Communication> communications=pageModel.getContent();

        Page<File> fileInfoPage=fileService.list(pageNo,pageSizeLimit);
        List<File> fileInfoList=fileInfoPage.getContent();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        List<CommunicationDO> communicationDOs=new ArrayList<>();
        for(Communication communication:communications){
            CommunicationDO communicationDO=new CommunicationDO(communication);
            communicationDOs.add(communicationDO);
        }

        for(File fileInfo:fileInfoList){
            FileInfoDO fileInfoDO=new FileInfoDO(fileInfo);
            fileInfoDOs.add(fileInfoDO);
        }

        Page<Reminder> reminderPage=reminderService.list(pageNo,pageSizeLimit);
        List<ReminderDO> reminderDOs=new ArrayList<ReminderDO>();
        List<Reminder> reminders=reminderPage.getContent();
        for(Reminder reminder:reminders){
            ReminderDO reminderDO=new ReminderDO(reminder);
            reminderDOs.add(reminderDO);
        }

        Map<String, Object> dashboardMap = new HashMap<String, Object>();
        dashboardMap.put("pageNo",pageNo);
        dashboardMap.put("files",fileInfoDOs);
        dashboardMap.put("communications",communicationDOs);
        dashboardMap.put("reminders",reminderDOs);
        dashboardMap.put("currentUser",userDO);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user",userDO);
        model.put("dashboardJson",AppUtils.convertObjectTOJSONString(dashboardMap));
        return new ModelAndView(view, "model", model);
    }

    @RequestMapping("/secure/pmDashboard")
    public ModelAndView pmUserDashboard(){

        String view ="globex/pm/pmDashboard";
        CurrentUserDO userDO=userService.getCurrentUserDO();

        Integer pageNo=0;
        Integer pageSize=AppConstants.DEFAULT_PAGE_SIZE;
        Page<File> fileInfoPage=fileService.list(pageNo, pageSize);
        List<File> fileInfos=fileInfoPage.getContent();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        for(File fileInfo:fileInfos){

            FileInfoDO fileInfoDO=new FileInfoDO(fileInfo);
            fileInfoDOs.add(fileInfoDO);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("files",fileInfoDOs);
        model.put("user",userDO);
        return new ModelAndView(view, "model", model);
    }


    @RequestMapping("/secure/viewMessages")
    @ResponseBody
    public Map<String, Object> getMessages(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                     @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo+1;
        pageSize=pageSize==null?AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        PageModel<Communication> pageModel=new PageModel<Communication>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        communicationService.list(pageModel);
        List<Communication> communications=pageModel.getContent();

        List<CommunicationDO> communicationDOs=new ArrayList<>();
        for(Communication communication:communications){
            CommunicationDO communicationDO=new CommunicationDO(communication);
            communicationDOs.add(communicationDO);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("communications",communicationDOs);
        return model;
    }

    @RequestMapping("/secure/viewApplications")
    @ResponseBody
    public Map<String, Object> viewApplications(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                           @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null?AppConstants.DEFAULT_PAGE_SIZE:pageSize;

        Page<File> fileInfoPage=fileService.list(pageNo, pageSize);
        List<File> fileInfos=fileInfoPage.getContent();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        for(File fileInfo:fileInfos){
            FileInfoDO fileInfoDO=new FileInfoDO(fileInfo);
            fileInfoDOs.add(fileInfoDO);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("totalRecords",fileInfoPage.getTotalElements());
        model.put("files",fileInfoDOs);
        return model;
    }
}