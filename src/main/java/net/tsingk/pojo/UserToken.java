package net.tsingk.pojo;

import java.util.Date;

public class UserToken {

    private String token;

    private int type;

    private long userId;

    private long gtime;

    private long etime;

    private Date gctime;

    private Date ectime;

    private String imToken;

    private String imId;
    private String openId;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGtime() {
        return gtime;
    }

    public void setGtime(long gtime) {
        this.gtime = gtime;
    }

    public long getEtime() {
        return etime;
    }

    public void setEtime(long etime) {
        this.etime = etime;
    }

    public Date getGctime() {
        return gctime;
    }

    public void setGctime(Date gctime) {
        this.gctime = gctime;
    }

    public Date getEctime() {
        return ectime;
    }

    public void setEctime(Date ectime) {
        this.ectime = ectime;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
