package com.yunwei.wetlandpark.entity;

import java.util.ArrayList;

/**
 * Created by zls on 2016/9/23.
 *
 * @Description:
 */

public class SearchEntity {
    String code;
    String msg;
    ArrayList<MapSearchEntity> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<MapSearchEntity> getData() {
        return data;
    }

    public void setData(ArrayList<MapSearchEntity> data) {
        this.data = data;
    }
}
