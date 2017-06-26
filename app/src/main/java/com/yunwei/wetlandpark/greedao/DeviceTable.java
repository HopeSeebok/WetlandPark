package com.yunwei.wetlandpark.greedao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "DEVICE_TABLE".
 */
public class DeviceTable implements java.io.Serializable {

    private Long id;
    private String deviceID;
    private String RFID;
    private String deviceCode;
    private String deviceAddress;
    private Integer deviceTypeCode;
    private String deviceTypeName;
    private Double lng;
    private Double lat;
    private Long createTime;
    private String imagesUrl;
    private Integer localTag;
    private String userID;
    private String userName;
    private Integer unitID;
    private String contact;
    private String phone;
    private String startTime;
    private String stopTime;
    private String useDepartment;
    private String ext;

    public DeviceTable() {
    }

    public DeviceTable(Long id) {
        this.id = id;
    }

    public DeviceTable(Long id, String deviceID, String RFID, String deviceCode, String deviceAddress, Integer deviceTypeCode, String deviceTypeName, Double lng, Double lat, Long createTime, String imagesUrl, Integer localTag, String userID, String userName, Integer unitID, String contact, String phone, String startTime, String stopTime, String useDepartment, String ext) {
        this.id = id;
        this.deviceID = deviceID;
        this.RFID = RFID;
        this.deviceCode = deviceCode;
        this.deviceAddress = deviceAddress;
        this.deviceTypeCode = deviceTypeCode;
        this.deviceTypeName = deviceTypeName;
        this.lng = lng;
        this.lat = lat;
        this.createTime = createTime;
        this.imagesUrl = imagesUrl;
        this.localTag = localTag;
        this.userID = userID;
        this.userName = userName;
        this.unitID = unitID;
        this.contact = contact;
        this.phone = phone;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.useDepartment = useDepartment;
        this.ext = ext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getRFID() {
        return RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Integer getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(Integer deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public Integer getLocalTag() {
        return localTag;
    }

    public void setLocalTag(Integer localTag) {
        this.localTag = localTag;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUnitID() {
        return unitID;
    }

    public void setUnitID(Integer unitID) {
        this.unitID = unitID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getUseDepartment() {
        return useDepartment;
    }

    public void setUseDepartment(String useDepartment) {
        this.useDepartment = useDepartment;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

}