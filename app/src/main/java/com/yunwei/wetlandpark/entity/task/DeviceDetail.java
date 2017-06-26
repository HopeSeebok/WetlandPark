package com.yunwei.wetlandpark.entity.task;

import java.util.List;

/**
 * Created by lx on 2016/9/14.
 */

public class DeviceDetail{
    /**
     * x : 1.1
     * y : 2.1
     * z : 3.1
     * rfid : sample string 1
     * code : sample string 2
     * facilitytype : sample string 3
     * facilityname : sample string 4
     * lat : 5.1
     * lng : 6.1
     * username : sample string 7
     * lastupadatetime : sample string 8
     * unitname : sample string 9
     * remark : sample string 10
     * address : sample string 11
     * road : sample string 12
     * status : 13
     * pianqu : sample string 14
     * attrjson : sample string 15
     * id : sample string 16
     * objectid : 17
     * userid : 18
     * unitid : 19
     * createtime : sample string 20
     */

    private ItemBean Item;
    /**
     * Uri : sample string 1
     * AttachType : 201
     */

    private List<AttachsBean> Attachs;
    /**
     * Index : 1
     * Uri : sample string 2
     * Note : sample string 3
     */

    private List<ImagesBean> Images;

    public ItemBean getItem() {
        return Item;
    }

    public void setItem(ItemBean Item) {
        this.Item = Item;
    }

    public List<AttachsBean> getAttachs() {
        return Attachs;
    }

    public void setAttachs(List<AttachsBean> Attachs) {
        this.Attachs = Attachs;
    }

    public List<ImagesBean> getImages() {
        return Images;
    }

    public void setImages(List<ImagesBean> Images) {
        this.Images = Images;
    }

    public static class ItemBean {
        private double x;
        private double y;
        private double z;
        private String rfid;
        private String code;
        private String facilitytype;
        private String type;
        private String facilityname;
        private double lat;
        private double lng;
        private String username;
        private String lastupadatetime;
        private String unitname;
        private String remark;
        private String address;
        private String road;
        private int status;
        private String pianqu;
        private String attrjson;
        private String id;
        private int objectid;
        private int userid;
        private int unitid;
        private String createtime;

        public String getType() {
            return type;
        }

        public ItemBean setType(String type) {
            this.type = type;
            return this;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getZ() {
            return z;
        }

        public void setZ(double z) {
            this.z = z;
        }

        public String getRfid() {
            return rfid;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getFacilitytype() {
            return facilitytype;
        }

        public void setFacilitytype(String facilitytype) {
            this.facilitytype = facilitytype;
        }

        public String getFacilityname() {
            return facilityname;
        }

        public void setFacilityname(String facilityname) {
            this.facilityname = facilityname;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLastupadatetime() {
            return lastupadatetime;
        }

        public void setLastupadatetime(String lastupadatetime) {
            this.lastupadatetime = lastupadatetime;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPianqu() {
            return pianqu;
        }

        public void setPianqu(String pianqu) {
            this.pianqu = pianqu;
        }

        public String getAttrjson() {
            return attrjson;
        }

        public void setAttrjson(String attrjson) {
            this.attrjson = attrjson;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getObjectid() {
            return objectid;
        }

        public void setObjectid(int objectid) {
            this.objectid = objectid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getUnitid() {
            return unitid;
        }

        public void setUnitid(int unitid) {
            this.unitid = unitid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }

    public static class AttachsBean {
        private String Uri;
        private int AttachType;

        public String getUri() {
            return Uri;
        }

        public void setUri(String Uri) {
            this.Uri = Uri;
        }

        public int getAttachType() {
            return AttachType;
        }

        public void setAttachType(int AttachType) {
            this.AttachType = AttachType;
        }
    }

    public static class ImagesBean {
        private int Index;
        private String Uri;
        private String Note;

        public int getIndex() {
            return Index;
        }

        public void setIndex(int Index) {
            this.Index = Index;
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
    }

}
