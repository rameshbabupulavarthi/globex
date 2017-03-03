package com.globex.controller;

import com.globex.model.entity.Message;
import com.globex.model.entity.common.Communication;
import com.globex.model.entity.pm.FileInfo;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.security.CurrentUserDO;
import com.globex.service.CommunicationService;
import com.globex.service.MessageService;
import com.globex.service.UserService;
import com.globex.service.pm.FileService;
import com.utils.AppConstants;
import com.utils.AppUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.integration.support.json.JsonObjectMapper;
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

        Page<Communication> communicationPage= communicationService.list(pageNo, pageSize);
        List<Communication> communications=communicationPage.getContent();

        Page<FileInfo> fileInfoPage=fileService.list(pageNo, pageSize);
        List<FileInfo> fileInfoList=fileInfoPage.getContent();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        for(FileInfo fileInfo:fileInfoList){
            FileInfoDO fileInfoDO=new FileInfoDO(fileInfo);
            fileInfoDOs.add(fileInfoDO);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("files",fileInfoDOs);
        model.put("communications",communications);
        model.put("user",userDO);
        return new ModelAndView(view, "model", model);
    }

    @RequestMapping("/secure/pmDashboard")
    public ModelAndView pmUserDashboard(){

        String view ="globex/pm/pmDashboard";
        CurrentUserDO userDO=userService.getCurrentUserDO();

        Integer pageNo=0;
        Integer pageSize=AppConstants.DEFAULT_PAGE_SIZE;
        Page<FileInfo> fileInfoPage=fileService.list(pageNo, pageSize);
        List<FileInfo> fileInfos=fileInfoPage.getContent();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        for(FileInfo fileInfo:fileInfos){

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
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        List<Message> messages=messageService.list(pageNo, pageSize);
        model.put("messages",messages);
        return model;
    }

    @RequestMapping("/secure/viewApplications")
    @ResponseBody
    public Map<String, Object> viewApplications(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                           @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null?AppConstants.DEFAULT_PAGE_SIZE:pageSize;

        Page<FileInfo> fileInfoPage=fileService.list(pageNo, pageSize);
        List<FileInfo> fileInfos=fileInfoPage.getContent();
        List<FileInfoDO> fileInfoDOs=new ArrayList<FileInfoDO>();
        for(FileInfo fileInfo:fileInfos){
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