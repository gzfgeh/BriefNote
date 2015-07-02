package com.gzfgeh.briefnote;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import cn.bmob.v3.Bmob;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class APP extends LitePalApplication {

    private SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, "ff52ea8fbec04dfc826eef0ea661ea66");

        if (db != null)
            db = Connector.getDatabase();

    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}
