package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.biz.IEventTypeList;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.impl
 * @Description:
 * @date 2016/8/19 14:48
 */
public class EventTypeListBiz implements IEventTypeList, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private Activity activity;

    @Override
    public void reqEvent(Activity activity) {
        this.activity = activity;
//        LiteHttpManage.Http_Get_Sync(activity, IConfigValues.EVENT_TYPE_LIST, this);
    }

    @Override
    public void onStart(AbstractRequest request) {

    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG, "result==" + o.toString());
        if (response.getHttpStatus().getCode() == 200&&!TextUtils.isEmpty(o.toString())) {
            ISpfUtil.setValue(activity, Constants.EVENT_VALUE_KEY, o.toString());
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG,"e=="+e.getMessage());
    }

    @Override
    public void onEnd(Response response) {

    }
}
