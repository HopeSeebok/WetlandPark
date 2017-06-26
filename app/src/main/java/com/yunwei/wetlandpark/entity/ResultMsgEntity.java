package com.yunwei.wetlandpark.entity;

import java.util.List;

/**
 * Created by SlientWhale on 2016/9/13.
 */
public class ResultMsgEntity {

    int code;

    String msg;//提醒信息

    /**
     * 服务器返回附加数据 协定
     *      单个数据就在item中
     *      list数据就在data中获取
     */

    String  item;

    List<Object> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getDataString(){
        return data.toString();
    }
}
