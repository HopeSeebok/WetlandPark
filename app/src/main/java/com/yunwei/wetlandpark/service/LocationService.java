package com.yunwei.wetlandpark.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.greedao.TrackPoint;
import com.yunwei.wetlandpark.utils.IGPSHelper;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.utils.IStringUtils;
import com.yunwei.map.entity.MPointEntity;
import com.yunwei.map.utils.CalculatePointAvailabUtil;
import com.yunwei.map.utils.MapUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;

/**
 * @Package: com.yunwei.zaina.service
 * @Description:定位Service 1、google定位与高德定位SDK同是开启
 * 2、默认情况下会使用高德定位、在无网络情况下使用GPS定位[处理逻辑在HomeFragment onBackGroundUserEvent方法]
 * @author: Aaron
 * @date: 2016-06-02
 * @Time: 09:12
 * @version: V1.0
 */
public class LocationService extends Service implements AMapLocationListener {
    private final String TAG = getClass().getSimpleName();

    private LocationManager mLocationManager;
    private GPSLocationListener gpsLocationListener;
    private Location mCurrentLocation;
    private GpsStatusListener gpsStatusListener;

    // 声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption locationClientOption;
    /**
     * 定位相关参数
     */
    private final int LOC_INTERVEL = 1000;// GPS定位间隔
    private final int REPORT_SITE_DISTANCE = 1;// GPS距离设置

    private long lastTrackRecordTime;
    private double lastLat;
    private double lastLng;
    private float speed;
    private double distance;//单位M

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        startGoogleLocation();
        startGaoDeMapService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
        if (mLocationManager != null && gpsLocationListener != null) {
            mLocationManager.removeUpdates(gpsLocationListener);
        }

