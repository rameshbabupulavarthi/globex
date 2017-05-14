package com.utils;

import com.globex.model.entity.pm.Organization;
import com.globex.model.vo.OrganizationDO;
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

    public static void main(String args[]){
        convertJsonStringToObject();
    }

    public static Object convertJsonStringToObject(){

        String jsonString="{\"orgName\":\"o\",\"address1\":\"o\",\"address2\":\"o\",\"country\":\"o\",\"state\":\"o\",\"city\":\"\",\"zip\":\"\",\"website\":\"\",\n" +
                "users:\"[{userName:o,password:o,firstName:o,lastName:o,email:o,phoneCountryCode:,phoneAreaCode:o,phone:,faxCountryCode:,faxAreaCode:,fax:,mobileCountryCode:,mobile:,comments:}]\",\n" +
                "\"accountInfos\":\"[{firstName:o,lastName:o,email:o,phoneCountryCode:,phoneAreaCode:,phone:,faxCountryCode:,faxAreaCode:,fax:,mobileCountryCode:,mobile:,bankInfo:,comments:}]\",\n" +
                "\"coverageAreas\":\"[]\"}";
        ObjectMapper mapper = new ObjectMapper();
        OrganizationDO organization=null;
        try {
            organization= mapper.readValue(jsonString, OrganizationDO.class);
            System.out.print(organization);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            /*logger.error("JsonMappingException error",e);*/
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException error",e);
            e.printStackTrace();
        }
        return organization;
    }
}
