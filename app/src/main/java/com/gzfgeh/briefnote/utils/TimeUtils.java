package com.gzfgeh.briefnote.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guzhenf on 7/6/2015.
 */
public class TimeUtils {

    private TimeUtils(){

    }

    public static long timeFormatToLong(String s){
        SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");

        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }
}
