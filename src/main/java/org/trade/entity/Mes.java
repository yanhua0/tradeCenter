package org.trade.entity;

import java.util.Date;

public class Mes {
    private Integer id;

    private String message;

    private Integer receieveid;

    private String sendname;

    private Integer state;

    private Date sendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getReceieveid() {
        return receieveid;
    }

    public void setReceieveid(Integer receieveid) {
        this.receieveid = receieveid;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname == null ? null : sendname.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Mes(String message, Integer receieveid, String sendname, Integer state) {
        this.message = message;
        this.receieveid = receieveid;
        this.sendname = sendname;
        this.state = state;
    }

    public Mes() {

    }
}