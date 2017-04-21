package com.globex.controller;

import com.globex.model.entity.user.User;
import com.globex.model.vo.PageModel;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.UserRepository;
import com.globex.security.CurrentUserDO;
import com.globex.service.UserService;
import com.utils.AppConstants;
import com.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunil Golla on 1/12/2017.
 */
@Controller
public class UserManagementController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/secure/createUser")
    public ModelAndView createUser(){
        Map<String, Object> model = new HashMap<String, Object>();
        return new ModelAndView("/globex/login", "model", model);
    }


    @RequestMapping("/secure/saveUser")
    @ResponseBody
    public String saveUser(HttpServletRequest request, @ModelAttribute("userData")UserDO userDO){

        CurrentUserDO currentUserDO=userService.getCurrentUserDO();
        Long currentUserId=currentUserDO.getUserId();

        CommonsMultipartFile fileToUpload=userDO.getThumbnailFile();
        if (fileToUpload != null && fileToUpload.getBytes().length > 0) {
            String rootPath = request.getServletContext().getRealPath("/");//System.getProperty("catalina.home");
            String relativePath =FileUtils.uploadFile(fileToUpload,currentUserId,rootPath);
            userDO.setThumbnail(relativePath);
        }
        userService.saveUser(userDO);
        return "success";
    }

    @RequestMapping("/secure/getUsers")
    @ResponseBody
    public Map<String, Object> getUsers(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                               @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        Map<String,Object> dataMap=userService.list(pageNo, pageSize);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("totalRecords",dataMap.get("totalRecords"));
        model.put("users",dataMap.get("users"));
        return model;
    }

    @RequestMapping("/secure/findUsers")
    @ResponseBody
    public Map<String, Object> findUsers(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                                        @RequestParam(value = "pageSize", required=false) Integer pageSize,
                                        @RequestParam(value = "email", required=false) String email ){
        pageNo=pageNo==null?0:pageNo;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        Map<String,Object> filters=new HashMap<String, Object>();
        filters.put("email",email);
        PageModel<UserDO> pageModel=new PageModel<UserDO>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setFilters(filters);

        PageModel<UserDO> fileInfoPage=userService.list(pageModel);
        List<UserDO> users=fileInfoPage.getContent();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        model.put("pageSize",pageSize);
        model.put("totalRecords",fileInfoPage.getTotalRecords());
        model.put("users",users);
        return model;
    }

    @RequestMapping("/secure/editUser")
    @ResponseBody
    public UserDO editUser(@RequestParam(value = "userId",required=false) Long userId){
        UserDO userDO=userService.getUser(userId);
        return userDO;
    }


    @RequestMapping("/secure/deleteUser")
    @ResponseBody
    public void deleteUser(@RequestParam(value = "userId",required=false) Long userId){
        userRepository.delete(userId);
    }

}