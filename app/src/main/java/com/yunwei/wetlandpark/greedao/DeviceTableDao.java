package com.yunwei.wetlandpark.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yunwei.wetlandpark.greedao.DeviceTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DEVICE_TABLE".
*/
public class DeviceTableDao extends AbstractDao<DeviceTable, Long> {

    public static final String TABLENAME = "DEVICE_TABLE";

    /**
     * Properties of entity DeviceTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property DeviceID = new Property(1, String.class, "deviceID", false, "DEVICE_ID");
        public final static Property RFID = new Property(2, String.class, "RFID", false, "RFID");
        public final static Property DeviceCode = new Property(3, String.class, "deviceCode", false, "DEVICE_CODE");
        public final static Property DeviceAddress = new Property(4, String.class, "deviceAddress", false, "DEVICE_ADDRESS");
        public final static Property DeviceTypeCode = new Property(5, Integer.class, "deviceTypeCode", false, "DEVICE_TYPE_CODE");
        public final static Property DeviceTypeName = new Property(6, String.class, "deviceTypeName", false, "DEVICE_TYPE_NAME");
        public final static Property Lng = new Property(7, Double.class, "lng", false, "LNG");
        public final static Property Lat = new Property(8, Double.class, "lat", false, "LAT");
        public final static Property CreateTime = new Property(9, Long.class, "createTime", false, "CREATE_TIME");
        public final static Property ImagesUrl = new Property(10, String.class, "imagesUrl", false, "IMAGES_URL");
        public final static Property LocalTag = new Property(11, Integer.class, "localTag", false, "LOCAL_TAG");
        public final static Property UserID = new Property(12, String.class, "userID", false, "USER_ID");
        public final static Property UserName = new Property(13, String.class, "userName", false, "USER_NAME");
        public final static Property UnitID = new Property(14, Integer.class, "unitID", false, "UNIT_ID");
        public final static Property Contact = new Property(15, String.class, "contact", false, "CONTACT");
        public final static Property Phone = new Property(16, String.class, "phone", false, "PHONE");
        public final static Property StartTime = new Property(17, String.class, "startTime", false, "START_TIME");
        public final static Property StopTime = new Property(18, String.class, "stopTime", false, "STOP_TIME");
        public final static Property UseDepartment = new Property(19, String.class, "useDepartment", false, "USE_DEPARTMENT");
        public final static Property Ext = new Property(20, String.class, "ext", false, "EXT");
    };


    public DeviceTableDao(DaoConfig config) {
        super(config);
    }
    
    public DeviceTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DEVICE_TABLE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DEVICE_ID\" TEXT," + // 1: deviceID
                "\"RFID\" TEXT," + // 2: RFID
                "\"DEVICE_CODE\" TEXT," + // 3: deviceCode
                "\"DEVICE_ADDRESS\" TEXT," + // 4: deviceAddress
                "\"DEVICE_TYPE_CODE\" INTEGER," + // 5: deviceTypeCode
                "\"DEVICE_TYPE_NAME\" TEXT," + // 6: deviceTypeName
                "\"LNG\" REAL," + // 7: lng
                "\"LAT\" REAL," + // 8: lat
                "\"CREATE_TIME\" INTEGER," + // 9: createTime
                "\"IMAGES_URL\" TEXT," + // 10: imagesUrl
                "\"LOCAL_TAG\" INTEGER," + // 11: localTag
                "\"USER_ID\" TEXT," + // 12: userID
                "\"USER_NAME\" TEXT," + // 13: userName
                "\"UNIT_ID\" INTEGER," + // 14: unitID
                "\"CONTACT\" TEXT," + // 15: contact
                "\"PHONE\" TEXT," + // 16: phone
                "\"START_TIME\" TEXT," + // 17: startTime
                "\"STOP_TIME\" TEXT," + // 18: stopTime
                "\"USE_DEPARTMENT\" TEXT," + // 19: useDepartment
                "\"EXT\" TEXT);"); // 20: ext
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DEVICE_TABLE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DeviceTable entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String deviceID = entity.getDeviceID();
        if (deviceID != null) {
            stmt.bindString(2, deviceID);
        }
 
        String RFID = entity.getRFID();
        if (RFID != null) {
            stmt.bindString(3, RFID);
        }
 
        String deviceCode = entity.getDeviceCode();
        if (deviceCode != null) {
            stmt.bindString(4, deviceCode);
        }
 
        String deviceAddress = entity.getDeviceAddress();
        if (deviceAddress != null) {
            stmt.bindString(5, deviceAddress);
        }
 
        Integer deviceTypeCode = entity.getDeviceTypeCode();
        if (deviceTypeCode != null) {
            stmt.bindLong(6, deviceTypeCode);
        }
 
        String deviceTypeName = entity.getDeviceTypeName();
        if (deviceTypeName != null) {
            stmt.bindString(7, deviceTypeName);
        }
 
        Double lng = entity.getLng();
        if (lng != null) {
            stmt.bindDouble(8, lng);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(9, lat);
        }
 
        Long createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(10, createTime);
        }
 
        String imagesUrl = entity.getImagesUrl();
        if (imagesUrl != null) {
            stmt.bindString(11, imagesUrl);
        }
 
        Integer localTag = entity.getLocalTag();
        if (localTag != null) {
            stmt.bindLong(12, localTag);
        }
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(13, userID);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(14, userName);
        }
 
        Integer unitID = entity.getUnitID();
        if (unitID != null) {
            stmt.bindLong(15, unitID);
        }
 
        String contact = entity.getContact();
        if (contact != null) {
            stmt.bindString(16, contact);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(17, phone);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(18, startTime);
        }
 
        String stopTime = entity.getStopTime();
        if (stopTime != null) {
            stmt.bindString(19, stopTime);
        }
 
        String useDepartment = entity.getUseDepartment();
        if (useDepartment != null) {
            stmt.bindString(20, useDepartment);
        }
 
        String ext = entity.getExt();
        if (ext != null) {
            stmt.bindString(21, ext);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DeviceTable readEntity(Cursor cursor, int offset) {
        DeviceTable entity = new DeviceTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // deviceID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // RFID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // deviceCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // deviceAddress
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // deviceTypeCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // deviceTypeName
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // lng
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // lat
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // createTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // imagesUrl
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // localTag
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // userID
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // userName
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // unitID
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // contact
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // phone
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // startTime
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // stopTime
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // useDepartment
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20) // ext
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DeviceTable entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDeviceID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRFID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDeviceCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDeviceAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDeviceTypeCode(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setDeviceTypeName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLng(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setLat(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setCreateTime(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setImagesUrl(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setLocalTag(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setUserID(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUserName(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setUnitID(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setContact(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setPhone(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setStartTime(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setStopTime(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setUseDepartment(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setExt(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DeviceTable entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DeviceTable entity) {
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
