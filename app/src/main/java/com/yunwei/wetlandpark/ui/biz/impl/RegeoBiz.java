package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.entity.RegeoEntity;
import com.yunwei.wetlandpark.ui.biz.IRegeo;
import com.yunwei.wetlandpark.ui.biz.interfac.RegeoLisenter;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.config.IConfigValues;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:逆地理编码业务类
 * @date 2016/9/19 11:26
 */
public class RegeoBiz implements IRegeo, HttpRequestCallBack {
    private final String TAG=getClass().getSimpleName();

    private RegeoLisenter lisenter;
    private Activity activity;

    @Override
    public void onStart(AbstractRequest request) {
        lisenter.onRegeoStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        if (response.getHttpStatus().getCode() == 200&&!TextUtils.isEmpty(o.toString())) {
            ILog.d(TAG,"result=="+o.toString());
            Gson gson=new Gson();
            RegeoEntity entity=gson.fromJson(o.toString(),RegeoEntity.class);
            lisenter.onRegeoSuccess(entity);
        }else {
            lisenter.onRegeoFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        lisenter.onRegeoFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
    }

    @Override
    public void onEnd(Response response) {
        lisenter.onRegeoEnd();
    }

    @Override
    public void regeo(Activity activity, double lng, double lat, RegeoLisenter lisenter) {
        this.activity = activity;
        this.lisenter = lisenter;
        String url = IConfigValues.REVERSE_GEO_CODIN + "output=json&location=" + lng + "," + lat + "&key="+BuildConfig.REGEO_MAP_KEY;
        ILog.d(TAG,"url=="+url);
        LiteHttpManage.Http_Get_Sync(activity,url,this);
    }
}
