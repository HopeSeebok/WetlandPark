package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.entity
 * @Mouble 公共模块
 * @Description: 从后台获取的配置文件的对象
 * @date 2016/8/24 16:24
 */
public class ConfigEntity implements Serializable {

    public static String FLAG = "ConfigEntity";
    public static String KEY = "ConfigValue";

    public ConfigEntity( ) { }

    @SerializedName("cameratype")
    private List<ConfigValue> cameraTypeList;//摄像头类型集合

    @SerializedName("cameracategory")
    List<ConfigValue> cameraCategoryList;//摄像头类别集合

    @SerializedName("subordinatebranch")
    List<ConfigValue> subordinateBranch;//所属分局集合

    @SerializedName("belongarea")
    List<ConfigValue> belongAreaList;//所属分区的集合

    @SerializedName("crewInfo")
    List<ConfigValue> crewList;//组员列表

    @SerializedName("faulttype")
    List<ConfigValue> hiddenTypeList;//隐患类型

    public List<ConfigValue> getCameraType() {
        return cameraTypeList;
    }

    public void setCameraType(List<ConfigValue> cameraTypeList) {
        this.cameraTypeList = cameraTypeList;
    }

    public List<ConfigValue> getCameraCategory() {
        return cameraCategoryList;
    }

    public void setCameraCategory(List<ConfigValue> cameraCategoryList) {
        this.cameraCategoryList = cameraCategoryList;
    }

    public List<ConfigValue> getOwnerOffice() {
        return subordinateBranch;
    }

    public void setOwnerOffice(List<ConfigValue> subordinateBranch) {
        this.subordinateBranch = subordinateBranch;
    }

    public List<ConfigValue> getZone() {
        return belongAreaList;
    }

    public void setZone(List<ConfigValue> belongAreaList) {
        this.belongAreaList = belongAreaList;
    }


    //设置组员信息 {Name": "郑龙胜","Value": "35"}
    public List<ConfigValue> getCrewList() {
        return crewList;
    }

    //获取组员信息
    public void setCrewList(List<ConfigValue> crewList) {
        this.crewList = crewList;
    }

    public List<ConfigValue> getHiddenTypeList() {
        return hiddenTypeList;
    }

    public void setHiddenTypeList(List<ConfigValue> hiddenTypeList) {
        this.hiddenTypeList = hiddenTypeList;
    }

    //选项对象
    public static class ConfigValue implements Serializable {
        @SerializedName("Name")
        String nameuhuiho;

        @SerializedName("Value")
        String valuedsff;

        public ConfigValue() {
        }

        public ConfigValue(String nameuhuiho, String valuedsff) {
            this.nameuhuiho = nameuhuiho;
            this.valuedsff = valuedsff;
        }

        public String getName() {
            return nameuhuiho;
        }

        public void setName(String name) {
            this.nameuhuiho = name;
        }

        public String getValue() {
            return valuedsff;
        }

        public void setValue(String value) {
            this.valuedsff = value;
        }
    }

}
