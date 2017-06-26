package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;


import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.presenter.NotificationsPresenter;
import com.yunwei.wetlandpark.ui.view.NotificationsView;
import com.yunwei.wetlandpark.view.SwitchButton;


/**
 * @Package: com.yunwei.zaina.ui.activity.set.fragment
 * @Description:消息提醒设置
 * @author: Aaron
 * @date: 2016-06-26
 * @Time: 14:18
 * @version: V1.0
 */
public class NotificationsFragment extends BaseFragment implements NotificationsView {

    public static final String NOTIFICATIONS_INFO_FLAG = "NOTIFICATIONS_info";
    private NotificationsPresenter mNotificationsPresenter;
    private SwitchButton mNotificationTaskVoiceSb;
    private SwitchButton mNotificationTaskNoticeSb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, null);
        initView(view);
        mNotificationsPresenter = new NotificationsPresenter(getActivity(), this);
        mNotificationsPresenter.initUI();
        return view;
    }

    private void initView(View view) {
        mNotificationTaskVoiceSb = (SwitchButton) view.findViewById(R.id.Notification_TaskVoice_sb);
        mNotificationTaskVoiceSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNotificationsPresenter.setVoiceVisiable(mNotificationTaskVoiceSb.isChecked());
            }
        });
        mNotificationTaskNoticeSb = (SwitchButton) view.findViewById(R.id.Notification_TaskNotice_sb);
        mNotificationTaskNoticeSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNotificationsPresenter.setNoticeVisiable(mNotificationTaskNoticeSb.isChecked());
            }
        });
    }

    @Override
    public void setView(boolean voiceVisiable, boolean noticeVisiable) {
        mNotificationTaskVoiceSb.setChecked(voiceVisiable);
        mNotificationTaskNoticeSb.setChecked(noticeVisiable);
    }

}
