package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.response.Response;
import com.yunwei.wetlandpark.BuildConfig;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.entity.UnitInfoEntity;
import com.yunwei.wetlandpark.ui.biz.IUnitListAction;
import com.yunwei.wetlandpark.ui.biz.interfac.UnitListListener;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.ParseJson;
import com.yunwei.library.http.LiteHttp.HttpRequestCallBack;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.cmcc.ui.biz.impl
 * @Description:请求公司列表
 * @date 2016/11/1 19:32
 */

public class UnitListBiz implements IUnitListAction, HttpRequestCallBack {
    private final String TAG = getClass().getSimpleName();

    private UnitListListener listListener;

    @Override
    public void reqUnitList(Activity activity, UnitListListener listListener) {
        this.listListener = listListener;
        LiteHttpManage.Http_Get_Sync(activity, ISpfUtil.getValue(activity, Constants.ACCESS_TOKEN_KEY, "").toString(), BuildConfig.DOMAI + BuildConfig.UNIT_LIST_URL, this);
    }

    @Override
    public void onStart(AbstractRequest request) {
        listListener.reqUnitListStart();
    }

    @Override
    public void onSuccess(Object o, Response response) {
        if (response.getHttpStatus().getCode() == 200) {
            ILog.d(TAG, "result===" + o.toString());
            UnitInfoEntity entity = ParseJson.toObject(o.toString(), UnitInfoEntity.class);
            if (entity == null) {
                listListener.reqUnitListFailure("数据为空");
                return;
            }
            if (entity.isSuccess()) {
                listListener.reqUnitListSuccess(entity.getEntities());
            } else {
                listListener.reqUnitListFailure("数据为空");
            }
        }
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        listListener.reqUnitListFailure("");
    }

    @Override
    public void onEnd(Response response) {
        listListener.reqUnitListEnd();
    }
}
