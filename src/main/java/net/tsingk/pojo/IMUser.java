package net.tsingk.pojo;

public abstract  class IMUser {

    protected String imUserId;

    protected String imUserToken;


    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }

    public String getImUserToken() {
        return imUserToken;
    }

    public void setImUserToken(String imUserToken) {
        this.imUserToken = imUserToken;
    }
}
