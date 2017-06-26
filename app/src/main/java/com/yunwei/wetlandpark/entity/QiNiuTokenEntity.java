package com.yunwei.wetlandpark.entity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.entity
 * @Description:
 * @date 2016/12/15 16:44
 */

public class QiNiuTokenEntity {

    private String Token;
    private String Bucket;
    private String Domain;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getBucket() {
        return Bucket;
    }

    public void setBucket(String bucket) {
        Bucket = bucket;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }
}
