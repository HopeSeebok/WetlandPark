package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush;

import java.util.ArrayList;

/**
 * Created by longma on 2016/7/15.
 */

public class MessageBean  {
    int code;
//    ArrayList<String> item;
    ArrayList<Integer> data;
    String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

//    public ArrayList<String> getItem() {
//        return item;
//    }
//
//    public void setItem(ArrayList<String> item) {
//        this.item = item;
//    }
}
