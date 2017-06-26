package com.yunwei.wetlandpark.ui.presenter;

import android.content.Context;

import com.yunwei.wetlandpark.ui.biz.impl.NotificationsBiz;
import com.yunwei.wetlandpark.ui.view.NotificationsView;


/**
 * @author zls
 * @version V1.0
 * @Package com.yunwei.hydrant.ui.presenter
 * @Description:
 * @date 2016/10/19
 * @changeby:
 */

public class NotificationsPresenter {

    private final Context mContext;
    private final NotificationsBiz mNotificationsBiz;
    private final NotificationsView mNotificationsView;

    public NotificationsPresenter(Context context, NotificationsView notificationsView) {
        mContext = context;
        mNotificationsBiz = new NotificationsBiz(context);
        mNotificationsView = notificationsView;
    }

    public void initUI() {
        mNotificationsView.setView(mNotificationsBiz.isVoiceVisiable(),mNotificationsBiz.isNoticeVisiable());
    }

    public void setVoiceVisiable(boolean checked) {
        mNotificationsBiz.setVoiceVisiable(checked);
    }

    public void setNoticeVisiable(boolean checked) {
        mNotificationsBiz.setNoticeVisiable(checked);
    }
}
