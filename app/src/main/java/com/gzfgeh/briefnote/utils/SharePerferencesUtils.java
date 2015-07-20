package com.gzfgeh.briefnote.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gzfgeh.briefnote.APP;

public class SharePerferencesUtils {
    public final static String SETTING = "Setting";
    
    private SharePerferencesUtils(){
    	
    }
    
    public static void putValue(int resKey, int value) {
         Context context = APP.getContext();
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
         sp.putInt(context.getString(resKey), value);
         sp.commit();  
    }  
    public static void putValue(int resKey, boolean value) {
        Context context = APP.getContext();
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putBoolean(context.getString(resKey), value);
         sp.commit();  
    }

    public static void putValue(int resKey, String value) {
        Context context = APP.getContext();
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putString(context.getString(resKey), value);
         sp.commit();  
    }

    public static void putValue(int resKey, long value) {
        Context context = APP.getContext();
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putLong(context.getString(resKey), value);
        sp.commit();
    }

    public static void putValue(String resKey, long value) {
        Context context = APP.getContext();
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putLong(resKey, value);
        sp.commit();
    }

    public static void putValue(String resKey, int value) {
        Context context = APP.getContext();
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(resKey, value);
        sp.commit();
    }

    public static void putValue(String resKey, boolean value) {
        Context context = APP.getContext();
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(resKey, value);
        sp.commit();
    }

    public static void putValue(String resKey, String value) {
        Context context = APP.getContext();
        Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(resKey, value);
        sp.commit();
    }

    public static int getValue(int resKey, int defValue) {
        Context context = APP.getContext();
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        int value = sp.getInt(context.getString(resKey), defValue);
        return value;  
    }

    public static boolean getValue(int resKey, boolean defValue) {
        Context context = APP.getContext();
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        boolean value = sp.getBoolean(context.getString(resKey), defValue);
        return value;  
    }

    public static String getValue(int resKey, String defValue) {
        Context context = APP.getContext();
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        String value = sp.getString(context.getString(resKey), defValue);
        return value;  
    }

    public static int getValue(String resKey, int defValue) {
        Context context = APP.getContext();
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(resKey, defValue);
        return value;
    }

    public static String getValue(String resKey, String defValue) {
        Context context = APP.getContext();
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(resKey, defValue);
        return value;
    }

    public static long getValue(String resKey, long defValue) {
        Context context = APP.getContext();
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        long value = sp.getLong(resKey, defValue);
        return value;
    }
}  
