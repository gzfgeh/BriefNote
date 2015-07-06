package com.gzfgeh.briefnote.database;

import java.sql.Date;

import cn.bmob.v3.BmobObject;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class DBObject extends BmobObject {

    private String title;
    private String content;
    private String url;
    private Date alertTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }
}
