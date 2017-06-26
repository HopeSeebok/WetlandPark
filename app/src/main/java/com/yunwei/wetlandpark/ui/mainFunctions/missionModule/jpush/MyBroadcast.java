package com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.activity.task.jpush
 * @Description:
 * @date 2016/11/25
 * @changeby:
 */

public class MyBroadcast extends BroadcastReceiver {
    private final static String ACTION = "com.lp.MyBroadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){
            if(ACTION.equals(intent.getAction())){
//                Toast.makeText(context, intent.getStringExtra("lp"), Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new MessageBean());
            }
        }
    }
}
