package com.globex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    @RequestMapping(value = "/secure/downloadFile", method = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(HttpServletRequest request,HttpServletResponse response,
                               @RequestParam(value = "filePath", required = false) String filePath) throws IOException {

        int BUFFER_SIZE = 4096;

        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + filePath;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }
}
