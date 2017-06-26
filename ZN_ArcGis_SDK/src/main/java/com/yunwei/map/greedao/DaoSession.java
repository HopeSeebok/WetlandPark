package com.yunwei.map.greedao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig mapLayer1DaoConfig;
    private final DaoConfig mapLayer2DaoConfig;
    private final DaoConfig mapLayer3DaoConfig;
    private final DaoConfig mapLayer4DaoConfig;
    private final DaoConfig mapLayer5DaoConfig;
    private final DaoConfig mapLayer6DaoConfig;
    private final DaoConfig mapLayer7DaoConfig;
    private final DaoConfig mapLayer8DaoConfig;
    private final DaoConfig mapLayer9DaoConfig;
    private final DaoConfig mapLayer10DaoConfig;
    private final DaoConfig mapLayer11DaoConfig;
    private final DaoConfig mapLayer12DaoConfig;
    private final DaoConfig mapLayer13DaoConfig;
    private final DaoConfig mapLayer14DaoConfig;
    private final DaoConfig mapLayer15DaoConfig;
    private final DaoConfig mapLayer16DaoConfig;
    private final DaoConfig mapLayer17DaoConfig;
    private final DaoConfig mapLayer18DaoConfig;
    private final DaoConfig mapLayer19DaoConfig;

    private final MapLayer1Dao mapLayer1Dao;
    private final MapLayer2Dao mapLayer2Dao;
    private final MapLayer3Dao mapLayer3Dao;
    private final MapLayer4Dao mapLayer4Dao;
    private final MapLayer5Dao mapLayer5Dao;
    private final MapLayer6Dao mapLayer6Dao;
    private final MapLayer7Dao mapLayer7Dao;
    private final MapLayer8Dao mapLayer8Dao;
    private final MapLayer9Dao mapLayer9Dao;
    private final MapLayer10Dao mapLayer10Dao;
    private final MapLayer11Dao mapLayer11Dao;
    private final MapLayer12Dao mapLayer12Dao;
    private final MapLayer13Dao mapLayer13Dao;
    private final MapLayer14Dao mapLayer14Dao;
    private final MapLayer15Dao mapLayer15Dao;
    private final MapLayer16Dao mapLayer16Dao;
    private final MapLayer17Dao mapLayer17Dao;
    private final MapLayer18Dao mapLayer18Dao;
    private final MapLayer19Dao mapLayer19Dao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        mapLayer1DaoConfig = daoConfigMap.get(MapLayer1Dao.class).clone();
        mapLayer1DaoConfig.initIdentityScope(type);

        mapLayer2DaoConfig = daoConfigMap.get(MapLayer2Dao.class).clone();
        mapLayer2DaoConfig.initIdentityScope(type);

        mapLayer3DaoConfig = daoConfigMap.get(MapLayer3Dao.class).clone();
        mapLayer3DaoConfig.initIdentityScope(type);

        mapLayer4DaoConfig = daoConfigMap.get(MapLayer4Dao.class).clone();
        mapLayer4DaoConfig.initIdentityScope(type);

        mapLayer5DaoConfig = daoConfigMap.get(MapLayer5Dao.class).clone();
        mapLayer5DaoConfig.initIdentityScope(type);

        mapLayer6DaoConfig = daoConfigMap.get(MapLayer6Dao.class).clone();
        mapLayer6DaoConfig.initIdentityScope(type);

        mapLayer7DaoConfig = daoConfigMap.get(MapLayer7Dao.class).clone();
        mapLayer7DaoConfig.initIdentityScope(type);

        mapLayer8DaoConfig = daoConfigMap.get(MapLayer8Dao.class).clone();
        mapLayer8DaoConfig.initIdentityScope(type);

        mapLayer9DaoConfig = daoConfigMap.get(MapLayer9Dao.class).clone();
        mapLayer9DaoConfig.initIdentityScope(type);

        mapLayer10DaoConfig = daoConfigMap.get(MapLayer10Dao.class).clone();
        mapLayer10DaoConfig.initIdentityScope(type);

        mapLayer11DaoConfig = daoConfigMap.get(MapLayer11Dao.class).clone();
        mapLayer11DaoConfig.initIdentityScope(type);

        mapLayer12DaoConfig = daoConfigMap.get(MapLayer12Dao.class).clone();
        mapLayer12DaoConfig.initIdentityScope(type);

        mapLayer13DaoConfig = daoConfigMap.get(MapLayer13Dao.class).clone();
        mapLayer13DaoConfig.initIdentityScope(type);

        mapLayer14DaoConfig = daoConfigMap.get(MapLayer14Dao.class).clone();
        mapLayer14DaoConfig.initIdentityScope(type);

        mapLayer15DaoConfig = daoConfigMap.get(MapLayer15Dao.class).clone();
        mapLayer15DaoConfig.initIdentityScope(type);

        mapLayer16DaoConfig = daoConfigMap.get(MapLayer16Dao.class).clone();
        mapLayer16DaoConfig.initIdentityScope(type);

        mapLayer17DaoConfig = daoConfigMap.get(MapLayer17Dao.class).clone();
        mapLayer17DaoConfig.initIdentityScope(type);

        mapLayer18DaoConfig = daoConfigMap.get(MapLayer18Dao.class).clone();
        mapLayer18DaoConfig.initIdentityScope(type);

        mapLayer19DaoConfig = daoConfigMap.get(MapLayer19Dao.class).clone();
        mapLayer19DaoConfig.initIdentityScope(type);

        mapLayer1Dao = new MapLayer1Dao(mapLayer1DaoConfig, this);
        mapLayer2Dao = new MapLayer2Dao(mapLayer2DaoConfig, this);
        mapLayer3Dao = new MapLayer3Dao(mapLayer3DaoConfig, this);
        mapLayer4Dao = new MapLayer4Dao(mapLayer4DaoConfig, this);
        mapLayer5Dao = new MapLayer5Dao(mapLayer5DaoConfig, this);
        mapLayer6Dao = new MapLayer6Dao(mapLayer6DaoConfig, this);
        mapLayer7Dao = new MapLayer7Dao(mapLayer7DaoConfig, this);
        mapLayer8Dao = new MapLayer8Dao(mapLayer8DaoConfig, this);
        mapLayer9Dao = new MapLayer9Dao(mapLayer9DaoConfig, this);
        mapLayer10Dao = new MapLayer10Dao(mapLayer10DaoConfig, this);
        mapLayer11Dao = new MapLayer11Dao(mapLayer11DaoConfig, this);
        mapLayer12Dao = new MapLayer12Dao(mapLayer12DaoConfig, this);
        mapLayer13Dao = new MapLayer13Dao(mapLayer13DaoConfig, this);
        mapLayer14Dao = new MapLayer14Dao(mapLayer14DaoConfig, this);
        mapLayer15Dao = new MapLayer15Dao(mapLayer15DaoConfig, this);
        mapLayer16Dao = new MapLayer16Dao(mapLayer16DaoConfig, this);
        mapLayer17Dao = new MapLayer17Dao(mapLayer17DaoConfig, this);
        mapLayer18Dao = new MapLayer18Dao(mapLayer18DaoConfig, this);
        mapLayer19Dao = new MapLayer19Dao(mapLayer19DaoConfig, this);

        registerDao(MapLayer1.class, mapLayer1Dao);
        registerDao(MapLayer2.class, mapLayer2Dao);
        registerDao(MapLayer3.class, mapLayer3Dao);
        registerDao(MapLayer4.class, mapLayer4Dao);
        registerDao(MapLayer5.class, mapLayer5Dao);
        registerDao(MapLayer6.class, mapLayer6Dao);
        registerDao(MapLayer7.class, mapLayer7Dao);
        registerDao(MapLayer8.class, mapLayer8Dao);
        registerDao(MapLayer9.class, mapLayer9Dao);
        registerDao(MapLayer10.class, mapLayer10Dao);
        registerDao(MapLayer11.class, mapLayer11Dao);
        registerDao(MapLayer12.class, mapLayer12Dao);
        registerDao(MapLayer13.class, mapLayer13Dao);
        registerDao(MapLayer14.class, mapLayer14Dao);
        registerDao(MapLayer15.class, mapLayer15Dao);
        registerDao(MapLayer16.class, mapLayer16Dao);
        registerDao(MapLayer17.class, mapLayer17Dao);
        registerDao(MapLayer18.class, mapLayer18Dao);
        registerDao(MapLayer19.class, mapLayer19Dao);
    }
    
    public void clear() {
        mapLayer1DaoConfig.getIdentityScope().clear();
        mapLayer2DaoConfig.getIdentityScope().clear();
        mapLayer3DaoConfig.getIdentityScope().clear();
        mapLayer4DaoConfig.getIdentityScope().clear();
        mapLayer5DaoConfig.getIdentityScope().clear();
        mapLayer6DaoConfig.getIdentityScope().clear();
        mapLayer7DaoConfig.getIdentityScope().clear();
        mapLayer8DaoConfig.getIdentityScope().clear();
        mapLayer9DaoConfig.getIdentityScope().clear();
        mapLayer10DaoConfig.getIdentityScope().clear();
        mapLayer11DaoConfig.getIdentityScope().clear();
        mapLayer12DaoConfig.getIdentityScope().clear();
        mapLayer13DaoConfig.getIdentityScope().clear();
        mapLayer14DaoConfig.getIdentityScope().clear();
        mapLayer15DaoConfig.getIdentityScope().clear();
        mapLayer16DaoConfig.getIdentityScope().clear();
        mapLayer17DaoConfig.getIdentityScope().clear();
        mapLayer18DaoConfig.getIdentityScope().clear();
        mapLayer19DaoConfig.getIdentityScope().clear();
    }

    public MapLayer1Dao getMapLayer1Dao() {
        return mapLayer1Dao;
    }

    public MapLayer2Dao getMapLayer2Dao() {
        return mapLayer2Dao;
    }

    public MapLayer3Dao getMapLayer3Dao() {
        return mapLayer3Dao;
    }

    public MapLayer4Dao getMapLayer4Dao() {
        return mapLayer4Dao;
    }

    public MapLayer5Dao getMapLayer5Dao() {
        return mapLayer5Dao;
    }

    public MapLayer6Dao getMapLayer6Dao() {
        return mapLayer6Dao;
    }

    public MapLayer7Dao getMapLayer7Dao() {
        return mapLayer7Dao;
    }

    public MapLayer8Dao getMapLayer8Dao() {
        return mapLayer8Dao;
    }

    public MapLayer9Dao getMapLayer9Dao() {
        return mapLayer9Dao;
    }

    public MapLayer10Dao getMapLayer10Dao() {
        return mapLayer10Dao;
    }

    public MapLayer11Dao getMapLayer11Dao() {
        return mapLayer11Dao;
    }

    public MapLayer12Dao getMapLayer12Dao() {
        return mapLayer12Dao;
    }

    public MapLayer13Dao getMapLayer13Dao() {
        return mapLayer13Dao;
    }

    public MapLayer14Dao getMapLayer14Dao() {
        return mapLayer14Dao;
    }

    public MapLayer15Dao getMapLayer15Dao() {
        return mapLayer15Dao;
    }

    public MapLayer16Dao getMapLayer16Dao() {
        return mapLayer16Dao;
    }

    public MapLayer17Dao getMapLayer17Dao() {
        return mapLayer17Dao;
    }

    public MapLayer18Dao getMapLayer18Dao() {
        return mapLayer18Dao;
    }

    public MapLayer19Dao getMapLayer19Dao() {
        return mapLayer19Dao;
    }

}
