package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.content.JsonBody;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.HTTPResultEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.biz.IUpdateUserInfo;
import com.yunwei.wetlandpark.ui.biz.interfac.UpdateUserInfoListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl
 * @Description:
 * @date 2016/9/13 20:45
 */
public class UpdateUserInfoBiz implements IUpdateUserInfo, HttpRequestCallBack {
    private final String TAG=getClass().getSimpleName();

    private UpdateUserInfoListener listener;

    @Override
    public void updateUserInfo(Activity activity, Object o, UpdateUserInfoListener listener) {
        this.listener = listener;
        JsonBody body = new JsonBody(o);
//        ILog.d(TAG, "entity:"+body.toString());
        LiteHttpManage.Http_Put_Sync(activity,
                ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(),
                BuildConfig.DOMAI + BuildConfig.EDIT_USER_INFO_URL + ZNAPPlication.getUserInfoEntity(activity).getId(),
                body,
                this);
    }

    @Override
    public void updateUserPsd(Activity activity, Object o, UpdateUserInfoListener listener) {
        this.listener = listener;
        JsonBody body = new JsonBody(o);
        LiteHttpManage.Http_Put_Sync(activity,
                ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(),
                BuildConfig.DOMAI + BuildConfig.UPDATE_USER_PWD_URL + ZNAPPlication.getUserInfoEntity(activity).getId(),
                body,
                this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listener.onUpdateStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        ILog.d(TAG,"code==="+response.getHttpStatus().getCode());
        HTTPResultEntity entity = new Gson().fromJson(o.toString(), HTTPResultEntity.class);
        if (entity.isSuccess()){
            listener.onUpdateSuccess();
        }else {
            listener.onUpdateFailure(401,"更新失败");
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        ILog.e(TAG,"code=="+response.getHttpStatus().getCode());
        listener.onUpdateFailure(401,"更新失败");
    }

    @Override
    public void onEnd(Response response) {
        listener.onUpdateEnd();
    }
}
