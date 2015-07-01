package com.gzfgeh.briefnote.DataBase;

import android.content.Context;

/**
 * Created by guzhenf on 7/1/2015.
 */
public class DBConfig {

    private Context context;
    private String dbName = "BriefNote.db";
    private int version = 1;
    private DBUpdateListener dbUpdateListener;
    private String targetDirectory;             //数据库文件在SD卡中的目录

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DBUpdateListener getDbUpdateListener() {
        return dbUpdateListener;
    }

    public void setDbUpdateListener(DBUpdateListener dbUpdateListener) {
        this.dbUpdateListener = dbUpdateListener;
    }

    public String getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }
}
