package com.yunwei.wetlandpark.ui.biz.impl;

import android.content.Context;

import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.utils.ISpfUtil;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.biz.impl
 * @Description:
 * @date 2016/10/19
 * @changeby:
 */
public class NotificationsBiz {
    private final Context mContext;

    public NotificationsBiz(Context context) {
        mContext=context;
    }

    public boolean isVoiceVisiable(){
        return (boolean) ISpfUtil.getValue(mContext, Constants.TASK_VOICE_TIPS,false);
    }

    public boolean isNoticeVisiable(){
        return (boolean) ISpfUtil.getValue(mContext, Constants.TASK_NOTICE_TIPS,false);
    }

    public void setVoiceVisiable(boolean visiable){
        ISpfUtil.setValue(mContext,Constants.TASK_VOICE_TIPS,visiable);
    }

    public void setNoticeVisiable(boolean visiable){
        ISpfUtil.setValue(mContext,Constants.TASK_NOTICE_TIPS,visiable);
    }
}
