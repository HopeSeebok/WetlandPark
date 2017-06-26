package com.yunwei.wetlandpark.ui.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Describe:网络广播监听器
 * Author: hezhiWu
 * Date: 2016-08-03
 * Time: 22:28
 * Version:1.0
 */
public class NetworkBroadCastReceiver extends BroadcastReceiver {

    NetworkInfo.State wifiState = null;
    NetworkInfo.State mobileState = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        NoticeEvent event = new NoticeEvent();

        if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED == mobileState) {
            event.setFlag(EventConstant.NET_MOBILE_STATE);
        } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED == wifiState && NetworkInfo.State.CONNECTED != mobileState) {
            event.setFlag(EventConstant.NET_WIFI_STATE);
        } else if (wifiState != null && mobileState != null && NetworkInfo.State.CONNECTED != wifiState && NetworkInfo.State.CONNECTED != mobileState) {
            event.setFlag(EventConstant.NET_UNAVAILABLE);
            Toast.makeText(context,R.string.netword_invalid,Toast.LENGTH_SHORT).show();
        }
        EventBus.getDefault().post(event);
    }
}
