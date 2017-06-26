package com.yunwei.wetlandpark.entity.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by longma on 2016/7/19.
 */

public class ImagesBean  {
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    /**
     * Index : 1
     * Uri : sample string 2
     * Note : sample string 3
     */
    @SerializedName("Index")
    @Expose
    private int ImagesIndex;
    private String Uri;
    private String Note;

    public int getImagesIndex() {
        return ImagesIndex;
    }

    public void setImagesIndex(int imagesIndex) {
        ImagesIndex = imagesIndex;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String Uri) {
        this.Uri = Uri;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    private String TaskId;

    public String getTaskId() {
        return TaskId;
    }
    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    private ItemsBean itemsBean;

    public ItemsBean getItemsBean() {
        return itemsBean;
    }

    public void setItemsBean(ItemsBean itemsBean) {
        this.itemsBean = itemsBean;
    }
}
