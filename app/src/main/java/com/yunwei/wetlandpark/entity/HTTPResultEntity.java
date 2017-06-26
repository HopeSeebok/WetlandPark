package com.yunwei.wetlandpark.entity;

/**
 * @author huangyue
 * @version V1.0
 * @Package com.yunwei.cmcc.entity
 * @Description: 接收HTTP Response Body
 * @date 2016/11/04 09:55
 */

public class HTTPResultEntity {

    private boolean success;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return "HTTPResultEntity{" +
                "success=" + success +
                ", msg='" + message + '\'' +
                '}';
    }
}
