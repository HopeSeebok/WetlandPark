package com.yunwei.wetlandpark.ui.biz;

import android.app.Activity;

import com.yunwei.wetlandpark.ui.biz.interfac.RequestDeviceByRFIDListener;


public interface IDeviceByRFID {

	public void requestDeviceByRFID(Activity activity, String rfid, RequestDeviceByRFIDListener listener);
	
}
