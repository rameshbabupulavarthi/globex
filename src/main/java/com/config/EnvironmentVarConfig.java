package com.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Sunil Golla on 1/31/2017.
 */
@Configuration
public class EnvironmentVarConfig {

    public static final String FILE_UPLOAD_PATH="";
    public static String getUploadPath(){
        String path="";
        if(System.getProperty(FILE_UPLOAD_PATH)!=null){
            path=System.getProperty(FILE_UPLOAD_PATH);
        }
        return path;
    }
}



