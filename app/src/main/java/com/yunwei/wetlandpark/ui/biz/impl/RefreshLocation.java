package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.RefreshLoactionEntity;
import com.yunwei.wetlandpark.ui.biz.IRefreshLocation;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.gas.ui.biz
 * @Description:更新位置业务处理
 * @date 2016/7/28 9:38
 */
public class RefreshLocation implements IRefreshLocation, HttpRequestCallBack {
    private final String TAG = this.getClass().getSimpleName();


    @Override
    public void refreshlocation(Activity activity, RefreshLoactionEntity entity) {
        String json = new Gson().toJson(entity);
        JsonBody body = new JsonBody(json);
        ILog.d(TAG, "body==" + body + "Url==" + IConfigValues.REFRESH_LOCATION);
        LiteHttpManage.Http_Post_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), IConfigValues.REFRESH_LOCATION, body, this);
    }

    @Override
    public void onStart(AbstractRequest request) {

    }

    @Override
    public void onEnd(Response response) {

    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG,"error=="+e.getMessage());
//        ILog.d(TAG, "onFailure==" + response.getHttpStatus().getCode());
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG, "onUploadSuccess==" + response.getHttpStatus().getCode());
    }
}
