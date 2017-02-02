package com.globex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ramesh on 20-12-2016.
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "/secure/upload", method = RequestMethod.POST)
    @ResponseBody
    public String processForm(/*@ModelAttribute(value="FORM") FileUploadBean form,*/
                              BindingResult result, String path, HttpServletRequest request,
                              @RequestParam(value = "chunk", required = false) Long chunk,
                              @RequestParam(value = "chunks", required = false) Long chunks,
                              @RequestParam(value = "fileName", required = false) String fileName,
                              @RequestParam(value = "fileType", required = false) String fileType,
                              @RequestParam(value = "forceDownload", required = false) Boolean forceDownload) {

        return "";
    }
}
