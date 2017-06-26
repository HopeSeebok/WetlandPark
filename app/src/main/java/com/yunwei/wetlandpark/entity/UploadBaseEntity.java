package com.yunwei.wetlandpark.entity;

import java.util.List;

/**
 * Created by SEEBOK on 2016/9/12.
 */
public abstract class UploadBaseEntity {
    private List<Images> Images;

    public List<com.yunwei.wetlandpark.entity.Images> getImages() {
        return Images;
    }

    public void setImages(List<com.yunwei.wetlandpark.entity.Images> images) {
        Images = images;
    }
}
