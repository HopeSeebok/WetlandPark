package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.biz.IEventPoint;
import com.yunwei.wetlandpark.ui.biz.interfac.EventPointListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.impl
 * @Description:
 * @date 2016/8/26 15:59
 */
public class EventPointBiz implements IEventPoint, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private EventPointListener listener;
    private Activity activity;

    @Override
    public void reqEventPoint(Activity activity, String eventId, EventPointListener listener) {
        this.activity = activity;
        this.listener = listener;
//        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), IConfigValues.EVENT_POINT_URL + eventId, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listener.reqEventPointStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG,"result==="+o.toString());
        if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {

        } else {
            listener.reqEventPointfailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG,"error=="+e.getMessage());
        listener.reqEventPointfailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
    }

    @Override
    public void onEnd(Response response) {
        listener.reqEventPointEnd();
    }
}
