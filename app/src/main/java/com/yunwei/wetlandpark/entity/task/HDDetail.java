package com.yunwei.wetlandpark.entity.task;

import java.util.List;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.entity.task
 * @Description:
 * @date 2016/9/26
 * @changeby:
 */

public class HDDetail {

    /**
     * guid : sample string 1
     * objectid : 2
     * x : 3.1
     * y : 4.1
     * z : 5.1
     * userid : 6
     * unitid : 7
     * createtime : sample string 8
     * pointid : 9
     * code : sample string 1
     * hdtype : sample string 2
     * hdplace : sample string 3
     * hddesc : sample string 4
     * hdstate : sample string 5
     * usrename : sample string 6
     * usertel : sample string 7
     * hdhandler : sample string 8
     * facilitycode : sample string 9
     * facilitytype : sample string 10
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
        private String guid;
        private int objectid;
        private double x;
        private double y;
        private double z;
        private int userid;
        private int unitid;
        private String createtime;
        private int pointid;
        private String code;
        private String hdtype;
        private String hdplace;
        private String hddesc;
        private String hdstate;
        private String usrename;
        private String usertel;
        private String hdhandler;
        private String facilitycode;
        private String facilitytype;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public int getObjectid() {
            return objectid;
        }

        public void setObjectid(int objectid) {
            this.objectid = objectid;
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

        public int getPointid() {
            return pointid;
        }

        public void setPointid(int pointid) {
            this.pointid = pointid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getHdtype() {
            return hdtype;
        }

        public void setHdtype(String hdtype) {
            this.hdtype = hdtype;
        }

        public String getHdplace() {
            return hdplace;
        }

        public void setHdplace(String hdplace) {
            this.hdplace = hdplace;
        }

        public String getHddesc() {
            return hddesc;
        }

        public void setHddesc(String hddesc) {
            this.hddesc = hddesc;
        }

        public String getHdstate() {
            return hdstate;
        }

        public void setHdstate(String hdstate) {
            this.hdstate = hdstate;
        }

        public String getUsrename() {
            return usrename;
        }

        public void setUsrename(String usrename) {
            this.usrename = usrename;
        }

        public String getUsertel() {
            return usertel;
        }

        public void setUsertel(String usertel) {
            this.usertel = usertel;
        }

        public String getHdhandler() {
            return hdhandler;
        }

        public void setHdhandler(String hdhandler) {
            this.hdhandler = hdhandler;
        }

        public String getFacilitycode() {
            return facilitycode;
        }

        public void setFacilitycode(String facilitycode) {
            this.facilitycode = facilitycode;
        }

        public String getFacilitytype() {
            return facilitytype;
        }

        public void setFacilitytype(String facilitytype) {
            this.facilitytype = facilitytype;
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
