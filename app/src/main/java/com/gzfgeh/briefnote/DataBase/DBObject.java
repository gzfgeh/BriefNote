package com.gzfgeh.briefnote.database;

import org.litepal.crud.DataSupport;

import java.sql.Date;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class DBObject extends DataSupport {

    private String title;
    private String content;
    private String url;
    private Date createDate;
    private Date operateDate;
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

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }
}
