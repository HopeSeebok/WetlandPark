package com.yunwei.wetlandpark.ui.biz.interfac;


import com.yunwei.wetlandpark.entity.DeviceEntity;

public interface RequestDeviceByRFIDListener {

    void reqDeviceByRFIDStart();

    void reqDeviceByRFIDEnd();

    void reqDeviceByRFIDSuccess(int code, DeviceEntity entity);

    void reqDeviceByRFIDFailure(int code, String msg);
}
