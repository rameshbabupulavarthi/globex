package com.utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sunil Golla on 1/11/2017.
 */
public class AppUtils {

    private static Logger logger = LoggerFactory.getLogger(AppUtils.class);

    public static String getDomainName(String url){
        if(!url.startsWith("http") && !url.startsWith("https")){
            url = "http://" + url;
        }
        URL netUrl;
        try{
            netUrl = new URL(url);
        }catch(MalformedURLException ex){
            throw new RuntimeException("Requested url is not suppoted ",ex);
        }
        String host = netUrl.getHost();
        if(host.startsWith("www")){
            host = host.substring("www".length()+1);
        }
        return host;
    }

    public static String convertObjectTOJSONString(Object object){
        if(object == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException error",e);
        } catch (IOException e) {
            logger.error("IOException error",e);
        }
        return null;
    }
}
