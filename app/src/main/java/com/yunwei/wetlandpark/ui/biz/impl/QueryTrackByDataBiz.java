package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.biz.IQueryTrackByData;
import com.yunwei.wetlandpark.ui.biz.interfac.QueryTrackByDataListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.config.IConfigValues;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:
 * @date 2016/9/18 17:03
 */
public class QueryTrackByDataBiz implements IQueryTrackByData, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private Activity activity;
    private QueryTrackByDataListener listener;

    @Override
    public void onStart(AbstractRequest request) {
        listener.onQueryTrackByDataStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG, "result==" + o.toString());
        if (response.getHttpStatus().getCode() == 200) {
            Type listType = new TypeToken<LinkedList<TrackEntity>>() {
            }.getType();
            List<TrackEntity> list = new Gson().fromJson(o.toString(), listType);
            listener.onQueryTrackByDataSuccess(list);
        } else {
            listener.onQueryTrackByDataFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        listener.onQueryTrackByDataFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
    }

    @Override
    public void onEnd(Response response) {
        listener.onQueryTrackByDataEnd();
    }

    @Override
    public void queryTrack(Activity activity, String data, QueryTrackByDataListener listener) {
        this.listener = listener;
        this.activity = activity;
        String url = IConfigValues.TRACK_QUERY_BY_DATA + data;
        ILog.d(TAG, "url==" + url);
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), url, this);
    }
}
