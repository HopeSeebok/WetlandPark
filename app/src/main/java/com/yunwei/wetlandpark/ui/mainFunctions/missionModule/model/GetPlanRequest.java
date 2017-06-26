package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.model;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
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

public class GetPlanRequest implements HttpRequestCallBack {
    private Subscriber<? super String> mSub;

    public static GetPlanRequest getInstance() {
        return new GetPlanRequest();
    }

    public void getPlan(Subscriber<? super String> subscriber) {
        mSub=subscriber;
        String url = IConfigValues.DOMAIN + "v2/plan/now";
        String token = (String) ISpfUtil.getValue(ZNAPPlication.getInstance(), Constants.ACCESS_TOKEN_KEY, "");
        LiteHttpManage.Http_Get_Sync(ZNAPPlication.getInstance(), token, url, this);
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
