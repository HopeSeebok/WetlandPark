package com.yunwei.wetlandpark.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORK_RECORD_TABLE".
*/
public class WorkRecordTableDao extends AbstractDao<WorkRecordTable, Long> {

    public static final String TABLENAME = "WORK_RECORD_TABLE";

    /**
     * Properties of entity WorkRecordTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property TaskID = new Property(1, String.class, "taskID", false, "TASK_ID");
        public final static Property TaskPointID = new Property(2, String.class, "taskPointID", false, "TASK_POINT_ID");
        public final static Property DeviceType = new Property(3, String.class, "deviceType", false, "DEVICE_TYPE");
        public final static Property DeviceCode = new Property(4, String.class, "deviceCode", false, "DEVICE_CODE");
        public final static Property DeviceID = new Property(5, String.class, "deviceID", false, "DEVICE_ID");
        public final static Property RoadLocation = new Property(6, String.class, "roadLocation", false, "ROAD_LOCATION");
        public final static Property AreaLocation = new Property(7, String.class, "areaLocation", false, "AREA_LOCATION");
        public final static Property Remark = new Property(8, String.class, "remark", false, "REMARK");
        public final static Property RecordType = new Property(9, Integer.class, "recordType", false, "RECORD_TYPE");
        public final static Property UseState = new Property(10, Integer.class, "useState", false, "USE_STATE");
        public final static Property LocalTag = new Property(11, Integer.class, "localTag", false, "LOCAL_TAG");
        public final static Property Lng = new Property(12, Double.class, "lng", false, "LNG");
        public final static Property Lat = new Property(13, Double.class, "lat", false, "LAT");
        public final static Property Address = new Property(14, String.class, "address", false, "ADDRESS");
        public final static Property WorkAddress = new Property(15, String.class, "workAddress", false, "WORK_ADDRESS");
        public final static Property SaveTime = new Property(16, Long.class, "saveTime", false, "SAVE_TIME");
        public final static Property UserName = new Property(17, String.class, "userName", false, "USER_NAME");
        public final static Property UserID = new Property(18, String.class, "userID", false, "USER_ID");
        public final static Property WorkDesc = new Property(19, String.class, "workDesc", false, "WORK_DESC");
        public final static Property FaultReason = new Property(20, String.class, "faultReason", false, "FAULT_REASON");
        public final static Property ReportType = new Property(21, String.class, "reportType", false, "REPORT_TYPE");
        public final static Property HdType = new Property(22, String.class, "hdType", false, "HD_TYPE");
        public final static Property HdDesc = new Property(23, String.class, "hdDesc", false, "HD_DESC");
        public final static Property ImagesUrl = new Property(24, String.class, "imagesUrl", false, "IMAGES_URL");
        public final static Property ReservedStringOne = new Property(25, String.class, "reservedStringOne", false, "RESERVED_STRING_ONE");
        public final static Property ReservedStringTow = new Property(26, String.class, "reservedStringTow", false, "RESERVED_STRING_TOW");
        public final static Property ReservedIntOne = new Property(27, Integer.class, "reservedIntOne", false, "RESERVED_INT_ONE");
    };


    public WorkRecordTableDao(DaoConfig config) {
        super(config);
    }
    
    public WorkRecordTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORK_RECORD_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TASK_ID\" TEXT," + // 1: taskID
                "\"TASK_POINT_ID\" TEXT," + // 2: taskPointID
                "\"DEVICE_TYPE\" TEXT," + // 3: deviceType
                "\"DEVICE_CODE\" TEXT," + // 4: deviceCode
                "\"DEVICE_ID\" TEXT," + // 5: deviceID
                "\"ROAD_LOCATION\" TEXT," + // 6: roadLocation
                "\"AREA_LOCATION\" TEXT," + // 7: areaLocation
                "\"REMARK\" TEXT," + // 8: remark
                "\"RECORD_TYPE\" INTEGER," + // 9: recordType
                "\"USE_STATE\" INTEGER," + // 10: useState
                "\"LOCAL_TAG\" INTEGER," + // 11: localTag
                "\"LNG\" REAL," + // 12: lng
                "\"LAT\" REAL," + // 13: lat
                "\"ADDRESS\" TEXT," + // 14: address
                "\"WORK_ADDRESS\" TEXT," + // 15: workAddress
                "\"SAVE_TIME\" INTEGER," + // 16: saveTime
                "\"USER_NAME\" TEXT," + // 17: userName
                "\"USER_ID\" TEXT," + // 18: userID
                "\"WORK_DESC\" TEXT," + // 19: workDesc
                "\"FAULT_REASON\" TEXT," + // 20: faultReason
                "\"REPORT_TYPE\" TEXT," + // 21: reportType
                "\"HD_TYPE\" TEXT," + // 22: hdType
                "\"HD_DESC\" TEXT," + // 23: hdDesc
                "\"IMAGES_URL\" TEXT," + // 24: imagesUrl
                "\"RESERVED_STRING_ONE\" TEXT," + // 25: reservedStringOne
                "\"RESERVED_STRING_TOW\" TEXT," + // 26: reservedStringTow
                "\"RESERVED_INT_ONE\" INTEGER);"); // 27: reservedIntOne
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORK_RECORD_TABLE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, WorkRecordTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String taskID = entity.getTaskID();
        if (taskID != null) {
            stmt.bindString(2, taskID);
        }
 
        String taskPointID = entity.getTaskPointID();
        if (taskPointID != null) {
            stmt.bindString(3, taskPointID);
        }
 
        String deviceType = entity.getDeviceType();
        if (deviceType != null) {
            stmt.bindString(4, deviceType);
        }
 
        String deviceCode = entity.getDeviceCode();
        if (deviceCode != null) {
            stmt.bindString(5, deviceCode);
        }
 
        String deviceID = entity.getDeviceID();
        if (deviceID != null) {
            stmt.bindString(6, deviceID);
        }
 
        String roadLocation = entity.getRoadLocation();
        if (roadLocation != null) {
            stmt.bindString(7, roadLocation);
        }
 
        String areaLocation = entity.getAreaLocation();
        if (areaLocation != null) {
            stmt.bindString(8, areaLocation);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(9, remark);
        }
 
        Integer recordType = entity.getRecordType();
        if (recordType != null) {
            stmt.bindLong(10, recordType);
        }
 
        Integer useState = entity.getUseState();
        if (useState != null) {
            stmt.bindLong(11, useState);
        }
 
        Integer localTag = entity.getLocalTag();
        if (localTag != null) {
            stmt.bindLong(12, localTag);
        }
 
        Double lng = entity.getLng();
        if (lng != null) {
            stmt.bindDouble(13, lng);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(14, lat);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(15, address);
        }
 
        String workAddress = entity.getWorkAddress();
        if (workAddress != null) {
            stmt.bindString(16, workAddress);
        }
 
        Long saveTime = entity.getSaveTime();
        if (saveTime != null) {
            stmt.bindLong(17, saveTime);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(18, userName);
        }
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(19, userID);
        }
 
        String workDesc = entity.getWorkDesc();
        if (workDesc != null) {
            stmt.bindString(20, workDesc);
        }
 
        String faultReason = entity.getFaultReason();
        if (faultReason != null) {
            stmt.bindString(21, faultReason);
        }
 
        String reportType = entity.getReportType();
        if (reportType != null) {
            stmt.bindString(22, reportType);
        }
 
        String hdType = entity.getHdType();
        if (hdType != null) {
            stmt.bindString(23, hdType);
        }
 
        String hdDesc = entity.getHdDesc();
        if (hdDesc != null) {
            stmt.bindString(24, hdDesc);
        }
 
        String imagesUrl = entity.getImagesUrl();
        if (imagesUrl != null) {
            stmt.bindString(25, imagesUrl);
        }
 
        String reservedStringOne = entity.getReservedStringOne();
        if (reservedStringOne != null) {
            stmt.bindString(26, reservedStringOne);
        }
 
        String reservedStringTow = entity.getReservedStringTow();
        if (reservedStringTow != null) {
            stmt.bindString(27, reservedStringTow);
        }
 
        Integer reservedIntOne = entity.getReservedIntOne();
        if (reservedIntOne != null) {
            stmt.bindLong(28, reservedIntOne);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public WorkRecordTable readEntity(Cursor cursor, int offset) {
        WorkRecordTable entity = new WorkRecordTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // taskID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // taskPointID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deviceType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // deviceCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // deviceID
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // roadLocation
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // areaLocation
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // remark
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // recordType
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // useState
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // localTag
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12), // lng
            cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13), // lat
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // address
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // workAddress
            cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16), // saveTime
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // userName
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // userID
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // workDesc
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // faultReason
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // reportType
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // hdType
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // hdDesc
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // imagesUrl
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // reservedStringOne
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // reservedStringTow
            cursor.isNull(offset + 27) ? null : cursor.getInt(offset + 27) // reservedIntOne
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, WorkRecordTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTaskID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTaskPointID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeviceType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDeviceCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDeviceID(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRoadLocation(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAreaLocation(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRemark(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setRecordType(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setUseState(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setLocalTag(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setLng(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
        entity.setLat(cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13));
        entity.setAddress(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setWorkAddress(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setSaveTime(cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16));
        entity.setUserName(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setUserID(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setWorkDesc(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setFaultReason(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setReportType(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setHdType(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setHdDesc(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setImagesUrl(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setReservedStringOne(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setReservedStringTow(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setReservedIntOne(cursor.isNull(offset + 27) ? null : cursor.getInt(offset + 27));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(WorkRecordTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(WorkRecordTable entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
