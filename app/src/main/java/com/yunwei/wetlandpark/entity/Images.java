package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lx on 2016/7/26.
 */
public class Images implements Serializable{
    @SerializedName("Id")
    private String Index;

    @SerializedName("Url")
    private String Url;

    public String getIndex() {
        return Index;
    }

    public void setIndex(String index) {
        Index = index;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}