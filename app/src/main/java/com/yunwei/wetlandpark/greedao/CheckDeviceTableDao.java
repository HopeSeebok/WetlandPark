package com.yunwei.wetlandpark.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHECK_DEVICE_TABLE".
*/
public class CheckDeviceTableDao extends AbstractDao<CheckDeviceTable, Long> {

    public static final String TABLENAME = "CHECK_DEVICE_TABLE";

    /**
     * Properties of entity CheckDeviceTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DeviceID = new Property(1, String.class, "deviceID", false, "DEVICE_ID");
        public final static Property DeviceCode = new Property(2, String.class, "deviceCode", false, "DEVICE_CODE");
        public final static Property DeviceAddress = new Property(3, String.class, "deviceAddress", false, "DEVICE_ADDRESS");
        public final static Property DeviceTypeCode = new Property(4, Integer.class, "deviceTypeCode", false, "DEVICE_TYPE_CODE");
        public final static Property DeviceTypeName = new Property(5, String.class, "deviceTypeName", false, "DEVICE_TYPE_NAME");
        public final static Property UserName = new Property(6, String.class, "userName", false, "USER_NAME");
        public final static Property UserID = new Property(7, String.class, "userID", false, "USER_ID");
        public final static Property HdType = new Property(8, String.class, "hdType", false, "HD_TYPE");
        public final static Property HdDesc = new Property(9, String.class, "hdDesc", false, "HD_DESC");
        public final static Property DeviceStatus = new Property(10, Integer.class, "deviceStatus", false, "DEVICE_STATUS");
        public final static Property Lng = new Property(11, Double.class, "lng", false, "LNG");
        public final static Property Lat = new Property(12, Double.class, "lat", false, "LAT");
        public final static Property WorkAddress = new Property(13, String.class, "workAddress", false, "WORK_ADDRESS");
        public final static Property SaveTime = new Property(14, Long.class, "saveTime", false, "SAVE_TIME");
        public final static Property Note = new Property(15, String.class, "note", false, "NOTE");
        public final static Property ReportType = new Property(16, String.class, "reportType", false, "REPORT_TYPE");
        public final static Property ImagesUrl = new Property(17, String.class, "imagesUrl", false, "IMAGES_URL");
        public final static Property LocalTag = new Property(18, Integer.class, "localTag", false, "LOCAL_TAG");
        public final static Property MissionID = new Property(19, String.class, "missionID", false, "MISSION_ID");
        public final static Property MissionPointID = new Property(20, String.class, "missionPointID", false, "MISSION_POINT_ID");
    };


    public CheckDeviceTableDao(DaoConfig config) {
        super(config);
    }
    
    public CheckDeviceTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHECK_DEVICE_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DEVICE_ID\" TEXT," + // 1: deviceID
                "\"DEVICE_CODE\" TEXT," + // 2: deviceCode
                "\"DEVICE_ADDRESS\" TEXT," + // 3: deviceAddress
                "\"DEVICE_TYPE_CODE\" INTEGER," + // 4: deviceTypeCode
                "\"DEVICE_TYPE_NAME\" TEXT," + // 5: deviceTypeName
                "\"USER_NAME\" TEXT," + // 6: userName
                "\"USER_ID\" TEXT," + // 7: userID
                "\"HD_TYPE\" TEXT," + // 8: hdType
                "\"HD_DESC\" TEXT," + // 9: hdDesc
                "\"DEVICE_STATUS\" INTEGER," + // 10: deviceStatus
                "\"LNG\" REAL," + // 11: lng
                "\"LAT\" REAL," + // 12: lat
                "\"WORK_ADDRESS\" TEXT," + // 13: workAddress
                "\"SAVE_TIME\" INTEGER," + // 14: saveTime
                "\"NOTE\" TEXT," + // 15: note
                "\"REPORT_TYPE\" TEXT," + // 16: reportType
                "\"IMAGES_URL\" TEXT," + // 17: imagesUrl
                "\"LOCAL_TAG\" INTEGER," + // 18: localTag
                "\"MISSION_ID\" TEXT," + // 19: missionID
                "\"MISSION_POINT_ID\" TEXT);"); // 20: missionPointID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHECK_DEVICE_TABLE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CheckDeviceTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String deviceID = entity.getDeviceID();
        if (deviceID != null) {
            stmt.bindString(2, deviceID);
        }
 
        String deviceCode = entity.getDeviceCode();
        if (deviceCode != null) {
            stmt.bindString(3, deviceCode);
        }
 
        String deviceAddress = entity.getDeviceAddress();
        if (deviceAddress != null) {
            stmt.bindString(4, deviceAddress);
        }
 
        Integer deviceTypeCode = entity.getDeviceTypeCode();
        if (deviceTypeCode != null) {
            stmt.bindLong(5, deviceTypeCode);
        }
 
        String deviceTypeName = entity.getDeviceTypeName();
        if (deviceTypeName != null) {
            stmt.bindString(6, deviceTypeName);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(8, userID);
        }
 
        String hdType = entity.getHdType();
        if (hdType != null) {
            stmt.bindString(9, hdType);
        }
 
        String hdDesc = entity.getHdDesc();
        if (hdDesc != null) {
            stmt.bindString(10, hdDesc);
        }
 
        Integer deviceStatus = entity.getDeviceStatus();
        if (deviceStatus != null) {
            stmt.bindLong(11, deviceStatus);
        }
 
        Double lng = entity.getLng();
        if (lng != null) {
            stmt.bindDouble(12, lng);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(13, lat);
        }
 
        String workAddress = entity.getWorkAddress();
        if (workAddress != null) {
            stmt.bindString(14, workAddress);
        }
 
        Long saveTime = entity.getSaveTime();
        if (saveTime != null) {
            stmt.bindLong(15, saveTime);
        }
 
        String note = entity.getNote();
        if (note != null) {
            stmt.bindString(16, note);
        }
 
        String reportType = entity.getReportType();
        if (reportType != null) {
            stmt.bindString(17, reportType);
        }
 
        String imagesUrl = entity.getImagesUrl();
        if (imagesUrl != null) {
            stmt.bindString(18, imagesUrl);
        }
 
        Integer localTag = entity.getLocalTag();
        if (localTag != null) {
            stmt.bindLong(19, localTag);
        }
 
        String missionID = entity.getMissionID();
        if (missionID != null) {
            stmt.bindString(20, missionID);
        }
 
        String missionPointID = entity.getMissionPointID();
        if (missionPointID != null) {
            stmt.bindString(21, missionPointID);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public CheckDeviceTable readEntity(Cursor cursor, int offset) {
        CheckDeviceTable entity = new CheckDeviceTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // deviceID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // deviceCode
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deviceAddress
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // deviceTypeCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // deviceTypeName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // userName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // userID
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // hdType
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // hdDesc
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // deviceStatus
            cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11), // lng
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12), // lat
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // workAddress
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14), // saveTime
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // note
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // reportType
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // imagesUrl
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // localTag
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // missionID
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20) // missionPointID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CheckDeviceTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDeviceID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDeviceCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeviceAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDeviceTypeCode(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setDeviceTypeName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserID(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setHdType(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setHdDesc(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDeviceStatus(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setLng(cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11));
        entity.setLat(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
        entity.setWorkAddress(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSaveTime(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
        entity.setNote(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setReportType(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setImagesUrl(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setLocalTag(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setMissionID(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setMissionPointID(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CheckDeviceTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CheckDeviceTable entity) {
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
