package com.yunwei.wetlandpark.common.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @Package: com.yunwei.zaina.common.handler
 * @Description:handler基类，防止handler引用Activity,造成内存溢出
 * @author: Aaron
 * @date: 2016-06-05
 * @Time: 15:12
 * @version: V1.0
 */
public class BAHandler extends Handler {
    WeakReference<Activity> weakReference;

    public BAHandler(Activity activity){
        weakReference=new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (weakReference.get()==null){
            throw new NullPointerException();
        }
    }
}
