package com.utils;

/**
 * Created by Sunil Golla on 6/18/2017.
 */
public class StringUtils {

    public static String getValue(String value){
        if(value!=null){
            return value.trim();
        }
        return null;
    }
}
