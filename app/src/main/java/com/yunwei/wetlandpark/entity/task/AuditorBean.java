package com.yunwei.wetlandpark.entity.task;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by longma on 2016/7/14.
 */

public  class AuditorBean  implements Parcelable {
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    @SerializedName("Id")
    @Expose
    private String AuditorId;
    private String Name;

    public String getAuditorId() {
        return AuditorId;
    }

    public void setAuditorId(String Id) {
        this.AuditorId = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AuditorId);
        dest.writeString(this.Name);
    }

    public AuditorBean() {
    }

    protected AuditorBean(Parcel in) {
        this.AuditorId = in.readString();
        this.Name = in.readString();
    }

    public static final Creator<AuditorBean> CREATOR = new Creator<AuditorBean>() {
        public AuditorBean createFromParcel(Parcel source) {
            return new AuditorBean(source);
        }

        public AuditorBean[] newArray(int size) {
            return new AuditorBean[size];
        }
    };

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