package com.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sunil Golla on 5/3/2017.
 */
public class DateUtil {

    public static String formatDate(Timestamp timestamp){
        String dateStr=null;
        if(timestamp!=null) {
            Date date = new Date(timestamp.getTime());
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            dateStr=format.format(date);
        }
        return dateStr;
    }

    public static String formatDate(Date date){
        String dateStr=null;
        if(date!=null) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            dateStr=format.format(date);
        }
        return dateStr;
    }

    public static Timestamp getTimestamp(String dateStr){
        Timestamp timestamp=null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try{
            Date date=format.parse(dateStr);
            timestamp=new Timestamp(date.getTime());
        }catch (Exception e){

        }
       return timestamp;
    }

    public static java.sql.Date getDate(String dateStr){
        java.sql.Date sqlDate=null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try{
            Date date=format.parse(dateStr);
            sqlDate=new java.sql.Date(date.getTime());
        }catch (Exception e){

        }
        return sqlDate;
    }
}
