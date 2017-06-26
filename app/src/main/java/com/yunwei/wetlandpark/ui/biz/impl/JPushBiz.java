package com.yunwei.wetlandpark.ui.biz.impl;

import android.app.Activity;
import android.content.Context;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.config.IConfigValues;
import com.yunwei.library.http.LiteHttp.LiteHttpManage;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.camera.ui.biz.impl
 * @Description:
 * @date 2016/10/11
 * @changeby:
 */

public class JPushBiz {

    private final Context mContext;

    public JPushBiz(Context context) {
        mContext=context;
    }

    /**
     * 通知后台发离线消息
     */
    public void offlineMessage() {
        String url = IConfigValues.OFF_LINE_MESSAGE ;
        LiteHttpManage.Http_Get_Sync((Activity) mContext, ISpfUtil.getValue(mContext, Constants.ACCESS_TOKEN_KEY, "").toString(), url, null);
    }
}
