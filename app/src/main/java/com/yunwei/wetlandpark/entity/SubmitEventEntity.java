package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.entity
 * @Description:
 * @date 2016/8/25 15:31
 */
public class SubmitEventEntity implements Serializable{

    @SerializedName("Attributes")
    private Attributes attributes;

    @SerializedName("Images")
    private List<Images> images;

    @SerializedName("Geometry")
    private Geometry geometry;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
