package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.biz.IDeviceByRFID;
import com.yunwei.wetlandpark.ui.biz.interfac.RequestDeviceByRFIDListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;


/**
 * @author wuhezhi
 * @ClassName: DeviceByRFIDBiz
 * @Description: 查询设施信息处理业务
 * @date 2016-7-3 下午3:52:12
 */
public class DeviceByRFIDBiz implements IDeviceByRFID, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private RequestDeviceByRFIDListener listener;
    private Activity activity;
    private String rfid;

    @Override
    public void requestDeviceByRFID(Activity activity, String rfid, RequestDeviceByRFIDListener listener) {
        this.listener = listener;
        this.activity = activity;
        this.rfid = rfid;
        String url = IConfigValues.DEVICE_INFO_BY_RFID + rfid;
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), url, this);
    }

    @Override
    public void onStart(@SuppressWarnings("rawtypes") AbstractRequest request) {
        if (listener != null) {
            listener.reqDeviceByRFIDStart();
        }
    }

    @Override
    public void onSuccess(Object o, @SuppressWarnings("rawtypes") Response response) {
        if (response.getHttpStatus().getCode() == 200) {
            ILog.d(TAG, "result===" + o.toString());
//            DeviceInfoEntity entity = ParseJson.toObject(o.toString(), DeviceInfoEntity.class);
//            if (entity.isSuccess()) {
//                listener.reqDeviceByRFIDSuccess(200, entity.getDevice());
//            } else if (IUtils.getStrToRes(activity, R.string.device_empty).equals(entity.getMessage())) {
//                /*设施不存在把RFID返回*/
//                listener.reqDeviceByRFIDFailure(Constants.DEVICE_EMPTY_VALUE, rfid);
//            }
        }
    }

    @Override
    public void onFailure(HttpException e, @SuppressWarnings("rawtypes") Response response) {
        if (listener != null) {
            listener.reqDeviceByRFIDFailure(-1, e.getMessage());
        }
    }

    @Override
    public void onEnd(@SuppressWarnings("rawtypes") Response response) {
        if (listener != null) {
            listener.reqDeviceByRFIDEnd();
        }
    }
}
