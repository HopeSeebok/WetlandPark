package com.yunwei.wetlandpark.entity.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by longma on 2016/7/11.
 */

public class ExecutorBean {
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    @SerializedName("Id")
    @Expose
    private String ExecutorId;
    private String Name;

    public String getExecutorId() {
        return ExecutorId;
    }

    public void setExecutorId(String Id) {
        this.ExecutorId = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    private ItemsBean itemsBean;

    public ItemsBean getItemsBean() {
        return itemsBean;
    }

    public void setItemsBean(ItemsBean itemsBean) {
        this.itemsBean = itemsBean;
    }

    private String TaskId;

    public String getTaskId() {
        return TaskId;
    }
    public void setTaskId(String taskId) {
        TaskId = taskId;
    }
}

