package com.gzfgeh.briefnote.DataBase;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

/**
 * Created by guzhenf on 7/1/2015.
 */
public class FinalDb {

    private static HashMap<String, FinalDb> map = new HashMap<>();
    private SQLiteDatabase db;
    private DBConfig config;

}
