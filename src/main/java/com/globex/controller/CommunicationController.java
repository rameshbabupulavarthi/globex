package com.globex.controller;

import com.globex.model.entity.common.Communication;
import com.globex.model.entity.common.Note;
import com.globex.model.vo.CommunicationDO;
import com.globex.model.vo.NoteDO;
import com.globex.model.vo.PageModel;
import com.globex.security.CurrentUserDO;
import com.globex.service.CommunicationService;
import com.globex.service.UserService;
import com.globex.constants.AppConstants;
import com.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    UserService userService;

    @RequestMapping("/secure/viewCommunications")
    @ResponseBody
    public Map<String, Object> viewCommunications(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                                  @RequestParam(value = "pageSize", required=false) Integer pageSize){


        pageNo=pageNo==null?0:pageNo+1;
        pageSize=pageSize==null?AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        PageModel<CommunicationDO> pageModel=new PageModel<CommunicationDO>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        communicationService.list(pageModel);
        List<CommunicationDO> communications=pageModel.getContent();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("communications",communications);
        return model;
    }

    @RequestMapping("/secure/getFileComments")
    @ResponseBody
    public Map<String, Object> getFileComments(@RequestParam(value = "fileId",required=true) Long fileId){

        List<Communication> communications= communicationService.listById(fileId);
        List<CommunicationDO> communicationDOs=new ArrayList<>();
        for(Communication communication:communications){
            CommunicationDO communicationDO=new CommunicationDO(communication);
            communicationDOs.add(communicationDO);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("communications",communicationDOs);
        return model;
    }

    @RequestMapping("/secure/postComments")
    @ResponseBody
    public String postComments(@RequestParam(value = "fileId",required=true) Long fileId,
                                            @RequestParam(value = "comment",required=true) String comment){

        return communicationService.saveComments(fileId,comment);
    }

    @RequestMapping("/secure/sendMessage")
    @ResponseBody
    public String sendMessage(HttpServletRequest request, @ModelAttribute("messageData")CommunicationDO communication){

        CurrentUserDO currentUserDO=userService.getCurrentUserDO();
        Long currentUserId=currentUserDO.getUserId();
        CommonsMultipartFile fileToUpload=communication.getAttachment();
        if (fileToUpload != null && fileToUpload.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath =FileUtils.uploadFile(fileToUpload,/*currentUserId,*/rootPath);
            communication.setFileAttachment(relativePath);
        }
        communicationService.saveComment(communication);
        return "success";
    }

    @RequestMapping("/secure/getFileNotes")
    @ResponseBody
    public Map<String, Object> getFileNotes(@RequestParam(value = "fileId",required=true) Long fileId){

        List<Note> notes= communicationService.listNotesByFile(fileId);
        List<NoteDO> noteDOs=new ArrayList<>();
        for(Note note:notes){
            NoteDO noteDO=new NoteDO(note);
            noteDOs.add(noteDO);
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("notes",noteDOs);
        return model;
    }

    @RequestMapping("/secure/postNote")
    @ResponseBody
    public String postNote(@RequestParam(value = "fileId",required=true) Long fileId,
                           @RequestParam(value = "information",required=false) String information,
                           @RequestParam(value = "note",required=true) String note){

        NoteDO noteDO=new NoteDO();
        noteDO.setFileId(fileId);
        noteDO.setInformation(information);
        noteDO.setNotes(note);
        return communicationService.saveNote(noteDO);
    }

}
