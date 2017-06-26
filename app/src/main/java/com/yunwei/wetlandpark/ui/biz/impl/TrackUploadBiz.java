package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.TrackEntity;
import com.yunwei.wetlandpark.ui.biz.ITrackUpload;
import com.yunwei.wetlandpark.ui.biz.interfac.TrackUploadListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.config.IConfigValues;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:
 * @date 2016/9/9 10:31
 */
public class TrackUploadBiz implements ITrackUpload, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private TrackUploadListener listener;
    private Activity activity;

    @Override
    public void uploadTrack(Activity activity, TrackEntity entity, TrackUploadListener listener) {
        this.listener = listener;
        this.activity = activity;
        JsonBody body = new JsonBody(new Gson().toJson(entity));
        LiteHttpManage.Http_Post_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), IConfigValues.TRACK_UPLOAD_URL, body, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listener.onStartTrackUpload();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG, "onUploadSuccess===" + o.toString());
        if (response.getHttpStatus().getCode() == 200 && !TextUtils.isEmpty(o.toString())) {
            listener.onSuccessTrackUpload(200, "上传成功");
        } else {
            listener.onFailureTrackUpload(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG,"error=="+e.getMessage());
        listener.onFailureTrackUpload(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
    }

    @Override
    public void onEnd(Response response) {
        listener.onEndTrackUpload();
    }
}
