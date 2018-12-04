package net.tsingk.pojo;

import java.util.Date;

public class Device {

    private long id;

    private String hwid;

    private String masterHwId;

    private String wifiSid;

    private int status;

    private Date regTime;

    private Date lastRptTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHwid() {
        return hwid;
    }

    public void setHwid(String hwid) {
        this.hwid = hwid;
    }

    public String getMasterHwId() {
        return masterHwId;
    }

    public void setMasterHwId(String masterHwId) {
        this.masterHwId = masterHwId;
    }

    public String getWifiSid() {
        return wifiSid;
    }

    public void setWifiSid(String wifiSid) {
        this.wifiSid = wifiSid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastRptTime() {
        return lastRptTime;
    }

    public void setLastRptTime(Date lastRptTime) {
        this.lastRptTime = lastRptTime;
    }
}
