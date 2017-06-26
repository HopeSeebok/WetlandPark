package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.ConfigEntity;
import com.yunwei.wetlandpark.ui.biz.IConfigSys;
import com.yunwei.wetlandpark.ui.biz.interfac.ConfigLinstener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.utils.ParseJson;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.drain.ui.biz.impl
 * @Description:请求系统配制文件业务类
 * @date 2016/8/24 16:28
 */
public class ConfigSysBiz implements IConfigSys, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private ConfigLinstener linstener;
    private Activity activity;

    @Override
    public void reqConfig(Activity activity, ConfigLinstener linstener) {
        this.linstener = linstener;
        this.activity = activity;
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), IConfigValues.CONFIG_URL, this);
    }

    //初始化业务的数据
    public ConfigEntity getConfigData(Context con) {
        String configInfo = (String)ISpfUtil.getValue(con, ConfigEntity.FLAG,"");
        ConfigEntity configData =ParseJson.toObject(configInfo,ConfigEntity.class);
        if(configData==null){
            configData = new ConfigEntity();
        }
        return configData;
    }

    @Override
    public void onEnd(Response response) {
        linstener.reqConfigEnd();
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG, "error==" + e.getMessage());
        linstener.reqConfigFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG, "result==" + o.toString());
        if (response.getHttpStatus().getCode()==200&&!TextUtils.isEmpty(o.toString())){
            ISpfUtil.setValue(activity,ConfigEntity.FLAG,o);
            linstener.reqConfigSuccess(null);
        }else {
            linstener.reqConfigFailure(401, IUtils.getStrToRes(activity, R.string.http_request_failure));
        }
    }

    @Override
    public void onStart(AbstractRequest request) {
        linstener.reqConfigStart();
    }



}
