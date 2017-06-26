package com.yunwei.wetlandpark.greedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yunwei.wetlandpark.greedao.PlanToInspectEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PLAN_TO_INSPECT_ENTITY".
*/
public class PlanToInspectEntityDao extends AbstractDao<PlanToInspectEntity, Long> {

    public static final String TABLENAME = "PLAN_TO_INSPECT_ENTITY";

    /**
     * Properties of entity PlanToInspectEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PlanId = new Property(1, String.class, "PlanId", false, "PLAN_ID");
        public final static Property PlanName = new Property(2, String.class, "PlanName", false, "PLAN_NAME");
        public final static Property UserId = new Property(3, String.class, "UserId", false, "USER_ID");
        public final static Property PlanRule = new Property(4, String.class, "PlanRule", false, "PLAN_RULE");
        public final static Property StartTime = new Property(5, String.class, "StartTime", false, "START_TIME");
        public final static Property EndTime = new Property(6, String.class, "EndTime", false, "END_TIME");
        public final static Property Executor = new Property(7, String.class, "Executor", false, "EXECUTOR");
        public final static Property Note = new Property(8, String.class, "Note", false, "NOTE");
        public final static Property Codes = new Property(9, String.class, "Codes", false, "CODES");
        public final static Property Rfids = new Property(10, String.class, "Rfids", false, "RFIDS");
        public final static Property Revint1 = new Property(11, Integer.class, "revint1", false, "REVINT1");
        public final static Property Revint2 = new Property(12, Integer.class, "revint2", false, "REVINT2");
        public final static Property Revint3 = new Property(13, Integer.class, "revint3", false, "REVINT3");
        public final static Property Revstr1 = new Property(14, String.class, "revstr1", false, "REVSTR1");
        public final static Property Revstr2 = new Property(15, String.class, "revstr2", false, "REVSTR2");
        public final static Property Revstr3 = new Property(16, String.class, "revstr3", false, "REVSTR3");
    };


    public PlanToInspectEntityDao(DaoConfig config) {
        super(config);
    }
    
    public PlanToInspectEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLAN_TO_INSPECT_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PLAN_ID\" TEXT," + // 1: PlanId
                "\"PLAN_NAME\" TEXT," + // 2: PlanName
                "\"USER_ID\" TEXT," + // 3: UserId
                "\"PLAN_RULE\" TEXT," + // 4: PlanRule
                "\"START_TIME\" TEXT," + // 5: StartTime
                "\"END_TIME\" TEXT," + // 6: EndTime
                "\"EXECUTOR\" TEXT," + // 7: Executor
                "\"NOTE\" TEXT," + // 8: Note
                "\"CODES\" TEXT," + // 9: Codes
                "\"RFIDS\" TEXT," + // 10: Rfids
                "\"REVINT1\" INTEGER," + // 11: revint1
                "\"REVINT2\" INTEGER," + // 12: revint2
                "\"REVINT3\" INTEGER," + // 13: revint3
                "\"REVSTR1\" TEXT," + // 14: revstr1
                "\"REVSTR2\" TEXT," + // 15: revstr2
                "\"REVSTR3\" TEXT);"); // 16: revstr3
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAN_TO_INSPECT_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PlanToInspectEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String PlanId = entity.getPlanId();
        if (PlanId != null) {
            stmt.bindString(2, PlanId);
        }
 
        String PlanName = entity.getPlanName();
        if (PlanName != null) {
            stmt.bindString(3, PlanName);
        }
 
        String UserId = entity.getUserId();
        if (UserId != null) {
            stmt.bindString(4, UserId);
        }
 
        String PlanRule = entity.getPlanRule();
        if (PlanRule != null) {
            stmt.bindString(5, PlanRule);
        }
 
        String StartTime = entity.getStartTime();
        if (StartTime != null) {
            stmt.bindString(6, StartTime);
        }
 
        String EndTime = entity.getEndTime();
        if (EndTime != null) {
            stmt.bindString(7, EndTime);
        }
 
        String Executor = entity.getExecutor();
        if (Executor != null) {
            stmt.bindString(8, Executor);
        }
 
        String Note = entity.getNote();
        if (Note != null) {
            stmt.bindString(9, Note);
        }
 
        String Codes = entity.getCodes();
        if (Codes != null) {
            stmt.bindString(10, Codes);
        }
 
        String Rfids = entity.getRfids();
        if (Rfids != null) {
            stmt.bindString(11, Rfids);
        }
 
        Integer revint1 = entity.getRevint1();
        if (revint1 != null) {
            stmt.bindLong(12, revint1);
        }
 
        Integer revint2 = entity.getRevint2();
        if (revint2 != null) {
            stmt.bindLong(13, revint2);
        }
 
        Integer revint3 = entity.getRevint3();
        if (revint3 != null) {
            stmt.bindLong(14, revint3);
        }
 
        String revstr1 = entity.getRevstr1();
        if (revstr1 != null) {
            stmt.bindString(15, revstr1);
        }
 
        String revstr2 = entity.getRevstr2();
        if (revstr2 != null) {
            stmt.bindString(16, revstr2);
        }
 
        String revstr3 = entity.getRevstr3();
        if (revstr3 != null) {
            stmt.bindString(17, revstr3);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PlanToInspectEntity readEntity(Cursor cursor, int offset) {
        PlanToInspectEntity entity = new PlanToInspectEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // PlanId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // PlanName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // UserId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // PlanRule
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // StartTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // EndTime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Executor
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // Note
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // Codes
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // Rfids
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // revint1
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // revint2
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // revint3
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // revstr1
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // revstr2
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16) // revstr3
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PlanToInspectEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPlanId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPlanName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUserId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPlanRule(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setStartTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setEndTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setExecutor(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNote(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCodes(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setRfids(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setRevint1(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setRevint2(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setRevint3(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setRevstr1(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setRevstr2(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setRevstr3(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PlanToInspectEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PlanToInspectEntity entity) {
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