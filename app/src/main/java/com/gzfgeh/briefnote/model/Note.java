package com.gzfgeh.briefnote.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by guzhenf on 7/8/2015.
 */
public class Note implements Serializable{

    private int id;
    private String title;
    private String content;
    private String url;
    private Date alertTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
