package com.yunwei.wetlandpark.entity.task;

/**
 * Created by longma on 2016/7/13.
 */

public class LocationBean {
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    private double Lng;
    private double Lat;
    private String Name;
    private String UpdateTime;

    public double getLng() {
        return Lng;
    }

    public void setLng(double Lng) {
        this.Lng = Lng;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double Lat) {
        this.Lat = Lat;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
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
