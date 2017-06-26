package com.yunwei.wetlandpark.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @Package: com.yunwei.zaina.entity
 * @Description:登录实体
 * @author: Aaron
 * @date: 2016-06-08
 * @Time: 15:29
 * @version: V1.0
 */
public class UserInfoEntity{
    private String access_token;

    private String token_type;

    private int expires_in;

    @SerializedName("id")
    @Expose
    private String Id;

    @SerializedName("icon")
    private String Icon;

    @SerializedName("name")
    private String name;

    @SerializedName("dept")
    private String dep;

    @SerializedName("group")
    private String group;

    @SerializedName("unitid")
    private String unitID;

    @SerializedName("unit")
    private String unitName;

    @SerializedName("Password")
    private String Password;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        this.Icon = icon;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setUnitID(String unitId){
        this.unitID = unitId;
    }

    public String getUnitID(){return this.unitID; }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPwd() {
        return Password;
    }

    public void setPwd(String pwd) {
        this.Password = pwd;
    }

}
