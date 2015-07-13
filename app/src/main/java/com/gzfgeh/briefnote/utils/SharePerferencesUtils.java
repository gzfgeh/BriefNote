package com.gzfgeh.briefnote.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePerferencesUtils {
    public final static String SETTING = "Setting";
    
    private SharePerferencesUtils(){
    	
    }
    
    public static void putValue(Context context,int resKey, int value) {
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putInt(context.getString(resKey), value);
         sp.commit();  
    }  
    public static void putValue(Context context,int resKey, boolean value) {
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putBoolean(context.getString(resKey), value);
         sp.commit();  
    }  
    public static void putValue(Context context,int resKey, String value) {
         Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();  
         sp.putString(context.getString(resKey), value);
         sp.commit();  
    }  
    
    public static int getValue(Context context,int resKey, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        int value = sp.getInt(context.getString(resKey), defValue);
        return value;  
    }

    public static boolean getValue(Context context,int resKey, boolean defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        boolean value = sp.getBoolean(context.getString(resKey), defValue);
        return value;  
    }

    public static String getValue(Context context,int resKey, String defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);  
        String value = sp.getString(context.getString(resKey), defValue);
        return value;  
    }

    public static int getValue(Context context,String resKey, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(resKey, defValue);
        return value;
    }
}  
