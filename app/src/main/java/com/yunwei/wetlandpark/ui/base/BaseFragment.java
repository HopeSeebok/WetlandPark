package com.yunwei.wetlandpark.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.yunwei.wetlandpark.R;
import com.yunwei.wetlandpark.common.Constants;
import com.yunwei.wetlandpark.common.dialog.ToastUtil;
import com.yunwei.wetlandpark.common.eventbus.NoticeEvent;
import com.yunwei.wetlandpark.common.handler.BAHandler;
import com.yunwei.wetlandpark.common.handler.HadlerUitls;
import com.yunwei.wetlandpark.ui.mainFunctions.TrackModule.FragmentInteraction;
import com.yunwei.wetlandpark.ui.mainFunctions.missionModule.jpush.MessageBean;
import com.yunwei.wetlandpark.utils.ILog;
import com.yunwei.wetlandpark.utils.ISpfUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Package: com.yunwei.zaina.ui.activity
 * @Description:Fragment基类
 * @author: Aaron
 * @date: 2016-05-31
 * @Time: 10:50
 * @version: V1.0
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 顶部通知栏View(duyang)
     * 只在主页四个Fragment中进行显示隐藏
     */
    private View mNotificationView;

    /**
     * 消息处理Handler
     */
    protected BAHandler mHandler;

    protected InputMethodManager mInput;

    protected Dialog dialog;


    /**
     * 用来与外部activity交互的
     */
    protected FragmentInteraction listterner;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentInteraction) {
            listterner = (FragmentInteraction) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mInput = (InputMethodManager) getActivity().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        initHandler();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden&&mNotificationView!=null) {
            boolean isSigned= (boolean) ISpfUtil.getValue(getActivity(), Constants.USER_SIGN_STATE_KEY,false);
            ILog.i(TAG, "BaseActivity#isSigned=" + isSigned);
            mNotificationView.setVisibility(isSigned?View.VISIBLE:View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化Handler
     */
    private void initHandler() {
        mHandler = new BAHandler(getActivity()) {
            @Override
            public void handleMessage(Message msg) {
                BaseFragment.this.dispatchMessage(msg);
            }
        };
    }

    /**
     * 初始化标题通知栏View（duyang）
     * @param view
     */
    public void initNotificationView(View view){
        ViewStub mNotificationViewStub=(ViewStub)view.findViewById(R.id.notification_view_stub);
        if (mNotificationViewStub != null) {
            boolean isSigned= (boolean) ISpfUtil.getValue(getActivity(), Constants.USER_SIGN_STATE_KEY,false);
            ILog.i(TAG, TAG+"#isSigned=" + isSigned);
            mNotificationView=mNotificationViewStub.inflate();
            mNotificationView.setVisibility(isSigned?View.VISIBLE:View.GONE);
        }
    }

    public void dispatchMessage(Message msg) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainUserEvent(NoticeEvent event) {
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackGroundUserEvent(NoticeEvent event) {

    }

    public static void sendEmptyMessage(Handler handler, int what) {
        HadlerUitls.sendEmptyMessage(handler, what);
    }

    public static void sendEmptyMessageDelayed(Handler handler, int what, long delaymillis) {
        HadlerUitls.sendEmptyMessageDelayed(handler, what, delaymillis);
    }

    public static void sendEmptyMessageAtTime(Handler handler, int what, long uptimeMillis) {
        HadlerUitls.sendEmptyMessageDelayed(handler, what, uptimeMillis);
    }

    public static void sendHandlerMessage(Handler handler, int what, Object obj) {
        HadlerUitls.sendHandlerMessage(handler, what, obj);
    }

    public static void sendHandlerMessage(Handler handler, int what, int arg1, Object obj) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1, obj);
    }

    public static void sendHandlerMessage(Handler handler, int what, int arg1, int arg2, Object obj) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1, arg2, obj);
    }

    public static void sendHandlerMessage(Handler handler, int what, int arg1) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1);
    }

    public static void sendHandlerMessage(Handler handler, int what, int arg1, int arg2) {
        HadlerUitls.sendHandlerMessage(handler, what, arg1, arg2);
    }

    public static void sendHandlerMessageDelayTime(Handler handler, int what, int arg1, int arg2, Object obj, long time) {
        HadlerUitls.sendHandlerMessageDelayTime(handler, what, arg1, arg2, obj, time);
    }

    public static void sendHandlerMessageAtTime(Handler handler, int what, int arg1, int arg2, Object obj, long time) {
        HadlerUitls.sendHandlerMessageAtTime(handler, what, arg1, arg2, obj, time);
    }

    /**
     * 显示软键盘
     *
     * @param edittext
     */
    public void showSoftInput(final EditText edittext) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                edittext.requestFocus();
                if (mInput != null)
                    mInput.showSoftInput(edittext, 0);
            }
        }, 700);
    }

    /**
     * 收起软键盘
     *
     * @param et
     */
    public void hideSoftInput(EditText et) {
        if (null != mInput && mInput.isActive())
            mInput.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public void showToast(int resid) {
        ToastUtil.showToast(getActivity(), resid);
    }

    public void showToast(String msg) {
        ToastUtil.showToast(getActivity(), msg);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainTaskEntity(MessageBean messageBean) {
    }

    /**
     * 设置标题通知栏Visilibity (duyang)
     */
    public void setNotificationViewVisibility(int visibility){
        if (mNotificationView != null) {
            mNotificationView.setVisibility(visibility);
        }
    }
}
