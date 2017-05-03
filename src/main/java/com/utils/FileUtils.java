package com.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by Sunil Golla on 1/31/2017.
 */
public class FileUtils {

    public static final String fileNameSeparator = "-";

    public static final String FILE_PATH = "/webresources/tmp/images/";

    public boolean saveBytesIntoFile(byte[] b, String fileName, boolean append) {
        try {
            File f = new File(fileName);
            File parentDir = f.getParentFile();
            if (!parentDir.exists()){
                boolean isSuccess = parentDir.mkdirs();
                if(!isSuccess){
                    throw new IOException("Could not able to create the directory");
                }
            }
            FileOutputStream fos = new FileOutputStream(fileName, append);
            fos.write(b);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static File[] getFilesInDirectoryByPattern(String directoryLocation, final String prefix, final String suffix){
        if(prefix==null && suffix==null){
            return null;
        }
        File dir = new File(directoryLocation);
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(prefix!=null){
                    return name.startsWith(prefix);
                } else {
                    return name.endsWith(suffix);
                }
            }
        };
        return dir.listFiles(filenameFilter);
    }

    public static String uploadThumbnail(CommonsMultipartFile fileToUpload,Long currentUserId,String absolutePath){
        String filename=fileToUpload.getOriginalFilename();
        filename = org.springframework.util.StringUtils.replace(filename, ",", "");
        Integer dot = filename.lastIndexOf(".");
        String extension = filename.substring(dot + 1);
        filename = filename.substring(0, dot);
        filename = filename + FileUtils.fileNameSeparator + currentUserId + "." + extension;
        String relativePath=FileUtils.FILE_PATH + filename;
        String absoluteSaveFilePath= absolutePath+relativePath;

        File existingFile = new File(absoluteSaveFilePath);
        existingFile.delete();
        byte[] file = fileToUpload.getBytes();
        FileUtils fu = new FileUtils();
        boolean result = fu.saveBytesIntoFile(file, absoluteSaveFilePath, true);
        System.out.print("result : "+result);
        return relativePath;
    }

    public static String uploadFile(CommonsMultipartFile fileToUpload,String absolutePath){
        String filename=fileToUpload.getOriginalFilename();
        /*filename = org.springframework.util.StringUtils.replace(filename, ",", "");
        Integer dot = filename.lastIndexOf(".");
        String extension = filename.substring(dot + 1);
        filename = filename.substring(0, dot);
        filename = filename + FileUtils.fileNameSeparator + currentUserId + "." + extension;*/
        String relativePath=FileUtils.FILE_PATH + filename;
        String absoluteSaveFilePath= absolutePath+relativePath;

        byte[] file = fileToUpload.getBytes();
        FileUtils fu = new FileUtils();
        boolean result = fu.saveBytesIntoFile(file, absoluteSaveFilePath, true);
        System.out.print("result : "+result);
        return relativePath;
    }
}
