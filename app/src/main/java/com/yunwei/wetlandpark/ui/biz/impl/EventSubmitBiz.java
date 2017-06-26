package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.ui.biz.IEventSubmit;
import com.yunwei.wetlandpark.ui.biz.interfac.EventSubmitListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.impl
 * @Description:
 * @date 2016/8/25 14:22
 */
public class EventSubmitBiz implements IEventSubmit,HttpRequestCallBack {
    private final String TAG=getClass().getSimpleName();

    private EventSubmitListener listener;

    @Override
    public void submitEvent(Activity activity, Object object,EventSubmitListener listener) {
        this.listener=listener;
        JsonBody body=new JsonBody(object);
        ILog.d(TAG,"body=="+body);
//        LiteHttpManage.Http_Post_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY,"").toString(), IConfigValues.SUBMIT_EVENT_URL,body,this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listener.onEventStart();
    }

    @Override
    public void onEnd(Response response) {
        listener.onEventEnd();
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        listener.onEventFailure();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG,"code=="+response.getHttpStatus().getCode());
        listener.onEventSuccess();
    }
}
