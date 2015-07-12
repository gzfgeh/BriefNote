package com.gzfgeh.briefnote.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.gzfgeh.briefnote.model.Note;

/**
 * Created by guzhenf on 7/12/2015.
 */
public class JsonUtils {

    public static <T> String json(T note){
        if (note == null)
            return "";

        try{
            return JSON.toJSONString(note);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T parse(String json, Class<T> clazz){
        if (TextUtils.isEmpty(json))
            return null;
        try {
            return JSON.parseObject(json, clazz);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Note parseNote(String json){
        return parse(json, Note.class);
    }

    public static String jsonNote(Note note){
        return json(note);
    }
}
