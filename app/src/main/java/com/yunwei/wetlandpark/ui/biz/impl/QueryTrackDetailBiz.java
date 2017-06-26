package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.TrackDetailEntity;
import com.yunwei.wetlandpark.ui.biz.IQueryTrackDetail;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryTrackDetailListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.config.IConfigValues;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:
 * @date 2016/9/18 17:13
 */
public class QueryTrackDetailBiz implements IQueryTrackDetail, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private Activity activity;
    private QueryTrackDetailListener listener;

    @Override
    public void onStart(AbstractRequest request) {
        listener.onQueryTrackDetailStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG, "result==" + o.toString());
        if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {
            TrackDetailEntity entity = new Gson().fromJson(o.toString(), TrackDetailEntity.class);
            listener.onQueryTrackDetailSuccess(entity);
        } else {
            listener.onQueryTrackDetailFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        listener.onQueryTrackDetailFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
    }

    @Override
    public void onEnd(Response response) {
        listener.onQueryTrackDetailEnd();
    }

    @Override
    public void queryTrackDetail(Activity activity, int id, QueryTrackDetailListener listener) {
        this.activity = activity;
        this.listener = listener;
        String url = IConfigValues.TRACK_DETAIL_URL + id;
        ILog.d(TAG, "url==" + url);
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), url, this);
    }
}
