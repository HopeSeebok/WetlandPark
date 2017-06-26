package com.yunwei.wetlandpark.ui.biz.interfac;


import com.yunwei.wetlandpark.entity.DeviceInfoEntity;

public interface RequestDeviceByIdListener {

	public void reqDeviceByIdStart();

	public void reqDeviceByIdEnd();

	public void reqDeviceByIdSuccess(DeviceInfoEntity entity);

	public void reqDeviceByIdFailure(int code, String msg);
}
