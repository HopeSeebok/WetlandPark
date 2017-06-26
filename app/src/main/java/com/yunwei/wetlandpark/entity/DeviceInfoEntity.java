package com.yunwei.wetlandpark.entity;

import com.yunwei.library.utils.IStringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.entity
 * @Description: 从图层上获取 设备的信息
 * @date 2016/7/28 14:23
 * @Check
 */

public class DeviceInfoEntity implements Serializable {

    private Device Item;

    private List<Images> Images;

    public void setItem(Device Item) {
        this.Item = Item;
    }

    public Device getItem() {
        return this.Item;
    }

    public void setImages(List<Images> Images) {
        this.Images = Images;
    }

    public List<Images> getImages() {
        return this.Images;
    }

    public class Images implements Serializable {

        private int Index;

        private String Uri;

        private String Note;

        public void setIndex(int Index) {
            this.Index = Index;
        }

        public int getIndex() {
            return this.Index;
        }

        public void setUri(String Uri) {
            this.Uri = Uri;
        }

        public String getUri() {
            return this.Uri;
        }

        public void setNote(String Note) {
            this.Note = Note;
        }

        public String getNote() {
            return this.Note;
        }

    }

    public class Device implements Serializable {
        private String id;//notice

        private String guid;

        private String code;

        private String oldcode;//旧的设施编码

        private String category;//摄像头类别

        private String addrcode;//球机地址码

        private String  monitorcode;//监控室编号

        private String lampcode;//杆编号

        private String ownerpolice;//所属警务局

        private String owner;//所属派出所

        private String owneroffice;//所属分局

        private int height;//杆高度

        private String type;

        private double x;

        private double y;

        private double z;

        private int userid;

        private String username;

        private String createtime;

        private String unitid;

        private String unitname;

        private int zoneid;

        private String zonename;//片区

        private String remark;

        private String address;

        private String description;//描述

        private String state;

        private int objectid;

        private int pointid;


        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setGuid(String rfid) {
            this.guid = rfid;
        }

        public String getGuid() {
            return this.guid;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

        public void setFacilitytype(String facilitytype) {
            this.type = facilitytype;
        }

        public String getFacilitytype() {
            return this.type;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getX() {
            return this.x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getY() {
            return this.y;
        }

        public void setZ(double z) {
            this.z = z;
        }

        public double getZ() {
            return this.z;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public void setUserid(String userid) {
            this.userid = IStringUtils.toInt(userid);
        }

        public int getUserid() {
            return this.userid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return this.username;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCreatetime() {
            return this.createtime;
        }

        public void setUnitid(String unitid) {
            this.unitid = unitid;
        }

        public String getUnitid() {
            return this.unitid;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public String getUnitname() {
            return this.unitname;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return this.remark;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return this.address;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStatus() {
            return this.state;
        }

        public void setObjectid(int objectid) {
            this.objectid = objectid;
        }

        public int getObjectid() {
            return this.objectid;
        }

        public void setPointid(int pointid) {
            this.pointid = pointid;
        }

        public int getPointid() {
            return this.pointid;
        }

        public String getOldCode() {
            return oldcode;
        }

        public void setOldCode(String oldCode) {
            this.oldcode = oldCode;
        }

        public String getCameraCategory() {
            return category;
        }

        public void setCameraCategory(String cameraCategory) {
            this.category = cameraCategory;
        }

        public String getAddrCode() {
            return addrcode;
        }

        public void setAddrCode(String addrCode) {
            this.addrcode = addrCode;
        }

        public String getLampCode() {
            return lampcode;
        }

        public void setLampCode(String lampCode) {
            this.lampcode = lampCode;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getZoneName() {
            return zonename;
        }

        public void setZoneName(String zonename) {
            this.zonename = zonename;
        }

        public int getZoneID() {
            return zoneid;
        }

        public void setZoneID(int zoneid) {
            this.zoneid = zoneid;
        }

        public String getMonitorcode() {
            return monitorcode;
        }

        public void setMonitorcode(String monitorcode) {
            this.monitorcode = monitorcode;
        }

        public String getOwnerpolice() {
            return ownerpolice;
        }

        public void setOwnerpolice(String ownerpolice) {
            this.ownerpolice = ownerpolice;
        }

        public String getOwneroffice() {
            return owneroffice;
        }

        public void setOwneroffice(String owneroffice) {
            this.owneroffice = owneroffice;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
