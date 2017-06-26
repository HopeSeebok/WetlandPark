package com.yunwei.wetlandpark.ui.mainFunctions.mineModule.frgment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.eventbus.EventConstant;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.ui.account.AccountInfoFragment;
import com.yunwei.wetlandpark.ui.account.login.LoginActivity;
import com.yunwei.wetlandpark.ui.base.BaseFragment;
import com.yunwei.wetlandpark.ui.base.ZNAPPlication;
import com.yunwei.wetlandpark.ui.mainFunctions.mineModule.SetingInfoActvity;
import com.yunwei.wetlandpark.utils.IActivityManage;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.wetlandpark.utils.IUtils;
import com.yunwei.wetlandpark.view.RoundedBitmapImageViewTarget;
import com.yunwei.library.dialog.DialogFactory;
import com.yunwei.library.utils.ImageLoadUrlFitter;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;

/**
 * Describe:我的界面
 * Author: hezhiWu
 * Date: 2016-08-28
 * Time: 10:00
 * Version:1.0
 */
public class MineFragment extends BaseFragment {

    private ImageView headIv;
    private TextView nameTv, unitTv;

    public static BaseFragment newInstance(){
        return new MineFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        findViewById(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUI();
    }

    private void findViewById(View view) {
        view.findViewById(R.id.set_msg_text).setOnClickListener(this);
        view.findViewById(R.id.set_track_text).setOnClickListener(this);
        view.findViewById(R.id.set_idea_text).setOnClickListener(this);
        view.findViewById(R.id.set_about_text).setOnClickListener(this);
        view.findViewById(R.id.set_change_user_text).setOnClickListener(this);
        view.findViewById(R.id.set_exit_text).setOnClickListener(this);
        view.findViewById(R.id.head_layout).setOnClickListener(this);

        headIv = (ImageView) view.findViewById(R.id.account_head_iv);
        nameTv = (TextView) view.findViewById(R.id.mine_name_tv);
        unitTv = (TextView) view.findViewById(R.id.mine_dev_tv);
        initNotificationView(view);
    }

    private void initUI() {
        if (ZNAPPlication.getUserInfoEntity(getActivity()) != null) {
            ILog.d(TAG, ZNAPPlication.getUserInfoEntity(getActivity()).toString());
            Glide.with(getActivity()).load(ZNAPPlication.getUserInfoEntity(getActivity()).getIcon()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headIv));
            nameTv.setText(ZNAPPlication.getUserInfoEntity(getActivity()).getName());
            unitTv.setText(ZNAPPlication.getUserInfoEntity(getActivity()).getGroup());
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.head_layout:
                bundle.putString(SetingInfoActvity.HEAD_TITLE_FLAG, "个人资料");
                bundle.putString(SetingInfoActvity.SHOW_FRAGMENT_FLAG, AccountInfoFragment.ACCOUNT_INFO_FLAG);
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActvity.class, bundle);
                break;
            case R.id.set_msg_text:
                bundle.putString(SetingInfoActvity.HEAD_TITLE_FLAG, "消息提醒设置");
                bundle.putString(SetingInfoActvity.SHOW_FRAGMENT_FLAG, NotificationsFragment.NOTIFICATIONS_INFO_FLAG);
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActvity.class, bundle);
                break;
            case R.id.set_track_text:
                bundle.putString(SetingInfoActvity.HEAD_TITLE_FLAG, "足迹记录设置");
                bundle.putString(SetingInfoActvity.SHOW_FRAGMENT_FLAG, TrackSetFragment.SET_TRACK_VALUE);
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActvity.class, bundle);
                break;
            case R.id.set_idea_text:
                bundle.putString(SetingInfoActvity.HEAD_TITLE_FLAG, "意见反馈");
                bundle.putString(SetingInfoActvity.SHOW_FRAGMENT_FLAG, SuggestionFragment.SUGGESTION_INFO_FLAG);
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActvity.class, bundle);
                break;
            case R.id.set_about_text:
                bundle.putString(SetingInfoActvity.HEAD_TITLE_FLAG, "关于");
                bundle.putString(SetingInfoActvity.SHOW_FRAGMENT_FLAG, AboutFragment.ABOUT_FLAG);
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActvity.class, bundle);
                break;
            case R.id.set_change_user_text:
                switchAccountDialog();
                break;
            case R.id.set_exit_text:
                exitDialog();
                break;
        }
    }

    /**
     * 切换用户Dialog
     */
    private void switchAccountDialog() {
        DialogFactory.showMsgDialog(getActivity(), "提示", "是否注销当前用户?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JPushInterface.setAlias(getActivity(), "", null);
                ZNAPPlication.getInstance().clearUserInfo();
                IActivityManage.getInstance().exit();
                ISpfUtil.setValue(getActivity(), Constants.USER_PWD_KEY, "");
                ISkipActivityUtil.startIntent(getActivity(), LoginActivity.class);
            }
        });
    }

    /**
     * 退出Dialog
     *
     */
    private void exitDialog() {
        DialogFactory.showMsgDialog(getActivity(), "提示", "确定要退出" + IUtils.getStrToRes(getActivity(), R.string.app_name) + "?",
                v -> IActivityManage.getInstance().exit());
    }

    @Override
    public void onMainUserEvent(NoticeEvent event) {
        super.onMainUserEvent(event);
        switch (event.getFlag()) {
            case EventConstant.NOTICE6:
                Glide.with(getActivity()).load(ImageLoadUrlFitter.fitterUrl(ZNAPPlication.getUserInfoEntity(getActivity()).getIcon())).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(headIv));
                break;
        }
    }
}
