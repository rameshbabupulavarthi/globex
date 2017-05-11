package com.globex.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globex.constants.Role;
import com.globex.model.entity.common.File;
import com.globex.model.entity.pm.Organization;
import com.globex.model.entity.user.User;
import com.globex.model.vo.ExposureDataDO;
import com.globex.model.vo.LocalBrokerInsuredContactDO;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.pm.ApplicationDO;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.service.UserService;
import com.globex.service.pm.FileService;
import com.utils.AppConstants;
import com.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Sunil Golla on 4/1/2017.
 */
@Controller
public class ApplicationController {

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @RequestMapping("/secure/viewApplications")
    @ResponseBody
    public Map<String, Object> viewApplications(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                                @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;

        PageModel<FileInfoDO> pageModel=new PageModel<FileInfoDO>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);

        User user=userService.getCurrentUser();
        Role roleType=Role.getValue(user.getUserType());
        if(Role.ROLE_GLOBEX==roleType || Role.ROLE_GLOBEX_ADMIN==roleType){
            Organization organization=user.getOrganization();
            Map<String,Object> filters=new HashMap<String,Object>();
            filters.put("orgId",organization.getId());
            pageModel.setFilters(filters);
        }
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
    public ApplicationDO submitApplication(HttpServletRequest request, @ModelAttribute("userData")ApplicationDO applicationDO) throws Exception{

        String exposureJson=applicationDO.getExposureJson();
        String localBrokerInsuredContactsJson=applicationDO.getLocalBrokerInsuredContactsJson();

        ObjectMapper mapper = new ObjectMapper();
        Set<ExposureDataDO> exposureData=null;
        Set<LocalBrokerInsuredContactDO> localBrokerInsuredContacts=null;
        if(exposureJson!=null && !exposureJson.isEmpty()){
            exposureData=mapper.readValue(exposureJson, new TypeReference<Set<ExposureDataDO>>() {});
        }
        if(localBrokerInsuredContactsJson!=null && !localBrokerInsuredContactsJson.isEmpty()){
            localBrokerInsuredContacts=mapper.readValue(localBrokerInsuredContactsJson, new TypeReference<Set<LocalBrokerInsuredContactDO>>() {});
        }
        applicationDO.setExposureDatas(exposureData);
        applicationDO.setLocalBrokerInsuredContacts(localBrokerInsuredContacts);

        FileInfoDO fileInfo=new FileInfoDO();
        fileInfo.setFileId(applicationDO.getFileId());
        applicationDO.setFileInfo(fileInfo);
        String rootPath = request.getServletContext().getRealPath("/");
        fileService.save(applicationDO,rootPath);
        applicationDO.setAttachment(null);
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
