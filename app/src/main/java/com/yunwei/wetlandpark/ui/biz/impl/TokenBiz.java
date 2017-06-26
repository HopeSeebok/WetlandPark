package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.TokenEntity;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.biz.IToken;
import com.yunwei.wetlandpark.ui.biz.interfac.RequestTokenListener;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.ParseJson;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;


/**
 * @author wuhezhi
 * @ClassName: TokenBiz
 * @Description: Token请求业务处理
 * @date 2016-7-2 下午4:19:10
 */
public class  TokenBiz implements IToken, HttpRequestCallBack {
    private final String TAG = this.getClass().getSimpleName();

    private RequestTokenListener listener;
    private Activity activity;

    @Override
    public void requestToken(Activity activity, RequestTokenListener listener) {
        this.listener = listener;
        this.activity = activity;
        if (ZNAPPlication.getUserInfoEntity(activity) == null) {
            return;
        }
        LiteHttpManage.Http_Get_Sync(activity, ZNAPPlication.getUserInfoEntity(activity).getAccess_token(), BuildConfig.DOMAI + BuildConfig.QINIU_TOKEN_URL, this);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onStart(AbstractRequest request) {
        if (listener != null) {
            listener.getTokenStart();
        }
    }

    @Override
    public void onSuccess(Object o, Response response) {
        if (!TextUtils.isEmpty(o.toString())) {
            TokenEntity entity = ParseJson.toObject(o.toString(), TokenEntity.class);
            if (entity.isSuccess()) {
                ISpfUtil.setValue(activity, Constants.QINIU_TOKEN_KEY, entity.getData());
                listener.getTokenSuccess(entity.getData());
            }
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        Log.d(TAG, "onFailure " + e.getMessage() + "code==" + response.getHttpStatus().getCode());
        if (listener != null) {
            listener.getTokenFailure();
        }
    }

    @Override
    public void onEnd(Response response) {
    }
}
