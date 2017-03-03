package com.globex.controller;

import com.globex.model.entity.common.Communication;
import com.globex.model.entity.pm.FileInfo;
import com.globex.model.vo.pm.FileInfoDO;
import com.globex.service.CommunicationService;
import com.globex.service.pm.FileService;
import com.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 2/20/2017.
 */
@Controller
public class CommunicationController {

    @Autowired
    CommunicationService communicationService;

    @RequestMapping("/secure/viewCommunications")
    @ResponseBody
    public Map<String, Object> viewCommunications(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                                  @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo+1;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;

        Page<Communication> communicationPage= communicationService.list(pageNo, pageSize);
        List<Communication> communications=communicationPage.getContent();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("communications",communications);
        return model;
    }
}
