package com.yunwei.wetlandpark.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.yunwei.library.qiniu.QiNiuConfig;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.UserInfoEntity;
import com.yunwei.wetlandpark.greedao.DaoMaster;
import com.yunwei.wetlandpark.greedao.DaoSession;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfig;

import cn.jpush.android.api.JPushInterface;

//import com.yunwei.im.HyphenateHelper;

/**
 * @Package: com.yunwei.zaina.ui.activity
 * @Description:全局类 application
 * @author: Aaron
 * @date: 2016-05-31
 * @Time: 11:45
 * @version: V1.0
 */
public class ZNAPPlication extends Application {

    private static ZNAPPlication instance;
    private static DaoSession daoSession;
    private static UserInfoEntity userInfoEntity;

    private double lng;//经度
    private double lat;//纬度

    //墨卡托坐标
    private double x;
    private double y;
    private double z;

    //当前位置信息
    private String currentAddr;
    //当前路段
    private String road;

    @Override
    public void onCreate() {
        super.onCreate();
        IConfig.init(this);
        initBugly();
        instance = this;

        QiNiuConfig.iniConfig(BuildConfig.QINIU_DOMAIN, BuildConfig.QINIU_FILENAME);

//        初始化jpush
        JPushInterface.init(getApplicationContext());
        JPushInterface.setDebugMode(true);

        //判断第一次启动设置任务语音和通知打开
        if ((boolean) ISpfUtil.getValue(getContext(), Constants.IS_FIRST_START, true)) {
            ISpfUtil.setValue(getContext(), Constants.TASK_VOICE_TIPS, true);
            ISpfUtil.setValue(getContext(), Constants.TASK_NOTICE_TIPS, true);
            ISpfUtil.setValue(getContext(), Constants.IS_FIRST_START, false);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 初始化Bugly
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext());
    }

    public static ZNAPPlication getInstance() {
//        if (instance == null) {
//            instance = new ZNAPPlication();
//        }
        return instance;
    }

    public Context getContext() {
        return this;
    }

    /**
     * DAO对象
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(instance, "CMCC", null);
            DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 获取用户信息
     *
     * @return
     * @modify 这里传递的参数要为Application
     */
    public static UserInfoEntity getUserInfoEntity(Activity activity) {
        if (userInfoEntity == null) {
            String userInfo = ISpfUtil.getValue(activity, Constants.LOGIN_INFO_KEY, "").toString();
            try {
                Gson gson = new Gson();
                userInfoEntity = gson.fromJson(userInfo, UserInfoEntity.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userInfoEntity;
    }

    /**
     * 获取用户信息
     *
     * @return
     * @modify 这里传递的参数要为Application
     */
    public static UserInfoEntity getUserInfoEntity() {
        if (userInfoEntity == null) {
            String userInfo = ISpfUtil.getValue(instance, Constants.LOGIN_INFO_KEY, "").toString();
            try {
                Gson gson = new Gson();
                userInfoEntity = gson.fromJson(userInfo, UserInfoEntity.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userInfoEntity;
    }

    public void clearUserInfo() {
        userInfoEntity = null;
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

    public String getCurrentAddr() {
        return currentAddr;
    }

    public void setCurrentAddr(String currentAddr) {
        this.currentAddr = currentAddr;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }
}