        if (mLocationManager != null && gpsStatusListener != null) {
            mLocationManager.removeGpsStatusListener(gpsStatusListener);
        }
    }

    /**
     * 启动google GPS定位方式
     */
    private void startGoogleLocation() {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        //GPS定位
        gpsLocationListener = new GPSLocationListener();
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOC_INTERVEL, REPORT_SITE_DISTANCE, gpsLocationListener);
        //监听GPS状态
        gpsStatusListener = new GpsStatusListener();
        mLocationManager.addGpsStatusListener(gpsStatusListener);
    }

    /**
     * @throws
     * @Title: startGaoDeMapService
     * @Description: 启用高德定位
     */

    private void startGaoDeMapService() {
        mLocationClient = new AMapLocationClient(getApplicationContext());

        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//高精度
        locationClientOption.setNeedAddress(true);
        locationClientOption.setInterval(LOC_INTERVEL);
        mLocationClient.setLocationOption(locationClientOption);

        mLocationClient.setLocationListener(this);
        mLocationClient.startLocation();
    }

    /**
     * GPS定位监听器
     */
    private class GPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (mCurrentLocation == null) {
                mCurrentLocation = location;
            }

            if (!IGPSHelper.isBetterLocation(location, mCurrentLocation)) {
                mCurrentLocation = location;
            }
            sendGoogleLocationData(location);
            ILog.d(TAG, "onLocationChanged lat==" + location.getLatitude() + ", lng==" + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            ILog.d(TAG, "onStatuschanged provider==" + provider + ", status==" + status + ", extras==" + extras.toString());

        }

        @Override
        public void onProviderEnabled(String provider) {
            ILog.d(TAG, "onProviderEnabled==" + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            ILog.d(TAG, "onProviderDisabled==" + provider);
        }
    }

    /**
     * 發送google位置信息
     *
     * @param location
     */
    private void sendGoogleLocationData(Location location) {
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE1);
        event.setObj(location);
        EventBus.getDefault().post(event);
    }

    /**
     * 發送高德位置信息
     *
     * @param location
     */
    private void sendGaoDeLocationData(AMapLocation location) {
        NoticeEvent event = new NoticeEvent();
        event.setFlag(EventConstant.NOTICE2);
        event.setObj(location);
        EventBus.getDefault().post(event);
//        /**
//         * 转成墨卡托坐标
//         */
//        MPointEntity mercatorPoint = ILngLatMercator.lonLat2WebMercator(location.getLongitude(), location.getLatitude());
//        /**
//         * 判断当前是否开始记录足迹
//         */
//        int state = IStringUtils.toInt(ISpfUtil.getValue(this, Constants.TRACK_RECORD_STATUE_KEY, 0).toString());
//        if (state == Constants.TRACK_RECORD_STATE.START.getValue()) {
//            trackRecord(location, mercatorPoint);
//        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                sendGaoDeLocationData(aMapLocation);
            } else {
                ILog.e(TAG, "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
            }
        }
    }

    /**
     * GPS变化状态监听
     */
    private class GpsStatusListener implements GpsStatus.Listener {
        @Override
        public void onGpsStatusChanged(int event) {
            switch (event) {
                case GpsStatus.GPS_EVENT_FIRST_FIX://GPS第一次定位
//                    ILog.d(TAG, "第一次定位");
                    break;
                case GpsStatus.GPS_EVENT_STARTED://定位启动
//                    ILog.d(TAG, "启动定位");
                    break;
                case GpsStatus.GPS_EVENT_STOPPED://定位结束
//                    ILog.d(TAG, "定位结束");
                    break;
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS://卫星状态变化
//                    ILog.i(TAG, "卫星状态改变");
                    //获取当前状态
                    GpsStatus gpsStatus = mLocationManager.getGpsStatus(null);
                    //获取卫星颗数的默认最大值
                    int maxSatellites = gpsStatus.getMaxSatellites();
                    //创建一个迭代器保存所有卫星
                    Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                    int count = 0;
                    while (iters.hasNext() && count <= maxSatellites) {
                        GpsSatellite s = iters.next();
                        count++;
                    }
//                    ILog.d(TAG, "搜索到：" + count + "颗卫星");
                    break;
            }
        }
    }

    /**
     * 足迹采集
     *
     * @param location
     */
    private void trackRecord(AMapLocation location, MPointEntity mercatorPoint) {
        long currentTime = System.currentTimeMillis();
        long referTime = IUtils.calculateRefreshTime(IStringUtils.toInt(ISpfUtil.getValue(getApplicationContext(), Constants.TRACK_RECORD_MODE_KEY, 3)));
        if (!(currentTime - lastTrackRecordTime >= referTime)) {
            return;
        }

        if (lastLng > 0 && lastLat > 0) {
            distance = MapUtils.distanceBetween(lastLng, lastLat, location.getLongitude(), location.getLatitude());
        }

        if (distance > 0 && lastTrackRecordTime > 0) {
            speed = MapUtils.speedBetween(distance, currentTime - lastTrackRecordTime);
        }

        ILog.e(TAG, "lng=" + location.getLatitude() + ", lat=" + location.getLongitude() + ", provider=" + location.getProvider());
        lastTrackRecordTime = currentTime;
        lastLng = location.getLongitude();
        lastLat = location.getLatitude();

        ILog.d(TAG, "distance=" + distance + ", speed=" + speed);
        if (CalculatePointAvailabUtil.isAvailabPoint(lastLng, lastLat, lastTrackRecordTime)) {
            long id = IStringUtils.toLong(ISpfUtil.getValue(this, Constants.TRACK_RECORD_ID_KEY, "").toString());

            TrackPoint entity = new TrackPoint();
            entity.setPId(id);
            entity.setX(mercatorPoint.getX());
            entity.setY(mercatorPoint.getY());
            entity.setProvider(location.getProvider());
            entity.setGpsSpeed(location.getSpeed());
            entity.setLat(lastLat);
            entity.setLng(lastLng);
            entity.setTime(lastTrackRecordTime);
            entity.setSpeed(speed);
            entity.setDistance(distance);
//            ZNAPPlication.getInstance().getDaoSession().getTrackPointDao().insert(entity);
            /**
             * 修改距离
             */
//            List<Track> list = ZNAPPlication.getInstance().getDaoSession().getTrackDao().queryBuilder().where(TrackDao.Properties.Id.eq(id)).list();
//            if (list != null && list.size() > 0) {
//                Track track = list.get(0);
//                double d = track.getDistance();
//                track.setDistance(d + distance);
//                ZNAPPlication.getInstance().getDaoSession().getTrackDao().update(track);
//            }
        }
    }
}
