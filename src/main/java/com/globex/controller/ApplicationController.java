package com.globex.controller;

import com.globex.model.entity.common.File;
import com.globex.model.vo.ExposureDataDO;
import com.globex.model.vo.LocalBrokerInsuredContactDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.pm.ApplicationDO;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.service.pm.FileService;
import com.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 4/1/2017.
 */
@Controller
public class ApplicationController {

    @Autowired
    FileService fileService;

    @RequestMapping("/secure/viewApplications")
    @ResponseBody
    public Map<String, Object> viewApplications(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                                @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;

        PageModel<FileInfoDO> pageModel=new PageModel<FileInfoDO>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);

        PageModel<FileInfoDO> fileInfoPage=fileService.list(pageModel);
        List<FileInfoDO> fileList=fileInfoPage.getContent();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("totalRecords",fileInfoPage.getTotalRecords());
        model.put("files",fileList);
        return model;
    }

    @RequestMapping("/secure/submitApplication")
    @ResponseBody
    public ApplicationDO submitApplication(HttpServletRequest request, @ModelAttribute("userData")ApplicationDO applicationDO){

        fileService.save(applicationDO);
        return applicationDO;
    }

    @RequestMapping("/secure/submitExposureData")
    @ResponseBody
    public List<ExposureDataDO> submitExposureData(HttpServletRequest request, @ModelAttribute("userData")ExposureDataDO exposureData){
        /*ApplicationDO application=exposureData.getApplication();
        Integer applicationId=application.getApplicationId();
        List<ExposureDataDO> exposureList=fileService.getExposureData(applicationId);
        return exposureList;*/
        return null;
    }

    @RequestMapping("/secure/submitLocalInsuranceData")
    @ResponseBody
    public LocalBrokerInsuredContactDO submit(HttpServletRequest request, @ModelAttribute("userData")LocalBrokerInsuredContactDO localBrokerInsuredContact){
        return localBrokerInsuredContact;
    }

}
