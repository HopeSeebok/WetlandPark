package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model;

import android.content.Context;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

import rx.Subscriber;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.water.ui.biz.impl.task
 * @Description:获取任务详情
 * @date 2016/9/26
 * @changeby:
 */

class MaintainDetailRequest implements HttpRequestCallBack {
    private Subscriber<? super String> mSub;

    public static MaintainDetailRequest getInstance() {
        return new MaintainDetailRequest();
    }

    void detailRequest(final Context context, final int taskId, Subscriber<? super String> sub) {
        mSub=sub;
        String url = IConfigValues.GET_MAINTAIN_INFO + taskId;
        String token = (String) ISpfUtil.getValue(context, Constants.ACCESS_TOKEN_KEY, "");
        LiteHttpManage.Http_Get_Sync(context, token, url, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
    }

    @Override
    public void onSuccess(Object o, Response response) {
        mSub.onNext(o.toString());
    }


    @Override
    public void onFailure(HttpException e, Response response) {
//        detailRequest(mContext, mTaskId, mSub);
    }

    @Override
    public void onEnd(Response response) {
    }
}
