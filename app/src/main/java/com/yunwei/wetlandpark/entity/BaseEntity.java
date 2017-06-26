package com.yunwei.wetlandpark.entity;

import java.io.Serializable;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.entity
 * @Description:实体基类
 * @date 2016/10/25 20:47
 */

public class BaseEntity implements Serializable {

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
