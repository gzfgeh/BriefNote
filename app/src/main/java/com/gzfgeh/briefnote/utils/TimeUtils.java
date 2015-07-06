package com.gzfgeh.briefnote.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guzhenf on 7/6/2015.
 */
public class TimeUtils {

    private TimeUtils(){

    }

    public static long timeFormatToLong(String time){
        if (TextUtils.isEmpty(time.trim()))
            return 0;

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date dt2 = null;
        try {
            dt2 = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //继续转换得到秒数的long型
        return dt2.getTime() / 1000;
    }
}
