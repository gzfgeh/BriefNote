package com.gzfgeh.briefnote.utils;

import android.content.Context;

import com.gzfgeh.briefnote.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guzhenf on 7/6/2015.
 */
public class TimeUtils {

    public static final SimpleDateFormat DATE_FORMAT_DATE_1    = new SimpleDateFormat(" HH : mm ");

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

    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getConciseTime(long timeInMillis, long nowInMillis, Context context) {
        if (context == null)
            return "";
        Date date = new Date(timeInMillis);
        Date now = new Date(nowInMillis);

        if (now.getYear() == date.getYear()) {
            if (now.getMonth() == date.getMonth()) {
                if (now.getDate() == date.getDate())
                    return context.getString(R.string.today, getTime(timeInMillis, DATE_FORMAT_DATE_1));
                else{
                    return context.getString(R.string.before_day, now.getDate() - date.getDate());
                }
            }else {
                return context.getString(R.string.before_month, now.getMonth() - date.getMonth());
            }
        }
        return context.getString(R.string.before_year, now.getYear() - date.getYear());
    }

    public static String getConciseTime(long timeInMillis, Context context) {
        return getConciseTime(timeInMillis, getCurrentTimeInLong(), context);
    }

    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }
}
