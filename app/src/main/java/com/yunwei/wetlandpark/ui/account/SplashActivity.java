package com.yunwei.wetlandpark.ui.account;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.handler.HandlerValue;
import com.yunwei.wetlandpark.ui.account.login.LoginActivity;
import com.yunwei.wetlandpark.ui.account.login.LoginPresenter;
import com.yunwei.wetlandpark.ui.base.BaseActivity;
import com.yunwei.wetlandpark.ui.mainFunctions.MainActivity;
import com.yunwei.wetlandpark.utils.ISkipActivityUtil;
import com.yunwei.wetlandpark.utils.ISpfUtil;
import com.yunwei.library.utils.IStringUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * @Package: com.yunwei.zaina.ui.activity
 * @Description:启动界面
 * @author: Aaron
 * @date: 2016-06-07
 * @Time: 15:34
 * @version: V1.0
 */
public class SplashActivity extends BaseActivity implements AccountContract.LoginView {

    private AccountContract.LoginPresenter loginPresenter;

    @Override
    public void dispatchMessage(Message msg) {
        switch (msg.what) {
            case HandlerValue.WELCOME_DELAYED_VALUE:
                ISkipActivityUtil.startIntent(this, LoginActivity.class);
                swipeBackLayout.setSwipeFinished(true);
                finish();
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_splash);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        if (IStringUtils.isEmpty(ISpfUtil.getValue(this, Constants.USER_NAME_KEY, "").toString()) || IStringUtils.isEmpty(ISpfUtil.getValue(this, Constants.USER_PWD_KEY, "").toString())) {
            sendEmptyMessageDelayed(mHandler, HandlerValue.WELCOME_DELAYED_VALUE, Constants.THREE_SECONDES);
        } else {
            loginPresenter = new LoginPresenter(this);
            loginPresenter.login();
        }
    }

    @Override
    public String[] getUserNameAndPassword() {
        String[] info = {ISpfUtil.getValue(this, Constants.USER_NAME_KEY, "").toString(),
                ISpfUtil.getValue(this, Constants.USER_PWD_KEY, "").toString()};
        return info;
    }

    @Override
    public void showLoginDialog() {
    }

    @Override
    public void dismissLoginDialog() {
    }

    @Override
    public void showLoginSuccessMsgAndFinishActivity() {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
        swipeBackLayout.setSwipeFinished(true);
        finish();
    }

    @Override
    public void showLoginFailedMsg(String msg) {
        sendEmptyMessage(mHandler, HandlerValue.WELCOME_DELAYED_VALUE);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
