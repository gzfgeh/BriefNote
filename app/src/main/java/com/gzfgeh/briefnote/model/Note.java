package com.gzfgeh.briefnote.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by guzhenf on 7/8/2015.
 */
public class Note extends DataSupport implements Serializable{

    private String title;
    private String content;
    private String url;
    private long lastOptTime;
    private Date alertTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public long getLastOptTime() {
        return lastOptTime;
    }

    public void setLastOptTime(long lastOptTime) {
        this.lastOptTime = lastOptTime;
    }

}
