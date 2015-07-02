package com.gzfgeh.briefnote.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by guzhenf on 7/1/2015.
 */
public interface DBUpdateListener {

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

}
