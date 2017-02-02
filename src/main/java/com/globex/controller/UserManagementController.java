package com.globex.controller;

import com.globex.model.entity.user.User;
import com.globex.model.vo.UserDO;
import com.globex.repository.rdbms.UserRepository;
import com.globex.security.CurrentUserDO;
import com.globex.service.UserService;
import com.utils.AppConstants;
import com.utils.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

        String absoluteSaveFilePath =null;

        if (fileToUpload != null && fileToUpload.getBytes().length > 0) {
            String filename=fileToUpload.getOriginalFilename();
            filename = org.springframework.util.StringUtils.replace(filename, ",", "");
            Integer dot = filename.lastIndexOf(".");
            String extension = filename.substring(dot + 1);
            filename = filename.substring(0, dot);
            filename = filename + FileUtils.fileNameSeparator + currentUserId + "." + extension;
            absoluteSaveFilePath= FileUtils.absoluteFilePath + filename;

            File existingFile = new File(absoluteSaveFilePath);
            existingFile.delete();
            byte[] file = fileToUpload.getBytes();
            FileUtils fu = new FileUtils();
            boolean result = fu.saveBytesIntoFile(file, absoluteSaveFilePath, true);
            System.out.print("result : "+result);
        }
        userDO.setThumbnail(absoluteSaveFilePath);
        userService.saveUser(userDO);
        return "success";
    }

    @RequestMapping("/secure/getUsers")
    @ResponseBody
    public Map<String, Object> getUsers(@RequestParam(value = "pageNo",required=false) Integer pageNo,
                               @RequestParam(value = "pageSize", required=false) Integer pageSize){

        pageNo=pageNo==null?0:pageNo+1;
        pageSize=pageSize==null? AppConstants.DEFAULT_PAGE_SIZE:pageSize;
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageNo",pageNo);
        List<UserDO> users=userService.list(pageNo, pageSize);
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