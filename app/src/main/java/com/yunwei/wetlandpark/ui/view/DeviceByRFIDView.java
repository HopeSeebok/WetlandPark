package com.yunwei.wetlandpark.ui.view;


import com.yunwei.wetlandpark.entity.DeviceEntity;

public interface DeviceByRFIDView extends BaseView {
	
	 void reqDeviceByRFIDStart();
	
	 void reqDeviceByRFIDEnd();

	 void reqDeviceByRFIDSuccess(int code,DeviceEntity entity);

	 void reqDeviceByRFIDFailure(int code, String msg);
}
